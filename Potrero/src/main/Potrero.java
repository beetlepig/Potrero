package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import common.Message;
import communication.Communication_to_Server;
import logic.User;
import processing.core.PApplet;

public class Potrero {
	
	private int energiaAcumulada;
	
	static Potrero instanciaPotrero;
	
	ArrayList<Cabra> cabritas;
	
	PApplet app;
	
	boolean iniciado=false;
	
	boolean cambioEntreMensajes;
	
	int acumuladoConsumoSegundo;
	

	float promedioConsumoPorSeg;
	
    Thread promedio;
    
    List<Cabra> list;
    
    
	User user;
	Communication_to_Server cts;
	Message msg;
   
	

	
	private Potrero(){
		
		user = new User();
		cts = Communication_to_Server.getInstance(user) ;
		//aqui va la ip del server
		cts.setIp("127.0.0.1");

		energiaAcumulada=1000;
		cabritas= new ArrayList<Cabra>();
		list= Collections.synchronizedList(cabritas);
        promedio= new Thread(promediarConsumo());
        promedio.start();

	
	}
	
	
	

	
 
	
	static  Potrero getInstancia(){    
		
		  if(instanciaPotrero==null){
				instanciaPotrero= new Potrero();
				
		        return instanciaPotrero;
		        }else{
		        	
		        	return instanciaPotrero;
		        }
		
	
         }
	

	
	
	

	
	public void addEnergia(float lecturaFotocelda){
		energiaAcumulada+=lecturaFotocelda;
		//System.out.println("energia Acumulada: "+ energiaAcumulada);
	}
	
	public void pintar(){

		if(list!=null){
		synchronized(list) {
		    for (Cabra o : list) {
		    	o.pintar();
		    }
		}
		}
		
		
		if(app!=null){
		app.fill(255);
		app.text("consumo por segundo:"+ promedioConsumoPorSeg, 200, 300);
		app.text("Energia Acumuala:"+ energiaAcumulada, 200, 200);
		app.text("Numero Cabras:"+ cabritas.size(), 200, 400);
		}
	}
	
	Runnable rondaUnSegundo(){
		Runnable r= new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try{
						
                      if(!cambioEntreMensajes){
                          enviarCabras();
                          cambioEntreMensajes=true;
                      } else {
                    	  enviarEnergia();
                    	  cambioEntreMensajes=false;
                      }
						
						
						if(list!=null){
							synchronized(list) {
							    for (Cabra o : list) {
							    	o.setEnergia(energiaAcumulada);
							    }
							}
							}
						
						
					Thread.sleep(500);
					}catch (InterruptedException e) {
						
					}
					
					
				}
				
			}
		};
		return r;
	}
	
	private void enviarCabras(){
		// Datos para el mensaje
		int UserID = user.getId();
		// identificar que el mensaje es de las cabras
		String tipo = "goats";
		int cabras = list.size();
		System.out.println("cantidad cabras:" + cabras);
		cts.sendMessage(new Message(UserID, tipo, cabras));
	}
	
	private void enviarEnergia(){
		// Envia energia al servidor
		int UserID = user.getId();
		// identificar que el mensaje es del potrero
		String tipo = "energy";
		int energia = energiaAcumulada;
		cts.sendMessage(new Message(UserID, tipo, energia));
	}
	
	
	public void eliminarCabra(){
		list.remove(0);
	}
	
	public void anadirCabra(){
		list.add(new Cabra());
	}
	
	Runnable promediarConsumo(){
		Runnable r= new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try{
						promedioConsumoPorSeg=acumuladoConsumoSegundo/3;
						acumuladoConsumoSegundo=0;
						
						Thread.sleep(3000);
					}catch (InterruptedException e) {
						
					}
					
					
					
				}
				
			}
		};
		return r;

		
		
	}
	
	
	
	
	

	
	
	
	

	public int getEnergiaAcumulada(){
		return energiaAcumulada;
	}
	
	public void setEnergiaAcumulada(int energia){
		energiaAcumulada+=energia;
	}
}
