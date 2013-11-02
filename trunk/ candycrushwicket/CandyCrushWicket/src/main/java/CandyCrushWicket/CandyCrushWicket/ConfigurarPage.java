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
import Tp.CandyCrush.GrandesExplosiones;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;
import appModel.MundoAppModel;

public class ConfigurarPage extends WebPage {
	private static final long serialVersionUID = 1L;
	private EditarNivelPanel editarPanel;
	private MundoAppModel mundoApp;

	public ConfigurarPage(MundoAppModel mundoModel) {
		this.mundoApp = mundoModel;
		this.editarPanel = new EditarNivelPanel("panel", mundoModel, this);
		this.add(this.editarPanel);
		Form<MundoAppModel> form = new Form<MundoAppModel>("mundoForm",
				new CompoundPropertyModel<MundoAppModel>(mundoModel));
		Form<MundoAppModel> form2 = new Form<MundoAppModel>("mundoForm2",
				new CompoundPropertyModel<MundoAppModel>(mundoModel));
		this.agregarAcciones(form);
		this.agregarGrillaNiveles(form2);
		this.add(form2);
		this.add(form);
		
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
				ConfigurarPage.this.agregarNivel(mundoApp.getNivelEnConstruccion());
				mundoApp.setNivelEnConstruccion(new Nivel());
			}

		});

		
	}

	protected void agregarNivel(Nivel niv) {
		
		mundoApp.agregarNivel(niv);
		
	}

}
