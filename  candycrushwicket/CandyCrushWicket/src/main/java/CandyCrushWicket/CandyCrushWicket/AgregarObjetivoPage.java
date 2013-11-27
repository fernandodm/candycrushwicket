package CandyCrushWicket.CandyCrushWicket;

import org.apache.wicket.markup.html.WebPage;

import Tp.CandyCrush.ExplosionesPorColor;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;

public class AgregarObjetivoPage extends WebPage implements EdicionCreacionObjetivoCommand{
	
	private EditarNivelPanel editarNivelPanel;
	private Objetivo objetivo;
	private EditarObjetivoPanel objetivoPanel;
	private Nivel nivel;

	public AgregarObjetivoPage(){
		
	}
	
	public AgregarObjetivoPage(Objetivo obj,
			EditarNivelPanel editarNivelPanel, EditarObjetivoPanel objetivoPanel) {
		
		setObjetivo(obj);
		setEditarNivelPanel(editarNivelPanel);
		setObjetivoPanel(objetivoPanel);
		setNivel(objetivoPanel.getNivel());
		this.add(getObjetivoPanel());
		
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
	
		this.nivel.agregarObjetivo(objetivo);
		
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	
	/*public void validarObjetivo(Objetivo unObjetivo) {
	if (! unObjetivo.puedeAgregarObjetivo()) {
		throw new UserException("La cantidad debe ser mayor a 0");
	}
}*/


}
