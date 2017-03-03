package com.example.autodoc.firebase;


import java.io.Serializable;

public class Usuario{

    private String mNome;
    private String mEmail;
    private String mSenha;

    public Usuario() {
    }

    public Usuario(String mNome, String mEmail, String mSenha) {
        this.mNome = mNome;
        this.mEmail = mEmail;
        this.mSenha = mSenha;
    }

    public String getmNome() {
        return mNome;
    }

    public void setmNome(String mNome) {
        this.mNome = mNome;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmSenha() {
        return mSenha;
    }

    public void setmSenha(String mSenha) {
        this.mSenha = mSenha;
    }

    @Override
    public String toString() {
        return "Nome: " +this.mNome + " Email: " +this.mEmail + " Senha: " +this.mSenha;
    }
}
