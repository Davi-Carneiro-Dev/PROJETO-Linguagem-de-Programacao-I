package model;

public class Professor extends Pessoa {

    private String especialidade;

    public Professor(String nome,
                     String cpf,
                     String usuario,
                     String senha,
                     String especialidade) {

        super(nome, cpf, usuario, senha);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "nome='" + getNome() + '\'' +
                ", especialidade='" + especialidade + '\'' +
                '}';
    }
}