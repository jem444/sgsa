package ia.infra.negocio.horario;

import java.util.Date;
import java.util.Vector;

public class ModeloHorario {
	private Vector<Atividade> atividades;
	
	public ModeloHorario() {
		this.atividades = new Vector<Atividade>();

		
		Aula aula01 = new Aula(1, new Date(00,00,00,7,30), new Date(00,00,00,8,20));
		Aula aula02 = new Aula(2, new Date(00,00,00,8,20), new Date(00,00,00,9,10));
		Intervalo intervalo1 = new Intervalo(3, new Date(00,00,00,9,10), new Date(00,00,00,9,30));
		Aula aula03 = new Aula(4, new Date(00,00,00,9,30), new Date(00,00,00,10,20));
		Aula aula04 = new Aula(5, new Date(00,00,00,10,20), new Date(00,00,00,11,10));
		Aula aula05 = new Aula(6, new Date(00,00,00,11,10), new Date(00,00,00,12,00));
		Aula aula06 = new Aula(7, new Date(00,00,00,12,00), new Date(00,00,00,12,50));
		Intervalo intervalo2 = new Intervalo(8, new Date(00,00,00,12,50), new Date(00,00,00,13,30));
		Aula aula07 = new Aula(9, new Date(00,00,00,13,30), new Date(00,00,00,14,20));
		Aula aula08 = new Aula(10, new Date(00,00,00,14,20), new Date(00,00,00,15,10));
		Intervalo intervalo3 = new Intervalo(11, new Date(00,00,00,15,10), new Date(00,00,00,15,30));
		Aula aula09 = new Aula(12, new Date(00,00,00,15,30), new Date(00,00,00,16,20));
		Aula aula10 = new Aula(13, new Date(00,00,00,16,20), new Date(00,00,00,17,10));
		Aula aula11 = new Aula(14, new Date(00,00,00,17,10), new Date(00,00,00,18,00));
		Aula aula12 = new Aula(15, new Date(00,00,00,18,00), new Date(00,00,00,18,50));
		Aula aula13 = new Aula(16, new Date(00,00,00,18,50), new Date(00,00,00,19,40));
		Aula aula14 = new Aula(17, new Date(00,00,00,19,40), new Date(00,00,00,20,30));
		Aula aula15 = new Aula(18, new Date(00,00,00,20,30), new Date(00,00,00,21,20));


		this.atividades.add(aula01);
		this.atividades.add(aula02);
		this.atividades.add(intervalo1);
		this.atividades.add(aula03);
		this.atividades.add(aula04);
		this.atividades.add(aula05);
		this.atividades.add(aula06);
		this.atividades.add(intervalo2);
		this.atividades.add(aula07);
		this.atividades.add(aula08);
		this.atividades.add(intervalo3);
		this.atividades.add(aula09);
		this.atividades.add(aula10);
		this.atividades.add(aula12);
		this.atividades.add(aula13);
		this.atividades.add(aula14);
		this.atividades.add(aula15);
	}

	public Vector<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(Vector<Atividade> atividades) {
		this.atividades = atividades;
	}
	
	
}
