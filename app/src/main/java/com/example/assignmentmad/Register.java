package com.example.assignmentmad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignmentmad.ADAPTERS.MemberAdapter;
import com.example.assignmentmad.DB.DB;
import com.example.assignmentmad.DB.DBHELPER;

public class Register extends AppCompatActivity {

    EditText txtname, txtemail, txtpass, txttel;
    Button submit;
    String name, email,pass, tel;

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    void init(){
        txtname = findViewById(R.id.txtname);
        txtemail = findViewById(R.id.txtemail);
        txtpass = findViewById(R.id.txtpass);
        txttel = findViewById(R.id.txttlp);
        submit = findViewById(R.id.registerbtn);
    }

    boolean cek(String pass){
        boolean kapital = false, angka = false, huruf = false;

        for(int i=0; i< pass.length();i++){
            char x = pass.charAt(i);
            if(Character.isUpperCase(x)){
                kapital = true;
            }else if(Character.isDigit(x)){
                angka = true;
            }else if(Character.isLowerCase(x)){
                huruf = true;
            }
        }

         if(kapital==true&&angka==true&&huruf==true){
            return true;
        }
         return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        openHelper = new DBHELPER(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            name = txtname.getText().toString();
            email = txtemail.getText().toString();
            pass = txtpass.getText().toString();
            tel = txttel.getText().toString();

            if (!name.contains(" ")){
                txtname.setError("Input at least 2 name");
            }else if(!email.contains(".")&&!email.contains("@")){
                txtemail.setError("Input valid email");
            }else if(email.indexOf("@") < 1){
                txtemail.setError("Input valid email 1");
            }else if(email.contains("@.")&&email.contains(".@")){
                txtemail.setError("Input valid email 2");
            }else if(pass.length()<5){
                txtpass.setError("Password must be 5 chars or longer");
            }
            else if(cek(pass)==false){
                txtpass.setError("Password must have 1 alphabet and 1 number");
            }
            else if(!tel.startsWith("+62")){
                txttel.setError("Phone number must starts with +62");
            }else {
                int id = DB.memberlist.size()+1;
                //String id = Integer.toString(i);
                String telepon = tel.replace("0","+62");

                DB.addMember(id,name,email,pass,telepon);

                insertmember(name,email,pass,tel);
                Toast.makeText(getApplicationContext(),"Silahkan login kembali",Toast.LENGTH_LONG).show();

                Intent enter = new Intent(Register.this, LogIn.class);
                startActivity(enter);
            }
            }
        });

    }

    void insertmember(String name, String email, String pass, String tel){
        openHelper = new DBHELPER(this);
        db = openHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHELPER.m1,name);
        cv.put(DBHELPER.m2,email);
        cv.put(DBHELPER.m3,pass);
        cv.put(DBHELPER.m4,tel);
        db.insert(DBHELPER.tabel1,null,cv);
    }

}