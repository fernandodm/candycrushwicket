package CandyCrushWicket.CandyCrushWicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;

import Tp.CandyCrush.Objetivo;
import Tp.CandyCrush.Partida;
import appModel.PartidaAppModel;

public class PerdistePage extends WebPage {
	
	
	private static final long serialVersionUID = 1L;
	private PartidaAppModel partidaApp;
	private Integer movs;
	
	public PartidaAppModel getPartidaApp() {
		return partidaApp;
	}

	public void setPartidaApp(PartidaAppModel partida) {
		this.partidaApp = partida;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public PerdistePage(PartidaAppModel partAM){
		this.setPartidaApp(partAM);

		
		Form<PartidaAppModel> form = new Form<PartidaAppModel>("partidaAppForm", new CompoundPropertyModel<PartidaAppModel>(partAM));
		form.setOutputMarkupId(true);
		form.add(new Label("partida.puntaje"));
		String nombre = partAM.getPartida().getNivelActual().getNombre();
		form.add(new Label("resultado","Nivel " + nombre + " no superado..."));
		
		this.agregarGrillaNiveles(form);
		this.agregarBotonIntentarDeNuevo(form);
		this.add(form);
	}
	
	public void agregarGrillaNiveles(Form<PartidaAppModel> parent){
		parent.add(new PropertyListView<Objetivo>("partida.mundo.niveles") {
		@Override
		protected void populateItem(final ListItem<Objetivo> item) {
			item.add(new Label("nombre"));
			item.add(new Label("descripcionEstado"));
						
			}

		});
	}
	
	public void agregarBotonIntentarDeNuevo(Form<PartidaAppModel> parent){
		parent.add(new Button("denuevo") {
			@Override
			public void onSubmit() {
				Integer indice = PerdistePage.this.getPartidaApp().getPartida().getMundo().getNiveles().indexOf(PerdistePage.this.getPartidaApp().getPartida().getNivelActual());
				Partida partida = new Partida(PerdistePage.this.getPartidaApp().getPartida().getMundo());
				partida.setNivelActual(partida.getMundo().getNiveles().get(indice));
				PartidaPage partidaPage = new PartidaPage(new PartidaAppModel(partida));
				this.setResponsePage(partidaPage);
			}

		});
	}
	

}
