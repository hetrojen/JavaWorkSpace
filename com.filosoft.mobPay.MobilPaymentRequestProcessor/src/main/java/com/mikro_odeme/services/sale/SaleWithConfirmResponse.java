
package com.mikro_odeme.services.sale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SaleWithConfirmResult" type="{http://services.mikro-odeme.com/}MSaleOutput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "saleWithConfirmResult"
})
@XmlRootElement(name = "SaleWithConfirmResponse")
public class SaleWithConfirmResponse {

    @XmlElement(name = "SaleWithConfirmResult")
    protected MSaleOutput saleWithConfirmResult;

    /**
     * Gets the value of the saleWithConfirmResult property.
     * 
     * @return
     *     possible object is
     *     {@link MSaleOutput }
     *     
     */
    public MSaleOutput getSaleWithConfirmResult() {
        return saleWithConfirmResult;
    }

    /**
     * Sets the value of the saleWithConfirmResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link MSaleOutput }
     *     
     */
    public void setSaleWithConfirmResult(MSaleOutput value) {
        this.saleWithConfirmResult = value;
    }

}
