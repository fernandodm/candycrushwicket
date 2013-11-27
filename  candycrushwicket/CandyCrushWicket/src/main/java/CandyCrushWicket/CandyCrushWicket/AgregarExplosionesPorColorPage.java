package CandyCrushWicket.CandyCrushWicket;

import Tp.CandyCrush.ExplosionesPorColor;

public class AgregarExplosionesPorColorPage extends AgregarObjetivoPage{
	
	public AgregarExplosionesPorColorPage(ExplosionesPorColor obj,
			EditarNivelPanel editarNivelPanel, EditarObjetivoPanel objetivoPanel) {
		
		setObjetivo(obj);
		setEditarNivelPanel(editarNivelPanel);
		setObjetivoPanel(objetivoPanel);
		setNivel(objetivoPanel.getNivel());
		this.add(getObjetivoPanel());
		
	}
	
}

