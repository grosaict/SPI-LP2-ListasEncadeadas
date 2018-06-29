
public class Lista <T extends Comparable <T>>{
	private No head;
	private No tail;
	private int tooSmall = 10;

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
	
	public void listar(){
		No no;
		int i =1;
		no = getHead();

		if (no == null){
			System.err.println("Lista Vazia!!!");
		}else{
			while (no != null){
				if (i%20 == 0){
					System.out.println(no.getData());
				} else {
					System.out.print(no.getData()+"   ");
				}
				i++;
				no = no.getNext();
			}
		}
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
		No auxNo;

		if (getHead() != null) {
			auxNo = searchNo(data);
			if (auxNo != null && data.compareTo((T) auxNo.getData()) == 0){
				if (auxNo == getHead()){
					if (auxNo == getTail()){
						setHead(null);
						setTail(null);
					} else {
						auxNo.getNext().setPrev(null);
						setHead(auxNo.getNext());
					}
				} else {
					if (auxNo == getTail()){
						auxNo.getPrev().setNext(null);
						setTail(auxNo.getPrev());
					} else {
						auxNo.getNext().setPrev(auxNo.getPrev());
						auxNo.getPrev().setNext(auxNo.getNext());
					}
				}
				remove(data);									// para exluir mais de uma ocorrência
				return "Dado "+data+" excluído com sucesso!";
			} else {
				return "Dado "+data+" não localizado na lista!";
			}
		} else {
			return "Lista vazia!!!";
		}
	}
	
	private No searchNo(T data) {
		No foundNo;
		if (isTooSmall()){
			foundNo = seekOneByOne(data);
		} else {
			foundNo = seekJumping(data);
		}
		return foundNo;
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

	private No seekJumping(T data) {
		return null;
	}

}
