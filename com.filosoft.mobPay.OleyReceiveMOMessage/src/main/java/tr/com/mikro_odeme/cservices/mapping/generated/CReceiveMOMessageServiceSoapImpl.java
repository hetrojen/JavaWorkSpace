
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package tr.com.mikro_odeme.cservices.mapping.generated;

import org.apache.cxf.interceptor.OutInterceptors;

import com.oley.payment.mobile.request.dao.PaymentRequestDAO;
import com.oley.payment.mobile.request.model.PaymentRequest;
import com.oley.payment.mobile.request.test.InsertPaymentRequestTester;

/**
 * This class was generated by Apache CXF 2.6.1
 * 2012-08-14T14:12:56.377+03:00
 * Generated source version: 2.6.1
 * 
 */

@javax.jws.WebService(
                      serviceName = "CReceiveMOMessageService",
                      portName = "CReceiveMOMessageServiceSoap",
                      targetNamespace = "http://cservices.mikro-odeme.com.tr/mapping/generated",
                      wsdlLocation = "file:/D:/Development/workspaceMobilPayment/OleyReceiveMOMessage/src/main/resources/CReceiveMOMessageService.wsdl",
                      endpointInterface = "tr.com.mikro_odeme.cservices.mapping.generated.CReceiveMOMessageServiceSoap")
@OutInterceptors(interceptors = "org.apache.cxf.interceptor.LoggingOutInterceptor")       
public class CReceiveMOMessageServiceSoapImpl implements CReceiveMOMessageServiceSoap {
	private PaymentRequestDAO paymentRequestDAO;
 //   private static final Logger LOG = Logger.getLogger(CReceiveMOMessageServiceSoapImpl.class.getName());

    /* (non-Javadoc)
     * @see tr.com.mikro_odeme.cservices.mapping.generated.CReceiveMOMessageServiceSoap#receiveMOMessage(tr.com.mikro_odeme.cservices.mapping.generated.CReceiveMOMessageInput  ınput )*
     */
	

    public tr.com.mikro_odeme.cservices.mapping.generated.CReceiveMOMessageOutput receiveMOMessage(CReceiveMOMessageInput ınput) { 
     //   LOG.info("Executing operation receiveMOMessage");
        System.out.println(ınput);
        CReceiveMOMessageOutput messageOutput=new CReceiveMOMessageOutput();
        try {  
        PaymentRequest paymentRequest=PaymentRequest.convertSMStoObject(ınput);
        paymentRequestDAO.insertPaymentRequest(paymentRequest);
  
    	messageOutput.setStatusCode(CReceiveMOMessageServiceSoap.RECEIVE_MO_MESSAGE_SUCCESS_CODE);
    	messageOutput.setErrorMessage(RECEIVE_MO_MESSAGE_SUCCESS_MESSAGE);
        return messageOutput;
   
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            messageOutput.setStatusCode(CReceiveMOMessageServiceSoap.RECEIVE_MO_MESSAGE_FAILURE_CODE);
            messageOutput.setErrorCode(CReceiveMOMessageServiceSoap.SYSTEM_ERROR_CODE);
            messageOutput.setErrorMessage(ex.getMessage());
            
        	messageOutput.setErrorMessage(RECEIVE_MO_MESSAGE_SUCCESS_MESSAGE);
        	 //todo 
            //throw new RuntimeException(ex);
            return messageOutput;
        }
    }

	public void setPaymentRequestDAO(PaymentRequestDAO paymentRequestDAO) {
		this.paymentRequestDAO = paymentRequestDAO;
	}

}