package CandyCrushWicket.CandyCrushWicket;

import org.apache.wicket.markup.html.WebPage;

import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;

public class EditarObjetivoPage extends WebPage  implements EdicionCreacionObjetivoCommand{
	
	private EditarNivelPanel editarNivelPanel;
	private Objetivo objetivo;
	private EditarObjetivoPanel objetivoPanel;
	private Nivel nivel;

	public EditarNivelPanel getEditarNivelPanel() {
		return editarNivelPanel;
	}

	public void setEditarNivelPanel(EditarNivelPanel editarNivelPanel) {
		this.editarNivelPanel = editarNivelPanel;
	}

	public Objetivo getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}

	public EditarObjetivoPanel getObjetivoPanel() {
		return objetivoPanel;
	}

	public void setObjetivoPanel(EditarObjetivoPanel objetivoPanel) {
		this.objetivoPanel = objetivoPanel;
	}

	public void aceptarObjetivo(Objetivo objetivo) {
		
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

}
