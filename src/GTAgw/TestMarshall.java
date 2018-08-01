/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author dev09
 */
public class TestMarshall {
    //Initialize the employees list
 
 
static Employees employees = new Employees();
static
{
    employees.setEmployees(new ArrayList<Employee>());
    //Create two employees
    Employee emp1 = new Employee();
    emp1.setId(1);
    emp1.setFirstName("Lokesh");
    emp1.setLastName("Gupta");
    emp1.setIncome(100.0);
     
    Employee emp2 = new Employee();
    emp2.setId(2);
    emp2.setFirstName("John");
    emp2.setLastName("Mclane");
    emp2.setIncome(200.0);
     
    //Add the employees in list
    employees.getEmployees().add(emp1);
    employees.getEmployees().add(emp2);
}
 
private static void marshalingExample() throws JAXBException
{
    JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
     
    //Marshal the employees list in console
    jaxbMarshaller.marshal(employees, System.out);
     
    //Marshal the employees list in file
    jaxbMarshaller.marshal(employees, new File("c:/temp/employees.xml"));
}

public static void main(String[] args) throws JAXBException{
    System.out.println("Marshalling : ");
    marshalingExample();
}
    
}
