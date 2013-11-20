package CandyCrushWicket.CandyCrushWicket;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.CompoundPropertyModel;

import appModel.MundoAppModel;

@SuppressWarnings("all")
public class InicioPage extends WebPage {

	private static final long serialVersionUID = 1L;
	private MundoAppModel mundoApp;
	
	public InicioPage() {
	
		this.mundoApp = new MundoAppModel();
		Form<MundoAppModel> loginForm = new Form<MundoAppModel>("mundoAppForm", new CompoundPropertyModel<MundoAppModel>(this.mundoApp));
		this.agregarCampo(loginForm);
		this.agregarAcciones(loginForm);
		this.add(loginForm);
       
    }
    
    private void agregarCampo(Form<MundoAppModel> parent) {
		
		final TextField<String> userTextField = new TextField<String>("nombreUsuario");
		userTextField.setRequired(Boolean.TRUE);
		userTextField.add(new PropertyValidator());
		parent.add(userTextField);
		parent.add(new FeedbackPanel("feedbackPanel"));
	}

    private void agregarAcciones(Form<MundoAppModel> parent) {
		parent.add(new Button("login") {
			@Override
			public void onSubmit() {
				ConfigurarPage confg = new ConfigurarPage(mundoApp);
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
