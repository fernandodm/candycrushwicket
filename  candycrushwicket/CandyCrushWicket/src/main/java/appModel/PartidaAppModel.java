package appModel;

import java.io.Serializable;

import Tp.CandyCrush.Coordenada;
import Tp.CandyCrush.Partida;

public class PartidaAppModel implements Serializable {
	
	private Partida partida;

	public PartidaAppModel(Partida partida) {
		this.setPartida(partida);
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}
	
	
	
	
	

}
