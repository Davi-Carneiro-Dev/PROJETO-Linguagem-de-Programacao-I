package service;

import model.Disciplina;
import model.Professor;
import model.Turma;

import java.util.ArrayList;
import java.util.List;

public class TurmaService {

    private final List<Turma> turmas = new ArrayList<>();

    public boolean cadastrarTurma(Turma turma) {
        if (turma == null) {
            return false;
        }

        if (buscarTurmaPorCodigo(turma.getCodigo()) != null) {
            return false;
        }

        turmas.add(turma);
        return true;
    }

    public List<Turma> listarTurmas() {
        return turmas;
    }

    public Turma buscarTurmaPorCodigo(int codigo) {
        for (Turma turma : turmas) {
            if (turma.getCodigo() == codigo) {
                return turma;
            }
        }

        return null;
    }

    public boolean alterarTurma(int codigo,
                                String novoNome,
                                Professor novoProfessor,
                                Disciplina novaDisciplina) {

        Turma turma = buscarTurmaPorCodigo(codigo);

        if (turma == null) {
            return false;
        }

        turma.setNome(novoNome);
        turma.setProfessor(novoProfessor);
        turma.setDisciplina(novaDisciplina);

        return true;
    }

    public boolean removerTurma(int codigo) {
        Turma turma = buscarTurmaPorCodigo(codigo);

        if (turma == null) {
            return false;
        }

        turmas.remove(turma);
        return true;
    }
}