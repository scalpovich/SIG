package GTAgw;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.packager.GenericPackager;
import org.jpos.tlv.TLVList;
import org.jpos.tlv.TLVMsg;
import org.jpos.tlv.*;

public class ISO8583 {
	void Unpack(byte[] isoraw) {
		GenericPackager packager = null;
		try {
			packager = new GenericPackager("packager/base24.xml");
		} catch (ISOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Setting packager
		ISOMsg isomsg = new ISOMsg();
		isomsg.setPackager(packager);
		
		
//		for (int i=0; i< isomsg.length;i++){
//			isomsg[i]=(byte)(int)isomsg.
        try {
			isomsg.unpack(isoraw);
		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String logstr=null;
        try {
			System.out.println("MTI="+isomsg.getMTI());
			logstr=	"MTI="+isomsg.getMTI()+"'\n'";
		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        MyLogger log = new MyLogger(); 
        
        for(int i=1;i<=isomsg.getMaxField();i++){
        if(isomsg.hasField(i))
        	//System.out.println(i+"="+isomsg.getString(i));
        	
        	logstr=logstr+i+"="+isomsg.getString(i)+"'\n'";
        }
        log.trxlog("ISO8583.java-1 : "+logstr) ;
        
        //EMV data on DE55
        if (isomsg.hasField(55)) {
            TLVList tlvData = new TLVList();
            try {
				tlvData.unpack(isomsg.getBytes(55));
			} catch (ISOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           /* for (TLVMsg tLVMsg : tlvData.getTags()) {
                System.out.println("EMVtag : " + Integer.toHexString(tLVMsg.getTag()));
                System.out.println("Value on String Type : " + ISOUtil.hexString(tLVMsg.getValue()));
            }
*/
        }
	}
		
	
}
