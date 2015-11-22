package practica1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

public class AppFutbol{
	
	static Scanner in = new Scanner (System.in);
	HashMap<Integer, Equipo> mEquipo; //el integer será el idequipo
	HashMap<Integer, Jugador> mJugadore; //Integer será idjugador
	HashMap<Integer, Arbitro> mArbitro; // ..igual
	HashMap<Integer, Estadio> mEstadio = new HashMap<Integer, Estadio>(); //..igual
	ArrayList<Partido> mpartidos;
	
	public AppFutbol(){//Aquí se pueden cargar los datos o en un nuevo método
		
	}
	public void AltaEquipo(){
		
	}
	public void BajaEquipo(){
		
	}
	public void AltaJugador(){ //Se da de alta en un equipo y si no está en el sistema también
		
	}
	public void BajaJugador(){ // de un equipo, no del sistema
		
	}
	public void AltaArbitro(){
		
	}
	public void BajaArbitro(){
		
	}
	public void AltaEstadio(){ //del sistema
		//Declaraciones
		Object key;
		Iterator<Integer> it;
		int id, capacidad;
		String ciudad, direccion;
		Boolean bucle;
		//Pido la id y busco que la id no esté repetida
		do{
			id = EstadioId();
			bucle = false;
			it = mEstadio.keySet().iterator();
			while(it.hasNext()){
				key = it.next();
				if(mEstadio.get(key).GetEstadioId() == id){
					System.out.println("Ya hay un equipo con esa id");
					bucle = true;
				}
			}
		}while(bucle);
		//Como no está repetido creo el nuevo Equipo
		ciudad = EstadioCiudad();
		direccion = EstadioDireccion();
		capacidad = EstadioCapacidad();
		Estadio estadio = new Estadio(id, direccion, ciudad, capacidad);
		mEstadio.put(id, estadio);
		//TODO añadirlo a un equipo
		//Muestro los equipos
		//Añado a uno de los equipos listados
	}
	public void AltaPartido(){
		
	}
	public void BajaPartido(){

		
	}
	//DONE
	public void ListarEstadios(){
		if(mEstadio.isEmpty()){
			System.out.println("No hay estadios en el sistema");
		}
		else{
			System.out.println("Los estadios son:");
			for (Entry<Integer, Estadio> estadio : mEstadio.entrySet()){
				Estadio valor = estadio.getValue();
				System.out.print("El estadio con id: " + valor.GetEstadioId());
				System.out.print(" que está en la ciudad de: " + valor.GetEstadioCiudad());
				System.out.print(" en la calle: " + valor.GetEstadioDireccion());
				System.out.println(" y con capacidad para :" + valor.GetEstadioCapacidad());
			}
		}
	}
	public void ListarEquipos(){
		
	}
	public void ListarArbitros(){
		
	}
	public void ContarPartidos(){
		
	}
	public void ListarPartidos(){ //devuelve info del partido dada una fecha
		
	}
	public void ListarPartidosEquipo(){//Devuelve la info del partido dado un equipo
		
	}
	public void ListarJugadores(){//dada una posición en el campo
		
	}
	public void listarJugadoresEquipo(){//dado un equipo
		
	}
	public void Salvar(){
		
	}
	public void CargarDatos(){
		
	}
	//+ void CalcularCampeonTemporada() **OPCIONAL**
	//+ void CalcularPosicionesEquipos(lequipos) **OPCIONAL**
	
	//Métodos
	public int EstadioId(){
		int id;
		try{
			System.out.println("Introduce la id del estadio:");
			id = in.nextInt();
		}
		catch(Exception e){
			System.out.println("Error");
			id = EstadioId();
		}
		return id;
	}
	public String EstadioDireccion(){
		String direccion;
		try{
			System.out.println("Introduce la direccion del estadio:");
			direccion = in.next();
		}
		catch(Exception e){
			System.out.println("Error");
			direccion = EstadioDireccion();
		}
		return direccion;
	}
	public String EstadioCiudad(){
		String ciudad;
		try{
			System.out.println("Introduce la ciudad del estadio:");
			ciudad = in.next();
		}
		catch(Exception e){
			System.out.println("Error");
			ciudad = EstadioCiudad();
		}
		return ciudad;
	}
	public int EstadioCapacidad(){
		int capacidad;
		try{
			System.out.println("Introduce la capacidad del estadio:");
			capacidad = in.nextInt();
		}
		catch(Exception e){
			System.out.println("Error");
			capacidad = EstadioCapacidad();
		}
		return capacidad;
	}
}