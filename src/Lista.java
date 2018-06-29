
public class Lista <T extends Comparable <T>>{
	private No head;
	private No tail;
	private int tooSmall = 10, insertions = 0;
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
				newNo.setNext(no);				// newNo next aponta para o No
				newNo.setPrev(no.getPrev());	// newNo prev aponta para o prev do No
				no.getPrev().setNext(newNo);	// next do prev do No (agora tb do newNo) aponta para o newNo
				no.setPrev(newNo);				// No prev aponta para o newNo 
			}
		} else {
			setHead(newNo);
			setTail(newNo);
		}
		//if (this.insertions++ > 10){
			this.validJump = false;
		//}
	}

	public void append(T data, No no) {
		No newNo = new No(data);

		if (getHead() != null) {
			if (no == getTail()){
				newNo.setPrev(getTail());
				getTail().setNext(newNo);
				setTail(newNo);
			} else {
				newNo.setPrev(no);				// newNo prev aponta para o No
				newNo.setNext(no.getNext());	// newNo next aponta para o next do No
				no.getNext().setPrev(newNo);	// prev do next do No (agora tb do newNo) aponta para o newNo
				no.setNext(newNo);				// No next aponta para o newNo 
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
			auxNo = searchNo(data);
			if (auxNo != null && data.compareTo((T) auxNo.getData()) == 0){
				noToDelete = auxNo;
				while (noToDelete == auxNo) {					// para exluir mais de uma ocorrência em lista ORDENADA
					if (noToDelete == getHead()){
						if (noToDelete == getTail()){
							setHead(null);
							setTail(null);
						} else {
							noToDelete.getNext().setPrev(null);
							setHead(noToDelete.getNext());
						}
						noToDelete = null;						// para exluir mais de uma ocorrência em lista ORDENADA
					} else {
						if (noToDelete == getTail()){
							noToDelete.getPrev().setNext(null);
							setTail(noToDelete.getPrev());
						} else {
							noToDelete.getNext().setPrev(noToDelete.getPrev());
							noToDelete.getPrev().setNext(noToDelete.getNext());
						}
						auxNo = noToDelete.getPrev();
						if (data.compareTo((T) auxNo.getData()) == 0){
							noToDelete = auxNo;					// para exluir mais de uma ocorrência em lista ORDENADA
						}
					}
				}
//				remove(data);										// para exluir mais de uma ocorrência em lista DESORDENADA
				this.validJump = false;
				return "Dado "+data+" excluído com sucesso!";
			} else {
				return "Dado "+data+" não localizado na lista!";
			}
		} else {
			return "Lista vazia!!!";
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
			if (data.compareTo((T) auxNo.getData()) <= 0){
				while (data.compareTo((T) auxNo.getData()) > 0 ||
					   data.compareTo((T) auxNo.getPrev().getData()) <= 0){
//*debug*/			System.out.println(data+" getPrev "+auxNo.getPrev().getData());
					auxNo = auxNo.getPrev();	// retorna um
				}
				return auxNo;
			}
			if (auxNo.getJump() == null){
//*debug*/		System.out.println(data+" getNext "+auxNo.getNext().getData());
				auxNo = auxNo.getNext();		// avança um
			} else {
//*debug*/		System.out.println(data+" getJump "+auxNo.getJump().getData());
				auxNo = auxNo.getJump();		// avança vários
			}
		}
		return auxNo;
	}
	
	private void creatJumpIndex() {
		No auxNo = getHead(), backNo = getHead();
		int jumpNow = 0;
		while (auxNo != null){
			auxNo.setJump(null);
			if (jumpNow >= 5){
				backNo.setJump(auxNo);
				backNo = auxNo;
				jumpNow = 0;
			}
			jumpNow++;
			auxNo = auxNo.getNext();
		}
		this.insertions = 0;
		this.validJump = true;
	}

	public void listar(){
		No auxNo = getHead();

		if (auxNo == null){
			System.err.println("Lista Vazia!!!");
		}else{
			int i = 1;
			while (auxNo != null){
				if (i%20 != 0){
					System.out.print(auxNo.getData()+"   ");
				} else {
					System.out.println(auxNo.getData());
				}
				i++;
				auxNo = auxNo.getNext();
			}
		}
	}

}
