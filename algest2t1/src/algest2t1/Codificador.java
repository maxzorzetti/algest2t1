package algest2t1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.ObjectInputStream.GetField;
import java.nio.file.Path;
import java.util.Map;

public class Codificador {	
	/*public static Nodo criaArvore(Heap heap){
		return criaArvore()
	}*/
	public static Nodo criaArvore(Heap heap){
		if(heap.size() == 1) return heap.get();
		Nodo n1 = heap.get();
		Nodo n2 = heap.get();
		Nodo nodo = new Nodo(null);
		nodo.setFrequencia(n1.getFrequencia() + n2.getFrequencia());
		nodo.setEsquerda(n1);
		nodo.setDireita(n2);
		heap.insert(nodo);
		return criaArvore(heap);
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
		int bits = 0;
		System.out.println(file.getName());
		System.out.println("InitialBits:\t" + file.length());
		try(BufferedReader in = new BufferedReader(new FileReader(file)); 
		PrintWriter out = new PrintWriter(new FileWriter(file.getName() + "_codificado." + getExt(file))) ){			
			int codePoint;
			Nodo nodo;
			while( (codePoint = in.read()) != -1){
				Character caractere = (char)codePoint;
				nodo = mapa.get(caractere);
				out.write(nodo.getCodigo());
				bits+= nodo.getCodigo().length();
			}			
		}
		System.out.println("FinalBits:\t" + bits);
		double compression = 1.0*bits/(file.length()*8);
		System.out.println(compression);
		System.out.println((1-(compression)));
		return new File(file.getName() + "_codificado." + getExt(file));
	}
/*	public static File codifica(File file, Map<Character, Nodo>mapa) throws IOException{
		try(BufferedReader in = new BufferedReader(new FileReader(file))){
			BinaryOut out = new BinaryOut(file.getName()+ "_codificado.txt");
			int codePoint;
			char[] codigo;
			while( (codePoint = in.read()) != -1){
				codigo = mapa.get((char)codePoint).getCodigo().toCharArray();
				for(char spips: codigo){
					if(spips == '1') out.write(true);
					else out.write(false);
				}
			}
			out.close();
		}		
		
		return file;		
	}*/
	
	public static File decodifica(File file, Map<Character, Nodo> mapa, Nodo raiz) throws FileNotFoundException, IOException{
		try(BufferedReader in = new BufferedReader(new FileReader(file)); 
		PrintWriter out = new PrintWriter(new FileWriter(file.getName() + "_decodificado." + getExt(file))) ){		
			
			int caminho;
			Character caractere;
			//Decodificador dec = new Decodificador(raiz); 
			Nodo deco = raiz;
			
			while( (caminho = in.read()) != -1){
				if((char)caminho == '0') deco = deco.esq();
				else if((char)caminho == '1') deco = deco.dir();
				else System.out.println("DEUPAUDEUPAUDEUPAU");
				caractere = deco.getCaractere();
				if(caractere != null){
					out.write(caractere);
					deco = raiz;
				}
			}			
		}			
		return new File(file.getName() + "_decodificado." + getExt(file));
	}

/*	public static File decodifica(File file, Map<Character, Nodo> mapa, Nodo raiz) throws FileNotFoundException, IOException{
		try(PrintWriter out = new PrintWriter(new FileWriter(file.getName() + "_descodificado.txt"))){
			BinaryIn in = new BinaryIn();
		}
		return file;
	}*/
	
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
	
	private static String getExt(File file){
		String name = file.getName();
		String ext = "bola";
		int i = name.lastIndexOf('.');
		if (i > 0) {
		    ext = name.substring(i+1);
		}					
		return ext;
	}
	
}
