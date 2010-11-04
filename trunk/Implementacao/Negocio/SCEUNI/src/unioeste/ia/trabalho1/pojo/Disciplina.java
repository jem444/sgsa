package unioeste.ia.trabalho1.pojo;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {

	/**Atributos**/
	private Integer cd_disciplina;
	private String nm_disciplina;
	private Integer quantidade_matricula;
	private Integer quantidade_aula;
	private Integer quantidadeAulaFaltaAlocar;
	
	private List<Aula> listaAula;
	private Professor professor;
	private Serie serie;
	private Curso curso;

	/**Metodos**/

	/**contructor**/
	public Disciplina() {
		super();
		this.cd_disciplina = 0;
		this.nm_disciplina = "";
		this.quantidade_matricula = 0;
		this.quantidade_aula = 0;
		this.quantidadeAulaFaltaAlocar = 0;
		this.listaAula = new ArrayList<Aula>();
		this.professor = new Professor();
		this.serie = new Serie();
		this.curso = new Curso();
	}

	public Disciplina(Integer cdDisciplina, String nmDisciplina,
			Integer quantidadeMatricula, Integer quantidadeAula,
			Integer quantidadeAulaFaltaAlocar,
			List<Aula> listaAula, Professor professor, Serie serie, Curso curso) {
		super();
		this.cd_disciplina = cdDisciplina;
		this.nm_disciplina = nmDisciplina;
		this.quantidade_matricula = quantidadeMatricula;
		this.quantidade_aula = quantidadeAula;
		this.quantidadeAulaFaltaAlocar = quantidadeAulaFaltaAlocar; 
		this.listaAula = listaAula;
		this.professor = professor;
		this.serie = serie;
		this.curso = curso;
	}
	
	/**gets e sets**/
	public String getNmDisciplina() {
		return nm_disciplina;
	}
	
	public void setNmDisciplina(String nmDisciplina) {
		this.nm_disciplina = nmDisciplina;
	}
	
	public List<Aula> getListaAula() {
		return listaAula;
	}
	
	public void setListaAula(List<Aula> listaAula) {
		this.listaAula = listaAula;
	}
	
	public void setQuantidade_matricula(Integer quantidade_matricula) {
		this.quantidade_matricula = quantidade_matricula;
	}
	
	public Integer getQuantidade_matricula() {
		return quantidade_matricula;
	}
	
	public void setQuantidade_aula(Integer quantidade_aula) {
		this.quantidade_aula = this.quantidadeAulaFaltaAlocar = quantidade_aula;
	}
	
	public Integer getQuantidade_aula() {
		return quantidade_aula;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public void setSerie(Serie serie) {
		this.serie = serie;
	}
	
	public Serie getSerie() {
		return serie;
	}
	
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public Curso getCurso() {
		return curso;
	}
	
	public void setCd_disciplina(Integer cd_disciplina) {
		this.cd_disciplina = cd_disciplina;
	}
	
	public Integer getCd_disciplina() {
		return cd_disciplina;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Disciplina)
			return ((Disciplina) obj).getNmDisciplina().equals(this.nm_disciplina) &&
			((Disciplina) obj).getCurso().equals(this.curso);
		return false;
	}
	
	@Override
	public String toString() {		
		return "\n=> Disciplina {"+nm_disciplina+","+quantidade_matricula+","+quantidadeAulaFaltaAlocar+","+professor.getNm_professor()+"}";
	}

	public void setQuantidadeAulaFaltaAlocar(Integer quantidadeAulaFaltaAlocar) {
		this.quantidadeAulaFaltaAlocar = quantidadeAulaFaltaAlocar;
	}

	public Integer getQuantidadeAulaFaltaAlocar() {
		return quantidadeAulaFaltaAlocar;
	}
}