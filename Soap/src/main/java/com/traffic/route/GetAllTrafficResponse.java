//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.12.08 at 11:29:38 PM CET 
//


package com.traffic.route;

import Route.Traffic.com.Traffic;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://Route.Traffic.com}traffics"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "traffics"
})
@XmlRootElement(name = "getAllTrafficResponse")
public class GetAllTrafficResponse {

    @XmlElement(required = true)
    protected Traffics traffics;

    /**
     * Gets the value of the traffics property.
     * 
     * @return
     *     possible object is
     *     {@link Traffics }
     *     
     */
    public Traffics getTraffics() {
        return traffics;
    }

    /**
     * Sets the value of the traffics property.
     * 
     * @param value
     *     allowed object is
     *     {@link Traffics }
     *     
     */
    public void setTraffics(Traffics value) {
        this.traffics = value;
    }

}
