package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public  static String ADDRESS="address";
    StringBuilder hobby=new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String name =  getIntent().getExtras().getString(MainActivity.KEY);
        TextView tv1=findViewById(R.id.textView1);
        tv1.setText("Welcome "+name);
    }

    public void clickHandler(View view) {
        String gender="male";

        EditText editTextadd=findViewById(R.id.editTextaddress);
        RadioButton r2=findViewById(R.id.radioButtonfemale);
        if(r2.isChecked()){
             gender=r2.getText().toString();
        }
        CheckBox cb1=findViewById(R.id.checkBoxmusic);
        CheckBox cb2=findViewById(R.id.checkBoxsport);
        CheckBox cb3=findViewById(R.id.checkBoxtravel);
        if(cb1.isChecked()){
            hobby.append(cb1.getText().toString());
        }
        if(cb2.isChecked()){
            hobby.append(cb2.getText().toString());
        }
        if(cb3.isChecked()){
            hobby.append(cb3.getText().toString());
        }
        String address=editTextadd.getText().toString();
        Intent resultIntent=new Intent(this, MainActivity.class);
        resultIntent.putExtra(ADDRESS,address);
        Toast.makeText(getApplicationContext(),"address: "+address+"\ngender: "+gender+"\nhobbies: "+hobby,Toast.LENGTH_LONG).show();
        startActivity(resultIntent);
}
}
