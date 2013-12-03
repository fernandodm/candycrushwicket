package CandyCrushWicket.CandyCrushWicket;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.validation.validator.NumberValidator;

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
		
		final TextField<String> cantidad = new TextField<String>("cantidad");
		cantidad.setRequired(Boolean.TRUE);
		cantidad.add(NumberValidator.minimum(1));
		parent.add(cantidad);
		
		parent.add(new FeedbackPanel("feedbackPanel"));
	}
}