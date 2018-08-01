/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author wanda.priatna
 */
public class TrimVaid {
    
    public static String trim(String vaid){
      String start = StringUtils.substringAfter(vaid, "5005");
      return start;
    }
    
//    public static void main(String[] args) {
//        String s = trim("50050895337338653");
//        System.out.println("Tring String : "+s);
//    }
//    
  
    
}
