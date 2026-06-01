package model;

public class Frequencia {

    private Aluno aluno;
    private double percentual;

    public Frequencia(Aluno aluno,
                      double percentual) {

        this.aluno = aluno;
        this.percentual = percentual;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public double getPercentual() {
        return percentual;
    }

    public void setPercentual(double percentual) {
        this.percentual = percentual;
    }
}