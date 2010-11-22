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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
		HorarioSerie horarioSerie= null;
		Serie serie = null;
		Curso curso = null;
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
							String[] numeroHora = nextLine[i].split(":");
							if (numeroHora.length>1) {
								if (countHora>2) {
									countHora = 0;
								}
								countHora++;
								if (countHora==2) {
									diaSemana = 0; 
									numeroAula++;
								}
							}else{
								horarioSerie = new HorarioSerie();
								String[] semestre = nextLine[i].split("º");
								if (semestre.length>1) {
									horarioSerie.setSemestre(Integer.parseInt(semestre[0]));
									qtdHorario++;
									if (qtdHorario==4) {
										qtdHorario = 0;
									}
								}else{
									cargaHoraria = Integer.parseInt(nextLine[i]);
								}
								
								horarioSerie.setCurso(curso);
								horarioSerie.setSerie(serie);
								listaHorario.add(horarioSerie);
								countHora=0;
							}
						}else{
							
							if (countHora == 2) {
								countHora++;
							}
							if(i==0){
								
								String[] cursoSerie = nextLine[i].split(" ");
								curso = new Curso(cursoSerie[0]);
								//horarioSerie.setCurso(curso);
								serie = new Serie(Integer.parseInt(cursoSerie[1]));
								//horarioSerie.setSerie(serie);
							}
							if(cargaHoraria!=0){
								abbrLido++;
								switch (abbrLido) {
								case 1:
									disciplina = listaDisciplina.get(nextLine[i]);
									disciplina.setNomeAbbr(nextLine[i]);
									System.out.println("disc1");
									break;
								case 2:
									disciplina.setNome(nextLine[i]);
									System.out.println("disc2");
									break;
								case 3:
									Professor professor = new Professor(nextLine[i]);
									disciplina.setProfessor(professor);
									System.out.println("disc3");
									break;
								default:
									abbrLido = 0;
									System.out.println("disc0");
									break;
								}

							}}
					}else
						if (countHora == 2) {
							countHora++;
						}
					if ((countHora>2)&&(nextLine[i]!=null)) {
						if (countHora<9) {
							if ((numeroAula==3)||(numeroAula==8)||(numeroAula==10)) {
								diaSemana++;
							}
							if (diaSemana>=0&& diaSemana<7) {
								//Atividade atividade = new
								//Disciplina disciplina = new Disciplina();
								//disciplina.setNmDisciplina(nextLine[i]);
								listaHorario.get(ishorario).getSemana()[numeroAula-1][diaSemana].setNome(nextLine[i]);//adiciona na lista de horarios

								Disciplina disc =new Disciplina(nextLine[i]);
								Atividade  ativ = listaHorario.get(ishorario).getSemana()[numeroAula-1][diaSemana];
								Aula aula = new Aula(ativ.getOrdem(),ativ.getInicio(), ativ.getTermino());
					
							//	disc.getAulas().add(aula);//adiciona na lista de diciplinas

								this.listaDisciplina.put(nextLine[i], disc);
								listaDisciplina.get(nextLine[i]).getAulas().add(aula);
								lista.add(nextLine[i]);

								System.out.println("aloca");
								countHora++;
								diaSemana++;
							}else{
								//listaHorario.add(horarioLocal);
								diaSemana = 0;
								ishorario++;
							}
							if (diaSemana==7) {
								ishorario++;
							}
							
						}	
					}
					if (nextLine[i]==null) {
						isNull++;
						System.out.println(isNull);
						//					if (isNull==139) {
						//						result = true;
						//						return result;
						//					}
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
