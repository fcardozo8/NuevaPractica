package dominio;

public class VehiculoUtils {


	public String quitaEspaciosDeMas(String aux) {
		String [] array= aux.split(" ");
		String salida = "";
		for (int i = 0; i < array.length; i++) 
			if (array[i].length()>0)
				salida += " "+array[i];
		return salida.substring(1);
	}
	
	public  String primeraLetraMayus(String palabra) {
		String palabra2[]=quitaEspaciosDeMas(palabra).split(" ");
		String aux = "" ;
		
		for (int i = 0; i < palabra2.length; i++) {

			aux+=palabra2[i].toUpperCase().substring(0,1)+". ";
		}
		System.out.println(aux);
	
		return aux;
		
		
	}

}
