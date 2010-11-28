package ia.io.horario;

import ia.infra.negocio.curso.Curso;
import ia.infra.negocio.curso.Disciplina;
import ia.infra.negocio.curso.Professor;
import ia.infra.negocio.curso.Serie;
import ia.infra.negocio.horario.Atividade;
import ia.infra.negocio.horario.Aula;
import ia.infra.negocio.horario.HorarioSerie;
import ia.infra.negocio.horario.ModeloHorario;
import ia.infra.negocio.sala.Sala;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import csv.impl.CSVReader;

public class ParserHorario{
	private File file;
	//private HorarioSerie horarioSerie;


	private ArrayList<String> listaDados = new ArrayList<String>();
	private HashMap<String,Disciplina> listaDisciplina = new HashMap<String, Disciplina>();
	//private HashMap<String, HorarioAula> listaHorario = new HashMap<String, HorarioAula>();
	private ArrayList<String> lista = new ArrayList<String>();
	private ArrayList<HashMap<Integer, HashMap<Integer, String>>> listaHorarios = new ArrayList<HashMap<Integer, HashMap<Integer, String>>>();
	int qtdHorario;
	private ArrayList<HorarioSerie> listaHorario = new ArrayList<HorarioSerie>();

	public ParserHorario(File filepath){
		this.file = filepath;
		//this.horarioSerie = new HorarioSerie();
		this.listaHorario = new ArrayList<HorarioSerie>();
	}



	public boolean readFile() throws Exception{
		CSVReader reader = null;
		boolean result = false;
		int isNull=0;

		try {
			reader = new CSVReader(this.file);
		} catch (FileNotFoundException e) {
			result = false;
			e.printStackTrace();
		}
		String [] nextLine;
		qtdHorario = 0;
		int ishorario = 0;
		int numeroAula = 0;
		//int auxHorario = 0;//auxilia na hora de alocar no horario certo
		HorarioSerie horarioSerie= null;
		Serie serie = null;
		Curso curso = null;
		int controleSemana = 0;
		boolean isnovo = false;
		while ((nextLine = reader.next()) != null)//loop na linha
		{
			int diaSemana = 0;

			Disciplina disciplina = new Disciplina(null);
			int cargaHoraria = 0;//Variavel parar ler as disciplinas
			int abbrLido = 0;//Variavel parar ler as disciplinas
			int countHora = 0;//variavel parar pegar o horario da aula

			for (int i = 0; i < nextLine.length; i++) {//loop da linha
				System.out.println(nextLine[i]);
				listaDados.add(nextLine[i]);
				int countCH=0;
				if (nextLine[i]!="CH") {
					if ((nextLine[i]!= "")&&(nextLine[i]!=null)) {//verificar se o daddo não é nulo ou vazio
						char c = nextLine[i].charAt(0);
						if (c>=0X30 && c<=0X39){//verificar se é um numero
							String[] numeroHora = nextLine[i].split(":");//verifica se eh um horario
							if (numeroHora.length>1) {

								if (countHora>2) {//comecou uma nova horario de aula
									countHora = 0;
								}
								countHora++;
								if (countHora==2) {//horario de termino
									controleSemana++;
									if ((controleSemana>2)) {
//										if (isnovo) {
//											controleSemana=1;
//											ishorario++;
//											//ishorario = listaHorario.size()-1;
//										}else{
											controleSemana=1;
											ishorario--;
											//ishorario = listaHorario.size()-2;
											numeroAula++;
									//	}
									}else{
										diaSemana = 0;
										ishorario++;
										//ishorario = listaHorario.size()-1;
									}
								}
								if(isnovo){
									isnovo = false;
									ishorario = listaHorario.size()-2;
									numeroAula = 0;
									controleSemana=0;
								}
							}else{
								String[] semestre = nextLine[i].split("º");//novo semestre para alocar novo horario
								if (semestre.length>1) {
									horarioSerie = new HorarioSerie();
									horarioSerie.setSemestre(Integer.parseInt(semestre[0]));
									qtdHorario++;
//									if (qtdHorario==4) {
//										qtdHorario = 0;
//									}
									isnovo = true;
									horarioSerie.setCurso(curso);
									horarioSerie.setSerie(serie);
									listaHorario.add(horarioSerie);
								}else{
									//cargaHoraria = Integer.parseInt(nextLine[i]);
								}
								
								countHora=0;
							}
						}else{
							if (countHora == 2) {
								countHora++;
							}
							if(i==0){
								String[] cursoSerie = nextLine[i].split(" ");//curso especificado no arquivo esta curso e serie no msm campo
								curso = new Curso(cursoSerie[0]);
								serie = new Serie(Integer.parseInt(cursoSerie[1]));
							}
							if(cargaHoraria!=0){
								abbrLido++;
								alocaCargaHoraria(nextLine[i], disciplina, abbrLido);
							}}
					}else
						if (countHora == 2) {
							countHora++;
						}
					if ((countHora>2)&&(nextLine[i]!=null)) {
						if (countHora<9) {
							if ((numeroAula==2)||(numeroAula==7)||(numeroAula==10)) {
								numeroAula++;
							}
							if (diaSemana>=0&& diaSemana<7) {
								alocaDisciplinaHorario(nextLine[i], ishorario-1, numeroAula, diaSemana, countHora);
								countHora++;
								diaSemana++;
								if (nextLine[i+1]==null) {//verificar se nao tem mais disciplinas no horario porem não foi preenchido todos os horarios
									for (int j = diaSemana; j < 6; j++) {
										listaHorario.get(ishorario+1).getSemana()[numeroAula][diaSemana+1].setNome("");
										System.out.println("aloca nada");
										countHora++;
										diaSemana++;
									}	
								}
							}else{
								//diaSemana = 0;
								//ishorario++;
							}
							//							System.out.println("\n numAula: "+numeroAula);
							//							System.out.println("diasemana: "+diaSemana);
							//							System.out.println("ishario: "+ishorario+"\n ");

							//							if (((numeroAula%2)==0)&&
							//									(numeroAula<=30)&&
							//									(diaSemana==6)) {
							//								ishorario--;
							//								numeroAula++;
							//							}
						}	
					}
					if (nextLine[i]==null) {
						isNull++;
						System.out.println("linha: "+isNull);
						if (isNull>=213) {
							result = true;
							return result;
						}
					}
					
				}else{
					countCH++;
					if (countCH > 4 ) {
						result = true;
						return result;
					}
				}
				
			}
		}
		return result;
	}
	/**
	 * 
	 * @param next[i]
	 * @param ishorario
	 * @param numeroAula
	 * @param diaSemana
	 * @param countHora
	 */
	public void alocaDisciplinaHorario(String discplina, int ishorario, int numeroAula, int diaSemana, int countHora){
		listaHorario.get(ishorario).getSemana()[numeroAula][diaSemana].setNome(discplina);//adiciona na lista de horarios

		Disciplina disc =new Disciplina(discplina);
		Atividade  ativ = listaHorario.get(ishorario).getSemana()[numeroAula][diaSemana];
		Aula aula = new Aula(ativ.getOrdem(),ativ.getInicio(), ativ.getTermino());

		this.listaDisciplina.put(discplina, disc);
		listaDisciplina.get(discplina).getAulas().add(aula);
		lista.add(discplina);
		System.out.println("aloca");
	}

	public void alocaCargaHoraria(String disc, Disciplina disciplina,int abbrLido) {
		switch (abbrLido) {
		case 1:
			disciplina = listaDisciplina.get(disc);
			disciplina.setNomeAbbr(disc);
			System.out.println("disc1");
			break;
		case 2:
			disciplina.setNome(disc);
			System.out.println("disc2");
			break;
		case 3:
			Professor professor = new Professor(disc);
			disciplina.setProfessor(professor);
			System.out.println("disc3");
			break;
		default:
			abbrLido = 0;
			System.out.println("disc0");
			break;
		}
	}


	public void alocarHorario(Vector<Curso> cursos, Vector<Sala> salas) {

		ModeloHorario modeloHorario = new ModeloHorario();
		//this.semana = new Atividade[0][7];
		//instancia todos os dias da semana, de segunda a sexta, com uma lista de atividades obtidas do modelo de horario
		Atividade[][] semana = new Atividade[modeloHorario.getAtividades().size()][6];
		for (int i=0; i<modeloHorario.getAtividades().size(); i++) {
			for (int j=0; j<6; j++) {
				semana[i][j] = (Atividade)((new ModeloHorario()).getAtividades().toArray())[i];
				semana[i][j].setOrdem(-1);
			}
		}
	}
	public ArrayList<String> getListaDados() {
		return listaDados;
	}
	public void setListaDados(ArrayList<String> listaDados) {
		this.listaDados = listaDados;
	}
	public ArrayList<HashMap<Integer, HashMap<Integer, String>>> getListaHorarios() {
		return listaHorarios;
	}
	public void setListaHorarios(
			ArrayList<HashMap<Integer, HashMap<Integer, String>>> listaHorarios) {
		this.listaHorarios = listaHorarios;
	}
	public ArrayList<String> getLista() {
		return lista;
	}
	public void setLista(ArrayList<String> lista) {
		this.lista = lista;
	}
	public int getQtdHorario() {
		return qtdHorario;
	}
	public void setQtdHorario(int qtdHorario) {
		this.qtdHorario = qtdHorario;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}



	public HashMap<String, Disciplina> getListaDisciplina() {
		return listaDisciplina;
	}

	public void setListaDisciplina(HashMap<String, Disciplina> listaDisciplina) {
		this.listaDisciplina = listaDisciplina;
	}

}
