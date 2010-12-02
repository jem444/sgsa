package ia.infra.algoritmo;

import ia.infra.negocio.horario.Atividade;
import ia.infra.negocio.horario.DiaSemana;
import ia.infra.negocio.horario.HorarioSerie;
import ia.infra.negocio.horario.ModeloHorario;
import ia.infra.negocio.sala.Sala;
import ia.io.horario.ParserHorario;
import ia.io.sala.ParserSala;

import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFileChooser;

public class Ensalamento {

	private Vector<Sala> listaSala;
	private ArrayList<HorarioSerie>  listaHorario;
	private ArrayList<AgrupamentoAula> listaAgrupamentoAula ;

	public void profundidade() throws Exception{
		System.out.println("Curso");
		auxAlocaHorariosCursos();
		System.out.println("Salas");
		auxAlocaSalas();
		


	}

	public void agruparHorarios(HorarioSerie horario){
		ModeloHorario modeloHorario = new ModeloHorario();
		this.listaAgrupamentoAula = new ArrayList<AgrupamentoAula>();
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
			int countDisciplina = 0;
			for (int j = 0; j < modeloHorario.getAtividades().size(); j++) {
				if ((j==2)||(j==7)||(j==10)) {
//					listaAgrupamentoAula.add(new AgrupamentoAula(disciplina,countDisciplina,horario.getCurso().getNome(), String.valueOf(horario.getSerie().getOrdem()), i));
//					countDisciplina = 0;
//					disciplina = "";
					}else{
					if ((disciplina.equals(""))&&(!(matrixHorario[j][i].getNome().equals("")))) {
						disciplina = matrixHorario[j][i].getNome();
						countDisciplina++;
					}else{
						if ((disciplina.equals(matrixHorario[j][i].getNome()))) {
							countDisciplina++;
							if (disciplina.equals("")) {
								countDisciplina = 0;
							}
						}else{
							listaAgrupamentoAula.add(new AgrupamentoAula(disciplina,countDisciplina,horario.getCurso().getNome(), String.valueOf(horario.getSerie().getId()), i));
							if (matrixHorario[j][i].getNome().equals("")) {
								countDisciplina = 0;
								disciplina ="";
							}else{
								countDisciplina = 1;
								disciplina =  matrixHorario[j][i].getNome();
							}
						}
					}
				}
			}
		}
	}

	public void auxAlocaSalas() throws Exception{
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.CANCEL_OPTION) {

		} else {
			File file = new File(fileChooser.getSelectedFile().getPath());
			listaSala = new Vector<Sala>();
			ParserSala ps = new ParserSala(file);
			listaSala = ps.getSalas();
		}
	}

	public void auxAlocaHorariosCursos() throws Exception{
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.CANCEL_OPTION) {

		} else {
			File file = new File(fileChooser.getSelectedFile().getPath());
			listaHorario = new ArrayList<HorarioSerie>();
			ParserHorario ph = new ParserHorario(file);
			ph.readFile();
			listaHorario = ph.getListaHorario();
		}
	}
}
