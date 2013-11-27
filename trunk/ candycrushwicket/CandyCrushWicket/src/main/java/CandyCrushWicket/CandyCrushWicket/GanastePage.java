package CandyCrushWicket.CandyCrushWicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;

import Tp.CandyCrush.Arriba;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;
import Tp.CandyCrush.Partida;
import appModel.PartidaAppModel;

public class GanastePage extends WebPage {
	
	private static final long serialVersionUID = 1L;
	private PartidaAppModel partidaApp;
	
	public PartidaAppModel getPartidaApp() {
		return partidaApp;
	}

	public void setPartidaApp(PartidaAppModel partida) {
		this.partidaApp = partida;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public GanastePage(PartidaAppModel partAM){
		this.setPartidaApp(partAM);
		
//		this.add(new Label(partAM.getPartida().getNivelActual().getNombre()));
		
		Form<PartidaAppModel> form = new Form<PartidaAppModel>("partidaAppForm", new CompoundPropertyModel<PartidaAppModel>(partAM));
		form.setOutputMarkupId(true);
		form.add(new Label("partida.puntaje"));
		String nombre = partAM.getPartida().getNivelActual().getNombre();
		form.add(new Label("resultado","Nivel " + nombre + " superado!!!"));
		this.agregarGrillaNiveles(form);
		this.agregarBotonSiguiente(form);
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
	
	public void agregarBotonSiguiente(Form<PartidaAppModel> parent){
		parent.add(new Button("ganar") {
			@Override
			public void onSubmit() {
				Integer indice = GanastePage.this.getPartidaApp().getPartida().getMundo().getNiveles().indexOf(GanastePage.this.getPartidaApp().getPartida().getNivelActual());
				Partida partida = new Partida(GanastePage.this.getPartidaApp().getPartida().getMundo());
				partida.setNivelActual(partida.getMundo().getNiveles().get(indice + 1));
				PartidaPage partidaPage = new PartidaPage(new PartidaAppModel(partida));
				this.setResponsePage(partidaPage);
			}

		});
	}
	

}
