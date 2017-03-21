package main;


import processing.core.*;
import processing.serial.Serial;


public class Main extends PApplet {


	private Serial port;
	private boolean serial;
    private String arduinoInput;

    
  
	
	public void settings() {
		size(1200, 700);
	} 

	public void setup() {

		serial = true;
		if (serial) {
			for (int i = 0; i < Serial.list().length; i++) {
				
				System.out.println(Serial.list()[i]);
				
			}
			//ELEGIR EL PUERTO CORRESPONDIENTE DEL ARDUINO 
			int elegido = 1;
			port = new Serial(this, Serial.list()[elegido], 9600);
			println("Inicializa en: " + Serial.list()[elegido]);
		}
		
	
	}

	public void draw() {
		background(150);
		fill(255);
		

		if (serial && port.available()>0) {
	
				  arduinoInput = port.readStringUntil('\n'); 
					if(arduinoInput!=null){
						println(arduinoInput); 
						if(arduinoInput.contains(":")){
						String string = arduinoInput;
						String[] parts = string.split(":");
						String part1 = parts[0];
						String part2 = parts[1]; 
						if(part1.equalsIgnoreCase("promedio")){
							if(Potrero.getInstancia().iniciado){
							anadirEnergia(part2);
							}
							
						}
					}
					
						}
				
		
		}
		
	
   Potrero.getInstancia().pintar();

	}
	
	void initPotrero(){

		   
		  
		if(Potrero.getInstancia().cabritas.size()==0 && !Potrero.getInstancia().iniciado){
			Potrero.getInstancia().app=this;
			for (int i = 0; i < 50; i++) {
				Potrero.getInstancia().cabritas.add(new Cabra());
				
			}
			Potrero.getInstancia().iniciado=true;
			new Thread(Potrero.getInstancia().rondaUnSegundo()).start();
			new Thread(mandarServo()).start();
		
			
			}
	}
	
	void anadirEnergia(String instruccion){
		Potrero.getInstancia().addEnergia(Float.parseFloat(instruccion));
	}
	
	
	Runnable mandarServo(){
		Runnable r= new Runnable() {
			
			@Override
			public void run() {
			    while(true){
			    	try{
			    		if(Potrero.getInstancia().getEnergiaAcumulada()>0 && Potrero.getInstancia().getEnergiaAcumulada()<20000){
			    	int mapeo=(int)	map(Potrero.getInstancia().getEnergiaAcumulada(), 0, 20000, 0, 180);
			    	port.write(mapeo);
			    		}else if(Potrero.getInstancia().getEnergiaAcumulada()<=0){
			    			port.write(0);
			    		} else if (Potrero.getInstancia().getEnergiaAcumulada()>=20000){
			    			port.write(180);
			    		}
			    	
			    	//	System.out.println("mandado: "+ mapeo);
			    		Thread.sleep(1000);
			    	}catch(InterruptedException e){
			    		
			    		
			    	}
			    }
				
			}
		};
		
		return r;
	}
	

	


	public static void main(String[] args) {
		PApplet.main("main.Main");
	}

	public void mousePressed() {
		initPotrero();
	}

	public void mouseReleased() {
	
	}
}
