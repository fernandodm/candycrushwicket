package CandyCrushWicket.CandyCrushWicket;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

import Tp.CandyCrush.ExplosionesPorColor;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;

@SuppressWarnings("all")
public class EditarExplosionesPorColorPanel extends EditarObjetivoPanel{

	public EditarExplosionesPorColorPanel(String id, Objetivo obj,
			EditarNivelPanel edNivPanel, Nivel nivel) {
		super(id, obj, edNivPanel, nivel);
		Form<ExplosionesPorColor> form = (Form<ExplosionesPorColor>) this.get("objetivoForm");
		this.agregarCampo(form);		
		
	}
	
	public void agregarCampo(Form<ExplosionesPorColor> parent) {
		parent.add(new TextField<String>("cantidad"));

	}
}