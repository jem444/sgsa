package ia.teste;

import ia.infra.negocio.curso.Curso;
import ia.infra.negocio.horario.HorarioSerie;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.Vector;


public class TesteSerialized {
	public TesteSerialized() {
		// TODO Auto-generated constructor stub
	}
	public void salvaHorariosSerie(Vector<HorarioSerie> horariosserie, Vector<Curso> cursos){
		try {
			System.out.println(horariosserie.size());
			FileOutputStream fos = new FileOutputStream("teste.hs");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(horariosserie);
			oos.writeObject(cursos);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	    	      
	}
	
	public Vector<HorarioSerie> obterHorariosSerie(Vector<HorarioSerie> h, Vector<Curso> c){
		
		try {
			FileInputStream fis   = new FileInputStream("teste.hs");
			ObjectInputStream ois = new ObjectInputStream(fis);
			h = (Vector<HorarioSerie>) ois.readObject();
			System.out.println(h.size());
			c = (Vector<Curso>) ois.readObject();
			System.out.println(c.size());
			return h;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Vector<Curso> obterCursos(Vector<HorarioSerie> h, Vector<Curso> c){
		
		try {
			FileInputStream fis   = new FileInputStream("teste.hs");
			ObjectInputStream ois = new ObjectInputStream(fis);
			h = (Vector<HorarioSerie>) ois.readObject();
			System.out.println(h.size());
			c = (Vector<Curso>) ois.readObject();
			System.out.println(c.size());
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//////////////////////====================================
	
	public static Vector<Curso> obterCursos(){
		
		try {
			FileInputStream fis   = new FileInputStream("teste2.hs");
			ObjectInputStream ois = new ObjectInputStream(fis);
			return (Vector<Curso>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void salvaCursos(Vector<Curso> cursos){
		try {
			System.out.println(cursos.size());
			FileOutputStream fos = new FileOutputStream("teste2.hs");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(cursos);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	    	      
	}
}
