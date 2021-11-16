package com.example.applicationobjectssqlite;

public class Aluno extends Pessoa{
    private String RA;
    private String curso;

    public Aluno(String RA, String curso, String nome, String RG, String email) {
        super(RG,nome,email);
        this.RA = RA;
        this.curso = curso;
    }

    public String getRA() {
        return RA;
    }

    public void setRA(String RA) {
        this.RA = RA;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "RA='" + RA + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}