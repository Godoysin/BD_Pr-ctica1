package practica1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.io.*;	

public class AppFutbol{
	
	static Scanner in = new Scanner (System.in);
	HashMap<Integer, Equipo> mEquipo = new HashMap<Integer, Equipo>(); //el integer será el idequipo
	HashMap<Integer, Jugador> mJugador = new HashMap<Integer, Jugador>(); //Integer será idjugador
	HashMap<Integer, Arbitro> mArbitro = new HashMap<Integer, Arbitro>(); // ..igual
	HashMap<Integer, Estadio> mEstadio = new HashMap<Integer, Estadio>(); //..igual
	ArrayList<Partido> mPartido = new ArrayList<Partido>();
	
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
	//Done
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
	//DONE
	public void AltaArbitro(){
		//Declaraciones
		Arbitro arbitro;
		Personas persona;
		Object key;
		Iterator<Integer> it;
		int id;
		String nombre, email, tlf, tipo;
		Boolean bucle;
		//Pido la id y busco que no esté repetida
		do{
			id = PersonaId();
			bucle = false;
			it = mArbitro.keySet().iterator();
			while(it.hasNext()){
				key = it.next();
				if(mArbitro.get(key).GetPersonaId() == id){
					System.out.println("Ya hay un arbitro con esa id");
					bucle = true;
				}
			}
		}while(bucle);
		//Como no está repetido creo el nuevo Arbitro
		nombre = PersonaNombre();
		email = PersonaEmail();
		tlf = PersonaTlf();
		tipo = ArbitroTipo();
		persona = new Personas(id, nombre, email, tlf);
		arbitro = new Arbitro(persona, tipo);
		mArbitro.put(id, arbitro);
		//Caso de error
		if(id == -1 || nombre.compareTo("-1") == 0 || email.compareTo("-1") == 0
				|| tlf.compareTo("-1") == 0 || tipo.compareTo("-1") == 0){
			System.out.println("Ha habido un error");
			mArbitro.remove(id);
		}
	}
	//DONE
	public void BajaArbitro(){
		//Declaraciones
		Boolean bucle, mostrar, error;
		String aux;
		int id, borrar;
		Iterator<Integer> it;
		Object key;
		borrar = 0;
		if(mArbitro.isEmpty()){
			System.out.println("No hay arbitros en el sistema");
		}
		else{
			//Doy la opción de que se le muestren los equipos que hay para que elija uno
			bucle = mostrar = true;
			error = false;
			try{
				do{
					System.out.println("¿Desea que se le muestren los arbitros en el sistema? S/N");
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
					ListarArbitros();
				}
				//Borro el equipo por id
				bucle = true;
				do{
					id = PersonaId();
					it = mArbitro.keySet().iterator();
					while(it.hasNext()){
						key = it.next();
						if(mArbitro.get(key).GetPersonaId() == id){
							bucle = false;
							borrar = id;
						}
					}
				}while(bucle);
				mArbitro.remove(borrar);
			}
		}
	}
	//DONE
	public void AltaEstadio(){ //del sistema
		//Declaraciones
		Estadio estadio = null;
		Object key;
		Iterator<Integer> it;
		int id, capacidad, id2;
		String ciudad, direccion, aux;
		Boolean bucle, error, mostrar;
		if(mEquipo.isEmpty()){
			System.out.println("No hay equipos en el sistema");
		}
		else{
			//Pido la id y busco que la id no esté repetida
			error = false;
			mostrar = true;
			id2 = 0;
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
			//Muestro los equipos
			if(id == -1 || ciudad.compareTo("-1") == 0 || direccion.compareTo("-1") == 0 
					|| capacidad == -1){
				error = true;
			}
			else{
				//Creo el estadio
				estadio = new Estadio(id, direccion, ciudad, capacidad);
				//Doy la opcion de mostrar los equipos en los que meter al estadio
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
				//Meto al estadio en la base de datos y en un equipo
				mEstadio.put(id, estadio);
				//Pido que meta al jugador en un equipo
				bucle = true;
				do{
					System.out.println("Introduzca al estadio en un equipo");
					id2 = EquipoId();
					if(id2 == -1){
						bucle = false;
					}
					//Lo añado a un equipo
					it = mEquipo.keySet().iterator();
					while(it.hasNext()){
						key = it.next();
						if(mEquipo.get(key).GetEquipoId() == id2){
							mEquipo.get(key).AltaEstadio(estadio);
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
	public void AltaPartido(){
		//Declaraciones
		Estadio estadio;
		ArrayList<Arbitro> arbitro = new ArrayList<Arbitro>();
		ArrayList<Jugador> jugador1, jugador2;
		Equipo equipo1, equipo2;
		Boolean bucle, ida, aniadir, repetido;
		String aux;
		int ocurrencias, id, i, idequipo, idestadio, golesEq1, golesEq2, idarbitro, anio, mes, dia, hora, minuto;
		Iterator<Integer> it;
		Object key;
		estadio = null;
		equipo1 = equipo2 = null;
		ocurrencias = id = golesEq1 = golesEq2 = 0;
		aniadir = ida = true;
		jugador1 = jugador2 = null;
		//Tengo equipos
		if(mEquipo.isEmpty()){
			System.out.println("No hay equipos en el sistema");
		}
		else{
			//Tengo estadios
			if(mEstadio.isEmpty()){
				System.out.println("No hay estadios en el sistema");
			}
			else{
				//Tengo arbitros
				if(mArbitro.isEmpty()){
					System.out.println("No hay arbitros en el sistema");
				}
				else{
					it = mEquipo.keySet().iterator();
					while(it.hasNext()){
						key = it.next();
						if(mEquipo.get(key).ejugador.isEmpty() == false){
							ocurrencias++;
						}
					}
					//Tengo dos o más equipos con jugadores
					if(ocurrencias >= 2){
						//Compruebo que no se repita la id del partido
						do{
							bucle = false;
							id = PartidoId();
							for(i = 0; i < mPartido.size(); i++){
								if(mPartido.get(i).GetPartidoId() == id){
									System.out.println("Ya hay un partido con esa id");
									bucle = true;
								}
							}
						}while(bucle);
						//Añado los equipos
						it = mEquipo.keySet().iterator();
						while(it.hasNext()){
							key = it.next();
							if(mEquipo.get(key).ejugador.isEmpty() == false){
								System.out.println("El equipo con id: " + mEquipo.get(key).GetEquipoId()
										+ " tiene jugadores");
							}
						}
						do{
							bucle = true;
							System.out.println("Introduzca el primer equipo que juega el partido");
							idequipo = EquipoId();
							it = mEquipo.keySet().iterator();
							while(it.hasNext()){
								key = it.next();
								if(mEquipo.get(key).ejugador.isEmpty() == false && mEquipo.get(key).GetEquipoId() == idequipo){
									equipo1 = mEquipo.get(key);
									bucle = false;
								}
							}
						}while(bucle);
						do{
							bucle = true;
							System.out.println("Introduzca el segundo equipo que juega el partido");
							idequipo = EquipoId();
							it = mEquipo.keySet().iterator();
							while(it.hasNext()){
								key = it.next();
								if(mEquipo.get(key).ejugador.isEmpty() == false && mEquipo.get(key).GetEquipoId() == idequipo 
										&& mEquipo.get(key) != equipo1){
									equipo2 = mEquipo.get(key);
									bucle = false;
								}
							}
						}while(bucle);
						jugador1 = equipo1.ejugador;
						jugador2 = equipo2.ejugador;
						ListarPartidos();
						do{
							bucle = true;
							System.out.println("Introduzca el estadio donde se juega el partido");
							idestadio = EstadioId();
							it = mEstadio.keySet().iterator();
							while(it.hasNext()){
								key = it.next();
								if(mEstadio.get(key).GetEstadioId() == idestadio){
									bucle = false;
									estadio = mEstadio.get(key);
								}
							}
						}while(bucle);
						System.out.println("Equipo 1");
						golesEq1 = PartidoGoles();
						System.out.println("Equipo 2");
						golesEq2 = PartidoGoles();
						ida = PartidoIda();
						//Añado un árbitro
						ListarArbitros();
						do{
							bucle = true;
							System.out.println("Introduzca los árbitros del partido");
							idarbitro = PersonaId();
							it = mArbitro.keySet().iterator();
							while(it.hasNext()){
								key = it.next();
								if(mArbitro.get(key).GetPersonaId() == idarbitro){
									arbitro.add(mArbitro.get(key));
									bucle = false;
								}
							}
						}while(bucle);
						//Doy la opción de añadir más
						do{
							do{
								System.out.println("¿Quieres añadir otro árbitro? S/N");
								aux = in.next();
								if(aux.compareTo("S") == 0 || aux.compareTo("s") == 0){
									aniadir = true;
									bucle = false;
								}
								else if(aux.compareTo("N") == 0 || aux.compareTo("n") == 0){
									aniadir = false;
									bucle = false;
								}
								else{
									System.out.println("Error");
								}
							}while(bucle);
							if(aniadir){
								do{
									bucle = true;
									idarbitro = PersonaId();
									it = mArbitro.keySet().iterator();
									while(it.hasNext()){
										key = it.next();
										if(mArbitro.get(key).GetPersonaId() == idarbitro){
											repetido = false;
											for(i = 0; i < arbitro.size(); i++){
												if(arbitro.get(i).GetPersonaId() == idarbitro){
													System.out.println("Ese árbitro ya está en el partido");
													repetido = true;
												}
												if(repetido == false){
													arbitro.add(mArbitro.get(key));
												}
												bucle = false;
											}
										}
									}
								}while(bucle);
							}
						}while(aniadir);
					}
					//Introduzco la fecha
					System.out.println("Introduzca la fecha del partido");
					anio = FechaAnio();
					mes = FechaMes();
					dia = FechaDia();
					hora = FechaHora();
					minuto = FechaMinuto();
					Fecha fecha = new Fecha(anio, mes, dia, hora, minuto);
					Partido partido = new Partido(id, estadio, fecha, equipo1, equipo2, ida,
							arbitro, jugador1, jugador2, golesEq1, golesEq2);
					mPartido.add(partido);
				}
			}
		}
	}
	public void BajaPartido(){
		//Declaraciones
		Boolean bucle, mostrar, error;
		String aux;
		int id, i, borrar;
		if(mPartido.isEmpty()){
			System.out.println("No hay partidos en el sistema");
		}
		else{
			//Doy la opción de que se le muestren los equipos que hay para que elija uno
			bucle = mostrar = true;
			error = false;
			borrar = 0;
			try{
				do{
					System.out.println("¿Desea que se le muestren los partidos en el sistema? S/N");
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
					ListarPartidos();
				}
				//Borro el equipo por id
				bucle = true;
				do{
					id = PartidoId();
					for(i = 0; i < mPartido.size(); i++){
						if(mPartido.get(i).GetPartidoId() == id){
							bucle = false;
							borrar = id;
						}
					}
				}while(bucle);
				mPartido.remove(borrar);
			}
		}
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
	//DONE
	public void ListarArbitros(){
		if(mArbitro.isEmpty()){
			System.out.println("No hay arbitros en el sistema");
		}
		else{
			System.out.println("Los arbitros son:");
			for (Entry<Integer, Arbitro> arbitro : mArbitro.entrySet()){
				Arbitro valor = arbitro.getValue();
				System.out.print("El arbitro con id: " + valor.GetPersonaId());
				System.out.print(", de nombre: " + valor.GetPersonaNombre());
				System.out.print(", con email: " + valor.GetPersonaEmail());
				System.out.print(", con tlf: " + valor.GetPersonaTlf());
				System.out.println(" es: " + valor.GetArbitroTipo());
			}
		}
	}
	public void ContarPartidos(){
		//Declaraciones
		if(mPartido.isEmpty()){
			System.out.println("No hay partidos en el sistema");
		}
		else{
			System.out.println("Hay " + mPartido.size() + " partidos");
		}
	}
	public void ListarPartidos(){ //devuelve info del partido dada una fecha
		//Declaraciones
		int anio, mes, dia, i;
		if(mPartido.isEmpty()){
			System.out.println("No hay partidos en el sistema");
		}
		else{
			System.out.println("Selecciona el día del que mirar los partidos");
			anio = FechaAnio();
			mes = FechaMes();
			dia = FechaDia();
			for(i = 0; i < mPartido.size(); i++){
				if(mPartido.get(i).GetPartidoFecha().GetFechaAnio() == anio 
						&& mPartido.get(i).GetPartidoFecha().GetFechaMes() == mes
						&& mPartido.get(i).GetPartidoFecha().GetFechaDia() == dia){
					System.out.print("El partido con id: " + mPartido.get(i).GetPartidoId());
					System.out.print(", en el que juegan los equipos con id: " + mPartido.get(i).GetPartidoEquipo1().GetEquipoId());
					System.out.print(" y " + mPartido.get(i).GetPartidoEquipo2().GetEquipoId());
					System.out.print(" se jugó ");
					mPartido.get(i).GetPartidoFecha().GetFecha();
				}
			}
		}
	}
	public void ListarPartidosEquipo(){//Devuelve la info del partido dado un equipo
		//Declaraciones
		int id, i;
		Equipo equipo;
		Boolean bucle;
		Iterator<Integer> it;
		Object key;
		equipo = null;
		if(mPartido.isEmpty()){
			System.out.println("No hay partidos en el sistema");
		}
		else{
			do{
				bucle = true;
				id = EquipoId();
				it = mEquipo.keySet().iterator();
				while(it.hasNext()){
					key = it.next();
					if(mEquipo.get(key).GetEquipoId() == id){
						equipo = mEquipo.get(key);
						bucle = false;
					}
				}
			}while(bucle);
			for(i = 0; i < mPartido.size(); i++){
				if(mPartido.get(i).GetPartidoEquipo1() == equipo
						|| mPartido.get(i).GetPartidoEquipo2() == equipo){
					System.out.print("El equipo con id: " + equipo.GetEquipoId());
					System.out.print(", juega el partido con id: " + mPartido.get(i).GetPartidoId());
					if(mPartido.get(i).GetPartidoEquipo1() == equipo){
						System.out.println(", marcó " + mPartido.get(i).GetPartidoGolesEq1() + " goles");
					}
					else{
						System.out.println(", marcó " + mPartido.get(i).GetPartidoGolesEq2() + " goles");
					}
				}
			}
		}
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
		//Declaraciones
		int i;
		Iterator<Integer> it;
		Object key;
		try{
			//Salvo los equipos
			BufferedWriter bwEq = new BufferedWriter(new FileWriter("Equipo.txt"));
			if(mEquipo.isEmpty() == false){
				it = mEquipo.keySet().iterator();
				while(it.hasNext()){
					key = it.next();
					bwEq.write(String.valueOf(mEquipo.get(key).GetEquipoId()));
					bwEq.newLine();
					if(mEquipo.get(key).GetEquipoEstadio() == null){
						bwEq.write("0");
						bwEq.newLine();
					}
					else{
						bwEq.write("1");
						bwEq.newLine();
						bwEq.write(String.valueOf(mEquipo.get(key).GetEquipoEstadio().GetEstadioId()));
						bwEq.newLine();
						bwEq.write(mEquipo.get(key).GetEquipoEstadio().GetEstadioDireccion());
						bwEq.newLine();
						bwEq.write(mEquipo.get(key).GetEquipoEstadio().GetEstadioCiudad());
						bwEq.newLine();
						bwEq.write(String.valueOf(mEquipo.get(key).GetEquipoEstadio().GetEstadioCapacidad()));
						bwEq.newLine();
					}
					bwEq.write(String.valueOf(mEquipo.get(key).GetEquipoPuntos()));
					bwEq.newLine();
					if(mEquipo.get(key).ejugador.isEmpty()){
						bwEq.write("0");
						bwEq.newLine();
					}
					else{
						bwEq.write(String.valueOf(mEquipo.get(key).ejugador.size()));
						bwEq.newLine();
						for(i = 0; i < mEquipo.get(key).ejugador.size(); i++){
							bwEq.write(String.valueOf(mEquipo.get(key).ejugador.get(i).GetPersonaId()));
							bwEq.newLine();
							bwEq.write(mEquipo.get(key).ejugador.get(i).GetPersonaNombre());
							bwEq.newLine();
							bwEq.write(mEquipo.get(key).ejugador.get(i).GetPersonaEmail());
							bwEq.newLine();
							bwEq.write(mEquipo.get(key).ejugador.get(i).GetPersonaTlf());
							bwEq.newLine();
							bwEq.write(String.valueOf(mEquipo.get(key).ejugador.get(i).GetJugadorSalario()));
							bwEq.newLine();
							bwEq.write(mEquipo.get(key).ejugador.get(i).GetJugadorPosicion());
							bwEq.newLine();
							bwEq.write(String.valueOf(mEquipo.get(key).ejugador.get(i).GetJugadorTitular()));
							bwEq.newLine();
							bwEq.write(String.valueOf(mEquipo.get(key).ejugador.get(i).GetJugadorNumero()));
							bwEq.newLine();
						}
					}
					
				}
			}
			bwEq.close();
			//Salvo los estadios
			BufferedWriter bwEst = new BufferedWriter(new FileWriter("Estadio.txt"));
			if(mEstadio.isEmpty() == false){
				it = mEstadio.keySet().iterator();
				while(it.hasNext()){
					key = it.next();
					bwEst.write(String.valueOf(mEstadio.get(key).GetEstadioId()));
					bwEst.newLine();
					bwEst.write(mEstadio.get(key).GetEstadioDireccion());
					bwEst.newLine();
					bwEst.write(mEstadio.get(key).GetEstadioCiudad());
					bwEst.newLine();
					bwEst.write(String.valueOf(mEstadio.get(key).GetEstadioCapacidad()));
					bwEst.newLine();
					
				}
			}
			bwEst.close();
		}
		catch(IOException e){
			System.out.println("Error de E/S");
		}
	}
	public void CargarDatos(){
		//Declaraciones
		int i, id, idestadio, capacidad, idequipo, puntos, salario, numero, aux;
		String direccion, ciudad, nombre, email, tlf, posicion;
		Boolean titular;
		Equipo equipo;
		Estadio estadio;
		Jugador jugador;
		Personas persona;
		estadio = null;
		String linea = null;
		try{
			//Cargo los equipos
			BufferedReader brEq = new BufferedReader(new FileReader("Equipo.txt"));
			if(mEquipo.isEmpty()){
				while((linea = brEq.readLine()) != null){
					estadio = null;
					idequipo = Integer.parseInt(linea);
					aux = Integer.parseInt(linea);
					if(aux == 0){
						estadio = null;
					}
					else{
						idestadio = Integer.parseInt(linea);
						direccion = linea;
						ciudad = linea;
						capacidad = Integer.parseInt(linea);
						estadio = new Estadio(idestadio, direccion, ciudad, capacidad);
					}
					puntos = Integer.parseInt(linea);
					equipo = new Equipo(idequipo, puntos);
					if(estadio != null){
						equipo.AltaEstadio(estadio);
					}
					aux = Integer.parseInt(linea);
					if(aux == 0){
						jugador = null;
					}
					else{
						for(i = 0; i < Integer.parseInt(linea); i++){
							id = Integer.parseInt(linea);
							nombre = linea;
							email = linea;
							tlf = linea;
							salario = Integer.parseInt(linea);
							posicion = linea;
							titular = Boolean.parseBoolean(linea);
							numero = Integer.parseInt(linea);
							persona = new Personas(id, nombre, email, tlf);
							jugador = new Jugador(persona, salario, posicion, titular, numero);
							equipo.AltaJugador(jugador);
						}
					}
					mEquipo.put(idequipo, equipo);
				}
			}
			brEq.close();
			//Cargo los estadios
			BufferedReader brEst = new BufferedReader(new FileReader("Estadio.txt"));
			if(mEstadio.isEmpty()){
				while((linea = brEst.readLine()) != null){
					idestadio = Integer.parseInt(linea);
					direccion = linea;
					ciudad = linea;
					capacidad = Integer.parseInt(linea);
					estadio = new Estadio(idestadio, direccion, ciudad, capacidad);
					mEstadio.put(idestadio, estadio);
				}
			}
			brEst.close();
		}
		catch(IOException e){
			System.out.println("Error de E/S");
		}
	}
	//public void CalcularCampeonTemporada(){ **OPCIONAL**
	//public void CalcularPosicionesEquipos(){ **OPCIONAL**
	
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
	public int PartidoId(){
		int id = 1;
		try{
			System.out.println("Introduce la id del partido");
			id = in.nextInt();
		}
		catch(Exception e){
			System.out.println("Error");
			id = -1;
		}
		return id;
	}
	public Boolean PartidoIda(){
		Boolean ida,bucle;
        String aux;
        bucle = true;
        ida = false;
        try{
        	do{
        		System.out.println("Indica si el partido es de ida S/N");
        		aux = in.next();
        		if(aux.compareTo("S") == 0 || aux.compareTo("s") == 0){
        			ida = true;
        			bucle = false;
        		}
        		else if(aux.compareTo("N") == 0 || aux.compareTo("n") == 0){
        			ida = false;
        			bucle = false;
        		}
        		else{
        			System.out.println("Error");
        		}
        	}while(bucle);
        }
        catch(Exception e){
           	System.out.println("Error");
           	ida = false;
        }
        return ida;
	}
	public int PartidoGoles(){
		int goles = 1;
		try{
			System.out.println("Introduce los goles del equipo");
			goles = in.nextInt();
		}
		catch(Exception e){
			System.out.println("Error");
			goles = -1;
		}
		return goles;
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