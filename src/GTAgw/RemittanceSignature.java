/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rakhmat.adhi
 */
public class RemittanceSignature {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*
         * sig:TransactionID.STAN->000001
sig:TransactionID.TransDateTime->20161207020323
sig:TransactionID.InstID->000001
sig:TransactionInfo.RefNumber->148107620359
sig:TransactionInfo.TerminalID->12345678
sig:TransactionInfo.LocalDateTime->20161207090323
sig:SenderData.AccountID->123456789012345670
sig:SenderData.Amount->000000400000
sig:BeneficiaryData.InstID->130
sig:BeneficiaryData.AccountID->504948010001000373
sig:BeneficiaryData.Amount->500000
sig:BeneficiaryData.CustRefNumber->200286
sig:TransactionID.CountryCode->ID
         * 
to sign:000001201612070203230000011481076203591234567820161207090323123456789012345670000000400000130504948010001000373500000200286ID
        
        0456d1aff6da02ac954e3fd57b2c4f5b0d751e22e0dcf5ae911ed2c02095f6a9afecb681654b77e8b79524434f637434df852218e300a53001a7aa13563698b346247c9c4efc30bf9b23ab66264a8894aa5d024dc49d2595b5f654148b382f2f986b3a18ca55563a46a9e9ef47a6a94e4249a450d8f68f5558f2e5a6109d25b2
         * 
         */
        String concatMessage = "000001201612070203230000011481076203591234567820161207090323123456789012345670000000400000130504948010001000373500000200286ID";
       // Ads ads = new Ads("D:\\remittance\\RemittanceSignature\\artajasa.p12", "123456");
        Ads ads = new Ads("D:\\remittance\\tokoemas\\artajasa.p12", "password");
      
        
        try {
            
             ads.loadKeys("artajasa");
            System.out.println("Encrypted Result :"+  ads.getDigitalSignatureAsString(concatMessage));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
