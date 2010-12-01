package ia.infra.negocio.horario;

public enum DiaSemana {
	
	DOMINGO (0),
	SEGUNDA(1),
	TERCAFEIRA (2),
	QUARTAFEIRA(3),
	QUITAFEIRA(4),
	SEXTAFEIRA (5),
	SABADO (6);

	private final byte value;

	DiaSemana (Integer value) {
		this.value = value.byteValue();		
	}

	public int value() {
		return value;
	}
}
