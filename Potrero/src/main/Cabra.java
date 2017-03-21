package main;



public class Cabra {
	
	int energiaAcumuladaCabra;

	boolean viva;
 

	float ranX;
	float ranY;
	
	public Cabra(){
		

        ranX= Potrero.getInstancia().app.random(0,Potrero.getInstancia().app.width);
         ranY= Potrero.getInstancia().app.random(0,Potrero.getInstancia().app.height);
		energiaAcumuladaCabra=150;
		viva=true;
		new Thread(existir()).start();;


	
	}
	
	public void deteleCabra(){
		viva=false;
	    Potrero.getInstancia().cabritas.remove(this);
	}
	

	
	public void setEnergia (int energiaPotrero){

			
	if(energiaPotrero>0 && energiaAcumuladaCabra<100){
						
		Potrero.getInstancia().setEnergiaAcumulada(-10);
		energiaAcumuladaCabra+=10;
				
	}
	
		
	}
	
	Runnable existir (){
		Runnable r= new Runnable() {
			
			@Override
			public void run() {
				while(viva){
					try{
						if(energiaAcumuladaCabra>0){
							
						energiaAcumuladaCabra-=10;
						Potrero.getInstancia().acumuladoConsumoSegundo+=10;
						}
						if(energiaAcumuladaCabra-10==0) {
							deteleCabra();
						}
						Thread.sleep(1000);
					}catch (InterruptedException e){
						
					}
				}
				System.err.println("cabra Muerta");
			}
		};
		return r;
	}
	
	public void pintar(){
		Potrero.getInstancia().app.fill(255);
		Potrero.getInstancia().app.ellipse(ranX,ranY, 30, 30);
		Potrero.getInstancia().app.text(energiaAcumuladaCabra, ranX-10, ranY-19);
	   
	}
	
	


}
