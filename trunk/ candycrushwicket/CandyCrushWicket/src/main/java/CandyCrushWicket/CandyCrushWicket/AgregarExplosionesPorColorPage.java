package CandyCrushWicket.CandyCrushWicket;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import appModel.MundoAppModel;

import Tp.CandyCrush.ExplosionesPorColor;

public class AgregarExplosionesPorColorPage extends AgregarObjetivoPage{
	private static final long serialVersionUID = 1L;
	
	
	public AgregarExplosionesPorColorPage(ExplosionesPorColor obj,
			EditarNivelPanel editNivelPanel, MundoAppModel mundo) {
		
		super(obj, editNivelPanel,mundo);
		//this.add(new ExplosionesPorColorPanel("expPorColor", obj, configurarPage, mundo));
		Form<ExplosionesPorColor> form = (Form<ExplosionesPorColor>) this.get("objetivoForm");
		this.agregarCampo(form);		
		
	}
	
	public void agregarCampo(Form<ExplosionesPorColor> parent) {
		parent.add(new TextField<String>("cantidad"));
		
	}

	
}
