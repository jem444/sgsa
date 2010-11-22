package ia.gui.view;

import ia.gui.infra.Erro;
import ia.gui.infra.JComboBoxDisciplinas;
import ia.infra.negocio.horario.Atividade;
import ia.infra.negocio.horario.ModeloHorario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;




public class WinConfigGradeHorario extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	protected MainWindow pai;
	protected JPanel main;
	protected JPanel panelOpcoes, panelGrid    , panelListagem, panelExtraOpcional;
	protected JButton btnSalvar, btnAdicionar, btnCancelar;
	
	protected float aHiboost;
	protected boolean tracking, reescalona;
	protected int intensidade, maxIntensidade, tipoFiltro;
	protected JLabel lblIntensidade, lblHiBoost;
	protected JButton btnIncSize;
	protected JTextField txtRatio, txtSizeWidth, txtSizeHeight, txtHiBoost;
	protected JSlider sliIntensidade;
	protected JComboBox cmbLocadoras;
	protected ButtonGroup groupContraste, groupPrewitt; 
	
	protected JPanel[] regions;
	protected int qtdRegions;

	private ModeloHorario horario;

	
	
	public WinConfigGradeHorario(MainWindow pai) {
		super(pai , "Configuracao de hoario");
		this.pai = pai;
		
		this.main          = new JPanel(new BorderLayout());
		this.panelOpcoes   = new JPanel(new BorderLayout());
		this.panelGrid     = new JPanel();
		this.panelListagem = new JPanel(new BorderLayout());
			
				
		main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelGrid.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
		cmbLocadoras = new JComboBox();
		cmbLocadoras.addActionListener(this);
		cmbLocadoras.setMaximumSize(new Dimension(180, 20));
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(this);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnAdicionar  = new JButton("+");
		btnAdicionar.addActionListener(this);
		
		JPanel botoes = new JPanel();
		botoes.add(btnSalvar);
		botoes.add(btnCancelar);
		
		panelOpcoes.add(btnAdicionar , BorderLayout.EAST);


//-----------------------------------------------------------
			String[] colunas = new String []{"Remover", "Tipo", "Inicio", "Termino", "Nome"};
			
	//------------------------------------------ 
	//-------- Monta o Grid do horario
			horario = new ModeloHorario();
			Object[][] linhas = new Object[horario.getAtividades().size()][5];

			int k = 0;
			for (Atividade atividade: horario.getAtividades()) {
				linhas[k][0] = "";
				linhas[k][1] = atividade.getClass().getCanonicalName().replaceAll("(.)*\\.", "");
				linhas[k][2] = atividade.getInicio().getHours()+":"+atividade.getInicio().getMinutes();
				linhas[k][3] = atividade.getTermino().getHours()+":"+atividade.getTermino().getMinutes();
				linhas[k][4] = atividade.getNome();
				k++;
			}
			
			JTable tabela = new JTable(linhas, colunas);

		    panelOpcoes.setLayout(new BorderLayout());
		    panelOpcoes.add(new JScrollPane(tabela), BorderLayout.CENTER);
		

//-----------------------------------------------------------		
		panelListagem.add(btnAdicionar , BorderLayout.NORTH);		

		main.add(panelListagem, BorderLayout.NORTH);
		main.add(panelOpcoes , BorderLayout.CENTER);		
		main.add(botoes , BorderLayout.SOUTH);
		
		this.setContentPane(main);
		this.setSize(new Dimension(600,700));
		this.setVisible(true);
		this.setResizable(false);
		
	}
	
 
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==cmbLocadoras) {
			Erro.print(cmbLocadoras.getSelectedItem().toString(), cmbLocadoras.getSelectedItem().toString());
			
		} else
			
		if (arg0.getSource()==btnSalvar){

		} else
			
		if (arg0.getSource()==btnAdicionar){
//			this.getPai().setLocadoraRemota(((Locadora)cmbLocadoras.getSelectedItem()));
//			listaDVDs = this.getPai().solicitarListaLocadoraRemota(1);
//			this.atualizarLista();
		} else
			
		if (arg0.getSource()==btnCancelar){
			this.dispose();
		} 
		
	}
	
		
	public MainWindow getPai() {
		return pai;
	}

}
