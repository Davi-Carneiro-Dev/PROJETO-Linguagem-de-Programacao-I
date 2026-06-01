package model;

public class Turma {

    private int codigo;
    private String nome;
    private Professor professor;
    private Disciplina disciplina;

    public Turma(int codigo,
                 String nome,
                 Professor professor,
                 Disciplina disciplina) {

        this.codigo = codigo;
        this.nome = nome;
        this.professor = professor;
        this.disciplina = disciplina;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String toString() {
        return "Turma{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", professor=" + professor.getNome() +
                ", disciplina=" + disciplina.getNome() +
                '}';
    }
}