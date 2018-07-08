
public class Lista <T extends Comparable <T>>{
	private No head;
	private No tail;
	private int tooSmall = 50, insertions = 0, jump = 20;
	private boolean validJump = false;

	public No getHead() {
		return this.head;
	}

	public void setHead(No head) {
		this.head = head;
	}

	public No getTail() {
		return this.tail;
	}

	public void setTail(No tail) {
		this.tail = tail;
	}
	
	public void insertBefore(T data, No no) {
		No newNo = new No(data);

		if (getHead() != null) {
			if (no == getHead()){
				newNo.setNext(getHead());
				getHead().setPrev(newNo);
				setHead(newNo);
			} else {
				newNo.setNext(no);				// next do newNo aponta para o No
				newNo.setPrev(no.getPrev());	// prev do newNo aponta para o prev do No
				no.getPrev().setNext(newNo);	// next do prev do No aponta para o newNo
				no.setPrev(newNo);				// prev do No aponta para o newNo 
			}
		} else {
			setHead(newNo);
			setTail(newNo);
		}
		if (this.insertions++ > 10){
			this.validJump = false;
		}
	}

	public void append(T data, No no) {
		No newNo = new No(data);

		if (getHead() != null) {
			if (no == getTail()){
				newNo.setPrev(getTail());
				getTail().setNext(newNo);
				setTail(newNo);
			} else {
				newNo.setPrev(no);				// prev do newNo aponta para o No
				newNo.setNext(no.getNext());	// next do newNo aponta para o next do No
				no.getNext().setPrev(newNo);	// prev do next do No aponta para o newNo
				no.setNext(newNo);				// next do No aponta para o newNo 
			}
		} else {
			setHead(newNo);
			setTail(newNo);
		}
		//if (this.insertions > 10){
			this.validJump = false;
		//}
	}
	
	public void insertInOrder(T data) {
		No newNo = new No(data);
	
		if (getHead() != null) {
			if (data.compareTo((T) getHead().getData()) <= 0) {
				insertBefore(data, getHead());
			} else {
				if (data.compareTo((T) getTail().getData()) >= 0) {
					append(data, getTail());
				} else {
					insertBefore(data, searchNo(data));
				}
			}
		} else {
			setHead(newNo);
			setTail(newNo);
		}
	}

	public String remove(T data){
		No auxNo, noToDelete;
		if (getHead() != null) {
			noToDelete = searchNo(data);
			if (noToDelete != null && data.compareTo((T) noToDelete.getData()) == 0){
				removeAct (noToDelete);					// a ação de remoção em sí
				this.validJump = false;
				return "Dado "+data+" excluído com sucesso!";
			} else {
				return "Dado "+data+" não localizado na lista!";
			}
		} else {
			return "Lista vazia!!!";
		}
	}
	
	private void removeAct (No noToDelete) {
		if (noToDelete == getHead()){
			if (noToDelete == getTail()){
				setHead(null);							// exclusão do único nó da lista
				setTail(null);
			} else {
				noToDelete.getNext().setPrev(null);		// prev do next ao head passa a ser null
				setHead(noToDelete.getNext());			// next do head passa a ser o head
			}
		} else {
			if (noToDelete == getTail()){
				noToDelete.getPrev().setNext(null);		// next do prev do tail passa a ser null
				setTail(noToDelete.getPrev());			// prev do tail passa a ser o tail
			} else {
				noToDelete.getNext().setPrev(noToDelete.getPrev());	// prev do next passa a ser o prev do noToDelete
				noToDelete.getPrev().setNext(noToDelete.getNext());	// next do prev passa a ser o next do noToDelete
			}
		}
//		remove(data);									// para exluir mais de uma ocorrência em lista DESordenada
		if (noToDelete.getNext() != null){				// para exluir mais de uma ocorrência em lista ORDENADA
			if (((T) noToDelete.getData()).compareTo((T) noToDelete.getNext().getData()) == 0){
				removeAct (noToDelete.getNext());
			}
		}
	}
	
	private No searchNo(T data) {
		if (data.compareTo((T) getHead().getData()) <= 0){
			return getHead();
		} else {
			if (isTooSmall()){
				return seekOneByOne(data);
			} else {
				return seekJumping(data);
			}
		}
	}
	
	private boolean isTooSmall () {
		No auxNo = getHead();
		for (int i=0; i<this.tooSmall; i++){
			if (auxNo == getTail()){
				return true;
			}
			auxNo = auxNo.getNext();
		}
		return false;
	}

	private No seekOneByOne(T data) {
		No auxNo = getHead();
		while (auxNo != null){
			if (data.compareTo((T) auxNo.getData()) <= 0){
				break;
			}
			auxNo = auxNo.getNext();
		}
		return auxNo;
	}

	private No seekJumping (T data) {
		No auxNo = getHead();

		if (!this.validJump){					// se houverem mais de X inserções ou alguma exclusão, refaz os índices.
			creatJumpIndex();
		}
		while (auxNo != null){
			if (data.compareTo((T) auxNo.getData()) <= 0){						// se dado <= ao dado do auxNo
				while (data.compareTo((T) auxNo.getPrev().getData()) <= 0){		// enquanto dado <= ao dado do no anterior ao auxNo
					auxNo = auxNo.getPrev();	// retorna um
				}
				return auxNo;					// retorna sempre a ocorrência de "índice" menor
			}
			if (auxNo.getJump() == null){
				auxNo = auxNo.getNext();		// avança um
			} else {
				auxNo = auxNo.getJump();		// avança vários
			}
		}
		return auxNo;
	}
	
	private void creatJumpIndex() {
		No auxNo = getHead(), backNo = getHead();
		int jumpNow = 0;						// controla as iterações para saber o momento do salto
		while (auxNo != null){
			jumpNow++;
			auxNo.setJump(null);
			if (jumpNow >= this.jump){			// momento do salto
				backNo.setJump(auxNo);
				backNo = auxNo;
				jumpNow = 0;					// reinicia o controlador
			}
			auxNo = auxNo.getNext();
		}
		this.insertions = 0;
		this.validJump = true;
	}
}
