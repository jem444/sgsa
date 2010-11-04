package unioeste.ia.trabalho1.controle.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import unioeste.ia.trabalho1.pojo.Curso;
import unioeste.ia.trabalho1.pojo.Disciplina;
import unioeste.ia.trabalho1.pojo.Sala;
import unioeste.ia.trabalho1.pojo.Serie;

public class UtilOrdena {

	/**
	 * 
	 * @param listaSala
	 * @return
	 */
	public static List<Sala> ordenaSala(List<Sala> listaSala) {
		Comparator<Sala> comparator = new Comparator<Sala>(){
			public int compare(Sala salaA, Sala salaB){
				return (salaA.getCapacidade() - salaB.getCapacidade()) * (-1);  
			}		   
		};
		Collections.sort(listaSala, comparator);
		return listaSala; 
	}
	
	/**
	 * 
	 * @param listaDisciplina
	 * @return
	 */
	public static List<Disciplina> ordenaDisciplina(List<Disciplina> listaDisciplina) {

		Comparator<Disciplina> comparator = new Comparator<Disciplina>(){
			public int compare(Disciplina disciplinaA, Disciplina disciplinaB){
				int resultado = (disciplinaA.getQuantidade_matricula() - disciplinaB.getQuantidade_matricula()) * (-1);
				if (resultado == 0)
					return (disciplinaA.getQuantidade_aula() - disciplinaB.getQuantidade_aula()) * (-1);
				else
					return resultado;  
				
			}		   
		};
		Collections.sort(listaDisciplina, comparator);
		return listaDisciplina; 
	}
	
	/**
	 * 
	 * @param listaDisciplina
	 * @return
	 */
	public static List<Disciplina> ordenaDisciplinaQuantidadeAula(List<Disciplina> listaDisciplina) {

		Comparator<Disciplina> comparator = new Comparator<Disciplina>(){
			public int compare(Disciplina disciplinaA, Disciplina disciplinaB){
				return (disciplinaA.getQuantidadeAulaFaltaAlocar() - disciplinaB.getQuantidadeAulaFaltaAlocar()) * (-1);
			}		   
		};
		Collections.sort(listaDisciplina, comparator);
		return listaDisciplina; 
	}

	public static List<Curso> ordenaDisciplinaQtdAula(List<Curso> listaCurso) {
		for (Curso curso : listaCurso) {
			for (Serie serie : curso.getListaSerie()) {				
				Comparator<Disciplina> comparator = new Comparator<Disciplina>(){
					public int compare(Disciplina disciplinaA, Disciplina disciplinaB) {
						int resultado = (disciplinaA.getQuantidade_matricula() - disciplinaB.getQuantidade_matricula())* (-1);
						if (resultado == 0)
							return (disciplinaA.getQuantidade_aula() - disciplinaB.getQuantidade_aula()) * (-1);
						else
							return resultado;  
					}		   
				};
				Collections.sort(serie.getListaDisciplina(), comparator);
			}
		}
		return listaCurso;	
	}
	
	
	public static List<Curso> ordenaDisciplinaMatriculados(List<Curso> listaCurso) {
		for (Curso curso : listaCurso) {
			for (Serie serie : curso.getListaSerie()) {				
				Comparator<Disciplina> comparator = new Comparator<Disciplina>(){
					public int compare(Disciplina disciplinaA, Disciplina disciplinaB) {
						int resultado = (disciplinaA.getQuantidade_matricula() - disciplinaB.getQuantidade_matricula())* (-1);
						if (resultado == 0)
							return (disciplinaA.getQuantidade_matricula() - disciplinaB.getQuantidade_matricula()) * (-1);
						else
							return resultado;  
					}		   
				};
				Collections.sort(serie.getListaDisciplina(), comparator);
			}
		}
		return listaCurso;	
	}

	public static List<Curso> ordenaSerie(List<Curso> listaCurso) {
		for (Curso curso : listaCurso) {
			Comparator<Serie> comparator = new Comparator<Serie>(){
			public int compare(Serie serieA, Serie serieB) {
						int resultado = (serieA.getNu_serie() - serieB.getNu_serie());
						if (resultado == 0)
							return (serieA.getNu_serie() - serieB.getNu_serie());
						else
							return resultado;  
					}		   
				};
				Collections.sort(curso.getListaSerie(), comparator);
			}
		return listaCurso;	
	}
	public List<Curso> ordenaTudo(List<Curso> listaCurso){
		List<Curso> cursos = new ArrayList<Curso>();
		cursos = ordenaSerie(listaCurso);
		cursos = ordenaDisciplinaMatriculados(cursos);
		cursos = ordenaDisciplinaQtdAula(cursos);
		return null;
		
	}
}
