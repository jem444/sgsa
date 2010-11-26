package ia.infra.negocio.curso;

import ia.infra.negocio.horario.Aula;
import ia.infra.negocio.sala.Sala;

import java.util.Vector;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

public class Disciplina {
	private int id;
	private String nome;
	private String descricao;
	private int numeroDeMatriculados;
	private Vector<Aula> aulas;
	private Professor professor;
	private Sala sala;
	private String nomeAbbr;
	
	/**
	 * indica em qual metade do ano a disciplina é ministrada
	 * 0 = anual (ambos os semestres)
	 * 1 = primeiro semestre
	 * 2 = segundo semestre
	 */ 
	private int semestre;
	
	
	public Disciplina(String nome, Professor professor) {
		this.nome = nome;
		this.aulas = new Vector<Aula>();
		this.setProfessor(professor);
	}

	public Disciplina(String nome) {
		this.nome = nome;
		this.aulas = new Vector<Aula>();
	}
        /***
         * criarAulas(int)
         * Tem por objetivo criar um vector com "quantidade" Aulas para estas poderem ser alocadas
         * com alguma disciplina
         * @param quantidade
         */
        public void criarAulas(int quantidade){
            for(int i = 0; i<=quantidade;i++){
                Aula aul = new Aula();
                aulas.add(aul);
            }
        }
	
	@Override
	public String toString() {
		return "Disc: "+ this.nome;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public int getNumeroDeMatriculados() {
		return numeroDeMatriculados;
	}


	public void setNumeroDeMatriculados(int numeroDeMatriculados) {
		this.numeroDeMatriculados = numeroDeMatriculados;
	}


	public Vector<Aula> getAulas() {
		return aulas;
	}


	public void setAulas(Vector<Aula> aulas) {
		this.aulas = aulas;
	}


	public Professor getProfessor() {
		return professor;
	}


	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public void setNomeAbbr(String nomeAbbr) {
		this.nomeAbbr = nomeAbbr;
	}

	public String getNomeAbbr() {
		return nomeAbbr;
	}
	
	
}
