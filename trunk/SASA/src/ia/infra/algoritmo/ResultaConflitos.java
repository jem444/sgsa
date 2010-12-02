package ia.infra.algoritmo;

import java.util.ArrayList;

public class ResultaConflitos {
	private String curso;
	private int semestre;
	private int conflito;
	private ArrayList<AgrupamentoAula> listaAgrupamento;
	
	public ResultaConflitos(int conflito, ArrayList<AgrupamentoAula> listaAgrupamento, String curso, int semestre) {
		this.conflito = conflito;
		this.listaAgrupamento = listaAgrupamento;
		this.curso = curso;
		this.semestre = semestre;
	}
	
	public ResultaConflitos() {
		this.conflito = 0;
		this.listaAgrupamento = new ArrayList<AgrupamentoAula>();
		this.curso = "";
		this.semestre = 0;
	}
	
	public void setConflito(int conflito) {
		this.conflito = conflito;
	}
	
	public int getConflito() {
		return conflito;
	}
	
	public void setListaAgrupamento(ArrayList<AgrupamentoAula> listaAgrupamento) {
		this.listaAgrupamento = listaAgrupamento;
	}
	
	public ArrayList<AgrupamentoAula> getListaAgrupamento() {
		return listaAgrupamento;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getCurso() {
		return curso;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public int getSemestre() {
		return semestre;
	}

}
