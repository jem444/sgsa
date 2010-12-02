package ia.infra.negocio.horario;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Atividade implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ordem;
	private String nome;
	private String descricao;
	private Date inicio;
	private Date termino;
	private int dia;
	
	public static final int DOMINGO     = 1;
	public static final int SEGUNDA     = 2;
	public static final int TERCAFEIRA  = 3;
	public static final int QUARTAFEIRA = 4;
	public static final int QUITAFEIRA  = 5;
	public static final int SEXTAFEIRA  = 6;
	public static final int SABADO      = 7;
	
	public Atividade(int ordem, Date inicio, Date termino) {
		this.ordem = ordem;
		this.inicio = inicio;
		this.termino = termino;
		this.nome = "";
	}
	
	public Atividade() {
		this.ordem   = -1;
		this.nome    = "";
		this.inicio  = new Date(0,0,0,0,0);
		this.termino = new Date(0,0,0,0,0);
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int id) {
		this.ordem = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getTermino() {
		return termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}
	/***
         * Retorna o horario em string
         * @return String
         */
	public String getHorario() {
		String horario = addZero(getInicio().getHours(), 2) + ":" 
							+ addZero(getInicio().getMinutes(), 2) + " as "
							+ addZero(getTermino().getHours(), 2) + ":" 
							+ addZero(getTermino().getMinutes(), 2);
							
		return horario;
	}

        /**
         * Retorna uma string contendo o numero n, junto com nzeros zeros
         * @param n
         * @param nzeros
         * @return String
         */
	private String addZero(int n, int nzeros) {
		String number = "" + n;
		if (number.length() < nzeros) {
			while (number.length() < nzeros) {
				number = "0" + number;
			}
		}

		return number;
	}
	
	/**
	 * Método setter com parser para String2Date
	 * @param inicio String no formato HH:mm
	 * @throws ParseException 
	 */
	public void setInicio(String inicio) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("HH:mm");
		Date ini = f.parse(inicio);
		setInicio(ini);
	}
	
	/**
	 * Método setter com parser para String2Date
	 * @param termino String no formato HH:mm
	 * @throws ParseException
	 */
	public void setTermino(String termino) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("HH:mm");
		Date term = f.parse(termino);
		setTermino(term);
	}
	@Override
	public String toString() {
		return inicio.getHours() + ":" + inicio.getMinutes() + " - " + termino.getHours() + ":" + termino.getMinutes();
	}
	
}
