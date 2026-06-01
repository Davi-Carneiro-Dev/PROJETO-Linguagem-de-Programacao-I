package controller;

import model.Disciplina;
import model.Professor;
import model.Turma;
import service.TurmaService;

import java.util.List;

public class TurmaController {

    private final TurmaService turmaService = new TurmaService();

    public boolean cadastrarTurma(int codigo,
                                  String nome,
                                  Professor professor,
                                  Disciplina disciplina) {

        Turma turma = new Turma(
                codigo,
                nome,
                professor,
                disciplina
        );

        return turmaService.cadastrarTurma(turma);
    }

    public List<Turma> listarTurmas() {
        return turmaService.listarTurmas();
    }

    public Turma buscarTurmaPorCodigo(int codigo) {
        return turmaService.buscarTurmaPorCodigo(codigo);
    }

    public boolean alterarTurma(int codigo,
                                String novoNome,
                                Professor novoProfessor,
                                Disciplina novaDisciplina) {

        return turmaService.alterarTurma(
                codigo,
                novoNome,
                novoProfessor,
                novaDisciplina
        );
    }

    public boolean removerTurma(int codigo) {
        return turmaService.removerTurma(codigo);
    }
}