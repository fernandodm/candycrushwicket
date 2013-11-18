package CandyCrushWicket.CandyCrushWicket;

import Tp.CandyCrush.ExplosionesPorColor;

public class EditarExplosionesPorColorPage extends EditarObjetivoPage{

	public EditarExplosionesPorColorPage(ExplosionesPorColor obj,
			EditarNivelPanel editarNivelPanel, EditarObjetivoPanel objetivoPanel) {
		
		setObjetivo(obj);
		setEditarNivelPanel(editarNivelPanel);
		setObjetivoPanel(objetivoPanel);
		setNivel(objetivoPanel.getNivel());
		this.add(getObjetivoPanel());
	}
}
