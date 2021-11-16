package com.example.applicationobjectssqlite;

public class Pessoa {
    protected String RG;
    protected String nome;
    protected String email;

    public Pessoa(String RG, String nome, String email) {
        this.RG = RG;
        this.nome = nome;
        this.email = email;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Pessoa{RG='" + RG + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }

}
