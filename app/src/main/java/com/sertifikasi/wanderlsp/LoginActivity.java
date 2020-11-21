package com.sertifikasi.wanderlsp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sertifikasi.wanderlsp.database.SQLIteHelper;

public class LoginActivity extends AppCompatActivity {
    EditText editUserame, editPassword;
    Button btnLogin, btnDaftar;
    SharedPreferences sharedPreferences;
    String dataLogin;
    SQLIteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUserame = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);
        btnDaftar = findViewById(R.id.daftar);

        sharedPreferences = getSharedPreferences("Remember",MODE_PRIVATE);
        dataLogin = sharedPreferences.getString("Login","");
        if (dataLogin.isEmpty()){

        }else {
            startActivity(new Intent(LoginActivity.this,MapsActivity.class));
            finish();
        }
        Log.e("data login",dataLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v) {
                String Username = editUserame.getText().toString();
                String Password = editPassword.getText().toString();

                if (Username.equals("") || Password.equals("")){
                    Toast.makeText(LoginActivity.this, "Username dan Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                }else {

                    sqliteHelper = new SQLIteHelper(LoginActivity.this);
                    Cursor cursorlogin = sqliteHelper.getSingleUser(Username,Password);

                    if (cursorlogin.getCount()>0){
                        Toast.makeText(LoginActivity.this, "Selamat Datang "+Username,Toast.LENGTH_LONG).show();

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Login",Username);
                        editor.commit();
                        startActivity(new Intent(LoginActivity.this,MapsActivity.class));
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, "Username atau Password anda salah", Toast.LENGTH_LONG).show();
                        editUserame.setText("");
                        editPassword.setText("");
                        editUserame.requestFocus();
                    }

                }

                /*
                if (Username.equals("admin") && Password.equals("admin")){
                    Toast.makeText(LoginActivity.this, "Selamat Datang"+Username,Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Login",Username);
                    editor.commit();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Username atau Password anda salah", Toast.LENGTH_LONG).show();
                    editUserame.setText("");
                    editPassword.setText("");
                    editUserame.requestFocus();
                }
                */
            }
        });
    }

    public void FungsiDaftar(View view) {
        startActivity(new Intent(LoginActivity.this,DaftarActivity.class));
    }
}

