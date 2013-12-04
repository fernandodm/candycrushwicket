package appModel;

import java.io.Serializable;
import java.util.List;

import CandyCrushWicket.CandyCrushWicket.PartidaPage;
import Tp.CandyCrush.Coordenada;
import Tp.CandyCrush.Movimiento;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Partida;
import Tp.CandyCrush.Tablero;

public class PartidaAppModel implements Serializable {
	
	private Partida partida;
//
	public PartidaAppModel(Partida partida) {
		this.setPartida(partida);
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public String puntajeMinimoToString() {

		return getPartida().puntajeDelNivelActual().toString();
	}

	public int movimientosFaltantesDeLaPartida() {

		return getPartida().getCantMovimientosFaltantes();
	}

	public Tablero tableroDeLaPartida() {
	
		return getPartida().getNivelActual().getTablero();
	}

	public Coordenada ObtenerCoordenadaDeLaPartida() {
		
		return getPartida().getCoordenadaActual();
	}

	public Movimiento movimientoARealizar() {
		
		return getPartida().getMovimientoARealizar();
	}

	public void setearALaPartidaLosMovimientos(int i) {
		getPartida().setCantMovimientosFaltantes(i);		
	}

	public Nivel obtenerNivel() {
		return getPartida().getNivelActual();
	}

	public List<Nivel> obtenerNivelesDelMundo() {
		return getPartida().getMundo().getNiveles();
	}	

}