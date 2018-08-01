package GTAgw;

import java.util.HashMap;
import java.util.Hashtable;
import org.jpos.iso.IFA_AMOUNT;
import org.jpos.iso.IFA_BINARY;
import org.jpos.iso.IFA_LLLCHAR;
import org.jpos.iso.IFA_BITMAP;
import org.jpos.iso.IFA_LLCHAR;
import org.jpos.iso.IFA_LLNUM;
import org.jpos.iso.IFA_NUMERIC;
import org.jpos.iso.IFEP_LLCHAR;
import org.jpos.iso.ISOBasePackager;
import org.jpos.iso.ISOFieldPackager;
import org.jpos.iso.IF_CHAR;

public class ISO1987APackagerJ extends ISOBasePackager{
       //public HashMap hp;
       public Hashtable<String, Integer> hx;
       
       protected ISOFieldPackager fld[] = {
		/*000*/ new IFA_NUMERIC ( 4, "MTI"),
		/*001*/ new IFA_BITMAP ( 16, "Bitmap"),
		/*002*/ new IFA_LLCHAR (19, "PAN"),
		/*003*/ new IFA_NUMERIC (6, "PROCESSING_CODE"),
		/*004*/ new IFA_NUMERIC (12, "TOTAL_AMOUNT"),
		/*005*/ new IFA_NUMERIC (12, "AMOUNT_SETTLEMENT"),
		/*006*/ new IFA_NUMERIC (12, "AMOUNT_CARDHOLDER"),
		/*007*/ new IFA_NUMERIC (10, "TRANSMISSION_TIME"),
		/*008*/ new IFA_NUMERIC (8, "AMOUNT_CARDHOLDER_BILLINGFEE"),
		/*009*/ new IFA_NUMERIC (8, "CONVERT_RATE_SETTLE"),

        /*010*/ new IFA_NUMERIC (8, "CONVERT_RATE_CH_BILLING"),
		/*011*/ new IFA_NUMERIC (6, "STAN"),
		/*012*/ new IFA_NUMERIC (6, "LOCAL_TIME"),
		/*013*/ new IFA_NUMERIC (4, "LOCAL_DATE"),
		/*014*/ new IFA_NUMERIC (4, "EXPIRE_DATE"),
		/*015*/ new IFA_NUMERIC (4, "SETTLEMENT_DATE"),
		/*016*/ new IFA_NUMERIC (4, "CONVERSION_DATE"),
		/*017*/ new IFA_NUMERIC (4, "CAPTURE_DATE"),
		/*018*/ new IFA_NUMERIC (4, "MERCHANT_TYPE"),
		/*019*/ new IFA_NUMERIC (3, "ACQ_INST_COUNTRY_CODE"),

        /*020*/new IFA_NUMERIC (3, "PAN_EXT_COUNTYR_CODE"),
		/*021*/new IFA_NUMERIC(3,"FORWARD_INS_COUNTRY_CODE"),
		/*022*/new IFA_NUMERIC(3,"POINT_OF_SERVICE"),
		/*023*/new IFA_NUMERIC(3,"CARD_SEQ_NUMBER"),
		/*024*/new IFA_NUMERIC (3, "NII"),
		/*025*/new IFA_NUMERIC(2,"POINT_OF_SERVICE"),
		/*026*/new IFA_NUMERIC(2,"POS_PIN"),
		/*027*/new IFA_NUMERIC(1,"AUTH_IDENT_RESP_LEN"),
		/*028*/new IFA_AMOUNT(9, "AMOUNT_TRANSACTION_FEE"),
		/*029*/new IFA_AMOUNT(9, "AMOUNT_SETTLE_FEE"),

        /*030*/new IFA_AMOUNT(9,"AMOUNT_TRANSACTION_PROC_FEE"),
		/*031*/new IFA_AMOUNT(9,"AMOUNT_SETTLE_PROC_FEE"),
		/*032*/new IFA_LLNUM(99, "ID_APPL"),
		/*033*/new IFA_LLNUM(11, "FORWARD_INS_IDENT_CODE"),
		/*034*/new IFA_LLCHAR(28,"PAN_EXT"),
		/*035*/new IFA_LLNUM(37,"TRACK2"),
		/*036*/new IFA_LLLCHAR(104,"TRACK3"),
		/*037*/new IFA_NUMERIC (12, "RETRIEVAL_REF_NUM"),
		/*038*/new IF_CHAR(6, "AIR"),
		/*039*/new IF_CHAR(2, "RESPONSE_CODE"),

        /*040*/new IFA_NUMERIC(3, "ACTION_CODE"),
		/*041*/new IF_CHAR(8, "TERMINAL_ID"),
		/*042*/new IF_CHAR(15, "ACCEPTOR_ID"),
		/*043*/new IF_CHAR(40,"ACCEPTOR_NAME"),
		/*044*/new IFA_LLCHAR(25,"ADD_RESPONSE_DATA"),
		/*045*/new IFA_LLCHAR(76,"TRACK1"),
		/*046*/new IFA_LLLCHAR(999,"DATA46"),
		/*047*/new IFA_LLLCHAR(999,"DATA47"),
		/*048*/new IFA_LLLCHAR(999, "DATA48"),
		/*049*/new IF_CHAR(3, "CURRENCY_CODE"),

        /*50*/new IF_CHAR(3,"CURR_CODE_SETTLE"),
		/*51*/new IF_CHAR(3,"CURR_CODE_CARDHOLDER_BILLING"),
		/*52*/new IFA_BINARY(8,"PIN"),
		/*53*/new IFA_NUMERIC(16,"SEC_RELATED_CONTROL_INFO"),
		/*54*/new IFA_LLLCHAR(120,"ADDITIONAL_AMOUNT"),
		/*55*/new IFA_LLLCHAR(999,"DATA55"),
		/*56*/new IFA_LLLCHAR(999,"DATA56"),
		/*57*/new IFA_LLLCHAR(999,"DATA57"),
		/*58*/new IFA_LLLCHAR(999,"DATA58"),
		/*59*/new IFA_LLLCHAR(999,"DATA59"),

        /*060*/new IFA_LLLCHAR(999, "DATA60"),
        /*061*/new IFA_LLLCHAR(999, "DATA61"),
        /*062*/new IFA_LLLCHAR(999, "DATA62"),
        /*063*/new IFA_LLLCHAR(999, "DATA63"),
		/*064*/new IFA_BINARY(8,""),
		/*065*/new IFA_BINARY(1,"EXT_BITMAP"),
		/*066*/new IFA_NUMERIC(1,"SETLLE_CODE"),
		/*067*/new IFA_NUMERIC(2,"EXT_PAY_CODE"),
		/*068*/new IFA_NUMERIC(3,"RECV_INS_COUNTRY_CODE"),
		/*069*/new IFA_NUMERIC(3,"SETTLE_INS_COUNTRY_CODE"),

        /*070*/new IFA_NUMERIC (3, "NETWORK_CODE"),
		/*071*/new IFA_NUMERIC(4,"MESG_NUM"),
		/*072*/new IFA_NUMERIC(4,"MESG_NUM_LAST"),
		/*073*/new IFA_NUMERIC(6,"DATE_ACTION"),
		/*074*/new IFA_NUMERIC(10,"CREDIT_NUM"),
		/*075*/new IFA_NUMERIC(10,"CREDIT_REV_NUM"),
		/*076*/new IFA_NUMERIC(10,"DEBIT_NUM"),
		/*077*/new IFA_NUMERIC(10,"DEBIT_REV_NUM"),
		/*078*/new IFA_NUMERIC(10,"TRANSFER_NUM"),
		/*079*/new IFA_NUMERIC(10,"TRANSFER_REV_NUM"),

        /*80*/new IFA_NUMERIC(10, "INQ_NUM"),
		/*81*/new IFA_NUMERIC(10, "AUTH_NUM"),
		/*82*/new IFA_NUMERIC(12, "PROC_FEE_AMOUNT"),
		/*83*/new IFA_NUMERIC(12, "TRANSACTION_FEE_AMOUNT"),
		/*84*/new IFA_NUMERIC(12, ""),
		/*85*/new IFA_NUMERIC(12, ""),
		/*86*/new IFA_NUMERIC(16, ""),
		/*87*/new IFA_NUMERIC(16, ""),
		/*88*/new IFA_NUMERIC(16, ""),
		/*89*/new IFA_NUMERIC(16, ""),

        /*090*/new IFA_NUMERIC (42, "ORIGINAL_DATA_ELEMENT"),
        /*91*/new IF_CHAR(1,"FILE_UPDATE_CODE"),
		/*92*/new IF_CHAR(2,"FILE_SEC_CODE"),
		/*93*/new IF_CHAR(6,"RESP_INDICATOR"),
		/*94*/new IF_CHAR(7,"SERVC_INDICATOR"),
		/*95*/new IF_CHAR(42,"REPLCMENT_INDICATOR"),
		/*96*/new IFA_BINARY(16,"MESG_SEC_CODE"),
		/*97*/new IFA_AMOUNT(17,"AMOUNT_NET_SETTLE"),
		/*98*/new IF_CHAR(25,"PAYEE"),
		/*99*/new IFA_LLNUM(11,"SETTLE_INT_IDENT_CODE"),
                /*100*/new IFA_LLNUM(11,"RECVING_INS_IDENT_CODE"),
		/*101*/new IF_CHAR(17,"FILE_NAME"),
                /*102*/new IFA_LLNUM(28,"Account_identification_1"),
                /*103*/new IFA_LLNUM(28,"Account_identification_2"),
                /*104*/new IFA_LLLCHAR(100,"Transaction_description"),
                /*105*/new IFA_LLLCHAR(999,"Reserved"),
                /*106*/new IFA_LLLCHAR(999,"Reserved"),
                /*107*/new IFA_LLLCHAR(999,"Reserved"),
                /*108*/new IFA_LLLCHAR(999,"Reserved"),
                /*109*/new IFA_LLLCHAR(999,"Reserved"),
                /*110*/new IFA_LLLCHAR(999,"Reserved"),
                /*111*/new IFA_LLLCHAR(999,"Reserved"),
                /*112*/new IFA_LLLCHAR(999,"Reserved"),
                /*113*/new IFA_LLLCHAR(999,"Reserved"),
                /*114*/new IFA_LLLCHAR(999,"Reserved"),
                /*115*/new IFA_LLLCHAR(999,"Reserved"),
                /*116*/new IFA_LLLCHAR(999,"Reserved"),
                /*117*/new IFA_LLLCHAR(999,"Reserved"),
                /*118*/new IFA_LLLCHAR(999,"Reserved"),
                /*119*/new IFA_LLLCHAR(999,"Reserved"),
                /*120*/new IFA_LLLCHAR(999,"Reserved"),
                /*121*/new IFA_LLLCHAR(999,"Reserved"),
                /*122*/new IFA_LLLCHAR(999,"Reserved"),
                /*123*/new IFA_LLLCHAR(999,"Reserved"),
                /*124*/new IFA_LLLCHAR(999,"Reserved"),
                /*125*/new IFA_LLLCHAR(999,"Reserved"),
                /*126*/new IFA_LLLCHAR(999,"Reserved"),
                /*127*/new IFA_LLLCHAR(999,"Reserved")
	};
	public void setFieldType(int field,String tipe,String namaField,int jumField){

            hx.put(namaField, field);
            //hp.put(new String(namaField),new Integer(field));

        }
        public ISO1987APackagerJ() {
            super();
            //hp = new HashMap();
            hx = new Hashtable<String, Integer>();
            //setFieldPackager(fld);

	}
        public void setPackager(){
            setFieldPackager(fld);
            int a;
            for(a=0;a<fld.length;a++){
                if (fld[a].getDescription().equalsIgnoreCase(""));
                else {
                    hx.put(fld[a].getDescription(), a);
                    //hp.put(new String(fld[a].getDescription()),new Integer(a));
                }
            }
        }
}
