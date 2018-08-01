/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.createaccount;


import GTAgw.Employees;
import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import pojo.createaccount.CreateAccountResponsePojo;

/**
 *
 * @author dev09
 */
public class TestCreateAccount {
    //Initialize the employees list
 
 
static CreateAccountResponsePojo createAccountResp = new CreateAccountResponsePojo();
static
{
    createAccountResp.setTransactionId(new ArrayList<TransactionId>());
    TransactionId trxId = new TransactionId();
    trxId.setStan("001");
    trxId.setTransDateTime("20181006");
    trxId.setInstId("003");
    createAccountResp.getTransactionId().add(trxId);
    
    createAccountResp.setSenderData(new ArrayList<SenderData>());
    SenderData senderData = new SenderData();
    senderData.setAccountID("001001");
    senderData.setName("wanda");
    senderData.setAddress("some address");
    senderData.setCountryCode("306");
    senderData.setBirthDate("someBirth");
    senderData.setBirthPlace("jakarta");
    senderData.setPhoneNumber("098395");
    senderData.setEmail("someEmail");
    senderData.setOccupation("some Occupaton ");
    senderData.setCitizenship("indonesia");
    senderData.setIDNumber("123123");
    senderData.setFundResource("gaji");
    createAccountResp.getSenderData().add(senderData);
 
}
 
private static void marshalingExample() throws JAXBException
{
    JAXBContext jaxbContext = JAXBContext.newInstance(CreateAccountResponsePojo.class);
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
     
    //Marshal the employees list in console
    jaxbMarshaller.marshal(createAccountResp, System.out);
     
    //Marshal the employees list in file
    jaxbMarshaller.marshal(createAccountResp, new File("c:/temp/employees.xml"));
}

public static void main(String[] args) throws JAXBException{
    System.out.println("Marshalling : ");
    marshalingExample();
}
    
}


