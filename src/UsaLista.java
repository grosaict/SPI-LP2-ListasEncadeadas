import java.util.Random;

public class UsaLista {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int metodo, iteracoes=100;
		String data;
		Random r = new Random();
		
		Lista <String> l = new Lista <> ();
		
		/*for (int i=0;i<iteracoes;i++){							// inserção de forma aleatória no início ou fim
			data = String.format("%03d", r.nextInt(iteracoes)+1);
			metodo = r.nextInt(2)+1;
			switch (metodo){
			case 1:
				//data = "In."+data;
				l.insertBefore(data, l.getHead());
				break;
			case 2:
				//data = "Ap."+data;
				l.append(data, l.getTail());
				break;
			}
		}*/
		
		for (int i=0;i<iteracoes;i++){								// inserção ordenada dos valores
			data = String.format("%03d", r.nextInt(iteracoes)+1);
/* debug */	System.out.println(i+" I "+data);
			l.insertInOrder(data);
/* debug */	System.out.println("   "+l.getHead().getData()+"-"+l.getTail().getData());
		}
		
		l.listar();
		
		data = String.format("%03d", (r.nextInt(iteracoes)-1));
		System.out.println("\n\n"+l.remove(data)+"\n");

		l.listar();
	}

}
