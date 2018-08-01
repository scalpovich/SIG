/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author wanda.priatna
 */
public class testdate {
    
    public static void main(String[] args) {
        // (1) get today's date
    Date today = Calendar.getInstance().getTime();

    // (2) create a date "formatter" (the date format we want)
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");

    // (3) create a new String using the date format we want
    String folderName = formatter.format(today);
    
    // (4) this prints "Folder Name = 2009-09-06-08.23.23"
    System.out.println("Folder Name = " + folderName);
    }
    
}
