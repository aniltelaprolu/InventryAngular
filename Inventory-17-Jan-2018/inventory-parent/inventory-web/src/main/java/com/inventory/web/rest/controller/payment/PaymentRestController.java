/**
 * 
 */
package com.inventory.web.rest.controller.payment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

import com.inventory.common.constants.AppConstants.INVENTORYPAYMENTSTATUS;
import com.inventory.common.constants.AppConstants.PAYBY;
import com.inventory.common.constants.AppConstants.POSTATUS;
import com.inventory.common.constants.AppConstants.STATUS;
import com.inventory.common.modal.company.profile.CompanyBank;
import com.inventory.common.modal.payment.InventoryPayment;
import com.inventory.common.modal.product.Product;
import com.inventory.common.modal.product.Variant;
import com.inventory.common.modal.purchase.order.PurchaseOrder;
import com.inventory.common.modal.purchase.order.PurchaseOrderDetail;
import com.inventory.common.modal.receive.order.ReceivedOrder;
import com.inventory.common.modal.vendor.InvVendor;
import com.inventory.common.modal.vendor.VendorBankAccountDetails;
import com.inventory.common.service.company.profile.CompanyBankService;
import com.inventory.common.service.payment.PaymentService;
import com.inventory.common.service.product.ProductService;
import com.inventory.common.service.purchase.PurchaseOrderService;
import com.inventory.common.service.receive.order.ReceivedOrderDetailsService;
import com.inventory.common.service.receive.order.ReceivedOrderService;
import com.inventory.common.service.vendor.InvVendorService;
import com.inventory.common.service.vendor.VendorBankDetailsService;
import com.inventory.web.rest.vo.base.ResponseVO;
import com.inventory.web.rest.vo.base.SearchResponseVO;
import com.inventory.web.rest.vo.combobox.PurchaseOrderComboVO;
import com.inventory.web.rest.vo.combobox.ReceivedOrderComboVO;
import com.inventory.web.rest.vo.payment.PaymentVO;
import com.inventory.web.rest.vo.purchase.PurchaseOrderDetailsVO;
import com.inventory.web.rest.vo.purchase.PurchaseOrderVO;

/**
 * @author ES003
 *
 */
@RestController
@RequestMapping("service/payment")
public class PaymentRestController {
	private static final Logger logger = LoggerFactory.getLogger(PaymentRestController.class);

	@Autowired
	private InvVendorService vendorService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@Autowired
	private ReceivedOrderService receivedOrderService;
	
	@Autowired
	private ReceivedOrderDetailsService receivedOrderDetailsService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private VendorBankDetailsService vendorBankDetailsService;
	
	@Autowired
	private CompanyBankService companyBankService;
	
	
	/**
	 * 
	 * @param vaendorId
	 * @return
	 */
	@RequestMapping(value = "/getAllPurchaseOrderStatusByVendorId", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<PurchaseOrderComboVO>> getAllPurchaseOrderStatusByVendorId(@RequestParam("vendorId") Long vendorId) {
		logger.info("Retriving  PurchaseOrder Status : {}");

		ResponseVO<PurchaseOrderComboVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<PurchaseOrderComboVO>> response = new ResponseEntity<ResponseVO<PurchaseOrderComboVO>>(HttpStatus.OK);
		
		try {
			List<Object[]> listPOStatus = purchaseOrderService.getPurchaseOrderStatus(vendorId);
			
			if(listPOStatus== null) {
				message.setMessageId("NOT_FOUND");
				message.setMessageDetail("Details Not Found!");
				return new ResponseEntity<ResponseVO<PurchaseOrderComboVO>>(message, HttpStatus.NOT_FOUND);
			}

			if (listPOStatus != null) {
				PurchaseOrderComboVO purchaseOrderComboVO=null;
				List<PurchaseOrderComboVO> listComboVO=new ArrayList<>();
				for (Object[] obj : listPOStatus) {
					purchaseOrderComboVO = new PurchaseOrderComboVO();
					
					POSTATUS poStatus = (POSTATUS)obj[0];
					Long purchaseOrderId =(Long)obj[1];
					
					
					purchaseOrderComboVO.setPurchaseId(purchaseOrderId);
					purchaseOrderComboVO.setPoStatus(poStatus);
					
					listComboVO.add(purchaseOrderComboVO);
					
					message.setMessageId("STATUS_FOUND");
					message.setMessageDetail("Status Found!");
					message.setData(listComboVO); 
					response = new ResponseEntity<ResponseVO<PurchaseOrderComboVO>>(message, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving Status");
			message.setMessageId("STATUS_NOT_FOUND");
			message.setMessageDetail("Exception occured while Retreving Status");
			response = new ResponseEntity<ResponseVO<PurchaseOrderComboVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	
	/**
	 * 
	 * @param poStatus
	 * @return
	 */
	@RequestMapping(value = "/getPurchaseOrderNumberByStatus", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<PurchaseOrderComboVO>> getPurchaseOrderNumberByStatus(@RequestParam("poStatus") POSTATUS poStatus) {
		logger.info("Retriving  PurchaseOrderNumber : {}");

		ResponseVO<PurchaseOrderComboVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<PurchaseOrderComboVO>> response = new ResponseEntity<ResponseVO<PurchaseOrderComboVO>>(HttpStatus.OK);
		
		try {
			List<Object[]> listPONos = purchaseOrderService.getPurchaseOrderNoByStatus(poStatus);
			
			if(poStatus==null) {
				message.setMessageId("NOT_FOUND");
				message.setMessageDetail("Not Found!");
				return new ResponseEntity<ResponseVO<PurchaseOrderComboVO>>(message, HttpStatus.NOT_FOUND);
			}
			
			if(listPONos== null || listPONos.isEmpty()) {
				message.setMessageId("NOT_FOUND");
				message.setMessageDetail("Details Not Found!");
				return new ResponseEntity<ResponseVO<PurchaseOrderComboVO>>(message, HttpStatus.NOT_FOUND);
			}

			if (listPONos != null) {
				PurchaseOrderComboVO purchaseOrderComboVO=null;
				List<PurchaseOrderComboVO> listComboVO=new ArrayList<>();
				for (Object[] obj : listPONos) {
					purchaseOrderComboVO = new PurchaseOrderComboVO();
					
					String purchaseOrderNo = (String)obj[0];
					Long purchaseOrderId = (Long)obj[1];
					
					purchaseOrderComboVO.setPurchaseId(purchaseOrderId);
					purchaseOrderComboVO.setPurchaseNO(purchaseOrderNo);
					
					listComboVO.add(purchaseOrderComboVO);
					
					message.setMessageId("NUMBER_FOUND");
					message.setMessageDetail("PurchaseOrderNumber Found!");
					message.setData(listComboVO); 
					response = new ResponseEntity<ResponseVO<PurchaseOrderComboVO>>(message, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving PurchaseOrderNumber");
			message.setMessageId("NUMBER_NOT_FOUND");
			message.setMessageDetail("Exception occured while Retreving PurchaseOrderNumber");
			response = new ResponseEntity<ResponseVO<PurchaseOrderComboVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	
	
	/**
	 * 
	 * @param purchaseOrderId
	 * @return
	 */
	@RequestMapping(value = "/getReceivedOrderNumberByPurchaseId", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<ReceivedOrderComboVO>> getReceivedOrderNumberByPurchaseId(@RequestParam("purchaseOrderId") Long purchaseOrderId) {
		logger.info("Retriving  ReceivedOrderNumber : {}");

		ResponseVO<ReceivedOrderComboVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<ReceivedOrderComboVO>> response = new ResponseEntity<ResponseVO<ReceivedOrderComboVO>>(HttpStatus.OK);
		
		try {
			List<Object[]> listRONos = receivedOrderService.getAllReceivedOrderNumber(purchaseOrderId);		
			if(listRONos== null) {
				message.setMessageId("NOT_FOUND");
				message.setMessageDetail("Details Not Found!");
				return new ResponseEntity<ResponseVO<ReceivedOrderComboVO>>(message, HttpStatus.NOT_FOUND);
			}

			if (listRONos != null) {
				ReceivedOrderComboVO receivedOrderComboVO=null;
				List<ReceivedOrderComboVO> listComboVO=new ArrayList<>();
				for (Object[] obj : listRONos) {
					receivedOrderComboVO = new ReceivedOrderComboVO();
					
					Long receivedOrderId = (Long)obj[0];
					String receivedOrderNo = (String)obj[1];
					
					receivedOrderComboVO.setReceivedOrderId(receivedOrderId);
					receivedOrderComboVO.setReceivedOrderNo(receivedOrderNo);
					
					listComboVO.add(receivedOrderComboVO);
					
					message.setMessageId("NUMBER_FOUND");
					message.setMessageDetail("ReceivedOrderNumber Found!");
					message.setData(listComboVO); 
					response = new ResponseEntity<ResponseVO<ReceivedOrderComboVO>>(message, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving ReceivedOrderNumber");
			message.setMessageId("NUMBER_NOT_FOUND");
			message.setMessageDetail("Exception occured while Retreving ReceivedOrderNumber");
			response = new ResponseEntity<ResponseVO<ReceivedOrderComboVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	
	/**
	 * 
	 * @param receivedOrderId
	 * @return
	 */
	@RequestMapping(value = "/getTotalAmountDetails", method = RequestMethod.GET)
	public ResponseEntity<ResponseVO<ReceivedOrderComboVO>> getTotalAmountDetails(@RequestParam("receivedOrderId") Long receivedOrderId) {
		logger.info("Retriving  Details: {}");

		ResponseVO<ReceivedOrderComboVO> message = new ResponseVO<>();
		ResponseEntity<ResponseVO<ReceivedOrderComboVO>> response = new ResponseEntity<ResponseVO<ReceivedOrderComboVO>>(HttpStatus.OK);
		
		try {
			/*List<Object[]> listData = receivedOrderDetailsService.getTotalAmountDetails(receivedOrderId);
			
			if(listData== null) {
				message.setMessageId("NOT_FOUND");
				message.setMessageDetail("Details Not Found!");
				return new ResponseEntity<ResponseVO<ReceivedOrderComboVO>>(message, HttpStatus.NOT_FOUND);
			}

			if (listData != null) {
				ReceivedOrderComboVO receivedOrderComboVO=null;
				List<ReceivedOrderComboVO> listComboVO=new ArrayList<>();
				receivedOrderComboVO = new ReceivedOrderComboVO();
				Double totalAmount =0d;
				if(listData!=null) {
					for (Object[] obj : listData) {
						Double price = (Double)obj[0];
						int quantity = (int)obj[1];
						if(price!=0 && quantity!=0) {
						totalAmount =totalAmount+price*quantity;
						}
						
					}
					receivedOrderComboVO.setTotalAmount(totalAmount);
					listComboVO.add(receivedOrderComboVO);
					message.setMessageId("NUMBER_FOUND");
					message.setMessageDetail("Found!");
					message.setData(listComboVO); 
					response = new ResponseEntity<ResponseVO<ReceivedOrderComboVO>>(message, HttpStatus.OK);
				}	
				
				}
				*/	
			ReceivedOrderComboVO receivedOrderComboVO = new ReceivedOrderComboVO();
			List<ReceivedOrderComboVO> listComboVO=new ArrayList<>();
			
			Double dueAmount = receivedOrderService.getTotalDueAmount(receivedOrderId);
			receivedOrderComboVO.setTotalAmount(dueAmount);
			listComboVO.add(receivedOrderComboVO);
			message.setMessageId("NUMBER_FOUND");
			message.setMessageDetail("Found!");
			message.setData(listComboVO); 
			response = new ResponseEntity<ResponseVO<ReceivedOrderComboVO>>(message, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while Retreving Data");
			message.setMessageId("NOT_FOUND");
			message.setMessageDetail("Exception occured while Retreving Data");
			response = new ResponseEntity<ResponseVO<ReceivedOrderComboVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	
	/**
	 * 
	 * @param paymentVO
	 * @return
	 */
	@RequestMapping(value = "/makePayment", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO<PaymentVO>> makePayment(@RequestBody PaymentVO paymentVO) {
		ResponseVO<PaymentVO> message = new ResponseVO<PaymentVO>();
		ResponseEntity<ResponseVO<PaymentVO>> response = new ResponseEntity<ResponseVO<PaymentVO>>(HttpStatus.OK);

		if (paymentVO == null) {
			message.setMessageId("BLANK_DATA");
			message.setMessageDetail("Payment data not found");
		}

		try {
			if (paymentVO != null) {
				InventoryPayment payment = new InventoryPayment();
				InvVendor vendor = vendorService.findByVendorId(paymentVO.getVendorId());
				PurchaseOrder purchaseOrder = purchaseOrderService.findByPurchaseOrderId(paymentVO.getPurchaseOrderId());
				ReceivedOrder receivedOrder = receivedOrderService.findById(paymentVO.getReceivedOrderId());
				VendorBankAccountDetails bankDetails = null;
				
				CompanyBank companyBank = null;
				
				payment.setInvVendor(vendor);
				payment.setPurchaseOrder(purchaseOrder);
				payment.setPoStatus(payment.getPoStatus());
				payment.setReceivedOrder(receivedOrder);
				payment.setTotalAmount(paymentVO.getTotalAmount());
				payment.setPayAmount(paymentVO.getPayAmount());
				payment.setPayBy(paymentVO.getPayBy());
				payment.setPoStatus(paymentVO.getPoStatus());
				Double dueAmount = receivedOrderService.getTotalDueAmount(receivedOrder.getReceivedOrderId());
				if(dueAmount-paymentVO.getPayAmount()==0) {
					payment.setInventoryPaymentStatus(INVENTORYPAYMENTSTATUS.COMPLETED);
				}
				else {
				payment.setInventoryPaymentStatus(INVENTORYPAYMENTSTATUS.PARTIAL);
				}
				if(PAYBY.BANKTRANSFER.equals(paymentVO.getPayBy())) {
					companyBank = companyBankService.findByCompanyBankId(paymentVO.getCompanyBankId());
					bankDetails = vendorBankDetailsService.findById(paymentVO.getVendorAccountId());
					payment.setVendorBankAccountDetails(bankDetails);
					payment.setCompanyBank(companyBank);
				}
				if(PAYBY.CHEQUE.equals(paymentVO.getPayBy())) {
					payment.setChequeNo(paymentVO.getChequeNo());
				}
				
				InventoryPayment pay = paymentService.savePaymentData(payment);
				
				receivedOrderService.updateDueAmount(paymentVO.getTotalAmount()-paymentVO.getPayAmount(), pay.getReceivedOrder().getReceivedOrderId());
				
				message.setMessageId("FOUND");
				message.setMessageDetail("Successfully Done");
				response = new ResponseEntity<ResponseVO<PaymentVO>>(message, HttpStatus.OK);
			} else {
				message.setMessageId("BLANK_DATA");
				message.setMessageDetail("Payment data not found");
				response = new ResponseEntity<ResponseVO<PaymentVO>>(message, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			logger.error("Exception Occured While Doing Payment");
			e.printStackTrace();
			message.setMessageId("TECHNICAL_ERROR");
			message.setMessageDetail("Payment Failed");
			response = new ResponseEntity<ResponseVO<PaymentVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}
	
	
	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/getAllPaymentDetails", method = RequestMethod.GET)
	public ResponseEntity<SearchResponseVO<PaymentVO>> getAllPurchaseDetails(@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize) {
		logger.info("Fetching All Payment Details");

		SearchResponseVO<PaymentVO> message = new SearchResponseVO<>();
		ResponseEntity<SearchResponseVO<PaymentVO>> response = new ResponseEntity<SearchResponseVO<PaymentVO>>(HttpStatus.OK);

		try {
			PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);
			List<InventoryPayment> listPayment = paymentService.findAll(pageRequest);

			PaymentVO paymentVO = null;
			List<PaymentVO> listPaymentVO = new ArrayList<>();

			for (InventoryPayment payment : listPayment) {
				paymentVO = new PaymentVO();
				paymentVO.setVendorName(payment.getInvVendor().getName());
				paymentVO.setTotalAmount(payment.getTotalAmount());
				paymentVO.setPayAmount(payment.getPayAmount());
				paymentVO.setPoStatus(payment.getPoStatus());
				paymentVO.setPayBy(payment.getPayBy());
				paymentVO.setInventoryPaymentStatus(payment.getInventoryPaymentStatus());
				listPaymentVO.add(paymentVO);
			}

			message.setMessageId("GET_ALL_PAYMENT_DETAILS");
			message.setMessageDetail("Get All Payment Details");
			message.setData(listPaymentVO);
			response = new ResponseEntity<SearchResponseVO<PaymentVO>>(message, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error occured while gatting all Payment Details", e);
			e.printStackTrace();
			message.setMessageId("PAYMENT_FAILED");
			message.setMessageDetail("Error occured while Retriving Payment Details");
			response = new ResponseEntity<SearchResponseVO<PaymentVO>>(message, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

	
}
