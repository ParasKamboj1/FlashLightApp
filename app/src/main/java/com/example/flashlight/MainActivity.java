package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button button;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        image = findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(button.getText().toString().equals("ON")){
                    button.setText("OFF");
                    image.setImageResource(R.drawable.flashlighton);
                    changeLightState(true);
                }
                else{
                    button.setText("ON");
                    image.setImageResource(R.drawable.flashlightoff);
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
        button.setText("ON");
        image.setImageResource(R.drawable.flashlightoff);
    }

}