package debugar;

import unioeste.ia.trabalho1.controle.facade.FacadeEnsalamento;
import unioeste.ia.trabalho1.controle.facade.impl.FacadeImpl;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FacadeImpl f = new FacadeImpl();
		f.gerarProfundidadeDisciplina(null, null, null, null, null);
		//f.gerarProfundidadeSeries(null, null, null, null, null);
		//f.gerarGreedyDisciplina(null, null, null, null, null);
		//f.gerarGreedySerie(null, null, null, null, null);
	}
} 
