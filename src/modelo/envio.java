package modelo;

import java.io.Serializable;

public class envio implements Serializable{
	
	String usuario;
	Boolean login;

	public Boolean getLogin() {
		return login;
	}

	public void setLogin(Boolean login) {
		this.login = login;
	}

	public envio(String usuario) {
		super();
		this.usuario = usuario;
	}

	public envio() {
		// TODO Auto-generated constructor stub
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
