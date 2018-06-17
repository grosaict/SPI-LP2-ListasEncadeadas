import java.util.Random;

public class UsaLista {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int metodo, iteracoes=20;
		String dado;
		No no;
		Random r = new Random();
		
		Lista <String> l = new Lista <> ();
		
		for (int i=0;i<iteracoes;i++){
			metodo = r.nextInt(2)+1;
			dado = "Valor."+String.format("%04d", i);
			switch (metodo){
			case 1:
				dado = "Beg."+dado;
				l.addBeg(dado);
				break;
			case 2:
				dado = "End."+dado;
				l.addEnd(dado);
				break;
			}
		}
		
		no = l.getHead();

		if (no == null){
			System.err.println("Lista Vazia!!!");
		}else{
			while (no != null){
				System.out.println(no.getDado());
				no = no.getPrx();
			}
		}
	}

}
