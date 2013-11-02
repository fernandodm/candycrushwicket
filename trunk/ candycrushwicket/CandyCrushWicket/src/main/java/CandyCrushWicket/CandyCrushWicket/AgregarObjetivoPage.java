package CandyCrushWicket.CandyCrushWicket;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;

import Tp.CandyCrush.Objetivo;
import appModel.MundoAppModel;

public class AgregarObjetivoPage extends WebPage{
	private static final long serialVersionUID = 1L;
	private MundoAppModel mundoApp;
	private EditarNivelPanel confPage;
	private Objetivo objetivo;

	public MundoAppModel getMundoApp() {
		return mundoApp;
	}

	public void setMundoApp(MundoAppModel mundoApp) {
		this.mundoApp = mundoApp;
	}

	public EditarNivelPanel getConfPage() {
		return confPage;
	}

	public void setConfPage(EditarNivelPanel confPage) {
		this.confPage = confPage;
	}

	public Objetivo getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}

	
	public AgregarObjetivoPage(Objetivo obj, EditarNivelPanel configurarPage, MundoAppModel mundo) {
//		this.add(new ObjetivoPanel("objetivo", obj, configurarPage, mundo));
		this.objetivo = obj;
		this.confPage = configurarPage;
		this.mundoApp = mundo;
		Form<Objetivo> form = new Form<Objetivo>("objetivoForm", new CompoundPropertyModel<Objetivo>(this.objetivo));
		this.agregarAcciones(form);
		this.agregarCampos(form);
		this.add(form);
	}

	private void agregarCampos(Form<Objetivo> parent) {
		parent.add(new DropDownChoice<String>("color", crearListaColores(), createColorChoiceRenderer() ));
		
	}

	private void agregarAcciones(Form<Objetivo> parent) {
		parent.add(new Button("agregar") {
			@Override
			public void onSubmit() {
				AgregarObjetivoPage.this.agregarObjetivo();
				AgregarObjetivoPage.this.volver();
			}

		});
		
		parent.add(new Button("cancelar") {
			@Override
			public void onSubmit() {
				
				AgregarObjetivoPage.this.volver();
			}

		});
		
	}
	
	protected ChoiceRenderer<String> createColorChoiceRenderer() {
		return new ChoiceRenderer<String>() {
			@Override
			public Object getDisplayValue(String s) {
				return s;
			}
		};
	}

	protected LoadableDetachableModel<List<String>> crearListaColores() {
		return new LoadableDetachableModel<List<String>>() {
			@Override
			protected List<String> load() {
				return AgregarObjetivoPage.this.mundoApp.getNivelEnConstruccion().getDificultad().getColores();
			}
		};
	}

	protected void volver() {
		this.setResponsePage(confPage.getConfigurarPage());
		
	}

	protected void agregarObjetivo() {
		
		this.mundoApp.getNivelEnConstruccion().agregarObjetivo(this.objetivo);
		
	}


	
}
