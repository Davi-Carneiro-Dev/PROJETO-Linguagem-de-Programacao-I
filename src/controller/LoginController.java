package controller;

import service.LoginService;

public class LoginController {

    private final LoginService loginService = new LoginService();

    public boolean realizarLogin(String usuario, String senha) {
        return loginService.realizarLogin(usuario, senha);
    }

    public String identificarPerfil(String usuario, String senha) {
        return loginService.identificarPerfil(usuario, senha);
    }
}