package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import dominio.GestorVehiculo;
import dominio.Marca;
import dominio.Vehiculo;
import exceptions.ExceptionVehiculo;

class TestGeneral {

	@Test
	@Order(1)
	@DisplayName("Constructor Datos no validos")
	void test01() {
		try {
			Vehiculo v1= new Vehiculo("", "Luis", Marca.FIAT, 2010, 1200);
		}catch(ExceptionVehiculo ex) {
			assertEquals("Apellido Vacio",ex.getMessage());
		}catch(Exception ex) {
			fail("Esta linea no debería correrse");
		}
		try {
			Vehiculo v1= new Vehiculo("Paez", "", Marca.FIAT, 2010, 1200);
		}catch(ExceptionVehiculo ex) {
			assertEquals("Nombre Vacio",ex.getMessage());
		}catch(Exception ex) {
			fail("Esta linea no debería correrse");
		}
		try {
			Vehiculo v1= new Vehiculo("Paez", "Luis", Marca.FIAT, 2000, 1200);
		}catch(ExceptionVehiculo ex) {
			assertEquals("Modelo muy viejo",ex.getMessage());
		}catch(Exception ex) {
			fail("Esta linea no debería correrse");
		}
	}
	
	@Test
	@Order(2)
	@DisplayName("ToString")
	void test02() {
		try {
			Vehiculo v1= new Vehiculo("   paez   ", "  luis ", Marca.FIAT, 2010, 1200);
			assertEquals("PAEZ, L. (FIAT)", v1.toString());
			
			Vehiculo v2= new Vehiculo("   paez  gonzalez   ", "  luis    abel", Marca.FIAT, 2010, 1200);
			assertEquals("PAEZ GONZALEZ, L. A. (FIAT)", v2.toString());
		}catch(Exception ex) {
			fail("Esta linea no debería correrse");
		}
	}

	@Test
	@Order(3)
	@DisplayName("Equals")
	void test03() {
		try {
			Vehiculo v1= new Vehiculo("   paez  gonzalez ", "  luis    abel", Marca.FIAT, 2010, 1200);
			Vehiculo v2= new Vehiculo("   paez    gonzalez   ", "luis   abel", Marca.FORD, 2012, 1200);
			Vehiculo v3= new Vehiculo("   paez  gonzalez   ", "  luis abel   ", Marca.PEUGEOT, 2010, 1300);
			
			assertTrue(v1.equals(v2));
			assertFalse(v1.equals(v3));
		}catch(Exception ex) {
			fail("Esta linea no debería correrse");
		}
	}
	
	@Test
	@Order(4)
	@DisplayName("Gestor Singleton")
	void test04() {
		GestorVehiculo gv1 = GestorVehiculo.getIsntancia();
		GestorVehiculo gv2 = GestorVehiculo.getIsntancia();
		
		assertTrue(gv1.equals(gv2));
		assertTrue(gv1==gv2);
	}
	
	@Test
	@Order(5)
	@DisplayName("Cantidad de Vehiculos & AddVehiculo & Limpiar Simple")
	void test05() {
		GestorVehiculo gv = GestorVehiculo.getIsntancia();
		try {
			Vehiculo v1 = new Vehiculo("   paez  gonzalez ", "  luis    abel", Marca.FIAT, 2010, 1200);
			assertEquals(0, gv.getCantidadDeVehiculos());
			assertTrue(gv.addVehiculo(v1));
			assertEquals(1, gv.getCantidadDeVehiculos());
			gv.LimpiarVehiculos();
			assertEquals(0, gv.getCantidadDeVehiculos());
		}catch(Exception ex) {
			fail("Esta linea no debería correrse");
		}
	}
	
	@Test
	@Order(6)
	@DisplayName("Cantidad de Vehiculos & AddVehiculo & Limpiar Medio")
	void test06() {
		GestorVehiculo gv = GestorVehiculo.getIsntancia();
		gv.LimpiarVehiculos();
		try {
			Vehiculo v1 = new Vehiculo("   paez  gonzalez ", "  luis    abel", Marca.FIAT, 2010, 1200);
			Vehiculo v2 = new Vehiculo("   paez  gonzalez ", "  luis    abel", Marca.FIAT, 2010, 1200);
			Vehiculo v3 = new Vehiculo("   Rojas ", "  luis    abel", Marca.FIAT, 2010, 1200);
			assertEquals(0, gv.getCantidadDeVehiculos());
			
			assertTrue(gv.addVehiculo(v1));
			assertEquals(1, gv.getCantidadDeVehiculos());
			
			assertFalse(gv.addVehiculo(v2));
			assertEquals(1, gv.getCantidadDeVehiculos());
			
			assertTrue(gv.addVehiculo(v3));
			assertEquals(2, gv.getCantidadDeVehiculos());
		}catch(Exception ex) {
			fail("Esta linea no debería correrse");
		}
	}
	
	@Test
	@Order(7)
	@DisplayName("getVehiculo busqueda por parteApellidoDuenio ordenado por Cilindrada")
	void test07() {
		GestorVehiculo gv = GestorVehiculo.getIsntancia();
		gv.LimpiarVehiculos();
		try {
			Vehiculo v1 = new Vehiculo("Paez Gonzalez ", "Luis Abel", Marca.FIAT, 2010, 1200);
			Vehiculo v2 = new Vehiculo("Pardo Rojas", "Jorge", Marca.FIAT, 2010, 1000);
			Vehiculo v3 = new Vehiculo("Gonzalez", "Abel Marcos", Marca.FIAT, 2010, 1400);
			Vehiculo v4 = new Vehiculo("Gonzalez Rojas", "Jorge", Marca.FIAT, 2010, 1100);
			
			gv.addVehiculo(v1);
			gv.addVehiculo(v2);
			gv.addVehiculo(v3);
			gv.addVehiculo(v4);
			
			String parteApellidoBuscado1="GON";
			ArrayList<Vehiculo> esperado1= new ArrayList<Vehiculo>();
			esperado1.add(v4);
			esperado1.add(v1);
			esperado1.add(v3);
			
			//Descomentar y completar la siguiente linea de código
			Predicate<Vehiculo> p1 = new Predicate<Vehiculo>() {

				@Override
				public boolean test(Vehiculo t) {
					return t.getDuenioApellido().toLowerCase().contains(parteApellidoBuscado1.toLowerCase());
				}
			};
			
			
			//Descomentar y completar la siguiente linea de código
			Comparator<Vehiculo> com = new Comparator<Vehiculo>() {

				@Override
				public int compare(Vehiculo o1, Vehiculo o2) {
					if(o1.getCilindrada() < o2.getCilindrada())
						return -1;
					else if(o1.getCilindrada() > o2.getCilindrada())
						return 1;
					return 0;
				}
			};
			
 			ArrayList<Vehiculo> actual1 = gv.getVehiculos(p1,com);
 			
			assertEquals(esperado1.size(),actual1.size());
			for (int i = 0; i < esperado1.size(); i++) 
				assertEquals(esperado1.get(i),actual1.get(i));
			
			
			String parteApellidoBuscado2="PA";
			ArrayList<Vehiculo> esperado2= new ArrayList<Vehiculo>();
			esperado2.add(v2);
			esperado2.add(v1);
			
			//Descomentar y completar la siguiente linea de código
			Predicate<Vehiculo> p2 = new Predicate<Vehiculo>() {

				@Override
				public boolean test(Vehiculo t) {
					return t.getDuenioApellido().toLowerCase().contains(parteApellidoBuscado2.toLowerCase());
				}
			};
			
			ArrayList<Vehiculo> actual2 = gv.getVehiculos(p2,com);
			assertEquals(esperado2.size(),actual2.size());
			for (int i = 0; i < esperado2.size(); i++) 
				assertEquals(esperado2.get(i),actual2.get(i));
			
		}catch(Exception ex) {
			fail("Esta linea no debería correrse");
		}
	}
	
	@Test
	@Order(8)
	@DisplayName("getVehiculo busqueda por Limite Superior e inferior de modelo y orden por apellido y nombre")
	void test08() {
		GestorVehiculo gv = GestorVehiculo.getIsntancia();
		gv.LimpiarVehiculos();
		try {
			Vehiculo v1 = new Vehiculo("Gonzalez", "Luis", Marca.FIAT, 2011, 1200);
			Vehiculo v2 = new Vehiculo("Pardo Rojas", "Jorge", Marca.FIAT, 2015, 1000);
			Vehiculo v3 = new Vehiculo("Gonzalez", "Abel", Marca.FIAT, 2008, 1400);
			Vehiculo v4 = new Vehiculo("Gonzalez", "Jorge", Marca.FIAT, 2012, 1100);
			
			gv.addVehiculo(v1);
			gv.addVehiculo(v2);
			gv.addVehiculo(v3);
			gv.addVehiculo(v4);
			
			int limiteInferior1=2010;
			int limiteSuperior1=2016;
			ArrayList<Vehiculo> esperado1= new ArrayList<Vehiculo>();
			esperado1.add(v4);
			esperado1.add(v1);
			esperado1.add(v2);
			
			//Descomentar y completar la siguiente linea de código
			Predicate<Vehiculo> p1 = new Predicate<Vehiculo>() {

				@Override
				public boolean test(Vehiculo t) {
					return t.getModelo() >= limiteInferior1 && t.getModelo() <= limiteSuperior1;
				}
				
			};
			
			//Descomentar y completar la siguiente linea de código
			Comparator<Vehiculo> com = new Comparator<Vehiculo>() {

				@Override
				public int compare(Vehiculo o1, Vehiculo o2) {
					if(o1.getDuenioApellido().compareTo(o2.getDuenioApellido()) == 0)
						return o1.getDuenioNombre().compareTo(o2.getDuenioNombre());
					return o1.getDuenioApellido().compareTo(o2.getDuenioApellido());
				}
				
			};
			
 			ArrayList<Vehiculo> actual1 = gv.getVehiculos(p1,com);
 			
			assertEquals(esperado1.size(),actual1.size());
			for (int i = 0; i < esperado1.size(); i++) 
				assertEquals(esperado1.get(i),actual1.get(i));
			
			
			int limiteInferior2=2011;
			int limiteSuperior2=2014;
			ArrayList<Vehiculo> esperado2= new ArrayList<Vehiculo>();
			esperado2.add(v4);
			esperado2.add(v1);
			
			//Descomentar y completar la siguiente linea de código
			Predicate<Vehiculo> p2 = new Predicate<Vehiculo>() {

				@Override
				public boolean test(Vehiculo t) {
					return t.getModelo() >= limiteInferior2 && t.getModelo() <= limiteSuperior2;
				}
				
			};
			
			ArrayList<Vehiculo> actual2 = gv.getVehiculos(p2,com);
			assertEquals(esperado2.size(),actual2.size());
			for (int i = 0; i < esperado2.size(); i++) 
				assertEquals(esperado2.get(i),actual2.get(i));
			
		}catch(Exception ex) {
			fail("Esta linea no debería correrse");
		}
	}	
	
}
