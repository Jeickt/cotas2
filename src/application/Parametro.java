package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entities.Candidato;
import entities.CandidatoCham;
import entities.ClassCotaEx;
import entities.VulnEx;

public class Parametro {
	
	private static Double c1Ac = 0.0;
	private static Integer c1Cont = 0;
	private static Double c5Ac = 0.0;
	private static Integer c5Cont = 0;
	private static Double c7Ac = 0.0;
	private static Integer c7Cont = 0;
	private static Double c9Ac = 0.0;
	private static Integer c9Cont = 0;
	private static Double c10Ac = 0.0; 
	private static Integer c10Cont = 0;
	
	private static List<VulnEx> ordemParametros = new ArrayList<>();
	private static List<ClassCotaEx> ordemCotas = new ArrayList<>();
		
	public static List<ClassCotaEx> ordenarPrioridadeCotas(List<Candidato> candidatos) {
		for (Candidato cand : candidatos) {
			if (cand.getC()[0] == true) { c1Ac += cand.getAcertos(); c1Cont++; }
			if (cand.getC()[4] == true) { c5Ac += cand.getAcertos(); c5Cont++; }
			if (cand.getC()[6] == true) { c7Ac += cand.getAcertos(); c7Cont++; }	
			if (cand.getC()[8] == true) { c9Ac += cand.getAcertos(); c9Cont++; }	
			if (cand.getC()[9] == true) { c10Ac += cand.getAcertos(); c10Cont++; }
		}
		
		double c5Media = (double) c5Ac / (c5Cont + 0.000001);
		double c7Media = (double) c7Ac / (c7Cont + 0.000001);
		double c9Media = (double) c9Ac / (c9Cont + 0.000001);
		double c10Media = (double) c10Ac / (c10Cont + 0.000001);
		
		VulnEx c5 = new VulnEx(c5Media, "c5"); VulnEx c7 = new VulnEx(c7Media, "c7");
		VulnEx c9 = new VulnEx(c9Media, "c9"); VulnEx c10 = new VulnEx(c10Media, "c10");
		ordemParametros.add(c5); ordemParametros.add(c7); ordemParametros.add(c9); ordemParametros.add(c10);
		Collections.sort(ordemParametros);
			
		int i = 0;
		for (VulnEx vuln : ordemParametros) {
			vuln.setPeso((int)Math.pow(2, (3 - i)));
			i++;
		}
		
		int cot1 = 0, cot2 = 0, cot3 = 0, cot4 = 0, cot5 = 0, cot6 = 0, cot7 = 0, cot8 = 0, cot9 = 0, cot10 = 0;
		
		for (VulnEx ord : ordemParametros) {
			if (ord.getVulnerabilidadeIsolada() == "c5") {
				cot2 += ord.getPeso();
				cot3 += ord.getPeso();
				cot4 += ord.getPeso();
				cot5 += ord.getPeso();
			}
			if (ord.getVulnerabilidadeIsolada() == "c7") {
				cot2 += ord.getPeso();
				cot3 += ord.getPeso();
				cot6 += ord.getPeso();
				cot7 += ord.getPeso();
			}
			if (ord.getVulnerabilidadeIsolada() == "c9") {
				cot2 += ord.getPeso();
				cot3 += ord.getPeso();
				cot4 += ord.getPeso();
				cot5 += ord.getPeso();
				cot6 += ord.getPeso();
				cot7 += ord.getPeso();
				cot8 += ord.getPeso();
				cot9 += ord.getPeso();
			}
			if (ord.getVulnerabilidadeIsolada() == "c10") {
				cot2 += ord.getPeso();
				cot4 += ord.getPeso();
				cot6 += ord.getPeso();
				cot8 += ord.getPeso();
				cot10 += ord.getPeso();
			}
		}
		
		int vagas1 = 8, vagas2 = 1, vagas3 = 1, vagas4 = 1, vagas5 = 2, vagas6 = 1, vagas7 = 1, vagas8 = 1, vagas9 = 1, vagas10 = 1;
		
		ClassCotaEx cota1 = new ClassCotaEx("c1", cot1, vagas1); ClassCotaEx cota2 = new ClassCotaEx("c2", cot2, vagas2);
		ClassCotaEx cota3 = new ClassCotaEx("c3", cot3, vagas3); ClassCotaEx cota4 = new ClassCotaEx("c4", cot4, vagas4);
		ClassCotaEx cota5 = new ClassCotaEx("c5", cot5, vagas5); ClassCotaEx cota6 = new ClassCotaEx("c6", cot6, vagas6);
		ClassCotaEx cota7 = new ClassCotaEx("c7", cot7, vagas7); ClassCotaEx cota8 = new ClassCotaEx("c8", cot8, vagas8);
		ClassCotaEx cota9 = new ClassCotaEx("c9", cot9, vagas9); ClassCotaEx cota10 = new ClassCotaEx("c10", cot10, vagas10);
		
		ordemCotas.add(cota1); ordemCotas.add(cota2); ordemCotas.add(cota3); ordemCotas.add(cota4); ordemCotas.add(cota5);
		ordemCotas.add(cota6); ordemCotas.add(cota7); ordemCotas.add(cota8); ordemCotas.add(cota9); ordemCotas.add(cota10);
		Collections.sort(ordemCotas);
		
		return ordemCotas;
	}
	
	public static ArrayList<CandidatoCham> fazerListaDeCota(List<Candidato> candidatos, int cota) {
		ArrayList<CandidatoCham> listaCota = new ArrayList<>();
		Boolean[] x = new Boolean[10];
		for(Candidato cand : candidatos) {
			x =	cand.getC();
			if (x[cota]) {
				listaCota.add(new CandidatoCham(cand.getInscricaoPS(), cand.getNomeCandidato(), cand.getCodCandidato()));
			}
		}
		return listaCota;
	}
	
	public static boolean codDiferente(List<CandidatoCham> chamada, CandidatoCham cand) {
		for (CandidatoCham cham : chamada) {
			if (cham.getCodCandidato().equals(cand.getCodCandidato()))
				return false;
		}
		return true;
	}
	
	public static boolean codDiferente2(List<CandidatoCham> chamada, ArrayList<CandidatoCham> listaCota) {
		Set<CandidatoCham> conjuntoVerif = new HashSet<>();
		for(CandidatoCham cham : chamada)
			conjuntoVerif.add(cham);
		for(CandidatoCham lC : listaCota) {
			if (!conjuntoVerif.contains(lC))
				return true;
		}
		return false;
	}
	
}
