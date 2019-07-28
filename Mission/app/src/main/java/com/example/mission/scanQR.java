package com.example.mission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class scanQR extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);
        new IntentIntegrator(this).initiateScan();


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String compare_string = "test_qr_text";

        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                // todo
            }
            else if(compare_string.equals(result.getContents())){//qr코드가 스캔되면 data를 받아와 일치하는지 확인하고 팝업창을 띄우는 부분
                Toast.makeText(this, "스캔완료: " + result.getContents(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(scanQR.this, popup.class);
                intent.putExtra("next",3);
                startActivityForResult(intent, 1);
                // todo
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);


        }
    }
}
