package ia.infra.algoritmo;

import ia.infra.negocio.horario.HorarioSerie;

import java.util.ArrayList;

public class ResultaConflitos {
	private HorarioSerie horario;
	private ArrayList<AgrupamentoAula> listaAgrupamento;
	
	public ResultaConflitos(ArrayList<AgrupamentoAula> listaAgrupamento, HorarioSerie horario) {
		
		this.listaAgrupamento = listaAgrupamento;
		this.setHorario(horario);
	}
	
	public ResultaConflitos() {
		
		this.listaAgrupamento = new ArrayList<AgrupamentoAula>();
		this.setHorario(new HorarioSerie());
	}
	
	
	
	public void setListaAgrupamento(ArrayList<AgrupamentoAula> listaAgrupamento) {
		this.listaAgrupamento = listaAgrupamento;
	}
	
	public ArrayList<AgrupamentoAula> getListaAgrupamento() {
		return listaAgrupamento;
	}

	public void setHorario(HorarioSerie horario) {
		this.horario = horario;
	}

	public HorarioSerie getHorario() {
		return horario;
	}


}
