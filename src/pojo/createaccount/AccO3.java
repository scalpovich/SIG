/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.createaccount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dev09
 */

@XmlRootElement(name = "AccO3")
@XmlAccessorType (XmlAccessType.FIELD)
class AccO3 {
    
    @XmlElement(name = "AccountID")
    private String accountID;
    @XmlElement(name = "InstID")
    private String instId;
    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "Relationship")
    private String relationship;
    @XmlElement(name = "RegencyCode")
    private String regencyCode;
    @XmlElement(name = "Address")
    private String address;
    @XmlElement(name = "ProvCode")
    private String provCode;
    @XmlElement(name = "IDNumber")
    private String idNumber;
            
    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getRegencyCode() {
        return regencyCode;
    }

    public void setRegencyCode(String regencyCode) {
        this.regencyCode = regencyCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvCode() {
        return provCode;
    }

    public void setProvCode(String provCode) {
        this.provCode = provCode;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    
    
    
}
