package ia.io.curso;

import ia.infra.negocio.curso.Curso;
import ia.infra.negocio.curso.Disciplina;
import ia.infra.negocio.curso.Professor;
import ia.infra.negocio.curso.Serie;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

public class ParserCurso {
	private File file;
	
	
        public ParserCurso(File filepath) {
            this.file = filepath;
            //nothing else
        }
        public ParserCurso() {
            this.file = null;
            //nothing else
        }

        /***
         * getNewProfessor(String,Vector<Professor>)
         * Se caso o professor não exista ainda, ele cria um novo professor, caso contrario retorna
         * uma referencia para o professor ja existente
         * @param nome
         * @param professores
         * @return Professor
         */
        public Professor getNewProfessor(String nome, Vector<Professor> professores) {
            for (Professor prof : professores) {
                if (prof.getNome().equals(nome)) {
                    return prof;
                }
            }
            Professor prof = new Professor(nome);
            professores.add(prof);
            return prof;
        }

        /***
         * getNewCurso(String,Vector<Curso>)
         * Se caso o curso não exista ainda, ele cria um novo curso, caso contrario retorna
         * uma referencia para o curso ja existente
         * @param nome
         * @param cursos
         * @return Curso
         */
        public Curso getNewCurso(String nome, Vector<Curso> cursos) {
            for (Curso curso : cursos) {
                if (curso.getNome().equals(nome)) {
                    return curso;
                }
            }
            Curso curso = new Curso(nome);
            cursos.add(curso);
            return curso;
        }

	/***
         * getCursos()
         * Tem por objetivo retirar as informações referentes a cursos de um arquivo e retornar
         * um vetor contendo tais cursos
         * @param void
         * @return Vector<Curso>
         * @throws FileNotFoundException
         * @throws IOException
         */
	public  Vector<Curso> getCursos() throws FileNotFoundException, IOException {
		Vector<Curso> cursos = new Vector<Curso>();
		Vector<Professor> professores = new Vector<Professor>();


                FileReader in = new FileReader(file);
                BufferedReader buff = new BufferedReader(in);

                                                //inicialização de variaveis
                String linha = null;
                String serie = null;
                String curso = null;
                String numeroMedioMatriculado = null;
                String disciplina = null;
                String numeroMatriculado = null;
                String cargaHoraria = null;
                String professor = null;

                                                //para cada linha do arquivo
                while((linha = buff.readLine()) !=null){
                                                //retira o ponto final
                    if(linha.contains(".")){
                    linha = linha.substring(0, linha.length()-1).trim();
                    }
                    StringTokenizer st = new StringTokenizer(linha,",");
                                                //tratamento de validação, ao menos do tamanho
                    if (st.countTokens() == 7){
                        
                        serie = st.nextToken();
//                        serie = serie.substring(0, serie.length()-1).trim(); 
                        serie = serie.substring(0, 1).trim(); 
                        curso = st.nextToken().trim(); 
                        numeroMedioMatriculado = st.nextToken().trim(); 
                        disciplina = st.nextToken().trim(); 
                        numeroMatriculado = st.nextToken().trim(); 
                        cargaHoraria = st.nextToken().trim(); 
                        professor = st.nextToken().trim(); 

                                                        //a cada linha ele vai montando o determinado curso
                                                        //referente àquela linha
                        Curso curs = getNewCurso(curso, cursos);
                        System.out.println((serie));
                        //Serie srie = curs.getNewSerie(1);
                        Serie srie = curs.getNewSerie(Integer.parseInt(serie));
                        srie.setMediaDeMatriculados(Integer.parseInt(numeroMedioMatriculado));
                        Professor prof = getNewProfessor(professor, professores);
                        Disciplina disc = srie.getNewDisciplina(disciplina, prof);
                        disc.setNumeroDeMatriculados(Integer.parseInt(numeroMatriculado));
                        disc.criarAulas(Integer.parseInt(cargaHoraria));
       
                    }
                }

		
		//devolve vetor de cursos
		return cursos;
	}
}
