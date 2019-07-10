package com.example.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public  static String KEY = "name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void handler(View view) {
        switch (view.getId()){
            case R.id.loginbtn:
                EditText editText1= findViewById(R.id.unametxt);
                String name=editText1.getText().toString();
                Intent intent=new Intent(this, LoginActivity.class);
                intent.putExtra(KEY,name);
                startActivity(intent);
                break;

            case R.id.signupbtn:

        }

    }



}
