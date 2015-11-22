package practica1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

public class AppFutbol{
	
	static Scanner in = new Scanner (System.in);
	HashMap<Integer, Equipo> mEquipo = new HashMap<Integer, Equipo>(); //el integer será el idequipo
	HashMap<Integer, Jugador> mJugador = new HashMap<Integer, Jugador>(); //Integer será idjugador
	HashMap<Integer, Arbitro> mArbitro; // ..igual
	HashMap<Integer, Estadio> mEstadio = new HashMap<Integer, Estadio>(); //..igual
	ArrayList<Partido> mpartidos;
	
	public AppFutbol(){//Aquí se pueden cargar los datos o en un nuevo método
		
	}
	//DONE
	public void AltaEquipo(){
		//Declaraciones
		Object key;
		Iterator<Integer> it;
		int id, puntos;
		Boolean bucle;
		//Pido la id y busco que no esté repetida
		do{
			id = EquipoId();
			bucle = false;
			it = mEquipo.keySet().iterator();
			while(it.hasNext()){
				key = it.next();
				if(mEquipo.get(key).GetEquipoId() == id){
					System.out.println("Ya hay un equipo con esa id");
					bucle = true;
				}
			}
		}while(bucle);
		//Como no está repetido creo el nuevo Equipo
		puntos = EquipoPuntos();
		Equipo equipo = new Equipo(id, puntos);
		mEquipo.put(id, equipo);
		//Caso de error
		if(id == -1 || puntos == -1){
			System.out.println("Ha habido un error");
			mEquipo.remove(id);
		}
	}
	//DONE
	public void BajaEquipo(){
		//Declaraciones
		int id, borrar = 1;
		String aux;
		Boolean bucle, mostrar, error;
		Object key;
		Iterator<Integer> it;
		if(mEquipo.isEmpty()){
			System.out.println("No hay equipos en el sistema");
		}
		else{
			//Doy la opción de que se le muestren los equipos que hay para que elija uno
			bucle = mostrar = true;
			error = false;
			try{
				do{
					System.out.println("¿Desea que se le muestren los equipos en el sistema? S/N");
					aux = in.next();
					if(aux.compareTo("S") == 0 || aux.compareTo("s") == 0){
						mostrar = true;
						bucle = false;
					}
					else if(aux.compareTo("N") == 0 || aux.compareTo("n") == 0){
						mostrar = false;
						bucle = false;
					}
					else{
						System.out.println("Error");
					}
				}while(bucle);
			}
			catch(Exception e){
				System.out.println("Error");
				error = true;
			}
			if(error){
				System.out.println("Ha habido un error");
			}
			else{
				//Si quiere verlos
				if(mostrar){
					ListarEquipos();
				}
				//Borro el equipo por id
				bucle = true;
				do{
					id = EquipoId();
					it = mEquipo.keySet().iterator();
					while(it.hasNext()){
						key = it.next();
						if(mEquipo.get(key).GetEquipoId() == id){
							bucle = false;
							borrar = id;
						}
					}
				}while(bucle);
				mEquipo.remove(borrar);
			}
		}
	}
	//DONE
	public void AltaJugador(){ //Se da de alta en un equipo y si no está en el sistema también
		//Declaraciones
		Jugador jugador = null;
		Object key;
		Iterator<Integer> it;
		String nombre, email, tlf, posicion, aux;
		int id, id2, salario, num;
		Boolean bucle, titular, error, mostrar;
		if(mEquipo.isEmpty()){
			System.out.println("No hay equipos en el sistema");
		}
		else{
			//Pido la id y busco que no esté repetida
			bucle = mostrar = true;
			error = false;
			id2 = -1;
			do{
				id = PersonaId();
				bucle = false;
				it = mJugador.keySet().iterator();
				while(it.hasNext()){
					key = it.next();
					if(mJugador.get(key).GetPersonaId() == id){
						System.out.println("Ya hay un jugador con esa id");
						bucle = true;
					}
				}
			}while(bucle);
			//Como no está repetido creo el nuevo Jugador
			nombre = PersonaNombre();
			email = PersonaEmail();
			tlf = PersonaTlf();
			salario = JugadorSalario();
			posicion = JugadorPosicion();
			titular = JugadorTitular();
			num = JugadorNumero();
			if(id == -1 || nombre.compareTo("-1") == 0 || email.compareTo("-1") == 0 
					|| tlf.compareTo("-1") == 0 || salario == -1 || posicion.compareTo("-1") == 0
					|| num == -1){
				error = true;
			}
			else{
				//Creo el jugador
				Personas persona = new Personas(id, nombre, email, tlf);
				jugador = new Jugador(persona, salario, posicion, titular, num);
				//Doy la opcion de mostrar los equipos en los que meter al jugador
				bucle = true;
				try{
					do{
						System.out.println("¿Desea que se le muestren los equipos en el sistema? S/N");
						aux = in.next();
						if(aux.compareTo("S") == 0 || aux.compareTo("s") == 0){
							mostrar = true;
							bucle = false;
						}
						else if(aux.compareTo("N") == 0 || aux.compareTo("n") == 0){
							mostrar = false;
							bucle = false;
						}
						else{
							System.out.println("Error");
						}
					}while(bucle);
				}
				catch(Exception e){
					System.out.println("Error");
					error = true;
				}
			}
			if(error){
				System.out.println("Ha habido un error");
			}
			else{
				//Muestro los equipos
				if(mostrar){
					ListarEquipos();
				}
				//Meto al jugador en la base de datos y en un equipo
				mJugador.put(id, jugador);
				//Pido que meta al jugador en un equipo
				bucle = true;
				do{
					System.out.println("Introduzca al jugador en un equipo");
					id2 = EquipoId();
					if(id2 == -1){
						bucle = false;
					}
					it = mEquipo.keySet().iterator();
					while(it.hasNext()){
						key = it.next();
						if(mEquipo.get(key).GetEquipoId() == id2){
							mEquipo.get(key).AltaJugador(jugador);
							bucle = false;
						}
					}
				}while(bucle);
			}
			if(id2 == -1){
				System.out.println("Ha habido un error");
			}
		}
	}
	public void BajaJugador(){ // de un equipo, no del sistema
		//Declaraciones
		int i, id, borrar, ide;
		Boolean bucle, mostrar, error;
		String aux;
		Object key, borr;
		Iterator<Integer> it;
		if(mJugador.isEmpty()){
			System.out.println("No hay jugadores en el sistema");
		}
		else{
			//Doy la opción de que se le muestren los equipos que hay para que elija uno
			bucle = mostrar = true;
			error = false;
			borrar = 0;
			borr = null;
			try{
				do{
					System.out.println("¿Desea que se le muestren los jugadores en el sistema? S/N");
					aux = in.next();
					if(aux.compareTo("S") == 0 || aux.compareTo("s") == 0){
						mostrar = true;
						bucle = false;
					}
					else if(aux.compareTo("N") == 0 || aux.compareTo("n") == 0){
						mostrar = false;
						bucle = false;
					}
					else{
						System.out.println("Error");
					}
				}while(bucle);
			}
			catch(Exception e){
				System.out.println("Error");
				error = true;
			}
			if(error){
				System.out.println("Ha habido un error");
			}
			else{
				//Si quiere verlos
				if(mostrar){
					it = mEquipo.keySet().iterator();
					while(it.hasNext()){
						key = it.next();
						System.out.print("En el equipo de id: " +mEquipo.get(key).GetEquipoId());
						System.out.println(" están:");
						for(i = 0; i < mEquipo.get(key).ejugador.size(); i++){
							System.out.print("El jugador: " + mEquipo.get(key).ejugador.get(i).GetPersonaNombre());
							System.out.println(" con id: " + mEquipo.get(key).ejugador.get(i).GetPersonaId());
						}
					}
				}
				//Borro el equipo por id
				bucle = true;
				do{
					System.out.println("De qué equipo quieres borrar al jugador");
					ide = EquipoId();
					it = mEquipo.keySet().iterator();
					while(it.hasNext()){
						key = it.next();
						if(mEquipo.get(key).GetEquipoId() == ide){
							borr = key;
							bucle = false;
						}
					}
				}while(bucle);
				bucle = true;
				do{
					id = PersonaId();
					it = mEquipo.keySet().iterator();
					while(it.hasNext()){
						key = it.next();
						for(i = 0; i < mEquipo.get(borr).ejugador.size(); i++){
							borrar = id;
							bucle = false;
						}
					}
				}while(bucle);
				mEquipo.get(borr).BajaJugador(borrar);
			}
		}
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
		if(mEquipo.isEmpty()){
			System.out.println("No hay equipos en el sistema");
		}
		else{
			//Pido la id y busco que la id no esté repetida
			do{
				id = EstadioId();
				bucle = false;
				it = mEstadio.keySet().iterator();
				while(it.hasNext()){
					key = it.next();
					if(mEstadio.get(key).GetEstadioId() == id){
						System.out.println("Ya hay un estadio con esa id");
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
	}
	public void AltaPartido(){
		
	}
	public void BajaPartido(){

		
	}
	//DONE
	public void ListarEquipos(){
		if(mEquipo.isEmpty()){
			System.out.println("No hay equipos en el sistema");
		}
		else{
			System.out.println("Los equipos son:");
			for (Entry<Integer, Equipo> equipo : mEquipo.entrySet()){
				Equipo valor = equipo.getValue();
				System.out.print("El equipo con id: " + valor.GetEquipoId());
				System.out.println(" y puntuación de: " + valor.GetEquipoPuntos());
			}
		}
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
				System.out.println(" y con capacidad para: " + valor.GetEstadioCapacidad());
			}
		}
	}
	public void ListarArbitros(){
		
	}
	public void ContarPartidos(){
		
	}
	public void ListarPartidos(){ //devuelve info del partido dada una fecha
		
	}
	public void ListarPartidosEquipo(){//Devuelve la info del partido dado un equipo
		
	}
	//DONE
	public void ListarJugadores(){//dada una posición en el campo
		//Declaraciones
		String posicion;
		if(mJugador.isEmpty()){
			System.out.println("No hay jugadores en el sistema");
		}
		else{
			System.out.println("Seleccione la posición");
			posicion = JugadorPosicion();
			if(posicion.compareTo("-1") == 0){
				System.out.println("Ha habido un error");
			}
			else{
				System.out.println("Los jugadores en la posición de " + posicion + " son:");
				for (Entry<Integer, Jugador> jugador : mJugador.entrySet()){
					Jugador valor = jugador.getValue();
					if(valor.GetJugadorPosicion() == posicion){
						System.out.print("El jugador con id: " + valor.GetPersonaId());
						System.out.print(", de nombre: " + valor.GetPersonaNombre());
						System.out.print(", con email: " + valor.GetPersonaEmail());
						System.out.print(", con tlf: " + valor.GetPersonaTlf());
						System.out.print(", gana: " + valor.GetJugadorSalario());
						System.out.print(", tiene el número: " + valor.GetJugadorNumero());
						if(valor.GetJugadorTitular()){
							System.out.println(" y es titular");
						}
						else{
							System.out.println(" y no es titular");
						}
					}
				}
			}
		}
	}
	//DONE
	public void ListarJugadoresEquipo(){//dado un equipo
		//Declaraciones
		int id, i;
		Boolean novacio = false, bucle = true;
		Iterator<Integer> it;
		Object key, elegido = null;
		if(mEquipo.isEmpty()){
			System.out.println("No hay equipos en el sistema");
		}
		else{
			//Compruebo que los equipos tengan jugadores
			it = mEquipo.keySet().iterator();
			while(it.hasNext()){
				key = it.next();
				if(mEquipo.get(key).ejugador.isEmpty() == false){
					novacio = true;
				}
			}
			if(novacio){
				do{
					System.out.println("Seleccione el equipo");
					id = EquipoId();
					if(id == -1){
						System.out.println("Ha habido un error");
					}
					it = mEquipo.keySet().iterator();
					while(it.hasNext()){
						key = it.next();
						if(mEquipo.get(key).GetEquipoId() == id){
							bucle = false;
							elegido = key;
						}
					}
				}while(bucle);
				System.out.println("Los jugadores en el equipo de id: " + mEquipo.get(elegido).GetEquipoId() + " son:");
				for(i = 0; i < mEquipo.get(elegido).ejugador.size(); i++){
					System.out.print("El jugador con id: " + mEquipo.get(elegido).ejugador.get(i).GetPersonaId());
					System.out.print(", de nombre: " + mEquipo.get(elegido).ejugador.get(i).GetPersonaNombre());
					System.out.print(", con email: " + mEquipo.get(elegido).ejugador.get(i).GetPersonaEmail());
					System.out.print(", con tlf: " + mEquipo.get(elegido).ejugador.get(i).GetPersonaEmail());
					System.out.print(", gana: " + mEquipo.get(elegido).ejugador.get(i).GetJugadorSalario());
					System.out.print(", tiene el número: " + mEquipo.get(elegido).ejugador.get(i).GetJugadorNumero());
					System.out.print(", juega de: " + mEquipo.get(elegido).ejugador.get(i).GetJugadorPosicion());
					if(mEquipo.get(elegido).ejugador.get(i).GetJugadorTitular()){
						System.out.println(" y es titular");
					}
					else{
						System.out.println(" y no es titular");
					}	
				}
			}
		}
	}
	public void Salvar(){
		
	}
	public void CargarDatos(){
		
	}
	//+ void CalcularCampeonTemporada() **OPCIONAL**
	//+ void CalcularPosicionesEquipos(lequipos) **OPCIONAL**
	
	//Métodos
	public int EquipoId(){
		int id = 1;
		//boolean error = false;
		try{
			System.out.println("Introduce la id del equipo");
			id = in.nextInt();
		}
		catch(Exception e){
			System.out.println("Error");
			id = -1;
		//	error = true;
		}
		//if(error){
		//	id = EquipoId();
		//}
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
			puntos = -1;
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
        	id = -1;
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
        	nombre = "-1";
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
        	email = "-1";
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
        	tlf = "-1";
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
        	salario = -1;
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
        	posicion = "-1";
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
           	titular = false;
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
        	numero = -1;
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
        	tipo = "-1";
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
			anio = -1;
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
			mes = -1;
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
			dia = -1;
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
			hora = -1;
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
			minuto = -1;
		}
		return minuto;
	}
}