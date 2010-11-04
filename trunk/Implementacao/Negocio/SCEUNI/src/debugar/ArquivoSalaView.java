package debugar;

import java.io.FileReader;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import unioeste.ia.trabalho1.controle.parser.sala.ParseSala;
import unioeste.ia.trabalho1.pojo.Sala;


public class ArquivoSalaView extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2994754998583452160L;
	
	/**Atributos**/
	private List<Sala> listaSala;
	
    /**Metodos**/
	
	public void menuArquivoAbrir()
	{
		JFileChooser arq = new JFileChooser();
	if (arq.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			try {
				ParseSala parserS = new ParseSala(new FileReader(arq.getSelectedFile()));
				parserS.load();
				this.setListaSala(parserS.getListaSala());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**gets e sets**/
	public List<Sala> getListaSala() {
		return listaSala;
	}

	public void setListaSala(List<Sala> listaSala) {
		this.listaSala = listaSala;
	}
}