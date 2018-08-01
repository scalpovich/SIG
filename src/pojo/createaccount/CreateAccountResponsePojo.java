/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.createaccount;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "MethodResponse")
@XmlAccessorType (XmlAccessType.FIELD)
public class CreateAccountResponsePojo {
    
    @XmlElement(name = "TransactionID")
    private List<TransactionId> transactionId = null;
    
    @XmlElement(name = "SenderData")
    private List<SenderData> senderData = null;
    
    @XmlElement(name = "BeneficiaryData")
    private List<BeneficiaryData> benefData; 
    
    @XmlElement(name = "Response")
    private List<ResponseData> response;

    @XmlElement(name = "Signature")
    private List<SignatureData> signature; 

    public List<BeneficiaryData> getBenefData() {
        return benefData;
    }
    public void setBenefData(List<BeneficiaryData> benefData) {
        this.benefData = benefData;
    }
 
    public List<TransactionId> getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(List<TransactionId> transactionId) {
        this.transactionId = transactionId;
    }

    public List<SenderData> getSenderData() {
        return senderData;
    }

    public void setSenderData(List<SenderData> senderData) {
        this.senderData = senderData;
    }
    
    
   
    
}
