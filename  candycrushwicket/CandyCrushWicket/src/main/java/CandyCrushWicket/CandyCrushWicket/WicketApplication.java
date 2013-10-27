package CandyCrushWicket.CandyCrushWicket;

import org.apache.wicket.protocol.http.WebApplication;


/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see candyCrush.ui.wicket.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    
	@Override
	public Class<InicioPage> getHomePage() {
		return InicioPage.class;
	}

}