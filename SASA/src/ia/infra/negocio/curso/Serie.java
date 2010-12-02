package ia.infra.negocio.curso;

import java.io.Serializable;
import java.util.Vector;

public class Serie implements Serializable  {
	private static final long serialVersionUID = 1L;
	private int id;
	private int mediaDeMatriculados;
	private int ordem;
	private Vector<Disciplina> disciplinas;
	
	public Serie(int ordem) {
		this.ordem = ordem;
		this.disciplinas = new Vector<Disciplina>();
	}

        /***
         * getNewDisciplina(String, Professor)
         * Se caso a disciplina não exista ainda, ele cria uma nova disciplina, caso contrario retorna
         * uma referencia para a disciplinaja existente
         * @param nomeDsc
         * @param prof
         * @return Disciplina
         */
        public Disciplina getNewDisciplina(String nomeDsc, Professor prof) {
            for (Disciplina disc : disciplinas) {
                if (disc.getNome().equals(nomeDsc) &&
                    disc.getProfessor().equals(prof)) {
                    return disc;
                }
            }
            Disciplina disc = new Disciplina(nomeDsc,prof);
            this.disciplinas.add(disc);
            return disc;
        }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMediaDeMatriculados() {
		return mediaDeMatriculados;
	}

	public void setMediaDeMatriculados(int mediaDeMatriculados) {
		this.mediaDeMatriculados = mediaDeMatriculados;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public Vector<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Vector<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	@Override
	public String toString() {
		return "ordem: "+ordem+"qtd: "+ mediaDeMatriculados+"Disc: "+ disciplinas.toString();
	}
}
