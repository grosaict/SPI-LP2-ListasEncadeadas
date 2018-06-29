import java.util.Random;

public class UsaLista {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int metodo, iteracoes=9;
		String data;
		Random r = new Random();
		
		Lista <String> l = new Lista <> ();
		
		for (int i=0;i<iteracoes;i++){								// inserção ordenada dos valores
			data = String.format("%03d", r.nextInt(iteracoes)+1);
/* debug */	System.out.println(i+" I "+data);
			l.insertInOrder(data);
/* debug */	System.out.println("   "+l.getHead().getData()+"-"+l.getTail().getData());
		}
		
		l.listar();
		
		data = String.format("%03d", (r.nextInt(iteracoes)-1));
/* debug */	System.out.print("\nAPAGAR >> "+data);
		System.out.println("\n\n"+l.remove(data)+"\n");

		l.listar();
	}

}
