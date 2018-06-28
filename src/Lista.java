
public class Lista <T>{
	private No head;
	private No tail;

	public void insertBefore(T data, No no) {
		No newNo = new No(data);
		if (this.head != null) {
			if (no == this.head){
				newNo.setNext(this.head);
				this.head.setPrev(newNo);
				this.head = newNo;
			} else {
				newNo.setNext(no);				// newNo next aponta para o No
				newNo.setPrev(no.getPrev());	// newNo prev aponta para o prev do No
				no.getPrev().setNext(newNo);	// next do prev do No (agora tb do newNo) aponta para o newNo
				no.setPrev(newNo);				// No prev aponta para o newNo 

			}
		} else {
			this.head = this.tail = newNo;
		}
	}

	public void append(T data, No no) {
		No newNo = new No(data);
		if (this.head != null) {
			if (no == this.tail){
				newNo.setPrev(this.tail);
				this.tail.setNext(newNo);
				this.tail = newNo;
			} else {
				newNo.setPrev(no);				// newNo prev aponta para o No
				newNo.setNext(no.getNext());	// newNo next aponta para o next do No
				no.getNext().setPrev(newNo);	// prev do next do No (agora tb do newNo) aponta para o newNo
				no.setNext(newNo);				// No next aponta para o newNo 
			}
		} else {
			this.head = this.tail = newNo;
		}
	}
	
	public void insertInOrder(T data) {
		No newNo = new No(data);
		if (this.head != null) {
			if (((String) data).compareTo((String) this.head.getData()) <= 0) {
				insertBefore(data, this.head);
			} else {
				if (((String) data).compareTo((String) this.tail.getData()) >= 0) {
					append(data, this.tail);
				} else {
					append(data, this.tail);
				}
			}
		} else {
			this.head = this.tail = newNo;
		}
	}

	public String remove(T data){
		No no;
		if (head != null) {
			no = searchNo(data);
			if (no != null){
				no.getNext().setPrev(no.getPrev());
				no.getPrev().setNext(no.getNext());
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
		No no = this.head;
		while (no != null){
			// if (data.equals(no.getData())){
			// foi necessário usar o casting para funcionar o compareTo()
			if (((String) data).compareTo((String) no.getData()) == 0){
				break;
			}
			no = no.getNext();
		}
		return no;
	}
	
	public No getHead() {
		return this.head;
	}

	public No getTail() {
		return this.tail;
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

}
