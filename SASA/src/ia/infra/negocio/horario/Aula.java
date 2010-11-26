package ia.infra.negocio.horario;

import java.util.Date;


public class Aula extends Atividade {
	
	private String disciplina;
	
	public Aula(int ordem, Date inicio, Date termino) {
		super(ordem, inicio, termino);
	}
	
	public Aula() {
		setOrdem(-1);
		setInicio(new Date(00,00,00,00,00));
		setTermino(new Date(00,00,00,00,00));
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	@Override
	public String toString() {
		return "Disc: "+ this.getNome();
	}
}
