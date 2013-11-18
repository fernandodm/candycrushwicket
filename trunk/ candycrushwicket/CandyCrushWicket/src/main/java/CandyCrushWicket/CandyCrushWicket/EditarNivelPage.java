package CandyCrushWicket.CandyCrushWicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;


import Tp.CandyCrush.Nivel;
import appModel.MundoAppModel;

@SuppressWarnings("all")
public class EditarNivelPage extends WebPage implements EdicionCreacionNivelCommand {
	private ConfigurarPage confPage; 
	private MundoAppModel mundoApp;
	
	public EditarNivelPage(MundoAppModel mundoModel, ConfigurarPage web){
		
		this.confPage = web;
		this.mundoApp = mundoModel;
		this.add(new EditarNivelPanel("editarPanel", mundoModel, this));
		Form<MundoAppModel> form = new Form<MundoAppModel>("mundoAppForm",
				new CompoundPropertyModel<MundoAppModel>(mundoModel));
		this.agregarAcciones(form);
		this.add(form);
	
	}
	
	private void agregarAcciones(Form<MundoAppModel> parent) {
		parent.add(new Button("cancelar") {
			@Override
			public void onSubmit() {
				EditarNivelPage.this.mundoApp.setNivelEnConstruccion(new Nivel());
				EditarNivelPage.this.setResponsePage(EditarNivelPage.this.confPage);
			}

		});

	}

	protected void agregarNivel(Nivel nivel) {
		this.mundoApp.agregarNivel(nivel);
		
	}

	protected void eliminarNivel(Nivel nivelEnConstruccion) {
		this.mundoApp.eliminarNivel(nivelEnConstruccion);
	}

	public void aceptarNivel(Nivel nivel) {
		this.mundoApp.setNivelEnConstruccion(new Nivel());
		setResponsePage(confPage);
	}
	
}
