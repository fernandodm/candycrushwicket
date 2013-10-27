package CandyCrushWicket.CandyCrushWicket;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.CompoundPropertyModel;

import appModel.MundoAppModel;


/**
 * Homepage
 */
public class InicioPage extends WebPage {

	private static final long serialVersionUID = 1L;
	private MundoAppModel mundoApp;
	
	public InicioPage() {
	
		this.mundoApp = new MundoAppModel();
		Form<MundoAppModel> loginForm = new Form<MundoAppModel>("nombreUser", new CompoundPropertyModel<MundoAppModel>(this.mundoApp));
		this.agregarCampo(loginForm);
		this.agregarAcciones(loginForm);
		this.add(loginForm);
       
    }
    
    private void agregarCampo(Form<MundoAppModel> parent) {
		parent.add(new TextField<String>("nombreUsuario"));
	}

    private void agregarAcciones(Form<MundoAppModel> parent) {
		parent.add(new Button("login") {
			@Override
			public void onSubmit() {
				ConfigurarPage confg = new ConfigurarPage(mundoApp, InicioPage.this );
				this.setResponsePage(confg);
			}

		});
    }
    public MundoAppModel getMundoApp() {
		return mundoApp;
	}

	public void setMundoApp(MundoAppModel mundoApp) {
		this.mundoApp = mundoApp;
	}
    
}
