package ia.infra.algoritmo;

import ia.infra.negocio.curso.Curso;
import ia.infra.negocio.horario.Atividade;
import ia.infra.negocio.horario.HorarioSerie;
import ia.infra.negocio.horario.ModeloHorario;
import ia.infra.negocio.sala.Sala;
import ia.io.horario.ParserHorario;

import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFileChooser;
/**
 *  requisitos: Lista de Horarios, lista de cursos e lista de salas preenchidos
 * @author Shizue, Adriano e Gizeli
 *
 */
public class Ensalamento {

	private Vector<Sala> listaSala;//listas das salas 
	private Vector<HorarioSerie>  listaHorario;//lista dos horarios
	private Vector<Curso> estruturaToda;//estrutura: Curso-->Series-->Disciplinas
	private ArrayList<ResultaConflitos> listaConflitosResto;//lista dos conflitos das alocações 

	public Ensalamento(Vector<Sala> listaSala,
			Vector<HorarioSerie> listaHorario,
			Vector<Curso> estruturaToda) {
		super();
		this.listaSala = listaSala;
		this.listaHorario = listaHorario;
		this.estruturaToda = estruturaToda;
		System.out.println("ensalamento carrega dados iniciais.");
		
		ArrayList<AgrupamentoAula> resto = new ArrayList<AgrupamentoAula>();
		int conflito=0;
		alocarDuasSeries(0,5,6,resto, conflito);
		ResultaConflitos result = new ResultaConflitos(conflito,resto, listaHorario.get(0).getCurso().getNome(), listaHorario.get(0).getSemestre());
		listaConflitosResto.add(result);
	}

	public void alocarDuasSeries(int idSala, int serie1, int serie2, ArrayList<AgrupamentoAula> resto, int confl) {
		/*********Aloca primeiro horario para a primeira sala de aula ******/
		//ArrayList<AgrupamentoAula> agrupa = agruparHorarios(listaHorario.get(5));
		System.out.println("agrupado Horario");
		criarNovoHorario (idSala, serie1);//cria horario na sala 
		/**************************end aloca********************************/
		int conflito=0;

		ArrayList<AgrupamentoAula> agrupaHorario = agruparHorarios(listaHorario.get(serie2));
		//ArrayList<AgrupamentoAula> agrupaSala = agruparHorarios(listaSala.get(idSala).getListaHorario().get(0));
		HorarioSerie horarioSerieSala = new HorarioSerie();
		horarioSerieSala = listaSala.get(idSala).getListaHorario().get(0);
		HorarioSerie horarioSerieHorario = new HorarioSerie();
		horarioSerieHorario = listaHorario.get(serie2);

		for (int k = 0; k < agrupaHorario.size(); k++) {
			int vazios = encontreVazios(listaSala.get(idSala).getListaHorario().get(0).getSemana(), agrupaHorario.get(k).getDiaSemana(), agrupaHorario.get(k).getHoraAula());
			if (vazios >= agrupaHorario.get(k).getTamanho()) {
				for (int l = 0; l < agrupaHorario.get(k).getTamanho(); l++) {
					horarioSerieSala.getSemana()[agrupaHorario.get(k).getHoraAula()+l][agrupaHorario.get(k).getDiaSemana()].setNome(agrupaHorario.get(k).getDisciplina());
				}
				agrupaHorario.remove(k);
				k--;
			}else{
				conflito++;
			}
		}
		resto = agrupaHorario;
		confl = conflito;
	}

	public int encontreVazios(Atividade[][] horario, int diaSemana, int hora){
		int count = 0;
		ModeloHorario modeloHorario = new ModeloHorario();
		for (int i=hora; i<modeloHorario.getAtividades().size(); i++) {
			if ((i==2)||(i==7)||(i==10)) {
			}else{
				if (horario[i][diaSemana].getNome().equals("")) {
					count++;
				}else{
					return count;
				}
			}
		}
		return count;
	}

	public void criarNovoHorario (int idSala, int serie1){
		/*********Cria um horario para a sala******************/
		HorarioSerie horarioSerie = new HorarioSerie();
		horarioSerie.setSemestre(listaHorario.get(serie1).getSemestre());
		horarioSerie.setCurso(listaHorario.get(serie1).getCurso());
		horarioSerie.setSerie(listaHorario.get(serie1).getSerie());
		/****************end cria*************************************/
		horarioSerie = listaHorario.get(serie1);
		listaSala.get(idSala).getListaHorario().add(horarioSerie);
		System.out.println("Primeiro horario OK");
	}

	public ArrayList<AgrupamentoAula> agruparHorarios(HorarioSerie horario){
		ArrayList<AgrupamentoAula> listaAgrupamentoAula = new ArrayList<AgrupamentoAula>();//Agrupamento aula -- bloco das aulas juntas
		ModeloHorario modeloHorario = new ModeloHorario();
		/***********Alocar horario da aula**************/
		Atividade[][] matrixHorario = new Atividade[modeloHorario.getAtividades().size()][6];
		for (int i=0; i<modeloHorario.getAtividades().size(); i++) {
			for (int j=0; j<6; j++) {
				matrixHorario[i][j] = (Atividade)((new ModeloHorario()).getAtividades().toArray())[i];
				matrixHorario[i][j].setOrdem(-1);}}
		/**********************************************/
		matrixHorario = horario.getSemana();

		for (int i = 0; i < 6; i++) {
			String disciplina = "";
			int countDisciplina = 0, primeiraAula = 0;
			for (int j = 0; j < modeloHorario.getAtividades().size(); j++) {
				if ((j==2)||(j==7)||(j==10)) {
					//					listaAgrupamentoAula.add(new AgrupamentoAula(disciplina,countDisciplina,horario.getCurso().getNome(), String.valueOf(horario.getSerie().getOrdem()), i));
					//					countDisciplina = 0;
					//					disciplina = "";
				}else{
					if ((disciplina.equals(""))&&(!(matrixHorario[j][i].getNome().equals("")))) {
						disciplina = matrixHorario[j][i].getNome();
						countDisciplina++;
						primeiraAula = j;
					}else{
						if ((disciplina.equals(matrixHorario[j][i].getNome()))) {
							countDisciplina++;
							if (disciplina.equals("")) {
								countDisciplina = 0;
							}
						}else{
							listaAgrupamentoAula.add(new AgrupamentoAula(disciplina,countDisciplina,horario.getCurso().getNome(), String.valueOf(horario.getSerie().getId()), i, primeiraAula));
							if (matrixHorario[j][i].getNome().equals("")) {
								countDisciplina = 0;
								disciplina ="";

							}else{
								countDisciplina = 1;
								disciplina =  matrixHorario[j][i].getNome();
								primeiraAula = j;
							}
						}
					}
				}
			}
		}
		return listaAgrupamentoAula;
	}

	public void agruparCurso(Vector<HorarioSerie> listaHorarios){
		
	}
	
	/**
	 * Ainda nao utilizada pois tem q terminar uma parte do parser 
	 * @throws Exception
	 */
	public void auxAlocaHorariosCursos() throws Exception{
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.CANCEL_OPTION) {

		} else {
			File file = new File(fileChooser.getSelectedFile().getPath());
			listaHorario = new Vector<HorarioSerie>();
			ParserHorario ph = new ParserHorario(file);
			ph.readFile();
			//	listaHorario = ph.getListaHorario();
		}
	}
}
