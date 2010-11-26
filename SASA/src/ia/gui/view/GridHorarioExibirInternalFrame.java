package ia.gui.view;

import ia.gui.infra.Erro;
import ia.infra.negocio.curso.Curso;
import ia.infra.negocio.curso.Disciplina;
import ia.infra.negocio.curso.Serie;
import ia.infra.negocio.horario.Atividade;
import ia.infra.negocio.horario.Aula;
import ia.infra.negocio.horario.HorarioSerie;
import ia.infra.negocio.horario.ModeloHorario;
import ia.infra.negocio.sala.Sala;
import ia.teste.TesteHorario;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GridHorarioExibirInternalFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private JTable tabela;
	private Object[][] linhas;
	private String[] colunas;
	private HorarioSerie horario;
	
	public GridHorarioExibirInternalFrame(HorarioSerie horario, String title, boolean a, boolean b, boolean c, boolean d) {
		super(title, a, b, c, d);
		this.horario = horario;
		
		colunas = new String []{"ID", "Inicio", "Termino", "SEG", "TER", "QUA", "QUI", "SEX", "SAB"};
		
		tabela = new JTable(gerarLinhas(), colunas);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(new JScrollPane(tabela), "Center");
		
		this.setSize(840, 600);

	}

	public Object[][] gerarLinhas() {
		try {
			
			Atividade[][] semana = horario.getSemana();
			linhas = new Object[horario.getSemana().length][9];
			
			//preenche os campos início e término
			for (int j=0; j<semana.length; j++) {
				linhas[j][1] = semana[j][1].getInicio().getHours()+":"+semana[j][1].getInicio().getMinutes();
				linhas[j][2] = semana[j][1].getTermino().getHours()+":"+semana[j][1].getTermino().getMinutes();
			}
			
			//preenche as aulas
			for (Disciplina disciplina : horario.getSerie().getDisciplinas()) {
				for (Aula aula : disciplina.getAulas()) {
					for (int i=0; i<semana[0].length; i++) {
						for (int j=0; j<semana.length; j++) {
							if (aula.equals(semana[j][i])) {
								linhas[j][i+3] = disciplina.getNome();
							}
						}
					}
				}
			}
			
			
		} catch (NullPointerException e) {
			linhas = new Object[1][3];
			linhas[0][0] = "";
			linhas[0][1] = "";
			linhas[0][2] = "";
			
			
			String msg = "Não há informações a serem exibidas";
			Erro.print(msg, msg);
			e.printStackTrace();
		}

		return linhas;
	}

}
