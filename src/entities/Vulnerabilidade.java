package entities;

public abstract class Vulnerabilidade implements Comparable<Vulnerabilidade> {
	
	private Double media;
	private String vulnerabilidadeIsolada;
	private Integer peso;
	
	public Vulnerabilidade(Double media, String vulnerabilidadeIsolada) {
		this.media = media;
		this.vulnerabilidadeIsolada = vulnerabilidadeIsolada;
		
	}

	public Double getMedia() {
		return media;
	}

	public String getVulnerabilidadeIsolada() {
		return vulnerabilidadeIsolada;
	}


	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
	@Override
	public String toString() {
		return "Vulnerabilidades [media=" + media + ", vulnerabilidadeIsolada=" + vulnerabilidadeIsolada + ", peso="
				+ peso + "]";
	}

	public int compareTo(Vulnerabilidade outraVulnerabilidades) {
		if(this.media < outraVulnerabilidades.getMedia()) return -1;
		else if(this.media > outraVulnerabilidades.getMedia()) return +1;
		else return 0;
		}
}
