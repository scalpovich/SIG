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

@XmlRootElement(name = "SenderData")
@XmlAccessorType (XmlAccessType.FIELD)
 public class SenderData{
       @XmlElement(name = "AccountID")
        private String AccountID;
       @XmlElement(name = "Name")
        private String Name;
       @XmlElement(name = "Address")
        private String Address;
       @XmlElement(name = "CountryCode")
        private String CountryCode;
       @XmlElement(name = "BirthDate")
        private String BirthDate;
       @XmlElement(name = "BirthPlace")
        private String BirthPlace;
       @XmlElement(name = "PhoneNumber")
        private String PhoneNumber;
       @XmlElement(name = "Email")
        private String Email;
       @XmlElement(name = "Occupation")
        private String Occupation;
       @XmlElement(name = "Citizenship")
        private String Citizenship;
       @XmlElement(name = "IDNumber")
        private String IDNumber;
       @XmlElement(name = "FundResource")
        private String FundResource;

        public String getAccountID() {
            return AccountID;
        }

        public void setAccountID(String AccountID) {
            this.AccountID = AccountID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getCountryCode() {
            return CountryCode;
        }

        public void setCountryCode(String CountryCode) {
            this.CountryCode = CountryCode;
        }

        public String getBirthDate() {
            return BirthDate;
        }

        public void setBirthDate(String BirthDate) {
            this.BirthDate = BirthDate;
        }

        public String getBirthPlace() {
            return BirthPlace;
        }

        public void setBirthPlace(String BirthPlace) {
            this.BirthPlace = BirthPlace;
        }

        public String getPhoneNumber() {
            return PhoneNumber;
        }

        public void setPhoneNumber(String PhoneNumber) {
            this.PhoneNumber = PhoneNumber;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getOccupation() {
            return Occupation;
        }

        public void setOccupation(String Occupation) {
            this.Occupation = Occupation;
        }

        public String getCitizenship() {
            return Citizenship;
        }

        public void setCitizenship(String Citizenship) {
            this.Citizenship = Citizenship;
        }

        public String getIDNumber() {
            return IDNumber;
        }

        public void setIDNumber(String IDNumber) {
            this.IDNumber = IDNumber;
        }

        public String getFundResource() {
            return FundResource;
        }

        public void setFundResource(String FundResource) {
            this.FundResource = FundResource;
        }    
    }