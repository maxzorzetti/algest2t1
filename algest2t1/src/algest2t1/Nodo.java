package algest2t1;

import java.util.BitSet;

public class Nodo {
	private Character caractere;
	private int frequencia;
	private Nodo esquerda;
	private Nodo direita;
	private String codigo;
	
	public Nodo(Character caractere){
		this.caractere = caractere;
		this.frequencia = 0;
		this.direita = null;
		this.esquerda = null;
		this.codigo = null;
	}
	
	public Character getCaractere() {
		return caractere;
	}
	public int getFrequencia() {
		return frequencia;
	}
	public void addFrequencia() {
		this.frequencia++;
	}	
	public void setFrequencia(int freq){
		this.frequencia = freq;
	}
	public void setDireita(Nodo nodo){
		this.direita = nodo;
	}
	public void setEsquerda(Nodo nodo){
		this.esquerda = nodo;
	}	
	public Nodo dir(){
		return this.direita;
	}
	public Nodo esq(){
		return this.esquerda;
	}
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
	public String getCodigo(){
		return this.codigo;
	}
	
	public String toString(){
		String character;
		if(caractere == null) character = "null";
		else if(caractere == '\n') character = "LF";
		else if(caractere == '\r') character = "CR";
		else character = caractere.toString();
		
		return character + ":" + frequencia + ":" + codigo;
	}
}
