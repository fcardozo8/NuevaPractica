package dominio;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public class GestorVehiculo {
	private static GestorVehiculo gestor;
	private ArrayList<Vehiculo> misVehiculos;
	
	
	private GestorVehiculo() {
		misVehiculos = new ArrayList<Vehiculo>() ;
		
	}
	
	public static GestorVehiculo getIsntancia() {
		if (gestor==null) gestor = new GestorVehiculo();
		return gestor;
	}

	public Integer getCantidadDeVehiculos() {
		
		return misVehiculos.size();
	}

	public boolean addVehiculo(Vehiculo v1) {
		if(!YaEstaCargada(v1))
			return misVehiculos.add(v1);
		return false;
	}
	
	private boolean YaEstaCargada(Vehiculo v1) {
		return misVehiculos.stream().filter(p2->p2.equals(v1)).count()>0;
	}

	public void LimpiarVehiculos() {
		misVehiculos = new ArrayList<Vehiculo>() ;
		
	}
	
	
	
	
	
	
	
	

}
