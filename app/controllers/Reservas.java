package controllers;

import java.util.List;

import models.Reserva;
import models.Sala;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Reservas extends Controller {

	public static void form(Long id) {
		Reserva r = id != null ? Reserva.findById(id) : new Reserva();
		List<Sala> salas = Sala.findAll();
		render(r, salas);
	}

	public static void consultarReserva(Reserva reserva) {

		if (reserva.sala == null || reserva.sala.id == null) {
			flash.error("Selecione uma sala válida!");
			form(null);
			return;
		}

		Sala sala = Sala.findById(reserva.sala.id);
		if (sala == null) {
			flash.error("Sala não encontrada!");
			form(null);
			return;
		}

		Reserva reservaExistente = Reserva.find("dataReserva = ?1 AND horario = ?2 AND sala.id = ?3",
				reserva.dataReserva, reserva.horario, sala.id).first();
		if (reservaExistente != null) {
			flash.error("Este horário já está reservado para a sala informada!");
			form(null);
			return;
		} else {
			reserva.sala = sala;
			reserva.save();
			flash.success("Reserva realizada com sucesso!");
			listar();

		}
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
		List<Reserva> reserva = Reserva.findAll();
		List<Sala> salas = Sala.findAll();
		renderTemplate("Reservas/form.html", r, salas);

	}

}
