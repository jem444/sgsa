package ia.io.sala;

import ia.infra.negocio.sala.Sala;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

public class ParserSala {
	private File file;
	
	
	public ParserSala(File filepath) {
		this.file = filepath;
		//nothing else
	}

        /***
         * getSalas()
         * Tem por objetivo retirar as informações referentes a Sala de um arquivo e retornar
         * um vetor contendo tais salas
         *
         * @param void
         * @return Vector<Sala>
         * @throws FileNotFoundException
         * @throws IOException
         */
	public  Vector<Sala> getSalas() throws FileNotFoundException, IOException {
		Vector<Sala> salas = new Vector<Sala>();
		
                FileReader in = new FileReader(file);
                BufferedReader buff = new BufferedReader(in);

                                        //inicialização de variaveis
                String linha = null;
                String sal = null;
                String capac = null;
                int capacidade = 0;

                                        //para cada linha do arquivo
                while((linha = buff.readLine()) !=null){                  
                    StringTokenizer st = new StringTokenizer(linha,",");

                                        //validação do tamanho e da quantidade de elementos da linha
                    if(st.countTokens()==2){
                        sal = st.nextToken().trim();
                        capac = st.nextToken();
                                         //retira o ponto final
                        if(capac.contains(".")){
                            capac = capac.substring(0, capac.length()-1).trim();
                        }
                                         //seta a sala
                        capacidade = Integer.parseInt(capac);
                        Sala sala = new Sala(sal, capacidade);
                        salas.add(sala);
                    }
                }             
		//Lê o arquivo ou throw exceptions;
		//...
		//constrói um vetor de salas com as informa�oes do arquivo
		//...
		
		//devolve vetor de salas
		return salas;
	}
}
