   //Declaramos el Package en el que se encontrara toda la practica
   package circuitos;

   /**
	*Clase MaquinaCafe
	*
	*Clase que implementa una m�quina de caf� de 3 surtidores que ser�n 3 circuitos.
	*Se le conectan 6 fuentes a modo de pulsadores de la m�quina.
	*
	*@author Fernando Diaz && Taoufik Aadia
	*@version 2.1
	*@see Circuito
	*@see Fuente
	*@see PuertaOR
	*@see CircuitoException
	*
	*/
    public class MaquinaCafe extends java.lang.Object{
   
      /**Identificador del pulsador de caf� solo*/
      public static final int CAFE_SOLO=0;
   
      /**Identificador del pulsador de caf� con leche*/
      public static final int CAFE_LECHE =1;
   
      /**Identificador del pulsador de caf� con az�car*/
      public static final int CAFE_AZUCAR=2;
   
      /**Identificador del pulsador de caf� con leche y con az�car*/
      public static final int CAFE_LECHE_AZUCAR=3;
   
      /**Identificador del pulsador de leche sola*/
      public static final int LECHE_SOLA=4;
   
      /**Identificador del pulsador de leche con az�car*/
      public static final int LECHE_AZUCAR=5;
   
      /**N�mero de pulsadores de nuestra m�quina de caf�*/
      public static final int NUM_PULSADORES=6;
   
      /**declaramos un array de Fuentes, que almacenar� las distintas opciones*/
      private Fuente [] opcion;
   
   	/**
   	*Declaramos los circuitos necesarios para sumar las fuentes en cada surtidor
   	*Circuito, para el surtidir de Caf�
   	*/
      private Circuito orCafe;
   
   	/**Circuito, para el surtidir de Leche*/
      private Circuito orLeche;
   
   	/**Circuito, para el surtidir de Azucar*/
      private Circuito orAzucar;
   
   
   	  /**
      *Constructor que crea los 3 circuitos, crea las 6 fuentes y conecta adecuadamente los circuitos.
      *
 	  *@throws CircuitoException
      */
       public MaquinaCafe()throws CircuitoException{
      
       //creamos el array de opciones, con 6  opciones, en cada posici�n de este creamos una Fuente, inicializada a false
         opcion=new Fuente[NUM_PULSADORES];
         for(int i=0; i<NUM_PULSADORES; i++)
            opcion[i]=new Fuente(false);
      
      	//creamos los circuitos de tipo PuertaOR (tres circuitos, uno por cada surtidor)
         orCafe=new PuertaOR(4);
         orLeche=new PuertaOR(4);
         orAzucar=new PuertaOR(3);
      
         //Conectamos el surtidor de caf� a las fuentes q le corresponden
         orCafe.conectar(0,opcion[0]);
         orCafe.conectar(1,opcion[1]);
         orCafe.conectar(2,opcion[2]);
         orCafe.conectar(3,opcion[3]);
      
      	//Conectamos el surtidor de caf� a las fuentes q le corresponden
         orLeche.conectar(0,opcion[1]);
         orLeche.conectar(1,opcion[3]);
         orLeche.conectar(2,opcion[4]);
         orLeche.conectar(3,opcion[5]);
      
         //Conectamos el surtidor de caf� a las fuentes q le corresponden
         orAzucar.conectar(0,opcion[2]);
         orAzucar.conectar(1,opcion[3]);
         orAzucar.conectar(2,opcion[5]);
      
      }
   
     /**
     *Devuelve el valor del circuito de azucar.
   	 *
   	 *@return valor del circuito de azucar.
   	 *@throws CircuitoException  Si hay alg�n error a la hora de extraer el valor del circuito de azucar.
   	 *@see CircuitoFuncional#evaluar()
     */
       public boolean getAzucar()throws CircuitoException{
      
         return orAzucar.evaluar();
      }
   
     /**
	 *Devuelve el valor del circuito de caf�.
   	 *
   	 *@return valor del circuito de caf�.
   	 *@throws CircuitoException  Si hay alg�n error a la hora de extraer el valor del circuito de caf�.
     */
       public boolean getCafe()throws CircuitoException{
      
         return orCafe.evaluar();
      }
   
     /**
     *Devuelve el valor del circuito de leche.
   	 *
   	 *@return valor del circuito de leche.
   	 *@throws CircuitoException  Si hay alg�n error a la hora de extraer el valor del circuito de leche.
   	 *@see CircuitoFuncional#evaluar()
	 */
       public boolean getLeche()throws CircuitoException{
      
         return orLeche.evaluar();
      }
   
	 /**
   	 *Devuelve el valor booleano de todos los circuitos
   	 *
   	 *@return array con el valor de todos los booleanos del circuito.
   	 *@throws CircuitoException  Si hay alg�n error a la hora de extraer el valor de cualquier circuito de la maquina de caf�.
   	 *@see CircuitoFuncional#evaluar()
   	 */
       public boolean[] getSurtidores()throws CircuitoException{
      
      	//Se devuelve un array de boolean despu�s de evaluar cada surtidor
         return (new boolean[]{getCafe(),getLeche(),getAzucar()});
      }
   
     /**
   	 *Pone a false todos los pulsadores.
   	 *@see Fuente#setValor(boolean valor)
   	 */
       public void reset(){
      
       //Con un bucle recorremos el array de fuentes poniendo a false todas las fuentes
         for(int i=0;i<NUM_PULSADORES;i++)
            opcion[i].setValor(false);
      }
   
     /**
   	 *Pone a true el pulsador indicado, dejando el resto a false.
   	 *
   	 *@param opcion  Identificador de pulsador a poner a true.
   	 *@throws CircuitoException Si la opcion se va fuera del rango de pulsadores
   	 *
   	 *@see #reset()
   	 *@see Fuente#setValor(boolean valor)
   	 *
   	 */
       public void setOpcion(int option)throws CircuitoException{
      
      	//lanzamos una CircuitoException si el par�metro es negativo
         if(option<0)
            throw new CircuitoException("Opcion no disponible");
         
         //lanzamos una CircuitoException si el par�metro es superior al numero de opciones
         else if(option>NUM_PULSADORES-1)
            throw new CircuitoException("Opcion no disponible");
         
         //si no se a lanzado ninguna CircuitoException, ponemos a false todas las fuentes menos la correspondiente a la opci�n
         //pasada por par�metro
         else{
         
         	//ponemos a false todas las fuentes
            reset();
         
         	//analizamos los distintos valores q puede tomar el par�metro, poniendo a true la fuente correspondiente a
         	//la opci�n escogida
            switch (option){
            
               case CAFE_SOLO:
               
                  opcion[0].setValor(true);
                  break;
            
               case CAFE_LECHE:
               
                  opcion[1].setValor(true);
                  break;
            
               case CAFE_AZUCAR:
                  opcion[2].setValor(true);
                  break;
            
               case CAFE_LECHE_AZUCAR:
               
                  opcion[3].setValor(true);
                  break;
            
               case LECHE_SOLA:
               
                  opcion[4].setValor(true);
                  break;
            
               case LECHE_AZUCAR:
               
                  opcion[5].setValor(true);
                  break;
            
            }
         }
      }
   
   /**
   *
   *Devuelve un string con el siguiente formato:
   *Caf�(1). Leche(0). Az�car(0)
   *Siendo 1: Valor true del circuito. 0: Valor false del circuito.
   *Si existe alguna excepci�n al conseguir el valor de los circuitos, devolver:
   *Caf�(?). Leche(0). Az�car(0)
   *Poniendo la interrogaci�n '?' all� donde haya habido error.
   *
   *@return string con el formato adecuado.
   *
   *@see #getCafe()
   *@see #getLeche()
   *@see #getAzucar()
   */
       public java.lang.String toString(){
      
      	//declaramos como variables locales tres char, qua almacenar�n el valor a concatenar con la cadena a devolver por el m�todo
         char valorCafe, valorLeche, valorAzucar;
      
      	//para casos normales se asigna a valorCafe, 1 si el Surtidor de caf� est� a true y 0, si est� a false
         try{
         
            if(getCafe()==true)
               valorCafe='1';
            else valorCafe='0';
         
         }
         
         	 //si a surgido alguna CircuitoException el car�cter a concatenar es el simbolo "?"
             catch(CircuitoException e){
               valorCafe='?';
            }
      
      	//para casos normales se asigna a valorLeche, 1 si el Surtidor de caf� est� a true y 0, si est� a false
         try{
         
            if(getLeche()==true)
               valorLeche='1';
            else valorLeche='0';
         }
         
             //si a surgido alguna CircuitoException el car�cter a concatenar es el simbolo "?"
             catch(CircuitoException e){
               valorLeche='?';
            }
      
      	//para casos normales se asigna a valorAzucar, 1 si el Surtidor de caf� est� a true y 0, si est� a false
         try{
         
            if(getAzucar()==true)
               valorAzucar='1';
            else valorAzucar='0';
         }
         
           //si a surgido alguna CircuitoException el car�cter a concatenar es el simbolo "?"
             catch(CircuitoException e){
               valorAzucar='?';
            }
      
      	//Se devuelve la cadena con el formato adecuado
         return("Cafe("+valorCafe+"). Leche("+valorLeche+"). Azucar ("+valorAzucar+")");
      }
   
	/**
   	*
   	*M�todo de prueba que "aprieta" cada pulsador e invoca a toString para chequear la salida de los 3 circuitos.
   	*En el caso que haya exception se imprime la excepci�n como error y se sale de la prueba.
   	*
   	*@param args  argumentos de entrada. En este caso no es necesario definirlos.
   	*
   	*/
       public static void main (String args[]){
      
         try{
         
         	//creamos un Objeto de la clase MaquinaCafe
            MaquinaCafe maquina=new MaquinaCafe();
         
         	//declaramos y dreamos un array de cadenas que nos simplifica el c�digo a sacar por panatalla
            String[] array=new String[]{"Cafe Solo : ","Cafe con Leche : ","Cafe con Azucar : ","Cafe con Leche con Azucar : ","Leche : ","Leche con Azucar : "};
         
            //Utilizando un bucle representamos por pantalla las distintas cadenas q devuelve el toString(), para todas las opciones posibles
            for(int i=0;i<NUM_PULSADORES;i++){
               maquina.setOpcion(i);
               System.out.println(array[i]+maquina.toString());
            }
         }
         
            //si se a recogido alguna CircuitoException se imprime el mensage adecuado y se finaliza la aplicaci�n
             catch(CircuitoException e){
               System.out.println(e.getMessage());
               System.exit(0);
            }
         	
         	//improbable
             catch(Exception e){
               System.out.println(e.getMessage());
               System.exit(0);
            }
      
      }
   }
