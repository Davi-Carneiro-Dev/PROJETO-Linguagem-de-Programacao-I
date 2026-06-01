package controller;

import model.Aluno;
import model.Frequencia;
import service.AlunoService;

import java.util.List;

public class AlunoController {

    private final AlunoService alunoService = new AlunoService();

    public boolean cadastrarAluno(String nome,
                                  String cpf,
                                  String usuario,
                                  String senha,
                                  String matricula) {

        Aluno aluno = new Aluno(nome, cpf, usuario, senha, matricula);

        return alunoService.cadastrarAluno(aluno);
    }

    public List<Aluno> listarAlunos() {
        return alunoService.listarAlunos();
    }

    public Aluno buscarAlunoPorMatricula(String matricula) {
        return alunoService.buscarAlunoPorMatricula(matricula);
    }

    public boolean alterarAluno(String matriculaAtual,
                                String novoNome,
                                String novoCpf,
                                String novoUsuario,
                                String novaSenha,
                                String novaMatricula) {

        return alunoService.alterarAluno(
                matriculaAtual,
                novoNome,
                novoCpf,
                novoUsuario,
                novaSenha,
                novaMatricula
        );
    }

    public boolean removerAluno(String matricula) {
        return alunoService.removerAluno(matricula);
    }

    public boolean registrarFrequencia(String matricula, double percentual) {
        Aluno aluno = alunoService.buscarAlunoPorMatricula(matricula);

        if (aluno == null) {
            return false;
        }

        Frequencia frequencia = new Frequencia(aluno, percentual);

        return alunoService.registrarFrequencia(frequencia);
    }

    public List<Frequencia> listarFrequencias() {
        return alunoService.listarFrequencias();
    }

    public Frequencia buscarFrequenciaPorMatricula(String matricula) {
        return alunoService.buscarFrequenciaPorMatricula(matricula);
    }

    public boolean alterarFrequencia(String matricula, double novoPercentual) {
        return alunoService.alterarFrequencia(matricula, novoPercentual);
    }
}