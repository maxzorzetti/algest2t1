package algest2t1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Leitor {
	public static Map<Character, Nodo> processa(File file) throws IOException{
		Map<Character, Nodo> mapa = new HashMap<Character, Nodo>();
		
		try(BufferedReader in = new BufferedReader(new FileReader(file))){
			int codePoint;
			while( (codePoint = in.read()) != -1){
				Character caractere = (char)codePoint;
				if(mapa.containsKey(caractere)){
					mapa.get(caractere).addFrequencia();
				} else{
					Nodo nodo = new Nodo(caractere);
					nodo.addFrequencia();
					mapa.put(caractere, nodo);
				}
			}
		}
		
		/*try(Scanner in = new Scanner(Files.newBufferedReader(path, Charset.forName("utf-8")))){
			in.useDelimiter("");
			//in.next();
			while(in.hasNext()){
				Character caractere = in.next().charAt(0);

				if(mapa.containsKey(caractere)){
					mapa.get(caractere).addFrequencia();
				} else{
					mapa.put(caractere, new Freq(caractere));
				}
			}
		}*/
		
		return mapa;
	}
}