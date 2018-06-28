
public class No<T>{
	public final T data;
	private No<T> prev;
	private No<T> next;

	public No (T data) {
		this.data = data;
		this.prev = null;
		this.next = null;
	}

	public No<T> getPrev() {
		return this.prev;
	}

	public void setPrev(No<T> prev) {
		this.prev = prev;
	}

	public No<T> getNext() {
		return this.next;
	}

	public void setNext(No<T> next) {
		this.next = next;
	}

	public T getData() {
		return this.data;
	}
}
