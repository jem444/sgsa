package ia.teste;

import ia.infra.negocio.curso.Curso;
import ia.infra.negocio.curso.Disciplina;
import ia.infra.negocio.curso.Professor;
import ia.infra.negocio.curso.Serie;
import ia.infra.negocio.horario.Aula;
import ia.infra.negocio.sala.Sala;

import java.io.File;
import java.util.Date;
import java.util.Vector;

public class Teste {
	
	
	public Teste() {
	}
	
	public static Vector<Curso> getCursos() {
		Vector<Curso> cursos = new Vector<Curso>();
		Vector<Professor> professores = new Vector<Professor>();
		
		Curso c1 = new Curso("Bacharelado em Exoterismo");
		Curso c2 = new Curso("Bacharelado em Busca Heurística de Unicórnios");
		Serie s11 = new Serie(1);
		Serie s12 = new Serie(2);
		Serie s21 = new Serie(1);
		Serie s22 = new Serie(2);
		Disciplina d111 = new Disciplina("Plantar batatas", new Professor("Emmet Brown"));
		Disciplina d112 = new Disciplina("Plantar alcachofras", new Professor("Emmet Brown"));
		Disciplina d113 = new Disciplina("Plantar brocolis", new Professor("Pardal"));
		Disciplina d114 = new Disciplina("Plantar aipo", new Professor("Joselito"));
		Disciplina d121 = new Disciplina("Plantar batatas I", new Professor("Joselito"));
		Disciplina d122 = new Disciplina("Plantar batatas II", new Professor("Geninha"));
		Disciplina d221 = new Disciplina("Plantar alface", new Professor("Pardal"));
		Disciplina d222 = new Disciplina("Plantar bananeiras", new Professor("Joselito"));
		

		d111.setAulas(getNewVectorAula(3));
		d112.setAulas(getNewVectorAula(2));
		d113.setAulas(getNewVectorAula(2));
		d114.setAulas(getNewVectorAula(3));
		d121.setAulas(getNewVectorAula(5));
		d122.setAulas(getNewVectorAula(5));
		d221.setAulas(getNewVectorAula(4));
		d222.setAulas(getNewVectorAula(4));
		
		s11.getDisciplinas().add(d111);
		s11.getDisciplinas().add(d112);
		s11.getDisciplinas().add(d113);
		s11.getDisciplinas().add(d114);
		s12.getDisciplinas().add(d121);
		s12.getDisciplinas().add(d122);
		s22.getDisciplinas().add(d221);
		s22.getDisciplinas().add(d222);
		
		c1.getSeries().add(s11);
		c1.getSeries().add(s12);
		c2.getSeries().add(s21);
		c2.getSeries().add(s22);
		
		cursos.add(c1);
		cursos.add(c2);
		return cursos;
	}
	
	private static Vector<Aula> getNewVectorAula(int size) {
		Vector<Aula> aulas = new Vector<Aula>(size);
		for (int i=0; i<size; i++) {
			aulas.add(new Aula(-1, new Date(00,00,00,00,00), new Date(00,00,00,00,00)));
		}
		
		return aulas;
	}
	
	public static Vector<Sala> getSalas() {
		Vector<Sala> salas = new Vector<Sala>();
		
		Sala a = new Sala("Unila B8, E2, S1", 25);
		Sala b = new Sala("Unila B8, E2, S2", 23);
		Sala c = new Sala("Unila B8, E2, S3", 24);
		Sala d = new Sala("Unila B8, E2, S4", 26);
		salas.add(a);
		salas.add(b);
		salas.add(c);
		salas.add(d);

		return salas;
	}
}
