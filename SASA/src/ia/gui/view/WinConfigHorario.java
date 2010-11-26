package ia.gui.view;


import ia.gui.infra.Erro;
import ia.gui.view.GridHorarioEditarInternalFrame.MyComboBoxEditor;
import ia.gui.view.GridHorarioEditarInternalFrame.SimpleTableModel;
import ia.infra.negocio.curso.Curso;
import ia.infra.negocio.curso.Disciplina;
import ia.infra.negocio.curso.Serie;
import ia.infra.negocio.horario.Atividade;
import ia.infra.negocio.horario.Aula;
import ia.infra.negocio.horario.HorarioSerie;
import ia.infra.negocio.horario.ModeloHorario;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


public class WinConfigHorario extends JDialog implements ActionListener {
	protected MainWindow owner;
	
	protected JPanel main;
	protected JPanel panelOpcoes, panelGrid    , panelListagem, panelExtraOpcional;
	protected JButton btnSalvar, btnAdicionar, btnCancelar;
	protected JLabel lblAdicionar;
	
	protected ModeloHorario horario;
	protected Vector<HorarioSerie> horarios;
	
	
	public WinConfigHorario(MainWindow owner) {
		super(owner, "Configuração de horários");
		this.owner = owner;
		this.horarios = owner.getAllHorarioSerie();

		JTabbedPane tabled = new JTabbedPane();
		
		// ----- TAB 1: Grade de horários
		JPanel tab1Panel = new JPanel(new BorderLayout());
		JPanel authServerPanel = new JPanel(new BorderLayout());
		JPanel replServerPanel = new JPanel(new BorderLayout());

		authServerPanel.setBorder(BorderFactory.createTitledBorder(""));
		replServerPanel.setBorder(BorderFactory.createTitledBorder("Grade padrão de horários"));
		
		btnAdicionar = new JButton("+");
		lblAdicionar = new JLabel("Adicionar evento à grade padrão de horário");
		JPanel fu00 = new JPanel(new BorderLayout());
		JPanel fu01 = new JPanel(new BorderLayout());
		fu00.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		fu01.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		fu00.add(btnAdicionar, BorderLayout.CENTER);
		fu01.add(lblAdicionar, BorderLayout.CENTER);
		authServerPanel.add(fu00, BorderLayout.WEST);
		authServerPanel.add(fu01, BorderLayout.CENTER);
		
		btnAdicionar.addActionListener(this);

		replServerPanel.add(new JScrollPane(this.getModeloHorarioTable()), BorderLayout.CENTER);

		tab1Panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		tab1Panel.add(authServerPanel, BorderLayout.NORTH);
		tab1Panel.add(replServerPanel, BorderLayout.CENTER);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(this);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		
		JPanel botoes = new JPanel();
		botoes.add(btnSalvar);
		botoes.add(btnCancelar);
		
		tab1Panel.add(botoes , BorderLayout.SOUTH);

		
		// ----- TAB 2: Cursos
		JPanel tab2Panel = new JPanel(new BorderLayout());
		JPanel loggedClientPanel = new JPanel(new BorderLayout());

		loggedClientPanel.add(new JScrollPane(this.getCursosTable()), BorderLayout.CENTER);
		
		tab2Panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		tab2Panel.add(loggedClientPanel, BorderLayout.CENTER);
		
		
		// ----- TAB 3: Cursos
		JPanel tab3Panel = new JPanel(new BorderLayout());
		JPanel tab3CusosPanel = new JPanel(new BorderLayout());
		
		tab3CusosPanel.add(new JScrollPane(this.getCursosTable()), BorderLayout.CENTER);
		
		tab3Panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		tab3Panel.add(tab3CusosPanel, BorderLayout.CENTER);

		
		// ---------------
		tabled.add(tab1Panel, "Grade");
		tabled.add(tab2Panel, "Horários");
		tabled.add(tab3Panel, "Cursos");
		
		this.add(tabled);
		this.setAlwaysOnTop(false);
		this.setSize(600, 500);

	}
	
	public JTable getModeloHorarioTable() {
		//-----------------------------------------------------------
		String[] colunas = new String []{"Tipo", "Inicio", "Termino", "Nome", "Remover"};
		
		//------------------------------------------ 
		//-------- Monta o Grid do horario
		horario = new ModeloHorario();
		Object[][] linhas = new Object[horario.getAtividades().size()][5];

		int k = 0;
		for (Atividade atividade: horario.getAtividades()) {
			linhas[k][0] = atividade.getClass().getCanonicalName().replaceAll("(.)*\\.", "");
			linhas[k][1] = atividade.getInicio().getHours()+":"+atividade.getInicio().getMinutes();
			linhas[k][2] = atividade.getTermino().getHours()+":"+atividade.getTermino().getMinutes();
			linhas[k][3] = atividade.getNome();
			linhas[k][4] = "";
			k++;
		}
		
		JTable tabela = new JTable(linhas, colunas);
		return tabela;

	}
	
	public JTable getCursosTable() {
		//-----------------------------------------------------------
		String[] colunas = new String []{"Curso", "Série", " - ", " - "};
		
		Object[][] linhas = gerarLinhas();
		JTable tabela = new JTable(linhas, colunas);
		tabela.setCellEditor(new ButtonColumn(tabela, new int[]{2, 3}));
			
		return tabela;
	}
	
	private Object[][] gerarLinhas() {

		try {
			Object[][] linhas = new Object[horarios.size()][4];
			int k = 0;
			for (HorarioSerie horario : this.horarios) {
				linhas[k][0] = horario.getCurso().getNome();
				linhas[k][1] = horario.getSerie().getOrdem();
				linhas[k][2] = "Ver";
				linhas[k][3] = "Editar";
				k++;
			}

			return linhas;

		} catch (NullPointerException e) {
			Object[][] linhas = {{"", "", ""}, {"", "", ""}, {"", "", ""}};

			Erro.print("Erro 202:", "Não há informação das disciplinas e aulas");
			return linhas;
		}

	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnAdicionar) {

		}
	}

	class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
		JTable table;
		JButton renderButton;
		JButton editButton;
		String text;

		public ButtonColumn(JTable table, int[] column) {
			super();
			this.table = table;
			renderButton = new JButton();

			editButton = new JButton();
			editButton.setFocusPainted(false);
			editButton.addActionListener(this);

			TableColumnModel columnModel = table.getColumnModel();
			for (int i=0; i<column.length; i++) {
				columnModel.getColumn(column[i]).setCellRenderer(this);
				columnModel.getColumn(column[i]).setCellEditor(this);
			}
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			if (hasFocus) {
				renderButton.setForeground(table.getForeground());
				renderButton.setBackground(UIManager.getColor("Button.background"));
			} else if (isSelected) {
				renderButton.setForeground(table.getSelectionForeground());
				renderButton.setBackground(table.getSelectionBackground());
			} else {
				renderButton.setForeground(table.getForeground());
				renderButton.setBackground(UIManager.getColor("Button.background"));
			}

			renderButton.setText( (value == null) ? "" : value.toString() );
			return renderButton;
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			text = (value == null) ? "" : value.toString();
			editButton.setText( text );
			return editButton;
		}

		public Object getCellEditorValue(){
			return text;
		}

		public void actionPerformed(ActionEvent e){
			fireEditingStopped();
//			System.out.println(e.getActionCommand() + " : " + table.getSelectedRow());
			if (table.getSelectedColumn() == 2) {
				owner.exibirHorario(horarios.get(table.getSelectedRow()));
			} else {
				owner.editarHorario(horarios.get(table.getSelectedRow()));
			}
		}
	}

}
