package com.example.mission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class popup_s extends AppCompatActivity {

    Button okBtn;
    public String answer = "5times";
    EditText editText = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_s);

        okBtn=(Button)findViewById(R.id.button1);
        editText = (EditText) findViewById(R.id.edit_answer);

        okBtn.setOnClickListener(new View.OnClickListener() {

            //일단 정답팝업으로만 가게 해놓음. 디비 정답이랑 매칭후 맞으면 yes 틀리면 no 팝업으로 가야함.
            @Override
            public void onClick(View view) {
                if(answer.equals(editText.getText().toString())){
                    Intent intent=new Intent(getApplicationContext(),popup_yes.class);
                    startActivity(intent);
                }
                else{
                    Intent intent=new Intent(getApplicationContext(),popup_no.class);
                    startActivity(intent);
                }

            }
        });
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

}

