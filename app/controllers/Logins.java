package controllers;

import models.Usuario;
import play.mvc.Controller;
import play.mvc.results.RenderTemplate;

public class Logins extends Controller{
	
	public static void form() {
		render();                              
	}

	public static void logar(String nomeUsuario, String senha) {
		Usuario user = Usuario.find("nomeUsuario = ?1 and senha = ?2", nomeUsuario, senha).first();
		if (user == null) {
			flash.error("Usuário inválido!");
			form();
		} else {
			session.put("usuarioLogado", user.nomeUsuario);
			if (user.admin == true) {
				session.put("administrador", true);				
			}
			renderTemplate("Logins/painel.html");
		}
	}
	public static void formPainel() {
		renderTemplate("Logins/painel.html");
	}
	
	public static void sair() {
		session.clear();
		flash.success("Você saiu do sistema");
		form();
	}

}
