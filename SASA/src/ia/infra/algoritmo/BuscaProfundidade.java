package ia.infra.algoritmo;

import ia.infra.negocio.curso.Curso;
import ia.infra.negocio.curso.Disciplina;
import ia.infra.negocio.curso.Serie;
import ia.infra.negocio.horario.Atividade;
import ia.infra.negocio.horario.Aula;
import ia.infra.negocio.horario.Intervalo;
import ia.infra.negocio.horario.ModeloHorario;
import ia.infra.negocio.sala.Sala;

import java.awt.Point;
import java.util.Vector;

public class BuscaProfundidade {
	private Vector<Curso> cursos;
	private Vector<Sala>  salas;
	private ModeloHorario modeloHorario;
	private Atividade[][] semana;
	


	public BuscaProfundidade(Vector<Curso> cursos, Vector<Sala> salas) {
		this.cursos = cursos;
		this.salas  = salas;
		
		this.modeloHorario = new ModeloHorario();
		//this.semana = new Atividade[0][7];
		
		//instancia todos os dias da semana, de segunda a sexta, com uma lista de atividades obtidas do modelo de horario
		this.semana = new Atividade[modeloHorario.getAtividades().size()][5];
		for (int i=0; i<modeloHorario.getAtividades().size(); i++) {
			for (int j=0; j<5; j++) {
				this.semana[i][j] = (Atividade)((new ModeloHorario()).getAtividades().toArray())[i];
				this.semana[i][j].setOrdem(-1);
			}
		}
	}

	public Vector<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Vector<Curso> cursos) {
		this.cursos = cursos;
	}
	
	public Vector<Sala> getSalas() {
		return salas;
	}

	public void setSalas(Vector<Sala> salas) {
		this.salas = salas;
	}

	public ModeloHorario getModeloHorario() {
		return modeloHorario;
	}
	
	public Vector<Disciplina> getTodasDisciplinas() {
		Vector<Disciplina> disciplinas = new Vector<Disciplina>();
		//Captura todas as disciplinas por referencia
		for (Curso curso : cursos) {
			for (Serie serie : curso.getSeries()) {
				for (Disciplina disciplina : serie.getDisciplinas()) {
					disciplinas.add(disciplina);
				}
			}
		}
		
		return disciplinas;
	}

	public void setModeloHorario(ModeloHorario modeloHorario) {
		this.modeloHorario = modeloHorario;
	}

	/**
	 * Aplica um algoritmo de busca com o intuito de encontrar/montar um horario
	 * @return 
	 */
	public Vector<Curso> busca2() {
		Vector<Disciplina> disciplinas = this.getTodasDisciplinas();
		Sala sala =  this.salas.get(0); //substiruir por uma heuristica de salaS
		System.out.println(alocaDisciplinas(disciplinas, sala, semana));
		
		return cursos;
	}
	
	public boolean alocaDisciplinas(Vector<Disciplina> disciplinas, Sala sala, Atividade[][] semana) {
		if (disciplinas.size() == 0)
			return true;
		
		Disciplina disciplina = disciplinas.remove(0);


		//tenta alocar as aulas:
		if (alocaAula(disciplina.getAulas().size(), disciplina) && alocaDisciplinas(disciplinas, sala, semana)) {
			disciplina.setSala(sala);
			return true;

			//sen�o n�o deu certo e desaloca reconstruindo a lista:
		} else {
			desaloca(disciplina, semana);
			disciplinas.add(0, disciplina);
			return false;
		}
	}

        /***
         * Desfaz o processo de alocação de uma disciplina e suas aulas em uma semana dada;
         * @param disciplina
         * @param semana
         */
	private void desaloca(Disciplina disciplina, Atividade[][] semana) {
		for (int i=0; i < disciplina.getAulas().size(); i++) {
			Aula aula = disciplina.getAulas().remove(i);
			aula.setOrdem(-5);
			
			disciplina.getAulas().add(i, new Aula());
				
		}
	}

        /***
         *  Faz o processo de verificação da carga horaria de uma disciplina e chama
         * recursivamente o processo de alocaçao e verificação, com o intuito de alocar as partes menores
         * no horario.
         * @param quantidade
         * @param disciplina
         * @return
         */
	public boolean alocaAula(int quantidade, Disciplina disciplina) {
		System.out.println(disciplina.getNome()+" = "+quantidade);
		
		if (quantidade > 5) {
			//considera que nao existam disciplinas com carga horaria semana maior que 5 aulas
			return false;
		}
		
		if (quantidade == 5) { 
			if (alocaAula(3, disciplina) && alocaAula(2, disciplina))		
				return true;
			if (alocaAula(2, disciplina) && alocaAula(3, disciplina))		
				return true;
		}
		
		if (quantidade == 4) { 
			if (alocaAula(2, disciplina) && alocaAula(2, disciplina))		
				return true;
		}

//		if ((quantidade == 3) || (quantidade == 2)) { //aloca sozinho

		//para cada dia da semana:
		for (int dia=0; dia<5; dia++) {
			
			//se o dia atual possui "quantidade" aulas vagas
			int k = disponivel(semana, dia); //retorna quantos espa�os dispon�veis neste dia
			
			if (k >= quantidade) {
				//Aula aula = new Aula();
				
				for (int j=0; j<quantidade; j++) {
					for (Aula aux : disciplina.getAulas()) {
						if (aux.getOrdem() < 0) {
							aux  = pop(semana, dia);
							break;
						}
					}
				}
				
				return true;
			}
		}

		return false;
	}

        /***
         * Verifica a quantidade de atividades disponiveis em um dia
         * @param semana
         * @param dia
         * @return
         */
	private int disponivel(Atividade[][] semana, int dia) {
		int fuuu = 0;
		for (int i=0; i<semana.length; i++) {
			if ((semana[i][dia].getOrdem() < 0) && (semana[i][dia].getClass().equals(Aula.class)))
				fuuu++;
		}
		
		return fuuu;
	}

        /***
         * Reteria uma Aula do horario e retorna ela
         * @param semana
         * @param dia
         * @return Aula
         */
	private Aula pop(Atividade[][] semana, int dia){
		int k = 0;
		while ( (semana[k][dia].getOrdem() >= 0)  || (!semana[k][dia].getClass().equals(Aula.class)) ) {
			k++;
			if (k >= semana.length-1)
				break;
		}
		
		Aula aula = null;
		if(!semana[k][dia].getClass().equals(Intervalo.class)){
			aula = (Aula)semana[k][dia];
			semana[k][dia].setOrdem(k);
			System.out.println("pop(semana, dia = " + dia + ") = "+aula.getOrdem());
		}
		
		return aula;
	}

	public Atividade[][] getSemana() {
		return semana;
	}

	public void setSemana(Atividade[][] semana) {
		this.semana = semana;
	}


	
}
                                            