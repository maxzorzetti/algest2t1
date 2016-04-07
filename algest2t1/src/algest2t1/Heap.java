package algest2t1;

import java.util.Collection;

public class Heap {
	private Nodo[] tree;
	private int size;
	
	public Heap(Collection<Nodo> collection){
		this.size = 0;
		this.tree = new Nodo[collection.size()];
		
		for(Nodo nodo: collection){
			insert(nodo);
		}
	}
	
	private int pai(int i){ return (i-1)/ 2; }
	private int dir(int i){ return  2*i + 1; }
	private int esq(int i){ return  2*i + 2; }
	
	private void sift_up(int pos){		
		int pai = pai(pos);
	    if ( pai == pos ) return;

	    if ( tree[pai].getFrequencia() > tree[pos].getFrequencia() ) {
	      Nodo tmp = tree[pai];
	      tree[pai] = tree[pos];
	      tree[pos] = tmp;
	      sift_up ( pai );
	    }
	}
	
	public void insert(Nodo nodo){
		tree[size] = nodo;
		sift_up(size);
		size++;
	}
	
	private void sift_down(int pos){
		int pleft = esq(pos);
		int pright = dir(pos);
		int pmaior = pos;
		if(pleft < size && tree[pleft].getFrequencia() < tree[pmaior].getFrequencia()) pmaior = pleft;
		if(pright < size && tree[pright].getFrequencia() < tree[pmaior].getFrequencia()) pmaior = pright;

		if(pos != pmaior){
			Nodo aux = tree[pos];
			tree[pos] = tree[pmaior];
			tree[pmaior] = aux;
			sift_down(pmaior);
		} 
	}
	
	public Nodo get(){
		Nodo res = tree[0];
		Nodo tmp = tree[size-1];
		tree[--size] = null;
		tree[0] = tmp;
		sift_down( 0 );
		//TODO redo
		return res;
	}
	
	public int size(){
		return size;
	}
}
