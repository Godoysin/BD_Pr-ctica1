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
	public int EquipoId(){
		int id;
		try{
			System.out.println("Introduce la id del equipo");
			id = in.nextInt();
		}
		catch(Exception e){
			System.out.println("Error");
			id = EquipoId();
		}
		return id;
	}
	public int EquipoPuntos(){
        int puntos;
        try{
        	System.out.println("Introduce los puntos del equipo");
        	puntos = in.nextInt();
        }
        catch(Exception e){
        	System.out.println("Error");
			puntos = EquipoPuntos();
        }
        return puntos;
    }
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
	public int PersonaId(){
        int id;
        try{
        	System.out.println("Introduce la id de la persona");
        	id = in.nextInt();
        }
        catch(Exception e){
        	System.out.println("Error");
        	id = PersonaId();
        }
        return id;
    }
	public String PersonaNombre(){
        String nombre;
        try{
        	System.out.println("Introduce el nombre de la persona");
        	nombre = in.next();
        }
        catch(Exception e){
        	System.out.println("Error");
        	nombre = PersonaNombre();
        }
        return nombre;
    }
	public String PersonaEmail(){
        String email;
        try{
        	System.out.println("Introduce el email de la persona");
        	email = in.next();
        }
        catch(Exception e){
        	System.out.println("Error");
        	email = PersonaEmail();
        }
        return email;
    }
	public String PersonaTlf(){

        String tlf;
        try{
        	System.out.println("Introduce el tlf de la persona");
        	tlf = in.next();
        }
        catch(Exception e){
        	System.out.println("Error");
        	tlf = PersonaTlf();
        }
        return tlf;
    }
	public int JugadorSalario(){
        int salario;
        try{
        	System.out.println("Introduce el salario del jugador");
        	salario = in.nextInt();
        }
        catch(Exception e){
        	System.out.println("Error");
        	salario = JugadorSalario();
        }
        return salario;
    }
	public String JugadorPosicion(){
        int eleccion;
        String posicion;
        try{
        	do{
        		System.out.println("Introduce la posicion del jugador");
        		System.out.println("1-Delantero, 2-Centrocampista, 3-Defensa, 4-Portero");
        		eleccion = in.nextInt();
        	}while(eleccion != 1 && eleccion != 2 && eleccion != 3 && eleccion != 4);
        	posicion = JugadorEleccion(eleccion);
        }
        catch(Exception e){
        	System.out.println("Error");
        	posicion = JugadorPosicion();
        }
        return posicion;
    }
	public Boolean JugadorTitular(){
        Boolean titular,bucle;
        String aux;
        bucle = true;
        titular = false;
        try{
        	do{
        		System.out.println("Indica si el jugador es titular S/N");
        		aux = in.next();
        		if(aux.compareTo("S") == 0 || aux.compareTo("s") == 0){
        			titular = true;
        			bucle = false;
        		}
        		else if(aux.compareTo("N") == 0 || aux.compareTo("n") == 0){
        			titular = false;
        			bucle = false;
        		}
        		else{
        			System.out.println("Error");
        		}
        	}while(bucle);
        }
        catch(Exception e){
           	System.out.println("Error");
           	titular = JugadorTitular();
        }
        return titular;
    }
	public int JugadorNumero(){
        int numero;
        try{
        	System.out.println("Introduce el numero del jugador");
        	numero = in.nextInt();
        }
        catch(Exception e){
        	System.out.println("Error");
        	numero = JugadorNumero();
        }
        return numero;
    }
	public String JugadorEleccion(int num){
		String eleccion = "";
		if(num == 1){
			eleccion = "Delantero";
		}
		if(num == 2){
			eleccion = "Centrocampista";
		}
		if(num == 3){
			eleccion = "Defensa";
		}
		if(num == 4){
			eleccion = "Portero";
		}
		return eleccion;
	}
	public String ArbitroTipo(){
		int eleccion;
        String tipo;
        try{
        	do{
        		System.out.println("Introduce el tipo de arbitro");
        		System.out.println("1-Principal, 2-Asistente, 3-Cuarto, 4-Area");
        		eleccion = in.nextInt();
        	}while(eleccion != 1 && eleccion != 2 && eleccion != 3 && eleccion != 4);
        	tipo = ArbitroEleccion(eleccion);
        }
        catch(Exception e){
        	System.out.println("Error");
        	tipo = ArbitroTipo();
        }
        return tipo;
	}
	public String ArbitroEleccion(int num){
		String eleccion = "";
		if(num == 1){
			eleccion = "Principal";
		}
		if(num == 2){
			eleccion = "Asistente";
		}
		if(num == 3){
			eleccion = "Cuarto";
		}
		if(num == 4){
			eleccion = "Area";
		}
		return eleccion;
	}
	public int FechaAnio(){
		int anio;
		try{
			System.out.println("Introduce el año del partido");
			anio = in.nextInt();
		}
		catch(Exception e){
			System.out.println("Error");
			anio = FechaAnio();
		}
		return anio;
	}
	public int FechaMes(){
		int mes;
		try{
			do{
				System.out.println("Introduce el mes del partido");
				mes = in.nextInt();
			}while(mes <= 0 || mes >= 13);
		}
		catch(Exception e){
			System.out.println("Error");
			mes = FechaMes();
		}
		return mes-1;
	}
	public int FechaDia(){
		int dia;
		try{
			do{
				System.out.println("Introduce el día del partido");
				dia = in.nextInt();
			}while(dia <= 0 || dia >= 32);
		}
		catch(Exception e){
			System.out.println("Error");
			dia = FechaDia();
		}
		return dia;
	}
	public int FechaHora(){
		int hora;
		try{
			do{
				System.out.println("Introduce la hora del partido");
				hora = in.nextInt();
			}while(hora < 0 || hora >=24 );
		}
		catch(Exception e){
			System.out.println("Error");
			hora = FechaHora();
		}
		return hora;
	}
	public int FechaMinuto(){
		int minuto;
		try{
			do{
				System.out.println("Introduce el minuto del partido");
				minuto = in.nextInt();
			}while(minuto < 0 || minuto >=60 );
		}
		catch(Exception e){
			System.out.println("Error");
			minuto = FechaMinuto();
		}
		return minuto;
	}
}