package entities;

public class Candidato {
	
	private Boolean[] cotas = new Boolean[10];
	private Integer ordem;
	private String codCandidato;
	private String nomeCandidato;
	private Integer inscricaoPS;
	private Double acertos;
	
	public Candidato(Boolean[] cotas, Integer ordem, String codCandidato, String nomeCandidato, Integer inscricaoPS, Double acertos) {
		this.cotas = cotas;
		this.ordem = ordem;
		this.codCandidato = codCandidato;
		this.nomeCandidato = nomeCandidato;
		this.inscricaoPS = inscricaoPS;
		this.acertos = acertos;
	}
	
	
	public Boolean[] getC() {
		return cotas;
	}

	public Integer getOrdem() {
		return ordem;
	}


	public String getCodCandidato() {
		return codCandidato;
	}


	public String getNomeCandidato() {
		return nomeCandidato;
	}


	public Integer getInscricaoPS() {
		return inscricaoPS;
	}


	public Double getAcertos() {
		return acertos;
	}

	@Override
	public String toString() {
		return "Boolean= " + cotas 
				+ ", Classificação= " + ordem + 
				", Código= " + codCandidato + 
				", Nome= " + nomeCandidato + 
				", Cota = C" + inscricaoPS + 
				", Acertos= " + acertos;
	}

}
