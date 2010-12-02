/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia;

import ia.infra.algoritmo.Ensalamento;
import ia.io.curso.ParserCurso;
import ia.io.horario.ParserHorario;
import ia.io.sala.ParserSala;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Baltazar
 */
public class Main {

	/**
	 * @param args the command line arguments
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.CANCEL_OPTION) {

		} else {
			File file = new File(fileChooser.getSelectedFile().getPath());
			// ParserCurso pc = new ParserCurso(file);
			//pc.getCursos();
			ParserHorario ph = new ParserHorario(file);
			ph.readFile();
			//Ensalamento ensala = new Ensalamento();
			//ensala.agruparHorarios(ph.getListaHorario().get(0));
			System.out.println("fimm!!!");
		}
	}
}
