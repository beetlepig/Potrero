package main;

import java.util.ArrayList;
import java.util.LinkedList;

import processing.core.PApplet;

public class Potrero {
	
	private int energiaAcumulada;
	
	static Potrero instanciaPotrero;
	
	ArrayList<Cabra> cabritas;
	
	PApplet app;
	
	boolean iniciado=false;
	
	int acumuladoConsumoSegundo;
	

	float promedioConsumoPorSeg;
	
    Thread promedio;
   
	

	
	private Potrero(){

		energiaAcumulada=1000;
		cabritas= new ArrayList<Cabra>();
        promedio= new Thread(promediarConsumo());
        promedio.start();

	
	}
	
	
	

	
 
	
	static  Potrero getInstancia(){      //AKA INIT
		
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

		for (int i = 0; i < cabritas.size(); i++) {
			Cabra cabri = cabritas.get(i);
			cabri.pintar();
			
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
						
                      
						for (int i = 0; i < cabritas.size(); i++) {
							Cabra cabri = cabritas.get(i);
							cabri.setEnergia(energiaAcumulada);
							
						}
						
					Thread.sleep(500);
					}catch (InterruptedException e) {
						// TODO: handle exception
					}
					
					
				}
				
			}
		};
		return r;
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
						// TODO: handle exception
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
