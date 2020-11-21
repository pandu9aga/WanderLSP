package com.sertifikasi.wanderlsp.model;

public class ModelDaftar {
    String Nama, Email, Username, Password;

    public ModelDaftar(){

    }

    public ModelDaftar(String Nama, String Email, String Username, String Password){
        this.Nama = Nama;
        this.Email = Email;
        this.Username = Username;
        this.Password = Password;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

