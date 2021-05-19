package com.myandroid.dangdinhsi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.myandroid.dangdinhsi.Entity.AppDatabase;
import com.myandroid.dangdinhsi.Entity.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AppDatabase db;
    List<User> list = new ArrayList<>();
    EditText edName,edEmail,edMessage;
    Spinner spinner;
    CheckBox checkBox;
    Button btSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= AppDatabase.getAppDatabase(this);
        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edMessage = findViewById(R.id.edMessage);
        checkBox = findViewById(R.id.ck);
        btSend = findViewById(R.id.btSend);
        btSend.setOnClickListener(this);
        String[] rate = {"Rate 1*","Rate 2*","Rate 3*","Rate 4*","Rate 5*"};
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,rate);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v==btSend){
            onSendFeedBack();
            list = db.userDAO().getAllUser();
            String count = String.valueOf(list.size());
           Toast.makeText(this,"Size Database: "+count,Toast.LENGTH_LONG).show();
        }
    }

    public void onSendFeedBack(){
        list = new ArrayList<>();

        if(edName.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter name",Toast.LENGTH_SHORT).show();
            return;
        }
        if(edMessage.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter message",Toast.LENGTH_SHORT).show();
            return;
        }
        if(edEmail.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }

        if(!checkBox.isChecked()){
            Toast.makeText(this,"Please agree rules",Toast.LENGTH_SHORT).show();
            return;
        }
        User user =new User();
        user.setName(edName.getText().toString());
        user.setEmail(edEmail.getText().toString());
        user.setMessage(edMessage.getText().toString());
        user.setRate(spinner.getSelectedItem().toString());
        db.userDAO().insertUser(user);
    }
}