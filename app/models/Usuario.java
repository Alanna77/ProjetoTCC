package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Usuario extends Model{
	
	public String nomeUsuario;
	public String senha;
	public boolean admin;
	
	public static String autenticar(String nomeUsuario, String senha) {
		Usuario user = Usuario.find("nomeUsuario = ?1 and senha = ?2", nomeUsuario, senha).first();
		if (user == null) {
			return null;
		} else {
			return user.nomeUsuario;
		}
	}
	


}
