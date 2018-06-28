
public class Vetor<T> {
	No head, tail;

	public Vetor(No head, No tail) {
		this.head = head;
		this.tail = tail;
	}

	public No searchSmallerNo(T data) {
		No smallerNo = null, auxNo = this.head;
		No[] index = null;
		int size = 0, increase = 100, start = 0, foward = 10, rewind = 5, max = foward, top = foward, min = 0;
		
		index = madeArray(index, size, increase);
		size = size + increase;
		
		while (smallerNo == null) {
			if (max > top){
				max = top;
			}
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
					top = max = i;
				}
			}
			if (isBetween(data, auxNo.getPrev().getData(), auxNo.getData())) {			// Se está entre ...
				smallerNo = auxNo.getPrev();											// então GOAL!
			} else {
				while (isTooAdvanced(data, auxNo.getPrev().getData(), auxNo.getData()) && smallerNo == null) {	// Se passou do ponto ...
					min = max - rewind;																			// ... então retrocede ...
					if (min <= 0){
						min = 1;
					}
/* debug */			System.out.println((min)+"R"+auxNo.getData());
					if (isBetween(data, auxNo.getPrev().getData(), auxNo.getData())) {	// Se está entre ...
						smallerNo = auxNo;												// ... então GOAL!
					} else {																
						if (!isFarBehind(data, auxNo.getPrev().getData(), auxNo.getData())) {	// Se não passou do ponto rebobina mais um pouco.
							min = min - rewind;
							if (min <= 0){
								min = 1;
							}
							auxNo = index[min];
						}
					}
				}
				if (min <= 0){														// Avança mais um pouco ...
					start = 1;
				} else {
					start = min;													// ... a partir de onde parou ...
				}
				foward = rewind / 2;												// ... reduzindo o avanço pela metade ...
				if (foward <= 0){
					foward = 1;
				}
				rewind = foward / 2;												// ... e o retorno também ...
				if (rewind <= 0){
					rewind = 1;
				}
				max = start + foward;
			}
		}
		return smallerNo;
	}
	
	private boolean isBetween (T data, Object anterior, Object seguinte){
/* debug */	System.out.println(anterior+" <= "+data+" <= "+seguinte);
		if (((String) data).compareTo((String) anterior) >= 0 &&
			((String) data).compareTo((String) seguinte) <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isTooAdvanced (T data, Object anterior, Object seguinte){
/* debug */	System.out.println(anterior+" > "+data+" < "+seguinte);
		if (((String) data).compareTo((String) anterior) < 0 &&
			((String) data).compareTo((String) seguinte) < 0) {
				return true;
			} else {
				return false;
			}
	}
	
	private boolean isFarBehind (T data, Object anterior, Object seguinte){
/* debug */	System.out.println(anterior+" < "+data+" > "+seguinte);
		if (((String) data).compareTo((String) anterior) > 0 &&
			((String) data).compareTo((String) seguinte) > 0) {
				return true;
			} else {
				return false;
			}
	}


	private No[] madeArray(No[] index, int size, int increase) {		// cria ou aumenta um vetor dinâmico de No's
		No[] newIndex = new No[size + increase];
		if (size != 0){
			for (int i=0; i < size; i++){
				newIndex[i] = index[i];
			}
		}
		return newIndex;
	}
}
