package ia.gui.view;

import ia.infra.algoritmo.BuscaProfundidade;
import ia.infra.negocio.curso.Curso;
import ia.infra.negocio.curso.Serie;
import ia.infra.negocio.horario.Atividade;
import ia.infra.negocio.sala.Sala;
import ia.io.curso.ParserCurso;
import ia.io.sala.ParserSala;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;




public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	//Tamanho maximo do arquivo:
	final static int MAXFILE = 65536;
	
	private JMenuBar menuBar;
	private JMenu mArquivo, mEditar, mExibir, mOrganizar, mJanelas;
	private JMenuItem mArquivoAbrir, mArquivoSalvar, mArquivoImprimir, mArquivoCarregarSalas, 
					  mArquivoCarregarDisciplinas, mArquivoImportarHorário, mArquivoSair;
	private JMenuItem mEditarConfigurarGradeHorario, mEditarConfigurarHorario, mListarClientes, mListarDVDsVenda;
	private JMenuItem mExibirSalas, mExibirCursos;
	private JMenuItem mAplicarBuscaExaustiva, mAplicarBuscaHeuristica;
//	private JTextArea editor, console;
	private JDesktopPane desktop;
	private Vector<JInternalFrame> janelas;
	private JInternalFrame janelaSalas;
	private JMenuItem item;
	private Atividade[][] semana;

	private Point nextInitInternalFrame = new Point(5, 5);

	public static final int JANELA_CONFIGURA_GRADE_HORARIO = 1;
	public static final int JANELA_CONFIGURA_HORARIO       = 2;
	public static final int JANELA_ALEATORIA               = 999;
	
//	private File arquivoDisciplinas, arquivoSalas;
	private Vector<Curso> cursos;
	private Vector<Sala> salas;

	
	public MainWindow(){
		super("Agendamento de salas");
		
		desktop = new JDesktopPane();
		janelas = new Vector<JInternalFrame>();
		
		this.getContentPane().setLayout(new GridLayout(0, 1));
		this.getContentPane().add(desktop);
		
		TrataEventos controle = new TrataEventos();
		
		//----------------------------------------------
		menuBar        = new JMenuBar();
		mArquivo       = new JMenu("Arquivo");
		mArquivo.setMnemonic('A');
		mArquivoAbrir  = new JMenuItem("Abrir");
		mArquivoAbrir.setMnemonic('A');
		mArquivoSalvar = new JMenuItem("Salvar");
		mArquivoSalvar.setMnemonic('S');
		mArquivoImprimir = new JMenuItem("Imprimir");
		mArquivoImprimir.setMnemonic('p');
		mArquivoCarregarSalas       = new JMenuItem("Importar Salas");
		mArquivoCarregarSalas.setMnemonic('l');
		mArquivoCarregarDisciplinas = new JMenuItem("Importar Disciplinas");
		mArquivoCarregarDisciplinas.setMnemonic('D');
		mArquivoImportarHorário     = new JMenuItem("Importar Horário");
		mArquivoImportarHorário.setMnemonic('H');
		mArquivoSair   = new JMenuItem("Sair");
		mArquivoSair.setMnemonic('S');
		
		mArquivoAbrir.addActionListener(controle);
		mArquivoSalvar.addActionListener(controle);
		mArquivoImprimir.addActionListener(controle);
		mArquivoCarregarSalas.addActionListener(controle);
		mArquivoCarregarDisciplinas.addActionListener(controle);
		mArquivoImportarHorário.addActionListener(controle);
		mArquivoSair.addActionListener(controle);
		
		mArquivo.add(mArquivoAbrir);
		mArquivo.add(mArquivoCarregarSalas);
		mArquivo.add(mArquivoCarregarDisciplinas);
//		mArquivo.add(mArquivoImportarHorário);
		mArquivo.addSeparator();
		mArquivo.add(mArquivoSalvar);
		mArquivo.add(mArquivoImprimir);
		mArquivo.addSeparator();
		mArquivo.add(mArquivoSair);
		
		
		mArquivoAbrir.setEnabled(false);
		mArquivoSalvar.setEnabled(false);
		mArquivoImprimir.setEnabled(false);
			
		//----------------------------------------------
		mOrganizar    = new JMenu("Organizar");
		mOrganizar.setMnemonic('O');
		mAplicarBuscaExaustiva    = new JMenuItem("Aplicar Busca Exaustiva");
		mAplicarBuscaExaustiva.setMnemonic('E');
		mAplicarBuscaHeuristica = new JMenuItem("Aplicar Busca Heurística");
		mAplicarBuscaHeuristica.setMnemonic('H');

		
		mAplicarBuscaExaustiva.addActionListener(controle);
		mAplicarBuscaHeuristica.addActionListener(controle);

		
		mOrganizar.add(mAplicarBuscaExaustiva);
		mOrganizar.add(mAplicarBuscaHeuristica);

		
		//----------------------------------------------
		mEditar = new JMenu("Editar");
		mEditar.setMnemonic('L');
		mEditarConfigurarGradeHorario   = new JMenuItem("Configurar Grade");
		mEditarConfigurarGradeHorario.setMnemonic('D');
		mEditarConfigurarHorario = new JMenuItem("Configurar Horário");
		mEditarConfigurarHorario.setMnemonic('H');
		mListarClientes = new JMenuItem("X");
		mListarClientes.setMnemonic('C');
		mListarDVDsVenda = new JMenuItem("y");
		mListarDVDsVenda.setMnemonic('t');

		
		mEditarConfigurarGradeHorario.addActionListener(controle);
		mEditarConfigurarHorario.addActionListener(controle);
		mListarClientes.addActionListener(controle);
		mListarDVDsVenda.addActionListener(controle);
		
		mEditar.add(mEditarConfigurarGradeHorario);
		mEditar.add(mEditarConfigurarHorario);
		mEditar.add(mListarClientes);
		mEditar.add(mListarDVDsVenda);
		mListarClientes.setEnabled(false);
		mListarDVDsVenda.setEnabled(false);

		
		//----------------------------------------------
		mExibir = new JMenu("Exibir");
		mExibir.setMnemonic('x');
		mExibirSalas   = new JMenuItem("Salas");
		mExibirSalas.setMnemonic('S');
		mExibirCursos = new JMenuItem("Cursos");
		mExibirCursos.setMnemonic('C');
		
		mExibirSalas.addActionListener(controle);
		mExibirCursos.addActionListener(controle);
		
		mExibir.add(mExibirSalas);
		mExibir.add(mExibirCursos);


		//----------------------------------------------
		mJanelas = new JMenu("Janelas");
		mJanelas.setMnemonic('J');
		
		
		//----------------------------------------------		
		menuBar.add(mArquivo);
		menuBar.add(mOrganizar);
		menuBar.add(mEditar);
		menuBar.add(mExibir);
		menuBar.add(mJanelas);
		
		this.setJMenuBar(menuBar);
		
//		this.setContentPane(main);
		this.setSize(new Dimension(1000,720));
		this.desktop.setBackground(new Color(000, 128, 192));
		this.setVisible(true);
		
	}
	
	private class TrataEventos implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource()==mArquivoAbrir){
				//abrirNovo();
			} else
			if (arg0.getSource()==mArquivoCarregarSalas){
				importarSalas();
			} else
			if (arg0.getSource()==mArquivoCarregarDisciplinas){
				importarCursos();
			} else
			if (arg0.getSource()==mArquivoImportarHorário){

			} else
			if (arg0.getSource()==mArquivoSalvar){
				//salvarComo();
			} else
			if (arg0.getSource()==mArquivoSair){
				sair();
			} else
			if (arg0.getSource()==mAplicarBuscaExaustiva){
				BuscaProfundidade busca = new BuscaProfundidade(cursos, salas);
//				busca.aloca(busca.getTodasDisciplinas());
//				busca.busca();
				busca.busca2();
				semana = busca.getSemana();
			} else
			if (arg0.getSource()==mAplicarBuscaHeuristica){
				((GridSalasInternalFrame)janelaSalas).setSalas(salas);
			}
			if (arg0.getSource()==mEditarConfigurarHorario){
				chamaJanela(MainWindow.JANELA_CONFIGURA_HORARIO);
			}
			if (arg0.getSource()==mEditarConfigurarGradeHorario){
				chamaJanela(MainWindow.JANELA_CONFIGURA_GRADE_HORARIO);
			}
			if (arg0.getSource()==mExibirSalas){
				exibirHorariosSalas();
			}
			if (arg0.getSource()==mExibirCursos){
				exibirHorariosCursos();
			}

		}
	}
	
	private JInternalFrame addJanela(Object bean, String titulo) {
		JInternalFrame internal = new JInternalFrame();
		
		if (bean.getClass().equals(Serie.class)) {
			internal = new GridSerieInternalFrame((Serie)bean, semana, titulo, true, true, true, true);

		} else
		if (bean.getClass().equals(Sala.class)) {
			internal = new GridSalaInternalFrame((Sala)bean, cursos, titulo, true, true, true, true);

		} else
		if (bean.getClass().equals(Atividade[][].class)) {
			internal = new GridHorarioInternalFrame((Atividade[][])bean, cursos, titulo, true, true, true, true);
			
		} else
		if (bean.getClass().equals(Vector.class)) {
			if (((Vector)bean).get(0).getClass().equals(Sala.class)) {
				internal = new GridSalasInternalFrame((Vector<Sala>)bean, titulo, true, true, true, true);
				
			} else
			if (((Vector)bean).get(0).getClass().equals(Curso.class)) {
				internal = new GridDisciplinaInternalFrame((Vector<Curso>)bean, titulo, true, true, true, true);
				
			} 
		} 
		
		internal.setLocation(getNextInitInternalFrame());
		internal.setVisible(true);
		janelas.add(internal);
		
		desktop.removeAll();
		
		mJanelas.removeAll();
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int k = 0;
				for (JInternalFrame internalJet : janelas) {
					if (e.getSource()==mJanelas.getMenuComponent(k)) {
						try {
							internalJet.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					k++;
				}
			}
		};
		
		for (JInternalFrame internalJet : janelas) {
			//atualiza as janelas no desktop
			desktop.add(internalJet);
			
			//atualiza o menu janelas
			item = new JMenuItem(internalJet.getTitle());
			item.addActionListener(listener);
			mJanelas.add(item);
		}
		
		try {
			internal.setSelected(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
		
		return internal;
	}
	
	private Point getNextInitInternalFrame() {
		Point p = (Point)nextInitInternalFrame.clone();
		nextInitInternalFrame.translate(15, 15);
		return p;
	}
	
	public void importarCursos() {
		FileDialog dialog = new FileDialog(this, "Importar Disciplinas", FileDialog.LOAD);
		dialog.setVisible(true);
		
		String diretorio, arquivo;
		diretorio = dialog.getDirectory();
		arquivo   = dialog.getFile();
		
		System.out.println(diretorio+arquivo);
		
		if (arquivo != null) {
			ParserCurso parser = new ParserCurso(new File(diretorio+arquivo));
			try {
				cursos = parser.getCursos();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		addJanela(cursos, "Cursos e disciplinas");
		
	}
	
	public void importarSalas() {
		FileDialog dialog = new FileDialog(this, "Importar Salas", FileDialog.LOAD);
		dialog.setVisible(true);
		
		String diretorio, arquivo;
		diretorio = dialog.getDirectory();
		arquivo   = dialog.getFile();
		
		System.out.println(diretorio+arquivo);
		
		if (arquivo != null) {
			ParserSala parser = new ParserSala(new File(diretorio+arquivo));
			try {
				salas = parser.getSalas();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		janelaSalas = addJanela(salas, "Salas");
	}
	
	public void exibirHorariosCursos(){
//		cursos = Teste.getCursos();
		
		for (Curso curso : cursos) {
			for (Serie serie : curso.getSeries()) {
				addJanela(serie, curso.getNome() + ": Série " + serie.getOrdem());
			}
		}	
	}
	
	public void exibirHorario(Atividade[][] semana) {
		addJanela(semana, "Horário");
		
	}
	
	public void exibirHorariosSalas(){
//		salas = Teste.getSalas();
		
		for (Sala sala : salas) {
			addJanela(sala, "Sala: " + sala.getNome() + " (" + sala.getCapacidade() + ")");
		}	
	}
	
	private void chamaJanela(int opc){
		switch (opc) {
		case MainWindow.JANELA_CONFIGURA_GRADE_HORARIO:
//			WinConfigHorario winConfigHorario = new WinConfigHorario(this);
//			winConfigHorario.setVisible(true);
//			break;
//			é o mesmo do de baixo 

		case MainWindow.JANELA_CONFIGURA_HORARIO:
			WinConfigHorario winConfigHorario = new WinConfigHorario(this);
			winConfigHorario.setVisible(true);
			break;

		default:
			break;
		}
	}
	
	
	private void sair() {
		System.exit(0);
	}
	
	public Vector<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Vector<Curso> cursos) {
		this.cursos = cursos;
	}

	public Vector<Sala> getSalas() {
		return salas;
	}

	public void setSalas(Vector<Sala> salas) {
		this.salas = salas;
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		MainWindow note = new MainWindow();
		note.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
