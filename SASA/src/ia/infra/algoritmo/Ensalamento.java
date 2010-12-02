package ia.infra.algoritmo;

import ia.infra.negocio.curso.Curso;
import ia.infra.negocio.horario.Atividade;
import ia.infra.negocio.horario.HorarioSerie;
import ia.infra.negocio.horario.ModeloHorario;
import ia.infra.negocio.sala.Sala;
import ia.io.horario.ParserHorario;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
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
	private ArrayList<ArrayList<HorarioSerie>> listaHorarioCursos;

	public Ensalamento(Vector<Sala> listaSala,
			Vector<HorarioSerie> listaHorario,
			Vector<Curso> estruturaToda) {
		super();
		this.listaSala = listaSala;
		this.listaHorario = listaHorario;
		this.estruturaToda = estruturaToda;
		this.listaConflitosResto = new ArrayList<ResultaConflitos>();
		System.out.println("ensalamento carrega dados iniciais.");


		//ArrayList<Integer> listaColisao = new ArrayList<Integer>();
		
		int serie1=0,serie2=0;
		listaHorarioCursos = agruparCurso(this.listaHorario);
		//ArrayList<AgrupamentoAula> resto = new ArrayList<AgrupamentoAula>();
		//int conflito=0;
//		Vector<Sala> listSala = new Vector<Sala>();
//		HorarioSerie horario = new HorarioSerie();
//		ArrayList<HorarioSerie> auxlistaSala = new ArrayList<HorarioSerie>();
//		auxlistaSala.add(horario);
//		resto = alocarDuasSeries(listaSala, listaHorarioCursos.get(1),0,0,1);
		Vector<Sala> listSala = new Vector<Sala>();
		listSala= (Vector<Sala>) listaSala.clone();
		int indiceSala = 0;
		for (int i = 0; i < listaHorarioCursos.size(); i++) {
			ArrayList<HorarioSerie> listaHoraLocal = listaHorarioCursos.get(i);
			//if (listaHoraLocal.get(0).getSemana()[0][0].getNome()!= "") {
				for (int j = 0; j < listaHoraLocal.size(); j++) {
					ArrayList<AgrupamentoAula> restoMenor = new ArrayList<AgrupamentoAula>();
					for (int j2 = j+1; j2 < listaHoraLocal.size(); j2++) {
						ArrayList<AgrupamentoAula> resto = new ArrayList<AgrupamentoAula>();
						resto = alocarDuasSeries(listSala,listaHoraLocal,0,j,j2);
						if (resto.size()!=0) {
							if (restoMenor.size()==0) {
								restoMenor = resto;
								serie1 = j;
								serie2 = j2;	
							}						
							int rest = Math.min(resto.size(), restoMenor.size());
							if (rest == resto.size()) {
								restoMenor = resto;
								serie1 = j;
								serie2 = j2;
							}
						}
					}
					restoMenor = alocarDuasSeries(this.listaSala, listaHoraLocal, indiceSala, serie1, serie2);
					
					listaHoraLocal.remove(serie1);
					listaHoraLocal.remove(serie2);
					indiceSala++;
					j--;
				}
			//}
		}
		System.out.println("funfou?");
	}

	public ArrayList<AgrupamentoAula> alocarDuasSeries(Vector<Sala> listaSala, ArrayList<HorarioSerie> listahorariopar, int idSala, int serie1, int serie2) {
		/*********Aloca primeiro horario para a primeira sala de aula ******/
		//ArrayList<AgrupamentoAula> agrupa = agruparHorarios(listaHorario.get(5));
		System.out.println("agrupado Horario");
		criarNovoHorario (listahorariopar, idSala, serie1);//cria horario na sala 
		/**************************end aloca********************************/
		int conflito=0;

		ArrayList<AgrupamentoAula> agrupaHorario = agruparHorarios(listahorariopar.get(serie2));
		//ArrayList<AgrupamentoAula> agrupaSala = agruparHorarios(listaSala.get(idSala).getListaHorario().get(0));
		HorarioSerie horarioSerieSala = new HorarioSerie();
		horarioSerieSala = listaSala.get(idSala).getListaHorario().get(0);
		HorarioSerie horarioSerieHorario = new HorarioSerie();
		horarioSerieHorario = listahorariopar.get(serie2);

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
		return agrupaHorario;
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

	public void criarNovoHorario (ArrayList<HorarioSerie> listahorariopar, int idSala, int serie1){
		/*********Cria um horario para a sala******************/
		HorarioSerie horarioSerie = new HorarioSerie();
		horarioSerie.setSemestre(listahorariopar.get(serie1).getSemestre());
		horarioSerie.setCurso(listahorariopar.get(serie1).getCurso());
		horarioSerie.setSerie(listahorariopar.get(serie1).getSerie());
		/****************end cria*************************************/
		horarioSerie = listahorariopar.get(serie1);
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

	public ArrayList<ArrayList<HorarioSerie>> agruparCurso(Vector<HorarioSerie> listaHorarios){
		String curso = "";
		ArrayList<ArrayList<HorarioSerie>> result = new ArrayList<ArrayList<HorarioSerie>>();
		ArrayList<HorarioSerie> listaHorarioCurso = null;

		for (HorarioSerie horarioSerie : listaHorarios) {
			if (curso.equals("")) {
				curso = horarioSerie.getCurso().getNome();
				listaHorarioCurso = new ArrayList<HorarioSerie>();
			}
			if (horarioSerie.getCurso().getNome().equals(curso)) {
				listaHorarioCurso.add(horarioSerie);

			}else{
				curso = horarioSerie.getCurso().getNome();
				result.add(listaHorarioCurso);
				listaHorarioCurso = new ArrayList<HorarioSerie>();
				listaHorarioCurso.add(horarioSerie);
			}
		}
		//adicionando o ultimo curso selecionado.
		result.add(listaHorarioCurso);
		return result;
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
