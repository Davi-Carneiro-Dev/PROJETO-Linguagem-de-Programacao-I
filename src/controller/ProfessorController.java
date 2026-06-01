package controller;

import model.Professor;
import service.ProfessorService;

import java.util.List;

public class ProfessorController {

    private final ProfessorService professorService = new ProfessorService();

    public boolean cadastrarProfessor(String nome,
                                      String cpf,
                                      String usuario,
                                      String senha,
                                      String especialidade) {

        Professor professor = new Professor(
                nome,
                cpf,
                usuario,
                senha,
                especialidade
        );

        return professorService.cadastrarProfessor(professor);
    }

    public List<Professor> listarProfessores() {
        return professorService.listarProfessores();
    }

    public Professor buscarProfessorPorCpf(String cpf) {
        return professorService.buscarProfessorPorCpf(cpf);
    }

    public boolean alterarProfessor(String cpfAtual,
                                    String novoNome,
                                    String novoCpf,
                                    String novoUsuario,
                                    String novaSenha,
                                    String novaEspecialidade) {

        return professorService.alterarProfessor(
                cpfAtual,
                novoNome,
                novoCpf,
                novoUsuario,
                novaSenha,
                novaEspecialidade
        );
    }

    public boolean removerProfessor(String cpf) {
        return professorService.removerProfessor(cpf);
    }
}