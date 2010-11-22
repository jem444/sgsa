package ia.gui.view;

import ia.gui.infra.Erro;
import ia.infra.negocio.curso.Curso;
import ia.infra.negocio.curso.Disciplina;
import ia.infra.negocio.curso.Serie;
import ia.infra.negocio.sala.Sala;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GridDisciplinaInternalFrame extends JInternalFrame {

	private Vector<Curso> cursos;
	private JTable tabela;
	private Object[][] linhas;
	private String[] colunas;
	
	public GridDisciplinaInternalFrame(Vector<Curso> cursos, String title, boolean a, boolean b, boolean c, boolean d) {
		super(title, a, b, c, d);
		this.cursos = cursos;
		
		colunas = new String []{"ID", "Curso", "Disciplina", "Professor", "Matriculados", "Aulas/Semana"};
		
		tabela = new JTable(gerarLinhas(linhas), colunas);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(new JScrollPane(tabela), "Center");
		
		this.setSize(400, 600);

	}

	public Vector<Curso> getCursos() {
		return cursos;
	}

	public void setDisciplinas(Vector<Curso> cursos) {
		this.cursos = cursos;
		tabela = new JTable(gerarLinhas(linhas), colunas);
	}
	
	public Object[][] gerarLinhas(Object[][] linhas) {
		try {
			//conta qntas linhas
			int i = 0;
			for (Curso curso : cursos) {
				for (Serie serie : curso.getSeries()) {
						i+= serie.getDisciplinas().size();
				}
			}

			//preenche as linhas
			linhas = new Object[i][6];
			
			int k = 0;
			for (Curso curso : cursos) {
				for (Serie serie : curso.getSeries()) {
					for (Disciplina disciplina: serie.getDisciplinas()) {
						linhas[k][0] = disciplina.getId();
						linhas[k][1] = curso.getNome();
						linhas[k][2] = disciplina.getNome();
						linhas[k][3] = disciplina.getProfessor().getNome();
						//linhas[k][3] = disciplina.getProfessor().getNome();
						linhas[k][4] = disciplina.getNumeroDeMatriculados();
						linhas[k][5] = disciplina.getAulas().size();
						k++;
					}
				}
			}
			
		} catch (NullPointerException e) {
			//salas = new Vector<Sala>();
			linhas = new Object[1][6];
			linhas[0][0] = "";
			linhas[0][1] = "";
			linhas[0][2] = "";
			linhas[0][3] = "";
			linhas[0][4] = "";
			linhas[0][5] = "";
			
			String msg = "Não há disciplinas a serem exibidas";
			Erro.print(msg, msg);
		}

		return linhas;
	}

}
