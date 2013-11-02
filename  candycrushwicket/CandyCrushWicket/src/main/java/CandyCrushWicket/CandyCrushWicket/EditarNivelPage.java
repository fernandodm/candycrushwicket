package CandyCrushWicket.CandyCrushWicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;

import Tp.CandyCrush.ExplosionesPorColor;
import Tp.CandyCrush.GrandesExplosiones;
import Tp.CandyCrush.Nivel;
import appModel.MundoAppModel;

public class EditarNivelPage extends WebPage {
	private ConfigurarPage confPage; 
	private MundoAppModel mundoApp;
	
	public EditarNivelPage(MundoAppModel mundoModel, ConfigurarPage web){
		
		this.confPage = web;
		this.mundoApp = mundoModel;
		this.add(new EditarNivelPanel("editarPanel", mundoModel, web));
		Form<MundoAppModel> form = new Form<MundoAppModel>("mundoAppForm",
				new CompoundPropertyModel<MundoAppModel>(mundoModel));
		this.agregarAcciones(form);
		this.add(form);
				
	}
	
	private void agregarAcciones(Form<MundoAppModel> parent) {
		parent.add(new Button("realizarCambios") {
			@Override
			public void onSubmit() {
					
				EditarNivelPage.this.eliminarNivel(EditarNivelPage.this.mundoApp.getNivelEnConstruccion());
				EditarNivelPage.this.agregarNivel(EditarNivelPage.this.mundoApp.getNivelEnConstruccion());
				EditarNivelPage.this.mundoApp.setNivelEnConstruccion(new Nivel());
				EditarNivelPage.this.setResponsePage(EditarNivelPage.this.confPage);
			}

		});

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
	
}
