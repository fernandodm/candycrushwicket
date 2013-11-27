package CandyCrushWicket.CandyCrushWicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;

import Tp.CandyCrush.Partida;
import appModel.PartidaAppModel;

public class FinalPage extends WebPage {

	private static final long serialVersionUID = 1L;
	private PartidaAppModel partidaApp;
	
	public PartidaAppModel getPartidaApp() {
		return partidaApp;
	}
	
	public void setPartidaApp(PartidaAppModel partidaApp) {
		this.partidaApp = partidaApp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public FinalPage(PartidaAppModel partApp){
		this.setPartidaApp(partApp);
		
		
		Form<PartidaAppModel> form = new Form<PartidaAppModel>("partidaAppForm", new CompoundPropertyModel<PartidaAppModel>(partApp));
		form.setOutputMarkupId(true);
		this.agregarBotonTerminar(form);
		this.add(form);
	}
	
	public void agregarBotonTerminar(Form<PartidaAppModel> parent){
		parent.add(new Button("listo") {
			@Override
			public void onSubmit() {
				this.setResponsePage(new InicioPage());
			}

		});
	}
	
}
