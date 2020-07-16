package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Candidato;
import entities.CandidatoCham;

public class IntArquivo {

	static List<Candidato> candidatos = new ArrayList<>();
	static File dados = new File("C:\\Users\\jeiso\\OneDrive\\Área de Trabalho\\Teste-Sup-Horticultura-Bento.csv");
	static String sourceFile = dados.getParent();
	static String targetFile = sourceFile + "\\Bento.txt";
	static Integer vagasTotais;
	
	public static List<Candidato> listarCandidatos() {
		try(BufferedReader br = new BufferedReader(new FileReader(dados))) {
			String linhaCsv = br.readLine();

			while (linhaCsv != null) {
				String[] linha = linhaCsv.split(",");
				int ordem = Integer.parseInt(linha[13]);
				String codCandidato = linha[5];
				String nomeCandidato = linha[4];
				int inscricaoPS = Integer.parseInt(linha[6]);
				String acTemp = linha[12];
				acTemp = acTemp.replace(",", ".");
				Double acertos = Double.parseDouble(acTemp);
				Boolean[] cotas = new Boolean[10];
				cotas = ajusteCotas(inscricaoPS);

				candidatos.add(new Candidato(cotas, ordem, codCandidato, nomeCandidato, inscricaoPS, acertos));

				linhaCsv = br.readLine();
			}
		}
		catch(IOException e1) {
			System.out.println("Error reading file: " + e1.getMessage());
		}
		return candidatos;
	}

	public static void listarChamada(List<CandidatoCham> chamada) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))) {
			for (CandidatoCham pess : chamada) {
				bw.write(pess.toString());
				bw.newLine();
			}

			System.out.println(targetFile + " CREATED!");

		}
		catch(IOException e2) {
			System.out.println("Error writing file: " + e2.getMessage());
		}
	}
	
	public static Boolean[] ajusteCotas(int iPS) {
		Boolean[] c = new Boolean[10];
		if (iPS == 2) { c[0] = true; c[1] = true;	c[2] = true; c[3] = true; c[4] = true;
		c[5] = true; c[6] = true; c[7] = true; c[8] = true; c[9] = true; return c; }
		if (iPS == 3) { c[0] = true; c[1] = false; c[2] = true; c[3] = false; c[4] = true; 
		c[5] = false; c[6] = true; c[7] = false; c[8] = true; c[9] = false; return c; }
		if (iPS == 4) { c[0] = true; c[1] = false; c[2] = false; c[3] = true; c[4] = true; 
		c[5] = false; c[6] = false; c[7] = true; c[8] = true; c[9] = true; return c; }
		if (iPS == 5) { c[0] = true; c[1] = false; c[2] = false; c[3] = false; c[4] = true; 
		c[5] = false; c[6] = false; c[7] = false; c[8] = true; c[9] = false; return c; }
		if (iPS == 6) {	c[0] = true; c[1] = false; c[2] = false; c[3] = false; c[4] = false; 
		c[5] = true; c[6] = true; c[7] = true; c[8] = true; c[9] = true; return c; }
		if (iPS == 7) { c[0] = true; c[1] = false; c[2] = false; c[3] = false; c[4] = false; 
		c[5] = false; c[6] = true; c[7] = false; c[8] = true; c[9] = false; return c; }
		if (iPS == 8) { c[0] = true; c[1] = false; c[2] = false; c[3] = false; c[4] = false; 
		c[5] = false; c[6] = false; c[7] = true; c[8] = true; c[9] = true; return c; }
		if (iPS == 9) { c[0] = true; c[1] = false; c[2] = false; c[3] = false; c[4] = false; 
		c[5] = false; c[6] = false; c[7] = false; c[8] = true; c[9] = false; return c; }
		if (iPS == 10) { c[0] = true; c[1] = false; c[2] = false; c[3] = false; c[4] = false; 
		c[5] = false; c[6] = false; c[7] = false; c[8] = false; c[9] = true; return c; } 
		else { c[0] = true; c[1] = false; c[2] = false; c[3] = false; c[4] = false; 
		c[5] = false; c[6] = false; c[7] = false; c[8] = false; c[9] = false; return c; } 
	}
}

