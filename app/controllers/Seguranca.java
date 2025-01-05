package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {
	
	@Before
	static void verificarAutenticacao() {
		if (!session.contains("usuarioLogado")) {
			flash.error("Por favor realize login");
			Logins.form();
		}
	}

}
