package com.example.antoine.BananaProject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

/*import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;*/
//import org.apache.commons.codec.binary.Base64;


/*import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.io.*;
import org.spongycastle.jcajce.provider.asymmetric.RSA;
import org.spongycastle.jcajce.provider.asymmetric.rsa.BCRSAPrivateKey;
import org.spongycastle.jcajce.provider.symmetric.ARC4;
import org.spongycastle.jce.provider.BouncyCastleProvider;*/


public class DisplayActivity extends AppCompatActivity {
    private static final String TAG = "";
    TextView text;
    PublicKey publicKey;
    PrivateKey privateKey;


    static {
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display);
        //this.setTitle(getString(R.string.title_activity_display));



        Intent intent = getIntent();

        byte[] ScanByte= intent.getByteArrayExtra("scanresultByte"); //Get the byte stream
        String ScanText = intent.getStringExtra("scanresultText");
        //System.out.println("SCAN RESULT :: " + QRCODE );

        text = (TextView) findViewById(R.id.OTP);
        text.setText("");

        //text.setText(value);
// Code to convert str to byte arr:

        //System.out.println('\n' + "QRcode :: " + ByteStream.toString() + '\n');
        System.out.println('\n' + "QRcode string :: " + ScanText+ '\n');
        byte[] StrToByte = ScanText.getBytes();
        String ByteToStr = new String(StrToByte);
        System.out.println('\n' + "QRcode bytes :: " + ByteToStr + '\n');
        String path_pkey = getString(R.string.path_privatekey);


         /* Get the private key from file. */

        try{
            privateKey = readPrivateKey(path_pkey);}
        catch(Exception e){
            e.printStackTrace();
        }
        //System.out.println("Details about loaded key :: ");
        //System.out.println(privateKey.toString());

        try {
            //String Decrypted =  RSADecrypt(ByteStream, privateKey) ;
            //byte[] afterDecrypting = decryptFromBase64(privateKey, ScanText);
            String StrDecrypted = decryptFromBase64(privateKey, ScanText);
            String StrOTP = StrDecrypted.substring(StrDecrypted.length()-8,StrDecrypted.length());
            //String StrOTP = new String(StrDecrypted);
            System.out.println(StrOTP);
            text.setText(StrOTP);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static byte[] decrypt(PrivateKey privateKey, byte[] encryptedText) throws Exception {

        try {
            Cipher rsaCipher = Cipher.getInstance("RSA", "SC");

            rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
            return rsaCipher.doFinal(encryptedText);
        } catch (Exception e) {
            Log.e(TAG, "Error while decrypting data: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static String decryptFromBase64(PrivateKey key, String cyphredText) throws Exception{
        byte[] afterDecrypting = decrypt(key, Base64.decode(cyphredText, Base64.DEFAULT));
        String decrypted = new String(afterDecrypting);
        return decrypted;
    }
    /**
     * Reads the PKCS#8 standard encoded RSA private key in
     * <code>filename</code>.
     *
     * @param filename The name of the file with the private key.
     *
     * @return The private key in <code>filename</code>.
     */


    public static PrivateKey readPrivateKey(String filename) throws Exception {
        FileInputStream file = new FileInputStream(filename);
        byte[] bytes = new byte[file.available()];
        file.read(bytes);
        file.close();
        PKCS8EncodedKeySpec privspec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PrivateKey privkey = factory.generatePrivate(privspec);
        return privkey;
    }
}
