package service;

import model.Aluno;
import model.Frequencia;

import java.util.ArrayList;
import java.util.List;

public class AlunoService {

    private final List<Aluno> alunos = new ArrayList<>();
    private final List<Frequencia> frequencias = new ArrayList<>();

    public boolean cadastrarAluno(Aluno aluno) {
        if (aluno == null) {
            return false;
        }

        if (buscarAlunoPorMatricula(aluno.getMatricula()) != null) {
            return false;
        }

        alunos.add(aluno);
        return true;
    }

    public List<Aluno> listarAlunos() {
        return alunos;
    }

    public Aluno buscarAlunoPorMatricula(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }

        return null;
    }

    public boolean alterarAluno(String matriculaAtual,
                                String novoNome,
                                String novoCpf,
                                String novoUsuario,
                                String novaSenha,
                                String novaMatricula) {

        Aluno aluno = buscarAlunoPorMatricula(matriculaAtual);

        if (aluno == null) {
            return false;
        }

        aluno.setNome(novoNome);
        aluno.setCpf(novoCpf);
        aluno.setUsuario(novoUsuario);
        aluno.setSenha(novaSenha);
        aluno.setMatricula(novaMatricula);

        return true;
    }

    public boolean removerAluno(String matricula) {
        Aluno aluno = buscarAlunoPorMatricula(matricula);

        if (aluno == null) {
            return false;
        }

        alunos.remove(aluno);
        return true;
    }

    public boolean registrarFrequencia(Frequencia frequencia) {
        if (frequencia == null) {
            return false;
        }

        if (!frequenciaValida(frequencia.getPercentual())) {
            return false;
        }

        frequencias.add(frequencia);
        return true;
    }

    public List<Frequencia> listarFrequencias() {
        return frequencias;
    }

    public Frequencia buscarFrequenciaPorMatricula(String matricula) {
        for (Frequencia frequencia : frequencias) {
            if (frequencia.getAluno().getMatricula().equals(matricula)) {
                return frequencia;
            }
        }

        return null;
    }

    public boolean alterarFrequencia(String matricula, double novoPercentual) {
        if (!frequenciaValida(novoPercentual)) {
            return false;
        }

        Frequencia frequencia = buscarFrequenciaPorMatricula(matricula);

        if (frequencia == null) {
            return false;
        }

        frequencia.setPercentual(novoPercentual);
        return true;
    }

    private boolean frequenciaValida(double percentual) {
        return percentual >= 0 && percentual <= 100;
    }
}