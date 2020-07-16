package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import entities.Candidato;
import entities.CandidatoCham;
import entities.ClassCotaEx;


public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		List<Candidato> candidatos = new ArrayList<>();
		List<ClassCotaEx> parametros = new ArrayList<>();
		List<CandidatoCham> chamada = new ArrayList<>();
		List<ArrayList> listas = new ArrayList();
		List<Integer> ultimasPosicoes = new ArrayList();
		
		candidatos = IntArquivo.listarCandidatos();
		
		for (int i=0; i<10; i++) {
			listas.add(Parametro.fazerListaDeCota(candidatos, i));
			ultimasPosicoes.add(listas.get(i).size());
			if (ultimasPosicoes.get(i) != 0)
				ultimasPosicoes.set(i, ultimasPosicoes.get(i) - 1);
		}
		
		int vagasTotal = 30;
		
		parametros = Parametro.ordenarPrioridadeCotas(candidatos);
		
		for (ClassCotaEx c : parametros) {
			System.out.println(c.getNomeCota());
		}
		
		ArrayList<CandidatoCham> listaTemp = new ArrayList<>();
		
		for (ClassCotaEx par : parametros) {
			int i = 0;
			while (par.getVagas() != 0) {
				switch (par.getNomeCota()) {
				case "c2": listaTemp = listas.get(1); break;
				case "c3": listaTemp = listas.get(2); break;
				case "c4": listaTemp = listas.get(3); break;
				case "c5": listaTemp = listas.get(4); break;
				case "c6": listaTemp = listas.get(5); break;
				case "c7": listaTemp = listas.get(6); break;
				case "c8": listaTemp = listas.get(7); break;
				case "c9": listaTemp = listas.get(8); break;
				case "c10": listaTemp = listas.get(9); break;
				default: listaTemp = listas.get(0); break;
				}
				
				if (listaTemp.size()>i) {
					CandidatoCham candTemp = listaTemp.get(i);
					if (Parametro.codDiferente(chamada, candTemp)) {
						candTemp.setCotachamada(par.getNomeCota());
						chamada.add(candTemp); i++; par.diminuiVagas(); vagasTotal--;
					}
					else {
						i++;
					}
				}
				else break;
			}
		}
		
		for (int i=0; i < 50; i++) {
			if (vagasTotal != 0) {
				parametros = remanejoVagas(parametros, chamada, listas, ultimasPosicoes);
				
				for (ClassCotaEx par : parametros) {
					int j = 0;
					while (par.getVagas() != 0) {
						switch (par.getNomeCota()) {
						case "c2": listaTemp = listas.get(1); break;
						case "c3": listaTemp = listas.get(2); break;
						case "c4": listaTemp = listas.get(3); break;
						case "c5": listaTemp = listas.get(4); break;
						case "c6": listaTemp = listas.get(5); break;
						case "c7": listaTemp = listas.get(6); break;
						case "c8": listaTemp = listas.get(7); break;
						case "c9": listaTemp = listas.get(8); break;
						case "c10": listaTemp = listas.get(9); break;
						default: listaTemp = listas.get(0); break;
						}
						
						if (listaTemp.size()>j) {
							CandidatoCham candTemp = listaTemp.get(j);
							if (Parametro.codDiferente(chamada, candTemp)) {
								candTemp.setCotachamada(par.getNomeCota());
								chamada.add(candTemp); j++; par.diminuiVagas(); vagasTotal--;
							}
							else {
								j++;
							}
						}
						else break;
					}
				}
			}
		}
		
		IntArquivo.listarChamada(chamada);
		
		for (CandidatoCham cand : chamada) {
			System.out.println(cand.toString());
		}
	}
	
	public static List<ClassCotaEx> remanejoVagas(List<ClassCotaEx> parametros, 
			List<CandidatoCham> chamada, List<ArrayList> listas, List<Integer> ultimasPosicoes) {
		
		int v5 = 0, v7 = 0, v9 = 0, v10 = 0;
		
		for (ClassCotaEx par : parametros) {
			if (par.getNomeCota() == "c5") v5 = par.getPeso();
			if (par.getNomeCota() =="c7") v7 = par.getPeso();
			if (par.getNomeCota() == "c9") v9 = par.getPeso();
			if (par.getNomeCota() == "c10") v10 = par.getPeso();
		}
		
		v5 -= v9; v7 -= v9;
		
		for (ClassCotaEx par : parametros) {
			if (par.getVagas() != 0) {
				int x = 1;
				par.diminuiVagas();
				
				if (par.getNomeCota() == "c2") {
					if (v5 > v7 && v5 > v10) {
						if (v7 > v10) {
							if (ultimasPosicoes.get(2) != 0 && Parametro.codDiferente2(chamada, listas.get(2))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c3") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(3) != 0 && Parametro.codDiferente2(chamada, listas.get(3))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c4") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(4) != 0 && Parametro.codDiferente2(chamada, listas.get(4))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c5") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(5) != 0 && Parametro.codDiferente2(chamada, listas.get(5))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c6") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(6) != 0 && Parametro.codDiferente2(chamada, listas.get(6))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c7") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(7) != 0 && Parametro.codDiferente2(chamada, listas.get(7))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c8") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(8) != 0 && Parametro.codDiferente2(chamada, listas.get(8))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c9") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(9) != 0 && Parametro.codDiferente2(chamada, listas.get(9))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c10") par2.aumentaVagas(x);
								}
							}
							else {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c1") par2.aumentaVagas(x);
								}
							}
						}
						else {
							if (ultimasPosicoes.get(3) != 0 && Parametro.codDiferente2(chamada, listas.get(3))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c4") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(2) != 0 && Parametro.codDiferente2(chamada, listas.get(2))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c3") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(4) != 0 && Parametro.codDiferente2(chamada, listas.get(4))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c5") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(5) != 0 && Parametro.codDiferente2(chamada, listas.get(5))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c6") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(7) != 0 && Parametro.codDiferente2(chamada, listas.get(7))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c8") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(6) != 0 && Parametro.codDiferente2(chamada, listas.get(6))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c7") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(8) != 0 && Parametro.codDiferente2(chamada, listas.get(8))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c9") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(9) != 0 && Parametro.codDiferente2(chamada, listas.get(9))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c10") par2.aumentaVagas(x);
								}
							}
							else {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c1") par2.aumentaVagas(x);
								}
							}
						}
					}
					else if (v7 > v10) {
						if (v5 > v10) {
							if (ultimasPosicoes.get(2) != 0 && Parametro.codDiferente2(chamada, listas.get(2))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c3") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(5) != 0 && Parametro.codDiferente2(chamada, listas.get(5))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c6") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(6) != 0 && Parametro.codDiferente2(chamada, listas.get(6))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c7") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(3) != 0 && Parametro.codDiferente2(chamada, listas.get(3))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c4") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(4) != 0 && Parametro.codDiferente2(chamada, listas.get(4))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c5") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(7) != 0 && Parametro.codDiferente2(chamada, listas.get(7))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c8") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(8) != 0 && Parametro.codDiferente2(chamada, listas.get(8))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c9") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(9) != 0 && Parametro.codDiferente2(chamada, listas.get(9))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c10") par2.aumentaVagas(x);
								}
							}
							else {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c1") par2.aumentaVagas(x);
								}
							}
						}
						else {
							if (ultimasPosicoes.get(5) != 0 && Parametro.codDiferente2(chamada, listas.get(5))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c6") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(2) != 0 && Parametro.codDiferente2(chamada, listas.get(2))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c3") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(6) != 0 && Parametro.codDiferente2(chamada, listas.get(6))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c7") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(3) != 0 && Parametro.codDiferente2(chamada, listas.get(3))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c4") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(7) != 0 && Parametro.codDiferente2(chamada, listas.get(7))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c8") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(4) != 0 && Parametro.codDiferente2(chamada, listas.get(4))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c5") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(8) != 0 && Parametro.codDiferente2(chamada, listas.get(8))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c9") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(9) != 0 && Parametro.codDiferente2(chamada, listas.get(9))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c10") par2.aumentaVagas(x);
								}
							}
							else {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c1") par2.aumentaVagas(x);
								}
							}
						}
					}
					else {
						if (v5 > v7) {
							if (ultimasPosicoes.get(3) != 0 && Parametro.codDiferente2(chamada, listas.get(3))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c4") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(5) != 0 && Parametro.codDiferente2(chamada, listas.get(5))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c6") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(7) != 0 && Parametro.codDiferente2(chamada, listas.get(7))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c8") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(2) != 0 && Parametro.codDiferente2(chamada, listas.get(2))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c3") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(4) != 0 && Parametro.codDiferente2(chamada, listas.get(4))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c5") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(6) != 0 && Parametro.codDiferente2(chamada, listas.get(6))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c7") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(8) != 0 && Parametro.codDiferente2(chamada, listas.get(8))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c9") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(9) != 0 && Parametro.codDiferente2(chamada, listas.get(9))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c10") par2.aumentaVagas(x);
								}
							}
							else {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c1") par2.aumentaVagas(x);
								}
							}
						}
						else {
							if (ultimasPosicoes.get(5) != 0 && Parametro.codDiferente2(chamada, listas.get(5))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c6") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(3) != 0 && Parametro.codDiferente2(chamada, listas.get(3))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c4") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(7) != 0 && Parametro.codDiferente2(chamada, listas.get(7))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c8") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(2) != 0 && Parametro.codDiferente2(chamada, listas.get(2))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c3") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(6) != 0 && Parametro.codDiferente2(chamada, listas.get(6))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c7") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(4) != 0 && Parametro.codDiferente2(chamada, listas.get(4))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c5") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(8) != 0 && Parametro.codDiferente2(chamada, listas.get(8))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c9") par2.aumentaVagas(x);
								}
							}
							else if (ultimasPosicoes.get(9) != 0 && Parametro.codDiferente2(chamada, listas.get(9))) {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c10") par2.aumentaVagas(x);
								}
							}
							else {
								for (ClassCotaEx par2 : parametros) {
									if (par2.getNomeCota() == "c1") par2.aumentaVagas(x);
								}
							}
						}
					}
				}
				if (par.getNomeCota() == "c3") {
					if (v5 > v7) {
						if (ultimasPosicoes.get(4) != 0 && Parametro.codDiferente2(chamada, listas.get(4))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c5") par2.aumentaVagas(x);
							}
						}
						else if (ultimasPosicoes.get(6) != 0 && Parametro.codDiferente2(chamada, listas.get(6))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c7") par2.aumentaVagas(x);
							}
						}
						else if (ultimasPosicoes.get(8) != 0 && Parametro.codDiferente2(chamada, listas.get(8))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c9") par2.aumentaVagas(x);
							}
						}
						else {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c1") par2.aumentaVagas(x);
							}
						}
					}
					else {
						if (ultimasPosicoes.get(6) != 0 && Parametro.codDiferente2(chamada, listas.get(6))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c7") par2.aumentaVagas(x);
							}
						}
						else if (ultimasPosicoes.get(4) != 0 && Parametro.codDiferente2(chamada, listas.get(4))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c5") par2.aumentaVagas(x);
							}
						}
						else if (ultimasPosicoes.get(8) != 0 && Parametro.codDiferente2(chamada, listas.get(8))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c9") par2.aumentaVagas(x);
							}
						}
						else {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c1") par2.aumentaVagas(x);
							}
						}
					}
				}
				if (par.getNomeCota() == "c4") {
					if (v5 > v10) {
						if (ultimasPosicoes.get(4) != 0 && Parametro.codDiferente2(chamada, listas.get(4))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c5") par2.aumentaVagas(x);
							}
						}
						else if (ultimasPosicoes.get(7) != 0 && Parametro.codDiferente2(chamada, listas.get(7))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c8") par2.aumentaVagas(x);
							}
						}
						else if (ultimasPosicoes.get(8) != 0 && Parametro.codDiferente2(chamada, listas.get(8))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c9") par2.aumentaVagas(x);
							}
						}
						else if (ultimasPosicoes.get(9) != 0 && Parametro.codDiferente2(chamada, listas.get(9))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c10") par2.aumentaVagas(x);
							}
						}
						else {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c1") par2.aumentaVagas(x);
							}
						}
					}
					else {
						if (ultimasPosicoes.get(7) != 0 && Parametro.codDiferente2(chamada, listas.get(7))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c8") par2.aumentaVagas(x);
							}
						}
						else if (ultimasPosicoes.get(4) != 0 && Parametro.codDiferente2(chamada, listas.get(4))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c5") par2.aumentaVagas(x);
							}
						}
						else if (ultimasPosicoes.get(8) != 0 && Parametro.codDiferente2(chamada, listas.get(8))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c9") par2.aumentaVagas(x);
							}
						}
						else if (ultimasPosicoes.get(9) != 0 && Parametro.codDiferente2(chamada, listas.get(9))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c10") par2.aumentaVagas(x);
							}
						}
						else {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c1") par2.aumentaVagas(x);
							}
						}
					}
				}
				if (par.getNomeCota() == "c5") {
					if (ultimasPosicoes.get(8) != 0 && Parametro.codDiferente2(chamada, listas.get(8))) {
						for (ClassCotaEx par2 : parametros) {
							if (par2.getNomeCota() == "c9") par2.aumentaVagas(x);
						}
					}
					else {
						for (ClassCotaEx par2 : parametros) {
							if (par2.getNomeCota() == "c1") par2.aumentaVagas(x);
						}
					}
				}
				if (par.getNomeCota() == "c6") {
					if (v7 > v10) {
						if (ultimasPosicoes.get(6) != 0 && Parametro.codDiferente2(chamada, listas.get(6))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c7") par2.aumentaVagas(x);
							}
						}
						else if (ultimasPosicoes.get(7) != 0 && Parametro.codDiferente2(chamada, listas.get(7))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c8") par2.aumentaVagas(x);
							}
						}
						else if (ultimasPosicoes.get(8) != 0 && Parametro.codDiferente2(chamada, listas.get(8))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c9") par2.aumentaVagas(x);
							}
						}
						else if (ultimasPosicoes.get(9) != 0 && Parametro.codDiferente2(chamada, listas.get(9))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c10") par2.aumentaVagas(x);
							}
						}
						else {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c1") par2.aumentaVagas(x);
							}
						}
					}
					else {
						if (ultimasPosicoes.get(7) != 0 && Parametro.codDiferente2(chamada, listas.get(7))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c8") par2.aumentaVagas(x);
							}
						}
						else if (ultimasPosicoes.get(6) != 0 && Parametro.codDiferente2(chamada, listas.get(6))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c7") par2.aumentaVagas(x);
							}
						}
						else if (ultimasPosicoes.get(8) != 0 && Parametro.codDiferente2(chamada, listas.get(8))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c9") par2.aumentaVagas(x);
							}
						}
						else if (ultimasPosicoes.get(9) != 0 && Parametro.codDiferente2(chamada, listas.get(9))) {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c10") par2.aumentaVagas(x);
							}
						}
						else {
							for (ClassCotaEx par2 : parametros) {
								if (par2.getNomeCota() == "c1") par2.aumentaVagas(x);
							}
						}
					}
				}
				if (par.getNomeCota() == "c7") {
					if (ultimasPosicoes.get(8) != 0 && Parametro.codDiferente2(chamada, listas.get(8))) {
						for (ClassCotaEx par2 : parametros) {
							if (par2.getNomeCota() == "c9") par2.aumentaVagas(x);
						}
					}
					else {
						for (ClassCotaEx par2 : parametros) {
							if (par2.getNomeCota() == "c1") par2.aumentaVagas(x);
						}
					}
				}
				if (par.getNomeCota() == "c8") {
					if (ultimasPosicoes.get(8) != 0 && Parametro.codDiferente2(chamada, listas.get(8))) {
						for (ClassCotaEx par2 : parametros) {
							if (par2.getNomeCota() == "c9") par2.aumentaVagas(x);
						}
					}
					else if (ultimasPosicoes.get(9) != 0 && Parametro.codDiferente2(chamada, listas.get(9))) {
						for (ClassCotaEx par2 : parametros) {
							if (par2.getNomeCota() == "c10") par2.aumentaVagas(x);
						}
					}
					else {
						for (ClassCotaEx par2 : parametros) {
							if (par2.getNomeCota() == "c1") par2.aumentaVagas(x);
						}
					}
				}
				if (par.getNomeCota() == "c9") {
					for (ClassCotaEx par2 : parametros) {
						if (par2.getNomeCota() == "c1") par2.aumentaVagas(x);
					}
				}
				if (par.getNomeCota() == "c10") {
					for (ClassCotaEx par2 : parametros) {
						if (par2.getNomeCota() == "c1") par2.aumentaVagas(x);
					}
				}
				return parametros;
			}
		}
		return parametros;
	}
	
}