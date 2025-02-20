//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.22 at 05:37:40 PM CEST 
//


package com.xml.megatravel.soap.model.user;

import com.xml.megatravel.soap.model.base.XmlBase;
import com.xml.megatravel.soap.model.category.XmlCategory;
import com.xml.megatravel.soap.model.image.XmlImage;
import com.xml.megatravel.soap.model.reservation.XmlReservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for XmlUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="XmlUser"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.xml.com/megatravel/soap/model/base}XmlBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="firstNme"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="0"/&gt;
 *               &lt;maxLength value="128"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="lastName"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="0"/&gt;
 *               &lt;maxLength value="128"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="username"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="6"/&gt;
 *               &lt;maxLength value="32"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="password"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="64"/&gt;
 *               &lt;maxLength value="64"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="email" type="{http://www.xml.com/megatravel/soap/model/user}email"/&gt;
 *         &lt;element name="role"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="USER"/&gt;
 *               &lt;enumeration value="ADMIN"/&gt;
 *               &lt;enumeration value="AGENT"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="profilePicture" type="{http://www.xml.com/megatravel/soap/model/image}XmlImage"/&gt;
 *         &lt;element name="isActive" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="category" type="{http://www.xml.com/megatravel/soap/model/category}XmlCategory"/&gt;
 *         &lt;element name="reservations" type="{http://www.xml.com/megatravel/soap/model/reservation}XmlReservation" maxOccurs="unbounded"/&gt;
 *         &lt;element name="messages" type="{http://www.xml.com/megatravel/soap/model/message}XmlMessage" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XmlUser", propOrder = {
    "firstNme",
    "lastName",
    "username",
    "password",
    "email",
    "role",
    "profilePicture",
    "isActive",
    "category",
    "reservations"
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class XmlUser
    extends XmlBase
{

    @XmlElement(required = true)
    protected String firstNme;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true)
    protected String username;
    @XmlElement(required = true)
    protected String password;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true, defaultValue = "USER")
    protected String role;
    @XmlElement(required = true, nillable = true)
    protected XmlImage profilePicture;
    protected boolean isActive;
    @XmlElement(required = true)
    protected String category;
    @XmlElement(required = true)
    protected List<XmlReservation> reservations;

    /**
     * Gets the value of the firstNme property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstNme() {
        return firstNme;
    }

    /**
     * Sets the value of the firstNme property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstNme(String value) {
        this.firstNme = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRole(String value) {
        this.role = value;
    }

    /**
     * Gets the value of the profilePicture property.
     * 
     * @return
     *     possible object is
     *     {@link XmlImage }
     *     
     */
    public XmlImage getProfilePicture() {
        return profilePicture;
    }

    /**
     * Sets the value of the profilePicture property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlImage }
     *     
     */
    public void setProfilePicture(XmlImage value) {
        this.profilePicture = value;
    }

    /**
     * Gets the value of the isActive property.
     * 
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * Sets the value of the isActive property.
     * 
     */
    public void setIsActive(boolean value) {
        this.isActive = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link XmlCategory }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlCategory }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the reservations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reservations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReservations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XmlReservation }
     * 
     * 
     */
    public List<XmlReservation> getReservations() {
        if (reservations == null) {
            reservations = new ArrayList<XmlReservation>();
        }
        return this.reservations;
    }


}
