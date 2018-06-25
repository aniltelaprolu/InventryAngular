/**
 * 
 */
package com.inventory.web.rest.controller.received.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.common.constants.AppConstants.POSTATUS;
import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.product.Product;
import com.inventory.common.modal.product.Variant;
import com.inventory.common.modal.purchase.order.PurchaseOrder;
import com.inventory.common.modal.receive.order.InventoryItem;
import com.inventory.common.modal.receive.order.ReceivedOrder;
import com.inventory.common.modal.receive.order.ReceivedOrderDetail;
import com.inventory.common.modal.vendor.InvVendor;
import com.inventory.common.service.product.ProductService;
import com.inventory.common.service.product.VariantService;
import com.inventory.common.service.purchase.PurchaseOrderDetailsService;
import com.inventory.common.service.purchase.PurchaseOrderService;
import com.inventory.common.service.receive.order.InventoryItemService;
import com.inventory.common.service.receive.order.ReceivedOrderDetailsService;
import com.inventory.common.service.receive.order.ReceivedOrderService;
import com.inventory.common.service.vendor.InvVendorService;
import com.inventory.web.rest.vo.base.ResponseVO;
import com.inventory.web.rest.vo.received.ReceivedOrderDetailsVO;
import com.inventory.web.rest.vo.received.ReceivedOrderVO;

/**
 * 
 * @author ES003
 *
 */
@RestController
@RequestMapping("service/received")
public class ReceivedOrderRestController {
	private static final Logger logger = LoggerFactory.getLogger(ReceivedOrderRestController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private InvVendorService vendorService;

	@Autowired
	private VariantService variantService;

	@Autowired
	private PurchaseOrderService purchaseService;

	@Autowired
	private ReceivedOrderService receivedOrderService;

	@Autowired
	private ReceivedOrderDetailsService receivedOrderDetailsService;

	@Autowired
	private PurchaseOrderDetailsService purchaseOrderDetailsService;

	@Autowired
	private InventoryItemService inventoryItemService;

	/**
	 * 
	 * @param receVO
	 * @return
	 */
	private String generateReceivedOrderNumber(ReceivedOrderVO receVO) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		return "RNO" + timeStamp;
	}

	/**
	 * 
	 * @param receivedOrderVO
	 * @return
	 */
	@RequestMapping(value = "/receivedOrder", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<ReceivedOrderVO>> receivedOrder(@RequestBody ReceivedOrderVO receivedOrderVO) {

		ResponseVO<ReceivedOrderVO> message = new ResponseVO<ReceivedOrderVO>();

		ResponseEntity<ResponseVO<ReceivedOrderVO>> response = new ResponseEntity<ResponseVO<ReceivedOrderVO>>(
				HttpStatus.OK);

		if (receivedOrderVO == null) {
			message.setMessageId("BLANK_DATA");
			message.setMessageDetail("Received data not found");
		}

		
		try {
			if (receivedOrderVO != null) {

				ReceivedOrder receivedOrder = new ReceivedOrder();
				List<ReceivedOrder> list = new ArrayList<>();
				InvVendor invVendor = vendorService.findByVendorId(receivedOrderVO.getVendorId());
				receivedOrder.setShippingAddress(receivedOrderVO.getShippingAddress());
				receivedOrder.setBillingAddress(receivedOrderVO.getBillingAddress());
				receivedOrder.setReceivedOrderDate(receivedOrderVO.getReceivedOrderDate());
				receivedOrder.setShippingDate(receivedOrderVO.getShippingDate());
				receivedOrder.setStatus(STATUS.ACTIVE);
				receivedOrder.setReceviedOrderNo(generateReceivedOrderNumber(receivedOrderVO));

				PurchaseOrder purchaseOrder = purchaseService
						.findByPurchaseOrderId(receivedOrderVO.getPurchaseOrderId());
				Set<ReceivedOrderDetailsVO> setVO = receivedOrderVO.getListReceivedDetails();

				ReceivedOrderDetail details = null;
				Set<ReceivedOrderDetail> setDetails = new HashSet<>();

				if (setVO != null) {

					for (ReceivedOrderDetailsVO vo : setVO) {
						details = new ReceivedOrderDetail();
						details.setPrice(vo.getPrice());
						details.setQuantity(vo.getQuantity());

						receivedOrder.setPurchaseOrder(purchaseOrder);
						int OriginalleftQuantity = purchaseOrderDetailsService.getAllQuantityDetails(
								receivedOrderVO.getPurchaseOrderId(), vo.getProductId(), vo.getVariantId());
						purchaseOrderDetailsService.updateLeftQuantityByPurchaseOrderId(
								OriginalleftQuantity - vo.getQuantity(), receivedOrderVO.getPurchaseOrderId(),
								vo.getProductId(), vo.getVariantId());

						Product product = productService.findById(vo.getProductId());
						Variant variant = variantService.findByVariantId(vo.getVariantId());
						details.setProduct(product);
						details.setVariant(variant);
						details.setReceivedOrder(receivedOrder);
						setDetails.add(details);

					}
				}

				receivedOrder.setReceivedOrderDetail(setDetails);
				receivedOrder.setInvVendor(invVendor);
				list.add(receivedOrder);
				invVendor.setReveivedOrder(list);

				ReceivedOrder receiveOrder = receivedOrderService.saveReceiveOrderData(receivedOrder);

				List<Object[]> listDetails = receivedOrderDetailsService
						.getOrderDetails(receiveOrder.getReceivedOrderId());

				Double totalAmount = 0d;
				for (Object[] data : listDetails) {
					Long productId = (Long) data[0];
					Long variantId = (Long) data[1];
					Double price = (Double) data[2];
					int quantity = (int) data[3];
					totalAmount = totalAmount + price * quantity;
					System.out.println(productId + "  " + variantId + "  " + price + "  " + quantity);

					for (int i = 0; i < quantity; i++) {
						InventoryItem item = new InventoryItem();
						item.setProduct(productService.findById(productId));
						item.setVariant(variantService.findByVariantId(variantId));
						item.setInvVendor(invVendor);
						item.setPurchasePrice(price);
						item.setReceivedOrder(receivedOrder);
						inventoryItemService.saveInventoryItemData(item);
					}
				}
				receivedOrderService.updateDueAmount(totalAmount, receiveOrder.getReceivedOrderId());

				List<Integer> leftQuantity = purchaseOrderDetailsService
						.getAllLeftQuantity(receivedOrderVO.getPurchaseOrderId());

				int totalQuantity = 0;
				for (Integer quantity : leftQuantity) {
					totalQuantity = totalQuantity + quantity;
				}

				if (totalQuantity != 0) {
					purchaseService.updatePurchaseOrderStatus(purchaseOrder.getPurchaseOrderId(), POSTATUS.PARTIALLY);
				} else {
					purchaseService.updatePurchaseOrderStatus(purchaseOrder.getPurchaseOrderId(), POSTATUS.COMPLETED);
				}

				message.setMessageId("FOUND");
				message.setMessageDetail("Successfully Done");
				response = new ResponseEntity<ResponseVO<ReceivedOrderVO>>(message, HttpStatus.OK);
			} else {
				message.setMessageId("BLANK_DATA");
				message.setMessageDetail("Received data not found");
				response = new ResponseEntity<ResponseVO<ReceivedOrderVO>>(message, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			logger.error("Exception Occured While saving Received Data");
			e.printStackTrace();
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("Received Failed");
			response = new ResponseEntity<ResponseVO<ReceivedOrderVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
}
