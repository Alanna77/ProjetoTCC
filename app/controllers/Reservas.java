package controllers;

import java.util.List;

import models.Reserva;
import models.Sala;
import play.mvc.Controller;
import play.mvc.With;


public class Reservas extends Controller{
	
	public static void form() {
		List<Sala> s = Sala.findAll();
		render(s);
	}
	
	public static void consultarReserva(Reserva reserva) {
		Reserva reservas = Reserva.find("horario = ?1", reserva.horario).first();
		if (reservas != null && reservas.id == null) {
			flash.error("O horário está indisponível!");
			listar();
		}
		else {
			reserva.responsavel = reserva.responsavel;
			reserva.dataReserva = reserva.dataReserva;
			reserva.horario = reserva.horario;
			reserva.save();
			flash.success("Reserva realizada com sucesso!");
			listar();
				
			}
	}
	
	public static void detalhar(Long id) {
		Reserva r = Reserva.findById(id);
		render(r);
	}
	
	public static void removerReserva(Long id) {
		Reserva remocaoreserva = Reserva.findById(id);
		remocaoreserva.delete();
		listar();
	}
	
	public static void listar() {
		List<Reserva> reservas = Reserva.findAll();
		render(reservas);
		
	}
	
	public static void editarReserva(Long id) {
		Reserva r = Reserva.findById(id);
		List<Sala> s = Sala.findAll();
		renderTemplate("Salas/form.html", s, r);
	
	}

}
