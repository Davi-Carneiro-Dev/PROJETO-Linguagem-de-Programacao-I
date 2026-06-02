package controller;

import model.Aluno;
import model.Disciplina;
import model.Nota;
import service.NotaService;

import java.util.List;

public class NotaController {

    private final NotaService notaService = new NotaService();

    public boolean registrarNota(Aluno aluno,
                                 Disciplina disciplina,
                                 double valor) {

        Nota nota = new Nota(aluno, disciplina, valor);

        return notaService.registrarNota(nota);
    }

    public List<Nota> listarNotas() {
        return notaService.listarNotas();
    }

    public Nota buscarNota(String matriculaAluno, int codigoDisciplina) {
        return notaService.buscarNota(matriculaAluno, codigoDisciplina);
    }

    public boolean alterarNota(String matriculaAluno,
                               int codigoDisciplina,
                               double novoValor) {

        return notaService.alterarNota(
                matriculaAluno,
                codigoDisciplina,
                novoValor
        );
    }

    public boolean removerNota(String matriculaAluno, int codigoDisciplina) {
        return notaService.removerNota(matriculaAluno, codigoDisciplina);
    }

    public List<Nota> listarNotasPorAluno(String matriculaAluno) {
        return notaService.listarNotasPorAluno(matriculaAluno);
    }

    public double calcularMediaAluno(String matriculaAluno) {
        return notaService.calcularMediaAluno(matriculaAluno);
    }
}