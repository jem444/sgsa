package unioeste.ia.trabalho1.controle.facade;

import java.util.List;
import java.util.Map;

import unioeste.ia.trabalho1.pojo.Aula;
import unioeste.ia.trabalho1.pojo.Curso;
import unioeste.ia.trabalho1.pojo.Disciplina;
import unioeste.ia.trabalho1.pojo.Professor;
import unioeste.ia.trabalho1.pojo.Sala;
import unioeste.ia.trabalho1.pojo.Serie;

public interface FacadeAlocacao {

	/**
	 * Metodo que verifica se existe cursos identicos na lista de curso
	 * @param listaCurso
	 * @return listaCurso
	 */
	public List<Curso> tiraRepetidoCurso(List<Curso> listaCurso);

	/**
	 * Metodo que verifica se as series estao alocadas ao curso
	 * @param listaSerie
	 * @param listaCurso
	 * @return listaCurso
	 */
	public List<Curso> verificaSerie(List<Serie> listaSerie, List<Curso> listaCurso);

	/**
	 * Metodo que verifica se existe as diciplinas estão alocadas nos cursos e respectivos series
	 * @param listaDisciplina
	 * @param listaCurso
	 * @return listaCurso
	 */
	public List<Disciplina> verificaDisciplina(List<Disciplina> listaDisciplina, List<Curso> listaCurso);

	/**
	 * Metodo que organiza os professores em suas respectivas disciplinas
	 * @param listaProfessor
	 * @param listaCurso
	 * @return
	 */
	public Map<String, Professor> verificaProfessor(List<Professor> listaProfessor, List<Curso> listaCurso);

	/**
	 * 
	 * @param listaCurso
	 * @param curso
	 * @param posInicial
	 * @return lista{acheiCurso(0-não, 1-sim), indiceCurso}
	 */
	public List<Integer> procuraCurso(List<Curso> listaCurso, Curso curso, Integer posInicial);

	/**
	 * 
	 * @param listaSerie
	 * @param serie
	 * @param posInicial
	 * @return lista{acheiSerie(0-não, 1-sim), indiceSerie}
	 */
	public List<Integer> procuraSerie(List<Serie> listaSerie, Serie serie, Integer posInicial);

	/**
	 * 
	 * @param listaDisciplina
	 * @param disciplina
	 * @param posInicial
	 * @return lista{acheiDisciplina(0-não, 1-sim), indiceDisciplina}
	 */
	public List<Integer> procuraDisciplina(List<Disciplina> listaDisciplina, Disciplina disciplina, Integer posInicial);

	/**
	 * 
	 * @param listaProfessor
	 * @param professor
	 * @param posInicial
	 * @return lista{acheiProfessor(0-não, 1-sim), indiceProfessor}
	 */
	public List<Integer> procuraProfessor(List<Professor> listaProfessor, Professor professor, Integer posInicial);

	/**
	 * 
	 * @param listaAula
	 * @param aula
	 * @param posInicial
	 * @return
	 */
	public List<Integer> procuraAula(List<Aula> listaAula, Aula aula,  Integer posInicial);
	
	/**
	 * 
	 * @param listaSala
	 * @param qtd_dias
	 * @param qtd
	 * @return
	 */
	public List<Sala> verificaSalas(List<Sala> listaSala, Integer qtd_dias, Integer qtd);

	/**
	 * 
	 * @param listaProfessor
	 */
	public List<Professor> tiraRepetidoProfessores(List<Professor> listaProfessor);

	/**
	 * 
	 * @param listaDisciplina
	 * @return
	 */
	public List<Disciplina> tiraRepetidoDisciplina(List<Disciplina> listaDisciplina);
	
	/**
	 * 
	 * @param listaProfessor
	 * @param qtd_dias
	 * @param qtd_aulas
	 * @return
	 */
	public List<Professor> carregaHorarioProfessor(List<Professor> listaProfessor, Integer qtd_dias, Integer qtd_aulas);
	
	/**
	 * 
	 * @param listaSala
	 * @param serie
	 * @param posInicial
	 * @return
	 */
	public List<Integer> procuraSalaPorSerie(List<Sala> listaSala, Serie serie,  Integer posInicial);
}
