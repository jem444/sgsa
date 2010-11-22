package ia.gui.infra;

import ia.infra.negocio.curso.Disciplina;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Vector;

import javax.swing.JComboBox;

public class JComboBoxDisciplinas extends JComboBox {
	private Vector<Disciplina> disciplinasAlocadas;
	private Vector<Disciplina> disciplinasNaoAlocadas;

	
	public JComboBoxDisciplinas(Vector<Disciplina> disciplinas) {
		this.disciplinasNaoAlocadas = disciplinas;
		this.disciplinasNaoAlocadas.add(0, new Disciplina("", null));
		
		this.disciplinasAlocadas = new Vector<Disciplina>();
		
		for (Disciplina disciplina : disciplinas) {
			this.addItem(disciplina);
		}
	}


	public Vector<Disciplina> getDisciplinasAlocadas() {
		return disciplinasAlocadas;
	}


	public void setDisciplinasAlocadas(Vector<Disciplina> disciplinasAlocadas) {
		this.disciplinasAlocadas = disciplinasAlocadas;
	}


	public Vector<Disciplina> getDisciplinasNaoAlocadas() {
		return disciplinasNaoAlocadas;
	}


	public void setDisciplinasNaoAlocadas(Vector<Disciplina> disciplinasNaoAlocadas) {
		this.disciplinasNaoAlocadas = disciplinasNaoAlocadas;
	}

	public void alocarDisciplina(int index) {
		Disciplina selected = this.disciplinasNaoAlocadas.remove(index);
		
		this.disciplinasAlocadas.add(selected);
		this.removeItem(selected);
	}
	
}
