package debugar;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import unioeste.ia.trabalho1.controle.parser.centro.ParseCursos;
import unioeste.ia.trabalho1.pojo.Curso;
import unioeste.ia.trabalho1.pojo.Disciplina;
import unioeste.ia.trabalho1.pojo.Professor;
import unioeste.ia.trabalho1.pojo.Serie;

public class ArquivoCursoView extends JFrame {

	/**Atributos**/
	
	private static final long serialVersionUID = -2753959516743120560L;

	private List<Serie> listaSerie;

	private List<Curso> listaCurso;

	private List<Disciplina> listaDisciplina;

	private List<Professor> listaProfessor;

	/**Metodos**/

	public void menuArquivoAbrir()
	{
		JFileChooser arq = new JFileChooser();
		if (arq.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			try {
				ParseCursos parseC = new ParseCursos(new FileReader(arq.getSelectedFile()));
				parseC.load();
				this.listaCurso = parseC.getListaCurso();
				this.listaDisciplina = parseC.getListaDisciplina();
				this.listaSerie = parseC.getListaSerie();
				this.listaProfessor = parseC.getListaProfessor();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**Contructor**/
	public ArquivoCursoView() throws HeadlessException {
		super();
		this.listaSerie = new ArrayList<Serie>();
		this.listaCurso = new ArrayList<Curso>();
		this.listaDisciplina = new ArrayList<Disciplina>();
		this.listaProfessor = new ArrayList<Professor>();
	}


	public ArquivoCursoView(GraphicsConfiguration arg0) {
		super(arg0);
		this.listaSerie = new ArrayList<Serie>();
		this.listaCurso = new ArrayList<Curso>();
		this.listaDisciplina = new ArrayList<Disciplina>();
		this.listaProfessor = new ArrayList<Professor>();
	}


	public ArquivoCursoView(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		this.listaSerie = new ArrayList<Serie>();
		this.listaCurso = new ArrayList<Curso>();
		this.listaDisciplina = new ArrayList<Disciplina>();
		this.listaProfessor = new ArrayList<Professor>();
	}


	public ArquivoCursoView(String arg0) throws HeadlessException {
		super(arg0);
		this.listaSerie = new ArrayList<Serie>();
		this.listaCurso = new ArrayList<Curso>();
		this.listaDisciplina = new ArrayList<Disciplina>();
		this.listaProfessor = new ArrayList<Professor>();
	}

	
	
	public ArquivoCursoView(List<Serie> listaSerie, List<Curso> listaCurso,
			List<Disciplina> listaDisciplina, List<Professor> listaProfessor)
			throws HeadlessException {
		super();
		this.listaSerie = listaSerie;
		this.listaCurso = listaCurso;
		this.listaDisciplina = listaDisciplina;
		this.listaProfessor = listaProfessor;
	}
	/**gets a sets**/
	
	public List<Serie> getListaSerie() {
		return listaSerie;
	}



	public void setListaSerie(List<Serie> listaSerie) {
		this.listaSerie = listaSerie;
	}

	public List<Curso> getListaCurso() {
		return listaCurso;
	}

	public void setListaCurso(List<Curso> listaCurso) {
		this.listaCurso = listaCurso;
	}

	public List<Disciplina> getListaDisciplina() {
		return listaDisciplina;
	}

	public void setListaDisciplina(List<Disciplina> listaDisciplina) {
		this.listaDisciplina = listaDisciplina;
	}

	public List<Professor> getListaProfessor() {
		return listaProfessor;
	}

	public void setListaProfessor(List<Professor> listaProfessor) {
		this.listaProfessor = listaProfessor;
	}
	

	

	
}
