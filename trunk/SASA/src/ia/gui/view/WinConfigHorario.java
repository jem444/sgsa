package ia.gui.view;


import ia.gui.infra.Erro;
import ia.infra.negocio.curso.Curso;
import ia.infra.negocio.curso.Serie;
import ia.infra.negocio.horario.Atividade;
import ia.infra.negocio.horario.ModeloHorario;
import ia.teste.TesteHorario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;


public class WinConfigHorario extends JDialog implements ActionListener {
	protected MainWindow owner;
	
	protected JPanel main;
	protected JPanel panelOpcoes, panelGrid    , panelListagem, panelExtraOpcional;
	protected JButton btnSalvar, btnAdicionar, btnCancelar;
	protected JLabel lblAdicionar;
	
	protected ModeloHorario horario;

	
	
	public WinConfigHorario(MainWindow owner) {
		super(owner, "Configuração de horários");
		this.owner = owner;

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

		
		// ---------------
		tabled.add(tab1Panel, "Grade");
		tabled.add(tab2Panel, "Cursos");
		
		this.add(tabled);
		
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
		String[] colunas = new String []{"Curso", "Série", "Editar"};

		
		Vector<Curso> cursos = owner.getCursos();
		
		int size = 0;
		
		try {
			for (Curso curso: cursos)
				size += curso.getSeries().size();
			
			Object[][] linhas = new Object[size][3];

			int k = 0;
			for (Curso curso: cursos) {
				for (Serie serie : curso.getSeries()) {
					linhas[k][0] = curso.getNome();
					linhas[k][1] = serie.getOrdem();
					k++;
				}
			}
			
			JTable tabela = new JTable(linhas, colunas);
			//onclick = 
			//TesteHorario t = new TesteHorario();
			//owner.exibirHorario(t.getSemana());

			return tabela;

		} catch (NullPointerException e) {
			Object[][] linhas = {{"","", ""}, {"","", ""}, {"","", ""}};
			JTable tabela = new JTable(linhas, new String []{"Curso", "Série", "Editar"});
			Erro.print("Não há informação das disciplinas e aulas", "Não há informação das disciplinas e aulas");
			return tabela;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnAdicionar) {
//			if (!owner.getConfigOwner().isServerOn()) {
//				owner.getConfigOwner().listen();
//				iniciarBtn.setText("Parar");
//				iniciarLbl.setText("Servidor pronto para receber conexões...");
//			} else {
//				try {
//					owner.getConfigOwner().serverShutdown();
//					iniciarBtn.setText("Iniciar");
//					iniciarLbl.setText("Servidor desligado...");
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
		}
		
	}
}
