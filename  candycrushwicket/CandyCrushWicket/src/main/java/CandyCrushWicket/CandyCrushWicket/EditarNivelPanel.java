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
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;

import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.ExplosionesPorColor;
import Tp.CandyCrush.GrandesExplosiones;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;
import appModel.MundoAppModel;

@SuppressWarnings("all")
public class EditarNivelPanel extends Panel {
	private static final long serialVersionUID = 1L;
	private MundoAppModel mundoApp;
	private EdicionCreacionNivelCommand command;
	private EditarObjetivoPanel objetivoPanel;

	
	public EditarNivelPanel(String id, MundoAppModel mundoModel, EdicionCreacionNivelCommand command) {
		super(id);
		this.mundoApp = mundoModel;
		this.command = command;
				
		this.add(new Label("nombre", "Bienvenido/a "
				+ this.mundoApp.getNombreUsuario()
				+ " ya podes configurar tus niveles"));
		Form<MundoAppModel> form = new Form<MundoAppModel>("mundoAppForm",
				new CompoundPropertyModel<MundoAppModel>(this.mundoApp));
		this.agregarCampos(form);
		this.agregarGrillaObjetivos(form);
		this.agregarAccionesObjetivo(form);
		this.agregarAcciones(form);
		this.add(form);
	}

	private void agregarAcciones(Form<MundoAppModel> form) {
		form.add(new Button("aceptar") {
			@Override
			public void onSubmit() {
				command.aceptarNivel(getNivelEnConstruccion());
			}
		});
	}
	
	protected Nivel getNivelEnConstruccion() {

		return mundoApp.getNivelEnConstruccion();
	}

	private void agregarGrillaObjetivos(Form<MundoAppModel> parent) {

		parent.add(new PropertyListView<Objetivo>("nivelEnConstruccion.objetivos") {
			@Override
			protected void populateItem(final ListItem<Objetivo> item) {
				item.add(new Label("descripcion"));

				item.add(new Button("eliminarObjetivo") {
					@Override
					public void onSubmit() {
						EditarNivelPanel.this.mundoApp.setObjetivoSeleccionado(item.getModelObject());
						EditarNivelPanel.this.mundoApp.eliminarObjetivoSeleccionado();
					}
				});
				
				item.add(new Button("editarObjetivo") {
					@Override
					public void onSubmit() {
						EditarNivelPanel.this.mundoApp.setObjetivoSeleccionado(item.getModelObject());
	
						if(EditarNivelPanel.this.mundoApp.getObjetivoSeleccionado().esGrandesExplosiones()){
							
							GrandesExplosiones obj = (GrandesExplosiones) EditarNivelPanel.this.mundoApp.getObjetivoSeleccionado(); 
							Nivel niv = EditarNivelPanel.this.mundoApp.getNivelEnConstruccion();
							
							EditarNivelPanel.this.objetivoPanel = new EditarGrandesExplosionesPanel("objetivoPanel", obj, EditarNivelPanel.this, niv);
							EditarNivelPanel.this.editarObjetivo(obj);
							
						}else{
			
							ExplosionesPorColor obj = (ExplosionesPorColor) EditarNivelPanel.this.mundoApp.getObjetivoSeleccionado(); 
							Nivel niv = EditarNivelPanel.this.mundoApp.getNivelEnConstruccion();
							
							EditarNivelPanel.this.objetivoPanel = new EditarExplosionesPorColorPanel("objetivoPanel", obj, EditarNivelPanel.this, niv);
							EditarNivelPanel.this.editarObjetivo(obj);
							
						}
						
					}
				});
			}

		});

	}

	private void agregarAccionesObjetivo(Form<MundoAppModel> parent) {
		parent.add(new Button("agregarExplosionesPorColor") {
			@Override
			public void onSubmit() {
				ExplosionesPorColor obj = new ExplosionesPorColor();
				EditarNivelPanel.this.objetivoPanel = new EditarExplosionesPorColorPanel("objetivoPanel", obj, EditarNivelPanel.this, EditarNivelPanel.this.mundoApp.getNivelEnConstruccion());
				EditarNivelPanel.this.agregarObjetivo(obj);
			}

		});

		parent.add(new Button("agregarGrandesExplosiones") {
			@Override
			public void onSubmit() {
				GrandesExplosiones obj = new GrandesExplosiones();
				EditarNivelPanel.this.objetivoPanel = new EditarGrandesExplosionesPanel("objetivoPanel", obj , EditarNivelPanel.this, EditarNivelPanel.this.mundoApp.getNivelEnConstruccion());
				EditarNivelPanel.this.agregarObjetivo(obj);
			}

		});

	}

	
	protected void agregarObjetivo(Objetivo obj) {

		AgregarObjetivoPage agregar = new AgregarObjetivoPage(obj, this, objetivoPanel);
		objetivoPanel.setCommand(agregar);
		this.setResponsePage(agregar);
	}
	
	protected void editarObjetivo(Objetivo obj) {

		EditarObjetivoPage agregar = new EditarObjetivoPage(obj, this, objetivoPanel);
		objetivoPanel.setCommand(agregar);
		this.setResponsePage(agregar);
	}


	protected void editarExplosionesPorColor(ExplosionesPorColor obj) {
		EditarObjetivoPage agregar = new EditarObjetivoPage(obj, this, objetivoPanel);
		objetivoPanel.setCommand(agregar);
		this.setResponsePage(agregar);
	}


	private void agregarCampos(Form<MundoAppModel> parent) {
		
		final DropDownChoice<Dificultad> dificultad = new DropDownChoice<Dificultad>(
				"nivelEnConstruccion.dificultad", crearListaDificultades(),
				createDificultadChoiceRenderer());
		dificultad.setRequired(Boolean.TRUE);
		parent.add(dificultad);
		
		final TextField<String> nameTextField = new TextField<String>("nivelEnConstruccion.nombre");
		nameTextField.setRequired(Boolean.TRUE);
		parent.add(nameTextField);
		
		final TextField<String> heightTextField = new TextField<String>("nivelEnConstruccion.tablero.alto");
		heightTextField.setRequired(Boolean.TRUE);
		//heightTextField.add(new PropertyValidator());
		parent.add(heightTextField);
		
		final TextField<String> weightTextField = new TextField<String>("nivelEnConstruccion.tablero.ancho");
		weightTextField.setRequired(Boolean.TRUE);
		//weightTextField.add(new PropertyValidator());
		parent.add(weightTextField);
			
		final TextField<String> movesTextField = new TextField<String>("nivelEnConstruccion.cantidadMovimientos");
		movesTextField.setRequired(Boolean.TRUE);
		//movesTextField.add(new PropertyValidator());
		parent.add(movesTextField);
		
		final TextField<String> scoreTextField = new TextField<String>("nivelEnConstruccion.puntajeMinimo");
		scoreTextField.setRequired(Boolean.TRUE);
		//scoreTextField.add(new PropertyValidator());
		parent.add(scoreTextField);
		
		parent.add(new FeedbackPanel("feedbackPanel"));
	
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
}

