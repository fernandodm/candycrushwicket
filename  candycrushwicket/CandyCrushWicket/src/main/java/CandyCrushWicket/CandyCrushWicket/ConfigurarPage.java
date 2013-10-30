package CandyCrushWicket.CandyCrushWicket;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;

import Tp.CandyCrush.Dificultad;
import appModel.MundoAppModel;

public class ConfigurarPage extends WebPage{
	private static final long serialVersionUID = 1L;
	private InicioPage mainPage;
	private MundoAppModel mundoApp;
	
	public ConfigurarPage(MundoAppModel mundoModel, InicioPage mainPage) {
		this.mainPage = mainPage;
		this.mundoApp = mundoModel;
		this.add(new Label("nombre", "Bienvenido/a " + this.mundoApp.getNombreUsuario() + " ya podes configurar tus niveles"));
		Form<MundoAppModel> form = new Form<MundoAppModel>("mundoAppForm", new CompoundPropertyModel<MundoAppModel>(this.mundoApp));
		this.agregarCampos(form);
		//this.agregarAcciones(form);
		this.add(form);
	}

	private void agregarAcciones(Form<MundoAppModel> form) {
		// TODO Auto-generated method stub
		
	}

	private void agregarCampos(Form<MundoAppModel> parent) {
		parent.add(new TextField<String>("nivelEnConstruccion.nombre"));
		parent.add(new DropDownChoice<Dificultad>("nivelEnConstruccion.dificultad", crearListaDificultades(), createDificultadChoiceRenderer()));
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
