package ia.gui.infra;

import javax.swing.JOptionPane;

public class Erro {

	/***
         * exit(String, String)
         * Mostra a mensagem de erro crítico e termina a execução do programa
         * @param msg
         * @param msgErro
         */
	public static void exit(String msg, String msgErro) {  
		JOptionPane.showMessageDialog(null, msg, "Erro crítico", 0);
		System.err.println(msg);  
		System.out.println(msgErro);  
		System.exit(0);  
	}  

        /***
         * print(String, String)
         * Apenas mostra a mensagem de erro crítico
         * @param msg
         * @param msgErro
         */
	public static void print(String msg, String msgErro) {  
		JOptionPane.showMessageDialog(null, msg, "Erro cr�tico", 0);  
		System.err.println(msg);  
		System.out.println(msgErro);  
	}  
	
	
}
