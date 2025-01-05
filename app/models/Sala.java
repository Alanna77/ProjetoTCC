package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Sala extends Model{
	
	public int numero;
	public int capacidade;
	public String equipamento;
	
	@OneToMany
	public List<Reserva> reservas;

}
