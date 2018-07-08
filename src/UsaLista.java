import java.util.Random;

public class UsaLista {

	public static void main(String[] args) {
		int metodo, iteracoes=1000;
		String data;
		Random r = new Random();
		
		Lista <String> l = new Lista <> ();
		
		for (int i=0;i<iteracoes;i++){								// inserção dos valores aleatórios
			data = String.format("%04d", r.nextInt(iteracoes)+1);
			l.insertInOrder(data);
/* debug */	System.out.println(String.format("%04d",i)+" - "+data+" H."+l.getHead().getData()+" T."+l.getTail().getData());
		}
		
		listar(l.getHead());
		
		data = String.format("%04d", (r.nextInt(iteracoes)-1));
		System.out.println("\n"+l.remove(data)+"\n");

		listar(l.getHead());
	}
	
	public static void listar(No auxNo){						// lista a partir do nó enviado
		if (auxNo == null){
			System.err.println("Lista Vazia!!!");
		}else{
			int i = 1;
			while (auxNo != null){
				if (i%20 != 0){
					System.out.print(auxNo.getData()+"   ");
				} else {
					System.out.println(auxNo.getData());		// quebra a linha a cada 20 impressões
				}
				i++;
				auxNo = auxNo.getNext();
			}
		}
	}

}
