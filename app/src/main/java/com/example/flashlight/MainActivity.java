package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        image = findViewById(R.id.imageView);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(image.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.closed).getConstantState())){
                    image.setImageResource(R.drawable.on);
                    changeLightState(true);
                }
                else{
                    image.setImageResource(R.drawable.closed);
                    changeLightState(false);
                }
            }

            public void changeLightState(boolean state) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
                    String camId = null;
                    try {
                        camId = cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(camId,state);
                    } catch (CameraAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        image.setImageResource(R.drawable.closed);
    }

}