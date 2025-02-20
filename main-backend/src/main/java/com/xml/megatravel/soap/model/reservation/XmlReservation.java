//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.22 at 05:37:40 PM CEST 
//


package com.xml.megatravel.soap.model.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.xml.megatravel.soap.model.base.XmlBase;
import com.xml.megatravel.soap.model.property.XmlProperty;
import com.xml.megatravel.soap.model.rating.XmlRating;
import com.xml.megatravel.soap.model.user.XmlUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


/**
 * <p>Java class for XmlReservation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="XmlReservation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.xml.com/megatravel/soap/model/base}XmlBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="property" type="{http://www.xml.com/megatravel/soap/model/property}XmlProperty"/&gt;
 *         &lt;element name="user" type="{http://www.xml.com/megatravel/soap/model/user}XmlUser"/&gt;
 *         &lt;element name="rating" type="{http://www.xml.com/megatravel/soap/model/rating}XmlRating"/&gt;
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="numberOfPeople" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="reservationStatus"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="PENDING"/&gt;
 *               &lt;enumeration value="SUCCESSFUL"/&gt;
 *               &lt;enumeration value="CANCELED"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XmlReservation", propOrder = {
    "property",
    "user",
    "rating",
    "startDate",
    "endDate",
    "numberOfPeople",
    "price",
    "reservationStatus"
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class XmlReservation
    extends XmlBase
{

    @XmlElement(required = true)
    protected XmlProperty property;
    @XmlElement(required = true)
    protected XmlUser user;
    @XmlElement(required = true)
    protected XmlRating rating;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;
    protected int numberOfPeople;
    protected double price;
    @XmlElement(required = true)
    protected String reservationStatus;

    /**
     * Gets the value of the property property.
     * 
     * @return
     *     possible object is
     *     {@link XmlProperty }
     *     
     */
    public XmlProperty getProperty() {
        return property;
    }

    /**
     * Sets the value of the property property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlProperty }
     *     
     */
    public void setProperty(XmlProperty value) {
        this.property = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link XmlUser }
     *     
     */
    public XmlUser getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlUser }
     *     
     */
    public void setUser(XmlUser value) {
        this.user = value;
    }

    /**
     * Gets the value of the rating property.
     * 
     * @return
     *     possible object is
     *     {@link XmlRating }
     *     
     */
    public XmlRating getRating() {
        return rating;
    }

    /**
     * Sets the value of the rating property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlRating }
     *     
     */
    public void setRating(XmlRating value) {
        this.rating = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the numberOfPeople property.
     * 
     */
    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    /**
     * Sets the value of the numberOfPeople property.
     * 
     */
    public void setNumberOfPeople(int value) {
        this.numberOfPeople = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the reservationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReservationStatus() {
        return reservationStatus;
    }

    /**
     * Sets the value of the reservationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReservationStatus(String value) {
        this.reservationStatus = value;
    }

}
