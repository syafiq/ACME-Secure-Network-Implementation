package com.example.antoine.BananaProject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class BlankActivity extends AppCompatActivity {
    EditText edit;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
// TODO Auto-generated method stub
        //String default_ping = (String) findViewById(R.string.default_ping);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        text = (TextView) findViewById(R.id.textView1);

        edit = (EditText) findViewById(R.id.editText1);
        //edit.setText(R.string.default_ping);

        //text.setText(edit.getText());


    }

    public void scan(View v) {
        Intent myIntent = new Intent(BlankActivity.this, CaptureActivity.class);
        startActivity(myIntent);
    }


    public void ping(View v) {
// TODO Auto-generated method stub
        text.setText("");
        Editable host = edit.getText();
        try {
            String pingCmd = "ping -c 5 " + host;
            String pingResult = "";
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(pingCmd);
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                text.setText(inputLine + "\n\n");
                pingResult += inputLine;
                text.setText(pingResult);
            }
            in.close();
        }//try
        catch (IOException e) {
            System.out.println(e);
        }

    }//ping

    public void launch_app(View v) {
        text.setText("");
        try {
            Intent i = this.getPackageManager().getLaunchIntentForPackage(getString(R.string.package_name));
            this.startActivity(i);
        } catch (Exception e) {
            System.out.print(e);
        }
    }//launch_app

    public void myip(View v) {
        try {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            String new_text = "";
            for (NetworkInterface netint : Collections.list(nets)) {//All interfaces
                /*Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
                InetAddress inetAddresses2 = inetAddresses.nextElement();
                text.setText(netint.getDisplayName() + " : " + inetAddresses2);*/
                String Outputstring =  displayInterfaceInformation(netint);

                System.out.println(Outputstring);
                new_text = new_text + "\n" + Outputstring;
            }
            text.setText(new_text);
        }

    catch (Exception e)
    {
        System.out.println(e);
    }
        /*try {
            String ipCmd = "ifconfig *0 ";
            String pingResult = "";
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(ipCmd);
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                text.setText(inputLine + "\n\n");
                pingResult += inputLine;
                text.setText(pingResult);
            }
            in.close();
        }//try
        catch (IOException e) {
            System.out.println(e);
        }*/

    }//myip

    static String displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        String new_text = "";
        new_text = new_text + "Name: "+ netint.getName();
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        new_text = new_text + ", inetaddr : ";
        //if (Collections.list(inetAddresses).isEmpty()) {return "";}
        //else {
            for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                new_text = new_text + inetAddress.getHostAddress() + "  ";
            }
        //}

        return new_text;
    }

}