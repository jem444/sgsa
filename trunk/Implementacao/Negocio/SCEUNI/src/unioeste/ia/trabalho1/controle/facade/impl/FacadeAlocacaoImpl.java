package unioeste.ia.trabalho1.controle.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import unioeste.ia.trabalho1.controle.facade.FacadeAlocacao;

import unioeste.ia.trabalho1.pojo.Aula;
import unioeste.ia.trabalho1.pojo.Curso;
import unioeste.ia.trabalho1.pojo.Disciplina;
import unioeste.ia.trabalho1.pojo.Periodo;
import unioeste.ia.trabalho1.pojo.Professor;
import unioeste.ia.trabalho1.pojo.Sala;
import unioeste.ia.trabalho1.pojo.Serie;

public class FacadeAlocacaoImpl {

	/**Metodos**/

	/**
	 * retira curso repetidos
	 */
	public List<Curso> tiraRepetidoCurso(List<Curso> listaCurso){
		if (listaCurso!= null) {
			List<Curso> cursos = new ArrayList<Curso>();			
			for (Curso curso : listaCurso) {
				if (!cursos.contains(curso))
					cursos.add(curso);
			}
			return cursos;
		}
		return null;
	}

	/**
	 * retira disciplinas repetidos
	 */
	public List<Disciplina> tiraRepetidoDisciplina(List<Disciplina> listaDisciplina){
		if (listaDisciplina!= null) {
			List<Disciplina> disciplinas = new ArrayList<Disciplina>();			
			for (Disciplina disciplina : listaDisciplina) {
				if (!disciplinas.contains(disciplina))
					disciplinas.add(disciplina);
			}
			return disciplinas;
		}
		return null;
		
	}

	/**
	 * aloca a series na arvore de dados 
	 * @param listaSerie
	 * @param listaCurso
	 * @return
	 */
	public List<Curso> verificaSerie(List<Serie> listaSerie, List<Curso> listaCurso){
		if (listaCurso != null && listaSerie != null) {
			List<Curso> cursos = listaCurso;
			List<Serie> series = listaSerie;
			Integer j = 0;
			while (j< series.size()) {
				Curso curso = series.get(j).getCurso();
				int k =0;
				boolean acheiCurso= false;
				while (acheiCurso==false && k < cursos.size()) {
					if (curso.equals(cursos.get(k))) {
						if (! cursos.get(k).getListaSerie().contains(series.get(j))) {
							cursos.get(k).getListaSerie().add(series.get(j));							
						}						
						acheiCurso = true;
					}else {
						k++;
					}					
				}
				series.remove(series.get(j));
			}
			return cursos;
		}
		return null;
	}

	/**
	 * aloca a series na arvore de dados 
	 * @param listaDisciplina
	 * @param listaCurso
	 * @return
	 */
	public List<Disciplina> verificaDisciplina(List<Disciplina> listaDisciplina, List<Curso> listaCurso){
		List<Curso> cursos = listaCurso;
		List<Disciplina> disciplinas = listaDisciplina;
		List<Disciplina> listaUnicaDisciplina = new ArrayList<Disciplina>();
		Integer j = 0;	
		while (j < disciplinas.size()) {
			Curso curso = disciplinas.get(j).getCurso();
			Serie serie = disciplinas.get(j).getSerie();
			int k =0;
			boolean acheiCurso= false;
			while (acheiCurso==false && k< cursos.size()) {
				if (curso.equals(cursos.get(k))) {
					acheiCurso = true;
					Boolean acheiSerie = false;
					Integer l = 0;
					while (acheiSerie == false && l< cursos.get(k).getListaSerie().size()){
						if (serie.equals(cursos.get(k).getListaSerie().get(l))) {
							acheiSerie = true;
							List<Disciplina> disc = cursos.get(k).getListaSerie().get(l).getListaDisciplina();
							List<Integer> acheiDisciplina = procuraDisciplina(disc, disciplinas.get(j), 0);
							if (acheiDisciplina.get(0) == 0) {
								cursos.get(k).getListaSerie().get(l).getListaDisciplina().add(disciplinas.get(j));					
							}							
						}else{l++;}
					}
					listaUnicaDisciplina.add(disciplinas.get(j));
					disciplinas.remove(disciplinas.get(j));
				}else {k++;}
			}
		}
		return listaUnicaDisciplina;
	}

	/**
	 * aloca a series na arvore de dados 
	 * @param listaSala
	 * @param qtd_dias
	 * @param qtd_aulas
	 * @return
	 */
	public List<Sala> verificaSalas(List<Sala> listaSala, Integer qtd_dias, Integer qtd_aulas){
		for (Sala sala : listaSala) {
			sala.setListaAula(carregaAulas(qtd_dias, qtd_aulas, sala.getListaAula()));
		}
		return listaSala;
	}

	/**
	 * aloca a series na arvore de dados 
	 * @param listaProfessor
	 * @param listaCurso
	 * @return
	 */
	public Map<String, Professor> verificaProfessor(List<Professor> listaProfessor, List<Curso> listaCurso){
		Map<String, Professor> listaUnicaProfessor = new HashMap<String, Professor>();

		for (Curso curso : listaCurso) {
			for (Serie serie : curso.getListaSerie()) {
				for (Disciplina disciplina : serie.getListaDisciplina()) {
					Professor professor = disciplina.getProfessor();					
					if (! listaUnicaProfessor.containsKey(professor.getNm_professor())) {
						professor.getListaDisciplina().add(disciplina);
						listaUnicaProfessor.put(professor.getNm_professor(), professor);
						professor.setHorario(carregaAulas(11, 5, professor.getHorario()));
					} else {
						professor = listaUnicaProfessor.get(professor.getNm_professor());
						disciplina.setProfessor(professor);
						professor.getListaDisciplina().add(disciplina);
					}					
				}
			}
		}
		return listaUnicaProfessor;
	}

	/**
	 * Verifica se o curso esta na lista 
	 * @param listaCurso
	 * @param curso
	 * @param posInicial
	 * @return lista{inteiro como 0 = nao alocado/ 1= alocado, posicao q esta na lista }
	 */
	public List<Integer> procuraCurso(List<Curso> listaCurso, Curso curso, Integer posInicial){
		List<Integer> respostaProcura = new ArrayList<Integer>();
		Integer acheiCurso = 0;
		while (acheiCurso == 0 && posInicial< listaCurso.size()){
			if (curso.equals(listaCurso.get(posInicial))) {
				acheiCurso = 1;
			}else{posInicial++;}
		}
		respostaProcura.add(acheiCurso);
		respostaProcura.add(posInicial);
		return respostaProcura;
	}

	/**
	 * Verifica se o Serie esta na lista 
	 * @param listaSerie
	 * @param serie
	 * @param posInicial
	 * @return lista{inteiro como 0 = nao alocado/ 1= alocado, posicao q esta na lista }
	 */
	public List<Integer> procuraSerie(List<Serie> listaSerie, Serie serie,  Integer posInicial){
		List<Integer> respostaProcura = new ArrayList<Integer>();
		Integer acheiSerie = 0;
		while (acheiSerie == 0 && posInicial< listaSerie.size()){
			if (serie.equals(listaSerie.get(posInicial))) {
				acheiSerie = 1;
			}else{
				posInicial++;
			}
		}

		respostaProcura.add(acheiSerie);
		respostaProcura.add(posInicial);
		return respostaProcura;
	}

	/**
	 * Verifica se o Serie esta na lista 
	 * @param listaSala
	 * @param serie
	 * @param posInicial
	 * @return lista{inteiro como 0 = nao alocado/ 1= alocado, posicao q esta na lista }
	 */
	public List<Integer> procuraSalaPorSerie(List<Sala> listaSala, Serie serie,  Integer posInicial){
		List<Integer> respostaProcura = new ArrayList<Integer>();
		Integer acheiSala = 0;
		while (acheiSala == 0 && posInicial< listaSala.size()){
			if (listaSala.get(posInicial).getListaSerie().size()!= 0) {
				Integer i = 0;
				while (acheiSala == 0&&
						i< listaSala.get(posInicial).getListaSerie().size()) {
					if (listaSala.get(posInicial).getListaSerie().get(i).getNmSerie().equals(serie.getNmSerie())) {
						if (listaSala.get(posInicial).getListaSerie().get(i).getCurso().equals(serie.getCurso())) {
							acheiSala = 1;
						}						
					}else{i++;}
					if (i>=listaSala.get(posInicial).getListaSerie().size()&& acheiSala == 0) {
						posInicial++;
					}
				}
				
			}else{posInicial++;}
		}

		if (acheiSala==0) {
			posInicial = 0;
			while(acheiSala==0&&posInicial< listaSala.size()){
				if ((listaSala.get(posInicial).getListaSerie().size()==0)) {
					acheiSala = 1;
				}else{
					if (listaSala.get(posInicial).getListaSerie().size()==1) {		
						if (listaSala.get(posInicial).getListaSerie().get(0).getCurso().equals(serie.getCurso())) {
							acheiSala = 1;
						}else {posInicial++;}
					}else {posInicial++;}
				}
			}
		}
		if (posInicial==listaSala.size()&& acheiSala==0) {
			acheiSala =2;
		}
		respostaProcura.add(acheiSala);
		respostaProcura.add(posInicial);
		return respostaProcura;
	}

	/**
	 * Verifica se o Disciplina esta na lista 
	 * @param listaDisciplina
	 * @param disciplina
	 * @param posInicial
	 * @return lista{inteiro como 0 = nao alocado/ 1= alocado, posicao q esta na lista }
	 */
	public List<Integer> procuraDisciplina(List<Disciplina> listaDisciplina, Disciplina disciplina,  Integer posInicial){
		List<Integer> respostaProcura = new ArrayList<Integer>();
		Integer acheiDisciplina = 0;
		while (acheiDisciplina == 0 && posInicial< listaDisciplina.size()){
			if (disciplina.equals(listaDisciplina.get(posInicial))) {
				acheiDisciplina = 1;
			}else{posInicial++;}
		}
		respostaProcura.add(acheiDisciplina);
		respostaProcura.add(posInicial);
		return respostaProcura;
	}

	/**
	 * Verifica se o Aula esta na lista 
	 * @param listaAula
	 * @param aula
	 * @param posInicial
	 * @return lista{inteiro como 0 = nao alocado/ 1= alocado, posicao q esta na lista }
	 */
	public List<Integer> procuraAula(List<Aula> listaAula, Aula aula,  Integer posInicial){
		List<Integer> respostaProcura = new ArrayList<Integer>();
		Integer acheiAula = 0;
		while (acheiAula == 0 && posInicial< listaAula.size()){
			if (aula.equals(listaAula.get(posInicial))) {
				acheiAula = 1;
			}else{posInicial++;}
		}
		respostaProcura.add(acheiAula);
		respostaProcura.add(posInicial);
		return respostaProcura;
	}

	/**
	 * Verifica se o professor esta na lista 
	 * @param listaProfessor
	 * @param professor
	 * @param l
	 * @return lista{inteiro como 0 = nao alocado/ 1= alocado, posicao q esta na lista }
	 */
	public List<Integer> procuraProfessor(List<Professor> listaProfessor, Professor professor,  Integer l){
		List<Integer> respostaProcura = new ArrayList<Integer>();
		Integer acheiProfessor = 0;
		while (acheiProfessor == 0 && l< listaProfessor.size()){
			if (professor.equals(listaProfessor.get(l))) {
				acheiProfessor = 1;
			}else{l++;}
		}
		respostaProcura.add(acheiProfessor);
		respostaProcura.add(l);
		return respostaProcura;
	}

	/**
	 * Carrega as aulas do professor 
	 * @param listaProfessor
	 * @param qtd_dias
	 * @param qtd_aulas
	 * @return lista{inteiro como 0 = nao alocado/ 1= alocado, posicao q esta na lista }
	 */
	/*public List<Professor> carregaHorarioProfessor(List<Professor> listaProfessor, Integer qtd_dias, Integer qtd_aulas){
		List<Professor> professores = tiraRepetidoProfessores(listaProfessor);
		for (int i = 0; i < professores.size(); i++) {
			professores.get(i).setHorario(carregaAulas(qtd_dias, qtd_aulas, new ArrayList<Aula>()));
		}
		return professores;
	}*/

	/**
	 * Carrega as aulas 
	 * @param qtd_dia
	 * @param qtd_aulas_por_dia
	 * @param listaAula
	 * @return lista{inteiro como 0 = nao alocado/ 1= alocado, posicao q esta na lista }
	 */
	private List<Aula> carregaAulas(Integer qtd_dia, Integer qtd_aulas_por_dia, List<Aula> listaAula){
		for (int i = 0; i < qtd_dia; i++) {
			Integer j,k,l;
			for (j = 0; j < 5 ; j++) {
				Aula aula = new Aula();
				aula.setDiaDaSemana(i);
				aula.setPeriodo(Periodo.MATUTINO);
				listaAula.add(aula);
				aula.setCd_aula(listaAula.size()+1);
			}
			for (k = j; k < 10 ; k++) {
				Aula aula = new Aula();
				aula.setDiaDaSemana(i);
				aula.setPeriodo(Periodo.VESPERTINO);
				listaAula.add(aula);
				aula.setCd_aula(listaAula.size()+1);
			}
			for (l = k; l < qtd_dia; l++) {
				Aula aula = new Aula();
				aula.setDiaDaSemana(i);
				aula.setPeriodo(Periodo.NOTURNO);
				listaAula.add(aula);
				aula.setCd_aula(listaAula.size()+1);
			}
		}
		return listaAula;
	}


}