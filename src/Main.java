import controller.AlunoController;
import controller.DisciplinaController;
import controller.LoginController;
import controller.NotaController;
import controller.ProfessorController;
import controller.RelatorioController;
import controller.TurmaController;

import model.Aluno;
import model.Boletim;
import model.Disciplina;
import model.Frequencia;
import model.Nota;
import model.Professor;
import model.Turma;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final LoginController loginController = new LoginController();
    private static final AlunoController alunoController = new AlunoController();
    private static final ProfessorController professorController = new ProfessorController();
    private static final DisciplinaController disciplinaController = new DisciplinaController();
    private static final TurmaController turmaController = new TurmaController();
    private static final NotaController notaController = new NotaController();

    private static final RelatorioController relatorioController =
            new RelatorioController(alunoController, notaController);

    public static void main(String[] args) {

        System.out.println("=== SISTEMA ESCOLAR INTELIGENTE ===");

        if (!realizarLogin()) {
            System.out.println("Acesso negado.");
            return;
        }

        int opcao;

        do {
            exibirMenuPrincipal();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    menuAlunos();
                    break;
                case 2:
                    menuProfessores();
                    break;
                case 3:
                    menuDisciplinas();
                    break;
                case 4:
                    menuTurmas();
                    break;
                case 5:
                    menuNotas();
                    break;
                case 6:
                    menuFrequencia();
                    break;
                case 7:
                    menuBoletimRelatorios();
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static boolean realizarLogin() {
        System.out.println("\n=== LOGIN ===");

        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        boolean loginValido = loginController.realizarLogin(usuario, senha);

        if (loginValido) {
            String perfil = loginController.identificarPerfil(usuario, senha);
            System.out.println("Login realizado com sucesso!");
            System.out.println("Perfil: " + perfil);
        }

        return loginValido;
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1 - Gerenciar alunos");
        System.out.println("2 - Gerenciar professores");
        System.out.println("3 - Gerenciar disciplinas");
        System.out.println("4 - Gerenciar turmas");
        System.out.println("5 - Registrar notas");
        System.out.println("6 - Registrar frequência");
        System.out.println("7 - Boletim e relatórios");
        System.out.println("0 - Sair");
    }

    private static void menuAlunos() {
        int opcao;

        do {
            System.out.println("\n=== MENU ALUNOS ===");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Listar alunos");
            System.out.println("3 - Buscar aluno");
            System.out.println("4 - Alterar aluno");
            System.out.println("5 - Remover aluno");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    listarAlunos();
                    break;
                case 3:
                    buscarAluno();
                    break;
                case 4:
                    alterarAluno();
                    break;
                case 5:
                    removerAluno();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void cadastrarAluno() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        boolean cadastrado = alunoController.cadastrarAluno(
                nome, cpf, usuario, senha, matricula
        );

        if (cadastrado) {
            System.out.println("Aluno cadastrado com sucesso.");
        } else {
            System.out.println("Erro ao cadastrar aluno.");
        }
    }

    private static void listarAlunos() {
        System.out.println("\n=== LISTA DE ALUNOS ===");

        if (alunoController.listarAlunos().isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        for (Aluno aluno : alunoController.listarAlunos()) {
            System.out.println(aluno);
        }
    }

    private static void buscarAluno() {
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        Aluno aluno = alunoController.buscarAlunoPorMatricula(matricula);

        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
        } else {
            System.out.println(aluno);
        }
    }

    private static void alterarAluno() {
        System.out.print("Matrícula atual: ");
        String matriculaAtual = scanner.nextLine();

        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine();

        System.out.print("Novo CPF: ");
        String novoCpf = scanner.nextLine();

        System.out.print("Novo usuário: ");
        String novoUsuario = scanner.nextLine();

        System.out.print("Nova senha: ");
        String novaSenha = scanner.nextLine();

        System.out.print("Nova matrícula: ");
        String novaMatricula = scanner.nextLine();

        boolean alterado = alunoController.alterarAluno(
                matriculaAtual,
                novoNome,
                novoCpf,
                novoUsuario,
                novaSenha,
                novaMatricula
        );

        if (alterado) {
            System.out.println("Aluno alterado com sucesso.");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    private static void removerAluno() {
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        boolean removido = alunoController.removerAluno(matricula);

        if (removido) {
            System.out.println("Aluno removido com sucesso.");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    private static void menuProfessores() {
        int opcao;

        do {
            System.out.println("\n=== MENU PROFESSORES ===");
            System.out.println("1 - Cadastrar professor");
            System.out.println("2 - Listar professores");
            System.out.println("3 - Buscar professor");
            System.out.println("4 - Alterar professor");
            System.out.println("5 - Remover professor");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarProfessor();
                    break;
                case 2:
                    listarProfessores();
                    break;
                case 3:
                    buscarProfessor();
                    break;
                case 4:
                    alterarProfessor();
                    break;
                case 5:
                    removerProfessor();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void cadastrarProfessor() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();

        boolean cadastrado = professorController.cadastrarProfessor(
                nome, cpf, usuario, senha, especialidade
        );

        if (cadastrado) {
            System.out.println("Professor cadastrado com sucesso.");
        } else {
            System.out.println("Erro ao cadastrar professor.");
        }
    }

    private static void listarProfessores() {
        System.out.println("\n=== LISTA DE PROFESSORES ===");

        if (professorController.listarProfessores().isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
            return;
        }

        for (Professor professor : professorController.listarProfessores()) {
            System.out.println(professor);
        }
    }

    private static void buscarProfessor() {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        Professor professor = professorController.buscarProfessorPorCpf(cpf);

        if (professor == null) {
            System.out.println("Professor não encontrado.");
        } else {
            System.out.println(professor);
        }
    }

    private static void alterarProfessor() {
        System.out.print("CPF atual: ");
        String cpfAtual = scanner.nextLine();

        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine();

        System.out.print("Novo CPF: ");
        String novoCpf = scanner.nextLine();

        System.out.print("Novo usuário: ");
        String novoUsuario = scanner.nextLine();

        System.out.print("Nova senha: ");
        String novaSenha = scanner.nextLine();

        System.out.print("Nova especialidade: ");
        String novaEspecialidade = scanner.nextLine();

        boolean alterado = professorController.alterarProfessor(
                cpfAtual,
                novoNome,
                novoCpf,
                novoUsuario,
                novaSenha,
                novaEspecialidade
        );

        if (alterado) {
            System.out.println("Professor alterado com sucesso.");
        } else {
            System.out.println("Professor não encontrado.");
        }
    }

    private static void removerProfessor() {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        boolean removido = professorController.removerProfessor(cpf);

        if (removido) {
            System.out.println("Professor removido com sucesso.");
        } else {
            System.out.println("Professor não encontrado.");
        }
    }

    private static void menuDisciplinas() {
        int opcao;

        do {
            System.out.println("\n=== MENU DISCIPLINAS ===");
            System.out.println("1 - Cadastrar disciplina");
            System.out.println("2 - Listar disciplinas");
            System.out.println("3 - Buscar disciplina");
            System.out.println("4 - Alterar disciplina");
            System.out.println("5 - Remover disciplina");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarDisciplina();
                    break;
                case 2:
                    listarDisciplinas();
                    break;
                case 3:
                    buscarDisciplina();
                    break;
                case 4:
                    alterarDisciplina();
                    break;
                case 5:
                    removerDisciplina();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void cadastrarDisciplina() {
        int codigo = lerInteiro("Código: ");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        int cargaHoraria = lerInteiro("Carga horária: ");

        boolean cadastrado = disciplinaController.cadastrarDisciplina(
                codigo, nome, cargaHoraria
        );

        if (cadastrado) {
            System.out.println("Disciplina cadastrada com sucesso.");
        } else {
            System.out.println("Erro ao cadastrar disciplina.");
        }
    }

    private static void listarDisciplinas() {
        System.out.println("\n=== LISTA DE DISCIPLINAS ===");

        if (disciplinaController.listarDisciplinas().isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }

        for (Disciplina disciplina : disciplinaController.listarDisciplinas()) {
            System.out.println(disciplina);
        }
    }

    private static void buscarDisciplina() {
        int codigo = lerInteiro("Código: ");

        Disciplina disciplina = disciplinaController.buscarDisciplinaPorCodigo(codigo);

        if (disciplina == null) {
            System.out.println("Disciplina não encontrada.");
        } else {
            System.out.println(disciplina);
        }
    }

    private static void alterarDisciplina() {
        int codigo = lerInteiro("Código: ");

        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine();

        int novaCargaHoraria = lerInteiro("Nova carga horária: ");

        boolean alterada = disciplinaController.alterarDisciplina(
                codigo, novoNome, novaCargaHoraria
        );

        if (alterada) {
            System.out.println("Disciplina alterada com sucesso.");
        } else {
            System.out.println("Disciplina não encontrada.");
        }
    }

    private static void removerDisciplina() {
        int codigo = lerInteiro("Código: ");

        boolean removida = disciplinaController.removerDisciplina(codigo);

        if (removida) {
            System.out.println("Disciplina removida com sucesso.");
        } else {
            System.out.println("Disciplina não encontrada.");
        }
    }

    private static void menuTurmas() {
        int opcao;

        do {
            System.out.println("\n=== MENU TURMAS ===");
            System.out.println("1 - Cadastrar turma");
            System.out.println("2 - Listar turmas");
            System.out.println("3 - Buscar turma");
            System.out.println("4 - Alterar turma");
            System.out.println("5 - Remover turma");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarTurma();
                    break;
                case 2:
                    listarTurmas();
                    break;
                case 3:
                    buscarTurma();
                    break;
                case 4:
                    alterarTurma();
                    break;
                case 5:
                    removerTurma();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void cadastrarTurma() {
        int codigo = lerInteiro("Código da turma: ");

        System.out.print("Nome da turma: ");
        String nome = scanner.nextLine();

        System.out.print("CPF do professor: ");
        String cpfProfessor = scanner.nextLine();

        Professor professor = professorController.buscarProfessorPorCpf(cpfProfessor);

        if (professor == null) {
            System.out.println("Professor não encontrado. Cadastre o professor primeiro.");
            return;
        }

        int codigoDisciplina = lerInteiro("Código da disciplina: ");

        Disciplina disciplina = disciplinaController.buscarDisciplinaPorCodigo(codigoDisciplina);

        if (disciplina == null) {
            System.out.println("Disciplina não encontrada. Cadastre a disciplina primeiro.");
            return;
        }

        boolean cadastrada = turmaController.cadastrarTurma(
                codigo, nome, professor, disciplina
        );

        if (cadastrada) {
            System.out.println("Turma cadastrada com sucesso.");
        } else {
            System.out.println("Erro ao cadastrar turma.");
        }
    }

    private static void listarTurmas() {
        System.out.println("\n=== LISTA DE TURMAS ===");

        if (turmaController.listarTurmas().isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
            return;
        }

        for (Turma turma : turmaController.listarTurmas()) {
            System.out.println(turma);
        }
    }

    private static void buscarTurma() {
        int codigo = lerInteiro("Código: ");

        Turma turma = turmaController.buscarTurmaPorCodigo(codigo);

        if (turma == null) {
            System.out.println("Turma não encontrada.");
        } else {
            System.out.println(turma);
        }
    }

    private static void alterarTurma() {
        int codigo = lerInteiro("Código da turma: ");

        System.out.print("Novo nome da turma: ");
        String novoNome = scanner.nextLine();

        System.out.print("CPF do novo professor: ");
        String cpfProfessor = scanner.nextLine();

        Professor professor = professorController.buscarProfessorPorCpf(cpfProfessor);

        if (professor == null) {
            System.out.println("Professor não encontrado.");
            return;
        }

        int codigoDisciplina = lerInteiro("Código da nova disciplina: ");

        Disciplina disciplina = disciplinaController.buscarDisciplinaPorCodigo(codigoDisciplina);

        if (disciplina == null) {
            System.out.println("Disciplina não encontrada.");
            return;
        }

        boolean alterada = turmaController.alterarTurma(
                codigo, novoNome, professor, disciplina
        );

        if (alterada) {
            System.out.println("Turma alterada com sucesso.");
        } else {
            System.out.println("Turma não encontrada.");
        }
    }

    private static void removerTurma() {
        int codigo = lerInteiro("Código: ");

        boolean removida = turmaController.removerTurma(codigo);

        if (removida) {
            System.out.println("Turma removida com sucesso.");
        } else {
            System.out.println("Turma não encontrada.");
        }
    }

    private static void menuNotas() {
        int opcao;

        do {
            System.out.println("\n=== MENU NOTAS ===");
            System.out.println("1 - Registrar nota");
            System.out.println("2 - Listar notas");
            System.out.println("3 - Alterar nota");
            System.out.println("4 - Remover nota");
            System.out.println("5 - Calcular média do aluno");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    registrarNota();
                    break;
                case 2:
                    listarNotas();
                    break;
                case 3:
                    alterarNota();
                    break;
                case 4:
                    removerNota();
                    break;
                case 5:
                    calcularMediaAluno();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void registrarNota() {
        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();

        Aluno aluno = alunoController.buscarAlunoPorMatricula(matricula);

        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        int codigoDisciplina = lerInteiro("Código da disciplina: ");

        Disciplina disciplina = disciplinaController.buscarDisciplinaPorCodigo(codigoDisciplina);

        if (disciplina == null) {
            System.out.println("Disciplina não encontrada.");
            return;
        }

        double valor = lerDouble("Nota: ");

        boolean registrada = notaController.registrarNota(
                aluno, disciplina, valor
        );

        if (registrada) {
            System.out.println("Nota registrada com sucesso.");
        } else {
            System.out.println("Erro ao registrar nota. A nota deve estar entre 0 e 10.");
        }
    }

    private static void listarNotas() {
        System.out.println("\n=== LISTA DE NOTAS ===");

        if (notaController.listarNotas().isEmpty()) {
            System.out.println("Nenhuma nota registrada.");
            return;
        }

        for (Nota nota : notaController.listarNotas()) {
            System.out.println(
                    "Aluno: " + nota.getAluno().getNome() +
                            " | Disciplina: " + nota.getDisciplina().getNome() +
                            " | Nota: " + nota.getValor()
            );
        }
    }

    private static void alterarNota() {
        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();

        int codigoDisciplina = lerInteiro("Código da disciplina: ");

        double novoValor = lerDouble("Nova nota: ");

        boolean alterada = notaController.alterarNota(
                matricula, codigoDisciplina, novoValor
        );

        if (alterada) {
            System.out.println("Nota alterada com sucesso.");
        } else {
            System.out.println("Nota não encontrada ou valor inválido.");
        }
    }

    private static void removerNota() {
        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();

        int codigoDisciplina = lerInteiro("Código da disciplina: ");

        boolean removida = notaController.removerNota(
                matricula, codigoDisciplina
        );

        if (removida) {
            System.out.println("Nota removida com sucesso.");
        } else {
            System.out.println("Nota não encontrada.");
        }
    }

    private static void calcularMediaAluno() {
        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();

        double media = notaController.calcularMediaAluno(matricula);

        System.out.println("Média do aluno: " + media);
    }

    private static void menuFrequencia() {
        int opcao;

        do {
            System.out.println("\n=== MENU FREQUÊNCIA ===");
            System.out.println("1 - Registrar frequência");
            System.out.println("2 - Listar frequências");
            System.out.println("3 - Alterar frequência");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    registrarFrequencia();
                    break;
                case 2:
                    listarFrequencias();
                    break;
                case 3:
                    alterarFrequencia();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void registrarFrequencia() {
        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();

        double percentual = lerDouble("Frequência em %: ");

        boolean registrada = alunoController.registrarFrequencia(
                matricula, percentual
        );

        if (registrada) {
            System.out.println("Frequência registrada com sucesso.");
        } else {
            System.out.println("Erro ao registrar frequência.");
        }
    }

    private static void listarFrequencias() {
        System.out.println("\n=== LISTA DE FREQUÊNCIAS ===");

        if (alunoController.listarFrequencias().isEmpty()) {
            System.out.println("Nenhuma frequência registrada.");
            return;
        }

        for (Frequencia frequencia : alunoController.listarFrequencias()) {
            System.out.println(
                    "Aluno: " + frequencia.getAluno().getNome() +
                            " | Frequência: " + frequencia.getPercentual() + "%"
            );
        }
    }

    private static void alterarFrequencia() {
        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();

        double percentual = lerDouble("Nova frequência em %: ");

        boolean alterada = alunoController.alterarFrequencia(
                matricula, percentual
        );

        if (alterada) {
            System.out.println("Frequência alterada com sucesso.");
        } else {
            System.out.println("Frequência não encontrada ou valor inválido.");
        }
    }

    private static void menuBoletimRelatorios() {
        int opcao;

        do {
            System.out.println("\n=== BOLETIM E RELATÓRIOS ===");
            System.out.println("1 - Emitir boletim escolar");
            System.out.println("2 - Consultar histórico escolar");
            System.out.println("3 - Gerar relatório de notas");
            System.out.println("4 - Gerar relatório de frequência");
            System.out.println("5 - Gerar relatório de aprovados/reprovados");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    emitirBoletim();
                    break;
                case 2:
                    consultarHistorico();
                    break;
                case 3:
                    System.out.println(relatorioController.gerarRelatorioNotas());
                    break;
                case 4:
                    System.out.println(relatorioController.gerarRelatorioFrequencia());
                    break;
                case 5:
                    System.out.println(relatorioController.gerarRelatorioAprovadosReprovados());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void emitirBoletim() {
        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();

        Boletim boletim = relatorioController.emitirBoletim(matricula);

        if (boletim == null) {
            System.out.println("Aluno não encontrado.");
        } else {
            System.out.println(boletim);
        }
    }

    private static void consultarHistorico() {
        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();

        System.out.println(relatorioController.consultarHistoricoEscolar(matricula));
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }
}