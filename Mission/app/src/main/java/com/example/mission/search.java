package com.example.mission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class search extends AppCompatActivity {


    Button btn01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        Intent intent = new Intent(search.this, popup_s.class);
                        startActivityForResult(intent, 1);

            }
        };
        btn01 = (Button) findViewById(R.id.button1);
        btn01.setOnClickListener(btnListener);


    }

}

