/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.createaccount;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author dev09
 */
public class UnmarshallExample {
    
    public static void main(String[] args) throws JAXBException{
       JAXBContext jaxbContext = JAXBContext.newInstance(CreateAccountResponsePojo.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller(); 
        CreateAccountResponsePojo pojo = (CreateAccountResponsePojo) jaxbUnmarshaller.unmarshal(new File("c:/temp/employees.xml") );
 
        for(TransactionId trx : pojo.getTransactionId()){
            System.out.println("Stan : "  + trx.getStan());
            System.out.println("TransDateTime" + trx.getTransDateTime());
        }
        for(SenderData send : pojo.getSenderData()){
            System.out.println("FundResource" + send.getFundResource());
        }
//        
//        List<BeneficiaryData> list = pojo.getBenefData();
//        for(AccO1 acc : list){
//            
//        }
        
        System.out.println("add for testing github ");
    }
    
}
