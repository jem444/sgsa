package ia.gui.view;

import ia.gui.infra.Erro;
import ia.infra.negocio.curso.Disciplina;
import ia.infra.negocio.horario.Atividade;
import ia.infra.negocio.horario.Aula;
import ia.infra.negocio.horario.HorarioSerie;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class GridHorarioEditarInternalFrame extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTable tabela;
	private Object[][] linhas;
	private String[] colunas;
	private JButton btnPronto;
	private HorarioSerie horario;

	
	public GridHorarioEditarInternalFrame(HorarioSerie horario, String title, boolean a, boolean b, boolean c, boolean d) {
		super(title, a, b, c, d);
		this.horario = horario;
		
		colunas = new String []{"ID", "Inicio", "Termino", "SEG", "TER", "QUA", "QUI", "SEX", "SAB"};
		
//		tabela = new JTable(gerarLinhas(), colunas);
		tabela = createJTable();
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(new JScrollPane(tabela), "Center");

		btnPronto = new JButton("Pronto");
		btnPronto.addActionListener(this);
		JPanel fu00 = new JPanel(new BorderLayout());
		fu00.add(btnPronto, BorderLayout.CENTER);
		
		JPanel panelBtnPronto = new JPanel(new BorderLayout());
		panelBtnPronto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelBtnPronto.add(fu00, BorderLayout.EAST);
		
		this.getContentPane().add(panelBtnPronto, BorderLayout.SOUTH);

		this.setSize(840, 600);
	}


	public Object[][] gerarLinhas() {
		try {
			
//			TesteHorario testeHorario = new TesteHorario();
//			Atividade[][] semana = testeHorario.getSemana();
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
	

	public JTable createJTable() {

		// quais colunas são editáveis
		boolean[] edicao = {false, false, false, true, true, true, true, true, true};

		ArrayList dados = new ArrayList();
		// Alimenta as linhas de dados
		Object[][] linhas = gerarLinhas();
		for (int i=0; i < linhas.length; i++) {
			dados.add(linhas[i]);
		}
		
		SimpleTableModel modelo = new SimpleTableModel(dados, colunas, edicao);
		JTable jtable = new JTable(modelo);
		jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//---------------------

		// Obtém os valores para o combobox
		Vector<Disciplina> disciplinas = horario.getSerie().getDisciplinas();
		Object[] values = (Disciplina[]) disciplinas.toArray(new Disciplina[disciplinas.size()]);     
		
		// Indica as colunas para o combobox
		int[] vColIndex = {3, 4, 5, 6, 7, 8};

		// Configura o combobox nas colunas indicadas
		for (int i=0; i<vColIndex.length; i++) {
			TableColumn col = jtable.getColumnModel().getColumn(vColIndex[i]);
			col.setCellEditor(new MyComboBoxEditor(values));
		}
		
//		col.setCellRenderer(new MyComboBoxRenderer(values));    

		
		return jtable;
	}

	
	/**
	 * Guarda os dados para exibir na grid
	 * @author kill
	 *
	 */
	public class SimpleTableModel extends AbstractTableModel{

		private ArrayList linhas = null;
		private String [] colunas = null;
		public String[] getColunas() {return colunas;}
		public ArrayList getLinhas() {return linhas;}
		public void setColunas(String[] strings) {colunas = strings;}
		public void setLinhas(ArrayList list) {linhas = list;}
		
		/**
		 * Construtor da classe que recebe os dados e as colunas desejadas
		 * @param dados
		 * @param colunas
		 */
		public SimpleTableModel(ArrayList dados, String[] colunas){
			setLinhas(dados);
			setColunas(colunas);
			
			colsEdicao = new boolean[colunas.length];
			for (int i=0; i<colunas.length; i++) {
				colsEdicao[i] = false;
			}
		}
		
		public SimpleTableModel(ArrayList dados, String[] colunas, boolean[] edicao){
			setLinhas(dados);
			setColunas(colunas);
			colsEdicao = edicao;
		}
		
		/**
		 * Armazena a informação de cada coluna sobre se ela é editável (true) ou não (false);
		 */
		private boolean[] colsEdicao;
		/**
		 * Retorna se uma determinada célula é editável, com base na informação da propriedade de 
		 * editável da coluna a qual ela pertence
		 * @param row
		 * @param col
		 * @return
		 */
		public boolean isCellEditable(int row, int col){
			return colsEdicao[col];
			
		}



		/**
		 * Retorna o numero de colunas no modelo
		 * @see javax.swing.table.TableModel#getColumnCount()
		 */
		public int getColumnCount() {return getColunas().length;}

		/**
		 * Retorna o numero de linhas existentes no modelo
		 * @see javax.swing.table.TableModel#getRowCount()
		 */
		public int getRowCount() {return getLinhas().size();}

		/**
		 * Obtem o valor na linha e coluna
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			// Obtem a linha, que é um Object[]
			Object[] linha = (Object[])getLinhas().get(rowIndex);
			// Retorna o objeto que esta na coluna
			return linha[columnIndex];
		}
		
		/**
		 * Seta o valor na linha e coluna
		 * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
		 */
		public void setValueAt(Object value, int row, int col){
			// Obtem a linha, que é uma Object[]
			Object[] linha = (Object[])getLinhas().get(row);

			// imprime o conteúdo anterior à mudança, caso houver
			// realiza a troca do conteúdo anterior para o novo, caso houvesse anterior
			Disciplina disciplina_anterior;
			Aula aula_anterior;
			if (getValueAt(row,col) != null) {
				System.out.println(getValueAt(row,col).getClass() + " = " + getValueAt(row,col));
				
				disciplina_anterior = (Disciplina)getValueAt(row,col);
				aula_anterior = (Aula)horario.getSemana()[row][col-3];
				disciplina_anterior.getAulas().remove(aula_anterior);
			} else {
				aula_anterior = new Aula();
				aula_anterior.setOrdem(row-1);
			}
			Disciplina disciplina_nova = (Disciplina)value;
			Aula aula_nova = new Aula();
			disciplina_nova.getAulas().add(aula_nova);
			aula_nova.setOrdem(aula_anterior.getOrdem());
			aula_nova.setNome(disciplina_nova.getNome());
			horario.getSemana()[row][col-3 ] = aula_nova;
			
			// # fim da troca
			
			// Altera o conteudo no indice da coluna passado
			linha[col] = value;
			
			// dispara o evento de celula alterada
			fireTableCellUpdated(row,col);
			
			// imprime o conteúdo após a mudança
			System.out.println(getValueAt(row,col).getClass() + " = " + getValueAt(row,col));
		}
		
		public void addRow( String [] dadosLinha){
			getLinhas().add(dadosLinha);
			// Informa a jtable de que houve linhas incluidas no modelo
			// COmo adicionamos no final, pegamos o tamanho total do modelo
			// menos 1 para obter a linha incluida.
			int linha = getLinhas().size()-1;
			fireTableRowsInserted(linha,linha);
			return;
		}

		public void removeRow(int row){
			getLinhas().remove(0);
			// informa a jtable que houve dados deletados passando a 
			// linha reovida
			fireTableRowsDeleted(row,row);
		}


		/**
		 * Remove a linha pelo valor da coluna informada
		 * @param val
		 * @param col
		 * @return
		 */
		public boolean removeRow(String val, int col){
			// obtem o iterator
			Iterator i = getLinhas().iterator();
			int linha = 0;
			// Faz um looping em cima das linhas
			while(i.hasNext()){
				// Obtem as colunas da linha atual
				String[] linhaCorrente = (String[])i.next();
				linha++;
				// compara o conteudo String da linha atual na coluna desejada
				// com o valor informado
				if( linhaCorrente[col].equals(val) ){
					getLinhas().remove(linha);
					// informa a jtable que houve dados deletados passando a 
					// linha removida
					fireTableRowsDeleted(linha,linha);
					return true;				
				}
			}
			// Nao encontrou nada
			return false;
		}


		// Implementação do getColumnName

		/**
		 * Retorna o nome da coluna.
		 * @see javax.swing.table.TableModel#getColumnName(int)
		 */
		public String getColumnName(int col){
			return getColunas()[col];
		}

	}
	
	public class MyComboBoxRenderer extends JComboBox implements TableCellRenderer {
		public MyComboBoxRenderer(Object[] items) {
			super(items);
		}            

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {           
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				super.setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(table.getBackground());
			}                
			setSelectedItem(value);
			return this;
		}
	}        

	public class MyComboBoxEditor extends DefaultCellEditor {
		public MyComboBoxEditor(Object[] items) {
			super(new JComboBox(items));
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(btnPronto)) {
			this.dispose();
		}
		
	}

}
