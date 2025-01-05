package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.jpa.Model;

@Entity
public class Reserva extends Model{
	
	public String responsavel;
	
	@Temporal(TemporalType.DATE)
	public Date dataReserva;
	
	public String horario;
	
	@ManyToOne
	public Sala salas;

}

