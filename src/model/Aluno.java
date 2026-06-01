package model;

import enums.SituacaoAluno;

public class Aluno extends Pessoa {

    private String matricula;
    private SituacaoAluno situacao;

    public Aluno(String nome,
                 String cpf,
                 String usuario,
                 String senha,
                 String matricula) {

        super(nome, cpf, usuario, senha);
        this.matricula = matricula;
        this.situacao = SituacaoAluno.RECUPERACAO;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public SituacaoAluno getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoAluno situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + getNome() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", usuario='" + getUsuario() + '\'' +
                ", matricula='" + matricula + '\'' +
                ", situacao=" + situacao +
                '}';
    }
}