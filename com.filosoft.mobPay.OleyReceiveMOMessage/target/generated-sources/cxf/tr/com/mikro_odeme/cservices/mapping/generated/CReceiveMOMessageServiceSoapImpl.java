
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package tr.com.mikro_odeme.cservices.mapping.generated;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

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
                      
public class CReceiveMOMessageServiceSoapImpl implements CReceiveMOMessageServiceSoap {

    private static final Logger LOG = Logger.getLogger(CReceiveMOMessageServiceSoapImpl.class.getName());

    /* (non-Javadoc)
     * @see tr.com.mikro_odeme.cservices.mapping.generated.CReceiveMOMessageServiceSoap#receiveMOMessage(tr.com.mikro_odeme.cservices.mapping.generated.CReceiveMOMessageInput  ınput )*
     */
    public tr.com.mikro_odeme.cservices.mapping.generated.CReceiveMOMessageOutput receiveMOMessage(CReceiveMOMessageInput ınput) { 
     
    	
    	
    	LOG.info("Executing operation receiveMOMessage");
        System.out.println(ınput);
        try {
            tr.com.mikro_odeme.cservices.mapping.generated.CReceiveMOMessageOutput _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}