package com.example.phonecallapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private EditText mEditTextNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextNumber = findViewById(R.id.edit_text_number);
        ImageView ImageCall = findViewById(R.id.phone_icon);

        ImageCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                makePhoneCall();

            }
        });
    }

    private void makePhoneCall () {

        String number = mEditTextNumber.getText().toString();

        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);

            } else {

                String dail = "tel: " + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dail)));

            }

        } else {

            Toast.makeText(MainActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CALL) {

            if (grantResults.length > 0 && grantResults [0] == PackageManager.PERMISSION_GRANTED) {

                makePhoneCall();

            } else {

                Toast.makeText(this, "Permission DENIED",Toast.LENGTH_SHORT).show();

            }
        }
    }
}
