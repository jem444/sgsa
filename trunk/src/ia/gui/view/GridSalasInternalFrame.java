package ia.gui.view;

import ia.gui.infra.Erro;
import ia.infra.negocio.sala.Sala;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GridSalasInternalFrame extends JInternalFrame {

	private Vector<Sala> salas;
	private JTable tabela;
	private Object[][] linhas;
	private String[] colunas;
	
	public GridSalasInternalFrame(Vector<Sala> salas, String title, boolean a, boolean b, boolean c, boolean d) {
		super(title, a, b, c, d);
		this.salas = salas;
		
		colunas = new String []{"ID", "Sala", "Capacidade"};
		
		tabela = new JTable(gerarLinhas(linhas), colunas);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(new JScrollPane(tabela), "Center");
		
		this.setSize(400, 600);

	}

	public Vector<Sala> getSalas() {
		return salas;
	}

	public void setSalas(Vector<Sala> salas) {
		this.salas = salas;
		tabela = new JTable(gerarLinhas(linhas), colunas);
	}
	
	public Object[][] gerarLinhas(Object[][] linhas) {
		try {
			linhas = new Object[salas.size()][3];
			
			int k = 0;
			for (Sala sala: salas) {
				linhas[k][0] = sala.getId();
				linhas[k][1] = sala.getNome();
				linhas[k][2] = sala.getCapacidade();
				k++;
			}
			
		} catch (NullPointerException e) {
			//salas = new Vector<Sala>();
			linhas = new Object[1][3];
			linhas[0][0] = "";
			linhas[0][1] = "";
			linhas[0][2] = "";
			
			
			String msg = "Não há salas a serem exibidas";
			Erro.print(msg, msg);
		}

		return linhas;
	}

}
