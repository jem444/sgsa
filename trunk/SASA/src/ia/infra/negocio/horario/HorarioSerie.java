package ia.infra.negocio.horario;

import ia.infra.negocio.curso.Curso;
import ia.infra.negocio.curso.Serie;

public class HorarioSerie {
	private Atividade[][] semana;
	private Curso curso;
	private Serie serie;
	private Integer semestre;
	
	public HorarioSerie() {
		ModeloHorario modeloHorario = new ModeloHorario();
		//instancia todos os dias da semana, de segunda a sexta, com uma lista de atividades obtidas do modelo de horario
		this.semana = new Atividade[modeloHorario.getAtividades().size()][6];
		for (int i=0; i<modeloHorario.getAtividades().size(); i++) {
			for (int j=0; j<6; j++) {
				this.semana[i][j] = (Atividade)((new ModeloHorario()).getAtividades().toArray())[i];
				this.semana[i][j].setOrdem(-1);
			}
		}
//		this.semana = null;
		this.curso = new Curso(null);
		this.serie = new Serie(-1);
		this.semestre = 0;
		
	}

	public HorarioSerie(Atividade[][] semana, Curso curso, Serie serie,
			Integer semestre) {
		this.semana = semana;
		this.curso = curso;
		this.serie = serie;
		this.semestre = semestre;
	}
	
	public Atividade[][] getSemana() {
		return semana;
	}
	public void setSemana(Atividade[][] semana) {
		this.semana = semana;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Serie getSerie() {
		return serie;
	}
	public void setSerie(Serie serie) {
		this.serie = serie;
	}
	public Integer getSemestre() {
		return semestre;
	}
	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}
	@Override
	public String toString() {
		String s = "Curso: "+curso.toString()+" Semana: "+semana.toString()+" Semestre: "+semestre;
		return s;
	}
	
}
