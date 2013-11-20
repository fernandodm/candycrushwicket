package CandyCrushWicket.CandyCrushWicket;


import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;

import Tp.CandyCrush.Nivel;
import appModel.MundoAppModel;
//
@SuppressWarnings("all")
public class ConfigurarPage extends WebPage implements EdicionCreacionNivelCommand {
	private static final long serialVersionUID = 1L;
	private EditarNivelPanel editarPanel;
	private MundoAppModel mundoApp;

	public ConfigurarPage(MundoAppModel mundoModel) {
		this.mundoApp = mundoModel;
		
		Form<MundoAppModel> form2 = new Form<MundoAppModel>("mundoForm2",
				new CompoundPropertyModel<MundoAppModel>(mundoModel));
		this.agregarGrillaNiveles(form2);
		this.agregarGrillaBuscador(form2);
		this.agregarCampoDelBucador(form2);
		this.add(form2);
		
		this.editarPanel = new EditarNivelPanel("panel", mundoModel, this);
		this.add(this.editarPanel);
		
	}
	
	private void agregarCampoDelBucador(Form<MundoAppModel> parent) {
		
		parent.add(new TextField<String>("filtro"));
		
		parent.add(new Button("filtrar") {
			@Override
			public void onSubmit() {
				ConfigurarPage.this.filtrar();
			}
		});
		
	}

	private void agregarGrillaBuscador(Form<MundoAppModel> parent) {
		parent.add(new PropertyListView<Nivel>("niveles") {
			@Override
			protected void populateItem(final ListItem<Nivel> item) {
				item.add(new Label("nombre"));
				
			}

		});

	}

	protected void filtrar() {
		mundoApp.buscarPorNombre(mundoApp.getFiltro());
		
	}

	private void agregarGrillaNiveles(Form<MundoAppModel> parent) {
		parent.add(new PropertyListView<Nivel>("mundo.niveles") {
			@Override
			protected void populateItem(final ListItem<Nivel> item) {
				item.add(new Label("nombre"));

				item.add(new Button("eliminarNivel") {
					@Override
					public void onSubmit() {
						ConfigurarPage.this.mundoApp.setNivelSeleccionado(item.getModelObject());
						ConfigurarPage.this.mundoApp.eliminarNivelSeleccionado();
					}
				});
				
				item.add(new Button("editarNivel") {
					@Override
					public void onSubmit() {
						ConfigurarPage.this.mundoApp.setNivelEnConstruccion(item.getModelObject());
						EditarNivelPage agregar = new EditarNivelPage(ConfigurarPage.this.mundoApp,ConfigurarPage.this);
						this.setResponsePage(agregar);
					}
				});
			}

		});

	} 
	
	public void aceptarNivel(Nivel nivel) { 
		mundoApp.agregarNivel(nivel);
		mundoApp.setNivelEnConstruccion(new Nivel());
	}
}
