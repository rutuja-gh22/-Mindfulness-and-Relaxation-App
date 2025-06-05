package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SignupActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dbHelper = new DatabaseHelper(this);

        EditText n = (EditText)findViewById(R.id.name);
        EditText p = (EditText)findViewById(R.id.password);
        EditText c = (EditText)findViewById(R.id.confirmPassword);
        Button b = (Button)findViewById(R.id.button2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = n.getText().toString();
                String password = p.getText().toString();
                String confirmPassword = c.getText().toString();

                if(name.equals("") || password.equals("") || confirmPassword.equals("")){
                    Toast.makeText(SignupActivity.this,"All fields are mandatory",Toast.LENGTH_SHORT);
                }else{
                    if(password.equals(confirmPassword)){
                        Boolean check_name = dbHelper.checkName(name);

                        if(check_name == false ){
                            Boolean insert = dbHelper.insertData(name,password);

                            if(insert == true){
                                Toast.makeText(SignupActivity.this,"You have signed up successfully!",Toast.LENGTH_SHORT);
                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                i.putExtra("name",name.toString());
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(SignupActivity.this,"Signup failed",Toast.LENGTH_SHORT);

                            }
                        }else{
                            Toast.makeText(SignupActivity.this,"User already exists! Please login",Toast.LENGTH_SHORT);
                        }
                    }else{
                        Toast.makeText(SignupActivity.this,"Invalid Password",Toast.LENGTH_SHORT);
                    }
                }
            }
        });

        TextView t = (TextView)findViewById(R.id.textView6);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(i1);
            }
        });
    }
}