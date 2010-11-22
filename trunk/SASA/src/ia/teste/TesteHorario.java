package ia.teste;

import java.util.Vector;

import ia.infra.negocio.curso.Curso;
import ia.infra.negocio.curso.Disciplina;
import ia.infra.negocio.curso.Professor;
import ia.infra.negocio.horario.Atividade;
import ia.infra.negocio.horario.ModeloHorario;
import ia.infra.negocio.sala.Sala;

public class TesteHorario {
	private ModeloHorario modeloHorario;
	private Atividade[][] semana;
	private Vector<Disciplina> disciplinas;
	
	public TesteHorario() {
		
		this.modeloHorario = new ModeloHorario();
		//this.semana = new Atividade[0][6];
		
		//instancia todos os dias da semana, de segunda a sábado, com uma lista de atividades obtidas do modelo de horario
		this.semana = new Atividade[modeloHorario.getAtividades().size()][6];
		for (int i=0; i<modeloHorario.getAtividades().size(); i++) {
			for (int j=0; j<6; j++) {
				this.semana[i][j] = (Atividade)((new ModeloHorario()).getAtividades().toArray())[i];
				this.semana[i][j].setOrdem(-1);
			}
		}
		
		alocaDisciplinas(semana);
	}
	
	private void alocaDisciplinas(Atividade[][] semana) {
		disciplinas = new Vector<Disciplina>();
		
		Disciplina d01 = new Disciplina("GEOM ÁLG", new Professor("Pardal"));
		d01.criarAulas(4);
		disciplinas.add(d01);
		Disciplina d02 = new Disciplina("INTRODUÇÃO", new Professor("Pardal"));
		d02.criarAulas(2);
		disciplinas.add(d02);
		Disciplina d03 = new Disciplina("COMPUTAÇÃO I", new Professor("Pardal"));
		d03.criarAulas(5);
		disciplinas.add(d03);
		Disciplina d04 = new Disciplina("LÓGICA", new Professor("Pardal"));
		d04.criarAulas(3);
		disciplinas.add(d04);
		Disciplina d05 = new Disciplina("PROBABILIDADE", new Professor("Pardal"));
		d05.criarAulas(2);
		disciplinas.add(d05);
		Disciplina d06 = new Disciplina("CÁLCULO", new Professor("Pardal"));
		d06.criarAulas(4);
		disciplinas.add(d06);
		Disciplina d07 = new Disciplina("FÍSICA", new Professor("Pardal"));
		d07.criarAulas(4);
		disciplinas.add(d07);
		
//		aloca semana[ordem][dia]
		semana[0][0] = (Atividade)d01.getAulas().get(0);
		semana[1][0] = (Atividade)d01.getAulas().get(1);
		semana[2][0] = (Atividade)d01.getAulas().get(2);
		semana[3][0] = (Atividade)d01.getAulas().get(3);
	}

	public ModeloHorario getModeloHorario() {
		return modeloHorario;
	}

	public void setModeloHorario(ModeloHorario modeloHorario) {
		this.modeloHorario = modeloHorario;
	}

	public Atividade[][] getSemana() {
		return semana;
	}

	public void setSemana(Atividade[][] semana) {
		this.semana = semana;
	}

	public Vector<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Vector<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	
}
