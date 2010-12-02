package ia.infra.negocio.curso;

import java.io.Serializable;
import java.util.Vector;

public class Curso implements Serializable  {
	private int id;
	private String nome;
	private String descricao;
	private Vector<Serie> series;
	
	
	public Curso(String nome) {
		this.nome = nome;
		this.series = new Vector<Serie>();
	}

        /***
         * getNewSerie(int)
         * Se caso a serie não exista ainda, ele cria uma nova serie, caso contrario retorna
         * uma referencia para a serie ja existente
         * @param ordem
         * @return Serie
         */
        public Serie getNewSerie(int ordem) {
            for (Serie serie : series) {
                if (serie.getOrdem() == ordem) {
                    return serie;
                }
            }
            Serie serie = new Serie(ordem);
            this.series.add(serie);
            return serie;
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

	public Vector<Serie> getSeries() {
		return series;
	}

	public void setSeries(Vector<Serie> series) {
		this.series = series;
	}
	
	@Override
	public String toString() {
		return "nome: "+nome+ " series:"+ series.toString();
	}
	
}
