package CandyCrushWicket.CandyCrushWicket;

import Tp.CandyCrush.GrandesExplosiones;

public class AgregarGrandesExplosionesPage extends AgregarObjetivoPage{
	

	
	public AgregarGrandesExplosionesPage(GrandesExplosiones obj,
			EditarNivelPanel editarNivelPanel, EditarObjetivoPanel objetivoPanel) {
		
		setObjetivo(obj);
		setEditarNivelPanel(editarNivelPanel);
		setObjetivoPanel(objetivoPanel);
		setNivel(objetivoPanel.getNivel());
		this.add(getObjetivoPanel());
		
	}

	

}