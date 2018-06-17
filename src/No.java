
public class No <T>{
	public final T dado;
	private No <T> ant;
	private No <T> prx;

	public No (T dado) {
		this.dado = dado;
		this.ant = null;
		this.prx = null;
	}

	public No<T> getAnt() {
		return this.ant;
	}

	public void setAnt(No<T> ant) {
		this.ant = ant;
	}

	public No<T> getPrx() {
		return this.prx;
	}

	public void setPrx(No<T> prx) {
		this.prx = prx;
	}

	public T getDado() {
		return this.dado;
	}
}
