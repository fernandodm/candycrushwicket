package appModel;

import org.uqbar.commons.model.UserException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;





import net.sf.oval.constraint.ValidateWithMethod;
import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.Mundo;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;


@Observable
@SuppressWarnings("all")
public class MundoAppModel implements Serializable {
	
	private String nombreUsuario;
	private Mundo mundo = new Mundo();
	private Nivel nivelEnConstruccion = new Nivel();
	private List<Nivel> niveles;
	private Objetivo objetivoSeleccionado;
	private Nivel nivelSeleccionado;
	private String filtro;
	
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
			
	public void validarNombreUsuario(String unNombre) {
		if (unNombre.contains(" ")) {
			throw new UserException("Ingrese un nombre de usuario válido sin dejar espacios");
		}
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.validarNombreUsuario(nombreUsuario);
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
	
	public void agregarNivel(Nivel niv){
		this.mundo.getNiveles().add(niv);
	}
	
	public Objetivo getObjetivoSeleccionado() {
		return objetivoSeleccionado;
	}
	public void setObjetivoSeleccionado(Objetivo objetivoSeleccionado) {
		this.objetivoSeleccionado = objetivoSeleccionado;
	}
	public void eliminarObjetivoSeleccionado() {
		
		nivelEnConstruccion.eliminarObjetivo(objetivoSeleccionado);
		
	}
	public Nivel getNivelSeleccionado() {
		return nivelSeleccionado;
	}
	public void setNivelSeleccionado(Nivel nivelSeleccionado) {
		this.nivelSeleccionado = nivelSeleccionado;
	}
	public void eliminarNivelSeleccionado() {
		mundo.eliminarNivel(nivelSeleccionado);
		
	}
	public void eliminarNivel(Nivel nivel) {
	
		mundo.eliminarNivel(nivel);
		
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
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
	

	public void agregarObjetivoAlNivel(Objetivo obj) {
		
		getNivelEnConstruccion().agregarObjetivo(obj);
		
	}
}
