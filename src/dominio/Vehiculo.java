package dominio;

import exceptions.ExceptionApellidoVacio;
import exceptions.ExceptionModeloViejo;
import exceptions.ExceptionNombreVacio;
import exceptions.ExceptionVehiculo;

public class Vehiculo {
	private String nombre;
	private String apellido;
	private Marca marca;
	private int modelo;
	private double cilindrada;

	
	
	public Vehiculo(String apellido, String nombre, Marca marca, int modelo, double cilindrada) throws ExceptionVehiculo{
		if (nombre.length()>0)
			this.nombre = VehiculoUtils.quitaEspaciosDeMas(nombre);
		else
			throw new ExceptionNombreVacio();
		
		if (apellido.length()>0)
			this.apellido = VehiculoUtils.quitaEspaciosDeMas(apellido);
		else
			throw new ExceptionApellidoVacio();

		
		this.marca = marca;
		
		if (modelo<2001 )
			throw new ExceptionModeloViejo();
		else 
			this.modelo= modelo;
		
		this.cilindrada = cilindrada;
		
//		System.out.println(this.apellido+" "+this.nombre);
	}

	public Marca getMarca() {
		return marca;
	}

	public String getDuenioApellido() {
		return apellido;
	}

	public String getDuenioNombre() {
		return nombre;
	}

	public int getModelo() {
		return modelo;
	}

	public double getCilindrada() {
		return cilindrada;
	}

	@Override
	public String toString() {
		return VehiculoUtils.quitaEspaciosDeMas(apellido).toUpperCase()+", "+ VehiculoUtils.primeraLetraMayus(nombre)+"("+marca+")";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Vehiculo)) return false;
		Vehiculo vehiculo = (Vehiculo) o;
		return Double.compare(vehiculo.cilindrada, cilindrada) == 0 && nombre.equals(vehiculo.nombre) && apellido.equals(vehiculo.apellido);
	}

}
