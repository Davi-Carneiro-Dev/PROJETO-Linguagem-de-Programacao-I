package model;

import enums.SituacaoAluno;

public class Boletim {

    private Aluno aluno;
    private double media;
    private SituacaoAluno situacao;

    public Boletim(Aluno aluno,
                   double media,
                   SituacaoAluno situacao) {

        this.aluno = aluno;
        this.media = media;
        this.situacao = situacao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public double getMedia() {
        return media;
    }

    public SituacaoAluno getSituacao() {
        return situacao;
    }

    @Override
    public String toString() {
        return "Boletim{" +
                "aluno=" + aluno.getNome() +
                ", media=" + media +
                ", situacao=" + situacao +
                '}';
    }
}