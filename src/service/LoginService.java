package service;

public class LoginService {

    private final String usuarioAdministrador = "admin";
    private final String senhaAdministrador = "123";

    private final String usuarioProfessor = "professor";
    private final String senhaProfessor = "123";

    public boolean realizarLogin(String usuario, String senha) {
        return validarAdministrador(usuario, senha)
                || validarProfessor(usuario, senha);
    }

    public String identificarPerfil(String usuario, String senha) {
        if (validarAdministrador(usuario, senha)) {
            return "ADMINISTRADOR";
        }

        if (validarProfessor(usuario, senha)) {
            return "PROFESSOR";
        }

        return "USUARIO_INVALIDO";
    }

    private boolean validarAdministrador(String usuario, String senha) {
        return usuarioAdministrador.equals(usuario)
                && senhaAdministrador.equals(senha);
    }

    private boolean validarProfessor(String usuario, String senha) {
        return usuarioProfessor.equals(usuario)
                && senhaProfessor.equals(senha);
    }
}