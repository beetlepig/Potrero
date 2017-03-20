#include <Servo.h>

//DANGEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEER
float lecturaFotocelda=0;
//---------------------------------------------------------

Servo servo;

byte target;

short arrayValores[11];

int suma;

byte posicion;

unsigned long contadorTiempo;

unsigned long tiempo;

unsigned long tiempoDos;

unsigned long tiempoTres;

unsigned short posActual = 0;



const int analogInPin = A0;  // Analog input pin that the potentiometer is attached to



float sensorValue = 0;        // value read from the pot



void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  servo.attach(8);
  initialize();
  posActual = servo.read();
}

void loop() {
  // put your main code here, to run repeatedly:
  contadorTiempo=millis();
  if ((contadorTiempo-tiempo)>100 ){
      tiempo=contadorTiempo;
 // read the analog in value:
      
    sensorValue = analogRead(analogInPin);
   // outputValueServo = map(sensorValue, 0, 1023, 0, 180);




//DANGEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEER


    
    lecturaFotocelda = map (sensorValue, 0, 1023, 0, 1000);
    
//    Serial.println(lecturaFotocelda,DEC);

//---------------------------------------------------------------------



    
    /*         Serial.print("target: ");
              Serial.println(outputValueServo); */

    arrayValores[posicion]= lecturaFotocelda;

    if(posicion<9){
       posicion++;
    }else if(posicion==9) {
      sacarPromedioYEnviar(arrayValores);
      posicion=0;
    }


  }

 if ((contadorTiempo-tiempoDos)>10 ){
  tiempoDos=contadorTiempo;
 
    if (posActual != target) {
    // A favor de las manecillas
    if (posActual < target) {
      rotatePositive(target);
    }
    // Contrario a las manecillas
    if (posActual > target) {
      rotateNegative(target);
    }

    }
    }


}

void sacarPromedioYEnviar(short arrayVar[]){
  for(int i=0; i < 10 ; i++){
    suma+=arrayVar[i];
  }
      float promedio= suma/10;
      Serial.print("promedio:");
      Serial.println(promedio);

 //     Serial.print("lectura:");
  //    Serial.println(sensorValue);
      suma=0;
}

void initialize() {
 servo.write(180);
  delay(100);
  for (int i = 0; i <= 180; i++) { // turn the servo all one way
    servo.write(i);
    delay(20);
  }
 delay(100);
  for (int i = 180; i > 0; i--) { // then the other
    servo.write(i);
    delay(20);
  }
 delay(100);
  Serial.println("Initialization done");
}

void rotatePositive(int target) {

    posActual ++;
    servo.write(posActual);

 
}

void rotateNegative(int target) {

    posActual --;
    servo.write(posActual);
 

}




