package controller;

import model.Disciplina;
import service.DisciplinaService;

import java.util.List;

public class DisciplinaController {

    private final DisciplinaService disciplinaService = new DisciplinaService();

    public boolean cadastrarDisciplina(int codigo,
                                       String nome,
                                       int cargaHoraria) {

        Disciplina disciplina = new Disciplina(
                codigo,
                nome,
                cargaHoraria
        );

        return disciplinaService.cadastrarDisciplina(disciplina);
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinaService.listarDisciplinas();
    }

    public Disciplina buscarDisciplinaPorCodigo(int codigo) {
        return disciplinaService.buscarDisciplinaPorCodigo(codigo);
    }

    public boolean alterarDisciplina(int codigo,
                                     String novoNome,
                                     int novaCargaHoraria) {

        return disciplinaService.alterarDisciplina(
                codigo,
                novoNome,
                novaCargaHoraria
        );
    }

    public boolean removerDisciplina(int codigo) {
        return disciplinaService.removerDisciplina(codigo);
    }
}
