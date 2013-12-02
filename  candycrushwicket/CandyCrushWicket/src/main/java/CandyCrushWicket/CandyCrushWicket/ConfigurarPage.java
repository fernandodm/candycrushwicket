package CandyCrushWicket.CandyCrushWicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;

import Tp.CandyCrush.Arriba;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Partida;
import Tp.CandyCrush.Tablero;
import appModel.MundoAppModel;
import appModel.PartidaAppModel;


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
		this.agregarBotonJugar(form2);
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
						ConfigurarPage.this.mundoApp
								.setNivelSeleccionado(item.getModelObject());
						ConfigurarPage.this.mundoApp.eliminarNivelSeleccionado();
					}
				});
				
				item.add(new Button("editarNivel") {
					@Override
					public void onSubmit() {
						
						ConfigurarPage.this.mundoApp
							.setNivelEnConstruccion(item.getModelObject());

						EditarNivelPage agregar = new EditarNivelPage(ConfigurarPage.this.mundoApp,ConfigurarPage.this);
						this.setResponsePage(agregar);
					}
				});
			}

		});

	}


	private void agregarAcciones(Form<MundoAppModel> parent) {
		parent.add(new Button("agregarNivel") {
			@Override
			public void onSubmit() {
				ConfigurarPage.this.aceptarNivel(mundoApp.getNivelEnConstruccion());
				mundoApp.setNivelEnConstruccion(new Nivel());
			}

		});
	}
		
	private void agregarBotonJugar(Form<MundoAppModel> parent){
		parent.add(new Button("jugar"){
			@Override
			public void onSubmit(){
				Partida partida = new Partida(mundoApp.getMundo());
				
				partida.setNivelActual(mundoApp.getMundo().getNiveles().get(0));
				partida.setCantMovimientosFaltantes(partida.getNivelActual().getCantidadMovimientos());
				for(Nivel each : partida.getMundo().getNiveles()){
					each.getTablero().iniciar();
				}

				PartidaPage partidaPage = new PartidaPage(new PartidaAppModel(partida));
				this.setResponsePage(partidaPage);
			}
		});
	}

	public void aceptarNivel(Nivel nivel) {

		nivel.setTablero(new Tablero(mundoApp.getNivelEnConstruccion().getTablero().getAlto(),mundoApp.getNivelEnConstruccion().getTablero().getAlto(),nivel));
		mundoApp.agregarNivel(nivel);
		
		mundoApp.setNivelEnConstruccion(new Nivel());
	}
}
