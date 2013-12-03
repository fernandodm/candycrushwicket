package CandyCrushWicket.CandyCrushWicket;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.validation.validator.NumberValidator;

import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.GrandesExplosiones;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;

@SuppressWarnings("all")
public class EditarGrandesExplosionesPanel extends EditarObjetivoPanel{

	public EditarGrandesExplosionesPanel(String id, Objetivo obj,
			EditarNivelPanel edNivPanel, Nivel nivel) {
		super(id, obj, edNivPanel, nivel);
		Form<GrandesExplosiones> form = (Form<GrandesExplosiones>) this.get("objetivoForm");
		this.agregarCampo(form);		
	}

	private void agregarCampo(Form<GrandesExplosiones> parent) {
	
		final DropDownChoice<Integer> cantidad = new DropDownChoice<Integer>(
				"cantidadGrandesExplosiones", crearListaCantidades(),
				createCantidadChoiceRenderer());
		cantidad.setRequired(Boolean.TRUE);
		parent.add(cantidad);
		
		parent.add(new FeedbackPanel("feedbackPanel"));
	}
	
	protected ChoiceRenderer<Integer> createCantidadChoiceRenderer() {
		return new ChoiceRenderer<Integer>() {
			@Override
			public Object getDisplayValue(Integer s) {
				return s;
			}
		};
	}

	protected LoadableDetachableModel<List<Integer>> crearListaCantidades() {
		return new LoadableDetachableModel<List<Integer>>() {
			@Override
			protected List<Integer> load() {
				List<Integer> a = new ArrayList<Integer>();
				a.add(1);
				a.add(2);
				a.add(3);
				a.add(4);
				a.add(5);
				return a;
			}
		};
	}
}
