
public class Vetor <T extends Comparable <T>> {
	No head, tail;

	public Vetor(No head, No tail) {
		this.head = head;
		this.tail = tail;
	}

	public No searchNo(T data) {
		No foundNo = null, auxNo = this.head;
		No[] index = null;
		int size = 0, increase = 100, start = 0, foward = 10, rewind = 5, max = foward, top = foward, min = 0;
		
		index = madeArray(index, size, increase);
		size = size + increase;
		
		while (foundNo == null) {
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
			if (isBetween(data, (T) auxNo.getPrev().getData(), (T) auxNo.getData())) {			// Se está entre ...
				foundNo = auxNo;											// então GOAL!
			} else {
				while (isTooAdvanced(data, (T) auxNo.getPrev().getData(), (T) auxNo.getData()) && foundNo == null) {	// Se passou do ponto ...
					min = max - rewind;																			// ... então retrocede ...
					if (min <= 0){
						min = 1;
					}
/* debug */			System.out.println((min)+"R"+auxNo.getData());
					if (isBetween(data, (T) auxNo.getPrev().getData(), (T) auxNo.getData())) {	// Se está entre ...
						foundNo = auxNo.getNext();												// ... então GOAL!
					} else {																
						if (!isFarBehind(data, (T) auxNo.getPrev().getData(), (T) auxNo.getData())) {	// Se não passou do ponto rebobina mais um pouco.
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
		return foundNo;
	}
	
	private boolean isBetween (T data, T anterior, T seguinte){
/* debug */	System.out.println(anterior+" <= "+data+" <= "+seguinte);
		if (data.compareTo(anterior) >= 0 &&
			data.compareTo(seguinte) <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isTooAdvanced (T data, T anterior, T seguinte){
/* debug */	System.out.println(anterior+" > "+data+" < "+seguinte);
		if (data.compareTo(anterior) < 0 &&
			data.compareTo(seguinte) < 0) {
				return true;
			} else {
				return false;
			}
	}
	
	private boolean isFarBehind (T data, T anterior, T seguinte){
/* debug */	System.out.println(anterior+" < "+data+" > "+seguinte);
		if (data.compareTo(anterior) > 0 &&
			data.compareTo(seguinte) > 0) {
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
