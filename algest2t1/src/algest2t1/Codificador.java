package algest2t1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Map;

public class Codificador {	
	public static Nodo criaArvore(Heap heap){
		while(true){
			Nodo n1 = heap.get();
			if(heap.size() == 0) return n1;
			Nodo n2 = heap.get();
			Nodo nodo = new Nodo(null);
			nodo.setFrequencia(n1.getFrequencia() + n2.getFrequencia());
			nodo.setEsquerda(n1);
			nodo.setDireita(n2);
			heap.insert(nodo);
		}
	}
	
	public static void codificaCaracteres(Nodo nodo){
		codificaCaracteres(nodo, "");
	}
	private static void codificaCaracteres(Nodo nodo, String codigo){
		if(nodo == null) return;
		if(nodo.dir() == null && nodo.esq() == null){
			nodo.setCodigo(codigo);
			System.out.println(nodo);
			return;
		}
		codificaCaracteres(nodo.esq(), codigo+"0");
		codificaCaracteres(nodo.dir(), codigo+"1");		
	}
	
	public static File codifica(File file, Map<Character, Nodo> mapa) throws IOException{
		try(BufferedReader in = new BufferedReader(new FileReader(file)); 
		PrintWriter out = new PrintWriter(new FileWriter(file.getName() + "_codificado.txt")) ){			
			int codePoint;
			Nodo nodo;
			while( (codePoint = in.read()) != -1){
				Character caractere = (char)codePoint;
				nodo = mapa.get(caractere);
				out.write(nodo.getCodigo());
			}			
		}
		return new File(file.getName() + "_codificado.txt");
	}
	
	public static File decodifica(File file, Map<Character, Nodo> mapa, Nodo raiz) throws FileNotFoundException, IOException{
		try(BufferedReader in = new BufferedReader(new FileReader(file)); 
		PrintWriter out = new PrintWriter(new FileWriter(file.getName() + "_descodificado.txt")) ){		
			
			int caminho;
			Character caractere;
			Decodificador dec = new Decodificador(raiz); 
			
			while( (caminho = in.read()) != -1){
				caractere = dec.processa(Character.toString((char)caminho));
				if(caractere != null) out.write(caractere);				
			}			
		}			
		return new File(file.getName() + "_descodificado.txt");
	}
	
	private static class Decodificador{
		Nodo raiz;
		Nodo pos;
		public Decodificador(Nodo raiz) {
			this.raiz = raiz;
			this.pos = raiz;
		}
		public Character processa(String caminho){
			if(caminho.equals("0"))pos = pos.esq();
			else pos = pos.dir();
			
			Character caractere = pos.getCaractere();
			if(caractere == null) return null;
			pos = raiz;
			return caractere;
		}
	}	
	
	public static void printLeaves(Nodo n){
		if(n == null) return;
		printLeaves(n.esq());
		if(n.dir() == null && n.esq() == null) 
			System.out.print(n.getCaractere() + ":" + n.getFrequencia() + ":" + n.getCodigo() + "   ");
		printLeaves(n.dir());
	}
	
	public static void print(Nodo n){
		print(n, "");
	}	
	private static void print(Nodo n, String s){
		if(n == null)return;          
		print(n.esq(), s+"\t");
		System.out.println(s + n.toString()); //caminhamento infixado 
		print(n.dir(), s+"\t");

	}
}
