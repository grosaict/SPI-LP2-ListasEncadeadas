import java.util.Random;

public class UsaLista {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int metodo, iteracoes=20;
		String data;
		Random r = new Random();
		
		Lista <String> l = new Lista <> ();
		
		for (int i=0;i<iteracoes;i++){
			metodo = r.nextInt(2)+1;
			data = String.format("%03d", i);
			//data = Integer.toString(i);
			switch (metodo){
			case 1:
				//data = "In."+data;
				l.insert(data);
				break;
			case 2:
				//data = "Ap."+data;
				l.append(data);
				break;
			}
		}
		
		l.listar();
		
		data = String.format("%03d", (r.nextInt(iteracoes)-1));
		//data = Integer.toString(r.nextInt(iteracoes)-1);
		System.out.println("\n"+l.remove(data)+"\n");

		l.listar();
	}

}
