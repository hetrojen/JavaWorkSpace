
package com.oley.mobilpayment.receivemomessage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for CReceiveMOMessageInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CReceiveMOMessageInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReceivedSMSId" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="SenderGSM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReceiverGSM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OriginalContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FixedContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DateReceived" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="GsmOperator" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="GsmType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CReceiveMOMessageInput", propOrder = {
    "receivedSMSId",
    "senderGSM",
    "receiverGSM",
    "originalContent",
    "fixedContent",
    "dateReceived",
    "gsmOperator",
    "gsmType"
})
public class CReceiveMOMessageInput {

    @XmlElement(name = "ReceivedSMSId", required = true)
    protected String receivedSMSId;
    @XmlElement(name = "SenderGSM")
    protected String senderGSM;
    @XmlElement(name = "ReceiverGSM")
    protected String receiverGSM;
    @XmlElement(name = "OriginalContent")
    protected String originalContent;
    @XmlElement(name = "FixedContent")
    protected String fixedContent;
    @XmlElement(name = "DateReceived", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateReceived;
    @XmlElement(name = "GsmOperator")
    protected int gsmOperator;
    @XmlElement(name = "GsmType")
    protected int gsmType;

    /**
     * Gets the value of the receivedSMSId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivedSMSId() {
        return receivedSMSId;
    }

    /**
     * Sets the value of the receivedSMSId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivedSMSId(String value) {
        this.receivedSMSId = value;
    }

    /**
     * Gets the value of the senderGSM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderGSM() {
        return senderGSM;
    }

    /**
     * Sets the value of the senderGSM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderGSM(String value) {
        this.senderGSM = value;
    }

    /**
     * Gets the value of the receiverGSM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverGSM() {
        return receiverGSM;
    }

    /**
     * Sets the value of the receiverGSM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverGSM(String value) {
        this.receiverGSM = value;
    }

    /**
     * Gets the value of the originalContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalContent() {
        return originalContent;
    }

    /**
     * Sets the value of the originalContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalContent(String value) {
        this.originalContent = value;
    }

    /**
     * Gets the value of the fixedContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFixedContent() {
        return fixedContent;
    }

    /**
     * Sets the value of the fixedContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFixedContent(String value) {
        this.fixedContent = value;
    }

    /**
     * Gets the value of the dateReceived property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateReceived() {
        return dateReceived;
    }

    /**
     * Sets the value of the dateReceived property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateReceived(XMLGregorianCalendar value) {
        this.dateReceived = value;
    }

    /**
     * Gets the value of the gsmOperator property.
     * 
     */
    public int getGsmOperator() {
        return gsmOperator;
    }

    /**
     * Sets the value of the gsmOperator property.
     * 
     */
    public void setGsmOperator(int value) {
        this.gsmOperator = value;
    }

    /**
     * Gets the value of the gsmType property.
     * 
     */
    public int getGsmType() {
        return gsmType;
    }

    /**
     * Sets the value of the gsmType property.
     * 
     */
    public void setGsmType(int value) {
        this.gsmType = value;
    }

}
