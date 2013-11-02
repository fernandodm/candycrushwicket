package CandyCrushWicket.CandyCrushWicket;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;

import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.ExplosionesPorColor;
import Tp.CandyCrush.GrandesExplosiones;
import Tp.CandyCrush.Objetivo;
import appModel.MundoAppModel;

public class EditarNivelPanel extends Panel {
	private static final long serialVersionUID = 1L;
	private MundoAppModel mundoApp;
	private ConfigurarPage configurarPage;
	
	public EditarNivelPanel(String id, MundoAppModel mundoModel, ConfigurarPage web) {
		super(id);
		this.setConfigurarPage(web);
		this.mundoApp = mundoModel;
		this.add(new Label("nombre", "Bienvenido/a "
				+ this.mundoApp.getNombreUsuario()
				+ " ya podes configurar tus niveles"));
		Form<MundoAppModel> form = new Form<MundoAppModel>("mundoAppForm",
				new CompoundPropertyModel<MundoAppModel>(this.mundoApp));
		this.agregarCampos(form);
		this.agregarGrillaObjetivos(form);
		this.agregarAccionesObjetivo(form);
		this.add(form);
	}

	private void agregarGrillaObjetivos(Form<MundoAppModel> parent) {

		parent.add(new PropertyListView<Objetivo>("nivelEnConstruccion.objetivos") {
			@Override
			protected void populateItem(final ListItem<Objetivo> item) {
				item.add(new Label("descripcion"));

				item.add(new Button("eliminarObjetivo") {
					@Override
					public void onSubmit() {
						EditarNivelPanel.this.mundoApp
								.setObjetivoSeleccionado(item.getModelObject());
						EditarNivelPanel.this.mundoApp.eliminarObjetivoSeleccionado();
					}
				});
				
				item.add(new Button("editarObjetivo") {
					@Override
					public void onSubmit() {
						EditarNivelPanel.this.mundoApp.setObjetivoSeleccionado(item.getModelObject());
						
						if(EditarNivelPanel.this.mundoApp.getObjetivoSeleccionado().esGrandesExplosiones()){
							EditarNivelPanel.this.eliminarObjetivoSeleccionado();
							EditarNivelPanel.this
							.agregarGrandesExplosiones((GrandesExplosiones) EditarNivelPanel.this.mundoApp.getObjetivoSeleccionado());
							
						}else{
							EditarNivelPanel.this.eliminarObjetivoSeleccionado();
							EditarNivelPanel.this
							.agregarExplosionesPorColor((ExplosionesPorColor) EditarNivelPanel.this.mundoApp.getObjetivoSeleccionado());
							
						}
						
					}
				});
			}

		});

	}

	protected void eliminarObjetivoSeleccionado() {
		mundoApp.getNivelEnConstruccion().eliminarObjetivo(mundoApp.getObjetivoSeleccionado());
		
	}

	private void agregarAccionesObjetivo(Form<MundoAppModel> parent) {
		parent.add(new Button("agregarExplosionesPorColor") {
			@Override
			public void onSubmit() {
				EditarNivelPanel.this
						.agregarExplosionesPorColor(new ExplosionesPorColor());
			}

		});

		parent.add(new Button("agregarGrandesExplosiones") {
			@Override
			public void onSubmit() {
				EditarNivelPanel.this
						.agregarGrandesExplosiones(new GrandesExplosiones());
			}

		});

	}

	protected void agregarGrandesExplosiones(GrandesExplosiones obj) {

		AgregarGrandesExplosionesPage agregar = new AgregarGrandesExplosionesPage(obj, this, this.mundoApp);
		this.setResponsePage(agregar);
	}

	protected void agregarExplosionesPorColor(ExplosionesPorColor obj) {

		AgregarExplosionesPorColorPage agregar = new AgregarExplosionesPorColorPage(obj, this, this.mundoApp);
		this.setResponsePage(agregar);
	}

	private void agregarCampos(Form<MundoAppModel> parent) {
		parent.add(new TextField<String>("nivelEnConstruccion.nombre"));
		parent.add(new DropDownChoice<Dificultad>(
				"nivelEnConstruccion.dificultad", crearListaDificultades(),
				createDificultadChoiceRenderer()));
		parent.add(new TextField<String>("nivelEnConstruccion.tablero.alto"));
		parent.add(new TextField<String>("nivelEnConstruccion.tablero.ancho"));
		parent.add(new TextField<String>(
				"nivelEnConstruccion.cantidadMovimientos"));
		parent.add(new TextField<String>("nivelEnConstruccion.puntajeMinimo"));
	}

	protected ChoiceRenderer<Dificultad> createDificultadChoiceRenderer() {
		return new ChoiceRenderer<Dificultad>() {
			@Override
			public Object getDisplayValue(Dificultad object) {
				return object.getNombre();
			}
		};
	}

	protected LoadableDetachableModel<List<Dificultad>> crearListaDificultades() {
		return new LoadableDetachableModel<List<Dificultad>>() {
			@Override
			protected List<Dificultad> load() {
				return Dificultad.getDificultades();
			}
		};
	}

	public ConfigurarPage getConfigurarPage() {
		return configurarPage;
	}

	public void setConfigurarPage(ConfigurarPage configurarPage) {
		this.configurarPage = configurarPage;
	}
	

}
