package unioeste.ia.trabalho1.pojo;

import java.util.ArrayList;
import java.util.List;

public class Professor {

	/**Atributos**/
	private Integer cd_professor;
	private String nm_professor;
	private List<Disciplina> listaDisciplina;
	private List<Aula> horario;
	
	/**Metodos**/
	
	/**contructor**/	
	public Professor() {
		super();
		this.cd_professor = 0;
		this.setNm_professor("");
		this.listaDisciplina = new ArrayList<Disciplina>();
		this.horario = new ArrayList<Aula>();

	}

	public Professor(Integer cdProfessor, String nmProfessor,
			List<Disciplina> listaDisciplina, List<Curso> listaCurso, List<Serie> listaSerie, List<Aula> horario) {
		super();
		cd_professor = cdProfessor;
		setNm_professor(nmProfessor);
		this.listaDisciplina = listaDisciplina;
		this.horario = horario;

	}


	/**gets e sets**/
	

	public List<Disciplina> getListaDisciplina() {
		return listaDisciplina;
	}

	public List<Aula> getHorario() {
		return horario;
	}

	public void setHorario(List<Aula> horario) {
		this.horario = horario;
	}

	public void setListaDisciplina(List<Disciplina> listaDisciplina) {
		this.listaDisciplina = listaDisciplina;
	}
	
	public void setCd_professor(Integer cd_professor) {
		this.cd_professor = cd_professor;
	}
	
	public Integer getCd_professor() {
		return cd_professor;
	}
	
	public void setNm_professor(String nm_professor) {
		this.nm_professor = nm_professor;
	}
	
	public String getNm_professor() {
		return nm_professor;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Professor)
			return ((Professor) obj).getNm_professor().equals(this.nm_professor);
		return false;
	}
	
	@Override
	public String toString() {
		return "=> Professor {"+ nm_professor+","+listaDisciplina+"}";
	}

}
