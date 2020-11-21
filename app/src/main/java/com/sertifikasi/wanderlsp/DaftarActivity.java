package com.sertifikasi.wanderlsp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sertifikasi.wanderlsp.database.SQLIteHelper;
import com.sertifikasi.wanderlsp.model.ModelDaftar;

public class DaftarActivity extends AppCompatActivity {
    EditText editNama,editEmail,editUsername, editPassword;
    Button btnDaftar, btnClear;
    SQLIteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        editNama = findViewById(R.id.namadaftar);
        editEmail = findViewById(R.id.emaildaftar);
        editUsername = findViewById(R.id.usernamedaftar);
        editPassword = findViewById(R.id.passworddaftar);
        btnClear = findViewById(R.id.clear);
        btnDaftar = findViewById(R.id.pendaftaran);
        sqliteHelper = new SQLIteHelper(this);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nama = editNama.getText().toString();
                String Email = editEmail.getText().toString();
                String Username = editUsername.getText().toString();
                String Password = editPassword.getText().toString();

                if (Nama.equals("") || Email.equals("") || Username.equals("") || Password.equals("")){
                    Toast.makeText(DaftarActivity.this, "Form tidak boleh ada yang kosong!", Toast.LENGTH_SHORT).show();
                }else {

                    if (sqliteHelper.InsertContact(new ModelDaftar(Nama, Email, Username, Password))){
                        startActivity(new Intent(DaftarActivity.this,LoginActivity.class));
                        finish();
                    }

                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNama.setText("");
                editEmail.setText("");
                editUsername.setText("");
                editPassword.setText("");
            }
        });

    }
}

