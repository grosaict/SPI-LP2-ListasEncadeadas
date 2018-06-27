
public class Lista <T>{
	private No head;
	private No tail;

	public String remove(T data){
		No no;
		if (head != null) {
			no = searchNo(data);
			if (no != null){
				no.getNext().setPrev(no.getPrev());
				no.getPrev().setNext(no.getNext());
				return "Dado "+data+" excluído com sucesso!";
			} else {
				return "Dado "+data+" não localizado na lista!";
			}
		} else {
			return "Lista vazia!";
		}
	}
	
	private No searchNo(T data) {
		No no = this.head;
		while (no != null){
			System.out.println("'"+data+"' '"+no.getData()+"'");
			if (data == no.getData()){	// data é <T> e getData() é <Object>
				break;
			}
			no = no.getNext();
		}
		return no;
	}

	public void append(T data) {
		No newNo = new No(data);
		if (head != null) {
			newNo.setPrev(tail);
			tail.setNext(newNo);
			tail = newNo;
		} else {
			head = tail = newNo;
		}
	}
	
	public void insert(T data) {
		No newNo = new No(data);
		if (head != null) {
			newNo.setNext(head);
			head.setPrev(newNo);
			head = newNo;			
		} else {
			head = tail = newNo;
		}
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
		no = this.head;

		if (no == null){
			System.err.println("Lista Vazia!!!");
		}else{
			while (no != null){
				if (i%10 == 0){
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
