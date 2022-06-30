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
	
//	private VehiculoUtils v= new VehiculoUtils(); 
	
	
	public Vehiculo(String apellido, String nombre, Marca marca, int modelo, double cilindrada) throws ExceptionVehiculo{
		super();
		if (nombre.length()<=0 || nombre.equals(null)) 
			throw new ExceptionNombreVacio();
		else 
			this.nombre = VehiculoUtils.quitaEspaciosDeMas(nombre);
		
		if (apellido.length()<0 || apellido.equals(null)) 
			throw new ExceptionApellidoVacio();
		else 
			this.apellido = VehiculoUtils.quitaEspaciosDeMas(apellido);
		
		this.marca = marca;
		
		if (modelo<2001 ) 
			throw new ExceptionModeloViejo();
		else 
			this.modelo= modelo;
		
		this.cilindrada = cilindrada;
		
		System.out.println(apellido+" "+nombre);
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
		return VehiculoUtils.quitaEspaciosDeMas(apellido).toUpperCase()+", "+ VehiculoUtils.primeraLetraMayus(nombre)+"("+marca+")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cilindrada);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Vehiculo other = (Vehiculo) obj;
//		if(nombre.equals(other.nombre) && apellido.equals(other.apellido) && cilindrada == other.cilindrada)
//			return true;
//		if (apellido == null) {
//			if (other.apellido != null)
//				return false;
//		} else if (!apellido.equals(other.apellido))
//			return false;
//		if (Double.doubleToLongBits(cilindrada) != Double.doubleToLongBits(other.cilindrada))
//			return false;
//		if (nombre == null) {
//			if (other.nombre != null)
//				return false;
//		} else if (!nombre.equals(other.nombre))
//			return false;
//		return true;
//	}
	
	public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Vehiculo)) return false;
        if (!super.equals(object)) return false;
        Vehiculo vehiculo = (Vehiculo) object;
        return java.lang.Double.compare(vehiculo.cilindrada, cilindrada) == 0 && nombre.equals(vehiculo.nombre) && apellido.equals(vehiculo.apellido);
    }



}
