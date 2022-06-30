package dominio;

import exceptions.ExceptionApellidoVacio;
import exceptions.ExceptionModeloViejo;
import exceptions.ExceptionNombreVacio;
import exceptions.ExceptionVehiculo;
import dominio.VehiculoUtils;

public class Vehiculo {
	private String nombre;
	private String apellido;
	private Marca marca;
	private int modelo;
	private double cilindrada;
	
	private VehiculoUtils v= new VehiculoUtils(); 
	
	public Vehiculo(String apellido, String nombre, Marca marca, int modelo, double cilindrada) throws ExceptionVehiculo{
		super();
		if (nombre.length()<=0 || nombre.equals(null)) throw new ExceptionNombreVacio();
		else 
			this.nombre = nombre;
		
		if (apellido.length()<0 || apellido.equals(null)) throw new ExceptionApellidoVacio();
		else 
			this.apellido = apellido;
		
		this.marca = marca;
		
		if (modelo<2001 ) throw new ExceptionModeloViejo();
		else 
			this.modelo= modelo;
		
		this.cilindrada = cilindrada;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Marca getMarca() {
		return marca;
	}

	public int getModelo() {
		return modelo;
	}

	public double getCilindrada() {
		return cilindrada;
	}

	@Override
	public String toString() {
		return v.quitaEspaciosDeMas(apellido).toUpperCase()+", "+ v.primeraLetraMayus(nombre)+"("+marca+")";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (Double.doubleToLongBits(cilindrada) != Double.doubleToLongBits(other.cilindrada))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if(nombre.equals(other.nombre) && apellido.equals(other.apellido) && cilindrada == other.cilindrada)
			return true;
		return true;
	}
	
}
