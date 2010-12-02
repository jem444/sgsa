package ia.infra.algoritmo;

public class AgrupamentoAula {
	private String disciplina;
	private int tamanho;
	private String curso;
	private String serie;
	private int diaSemana;
	private int horaAula;
	private boolean alocado;
	
	public AgrupamentoAula(String disciplina, int tamanho, String curso,
			String serie,int diasemana, int horaAula) {
		this.disciplina = disciplina;
		this.tamanho = tamanho;
		this.curso = curso;
		this.serie = serie;
		this.horaAula = horaAula;
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
	public void setAlocado(boolean alocado) {
		this.alocado = alocado;
	}
	public boolean isAlocado() {
		return alocado;
	}
	public void setHoraAula(int horaAula) {
		this.horaAula = horaAula;
	}
	public int getHoraAula() {
		return horaAula;
	}
	@Override
	public String toString() {
		return this.disciplina;
	}
	
}
