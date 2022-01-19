package com.example.euskalmet;

import java.io.Serializable;

public class Envio implements Serializable {

	private static final long serialVersionUID = 3421362398450973738L;
	
	private String usuario;
    private Boolean login;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }
}
