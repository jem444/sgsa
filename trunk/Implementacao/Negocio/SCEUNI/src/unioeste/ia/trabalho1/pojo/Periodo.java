package unioeste.ia.trabalho1.pojo;

public enum Periodo {
	
	MATUTINO(1), VESPERTINO(2), INTEGRAL(3), NOTURNO(4);
 
	private final byte value;
	
	Periodo (Integer value) {
		this.value = value.byteValue();		
	}
	
	public int value() {
		return value;
	}
	
	
	
}
