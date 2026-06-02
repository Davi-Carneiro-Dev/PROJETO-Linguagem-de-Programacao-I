package controller;

import model.Aluno;
import model.Boletim;
import model.Frequencia;
import model.Nota;
import service.RelatorioService;

import java.util.List;

public class RelatorioController {

    private final RelatorioService relatorioService = new RelatorioService();

    private final AlunoController alunoController;
    private final NotaController notaController;

    public RelatorioController(AlunoController alunoController,
                               NotaController notaController) {

        this.alunoController = alunoController;
        this.notaController = notaController;
    }

    public Boletim emitirBoletim(String matricula) {

        Aluno aluno = alunoController.buscarAlunoPorMatricula(matricula);

        if (aluno == null) {
            return null;
        }

        List<Nota> notas = notaController.listarNotasPorAluno(matricula);
        Frequencia frequencia = alunoController.buscarFrequenciaPorMatricula(matricula);

        return relatorioService.emitirBoletim(
                aluno,
                notas,
                frequencia
        );
    }

    public String consultarHistoricoEscolar(String matricula) {

        Aluno aluno = alunoController.buscarAlunoPorMatricula(matricula);

        if (aluno == null) {
            return "Aluno não encontrado.";
        }

        List<Nota> notas = notaController.listarNotasPorAluno(matricula);
        Frequencia frequencia = alunoController.buscarFrequenciaPorMatricula(matricula);

        return relatorioService.consultarHistoricoEscolar(
                aluno,
                notas,
                frequencia
        );
    }

    public String gerarRelatorioNotas() {
        return relatorioService.gerarRelatorioNotas(
                notaController.listarNotas()
        );
    }

    public String gerarRelatorioFrequencia() {
        return relatorioService.gerarRelatorioFrequencia(
                alunoController.listarFrequencias()
        );
    }

    public String gerarRelatorioAprovadosReprovados() {

        for (Aluno aluno : alunoController.listarAlunos()) {
            emitirBoletim(aluno.getMatricula());
        }

        return relatorioService.gerarRelatorioAprovadosReprovados(
                alunoController.listarAlunos()
        );
    }
}