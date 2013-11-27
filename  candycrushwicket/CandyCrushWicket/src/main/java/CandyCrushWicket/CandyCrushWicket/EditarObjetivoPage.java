package CandyCrushWicket.CandyCrushWicket;

import org.apache.wicket.markup.html.WebPage;

import Tp.CandyCrush.GrandesExplosiones;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;

public class EditarObjetivoPage extends WebPage  implements EdicionCreacionObjetivoCommand{
	
	private EditarNivelPanel editarNivelPanel;
	private Objetivo objetivo;
	private EditarObjetivoPanel objetivoPanel;
	private Nivel nivel;



	public EditarObjetivoPage(Objetivo obj,
			EditarNivelPanel editarNivelPanel2,
			EditarObjetivoPanel objetivoPanel2) {
		setObjetivo(obj);
		setEditarNivelPanel(editarNivelPanel2);
		setObjetivoPanel(objetivoPanel2);
		setNivel(objetivoPanel2.getNivel());
		add(objetivoPanel2);
	}

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
