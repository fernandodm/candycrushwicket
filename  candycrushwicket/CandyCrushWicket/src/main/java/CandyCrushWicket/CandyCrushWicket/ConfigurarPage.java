package CandyCrushWicket.CandyCrushWicket;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.ExplosionesPorColor;
import Tp.CandyCrush.Objetivo;
import appModel.MundoAppModel;

public class ConfigurarPage extends WebPage{
	private static final long serialVersionUID = 1L;
	private InicioPage mainPage;
	private MundoAppModel mundoApp;
	private Objetivo objetivoSeleccionado;
	
	public ConfigurarPage(MundoAppModel mundoModel, InicioPage mainPage) {
		this.mainPage = mainPage;
		this.mundoApp = mundoModel;
		this.add(new Label("nombre", "Bienvenido/a " + this.mundoApp.getNombreUsuario() + " ya podes configurar tus niveles"));
		Form<MundoAppModel> form = new Form<MundoAppModel>("mundoAppForm", new CompoundPropertyModel<MundoAppModel>(this.mundoApp));
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
						
			}

		});
	}

	private void agregarAccionesObjetivo(Form<MundoAppModel> parent) {
		parent.add(new Button("agregarExplosionesPorColor") {
			@Override
			public void onSubmit() {
				ConfigurarPage.this.agregarExplosionesPorColor(new ExplosionesPorColor());
			}

		});
		
	}

	protected void agregarExplosionesPorColor(ExplosionesPorColor obj) {
		
		AgregarExplosionesPorColorPage agregar = new AgregarExplosionesPorColorPage(obj, this);
		this.setResponsePage(agregar);
	}

	private void agregarCampos(Form<MundoAppModel> parent) {
		parent.add(new TextField<String>("nivelEnConstruccion.nombre"));
		parent.add(new DropDownChoice<Dificultad>("nivelEnConstruccion.dificultad", crearListaDificultades(), createDificultadChoiceRenderer()));
		parent.add(new TextField<String>("nivelEnConstruccion.tablero.alto"));
		parent.add(new TextField<String>("nivelEnConstruccion.tablero.ancho"));
		parent.add(new TextField<String>("nivelEnConstruccion.cantidadMovimientos"));
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

	public Objetivo getObjetivoSeleccionado() {
		return objetivoSeleccionado;
	}

	public void setObjetivoSeleccionado(Objetivo objetivoSeleccionado) {
		this.objetivoSeleccionado = objetivoSeleccionado;
	}
	
}
