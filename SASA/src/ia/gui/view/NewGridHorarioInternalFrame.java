package ia.gui.view;

import ia.gui.infra.Erro;
import ia.infra.negocio.curso.Curso;
import ia.infra.negocio.curso.Disciplina;
import ia.infra.negocio.curso.Serie;
import ia.infra.negocio.horario.Atividade;
import ia.infra.negocio.horario.Aula;
import ia.infra.negocio.horario.ModeloHorario;
import ia.infra.negocio.sala.Sala;
import ia.teste.TesteHorario;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class NewGridHorarioInternalFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private Sala sala;
	private Vector<Curso> cursos;
	private JTable tabela;
	private Object[][] linhas;
	private String[] colunas;
	private ModeloHorario modeloHorario;
	
	public NewGridHorarioInternalFrame(Atividade[][] semana, Vector<Curso> cursos, String title, boolean a, boolean b, boolean c, boolean d) {
		super(title, a, b, c, d);
		this.cursos = cursos;
		this.modeloHorario = new ModeloHorario();
		
		colunas = new String []{"ID", "Inicio", "Termino", "SEG", "TER", "QUA", "QUI", "SEX", "SAB"};
		
		tabela = new JTable(gerarLinhas(), colunas);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(new JScrollPane(tabela), "Center");
		
		this.setSize(400, 600);

	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}


	public Object[][] gerarLinhas() {
		try {
			
//			System.out.println("[id,"+"inicio,"+"termino,"+"dia,"+"sala"+"]");
			
						
			TesteHorario testeHorario = new TesteHorario();
			Atividade[][] semana = testeHorario.getSemana();
			linhas = new Object[modeloHorario.getAtividades().size()][9];
			
			//preenche os campos início e término
			for (int j=0; j<semana.length; j++) {
				linhas[j][1] = semana[j][1].getInicio().getHours()+":"+semana[j][1].getInicio().getMinutes();
				linhas[j][2] = semana[j][1].getTermino().getHours()+":"+semana[j][1].getTermino().getMinutes();
			}
			
			//preenche as aulas
			for (Disciplina disciplina : testeHorario.getDisciplinas()) {
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
			
			
//			linhas = new Object[modeloHorario.getAtividades().size()][8];
//			for (Curso curso : this.cursos) {
//				for (Serie serie : curso.getSeries()) {
//					for (Disciplina disciplina : serie.getDisciplinas()) {
//						if (disciplina.getSala().equals(this.sala)) {
//							//para cada aula da disciplina:
//							for (Aula aula : disciplina.getAulas()) {
//								if (aula.getOrdem() >= 0) {
//									linhas[aula.getOrdem()-1][2+aula.getDia()] = aula.getOrdem();
//									linhas[aula.getOrdem()-1][2+aula.getDia()] = aula.getInicio().toString();
//									linhas[aula.getOrdem()-1][2+aula.getDia()] = aula.getTermino().toString();
//									linhas[aula.getOrdem()-1][2+aula.getDia()] = sala.getCapacidade();
//									linhas[aula.getOrdem()-1][2+aula.getDia()] = sala.getCapacidade();
//									linhas[aula.getOrdem()-1][2+aula.getDia()] = sala.getCapacidade();
//									linhas[aula.getOrdem()-1][2+aula.getDia()] = sala.getCapacidade();
//									linhas[aula.getOrdem()-1][2+aula.getDia()] = sala.getCapacidade();
//									//								System.out.println("["+aula.getOrdem()+","+aula.getInicio().toString()+","+aula.getTermino().toString()+","+aula.getDia()+","+sala.getNome()+"]");
//								}
//							}
//						}
//					}
//				}
//			}
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
