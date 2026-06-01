package service;

import model.Professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorService {

    private final List<Professor> professores = new ArrayList<>();

    public boolean cadastrarProfessor(Professor professor) {
        if (professor == null) {
            return false;
        }

        if (buscarProfessorPorCpf(professor.getCpf()) != null) {
            return false;
        }

        professores.add(professor);
        return true;
    }

    public List<Professor> listarProfessores() {
        return professores;
    }

    public Professor buscarProfessorPorCpf(String cpf) {
        for (Professor professor : professores) {
            if (professor.getCpf().equals(cpf)) {
                return professor;
            }
        }

        return null;
    }

    public boolean alterarProfessor(String cpfAtual,
                                    String novoNome,
                                    String novoCpf,
                                    String novoUsuario,
                                    String novaSenha,
                                    String novaEspecialidade) {

        Professor professor = buscarProfessorPorCpf(cpfAtual);

        if (professor == null) {
            return false;
        }

        professor.setNome(novoNome);
        professor.setCpf(novoCpf);
        professor.setUsuario(novoUsuario);
        professor.setSenha(novaSenha);
        professor.setEspecialidade(novaEspecialidade);

        return true;
    }

    public boolean removerProfessor(String cpf) {
        Professor professor = buscarProfessorPorCpf(cpf);

        if (professor == null) {
            return false;
        }

        professores.remove(professor);
        return true;
    }
}