package unioeste.ia.trabalho1.controle.facade;

import java.util.List;

import unioeste.ia.trabalho1.pojo.Aula;
import unioeste.ia.trabalho1.pojo.Curso;
import unioeste.ia.trabalho1.pojo.Disciplina;
import unioeste.ia.trabalho1.pojo.Periodo;
import unioeste.ia.trabalho1.pojo.Professor;
import unioeste.ia.trabalho1.pojo.Sala;
import unioeste.ia.trabalho1.pojo.Serie;

public interface FacadeEnsalamento {

	public void gerar(List<Curso> cursos, List<Disciplina> disciplinas, List<Serie> series, List<Sala> salas, List<Professor> professores);

	/**
	 * 
	 * @param listaCurso
	 * @param listaSala
	 * @param listaDisciplina
	 * @param listaProfessor
	 */
	public void profundidadePorDisciplina(List<Curso> listaCurso, List<Sala> listaSala, List<Professor> listaProfessor);
	
	public void GreedyPorDisciplina(List<Sala> listaSala, List<Disciplina> listaDisciplina, List<Curso> listaCurso);
	
    public void profundidadePorSerie(List<Curso> listaCurso, List<Sala> listaSala, List<Professor> listaProfessor);
	
	public void GreedyPorSerie(List<Sala> listaSala, List<Disciplina> listaDisciplina, List<Curso> listaCurso);
	
	/**
	 * 
	 * @param qtdAula
	 * @param tamanho
	 * @return lista{numeroAulaDisponiveis, aulasRestantes}
	 */
	public List<Integer> gerarNumeroDeAula(Integer qtdAula, Integer tamanho , Integer inicio);
	
	/**
	 * 
	 * @param listaAula
	 * @param horarioProfessor
	 * @param indiceAulaAtual
	 * @param qtdAula
	 * @param curso
	 * @return lista{numeros do indice das aulas que estao disponiveis}
	 */
	public List<Integer> disponibilidadeAula(List<Aula> listaAula, List<Aula> horarioProfessor, Integer indiceAulaAtual, Integer qtdAula,  Periodo periodo);

	/**
	 * Retorna o numero de aulas que a disciplina deve alocar, devido ao numero de aulas que deve ser ministrada no mesmo dia 
	 * @param disciplina
	 * @return numero de aulas 
	 */
	public Integer qtdAlocarDisciplinaSala(Disciplina disciplina);
	
	
}
