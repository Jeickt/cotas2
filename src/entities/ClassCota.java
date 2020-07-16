package entities;

public abstract class ClassCota implements Comparable<ClassCota> {

		private String nomeCota;
		private Integer peso;
		private Integer vagas;
		
		public ClassCota(String nomeCota, Integer peso, Integer vagas) {
			this.nomeCota = nomeCota;
			this.peso = peso;
			this.vagas = vagas;
		}

		public String getNomeCota() {
			return nomeCota;
		}

		public Integer getPeso() {
			return peso;
		}
		
		public Integer getVagas() {
			return vagas;
		}
		
		public void aumentaVagas(int vagasTemp) {
			this.vagas += vagasTemp;
		}
		
		public void diminuiVagas() {
			this.vagas--;
		}
		
		public void zeraVagas() {
			this.vagas = 0;
		}

		public int compareTo(ClassCota outraClassCotas) {
			if(this.peso < outraClassCotas.getPeso()) return -1;
			else if(this.peso > outraClassCotas.getPeso()) return +1;
			else return 0;
		}
}
