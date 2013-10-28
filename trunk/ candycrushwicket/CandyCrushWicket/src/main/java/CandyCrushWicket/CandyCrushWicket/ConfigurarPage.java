package CandyCrushWicket.CandyCrushWicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import appModel.MundoAppModel;


public class ConfigurarPage extends WebPage{

	private InicioPage mainPage;
	private MundoAppModel mundoApp;
	
	public ConfigurarPage(MundoAppModel mundoModel, InicioPage mainPage) {
		this.mainPage = mainPage;
		this.mundoApp = mundoModel;
		this.add(new Label("nombre", "Bienvenido/a " + this.mundoApp.getNombreUsuario() + " ya podes configurar tus niveles"));
		//Form<MundoAppModel> form = new Form<MundoAppModel>("mundoAppForm", new CompoundPropertyModel<MundoAppModel>(this.mundoApp));
		//this.agregarCampos(form);
		//this.agregarAcciones(form);
		//this.add(form);
	}

	private void agregarAcciones(Form<MundoAppModel> form) {
		// TODO Auto-generated method stub
		
	}

	private void agregarCampos(Form<MundoAppModel> form) {
		
	}
	
}
