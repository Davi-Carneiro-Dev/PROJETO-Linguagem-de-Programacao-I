package service;

import model.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaService {

    private final List<Disciplina> disciplinas = new ArrayList<>();

    public boolean cadastrarDisciplina(Disciplina disciplina) {
        if (disciplina == null) {
            return false;
        }

        if (buscarDisciplinaPorCodigo(disciplina.getCodigo()) != null) {
            return false;
        }

        disciplinas.add(disciplina);
        return true;
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinas;
    }

    public Disciplina buscarDisciplinaPorCodigo(int codigo) {
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getCodigo() == codigo) {
                return disciplina;
            }
        }

        return null;
    }

    public boolean alterarDisciplina(int codigo,
                                     String novoNome,
                                     int novaCargaHoraria) {

        Disciplina disciplina = buscarDisciplinaPorCodigo(codigo);

        if (disciplina == null) {
            return false;
        }

        disciplina.setNome(novoNome);
        disciplina.setCargaHoraria(novaCargaHoraria);

        return true;
    }

    public boolean removerDisciplina(int codigo) {
        Disciplina disciplina = buscarDisciplinaPorCodigo(codigo);

        if (disciplina == null) {
            return false;
        }

        disciplinas.remove(disciplina);
        return true;
    }
}