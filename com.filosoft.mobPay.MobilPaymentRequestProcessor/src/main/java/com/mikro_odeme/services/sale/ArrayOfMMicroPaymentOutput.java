
package com.mikro_odeme.services.sale;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMMicroPaymentOutput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMMicroPaymentOutput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MMicroPaymentOutput" type="{http://services.mikro-odeme.com/}MMicroPaymentOutput" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMMicroPaymentOutput", propOrder = {
    "mMicroPaymentOutput"
})
public class ArrayOfMMicroPaymentOutput {

    @XmlElement(name = "MMicroPaymentOutput", nillable = true)
    protected List<MMicroPaymentOutput> mMicroPaymentOutput;

    /**
     * Gets the value of the mMicroPaymentOutput property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mMicroPaymentOutput property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMMicroPaymentOutput().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MMicroPaymentOutput }
     * 
     * 
     */
    public List<MMicroPaymentOutput> getMMicroPaymentOutput() {
        if (mMicroPaymentOutput == null) {
            mMicroPaymentOutput = new ArrayList<MMicroPaymentOutput>();
        }
        return this.mMicroPaymentOutput;
    }

}
