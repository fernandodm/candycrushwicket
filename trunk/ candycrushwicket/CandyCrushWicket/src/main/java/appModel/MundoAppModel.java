package appModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;


import net.sf.oval.constraint.ValidateWithMethod;
import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.Mundo;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;
import Tp.CandyCrush.Tablero;

@Observable
public class MundoAppModel implements Serializable {
	
	private String nombreUsuario;
	
	private Mundo mundo = new Mundo();
	private Nivel nivelEnConstruccion = new Nivel();
	private List<Nivel> niveles;
	private Objetivo objetivo;
	
	public List<Nivel> getNiveles() {
		return niveles;
	}
	public void setNiveles(List<Nivel> niveles) {
		this.niveles = niveles;
	}
	public Nivel getNivelEnConstruccion() {
		return nivelEnConstruccion;
	}
	public void setNivelEnConstruccion(Nivel nivelEnConstruccion) {
		this.nivelEnConstruccion = nivelEnConstruccion;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public Mundo getMundo() {
		return mundo;
	}
	public void setMundo(Mundo mundo) {
		this.mundo = mundo;
	}
	public void eliminarObjetivo(Objetivo objetivo) {
		nivelEnConstruccion.eliminarObjetivo(objetivo);
	}
	public List<Objetivo> objetivosDelNivel() {
		
		return nivelEnConstruccion.getObjetivos();
	}
	public Objetivo getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}
	
	public void agregarNivel(Nivel niv){
		this.mundo.getNiveles().add(niv);
	}
	


	public List<Nivel> buscarPorNombre(String nombre){
		this.setNiveles(new ArrayList<Nivel>());
		
			for(Nivel each: this.mundo.getNiveles()){
				System.out.println(each.getNombre().contains(nombre));
				if(each.getNombre().contains(nombre))
					this.getNiveles().add(each);
		
		}
		
		return this.getNiveles();
	}

	
}
