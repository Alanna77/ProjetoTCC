package controllers;

import java.util.List;

import models.Reserva;
import models.Sala;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Salas extends Controller{
	
	public static void form() {
		List<Reserva> reservas = Reserva.findAll();
		render(reservas);
	}
	
	public static void listar() {
		List<Sala> salas = Sala.findAll();
		render(salas);
		
	}
	
	public static void cadastrar(Sala sala) {
		Sala salaCadastrada = Sala.find("numero = ?1", sala.numero).first();
		if (salaCadastrada != null && sala.id == null) {
			flash.error("Já existe uma sala com esse número!");
			listar();
		}
		else {
			sala.numero = sala.numero;
			sala.capacidade = sala.capacidade;
			sala.equipamento = sala.equipamento;
			sala.save();
			flash.success("Sala cadastrada com sucesso!");
			listar();
				
			}
	}
	public static void detalhar(Long id) {
		Sala sala = Sala.findById(id);
		render(sala);
		
	}
	
	public static void deletarSala(Long id) {
		if (session.contains("administrador")) {
			Sala sala = Sala.findById(id);
			sala.delete();
		} else {
			flash.error("Somente os administradores podem realizar essa ação!");
		}
		listar();
	}

	
	public static void editarSala(Long id) {
		Sala s = Sala.findById(id);
		List<Reserva> reservas = Reserva.findAll();
		renderTemplate("Salas/form.html", s, reservas);
		
	}

}
