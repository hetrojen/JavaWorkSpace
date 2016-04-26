
package com.mikro_odeme.services.sale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MSaleInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MSaleInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MPAY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Gsm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SendOrderResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PaymentTypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProductList" type="{http://services.mikro-odeme.com/}ArrayOfMSaleProduct" minOccurs="0"/>
 *         &lt;element name="ReceivedSMSObjectId" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="SendNotificationSMS" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="OnSuccessfulSMS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnErrorSMS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RequestGsmOperator" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RequestGsmType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TurkcellServiceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Extra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomerIpAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MSaleInput", propOrder = {
    "mpay",
    "gsm",
    "content",
    "sendOrderResult",
    "paymentTypeId",
    "url",
    "productList",
    "receivedSMSObjectId",
    "sendNotificationSMS",
    "onSuccessfulSMS",
    "onErrorSMS",
    "requestGsmOperator",
    "requestGsmType",
    "turkcellServiceId",
    "extra",
    "customerIpAddress"
})
@XmlSeeAlso({
    CreditCardProxyInput.class
})
public class MSaleInput {

    @XmlElement(name = "MPAY")
    protected String mpay;
    @XmlElement(name = "Gsm")
    protected String gsm;
    @XmlElement(name = "Content")
    protected String content;
    @XmlElement(name = "SendOrderResult")
    protected boolean sendOrderResult;
    @XmlElement(name = "PaymentTypeId")
    protected int paymentTypeId;
    @XmlElement(name = "Url")
    protected String url;
    @XmlElement(name = "ProductList")
    protected ArrayOfMSaleProduct productList;
    @XmlElement(name = "ReceivedSMSObjectId", required = true)
    protected String receivedSMSObjectId;
    @XmlElement(name = "SendNotificationSMS")
    protected boolean sendNotificationSMS;
    @XmlElement(name = "OnSuccessfulSMS")
    protected String onSuccessfulSMS;
    @XmlElement(name = "OnErrorSMS")
    protected String onErrorSMS;
    @XmlElement(name = "RequestGsmOperator")
    protected int requestGsmOperator;
    @XmlElement(name = "RequestGsmType")
    protected int requestGsmType;
    @XmlElement(name = "TurkcellServiceId")
    protected String turkcellServiceId;
    @XmlElement(name = "Extra")
    protected String extra;
    @XmlElement(name = "CustomerIpAddress")
    protected String customerIpAddress;

    /**
     * Gets the value of the mpay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMPAY() {
        return mpay;
    }

    /**
     * Sets the value of the mpay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMPAY(String value) {
        this.mpay = value;
    }

    /**
     * Gets the value of the gsm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGsm() {
        return gsm;
    }

    /**
     * Sets the value of the gsm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGsm(String value) {
        this.gsm = value;
    }

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the sendOrderResult property.
     * 
     */
    public boolean isSendOrderResult() {
        return sendOrderResult;
    }

    /**
     * Sets the value of the sendOrderResult property.
     * 
     */
    public void setSendOrderResult(boolean value) {
        this.sendOrderResult = value;
    }

    /**
     * Gets the value of the paymentTypeId property.
     * 
     */
    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    /**
     * Sets the value of the paymentTypeId property.
     * 
     */
    public void setPaymentTypeId(int value) {
        this.paymentTypeId = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the productList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMSaleProduct }
     *     
     */
    public ArrayOfMSaleProduct getProductList() {
        return productList;
    }

    /**
     * Sets the value of the productList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMSaleProduct }
     *     
     */
    public void setProductList(ArrayOfMSaleProduct value) {
        this.productList = value;
    }

    /**
     * Gets the value of the receivedSMSObjectId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivedSMSObjectId() {
        return receivedSMSObjectId;
    }

    /**
     * Sets the value of the receivedSMSObjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivedSMSObjectId(String value) {
        this.receivedSMSObjectId = value;
    }

    /**
     * Gets the value of the sendNotificationSMS property.
     * 
     */
    public boolean isSendNotificationSMS() {
        return sendNotificationSMS;
    }

    /**
     * Sets the value of the sendNotificationSMS property.
     * 
     */
    public void setSendNotificationSMS(boolean value) {
        this.sendNotificationSMS = value;
    }

    /**
     * Gets the value of the onSuccessfulSMS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnSuccessfulSMS() {
        return onSuccessfulSMS;
    }

    /**
     * Sets the value of the onSuccessfulSMS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnSuccessfulSMS(String value) {
        this.onSuccessfulSMS = value;
    }

    /**
     * Gets the value of the onErrorSMS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnErrorSMS() {
        return onErrorSMS;
    }

    /**
     * Sets the value of the onErrorSMS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnErrorSMS(String value) {
        this.onErrorSMS = value;
    }

    /**
     * Gets the value of the requestGsmOperator property.
     * 
     */
    public int getRequestGsmOperator() {
        return requestGsmOperator;
    }

    /**
     * Sets the value of the requestGsmOperator property.
     * 
     */
    public void setRequestGsmOperator(int value) {
        this.requestGsmOperator = value;
    }

    /**
     * Gets the value of the requestGsmType property.
     * 
     */
    public int getRequestGsmType() {
        return requestGsmType;
    }

    /**
     * Sets the value of the requestGsmType property.
     * 
     */
    public void setRequestGsmType(int value) {
        this.requestGsmType = value;
    }

    /**
     * Gets the value of the turkcellServiceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTurkcellServiceId() {
        return turkcellServiceId;
    }

    /**
     * Sets the value of the turkcellServiceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTurkcellServiceId(String value) {
        this.turkcellServiceId = value;
    }

    /**
     * Gets the value of the extra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtra() {
        return extra;
    }

    /**
     * Sets the value of the extra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtra(String value) {
        this.extra = value;
    }

    /**
     * Gets the value of the customerIpAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerIpAddress() {
        return customerIpAddress;
    }

    /**
     * Sets the value of the customerIpAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerIpAddress(String value) {
        this.customerIpAddress = value;
    }

}
