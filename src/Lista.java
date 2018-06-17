
public class Lista <T>{
	private No head;
	private No tail;

	public void addEnd(T dado) {
		No newNo = new No(dado);
		if (head == null) {
			head = tail = newNo;
		} else {
			newNo.setAnt(tail);
			tail.setPrx(newNo);
			tail = newNo;
		}
	}
	
	public void addBeg(T dado) {
		No newNo = new No(dado);
		if (head == null) {
			head = tail = newNo;
		} else {
			newNo.setPrx(head);
			head.setAnt(newNo);
			head = newNo;
		}
	}

	public No getHead() {
		return this.head;
	}

	public No getTail() {
		return this.tail;
	}
}
