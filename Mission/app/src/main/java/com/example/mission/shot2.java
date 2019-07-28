package com.example.mission;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class shot2 extends AppCompatActivity {

    String mCurrentPhotoPath;
    Button btn01,btn02;
    public int status = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot2);

        final ImageView imageView = findViewById(R.id.imageView1);

        Intent intent = getIntent();
        final String assetName = intent.getStringExtra("assetName");
        mCurrentPhotoPath = intent.getStringExtra("mCurrentPhotoPath");


        imageView.post(new Runnable() {
            @Override
            public void run() {
                if (assetName != null) {
                    setPicFromAsset(assetName, imageView);
                } else if (mCurrentPhotoPath != null) {
                    setPicFromPath(mCurrentPhotoPath, imageView);
                }
            }
        });

        Button.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    //인증샷올리기 누르면 팝업창이 뜨게 됨. 업로드 부분 추가해야함.
                    case R.id.button1:
                        Intent intent = new Intent(shot2.this, popup.class);
                        intent.putExtra("next",3);
                        startActivityForResult(intent, 1);

                        break;
                    //촬영 다시하기 부분
                    case R.id.button2:
                        Intent intent2 = new Intent(shot2.this, shot.class);
                        startActivityForResult(intent2, 1);

                        break;

                }
            }
        };
        btn01 = (Button) findViewById(R.id.button1);
        btn01.setOnClickListener(btnListener);

        btn02 = (Button) findViewById(R.id.button2);
        btn02.setOnClickListener(btnListener);
    }



        private void setPicFromAsset(String assetName, ImageView imageView){
            // Get the dimensions of the View
            int targetW = imageView.getWidth();
            int targetH = imageView.getHeight();
        }


        private void setPicFromPath(String mCurrentPhotoPath, ImageView imageView) {
            // Get the dimensions of the View
            int targetW = imageView.getWidth();
            int targetH = imageView.getHeight();

            // Get the dimensions of the bitmap
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            // Determine how much to scale down the image
            int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;

            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
            Bitmap rotatedBitmap = bitmap;

            // rotate bitmap if needed
            try {
                ExifInterface ei = new ExifInterface(mCurrentPhotoPath);
                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        rotatedBitmap = rotateImage(bitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        rotatedBitmap = rotateImage(bitmap, 180);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        rotatedBitmap = rotateImage(bitmap, 270);
                        break;
                }
            } catch (IOException e) {
                Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

            imageView.setImageBitmap(rotatedBitmap);
        }

        public static Bitmap rotateImage(Bitmap source, float angle) {
            Matrix matrix = new Matrix();
            matrix.postRotate(angle);
            return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                    matrix, true);
        }



}


