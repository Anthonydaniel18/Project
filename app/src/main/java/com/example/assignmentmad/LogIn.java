package com.example.assignmentmad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignmentmad.DB.DB;
import com.example.assignmentmad.DB.DBHELPER;
import com.example.assignmentmad.DB.MODELS.Hotel;
import com.example.assignmentmad.DB.MODELS.Member;

import java.util.Vector;

public class LogIn extends AppCompatActivity {

    EditText pass, email;
    Button ok, reg;
    String mail, password,tel;
    int id;

    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;
    Register rg;

    void init(){
        email = findViewById(R.id.emailtxt);
        pass = findViewById(R.id.passtxt);
        ok = findViewById(R.id.okbtn);
        reg = findViewById(R.id.regbtn);
    }

    boolean cek(String email, String pass){
        Cursor c = db.rawQuery("Select * From " + DBHELPER.tabel1,null);

        while(c.moveToNext()){
            DB.memberlist.add(new Member(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4)));

            int a = c.getInt(0);
            String b = c.getString(1);
            String f = c.getString(2);
            String d = c.getString(3);
            String e = c.getString(4);
            Log.d("Database isi member", String.format("%d,%s,%s,%s,%s",a,b,f,d,e));
        }

        for(int i = 0; i < DB.memberlist.size(); i++){
            if(email.equals(DB.memberlist.get(i).mmbemail)&&pass.equals(DB.memberlist.get(i).mmbpass)){

                id = DB.memberlist.get(i).mmbid;
                tel = DB.memberlist.get(i).mmbtel;
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        init();

        openHelper = new DBHELPER(this);
        db = openHelper.getWritableDatabase();

        mail = email.getText().toString();
        password = pass.getText().toString();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(LogIn.this, Register.class);
                startActivity(reg);
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(mail.equals("")||password.equals("")){
                Toast.makeText(LogIn.this, "Please Input Email and Password", Toast.LENGTH_SHORT).show();
            }
            else if(cek(email.getText().toString(),pass.getText().toString())==true){

                DB.hotellist.clear();

                Cursor cs = db.rawQuery("Select * From " + DBHELPER.tabel3,null);

                while(cs.moveToNext()) {
                    DB.hotellist.add(new Hotel(cs.getString(0), cs.getString(1),
                            cs.getString(2), cs.getString(3), cs.getString(4),
                            cs.getString(5), cs.getInt(6), cs.getString(7)));

                    String aa = cs.getString(0);
                    String bb = cs.getString(1);
                    String ff = cs.getString(2);
                    String dd = cs.getString(3);
                    String ee = cs.getString(4);
                    int gg = cs.getInt(5);
                    String hh = cs.getString(6);
                    Log.d("Database isi hotel", String.format("%s,%s,%s,%s,%s,%d,%s", aa, bb, ff, dd, ee, gg, hh));
                }

                Toast.makeText(getApplicationContext(),"Selamat datang",Toast.LENGTH_LONG).show();
                Intent i = new Intent(LogIn.this,HomePage.class);
                i.putExtra("idmember",Integer.toString(id));
                i.putExtra("notel",tel);
                startActivity(i);
            }else{
                Toast.makeText(getApplicationContext(),"Anda belum terdaftar",Toast.LENGTH_LONG).show();
            }
        }}
        );

    }

}
