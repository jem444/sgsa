package ia.infra.algoritmo;

import ia.infra.negocio.horario.DiaSemana;

public class AgrupamentoAula {
	private String disciplina;
	private int tamanho;
	private String curso;
	private String serie;
	private int diaSemana;
	
	public AgrupamentoAula(String disciplina, int tamanho, String curso,
			String serie,int diasemana) {
		this.disciplina = disciplina;
		this.tamanho = tamanho;
		this.curso = curso;
		this.serie = serie;
		this.diaSemana = diasemana;
	}
	public AgrupamentoAula() {
		this.disciplina = "";
		this.tamanho = 0;
		this.curso = "";
		this.serie = "";
		this.diaSemana = 0;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}
	public int getDiaSemana() {
		return diaSemana;
	}
	
	
}
