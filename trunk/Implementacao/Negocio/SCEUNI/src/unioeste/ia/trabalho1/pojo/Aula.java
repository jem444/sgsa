package unioeste.ia.trabalho1.pojo;

public class Aula {
	
	/**Atributos**/
	private Integer cd_aula;
	private Boolean aulaDisponivel;
	private Integer diaDaSemana;
	private Periodo periodo;
	private Disciplina disciplina = new Disciplina();
	private Sala sala = new Sala();
	
	/**Metodos**/
	
	/**contructor**/

	public Aula() {
		super();
		this.cd_aula = 0;
		this.aulaDisponivel = true;
		this.diaDaSemana = 0;
		this.disciplina = new Disciplina();
		this.sala = new Sala();
		this.periodo = null;
	}
	public Aula(Integer cdSala, Boolean aulaDisponivel, Integer diaDaSemana,
			Disciplina disciplina, Sala sala, Periodo periodo) {
		super();
		this.cd_aula = cdSala;
		this.aulaDisponivel = aulaDisponivel;
		this.diaDaSemana = diaDaSemana;
		this.disciplina = disciplina;
		this.sala = sala;
		this.periodo = periodo;
	}

	/**gets e sets**/
	
	public Integer getDiaDaSemana() {
		return diaDaSemana;
	}
	public void setDiaDaSemana(Integer diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Sala getSala() {
		return sala;
	}

	public Periodo getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public Integer getCd_aula() {
		return cd_aula;
	}
	public void setCd_aula(Integer cdAula) {
		cd_aula = cdAula;
	}
	public Boolean isAulaDisponivel() {
		return aulaDisponivel;
	}
	public void setAulaDisponivel(Boolean aulaDisponivel) {
		this.aulaDisponivel = aulaDisponivel;
	}
	@Override
	public boolean equals(Object obj) {
	    if (obj != null && obj instanceof Aula ) {
			return ((Aula) obj).getCd_aula().equals(this.cd_aula);
		}
		return false;
	}
	@Override
	public String toString() {
		return "=> Aula {" +diaDaSemana+","+ disciplina.getSerie() +", " +disciplina.getNmDisciplina()+"," +periodo+"}\n";
	}	
}
