package com.inventory.web.rest.controller.purchase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.common.constants.AppConstants.POSTATUS;
import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.product.Product;
import com.inventory.common.modal.product.Variant;
import com.inventory.common.modal.purchase.order.PurchaseOrder;
import com.inventory.common.modal.purchase.order.PurchaseOrderDetail;
import com.inventory.common.modal.vendor.InvVendor;
import com.inventory.common.modal.vendor.VendorAddress;
import com.inventory.common.service.SendPurchaseAttachmentService;
import com.inventory.common.service.product.ProductService;
import com.inventory.common.service.product.VariantService;
import com.inventory.common.service.purchase.PurchaseOrderService;
import com.inventory.common.service.vendor.InvVendorService;
import com.inventory.web.rest.vo.base.ResponseVO;
import com.inventory.web.rest.vo.base.SearchResponseVO;
import com.inventory.web.rest.vo.purchase.PurchaseOrderDetailsVO;
import com.inventory.web.rest.vo.purchase.PurchaseOrderVO;
import com.inventory.web.util.pdfgen.PdfGenaratorUtil;

@RestController
@RequestMapping("service/purchase")
public class PurchaseOrderRestController {
	private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderRestController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private InvVendorService vendorService;

	@Autowired
	private VariantService variantService;

	@Autowired
	private PurchaseOrderService purchaseService;
	
	@Autowired
	private PdfGenaratorUtil util;

	@Autowired
	private SendPurchaseAttachmentService purchaseSendAttachment;
	
	
	private String generateOrderNumber(PurchaseOrderVO purchVO) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		Long venId = purchVO.getVendorId();
		System.out.println(timeStamp + venId);
		return "PNO" + timeStamp + venId;
	}

	/**
	 * 
	 * @param PurchaseOrderVO
	 * @return
	 */
	@RequestMapping(value = "/purchaseOrder", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<PurchaseOrderVO>> addPurchaseDetails(@RequestBody PurchaseOrderVO PurchaseOrderVO) {
		ResponseVO<PurchaseOrderVO> message = new ResponseVO<PurchaseOrderVO>();
		ResponseEntity<ResponseVO<PurchaseOrderVO>> response = new ResponseEntity<ResponseVO<PurchaseOrderVO>>(HttpStatus.OK);

		if (PurchaseOrderVO == null) {
			message.setMessageId("BLANK_DATA");
			message.setMessageDetail("Purchase data not found");
		}

		try {
			if (PurchaseOrderVO != null) {
				//int totalQuantity = 0;
				PurchaseOrder purchaseOrder = new PurchaseOrder();
				List<PurchaseOrder> list = new ArrayList<>();
				InvVendor invVendor = vendorService.findByVendorId(PurchaseOrderVO.getVendorId());
				purchaseOrder.setShippingAddress(PurchaseOrderVO.getShippingAddress());
				purchaseOrder.setBillingAddress(PurchaseOrderVO.getBillingAddress());
				purchaseOrder.setOrderDate(PurchaseOrderVO.getOrderDate());
				purchaseOrder.setRequiredByDate(PurchaseOrderVO.getRequiredByDate());
				purchaseOrder.setPoStatus(POSTATUS.PENDING);
				purchaseOrder.setStatus(STATUS.ACTIVE);
				purchaseOrder.setPurchaseOrderNo(generateOrderNumber(PurchaseOrderVO));

				Set<PurchaseOrderDetailsVO> setVO = PurchaseOrderVO.getListpurchaseDetails();
				Set<PurchaseOrderDetail> setDetails = new HashSet<>();
				if (setVO != null) {
					for (PurchaseOrderDetailsVO vo : setVO) {
						PurchaseOrderDetail details = new PurchaseOrderDetail();
						details.setLeftQuantity(vo.getQuantity());
						details.setQuantity(vo.getQuantity());
						Product product = productService.findById(vo.getProductId());
						Variant variant = variantService.findByVariantId(vo.getVariantId());
						details.setProduct(product);
						details.setVariant(variant);
						details.setPurchaseOrder(purchaseOrder);
						//totalQuantity = totalQuantity + vo.getQuantity();
						setDetails.add(details);
						//purchaseOrder.setTotalQuantity(totalQuantity);
					}
				}

				purchaseOrder.setPurchaseOrderDetail(setDetails);
				purchaseOrder.setInvVendor(invVendor);
				list.add(purchaseOrder);
				invVendor.setPurchaseOrder(list);

				purchaseService.savepurchaseData(purchaseOrder);
				Map<Object,Object> pdfMap = new HashMap<>();
				pdfMap.put("purchaseOrder", purchaseOrder);
				pdfMap.put("purchaseOrderDetail", purchaseOrder.getPurchaseOrderDetail());
				pdfMap.put("vendorName", purchaseOrder.getInvVendor());
				pdfMap.put("vendorAddrs", purchaseOrder.getInvVendor().getVenAddress().get(0));
				String pdfFile = util.createPdf("purchasepdf", pdfMap);
				
				System.out.println("PDF==================>"+pdfFile);
				
				purchaseSendAttachment.sendPurchaseAttachment(invVendor.getEmail(), pdfFile);
				
				message.setMessageId("FOUND");
				message.setMessageDetail("Successfully Done");
				response = new ResponseEntity<ResponseVO<PurchaseOrderVO>>(message, HttpStatus.OK);
			} else {
				message.setMessageId("BLANK_DATA");
				message.setMessageDetail("Purchase data not found");
				response = new ResponseEntity<ResponseVO<PurchaseOrderVO>>(message, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			logger.error("Exception Occured While Purchase");
			e.printStackTrace();
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("Purchase Failed");
			response = new ResponseEntity<ResponseVO<PurchaseOrderVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/getAllPurchaseDetails", method = RequestMethod.GET)
	public ResponseEntity<SearchResponseVO<PurchaseOrderVO>> getAllPurchaseDetails(@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize) {
		logger.info("Fetching All Purchase Details");

		SearchResponseVO<PurchaseOrderVO> message = new SearchResponseVO<>();
		ResponseEntity<SearchResponseVO<PurchaseOrderVO>> response = new ResponseEntity<SearchResponseVO<PurchaseOrderVO>>(
				HttpStatus.OK);

		try {
			PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);
			List<PurchaseOrder> listPurchase = purchaseService.findAll(pageRequest);

			PurchaseOrderVO purchaseOrderVO = null;
			List<PurchaseOrderVO> listPurchaseOrderVO = new ArrayList<>();

			for (PurchaseOrder purchaseOrder : listPurchase) {
				purchaseOrderVO = new PurchaseOrderVO();
				purchaseOrderVO.setPurchaseId(purchaseOrder.getPurchaseOrderId());
				purchaseOrderVO.setPurchaseNO(purchaseOrder.getPurchaseOrderNo());
				purchaseOrderVO.setOrderDate(purchaseOrder.getOrderDate());
				purchaseOrderVO.setRequiredByDate(purchaseOrder.getRequiredByDate());
				purchaseOrderVO.setShippingAddress(purchaseOrder.getShippingAddress());
				purchaseOrderVO.setBillingAddress(purchaseOrder.getBillingAddress());
				listPurchaseOrderVO.add(purchaseOrderVO);
			}

			message.setMessageId("GET_ALL_PURCHASE_DETAILS");
			message.setMessageDetail("Get All Purchase Details");
			message.setData(listPurchaseOrderVO);
			response = new ResponseEntity<SearchResponseVO<PurchaseOrderVO>>(message, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error occured while gatting all Purchase Details", e);
			e.printStackTrace();
			message.setMessageId("GET_ALL_PURCHASE_FAILED");
			message.setMessageDetail("Error occured while Retriving Purchase Details");
			response = new ResponseEntity<SearchResponseVO<PurchaseOrderVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

}