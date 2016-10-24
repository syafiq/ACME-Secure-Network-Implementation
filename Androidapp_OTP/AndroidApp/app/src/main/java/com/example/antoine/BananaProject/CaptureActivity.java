package com.example.antoine.BananaProject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CaptureActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {

        // Do something with the result here
        Intent myIntent = new Intent(CaptureActivity.this, DisplayActivity.class);
        myIntent.putExtra("scanresultText", rawResult.getText()); //Optional parameters
        myIntent.putExtra("scanresultByte", rawResult.getRawBytes()); //Optional parameters
        startActivity(myIntent);

        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }


}
