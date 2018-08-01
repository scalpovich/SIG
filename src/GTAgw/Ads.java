/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

/**
 *
 * @author amri, dani
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class Ads {

    private PrivateKey pk;
    private PublicKey pb;
    private PublicKey opb;
    private String ownPKCS12FilePath = "";
    private String ownPKCS12FilePassword = "";
    private String othersX509FilePath = "";

    /**
     * Contructor dan inisiasi file path
     * @param ownPKCS12FilePath path absolute file PKCS#12 milik sendiri
     * @param othersX509FilePath path absolute file sertifikat dijital x.509 milik kolega
     * @param password password untuk membuka file PKCS#12
     */
    public Ads(String ownPKCS12FilePath,
            String othersX509FilePath, String password) {
        this.ownPKCS12FilePath = ownPKCS12FilePath;
        this.ownPKCS12FilePassword = password;
        this.othersX509FilePath = othersX509FilePath;
    }

    public Ads(String ownPKCS12FilePath,
            String password) {
        this.ownPKCS12FilePath = ownPKCS12FilePath;
        this.ownPKCS12FilePassword = password;
    }

    public void loadKeys() throws InvalidKeySpecException,
            NoSuchAlgorithmException, KeyStoreException, IOException,
            UnrecoverableKeyException, CertificateException {
        //loadKeys("artajasa");
        Path path = Paths.get(ownPKCS12FilePath);
        byte[] privKeyByteArray = Files.readAllBytes(path);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privKeyByteArray);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        pk = keyFactory.generatePrivate(keySpec);

    }

    /**
     * Method untuk me-load isi key dalam file keystore (PKCS#12) dan
     * sertifikat dijital (X.509).
     * @throws java.security.spec.InvalidKeySpecException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyStoreException
     * @throws java.io.IOException
     * @throws java.security.UnrecoverableKeyException
     * @throws java.security.cert.CertificateException
     */
    public void loadKeys(String inst) throws InvalidKeySpecException,
            NoSuchAlgorithmException, KeyStoreException, IOException,
            UnrecoverableKeyException, CertificateException {

        // load our own key
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(this.ownPKCS12FilePath),
                this.ownPKCS12FilePassword.toCharArray());

        Key key = ks.getKey(inst, this.ownPKCS12FilePassword.toCharArray());
        if (key == null) {
            throw new RuntimeException("Key not found in Keystore");
        }
        RSAPrivateCrtKey priv = (RSAPrivateCrtKey) key;

        BigInteger n = priv.getModulus();
        BigInteger d = priv.getPrivateExponent();
        BigInteger e = priv.getPublicExponent();

        RSAPrivateKeySpec privateSpec = new RSAPrivateKeySpec(n, d);
        RSAPublicKeySpec publicSpec = new RSAPublicKeySpec(n, e);
        KeyFactory factory = KeyFactory.getInstance("RSA");

        pk = factory.generatePrivate(privateSpec);
        pb = factory.generatePublic(publicSpec);

        // Mengecek nilai N, E dan D, hanya untuk debugging
        //note, marked by danisupr4
        //System.out.println("N: " + n);
        //System.out.println("E: " + e);
        //System.out.println("D: " + d);

        // load other Public Keys
        //CertificateFactory cf = CertificateFactory.getInstance("X.509");
        //Certificate cert = cf.generateCertificate(new FileInputStream(this.othersX509FilePath));
        //opb = cert.getPublicKey();
        // note, marked by danisupr4
        //System.out.println("OPB: "+opb.getAlgorithm()+" "+opb.getFormat());

    }

    /**
     * Method untuk menghitung nilai digital signature dan hasilnya dalam
     * bentuk array of byte.
     * @param message
     * @return
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.InvalidKeyException
     * @throws java.security.SignatureException
     */
    public byte[] getDigitalSignature(String message) throws
            NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        byte[] result = new byte[1];

        Signature sign = Signature.getInstance("MD5withRSA");
        sign.initSign(pk);
        byte[] byteMessage = message.getBytes();
        sign.update(byteMessage, 0, byteMessage.length);
        result = sign.sign();

        return result;
    }

    /**
     * Method untuk menghitung digital signature yang hasilnya berupa String
     * hexadesimal sehingga bisa langsung digunakan dalam message XML.
     * @param message
     * @return
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.InvalidKeyException
     * @throws java.security.SignatureException
     */
    public String getDigitalSignatureAsString(String message) throws
            NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        byte[] sign = getDigitalSignature(message);
        return new String(Hex.encodeHex(sign));
    }

    /**
     * Method untuk mengecek kebenaran digital signature dengan input berupa
     * origila message yang dibuat dari penggabungan elemen-elemen penyusun
     * digital signature, dan signature dalam bentuk array of byte.
     * @param message
     * @param signature
     * @return
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.InvalidKeyException
     * @throws java.security.SignatureException
     */
    public boolean verifyDigitalSignature(String message, byte[] signature)
            throws NoSuchAlgorithmException, InvalidKeyException,
            SignatureException {
        boolean result = false;

        Signature sign = Signature.getInstance("MD5withRSA");
        sign.initVerify(opb);
        byte[] byteMessage = message.getBytes();
        sign.update(byteMessage, 0, byteMessage.length);
        result = sign.verify(signature);
        return result;
    }

    /**
     * Method ini adalah bentuk yang langsung dapat dipakai dari method
     * verifyDigitalSignature, dengan input berupa original message berrupa String
     * dan signature berupa String dalam format hexadesimal.
     * @param message
     * @param signature
     * @return
     * @throws java.io.IOException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.InvalidKeyException
     * @throws java.security.SignatureException
     * @throws org.apache.commons.codec.DecoderException
     */
    public boolean verifyStringDigitalSignature(String message, String signature)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException,
            SignatureException, DecoderException {
        boolean result = false;
        byte[] sign = Hex.decodeHex(signature.toCharArray());
        result = verifyDigitalSignature(message, sign);

        return result;
    }
}
