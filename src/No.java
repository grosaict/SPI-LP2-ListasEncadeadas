
public class No <T>{
	public final T data;
	private No <T> prev;
	private No <T> next;
	private No <T> skip;

	public No (T data) {
		this.data = data;
		this.prev = null;
		this.next = null;
		this.skip = null;
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

	public No<T> getSkip() {
		return this.skip;
	}

	public void setSkip(No<T> skip) {
		this.skip = skip;
	}

	public T getData() {
		return this.data;
	}
}
