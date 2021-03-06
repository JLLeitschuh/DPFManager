/**
 * <h1>Event.java</h1> <p> This program is free software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version; or,
 * at your choice, under the terms of the Mozilla Public License, v. 2.0. SPDX GPL-3.0+ or MPL-2.0+.
 * </p> <p> This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License and the Mozilla Public License for more details. </p>
 * <p> You should have received a copy of the GNU General Public License and the Mozilla Public
 * License along with this program. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>
 * and at <a href="http://mozilla.org/MPL/2.0">http://mozilla.org/MPL/2.0</a> . </p> <p> NB: for the
 * © statement, include Easy Innova SL or other company/Person contributing the code. </p> <p> ©
 * 2015 Easy Innova, SL </p>
 *
 * @author Mar Llambi
 * @version 1.0
 * @since 23/7/2015
 */

package dpfmanager.conformancechecker.tiff.reporting.METS.premis;

import dpfmanager.conformancechecker.configuration.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


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
 *         &lt;element name="eventIdentifier">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="eventIdentifierType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="eventIdentifierValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;anyAttribute namespace='http://www.w3.org/1999/xlink'/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="eventType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventDateTime">
 *           &lt;simpleType>
 *             &lt;union memberTypes=" {http://www.w3.org/2001/XMLSchema}date {http://www.w3.org/2001/XMLSchema}dateTime">
 *             &lt;/union>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="eventDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventOutcomeInformation" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="eventOutcome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="eventOutcomeDetail" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;any processContents='lax'/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="linkingAgentIdentifier" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="linkingAgentIdentifierType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="linkingAgentIdentifierValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="linkingAgentRole" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="LinkAgentXmlID" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *                 &lt;anyAttribute namespace='http://www.w3.org/1999/xlink'/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="linkingObjectIdentifier" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="linkingObjectIdentifierType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="linkingObjectIdentifierValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="LinkObjectXmlID" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *                 &lt;anyAttribute namespace='http://www.w3.org/1999/xlink'/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="xmlID" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="version">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="1.1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "eventIdentifier",
    "eventType",
    "eventDateTime",
    "eventDetail",
    "eventOutcomeInformation",
    "linkingAgentIdentifier",
    "linkingObjectIdentifier"
})
@XmlRootElement(name = "event")
public class Event {

    @XmlElement(required = true)
    protected EventIdentifier eventIdentifier;
    @XmlElement(required = true)
    protected String eventType;
    @XmlElement(required = true)
    protected String eventDateTime;
    protected Configuration  eventDetail;
    protected List<EventOutcomeInformation> eventOutcomeInformation;
    protected List<LinkingAgentIdentifier> linkingAgentIdentifier;
    protected List<LinkingObjectIdentifier> linkingObjectIdentifier;
    @XmlAttribute(name = "xmlID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String xmlID;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the eventIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link EventIdentifier }
     *     
     */
    public EventIdentifier getEventIdentifier() {
        return eventIdentifier;
    }

    /**
     * Sets the value of the eventIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventIdentifier }
     *     
     */
    public void setEventIdentifier(EventIdentifier value) {
        this.eventIdentifier = value;
    }

    /**
     * Gets the value of the eventType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Sets the value of the eventType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventType(String value) {
        this.eventType = value;
    }

    /**
     * Gets the value of the eventDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventDateTime() {
        return eventDateTime;
    }

    /**
     * Sets the value of the eventDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventDateTime(String value) {
        this.eventDateTime = value;
    }

    /**
     * Gets the value of the eventDetail property.
     * 
     * @return
     *     possible object is
     *     {@link Document }
     *     
     */
    public Configuration getEventDetail() {
        return eventDetail;
    }

    /**
     * Sets the value of the eventDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventDetail(Configuration value) {
        this.eventDetail = value;
    }

    /**
     * Gets the value of the eventOutcomeInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventOutcomeInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventOutcomeInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EventOutcomeInformation }
     * 
     * 
     */
    public List<EventOutcomeInformation> getEventOutcomeInformation() {
        if (eventOutcomeInformation == null) {
            eventOutcomeInformation = new ArrayList<EventOutcomeInformation>();
        }
        return this.eventOutcomeInformation;
    }

    public void setEventOutcomeInformation(EventOutcomeInformation s) {
        if (eventOutcomeInformation == null) {
            eventOutcomeInformation = new ArrayList<EventOutcomeInformation>();
        }
        this.eventOutcomeInformation.add(s);
    }

    /**
     * Gets the value of the linkingAgentIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkingAgentIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkingAgentIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinkingAgentIdentifier }
     * 
     * 
     */
    public List<LinkingAgentIdentifier> getLinkingAgentIdentifier() {
        if (linkingAgentIdentifier == null) {
            linkingAgentIdentifier = new ArrayList<LinkingAgentIdentifier>();
        }
        return this.linkingAgentIdentifier;
    }

    public void setLinkingAgentIdentifier(LinkingAgentIdentifier l) {
        if (linkingAgentIdentifier == null) {
            linkingAgentIdentifier = new ArrayList<LinkingAgentIdentifier>();
        }
        this.linkingAgentIdentifier.add(l);
    }

    /**
     * Gets the value of the linkingObjectIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkingObjectIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkingObjectIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinkingObjectIdentifier }
     * 
     * 
     */
    public List<LinkingObjectIdentifier> getLinkingObjectIdentifier() {
        if (linkingObjectIdentifier == null) {
            linkingObjectIdentifier = new ArrayList<LinkingObjectIdentifier>();
        }
        return this.linkingObjectIdentifier;
    }

    /**
     * Gets the value of the xmlID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlID() {
        return xmlID;
    }

    /**
     * Sets the value of the xmlID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlID(String value) {
        this.xmlID = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }


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
     *         &lt;element name="eventIdentifierType" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="eventIdentifierValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;anyAttribute namespace='http://www.w3.org/1999/xlink'/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "eventIdentifierType",
        "eventIdentifierValue"
    })
    public static class EventIdentifier {

        @XmlElement(required = true)
        protected String eventIdentifierType;
        @XmlElement(required = true)
        protected String eventIdentifierValue;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the eventIdentifierType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEventIdentifierType() {
            return eventIdentifierType;
        }

        /**
         * Sets the value of the eventIdentifierType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEventIdentifierType(String value) {
            this.eventIdentifierType = value;
        }

        /**
         * Gets the value of the eventIdentifierValue property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEventIdentifierValue() {
            return eventIdentifierValue;
        }

        /**
         * Sets the value of the eventIdentifierValue property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEventIdentifierValue(String value) {
            this.eventIdentifierValue = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         * 
         * <p>
         * the map is keyed by the name of the attribute and 
         * the value is the string value of the attribute.
         * 
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         * 
         * 
         * @return
         *     always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }


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
     *         &lt;element name="eventOutcome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="eventOutcomeDetail" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;any processContents='lax'/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "eventOutcome",
        "eventOutcomeDetail"
    })
    public static class EventOutcomeInformation {

        protected String eventOutcome;
        protected EventOutcomeDetail eventOutcomeDetail;

        /**
         * Gets the value of the eventOutcome property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEventOutcome() {
            return eventOutcome;
        }

        /**
         * Sets the value of the eventOutcome property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEventOutcome(String value) {
            this.eventOutcome = value;
        }

        /**
         * Gets the value of the eventOutcomeDetail property.
         * 
         * @return
         *     possible object is
         *     {@link EventOutcomeDetail }
         *     
         */
        public EventOutcomeDetail getEventOutcomeDetail() {
            return eventOutcomeDetail;
        }

        /**
         * Sets the value of the eventOutcomeDetail property.
         * 
         * @param value
         *     allowed object is
         *     {@link EventOutcomeDetail }
         *     
         */
        public void setEventOutcomeDetail(EventOutcomeDetail value) {
            this.eventOutcomeDetail = value;
        }


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
         *         &lt;any processContents='lax'/>
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
            "any"
        })
        public static class EventOutcomeDetail {

            @XmlAnyElement(lax = true)
            protected Object any;

            /**
             * Gets the value of the any property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     {@link Element }
             *     
             */
            public Object getAny() {
                return any;
            }

            /**
             * Sets the value of the any property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     {@link Element }
             *     
             */
            public void setAny(Object value) {
                this.any = value;
            }

        }

    }


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
     *         &lt;element name="linkingAgentIdentifierType" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="linkingAgentIdentifierValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="linkingAgentRole" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="LinkAgentXmlID" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
     *       &lt;anyAttribute namespace='http://www.w3.org/1999/xlink'/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "linkingAgentIdentifierType",
        "linkingAgentIdentifierValue",
        "linkingAgentRole"
    })
    public static class LinkingAgentIdentifier {

        @XmlElement(required = true)
        protected String linkingAgentIdentifierType;
        @XmlElement(required = true)
        protected String linkingAgentIdentifierValue;
        protected List<String> linkingAgentRole;
        @XmlAttribute(name = "LinkAgentXmlID")
        @XmlIDREF
        @XmlSchemaType(name = "IDREF")
        protected Object linkAgentXmlID;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the linkingAgentIdentifierType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLinkingAgentIdentifierType() {
            return linkingAgentIdentifierType;
        }

        /**
         * Sets the value of the linkingAgentIdentifierType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLinkingAgentIdentifierType(String value) {
            this.linkingAgentIdentifierType = value;
        }

        /**
         * Gets the value of the linkingAgentIdentifierValue property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLinkingAgentIdentifierValue() {
            return linkingAgentIdentifierValue;
        }

        /**
         * Sets the value of the linkingAgentIdentifierValue property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLinkingAgentIdentifierValue(String value) {
            this.linkingAgentIdentifierValue = value;
        }

        /**
         * Gets the value of the linkingAgentRole property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the linkingAgentRole property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getLinkingAgentRole().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getLinkingAgentRole() {
            if (linkingAgentRole == null) {
                linkingAgentRole = new ArrayList<String>();
            }
            return this.linkingAgentRole;
        }

        /**
         * Gets the value of the linkAgentXmlID property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getLinkAgentXmlID() {
            return linkAgentXmlID;
        }

        /**
         * Sets the value of the linkAgentXmlID property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setLinkAgentXmlID(Object value) {
            this.linkAgentXmlID = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         * 
         * <p>
         * the map is keyed by the name of the attribute and 
         * the value is the string value of the attribute.
         * 
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         * 
         * 
         * @return
         *     always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }


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
     *         &lt;element name="linkingObjectIdentifierType" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="linkingObjectIdentifierValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="LinkObjectXmlID" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
     *       &lt;anyAttribute namespace='http://www.w3.org/1999/xlink'/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "linkingObjectIdentifierType",
        "linkingObjectIdentifierValue"
    })
    public static class LinkingObjectIdentifier {

        @XmlElement(required = true)
        protected String linkingObjectIdentifierType;
        @XmlElement(required = true)
        protected String linkingObjectIdentifierValue;
        @XmlAttribute(name = "LinkObjectXmlID")
        @XmlIDREF
        @XmlSchemaType(name = "IDREF")
        protected Object linkObjectXmlID;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the linkingObjectIdentifierType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLinkingObjectIdentifierType() {
            return linkingObjectIdentifierType;
        }

        /**
         * Sets the value of the linkingObjectIdentifierType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLinkingObjectIdentifierType(String value) {
            this.linkingObjectIdentifierType = value;
        }

        /**
         * Gets the value of the linkingObjectIdentifierValue property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLinkingObjectIdentifierValue() {
            return linkingObjectIdentifierValue;
        }

        /**
         * Sets the value of the linkingObjectIdentifierValue property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLinkingObjectIdentifierValue(String value) {
            this.linkingObjectIdentifierValue = value;
        }

        /**
         * Gets the value of the linkObjectXmlID property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getLinkObjectXmlID() {
            return linkObjectXmlID;
        }

        /**
         * Sets the value of the linkObjectXmlID property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setLinkObjectXmlID(Object value) {
            this.linkObjectXmlID = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         * 
         * <p>
         * the map is keyed by the name of the attribute and 
         * the value is the string value of the attribute.
         * 
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         * 
         * 
         * @return
         *     always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }

}
