package ia.gui.view;

import ia.gui.infra.Erro;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Vector;


import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;




public class WinSelectLocadora1 extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	protected MainWindow pai;
	protected JPanel main;
	protected JPanel panelOpcoes, panelParameters, panelListagem, panelExtraOpcional;
	protected JButton btnConectar, btnCompra, btnCancelar, btnLocacao;
	
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
	
	
	
	public WinSelectLocadora1(MainWindow pai) {
		super(pai , "Conectar a uma locadora da Franquia");
		this.pai = pai;
		
		regions = new JPanel[256];
		qtdRegions = 0;
					
		this.main            = new JPanel(new BorderLayout());
		this.panelOpcoes     = new JPanel(new BorderLayout());
		this.panelParameters = new JPanel();
		this.panelListagem   = new JPanel(new GridLayout(256,1));
			
				
		main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelParameters.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		panelListagem.setBorder(BorderFactory.createTitledBorder("Preview"));
		
		//cmbFiltros = new JComboBox(opcsFiltros);
//		cmbLocadoras = new JComboBox(this.getPai().getLocadoras());
		cmbLocadoras.addActionListener(this);
		cmbLocadoras.setMaximumSize(new Dimension(180, 20));
		
		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(this);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCompra  = new JButton("Compra");
		btnCompra.addActionListener(this);
		btnLocacao = new JButton("Locação");
		btnLocacao.addActionListener(this);
		
		JPanel botoes = new JPanel();
		botoes.add(btnConectar);
		botoes.add(btnCompra);
		botoes.add(btnLocacao);
		botoes.add(btnCancelar);
		
		txtRatio = new JTextField();
		txtHiBoost = new JTextField();
		lblHiBoost = new JLabel("Porta:");
		
		panelParameters.add(new JLabel(" "));
		panelParameters.add(new JLabel("Dominio: "));
		panelParameters.add(txtRatio);
		panelParameters.add(lblHiBoost);
		panelParameters.add(txtHiBoost);
		
		
		lblHiBoost.setVisible(true);
		txtHiBoost.setVisible(true);
		
		panelOpcoes.add(cmbLocadoras , BorderLayout.NORTH);
		panelOpcoes.add(panelParameters , BorderLayout.CENTER);
		
		
		// teste de listagem
		main.add(new JScrollPane(panelListagem, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);

		
		
		//#teste de listagem
		
		main.add(panelOpcoes , BorderLayout.NORTH);		
		//main.add(panelListagem , BorderLayout.CENTER);	
		main.add(botoes , BorderLayout.SOUTH);
		
		this.setContentPane(main);
		this.setSize(new Dimension(550,500));
		this.setVisible(true);
		this.setResizable(false);
		
	}
	
 
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==cmbLocadoras) {
			Erro.print(cmbLocadoras.getSelectedItem().toString(), cmbLocadoras.getSelectedItem().toString());
			
		} else
			
		if (arg0.getSource()==btnLocacao) {
//			this.getPai().setLocadoraRemota(((Locadora)cmbLocadoras.getSelectedItem()));
//			listaDVDs = this.getPai().solicitarListaLocadoraRemota(2);
//			this.atualizarLista();
		} else
		
		if (arg0.getSource()==btnConectar){

		} else
			
		if (arg0.getSource()==btnCompra){
//			this.getPai().setLocadoraRemota(((Locadora)cmbLocadoras.getSelectedItem()));
//			listaDVDs = this.getPai().solicitarListaLocadoraRemota(1);
//			this.atualizarLista();
		} else
			
		if (arg0.getSource()==btnCancelar){
			this.dispose();
		} 
		
	}
	

	protected void atualizarLista() {
		// Limpa o panel
		panelListagem.removeAll();
		panelListagem.repaint();
		for (int i=0; i<qtdRegions; i++) {
			regions[qtdRegions] = null;
		}
		qtdRegions = 0;
		
		//redesenha um a um
//		for (DVD dvd : listaDVDs) {
//			addDVD(dvd);
//		}
	}
	
//	public void addDVD(DVD dvd) {
//		regions[qtdRegions] = new RegionViewer(dvd.getCodigo(), dvd.getTitulo(), dvd.getSinopse(), new BufferedImage(120, 80, BufferedImage.TYPE_INT_RGB));
//		panelListagem.add(regions[qtdRegions]);
//		
//		panelListagem.repaint();
//		panelListagem.revalidate();
//		qtdRegions++;
//	}
		
	public MainWindow getPai() {
		return pai;
	}

	public void setPai(MainWindow pai) {
		this.pai = pai;
	}
	
}
