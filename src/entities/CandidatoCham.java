package entities;

public class CandidatoCham {
	
	private Integer inscricao;
	private String cotaChamada;
	private String nome;
	private String codCandidato;
	
	public CandidatoCham(Integer inscricao, String nome, String codCandidato) {
		this.inscricao = inscricao;
		this.nome = nome;
		this.codCandidato = codCandidato;
	}

	public String getCodCandidato() {
		return codCandidato;
	}

	public String getCotaChamada() {
		return cotaChamada;
	}

	public void setCotachamada(String cotachamada) {
		this.cotaChamada = cotachamada;
	}

	public Integer getInscricao() {
		return inscricao;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return "" + inscricao + " - " + cotaChamada + " - " + nome;
	}
	
}
