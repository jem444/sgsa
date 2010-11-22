package ia.infra.negocio.horario;

public enum DiaSemana {
	
	DOMINGO (1),
	SEGUNDA(2),
	TERCAFEIRA (3),
	QUARTAFEIRA(4),
	QUITAFEIRA(5),
	SEXTAFEIRA (6),
	SABADO (7);

	private final byte value;

	DiaSemana (Integer value) {
		this.value = value.byteValue();		
	}

	public int value() {
		return value;
	}
}
