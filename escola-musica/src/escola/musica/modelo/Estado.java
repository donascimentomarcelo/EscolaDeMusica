package escola.musica.modelo;

public enum Estado {
	SP("S�o Paulo"),
	RJ("Rio de Janeiro"),
	PR("Paran�"),
	SC("Santa Catarina"),
	MG("Minas Gerais");
	
	private String label;
	
	
	//m�todo contrutor. ctrl 3, escreve constructor, 1� op�
	private Estado(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
