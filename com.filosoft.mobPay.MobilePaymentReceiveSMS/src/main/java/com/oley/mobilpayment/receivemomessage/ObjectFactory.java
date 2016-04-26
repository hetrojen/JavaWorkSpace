
package com.oley.mobilpayment.receivemomessage;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.oley.mobilpayment.receivemomessage package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Input_QNAME = new QName("http://cservices.mikro-odeme.com.tr/mapping/generated", "input");
    private final static QName _CReceiveMOMessageOutput_QNAME = new QName("http://cservices.mikro-odeme.com.tr/mapping/generated", "CReceiveMOMessageOutput");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.oley.mobilpayment.receivemomessage
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CReceiveMOMessageInput }
     * 
     */
    public CReceiveMOMessageInput createCReceiveMOMessageInput() {
        return new CReceiveMOMessageInput();
    }

    /**
     * Create an instance of {@link CReceiveMOMessageOutput }
     * 
     */
    public CReceiveMOMessageOutput createCReceiveMOMessageOutput() {
        return new CReceiveMOMessageOutput();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CReceiveMOMessageInput }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cservices.mikro-odeme.com.tr/mapping/generated", name = "input")
    public JAXBElement<CReceiveMOMessageInput> createInput(CReceiveMOMessageInput value) {
        return new JAXBElement<CReceiveMOMessageInput>(_Input_QNAME, CReceiveMOMessageInput.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CReceiveMOMessageOutput }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cservices.mikro-odeme.com.tr/mapping/generated", name = "CReceiveMOMessageOutput")
    public JAXBElement<CReceiveMOMessageOutput> createCReceiveMOMessageOutput(CReceiveMOMessageOutput value) {
        return new JAXBElement<CReceiveMOMessageOutput>(_CReceiveMOMessageOutput_QNAME, CReceiveMOMessageOutput.class, null, value);
    }

}
