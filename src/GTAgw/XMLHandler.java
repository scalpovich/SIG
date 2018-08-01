/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import java.io.File;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.bind.JAXBElement;
import java.io.StringReader;
import GTAgw.POJO.Employee;
import GTAgw.POJO.Reqdata;
import GTAgw.POJO.ReqInqPayment;
import GTAgw.POJO.Respdata;

/**
 *
 * @author fjuniadi
 */

public class XMLHandler {


    private static final String FILE_NAME = "jaxb-emp.xml";
/*
    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.setId(1);
        emp.setAge(25);
        emp.setName("Pankaj");
        emp.setGender("Male");
        emp.setRole("Developer");
        emp.setPassword("sensitive");

        //jaxbObjectToXML(emp);

        Reqdata empFromFile = jaxbXMLToObject();
        System.out.println(empFromFile.toString());
    }
*/

    public static Reqdata jaxbXMLToObject(String XMLData) {
        try {
            JAXBContext context = JAXBContext.newInstance(Reqdata.class);
            Unmarshaller un = context.createUnmarshaller();
            StreamSource streamSource = new StreamSource(new StringReader(XMLData));
            JAXBElement<Reqdata> je = un.unmarshal(streamSource, Reqdata.class);
            //Employee emp = (Employee) un.unmarshal(streamSource);
            
            Reqdata mydata = (Reqdata)je.getValue();
            System.out.println("Booking ID:- " +  mydata.getbookingid());
            return mydata;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ReqInqPayment ReqInqPaymentjaxbXMLToObject(String XMLData) {
        try {
            JAXBContext context = JAXBContext.newInstance(ReqInqPayment.class);
            Unmarshaller un = context.createUnmarshaller();
            StreamSource streamSource = new StreamSource(new StringReader(XMLData));
            JAXBElement<ReqInqPayment> je = un.unmarshal(streamSource, ReqInqPayment.class);
            //Employee emp = (Employee) un.unmarshal(streamSource);
            
            ReqInqPayment mydata = (ReqInqPayment)je.getValue();
            System.out.println("VA ID:- " +  mydata.getvaid());
            return mydata;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public static String jaxbObjectToXML(Respdata ObjResp) {
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Respdata.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Write to System.out for debugging
            m.marshal(ObjResp, System.out);

            // Write to File
            m.marshal(ObjResp, new File(FILE_NAME));
            
            StringWriter sw = new StringWriter();
            m.marshal(ObjResp, sw);
            xmlString = sw.toString();
            
            System.out.println("xmlString " +  xmlString);
            
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlString;
    }


    
}
