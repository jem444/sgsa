package ia.infra.negocio.horario;

import java.io.Serializable;
import java.util.Date;


public class Evento extends Atividade implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	
	
	public Evento(int ordem, Date inicio, Date termino) {
		super(ordem, inicio, termino);
	}
}
