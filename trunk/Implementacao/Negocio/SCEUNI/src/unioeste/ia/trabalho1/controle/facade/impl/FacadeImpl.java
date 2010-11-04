package unioeste.ia.trabalho1.controle.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import unioeste.ia.trabalho1.controle.facade.FacadeAlocacao;
import unioeste.ia.trabalho1.controle.util.UtilOrdena;
import unioeste.ia.trabalho1.pojo.Aula;
import unioeste.ia.trabalho1.pojo.Curso;
import unioeste.ia.trabalho1.pojo.Disciplina;
import unioeste.ia.trabalho1.pojo.Periodo;
import unioeste.ia.trabalho1.pojo.Professor;
import unioeste.ia.trabalho1.pojo.Sala;
import unioeste.ia.trabalho1.pojo.SalaStatus;
import unioeste.ia.trabalho1.pojo.Serie;
import debugar.*;


public class FacadeImpl{

	/**Atributos**/
	private List<Curso> tudo;
	private List<Serie> listaSerie;
	private List<Curso> listaCurso;
	private List<Disciplina> listaDisciplina;
	private List<Sala> listaSala;
	private List<Sala> listaSalaProfundidade;
	private List<Professor> horarioProf;
	private List<Professor> horarioProfProfundidade;
	Integer qtd_dias;
	Integer qtd_aulas;

	private List<Professor> listaProfessor;

	private List<Sala> listaSalaGreedy;

	private List<Curso> listaCursoGreedy;

	private List<Serie> listaSerieGreedy;

	private List<Disciplina> listaDisciplinaGreedy;

	private List<Professor> listaProfessorGreedy;

	private ArrayList<Professor> horarioProfGreedy;

	private List<Curso> tudoGreedy;



	/**Constructor**/
	public FacadeImpl() {
		super();
		this.tudo = new ArrayList<Curso>();
		this.listaSerie = new ArrayList<Serie>();
		this.listaCurso = new ArrayList<Curso>();
		this.listaDisciplina = new ArrayList<Disciplina>();
		//		this.listaProfessor = new ArrayList<Professor>();
		this.listaSala = new ArrayList<Sala>();
		this.horarioProf = new ArrayList<Professor>();
		this.qtd_dias = 0;
		this.qtd_aulas = 0;

	}

	public FacadeImpl(List<Curso> tudo, List<Serie> listaSerie,
			List<Curso> listaCurso, List<Disciplina> listaDisciplina,
			List<Professor> listaProfessor, List<Sala> listaSala, 
			List<Professor> horarioProf, Integer qtd_dias, Integer qtd_aulas) {
		super();
		this.tudo = tudo;
		this.listaSerie = listaSerie;
		this.listaCurso = listaCurso;
		this.listaDisciplina = listaDisciplina;
		//	this.listaProfessor = listaProfessor;
		this.listaSala = listaSala;
		this.horarioProf = horarioProf;
		this.qtd_dias = qtd_dias;
		this.qtd_aulas = qtd_aulas;
	}

	/**Metodos**/
	/*
	public void gerar(List<Curso> cursos, List<Disciplina> disciplinas, List<Serie> series, List<Sala> salas, List<Professor> professores) {
		this.qtd_dias = 5;
		this.qtd_aulas = 11;
		gerarProfundidade(cursos, disciplinas, series, salas, professores);
		//gerarGreedy();
	}*/

	/**
	 * gerar Matriz para q possa fazer a leitura  na interface
	 */
	public String[][] gerarMatriz(Sala sala, Integer diaSemana, Integer aulasDia){
		int indexDiaSemana, indexAula;
		String[][] aux = new String[aulasDia-1][diaSemana];
		indexDiaSemana = indexAula=0;
		for (Aula aula : sala.getListaAula()) {
			if (indexAula>=aulasDia-1) {
				indexDiaSemana++;
				indexAula = 0;
			}
			if (indexDiaSemana>= diaSemana ) {
				indexAula=0;
				indexDiaSemana=0;
			}
			aux[indexAula][indexDiaSemana]= aula.getDisciplina().getNmDisciplina();
			if (indexAula>=aulasDia) {
				indexDiaSemana++;
				indexAula = 0;
			}else{
				if (indexDiaSemana>= diaSemana ) {
					indexAula=0;
					indexDiaSemana=0;
				}else {
					indexAula++;
				}
				
			}
		}
		return aux;
	}

	/**
	 * Gera as alocacoes para poder realizar a execucao do codigo
	 * @param cursos
	 * @param disciplinas
	 * @param series
	 * @param salas
	 * @param professores
	 */
	public void gerarProfundidadeDisciplina(List<Curso> cursos, List<Disciplina> disciplinas, List<Serie> series, List<Sala> salas, List<Professor> professores){
		this.qtd_dias = 5;
		this.qtd_aulas = 11;
		//this.listaSala = salas;
		//this.listaCurso = cursos;
		//this.listaSerie = series;
		//this.listaDisciplina = disciplinas;
		//this.listaProfessor = professores;

		ArquivoSalaView arquivoSala = new ArquivoSalaView();
		arquivoSala.menuArquivoAbrir();
		this.listaSala = arquivoSala.getListaSala();


		ArquivoCursoView arquivoCurso = new ArquivoCursoView();
		arquivoCurso.menuArquivoAbrir();
		this.listaCurso = arquivoCurso.getListaCurso();
		this.listaSerie = arquivoCurso.getListaSerie();
		this.listaDisciplina = arquivoCurso.getListaDisciplina();
		//this.listaProfessor = arquivoCurso.getListaProfessor();

		FacadeAlocacaoImpl aloca = new FacadeAlocacaoImpl();
		horarioProf = new ArrayList<Professor>();
		//horarioProf = aloca.carregaHorarioProfessor(listaProfessor, qtd_dias, qtd_aulas);

		tudo = aloca.tiraRepetidoCurso(listaCurso);
		tudo = aloca.verificaSerie(listaSerie, tudo);
		listaDisciplina = aloca.verificaDisciplina(listaDisciplina, tudo);
		listaSala = aloca.verificaSalas(listaSala, qtd_dias, qtd_aulas);
		aloca.verificaProfessor(horarioProf, tudo);

		UtilOrdena.ordenaDisciplina(listaDisciplina);
		UtilOrdena.ordenaSala(listaSala);

		profundidadePorDisciplina(tudo, listaSala, horarioProf);
		//profundidadePorSerie(tudo, listaSala, horarioProf);

		System.out.println("fim");

	}
	
	/**
	 *  Gera as alocacoes para poder realizar a execucao do codigo
	 * @param cursos
	 * @param disciplinas
	 * @param series
	 * @param salas
	 * @param professores
	 */
	public void gerarProfundidadeSeries(List<Curso> cursos, List<Disciplina> disciplinas, List<Serie> series, List<Sala> salas, List<Professor> professores){
		this.qtd_dias = 5;
		this.qtd_aulas = 11;
		
		this.qtd_dias = 5;
		this.qtd_aulas = 11;

		
		this.listaSala = salas;


		this.listaCurso = cursos;
		this.listaSerie = series;
		this.listaDisciplina = disciplinas;
		this.listaProfessor = professores;
		
				ArquivoSalaView arquivoSala = new ArquivoSalaView();
		arquivoSala.menuArquivoAbrir();
		this.listaSala = arquivoSala.getListaSala();


		ArquivoCursoView arquivoCurso = new ArquivoCursoView();
		arquivoCurso.menuArquivoAbrir();
		this.listaCurso = arquivoCurso.getListaCurso();
		this.listaSerie = arquivoCurso.getListaSerie();
		this.listaDisciplina = arquivoCurso.getListaDisciplina();
		this.listaProfessor = arquivoCurso.getListaProfessor();
		FacadeAlocacaoImpl aloca = new FacadeAlocacaoImpl();
		horarioProf = new ArrayList<Professor>();
		//horarioProf = aloca.carregaHorarioProfessor(listaProfessor, qtd_dias, qtd_aulas);

		tudo = aloca.tiraRepetidoCurso(listaCurso);
		tudo = aloca.verificaSerie(listaSerie, tudo);
		listaDisciplina = aloca.verificaDisciplina(listaDisciplina, tudo);
		listaSala = aloca.verificaSalas(listaSala, qtd_dias, qtd_aulas);
		aloca.verificaProfessor(horarioProf, tudo);

		UtilOrdena.ordenaDisciplina(listaDisciplina);
		UtilOrdena.ordenaSala(listaSala);

		//profundidadePorDisciplina(tudo, listaSala, horarioProf);
		profundidadePorSerie(tudo, listaSala, horarioProf);

		System.out.println("fim");

	}

	/**
	 *  Gera as alocacoes para poder realizar a execucao do codigo
	 * @param cursos
	 * @param disciplinas
	 * @param series
	 * @param salas
	 * @param professores
	 */
	public void gerarGreedyDisciplina(List<Curso> cursos, List<Disciplina> disciplinas, List<Serie> series, List<Sala> salas, List<Professor> professores){
		this.qtd_dias = 5;
		this.qtd_aulas = 11;

		this.listaSala = salas;

		this.listaCursoGreedy = cursos;
		this.listaSerieGreedy = series;
		this.listaDisciplinaGreedy = disciplinas;
		this.listaProfessorGreedy = professores;

		ArquivoSalaView arquivoSala = new ArquivoSalaView();
		arquivoSala.menuArquivoAbrir();
		this.listaSala = arquivoSala.getListaSala();


		ArquivoCursoView arquivoCurso = new ArquivoCursoView();
		arquivoCurso.menuArquivoAbrir();
		this.listaCursoGreedy = arquivoCurso.getListaCurso();
		this.listaSerieGreedy = arquivoCurso.getListaSerie();
		this.listaDisciplinaGreedy = arquivoCurso.getListaDisciplina();
		this.listaProfessorGreedy = arquivoCurso.getListaProfessor();
		
		FacadeAlocacaoImpl aloca = new FacadeAlocacaoImpl();
		horarioProfGreedy = new ArrayList<Professor>();
	
		tudoGreedy = aloca.tiraRepetidoCurso(listaCursoGreedy);
		tudoGreedy = aloca.verificaSerie(listaSerieGreedy, tudoGreedy);
		listaDisciplinaGreedy = aloca.verificaDisciplina(listaDisciplinaGreedy, tudoGreedy);
		listaSala = aloca.verificaSalas(listaSala, qtd_dias, qtd_aulas);
		aloca.verificaProfessor(horarioProfGreedy, tudoGreedy);

		UtilOrdena.ordenaDisciplina(listaDisciplinaGreedy);
		UtilOrdena.ordenaSala(listaSala);


		GreedyPorDisciplina(listaSala, listaDisciplinaGreedy, tudoGreedy);
		//GreedyPorSerie(listaSalaGreedy, listaDisciplinaGreedy, tudoGreedy);
		System.out.println("fim Greedy");

	}
	
	/**
	 *  Gera as alocacoes para poder realizar a execucao do codigo
	 * @param cursos
	 * @param disciplinas
	 * @param series
	 * @param salas
	 * @param professores
	 */
	public void gerarGreedySerie(List<Curso> cursos, List<Disciplina> disciplinas, List<Serie> series, List<Sala> salas, List<Professor> professores){
		this.qtd_dias = 5;
		this.qtd_aulas = 11;

		this.listaSala = salas;

		this.listaCursoGreedy = cursos;
		this.listaSerieGreedy = series;
		this.listaDisciplinaGreedy = disciplinas;
		this.listaProfessorGreedy = professores;

		ArquivoSalaView arquivoSala = new ArquivoSalaView();
		arquivoSala.menuArquivoAbrir();
		this.listaSala = arquivoSala.getListaSala();


		ArquivoCursoView arquivoCurso = new ArquivoCursoView();
		arquivoCurso.menuArquivoAbrir();
		this.listaCursoGreedy = arquivoCurso.getListaCurso();
		this.listaSerieGreedy = arquivoCurso.getListaSerie();
		this.listaDisciplinaGreedy = arquivoCurso.getListaDisciplina();
		this.listaProfessorGreedy = arquivoCurso.getListaProfessor();
		
		FacadeAlocacaoImpl aloca = new FacadeAlocacaoImpl();
		horarioProfGreedy = new ArrayList<Professor>();
	
		tudoGreedy = aloca.tiraRepetidoCurso(listaCursoGreedy);
		tudoGreedy = aloca.verificaSerie(listaSerieGreedy, tudoGreedy);
		listaDisciplinaGreedy = aloca.verificaDisciplina(listaDisciplinaGreedy, tudoGreedy);
		listaSala = aloca.verificaSalas(listaSala, qtd_dias, qtd_aulas);
		aloca.verificaProfessor(horarioProfGreedy, tudoGreedy);

		UtilOrdena.ordenaDisciplina(listaDisciplinaGreedy);
		UtilOrdena.ordenaSala(listaSala);


		//GreedyPorDisciplina(listaSala, listaDisciplinaGreedy, tudoGreedy);
		GreedyPorSerie(listaSala, listaDisciplinaGreedy, tudoGreedy);
		System.out.println("fim Greedy");

	}

	/**
	 * verificar qntas aulas pode ser alocdas para naum ficar td no msm dia 
	 * @param disciplina
	 * @return
	 */
	public Integer qtdAlocarDisciplinaSala(Disciplina disciplina){
		Integer qtdAulaFaltaAlocar=0;
		if (disciplina.getQuantidadeAulaFaltaAlocar()<=3) {
			qtdAulaFaltaAlocar = disciplina.getQuantidadeAulaFaltaAlocar();
		}else{
			if (disciplina.getQuantidadeAulaFaltaAlocar()==4) {
				qtdAulaFaltaAlocar = 2;
			}else{
				qtdAulaFaltaAlocar = 3;
			}
		}
		return qtdAulaFaltaAlocar;
	}

	/**
	 * busca em profundidde com CSP 
	 * @param listaCurso
	 * @param listaSala
	 * @param listaProfessor
	 */
	public void profundidadePorDisciplina(List<Curso> listaCurso, List<Sala> listaSala, List<Professor> listaProfessor) {

		UtilOrdena.ordenaSerie(listaCurso);
		UtilOrdena.ordenaDisciplinaMatriculados(tudo);
		UtilOrdena.ordenaSala(listaSala);
		Integer i, j, k,l;
		i=j=k=l=0;
		proxSala: while (i < listaSala.size()) {// percorre as salas disponiveis 
			Sala sala = listaSala.get(i);
			proxCurso: while(j < listaCurso.size()) {//percorre o curso
				Curso curso = listaCurso.get(j);
				if (sala.getCurso().equals(curso)||sala.getCurso().equals(new Curso())) { //verifica se o curso q foi alocado na sala eh a mesma da disciplina em gostria de alocar
					sala.setCurso(curso);
					while (l<curso.getListaSerie().size()) {//percorre a series do curso
						Serie serie = curso.getListaSerie().get(l);
						if (sala.getListaSerie().size()< 2) { //qnd a sala ja esta alocada com 2 serie ele sai da compracao 
							sala.getListaSerie().add(serie);
							Integer aindaAlocar = 0;
							iniDisciplina: while(k < serie.getListaDisciplina().size()) {//percorre disciplina 
								Disciplina disciplina = serie.getListaDisciplina().get(k);
								if (sala.getCapacidade() >= disciplina.getQuantidade_matricula()) {
									Integer alocarAula=0;
									if (disciplina.getQuantidadeAulaFaltaAlocar()<=3) {// verifica qntas aulas podem ser alocadas para essa disciplina para q naum fique as aulas tudo junto 
										alocarAula = disciplina.getQuantidadeAulaFaltaAlocar();
									}else{
										if (disciplina.getQuantidadeAulaFaltaAlocar()==4) {
											alocarAula = 2;
										}else{
											alocarAula = 3;
										}
									}
									alocarDisciplinasEmSala(sala, disciplina.getSerie(), disciplina, alocarAula);
									aindaAlocar = aindaAlocar+disciplina.getQuantidadeAulaFaltaAlocar();
								}
								k++;
								if (serie.getListaDisciplina().indexOf(disciplina)+1== serie.getListaDisciplina().size()&&  
										aindaAlocar!=0) {//verificar se ja percorreu toda lista da sala 
									k=0;
									aindaAlocar =0;
									continue iniDisciplina;
								}
							}
						}else{
							i++;
							continue proxSala;
						}
						l++;
						k=0;
					}
				}else{i++;
				l=0;
				k=0;
				continue proxSala;}
				j++;
				l=0;
				k=0;
				continue proxCurso;
			}
			i++;
			l=0;
			k=0;
		}
	}
	
	/**
	 * Busca gulosa com CSP
	 * @param listaSala
	 * @param listaDisciplina
	 * @param listaCurso
	 */
	public void GreedyPorDisciplina(List<Sala> listaSala, List<Disciplina> listaDisciplina, List<Curso> listaCurso) {

		FacadeAlocacaoImpl aloca = new FacadeAlocacaoImpl();
		List<Integer> indexBusca = null;
		Map<String, Serie> seriesAlocada = new HashMap<String, Serie>();

		proxSala: for (Sala sala: listaSala) {
			
			for (Disciplina disciplina : listaDisciplina) {
				if (sala.getCapacidade() >= disciplina.getQuantidade_matricula()) {//compara se a capacidade da sala e permite ministrar disciplina
					indexBusca = aloca.procuraCurso(listaCurso, disciplina.getCurso(), 0);						

					if(indexBusca.get(0) == 1) { //encontrou curso
						Curso curso = listaCurso.get(indexBusca.get(1));
						indexBusca = aloca.procuraSerie(curso.getListaSerie(), disciplina.getSerie(), 0);
						if(indexBusca.get(0) == 1) { //encontrou serie
							Serie serie = curso.getListaSerie().get(indexBusca.get(1));
							if (!seriesAlocada.containsKey(serie.toString())) {
								setSerieEmSala(sala,serie);
								seriesAlocada.put(serie.toString(),serie);									
								if (seriesAlocada.size() % 2 == 0||(!serie.getCurso().equals(sala.getListaAula().get(0))))
									continue proxSala;
							} else {
								// Disciplina dessa serie jah foi alocada
							}
						}							
					}
				} else {
					// throw new NaoExisteSalaParaCapacidadeAlunosMatriculados();
				}					
			}

		}
	}

	/**
	 *  busca em profundidde com CSP 
	 * @param listaCurso
	 * @param listaSala
	 * @param listaProfessor
	 */
	public void profundidadePorSerie(List<Curso> listaCurso, List<Sala> listaSala, List<Professor> listaProfessor) {
		
		//FacadeAlocacaoImpl aloca = new FacadeAlocacaoImpl();
		UtilOrdena.ordenaSerie(listaCurso);
		UtilOrdena.ordenaDisciplinaMatriculados(tudo);
		UtilOrdena.ordenaSala(listaSala);

		Integer i, j, k,l;
		i=j=k=l=0;
		proxSala: while (i < listaSala.size()) {
			Sala sala = listaSala.get(i);
			proxCurso: while(j < listaCurso.size()) {
				Curso curso = listaCurso.get(j);
				if (sala.getCurso().equals(curso)||sala.getCurso().equals(new Curso())) {
					sala.setCurso(curso);
					while (l<curso.getListaSerie().size()) {
						Serie serie = curso.getListaSerie().get(l);
						if (sala.getListaSerie().size()< 2) {
							sala.getListaSerie().add(serie);
							Integer aindaAlocar = 0;
							iniDisciplina: while(k < serie.getListaDisciplina().size()) {
								Disciplina disciplina = serie.getListaDisciplina().get(k);
								if (sala.getCapacidade() >= serie.getQuantidade_matriculado_serie()) {
									Integer alocarAula=0;
									if (disciplina.getQuantidadeAulaFaltaAlocar()<=3) {
										alocarAula = disciplina.getQuantidadeAulaFaltaAlocar();
									}else{
										if (disciplina.getQuantidadeAulaFaltaAlocar()==4) {
											alocarAula = 2;
										}else{
											alocarAula = 3;
										}
									}
									alocarDisciplinasEmSala(sala, disciplina.getSerie(), disciplina, alocarAula);
									aindaAlocar = aindaAlocar+disciplina.getQuantidadeAulaFaltaAlocar();
								}
								k++;
								if (serie.getListaDisciplina().indexOf(disciplina)+1== serie.getListaDisciplina().size()&& 
										aindaAlocar!=0) {
									k=0;
									aindaAlocar =0;
									continue iniDisciplina;
								}
							}
						}else{
							i++;
							continue proxSala;
						}
						l++;
						k=0;
					}
				}else{i++;
				l=0;
				k=0;
				continue proxSala;}
				j++;
				l=0;
				k=0;
				continue proxCurso;
			}
			i++;
			l=0;
			k=0;
		}
	}

	/**
	 *  busca em gulosa com CSP 
	 * @param listaSala
	 * @param listaDisciplina
	 * @param listaCurso
	 */
	public void GreedyPorSerie(List<Sala> listaSala, List<Disciplina> listaDisciplina, List<Curso> listaCurso) {
		FacadeAlocacaoImpl aloca = new FacadeAlocacaoImpl();
		List<Integer> indexBusca = null;
		Map<String, Serie> seriesAlocada = new HashMap<String, Serie>();

		proxSala: for (Sala sala: listaSala) {
			for (Disciplina disciplina : listaDisciplina) {
				if(indexBusca.get(0) == 1) { //encontrou curso
					Curso curso = listaCurso.get(indexBusca.get(1));
					indexBusca = aloca.procuraSerie(curso.getListaSerie(), disciplina.getSerie(), 0);
					if(indexBusca.get(0) == 1) { //encontrou serie
						Serie serie = curso.getListaSerie().get(indexBusca.get(1));
				if (sala.getCapacidade() >= serie.getQuantidade_matriculado_serie()) {
					indexBusca = aloca.procuraCurso(listaCurso, disciplina.getCurso(), 0);						

					
							if (!seriesAlocada.containsKey(serie.toString())) {
								setSerieEmSala(sala,serie);
								seriesAlocada.put(serie.toString(),serie);									
								if (seriesAlocada.size() % 2 == 0||(!serie.getCurso().equals(sala.getListaAula().get(0))))
									continue proxSala;
							} else {
								//continue proxDisciplina;
								// Disciplina dessa serie jah foi alocada
							}
						}							
					}
				} else {
					// throw new NaoExisteSalaParaCapacidadeAlunosMatriculados();
				}					
			}

		}
	}
	
	/**
	 *  Seta a salas 
	 * @param sala
	 * @param serie
	 */
	private void setSerieEmSala(Sala sala, Serie serie) {
		sala.setStatus(SalaStatus.EM_USO);
		List<Integer> result = null;
		Periodo periodoDisponivel = serie.getCurso().getPeriodo();
		List<Periodo> listaPeriodosDisponivel = new ArrayList<Periodo>();

		UtilOrdena.ordenaDisciplinaQuantidadeAula(serie.getListaDisciplina());
		do {

			for (Disciplina disciplina : serie.getListaDisciplina()) {//percorre todas as disciplinas de uma serie
				if (disciplina.getQuantidadeAulaFaltaAlocar() > 0) {
					if (disciplina.getQuantidadeAulaFaltaAlocar() <= 3) {// quando for necessario alocar menos de 3 aulas
						int indexAula = 0;
						int tentativa = 0;
						do {						
							result = disponibilidadeAula(sala.getListaAula(), 
									disciplina.getProfessor().getHorario(), indexAula++, 
									disciplina.getQuantidadeAulaFaltaAlocar()-tentativa,
									periodoDisponivel);

							if (result.size()<(disciplina.getQuantidadeAulaFaltaAlocar()-tentativa) && result.size() > 0){
								indexAula = indexAula + result.size();
								result = new ArrayList<Integer>();
								if (indexAula >= sala.getListaAula().size()) {
									indexAula = 0;
									tentativa++;
								}
							}
						} while (indexAula < sala.getListaAula().size() && result.isEmpty() 
								&& disciplina.getQuantidade_aula()-tentativa > 0);

						if (disciplina.getQuantidade_aula()-tentativa == 0) {// verificacao os periodos para a naum deixe as disciplinas sejam alocadas no msm periodo 
							if (serie.getCurso().getPeriodo() == Periodo.INTEGRAL)
								if (listaPeriodosDisponivel.size() == 0) {
									listaPeriodosDisponivel.add(periodoDisponivel);
									switch (periodoDisponivel) {
									case MATUTINO:
										periodoDisponivel = Periodo.VESPERTINO;
										break;
									case VESPERTINO:
										periodoDisponivel = Periodo.MATUTINO;
										break;
									}
								} else {
									sala.setStatus(SalaStatus.CHEIO);
								}
							else
								sala.setStatus(SalaStatus.CHEIO);
						}

						if(!result.isEmpty()) {// aloca a sequencia de aulas com a disciplina q foi verificada
							disciplina.setQuantidadeAulaFaltaAlocar(disciplina.getQuantidadeAulaFaltaAlocar() - result.size());	
							Aula aulaSala = null;
							Aula aulaProfessor = null;
							for (Integer index : result) {
								aulaProfessor = disciplina.getProfessor().getHorario().get(index);
								aulaSala = sala.getListaAula().get(index);

								aulaProfessor.setDisciplina(disciplina);
								aulaSala.setDisciplina(disciplina);

								aulaProfessor.setAulaDisponivel(false);
								aulaSala.setAulaDisponivel(false);
								sala.setQtdAulasAlocadas(sala.getQtdAulasAlocadas()+1);

								periodoDisponivel = aulaSala.getPeriodo();
							}					
						} else {
							sala.setStatus(SalaStatus.CHEIO);
							break;
						}



					} else if (disciplina.getQuantidadeAulaFaltaAlocar() == 4) {// verificar se a diciplina tem 4 aulas a serem alocadas
						int indexAula = 0;
						int tentativa = 0;
						do {						
							result = disponibilidadeAula(sala.getListaAula(), 
									disciplina.getProfessor().getHorario(), indexAula++, 
									2-tentativa,
									periodoDisponivel);

							if (result.size()<(2-tentativa) && result.size() > 0){
								indexAula = indexAula + result.size();
								result = new ArrayList<Integer>();
								if (indexAula >= sala.getListaAula().size()) {
									indexAula = 0;
									tentativa++;
								}
							}
						} while (indexAula < sala.getListaAula().size() && result.isEmpty() 
								&& 2-tentativa > 0);

						if (2-tentativa == 0) {// verificacao os periodos para a naum deixe as disciplinas sejam alocadas no msm periodo 
							if (serie.getCurso().getPeriodo() == Periodo.INTEGRAL)
								if (listaPeriodosDisponivel.size() == 0) {
									listaPeriodosDisponivel.add(periodoDisponivel);
									switch (periodoDisponivel) {
									case MATUTINO:
										periodoDisponivel = Periodo.VESPERTINO;
										break;
									case VESPERTINO:
										periodoDisponivel = Periodo.MATUTINO;
										break;
									}
								} else {
									sala.setStatus(SalaStatus.CHEIO);
								}
							else
								sala.setStatus(SalaStatus.CHEIO);
						}

						if(!result.isEmpty()) {// aloca a sequencia de aulas com a disciplina q foi verificada
							disciplina.setQuantidadeAulaFaltaAlocar(disciplina.getQuantidadeAulaFaltaAlocar() - result.size());	
							Aula aulaSala = null;
							Aula aulaProfessor = null;
							for (Integer index : result) {
								aulaProfessor = disciplina.getProfessor().getHorario().get(index);
								aulaSala = sala.getListaAula().get(index);

								aulaProfessor.setDisciplina(disciplina);
								aulaSala.setDisciplina(disciplina);

								aulaProfessor.setAulaDisponivel(false);
								aulaSala.setAulaDisponivel(false);

								periodoDisponivel = aulaSala.getPeriodo();
								sala.setQtdAulasAlocadas(sala.getQtdAulasAlocadas()+1);
							}					
						} else {
							sala.setStatus(SalaStatus.CHEIO);
							break;
						}

					} else {

						int indexAula = 0;
						int tentativa = 0;
						do {		
										// aloca as aulas para disciplinas em que tenham mais de 4 aulas a serem alocadas
							result = disponibilidadeAula(sala.getListaAula(), 
									disciplina.getProfessor().getHorario(), indexAula++, 
									3-tentativa,
									periodoDisponivel);

							if (result.size()<(3-tentativa) && result.size() > 0){
								indexAula = indexAula + result.size() -1;
								result = new ArrayList<Integer>();
								if (indexAula >= sala.getListaAula().size()) {
									indexAula = 0;
									tentativa++;
								}
							}
						} while (indexAula < sala.getListaAula().size() && result.isEmpty() 
								&& 3-tentativa > 0);

						if (3-tentativa == 0) {// verificacao os periodos para a naum deixe as disciplinas sejam alocadas no msm periodo 
							if (serie.getCurso().getPeriodo() == Periodo.INTEGRAL)
								if (listaPeriodosDisponivel.size() == 0) {
									listaPeriodosDisponivel.add(periodoDisponivel);
									switch (periodoDisponivel) {
									case MATUTINO:
										periodoDisponivel = Periodo.VESPERTINO;
										break;
									case VESPERTINO:
										periodoDisponivel = Periodo.MATUTINO;
										break;
									}
								} else {
									sala.setStatus(SalaStatus.CHEIO);
								}
							else
								sala.setStatus(SalaStatus.CHEIO);
						}

						if(!result.isEmpty()) {// aloca a sequencia de aulas com a disciplina q foi verificada
							disciplina.setQuantidadeAulaFaltaAlocar(disciplina.getQuantidadeAulaFaltaAlocar() - result.size());	
							Aula aulaSala = null;
							Aula aulaProfessor = null;
							for (Integer index : result) {
								aulaProfessor = disciplina.getProfessor().getHorario().get(index);
								aulaSala = sala.getListaAula().get(index);

								aulaProfessor.setDisciplina(disciplina);
								aulaSala.setDisciplina(disciplina);

								aulaProfessor.setAulaDisponivel(false);
								aulaSala.setAulaDisponivel(false);

								periodoDisponivel = aulaSala.getPeriodo();
								sala.setQtdAulasAlocadas(sala.getQtdAulasAlocadas()+1);
							}					
						} else {
							sala.setStatus(SalaStatus.CHEIO);
							break;
						}

					}			
				}
			}
			UtilOrdena.ordenaDisciplinaQuantidadeAula(serie.getListaDisciplina());
		} while(serie.getListaDisciplina().get(0).getQuantidadeAulaFaltaAlocar() > 0 && sala.getStatus() != SalaStatus.CHEIO);				
	}

	/**
	 * alocar soment uma diciplina na aula ... setando a sala, professor e disciplina 
	 * @param sala
	 * @param aulaProfessor
	 * @param aulaSala
	 * @param disciplina
	 * @param indexHorario
	 */
	public void alocaDisciplinaAula(Sala sala, Aula aulaProfessor, Aula aulaSala, Disciplina disciplina, Integer indexHorario){

		aulaProfessor = disciplina.getProfessor().getHorario().get(indexHorario);
		aulaSala = sala.getListaAula().get(indexHorario);

		aulaProfessor.setDisciplina(disciplina);
		aulaSala.setDisciplina(disciplina);

		aulaProfessor.setAulaDisponivel(false);
		aulaSala.setAulaDisponivel(false);
		sala.setQtdAulasAlocadas(sala.getQtdAulasAlocadas()+1);
	}

	/**
	 * verifica a sequencia de aulas q tem discponibilidade conforme a numero de aulas que deseja ser alocadas
	 * @param listaAula
	 * @param horarioProfessor
	 * @param indiceAulaAtual
	 * @param qtdAula
	 * @param periodo
	 * @return
	 */
	public List<Integer> disponibilidadeAula(List<Aula> listaAula, List<Aula> horarioProfessor, Integer indiceAulaAtual, Integer qtdAula, Periodo periodo) {
		List<Integer> aulasDisponiveis = new ArrayList<Integer>();
		List<Integer> listaNumeroAula = new ArrayList<Integer>();
		Integer numeroAula = 0;
		Integer qtdAulaSequencial = 0; 
		Boolean senquenciaDisponivel = true;
		Aula horaProfessor;
		Aula aula  = new Aula();
		listaNumeroAula = gerarNumeroDeAula(qtdAula, listaAula.size(), indiceAulaAtual);
		numeroAula = listaNumeroAula.get(0);

		for (int i = indiceAulaAtual; i < (indiceAulaAtual + numeroAula) && senquenciaDisponivel ; i++) {										
			
			aula = listaAula.get(i);
			horaProfessor = horarioProfessor.get(i);
			if (aula.isAulaDisponivel() && 
					(periodo.value()&aula.getPeriodo().value()) !=  0 &&
					horaProfessor.isAulaDisponivel()) {
				aulasDisponiveis.add(i);
				periodo = aula.getPeriodo();
			} else {
				senquenciaDisponivel = false;
				qtdAulaSequencial = i - indiceAulaAtual; 
				indiceAulaAtual = qtdAulaSequencial + indiceAulaAtual;
			}											
		}
		return aulasDisponiveis;
	}

	/**
	 * gerar numeros de aulas  serem alocadas 
	 * @param qtdAula
	 * @param tamanho
	 * @param inicio
	 * @return
	 */
	public List<Integer> gerarNumeroDeAula(Integer qtdAula, Integer tamanho, Integer inicio){
		Integer numeroAula, aulaRestante = 0;
		List<Integer> listaqtdAula = new ArrayList<Integer>();
		if(inicio<tamanho){
			if (tamanho-inicio >= qtdAula) {
				numeroAula = qtdAula;
			}else{ 
				numeroAula = tamanho;
				aulaRestante = qtdAula- (tamanho-inicio); 
			}
			listaqtdAula.add(numeroAula);
			listaqtdAula.add(aulaRestante);
		}
		return listaqtdAula;
	}

	/**
	 * alocar as diciplinas em sala 
	 * @param sala
	 * @param serie
	 * @param disciplina
	 * @param qtdAulaFaltaAlocar
	 */
	public void alocarDisciplinasEmSala(Sala sala, Serie serie, Disciplina disciplina, Integer qtdAulaFaltaAlocar){
		List<Integer> result = null;
		Periodo periodoDisponivel = serie.getCurso().getPeriodo();
		List<Periodo> listaPeriodosDisponivel = new ArrayList<Periodo>();
		int indexAula = 0;
		int tentativa = 0;
		do {		
			
			result = disponibilidadeAula(sala.getListaAula(), 
					disciplina.getProfessor().getHorario(), indexAula++, 
					qtdAulaFaltaAlocar-tentativa,
					periodoDisponivel);

			if (result.size()<(qtdAulaFaltaAlocar-tentativa) && result.size() > 0){
				indexAula = indexAula + result.size() -1;
				result = new ArrayList<Integer>();
				if (indexAula >= sala.getListaAula().size()) {
					indexAula = 0;
					tentativa++;
				}
			}
		} while (indexAula < sala.getListaAula().size() && result.isEmpty() 
				&& qtdAulaFaltaAlocar-tentativa > 0);

		if (qtdAulaFaltaAlocar-tentativa == 0) {
			if (serie.getCurso().getPeriodo() == Periodo.INTEGRAL)
				if (listaPeriodosDisponivel.size() == 0) {
					listaPeriodosDisponivel.add(periodoDisponivel);
					switch (periodoDisponivel) {
					case MATUTINO:
						periodoDisponivel = Periodo.VESPERTINO;
						break;
					case VESPERTINO:
						periodoDisponivel = Periodo.MATUTINO;
						break;
					}
				} else {
					sala.setStatus(SalaStatus.CHEIO);
				}
			else
				sala.setStatus(SalaStatus.CHEIO);
		}

		if(!result.isEmpty()) {
			disciplina.setQuantidadeAulaFaltaAlocar(disciplina.getQuantidadeAulaFaltaAlocar() - result.size());	
			Aula aulaSala = null;
			Aula aulaProfessor = null;
			for (Integer index : result) {
				aulaProfessor = disciplina.getProfessor().getHorario().get(index);
				aulaSala = sala.getListaAula().get(index);

				aulaProfessor.setDisciplina(disciplina);
				aulaSala.setDisciplina(disciplina);

				aulaProfessor.setAulaDisponivel(false);
				aulaSala.setAulaDisponivel(false);

				periodoDisponivel = aulaSala.getPeriodo();
			}					
		} else {
			sala.setStatus(SalaStatus.CHEIO);
		}

	}

	public List<Curso> getTudo() {
		return tudo;
	}

	public void setTudo(List<Curso> tudo) {
		this.tudo = tudo;
	}

	public List<Serie> getListaSerie() {
		return listaSerie;
	}

	public void setListaSerie(List<Serie> listaSerie) {
		this.listaSerie = listaSerie;
	}

	public List<Curso> getListaCurso() {
		return listaCurso;
	}

	public void setListaCurso(List<Curso> listaCurso) {
		this.listaCurso = listaCurso;
	}

	public List<Disciplina> getListaDisciplina() {
		return listaDisciplina;
	}

	public void setListaDisciplina(List<Disciplina> listaDisciplina) {
		this.listaDisciplina = listaDisciplina;
	}

	public List<Sala> getListaSala() {
		return listaSala;
	}

	public void setListaSala(List<Sala> listaSala) {
		this.listaSala = listaSala;
	}

	public List<Professor> getHorarioProf() {
		return horarioProf;
	}

	public void setHorarioProf(List<Professor> horarioProf) {
		this.horarioProf = horarioProf;
	}

	public Integer getQtd_dias() {
		return qtd_dias;
	}

	public void setQtd_dias(Integer qtdDias) {
		qtd_dias = qtdDias;
	}

	public Integer getQtd_aulas() {
		return qtd_aulas;
	}

	public void setQtd_aulas(Integer qtdAulas) {
		qtd_aulas = qtdAulas;
	}

}
