package CandyCrushWicket.CandyCrushWicket;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;

import excepciones.ExcepcionNoGeneroExplosion;

import Tp.CandyCrush.Caramelo;
import Tp.CandyCrush.Fila;
import Tp.CandyCrush.Movimiento;
import Tp.CandyCrush.Objetivo;
import appModel.PartidaAppModel;

public class PartidaPage extends WebPage{
	
	private static final long serialVersionUID = 1L;
	private PartidaAppModel partidaApp;
	private Integer movimientos;
	
	public PartidaAppModel getPartidaApp() {
		return partidaApp;
	}

	public void setPartidaApp(PartidaAppModel partida) {
		this.partidaApp = partida;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public PartidaPage(PartidaAppModel partAM){
		this.setPartidaApp(partAM);

		Form<PartidaAppModel> form = new Form<PartidaAppModel>("partidaAppForm", new CompoundPropertyModel<PartidaAppModel>(partAM));
		form.setOutputMarkupId(true);
		form.add(new Label("partida.puntaje"));
		form.add(new Label("puntajeMinimo", this.getPartidaApp().getPartida().getNivelActual().getPuntajeMinimo().toString()));
		form.add(new Label("partida.cantMovimientosFaltantes"));
		this.movimientos = partAM.getPartida().getCantMovimientosFaltantes();
		this.agregarCamposMovimiento(form);
		this.agregarGrillaObjetivos(form);
		this.agregarBotonMover(form);
		this.agregarBotonGanar(form);
		this.agregarBotonPerder(form);
		this.agregarTablero(form);
		this.add(form);
	}

	public void agregarGrillaObjetivos(Form<PartidaAppModel> parent){
	
		parent.add(new PropertyListView<Objetivo>("partida.nivelActual.objetivos") {
		@Override
		protected void populateItem(final ListItem<Objetivo> item) {
			item.add(new Label("descripcion"));
			item.add(new Label("seCumplioDescripcion"));
						
			}

		});
	}
	
	public void agregarCamposMovimiento(Form<PartidaAppModel> parent){
		
		parent.add(new TextField<String>("partida.coordenadaActual.fila"));
		parent.add(new TextField<String>("partida.coordenadaActual.columna"));
		parent.add(new DropDownChoice<Movimiento>("partida.movimientoARealizar", crearListaMovimientos(), createMovimientoChoiceRenderer()));
		
	}
	
	protected ChoiceRenderer<Movimiento> createMovimientoChoiceRenderer() {
		return new ChoiceRenderer<Movimiento>() {
			@Override
			public Object getDisplayValue(Movimiento object) {
				return object.getNombre();
			}
		};
	}

	protected LoadableDetachableModel<List<Movimiento>> crearListaMovimientos() {
		return new LoadableDetachableModel<List<Movimiento>>() {
			@Override
			protected List<Movimiento> load() {
				return Movimiento.getMovimientos();
			}
		};
	}
	
	private void agregarBotonMover(Form<PartidaAppModel> parent){
		parent.add(new AjaxButton("mover"){
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				try {
					
					if((PartidaPage.this.getPartidaApp().getPartida().getCantMovimientosFaltantes() - 1) > 0){
					PartidaPage.this.getPartidaApp().getPartida().getNivelActual().getTablero().moverCarameloSiEsValido(PartidaPage.this.getPartidaApp().getPartida().getCoordenadaActual(), PartidaPage.this.getPartidaApp().getPartida().getMovimientoARealizar());
					PartidaPage.this.getPartidaApp().getPartida().setCantMovimientosFaltantes(PartidaPage.this.getPartidaApp().getPartida().getCantMovimientosFaltantes() - 1);
					} else {
						PartidaPage.this.getPartidaApp().getPartida().getNivelActual().setCantidadMovimientos(PartidaPage.this.movimientos);
						PartidaPage.this.getPartidaApp().getPartida().setCantMovimientosFaltantes(PartidaPage.this.movimientos);
						this.setResponsePage(new PerdistePage(PartidaPage.this.getPartidaApp()));
					}
				} catch (ExcepcionNoGeneroExplosion e) {
					e.printStackTrace();
				}
				target.addComponent(form);
			}
		});
	}
	
	private void agregarBotonGanar(Form<PartidaAppModel> parent){
		parent.add(new Button("ganar"){
			@Override
			public void onSubmit(){
				PartidaPage.this.getPartidaApp().getPartida().getNivelActual().setTermino(true);
				GanastePage partidaPage = new GanastePage(new PartidaAppModel(PartidaPage.this.getPartidaApp().getPartida()));
				Integer in = PartidaPage.this.getPartidaApp().getPartida().getMundo().getNiveles().indexOf(PartidaPage.this.getPartidaApp().getPartida().getNivelActual());
				if(in == PartidaPage.this.getPartidaApp().getPartida().getMundo().getNiveles().size() - 1){
					this.setResponsePage(new FinalPage(PartidaPage.this.getPartidaApp()));
				} else {
					this.setResponsePage(partidaPage);
				}
				
			}
		});
	}
	
	private void agregarBotonPerder(Form<PartidaAppModel> parent){
		parent.add(new Button("perder"){
			@Override
			public void onSubmit(){
				PartidaPage.this.getPartidaApp().getPartida().getNivelActual().setTermino(false);
				PerdistePage perdistePage = new PerdistePage(new PartidaAppModel(PartidaPage.this.getPartidaApp().getPartida()));
				this.setResponsePage(perdistePage);
				
			}
		});
	}
	
	public void agregarTablero(Form<PartidaAppModel> parent){
		
		parent.add(new PropertyListView<Fila>("partida.nivelActual.tablero.filas") {
		@Override
		protected void populateItem(final ListItem<Fila> item) {
			item.add(new Label("contenido"));
			}

		});
		
	}
	
	
}
