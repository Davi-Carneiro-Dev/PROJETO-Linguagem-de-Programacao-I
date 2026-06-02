package service;

import enums.SituacaoAluno;
import model.Aluno;
import model.Boletim;
import model.Frequencia;
import model.Nota;

import java.util.List;

public class RelatorioService {

    public Boletim emitirBoletim(Aluno aluno,
                                 List<Nota> notas,
                                 Frequencia frequencia) {

        double media = calcularMedia(notas);
        double percentualFrequencia = obterPercentualFrequencia(frequencia);

        SituacaoAluno situacao = definirSituacao(media, percentualFrequencia);

        aluno.setSituacao(situacao);

        return new Boletim(aluno, media, situacao);
    }

    public String consultarHistoricoEscolar(Aluno aluno,
                                            List<Nota> notas,
                                            Frequencia frequencia) {

        StringBuilder historico = new StringBuilder();

        historico.append("=== HISTÓRICO ESCOLAR ===\n");
        historico.append("Aluno: ").append(aluno.getNome()).append("\n");
        historico.append("Matrícula: ").append(aluno.getMatricula()).append("\n\n");

        historico.append("Notas:\n");

        if (notas.isEmpty()) {
            historico.append("Nenhuma nota registrada.\n");
        } else {
            for (Nota nota : notas) {
                historico.append("- ")
                        .append(nota.getDisciplina().getNome())
                        .append(": ")
                        .append(nota.getValor())
                        .append("\n");
            }
        }

        historico.append("\nFrequência: ")
                .append(obterPercentualFrequencia(frequencia))
                .append("%\n");

        historico.append("Situação: ")
                .append(aluno.getSituacao())
                .append("\n");

        return historico.toString();
    }

    public String gerarRelatorioNotas(List<Nota> notas) {

        StringBuilder relatorio = new StringBuilder();

        relatorio.append("=== RELATÓRIO DE NOTAS ===\n");

        if (notas.isEmpty()) {
            relatorio.append("Nenhuma nota registrada.\n");
            return relatorio.toString();
        }

        for (Nota nota : notas) {
            relatorio.append("Aluno: ")
                    .append(nota.getAluno().getNome())
                    .append(" | Disciplina: ")
                    .append(nota.getDisciplina().getNome())
                    .append(" | Nota: ")
                    .append(nota.getValor())
                    .append("\n");
        }

        return relatorio.toString();
    }

    public String gerarRelatorioFrequencia(List<Frequencia> frequencias) {

        StringBuilder relatorio = new StringBuilder();

        relatorio.append("=== RELATÓRIO DE FREQUÊNCIA ===\n");

        if (frequencias.isEmpty()) {
            relatorio.append("Nenhuma frequência registrada.\n");
            return relatorio.toString();
        }

        for (Frequencia frequencia : frequencias) {
            relatorio.append("Aluno: ")
                    .append(frequencia.getAluno().getNome())
                    .append(" | Frequência: ")
                    .append(frequencia.getPercentual())
                    .append("%\n");
        }

        return relatorio.toString();
    }

    public String gerarRelatorioAprovadosReprovados(List<Aluno> alunos) {

        StringBuilder relatorio = new StringBuilder();

        relatorio.append("=== ALUNOS APROVADOS ===\n");

        for (Aluno aluno : alunos) {
            if (aluno.getSituacao() == SituacaoAluno.APROVADO) {
                relatorio.append("- ")
                        .append(aluno.getNome())
                        .append("\n");
            }
        }

        relatorio.append("\n=== ALUNOS EM RECUPERAÇÃO ===\n");

        for (Aluno aluno : alunos) {
            if (aluno.getSituacao() == SituacaoAluno.RECUPERACAO) {
                relatorio.append("- ")
                        .append(aluno.getNome())
                        .append("\n");
            }
        }

        relatorio.append("\n=== ALUNOS REPROVADOS ===\n");

        for (Aluno aluno : alunos) {
            if (aluno.getSituacao() == SituacaoAluno.REPROVADO) {
                relatorio.append("- ")
                        .append(aluno.getNome())
                        .append("\n");
            }
        }

        return relatorio.toString();
    }

    private double calcularMedia(List<Nota> notas) {

        if (notas.isEmpty()) {
            return 0;
        }

        double soma = 0;

        for (Nota nota : notas) {
            soma += nota.getValor();
        }

        return soma / notas.size();
    }

    private double obterPercentualFrequencia(Frequencia frequencia) {

        if (frequencia == null) {
            return 0;
        }

        return frequencia.getPercentual();
    }

    private SituacaoAluno definirSituacao(double media,
                                          double frequencia) {

        if (media >= 7 && frequencia >= 75) {
            return SituacaoAluno.APROVADO;
        }

        if (media >= 5 && frequencia >= 75) {
            return SituacaoAluno.RECUPERACAO;
        }

        return SituacaoAluno.REPROVADO;
    }
}