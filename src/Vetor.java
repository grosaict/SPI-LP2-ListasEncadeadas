
public class Vetor<T> {
	No head, tail;

	public Vetor(No head, No tail) {
		this.head = head;
		this.tail = tail;
	}

	public No searchSmallerNo(T data) {
		No smallerNo = null, auxNo = this.head;
		No[] index;
		int size = 0, increase = 100, start = 0, foward = 10, rewind = 5, max = foward, min;
		
		index = madeArray(null, size, increase);
		size = size + increase;
		
		while (smallerNo == null) {
			for (int i=start; i<max; i++){
				if (i >= size){
					index = madeArray(index, size, increase);		// aumenta o vetor se necessário
					size = size + increase;
				}
/* debug */		System.out.println(i+"F"+auxNo.getData());
				index[i] = auxNo;
				if (auxNo != this.tail){
					auxNo = auxNo.getNext();
				} else {
					max = i;
				}
			}
/* debug */	System.out.println(data+"<="+auxNo.getData());
			if (((String) data).compareTo((String) auxNo.getData()) <= 0) {							// se ainda é <= que o valor atual ...
/* debug */		System.out.println(data+">="+auxNo.getPrev().getData());
				if (((String) data).compareTo((String) auxNo.getPrev().getData()) >= 0) {			// ... testa se o anterior é >= (goal) ...
					smallerNo = auxNo.getPrev();
				} else {																			// ... senão começa a rebobinar
/* debug */			System.out.println(data+"<"+auxNo.getPrev().getData());
					while (((String) data).compareTo((String) auxNo.getPrev().getData()) < 0 && smallerNo == null) {
/* debug */				System.out.println(data+"<"+auxNo.getPrev().getData());
						min = max - rewind;
						if (min <= 0){
							min = 1;
						}
/* debug */				System.out.println((min)+"R"+auxNo.getData());
	/* debug */			System.out.println(data+">="+auxNo.getPrev().getData());
						if (((String) data).compareTo((String) auxNo.getPrev().getData()) >= 0) {	// Se é >= que o anterior ...
	/* debug */				System.out.println(data+"<="+auxNo.getData());
							if (((String) data).compareTo((String) auxNo.getData()) <= 0) {			// ... testa se o anterior é <= (goal) ...
								smallerNo = auxNo;
							} else {																// ... senão rebobina mais um pouco
								rewind = rewind /2;
								if (rewind <= 0){
									rewind = 1;
								}
							}
						} else {
							if ((max - rewind) <= 0){
								start = 1;
							} else {
								start = max - rewind;
							}
							foward = rewind /2;
							if (foward <= 0){
								foward = 1;
							}
							rewind = foward /2;
							if (rewind <= 0){
								rewind = 1;
							}
							max = start + foward;
						}
						auxNo = index[min];
					}
				}
			} else {
				start = max;
				max = max + foward;
			}
		}
		return smallerNo;
	}
	
	private No[] madeArray(No[] index, int size, int increase) {		// cria ou aumenta um vetor dinâmico de No's
		if (size == 0){
			No[] newIndex = new No[size + increase];
			return newIndex;
		} else {
			No[] newIndex = new No[size + increase];
			for (int i=0; i < size; i++){
				newIndex[i] = index[i];
			}
			return newIndex;
		}
	}
}
