package service;

import model.Aluno;
import model.Disciplina;
import model.Nota;

import java.util.ArrayList;
import java.util.List;

public class NotaService {

    private final List<Nota> notas = new ArrayList<>();

    public boolean registrarNota(Nota nota) {
        if (nota == null) {
            return false;
        }

        if (!notaValida(nota.getValor())) {
            return false;
        }

        notas.add(nota);
        return true;
    }

    public List<Nota> listarNotas() {
        return notas;
    }

    public Nota buscarNota(String matriculaAluno, int codigoDisciplina) {
        for (Nota nota : notas) {
            boolean mesmaMatricula = nota.getAluno()
                    .getMatricula()
                    .equals(matriculaAluno);

            boolean mesmaDisciplina = nota.getDisciplina()
                    .getCodigo() == codigoDisciplina;

            if (mesmaMatricula && mesmaDisciplina) {
                return nota;
            }
        }

        return null;
    }

    public boolean alterarNota(String matriculaAluno,
                               int codigoDisciplina,
                               double novoValor) {

        if (!notaValida(novoValor)) {
            return false;
        }

        Nota nota = buscarNota(matriculaAluno, codigoDisciplina);

        if (nota == null) {
            return false;
        }

        nota.setValor(novoValor);
        return true;
    }

    public boolean removerNota(String matriculaAluno, int codigoDisciplina) {
        Nota nota = buscarNota(matriculaAluno, codigoDisciplina);

        if (nota == null) {
            return false;
        }

        notas.remove(nota);
        return true;
    }

    public List<Nota> listarNotasPorAluno(String matriculaAluno) {
        List<Nota> notasDoAluno = new ArrayList<>();

        for (Nota nota : notas) {
            if (nota.getAluno().getMatricula().equals(matriculaAluno)) {
                notasDoAluno.add(nota);
            }
        }

        return notasDoAluno;
    }

    public double calcularMediaAluno(String matriculaAluno) {
        List<Nota> notasDoAluno = listarNotasPorAluno(matriculaAluno);

        if (notasDoAluno.isEmpty()) {
            return 0;
        }

        double soma = 0;

        for (Nota nota : notasDoAluno) {
            soma += nota.getValor();
        }

        return soma / notasDoAluno.size();
    }

    private boolean notaValida(double valor) {
        return valor >= 0 && valor <= 10;
    }
}
