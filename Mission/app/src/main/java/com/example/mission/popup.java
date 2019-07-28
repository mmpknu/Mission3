package com.example.mission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*
* 1:shoot
* 2:QR
* 3:Quiz
* 4:puzzle
* 5:typing
* 6:AR
*
* */
public class popup extends AppCompatActivity {
    Button okBtn, canclebtn;
    public int status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        Bundle bundle = getIntent().getExtras();
        status = bundle.getInt("next");
        okBtn=(Button)findViewById(R.id.button1);

        okBtn.setOnClickListener(new View.OnClickListener() {

            //다음 미션 받기 클릭시 실행되는 부분, 각 단계에서 이 팝업을 부르면 그 다음으로 넘어가도록 구현해야함..
            @Override
            public void onClick(View view) {
                switch(status){
                    case 1:
                    {Intent intent=new Intent(getApplicationContext(),shot.class);
                    startActivity(intent);}
                        break;
                    case 2:
                    {Intent intent=new Intent(getApplicationContext(),QRcode.class);
                        startActivity(intent);}
                        break;
                    case 3:
                    {Intent intent=new Intent(getApplicationContext(),search.class);
                        startActivity(intent);}
                        break;
                    case 4:
                    {Intent intent=new Intent(getApplicationContext(),puzzle.class);
                        startActivity(intent);}
                        break;
                    case 5:
                    {Intent intent=new Intent(getApplicationContext(),typing.class);
                        startActivity(intent);}
                    break;
                    case 6:
                    {Intent intent=new Intent(getApplicationContext(),AR.class);
                        startActivity(intent);}
                    break;

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
