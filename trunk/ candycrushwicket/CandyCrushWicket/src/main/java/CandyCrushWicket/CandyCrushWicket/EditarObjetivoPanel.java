package CandyCrushWicket.CandyCrushWicket;

import java.util.List;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;

import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;

@SuppressWarnings("all")
public class EditarObjetivoPanel extends Panel{
	
	private EditarNivelPanel editarNivelPanel;
	private Objetivo objetivo;
	private EdicionCreacionObjetivoCommand command;
	private Nivel nivel;

	public EditarObjetivoPanel(String id, Objetivo obj, EditarNivelPanel edNivPanel, Nivel niv) {
		super(id);
		
		this.objetivo = obj;
		this.editarNivelPanel = edNivPanel;
		this.nivel = niv;
		Form<Objetivo> form = new Form<Objetivo>("objetivoForm", new CompoundPropertyModel<Objetivo>(this.objetivo));
		Form<Objetivo> formCancelar = new Form<Objetivo>("objetivoCancelar", new CompoundPropertyModel<Objetivo>(this.objetivo));
		this.agregarAcciones(form);
		this.agregarCampos(form);
		this.agregarCancelar(formCancelar);
		this.add(form);
		this.add(formCancelar);
		
	}

	private void agregarCancelar(Form<Objetivo> parent) {
		parent.add(new Button("cancelar") {
			@Override
			public void onSubmit() {
							
				EditarObjetivoPanel.this.volver();
				
			}
		});
		
	}

	private void agregarCampos(Form<Objetivo> parent) {
		parent.add(new DropDownChoice<String>("color", crearListaColores(), createColorChoiceRenderer() ));
		
	}

	private void agregarAcciones(Form<Objetivo> parent) {
		parent.add(new Button("agregar") {
			@Override
			public void onSubmit() {
				command.aceptarObjetivo(objetivo);
				EditarObjetivoPanel.this.volver();
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
				return EditarObjetivoPanel.this.nivel.getDificultad().getColores();
			}
		};
	}

	protected void volver() {
		this.setResponsePage(editarNivelPanel.getPage());
	}

	protected void agregarObjetivo() {
		this.nivel.agregarObjetivo(this.objetivo);
	}

	public EdicionCreacionObjetivoCommand getCommand() {
		return command;
	}

	public void setCommand(EdicionCreacionObjetivoCommand command) {
		this.command = command;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	
	public EditarNivelPanel getEditarNivelPanel() {
		return editarNivelPanel;
	}

	public void setEditarNivelPanel(EditarNivelPanel editNivPanel) {
		this.editarNivelPanel = editNivPanel;
	}

	public Objetivo getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}
}
