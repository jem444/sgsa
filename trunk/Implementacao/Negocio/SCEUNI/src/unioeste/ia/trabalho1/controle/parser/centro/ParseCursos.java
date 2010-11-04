/* Generated By:JavaCC: Do not edit this line. ParseCursos.java */
package unioeste.ia.trabalho1.controle.parser.centro;

import java.util.List;
import java.util.ArrayList;
import unioeste.ia.trabalho1.pojo.Curso;
import unioeste.ia.trabalho1.pojo.Disciplina;
import unioeste.ia.trabalho1.pojo.Serie;
import unioeste.ia.trabalho1.pojo.Professor;
import unioeste.ia.trabalho1.pojo.Periodo;
import unioeste.ia.trabalho1.controle.facade.impl.FacadeAlocacaoImpl;

public class ParseCursos implements ParseCursosConstants {

        private List<Serie> listaSerie = new ArrayList<Serie>();
        private List<Curso> listaCurso = new ArrayList<Curso>();
        private List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
        private List<Professor> listaProfessor = new ArrayList<Professor>();

    public void setListaSerie(List<Serie> listaSerie) {
                this.listaSerie = listaSerie;
        }

        public List<Serie> getListaSerie() {
                return listaSerie;
        }

        public void setListaCurso(List<Curso> listaCurso) {
                this.listaCurso = listaCurso;
        }

        public List<Curso> getListaCurso() {
                return listaCurso;
        }

        public void setListaDisciplina(List<Disciplina> listaDisciplina) {
                this.listaDisciplina = listaDisciplina;
        }

        public List<Disciplina> getListaDisciplina() {
                return listaDisciplina;
        }

        public void setListaProfessor(List<Professor> listaProfessor) {
                this.listaProfessor = listaProfessor;
        }

        public List<Professor> getListaProfessor() {
                return listaProfessor;
        }

  final public String serie() throws ParseException {
        Token t;
    t = jj_consume_token(SERIE);
                {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  final public Integer qtdSerie() throws ParseException {
    Token t;
    t = jj_consume_token(CONSTANTE);
                {if (true) return Integer.parseInt(t.image);}
    throw new Error("Missing return statement in function");
  }

  final public Integer qtdDisciplina() throws ParseException {
 Token t;
    t = jj_consume_token(CONSTANTE);
                {if (true) return Integer.parseInt(t.image);}
    throw new Error("Missing return statement in function");
  }

  final public Integer qtdAulas() throws ParseException {
 Token t;
    t = jj_consume_token(CONSTANTE);
                {if (true) return Integer.parseInt(t.image);}
    throw new Error("Missing return statement in function");
  }

  final public String disciplina() throws ParseException {
        Token t;
    t = jj_consume_token(IDENTIFICADOR);
                {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  final public String professor() throws ParseException {
        Token t;
    t = jj_consume_token(IDENTIFICADOR);
                {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  final public String curso() throws ParseException {
        Token t;
    t = jj_consume_token(IDENTIFICADOR);
        {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  final public void load() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SERIE:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      loadTudo();
    }
  }

  final public void loadTudo() throws ParseException {
         String nmSerie = " ";
                 String nmCurso = " ";
                 String nmDisciplina = " ";
                 String nmProfessor = " ";

                 Integer quantidade_matricula = 0;
                 Integer quantidade_aula = 0;
                 Integer quantidade_matriculado_serie = 0;
    nmSerie = serie();
    jj_consume_token(14);
    nmCurso = curso();
    jj_consume_token(14);
    quantidade_matriculado_serie = qtdSerie();
    jj_consume_token(14);
    nmDisciplina = disciplina();
    jj_consume_token(14);
    quantidade_matricula = qtdDisciplina();
    jj_consume_token(14);
    quantidade_aula = qtdAulas();
    jj_consume_token(14);
    nmProfessor = professor();
    jj_consume_token(15);
                Serie serie = new Serie();
                Curso curso = new Curso();
                Disciplina disciplina = new Disciplina();
                Professor professor = new Professor();

                serie.setNmSerie(nmSerie);
                serie.setQuantidade_matriculado_serie(quantidade_matriculado_serie);
                serie.setCd_serie(listaSerie.size());
                serie.setPeriodo(Periodo.INTEGRAL);

                serie.setNu_serie(Integer.valueOf(nmSerie.charAt(0))-48);

                curso.setNmCurso(nmCurso);
                curso.setCd_curso(listaCurso.size()+1);
                curso.getListaSerie().add(serie);
                curso.setPeriodo(Periodo.INTEGRAL);

                serie.setCurso(curso);

                disciplina.setNmDisciplina(nmDisciplina);
                disciplina.setQuantidade_aula(quantidade_aula);
                disciplina.setQuantidade_matricula(quantidade_matricula);
                disciplina.setSerie(serie);
                disciplina.setCurso(curso);
                disciplina.setCd_disciplina(listaDisciplina.size()+1);

                serie.getListaDisciplina().add(disciplina);

                professor.setNm_professor(nmProfessor);
                //professor.getListaDisciplina().add(disciplina);
                //professor.getListaCurso().add(curso);
                //professor.getListaSerie().add(serie);
                professor.setCd_professor(listaProfessor.size()+1);


                disciplina.setProfessor(professor);

                listaCurso.add(curso);
                listaSerie.add(serie);
                listaDisciplina.add(disciplina);
                listaProfessor.add(professor);
  }

  /** Generated Token Manager. */
  public ParseCursosTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[1];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x400,};
   }

  /** Constructor with InputStream. */
  public ParseCursos(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public ParseCursos(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParseCursosTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 1; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 1; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public ParseCursos(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParseCursosTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 1; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 1; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public ParseCursos(ParseCursosTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 1; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParseCursosTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 1; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[16];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 1; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 16; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
