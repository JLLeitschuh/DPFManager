/**
 * <h1>BasicDigitalObjectInformationType.java</h1> <p> This program is free software: you can redistribute it
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

package dpfmanager.conformancechecker.tiff.reporting.METS.niso;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BasicDigitalObjectInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BasicDigitalObjectInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ObjectIdentifier" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="objectIdentifierType" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
 *                   &lt;element name="objectIdentifierValue" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="fileSize" type="{http://www.loc.gov/mix/v20}nonNegativeIntegerType" minOccurs="0"/>
 *         &lt;element name="FormatDesignation" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="formatName" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
 *                   &lt;element name="formatVersion" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="FormatRegistry" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="formatRegistryName" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
 *                   &lt;element name="formatRegistryKey" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="byteOrder" type="{http://www.loc.gov/mix/v20}typeOfByteOrderType" minOccurs="0"/>
 *         &lt;element name="Compression" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="compressionScheme" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
 *                   &lt;element name="compressionSchemeLocalList" type="{http://www.loc.gov/mix/v20}URIType" minOccurs="0"/>
 *                   &lt;element name="compressionSchemeLocalValue" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
 *                   &lt;element name="compressionRatio" type="{http://www.loc.gov/mix/v20}rationalType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Fixity" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="messageDigestAlgorithm" type="{http://www.loc.gov/mix/v20}typeOfMessageDigestAlgorithmType" minOccurs="0"/>
 *                   &lt;element name="messageDigest" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
 *                   &lt;element name="messageDigestOriginator" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
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
@XmlType(name = "BasicDigitalObjectInformationType", propOrder = {
    "objectIdentifier",
    "fileSize",
    "formatDesignation",
    "formatRegistry",
    "byteOrder",
    "compression",
    "fixity"
})
public class BasicDigitalObjectInformationType {

    @XmlElement(name = "ObjectIdentifier")
    protected List<ObjectIdentifier> objectIdentifier;
    protected NonNegativeIntegerType fileSize;
    @XmlElement(name = "FormatDesignation")
    protected FormatDesignation formatDesignation;
    @XmlElement(name = "FormatRegistry")
    protected FormatRegistry formatRegistry;
    protected TypeOfByteOrderType byteOrder;
    @XmlElement(name = "Compression")
    protected List<Compression> compression;
    @XmlElement(name = "Fixity")
    protected List<Fixity> fixity;

    /**
     * Gets the value of the objectIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectIdentifier }
     * 
     * 
     */
    public List<ObjectIdentifier> getObjectIdentifier() {
        if (objectIdentifier == null) {
            objectIdentifier = new ArrayList<ObjectIdentifier>();
        }
        return this.objectIdentifier;
    }

    /**
     * Gets the value of the fileSize property.
     * 
     * @return
     *     possible object is
     *     {@link NonNegativeIntegerType }
     *     
     */
    public NonNegativeIntegerType getFileSize() {
        return fileSize;
    }

    /**
     * Sets the value of the fileSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonNegativeIntegerType }
     *     
     */
    public void setFileSize(NonNegativeIntegerType value) {
        this.fileSize = value;
    }

    /**
     * Gets the value of the formatDesignation property.
     * 
     * @return
     *     possible object is
     *     {@link FormatDesignation }
     *     
     */
    public FormatDesignation getFormatDesignation() {
        return formatDesignation;
    }

    /**
     * Sets the value of the formatDesignation property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormatDesignation }
     *     
     */
    public void setFormatDesignation(FormatDesignation value) {
        this.formatDesignation = value;
    }

    /**
     * Gets the value of the formatRegistry property.
     * 
     * @return
     *     possible object is
     *     {@link FormatRegistry }
     *     
     */
    public FormatRegistry getFormatRegistry() {
        return formatRegistry;
    }

    /**
     * Sets the value of the formatRegistry property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormatRegistry }
     *     
     */
    public void setFormatRegistry(FormatRegistry value) {
        this.formatRegistry = value;
    }

    /**
     * Gets the value of the byteOrder property.
     * 
     * @return
     *     possible object is
     *     {@link TypeOfByteOrderType }
     *     
     */
    public TypeOfByteOrderType getByteOrder() {
        return byteOrder;
    }

    /**
     * Sets the value of the byteOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeOfByteOrderType }
     *     
     */
    public void setByteOrder(TypeOfByteOrderType value) {
        this.byteOrder = value;
    }

    /**
     * Gets the value of the compression property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the compression property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompression().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Compression }
     * 
     * 
     */
    public List<Compression> getCompression() {
        if (compression == null) {
            compression = new ArrayList<Compression>();
        }
        return this.compression;
    }

    public void setCompression(Compression c) {
        if (compression == null) {
            compression = new ArrayList<Compression>();
        }
        this.compression.add(c);
    }

    /**
     * Gets the value of the fixity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fixity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFixity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Fixity }
     * 
     * 
     */
    public List<Fixity> getFixity() {
        if (fixity == null) {
            fixity = new ArrayList<Fixity>();
        }
        return this.fixity;
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
     *         &lt;element name="compressionScheme" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
     *         &lt;element name="compressionSchemeLocalList" type="{http://www.loc.gov/mix/v20}URIType" minOccurs="0"/>
     *         &lt;element name="compressionSchemeLocalValue" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
     *         &lt;element name="compressionRatio" type="{http://www.loc.gov/mix/v20}rationalType" minOccurs="0"/>
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
        "compressionScheme",
        "compressionSchemeLocalList",
        "compressionSchemeLocalValue",
        "compressionRatio"
    })
    public static class Compression {

        protected StringType compressionScheme;
        protected URIType compressionSchemeLocalList;
        protected StringType compressionSchemeLocalValue;
        protected RationalType compressionRatio;

        /**
         * Gets the value of the compressionScheme property.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getCompressionScheme() {
            return compressionScheme;
        }

        /**
         * Sets the value of the compressionScheme property.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setCompressionScheme(StringType value) {
            this.compressionScheme = value;
        }

        /**
         * Gets the value of the compressionSchemeLocalList property.
         * 
         * @return
         *     possible object is
         *     {@link URIType }
         *     
         */
        public URIType getCompressionSchemeLocalList() {
            return compressionSchemeLocalList;
        }

        /**
         * Sets the value of the compressionSchemeLocalList property.
         * 
         * @param value
         *     allowed object is
         *     {@link URIType }
         *     
         */
        public void setCompressionSchemeLocalList(URIType value) {
            this.compressionSchemeLocalList = value;
        }

        /**
         * Gets the value of the compressionSchemeLocalValue property.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getCompressionSchemeLocalValue() {
            return compressionSchemeLocalValue;
        }

        /**
         * Sets the value of the compressionSchemeLocalValue property.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setCompressionSchemeLocalValue(StringType value) {
            this.compressionSchemeLocalValue = value;
        }

        /**
         * Gets the value of the compressionRatio property.
         * 
         * @return
         *     possible object is
         *     {@link RationalType }
         *     
         */
        public RationalType getCompressionRatio() {
            return compressionRatio;
        }

        /**
         * Sets the value of the compressionRatio property.
         * 
         * @param value
         *     allowed object is
         *     {@link RationalType }
         *     
         */
        public void setCompressionRatio(RationalType value) {
            this.compressionRatio = value;
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
     *         &lt;element name="messageDigestAlgorithm" type="{http://www.loc.gov/mix/v20}typeOfMessageDigestAlgorithmType" minOccurs="0"/>
     *         &lt;element name="messageDigest" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
     *         &lt;element name="messageDigestOriginator" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
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
        "messageDigestAlgorithm",
        "messageDigest",
        "messageDigestOriginator"
    })
    public static class Fixity {

        protected TypeOfMessageDigestAlgorithmType messageDigestAlgorithm;
        protected StringType messageDigest;
        protected StringType messageDigestOriginator;

        /**
         * Gets the value of the messageDigestAlgorithm property.
         * 
         * @return
         *     possible object is
         *     {@link TypeOfMessageDigestAlgorithmType }
         *     
         */
        public TypeOfMessageDigestAlgorithmType getMessageDigestAlgorithm() {
            return messageDigestAlgorithm;
        }

        /**
         * Sets the value of the messageDigestAlgorithm property.
         * 
         * @param value
         *     allowed object is
         *     {@link TypeOfMessageDigestAlgorithmType }
         *     
         */
        public void setMessageDigestAlgorithm(TypeOfMessageDigestAlgorithmType value) {
            this.messageDigestAlgorithm = value;
        }

        /**
         * Gets the value of the messageDigest property.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getMessageDigest() {
            return messageDigest;
        }

        /**
         * Sets the value of the messageDigest property.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setMessageDigest(StringType value) {
            this.messageDigest = value;
        }

        /**
         * Gets the value of the messageDigestOriginator property.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getMessageDigestOriginator() {
            return messageDigestOriginator;
        }

        /**
         * Sets the value of the messageDigestOriginator property.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setMessageDigestOriginator(StringType value) {
            this.messageDigestOriginator = value;
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
     *         &lt;element name="formatName" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
     *         &lt;element name="formatVersion" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
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
        "formatName",
        "formatVersion"
    })
    public static class FormatDesignation {

        protected StringType formatName;
        protected StringType formatVersion;

        /**
         * Gets the value of the formatName property.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getFormatName() {
            return formatName;
        }

        /**
         * Sets the value of the formatName property.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setFormatName(StringType value) {
            this.formatName = value;
        }

        /**
         * Gets the value of the formatVersion property.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getFormatVersion() {
            return formatVersion;
        }

        /**
         * Sets the value of the formatVersion property.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setFormatVersion(StringType value) {
            this.formatVersion = value;
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
     *         &lt;element name="formatRegistryName" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
     *         &lt;element name="formatRegistryKey" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
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
        "formatRegistryName",
        "formatRegistryKey"
    })
    public static class FormatRegistry {

        protected StringType formatRegistryName;
        protected StringType formatRegistryKey;

        /**
         * Gets the value of the formatRegistryName property.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getFormatRegistryName() {
            return formatRegistryName;
        }

        /**
         * Sets the value of the formatRegistryName property.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setFormatRegistryName(StringType value) {
            this.formatRegistryName = value;
        }

        /**
         * Gets the value of the formatRegistryKey property.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getFormatRegistryKey() {
            return formatRegistryKey;
        }

        /**
         * Sets the value of the formatRegistryKey property.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setFormatRegistryKey(StringType value) {
            this.formatRegistryKey = value;
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
     *         &lt;element name="objectIdentifierType" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
     *         &lt;element name="objectIdentifierValue" type="{http://www.loc.gov/mix/v20}stringType" minOccurs="0"/>
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
        "objectIdentifierType",
        "objectIdentifierValue"
    })
    public static class ObjectIdentifier {

        protected StringType objectIdentifierType;
        protected StringType objectIdentifierValue;

        /**
         * Gets the value of the objectIdentifierType property.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getObjectIdentifierType() {
            return objectIdentifierType;
        }

        /**
         * Sets the value of the objectIdentifierType property.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setObjectIdentifierType(StringType value) {
            this.objectIdentifierType = value;
        }

        /**
         * Gets the value of the objectIdentifierValue property.
         * 
         * @return
         *     possible object is
         *     {@link StringType }
         *     
         */
        public StringType getObjectIdentifierValue() {
            return objectIdentifierValue;
        }

        /**
         * Sets the value of the objectIdentifierValue property.
         * 
         * @param value
         *     allowed object is
         *     {@link StringType }
         *     
         */
        public void setObjectIdentifierValue(StringType value) {
            this.objectIdentifierValue = value;
        }

    }

}
