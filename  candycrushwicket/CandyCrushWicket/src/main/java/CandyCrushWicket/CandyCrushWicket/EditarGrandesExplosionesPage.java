package CandyCrushWicket.CandyCrushWicket;

import Tp.CandyCrush.GrandesExplosiones;

public class EditarGrandesExplosionesPage extends EditarObjetivoPage{
	
	
	public EditarGrandesExplosionesPage(GrandesExplosiones obj,
			EditarNivelPanel editarNivelPanel, EditarObjetivoPanel objetivoPanel) {
		
		setObjetivo(obj);
		setEditarNivelPanel(editarNivelPanel);
		setObjetivoPanel(objetivoPanel);
		setNivel(objetivoPanel.getNivel());
		this.add(getObjetivoPanel());
		
	}

	
}
