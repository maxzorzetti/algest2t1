package algest2t1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

public class App {
	public static void main(String args[]){
		long startTime = System.currentTimeMillis();		
		try {			
			File arquivoOriginal = Paths.get("arq1.txt").toFile();
			Map<Character, Nodo> mapa = Leitor.processa( arquivoOriginal );

			/*for(Nodo freq: mapa.values()){
				System.out.println(freq);
			}*/
			
			Heap heap = new Heap(mapa.values());
			
			Nodo raiz = Codificador.criaArvore(heap);			
			//Codificador.print(raiz);
			
			Codificador.codificaCaracteres(raiz);
			//Codificador.printLeaves(raiz);
			System.out.println("\nCodificando...");
			File arquivoCodificado = Codificador.codifica(arquivoOriginal, mapa);
			
			System.out.println("\nDecodificando...");			
			File arquivoDecodificado = Codificador.decodifica(arquivoCodificado, mapa, raiz);			
			
			System.out.print("\nComparando...");
			if(Leitor.compara(arquivoOriginal, arquivoDecodificado)) System.out.println(" arquivos iguais!");
			else System.out.println(" arquivos diferentes :(");

			System.out.println("\nDone!");
			
		} catch (IOException e) {
			System.out.println("fk");
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println();
		//System.out.println(endTime - startTime);
	}
}
