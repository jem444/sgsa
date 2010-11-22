package ia.gui.view;

import ia.gui.infra.JComboBoxDisciplinas;
import ia.infra.negocio.curso.Disciplina;
import ia.infra.negocio.curso.Serie;
import ia.infra.negocio.horario.Atividade;
import ia.infra.negocio.horario.Aula;
import ia.infra.negocio.horario.ModeloHorario;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GridSerieInternalFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private ModeloHorario horario;
	private JComboBoxDisciplinas comboDisciplinas;
	private Serie serie;

	public GridSerieInternalFrame(Serie serie, Atividade[][] semana, String title, boolean a, boolean b, boolean c, boolean d) {
		super(title, a, b, c, d);
		this.serie = serie;
		
		String[] colunas = new String []{"AULA", "Horario", "Seg","Ter","Qua", "Qui", "Sex","Sab", "Dom"};
//		Object[][] linhas = new Object [][] {
//				{"1", "07:30 as 8:20", "", "", "", "", "", "", ""},
//				{"2", "07:30 as 8:20", "", "", "", "", "", "", ""},
//				{"3", "07:30 as 8:20", "", "", "", "", "", "", ""},
//				{"4", "07:30 as 8:20", "", "", "", "", "", "", ""},
//				{"5", "07:30 as 8:20", "", "", "", "", "", "", ""},
//				{"6", "07:30 as 8:20", "", "", "", "", "", "", ""},
//		};
		
		Object[][] linhas = new Object[semana.length][9];
		for (int i=0; i<semana.length; i++) {
			linhas[i][0] = semana[i][0].getNome();
			linhas[i][1] = semana[i][0].getHorario();

			for (int j=2; j<7; j++) { 
				for (Disciplina disciplina : serie.getDisciplinas()) {
					for (Aula aula : disciplina.getAulas()) {
						if (semana[i][j-2].getClass().equals(Aula.class)) {
							if (((Aula)semana[i][j-2]).equals(aula)){
								System.out.println("fuuuuuuuuuuuuuu");
								linhas[i][j] = disciplina.getNome();
							}
						}
					}
				}
			}
		}
		JTable tabela = new JTable(linhas, colunas);
		

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(new JScrollPane(tabela), "Center");
		
		this.setSize(700, 500);

	}

}
