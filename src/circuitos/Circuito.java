//declaramos el package en el que se encontrara toda la practica
   package circuitos;
   import java.util.*;

/**
 *Clase Circuito.
 *
 *La clase Circuito es la principal en la gerarquia de clases.
 *Es la clase base para un circuito y todos sus elementos
 *Un circuito tiene varias entradas y una salida. Cada una de las entradas puede estar conectada a la 
 *salida de otro circuito (por tanto, una conexión se representa como una referencia al objeto Circuito
 *correspondiente). 
 *Por tanto, el conjunto de conexiones de las entradas se representa como un array 
 *de referencias a objetos Circuito, donde la longitud del array es directamente el número de entradas.
 *No se almacena en el circuito información acerca de las conexiones de la salida. 
 *De la clase Circuito heredan (por el momento) CircuitoFuncional y CircuitoTablaVerdad
 *En la clase Circuito se definen los atributos y metodos que poseeran todas las demas clases de este modulo
 *Es una clase abstracta ya que en ella hay metodos que no se implementan
 *@author Fernando Diaz && Taoufik Aadia
 *@version 2.1
 *@see CircuitoFuncional
 *@see CircuitoTablaVerdad
 *@see CircuitoEstructural
 *@see CircuitoException
 */
    public abstract class Circuito extends java.lang.Object{
   
   /**Referencias a los circuitos conectados a cada una de las entradas de este circuito */
      private Circuito [] entradas;
   /** Etiqueta asociada al elemento para facilitar su representación*/
      private java.lang.String etiqueta;
   
   
   //CONSTRUCTURES
   /**Crea un nuevo circuito sin entradas*/
       public Circuito(){
      //se inicializan los atributos
         entradas=new Circuito[0];
         etiqueta=null;
      }
   
   
   /** 
   *Crea un nuevo circuito con un determinado array de entradas
   *@param entradas - array de entradas
   *@throws CircuitoException si el array de entradas apunta a null
   */
       public Circuito(Circuito [] entradas)throws CircuitoException{
         if(entradas==null)
            throw new CircuitoException("No se puede crear un circuito con un array de entradas que apunta a null");
      //se inicializa el atributo etiqueta que no se pasa por el constructor
         etiqueta=null;
         this.entradas=entradas;
      }
   
   
   /**
   *Crea un nuevo circuito con un determinado número de entradas
   *@param numEntradas - número de entradas (mayor o igual a 0) 
   *@throws CircuitoException - si numEntradas está fuera del rango permitido
   */
       public Circuito(int numEntradas) throws CircuitoException{
      //Si numEntradas es menor que cero lanza CircuitoException
         if (numEntradas<0) 
            throw new CircuitoException("el número de entradas no puede ser negativo!");
      //Se inicializan etiqueta a null porque no se pasa y se crea el array de entradas
      // del circuito de tamaño numEntradas
         etiqueta=null;
         entradas = new Circuito[numEntradas];
      }	
   
   
   
   //METODOS
   
   /**
   *Establece una conexión entre un determinado pin de entrada de este circuito y la salida de otro circuito. 
   *@param pinEntrada  número de pin de entrada de este circuito (entre 0 y entradas.length - 1)
   *@param circuito  circuito a cuya salida se realiza la conexión 
   *@throws CircuitoException  si el atributo entradas apunta a null y si pinEntrada está fuera del rango permitido
   */
       public void conectar(int pinEntrada,Circuito circuito)throws CircuitoException{
         if(entradas==null)
            throw new CircuitoException("Este circuito no tiene array de entradas por lo que no se puede realizar correctamente la conexion");
         //Si pinEntrada es menor que cero o mayor que la longitud del array menos uno lanza CircuitoExcepcion
         else if (pinEntrada<0)
            throw new CircuitoException("el pinEntrada no puede ser negativo");
         
         else if (pinEntrada>entradas.length-1)
            throw new CircuitoException("el pinEntrada no puede ser superior al tamaño del array menos 1");
         
         else
         //Se hace que (si el pinEntrada es correcto) dicha posicion del array de entradas
         // apunte al circuito que se pasa
            entradas[pinEntrada] = circuito;
      }
   
   	
   /** 
   *Elimina una conexión asociada a un determinado pin de entrada 
   *@param pinEntrada  número de pin (posicion del array entradas)
   *@throws CircuitoException  si pinEntrada está fuera del rango permitido.
   */
       public void desconectar(int pinEntrada)throws CircuitoException{
       
      //Si pinEntrada es menor que cero o mayor que la longitud del array menos uno lanza CircuitoExcepcion
         if (pinEntrada<0){
            throw new CircuitoException("el pinEntrada no puede ser negativo");
         }
         if (pinEntrada>entradas.length-1){
            throw new CircuitoException("el pinEntrada no puede ser superior al tamaño del array menos 1");
         }
      //Si pinEntrada es un valor correcto pone a null la posicion pinEntrada del array entradas
         entradas[pinEntrada]=null;
      }
        
   
   /**
   *Devuelve un array con todas las entradas al circuito
   *@return array de entradas de un circuito
   */
       public Circuito[] getEntradas(){
         return (entradas);
      }
   
   /** 
   *Devuelve la etiqueta asociada al circuito y que lo identifica
   *@return etiqueta
   */
       public java.lang.String getEtiqueta(){
         return (etiqueta);
      }
   
   
   /**
   *Devuelve el número de entradas que tiene el circuito. 
   *Es equivalente a obtener el array de entradas y contar el número de elementos que tiene. 
   *@return numero de entradas
   *@throws CircuitoException  si el array de entradas apunta a null porque no se podra obtener su longitud
   *@see #getEntradas()
   */
       public int getNumeroEntradas()throws CircuitoException{
      //Si entradas es un array que apunta a null lanza CircuitoException porque en ese caso
      //no se puede pedir su longitud
         if(entradas==null)
            throw new CircuitoException("no se puede obtener el numero de entradas porque el array entradas apunta a null");
         //variable i es el numero de entradas que tiene el circuito (longitud del array de entradas)
         else
            return entradas.length;
      }	
      		 
   
   /**
   *Establece el array de entradas del circuito, sobreescribiendo el que hubiese anteriormente. 
   *@param entradas  nuevo array de entradas del circuito
   *@throws CircuitoException no deberia saltar en esta clase pero si podria saltar en alguna subclase
   */          
       public void setEntradas(Circuito[] entradas)throws CircuitoException{
         this.entradas=entradas;
      }
          
   
   /** 
   *Establece una nueva etiqueta para el circuito, reemplazando la que hubiese anteriormente.
   *@param etiqueta   la nueva etiqueta
   */
       public void setEtiqueta(java.lang.String etiqueta){
         this.etiqueta=etiqueta;
      }
   
   /**
   *Crea un array de Circuito de tamaño numEntradas y se lo asigna al atributo entradas
   *@param numEntradas  número de entradas (mayor o igual a 0)
   *@throws CircuitoException  si numEntradas está fuera del rango permitido. (si es negativa)
   */
       public void setNumEntradas(int numEntradas) throws CircuitoException{
      //Si numEntradas es menor que 0 lanza CircuitoException porque el tamaño de un array 
      //no puede ser negativo
         if(numEntradas<0)
            throw new CircuitoException("el numero de entradas no puede ser negativo");
      //Si numEntradas es un valor correcto crea un array de Circuito de tamaño 
      //numEntradas y se lo asigna al atributo entradas
         entradas= new Circuito[numEntradas];
      }
    
   
   /**
   *Genera un String con la descripción de este circuito, 
   *siguiendo el formato: "etiqueta\n\t[0]entiqueta_entrada_0\n\t[1]...\n" 
   *@return String que describe el circuito
   *@see #getEtiqueta()
   */
       public java.lang.String toString(){
       //variable descripcion es la descripcion del circuito tal y como se ha pedido
         java.lang.String descripcion="";
         //se le concatena la etiqueta del circuito con el que llamamos al metodo 
         descripcion=getEtiqueta();
         descripcion+="\n";
       //si el array de entradas del circuito no apunta a null
         if(entradas!=null){
          //este bucle for muestra la descripcion de todas las entradas del circuito
            for(int i=0;i<entradas.length;i++){
               if(entradas[i]==null){
               //si esta posicion del array de entradas apunta a null
                  descripcion+="\t"+"["+i+"] "+"<desconectada>\n";
               }
               else{
                  if (entradas[i].getEtiqueta()==null){
                  //si el circuito que tiene conectado a una entrada no posee etiqueta
                     descripcion+="\t"+"["+i+"] "+"null\n";
                  }
                  else{
                  //si una entrada esta conectada a un circuito con una determinada etiqueta
                     descripcion+="\t"+"["+i+"] "+entradas[i].getEtiqueta()+"\n";
                  }
               }
            }
         }
       //si el array de entradas apunta a null este metodo solo devolveria la etiqueta del circuito 
       //y un retorno de carro, asi se evita pedir la longitud del array (en el for) cuando este es null
         return descripcion;
      }
   	
   	
   	/**
   	*Devuelve el resultado de evaluar el valor lógico de la salida de este circuito 
   	*(true equivale a un 1 lógico, false equivale a un 0 lógico). 
   	*@return valor logico de la salida del circuito
   	*@throws CircuitoException si el circuito no está adecuadamente conectado (por ejemplo, si 'entradas' es null o alguna de las posiciones de 'entradas' es null)
      */
       public abstract boolean evaluar() throws CircuitoException;
   	
   	
   	/**
   	*Compara este circuito con el que se le pasa por parámetro, para comprobar si son 
   	*equivalentes. Se considera que dos circuitos son equivalentes si, para todas las posibles 
   	*combinaciones de valores lógicos a la entrada, ambos devuelven el mismo valor lógico de salida.
   	*@param circuito es el circuito con el que se compara al que llama al método
   	*@return true si ambos circuitos son equivalentes, false si no lo son
   	*@throws CircuitoException si se produce algun error durante el proceso de comparacion
   	*@see #getEntradas()
   	*@see #getNumeroEntradas()
   	*@see #setEntradas(Circuito[] entradas)
   	*@see #evaluar()
      */
       public boolean equals(Circuito circuito) throws CircuitoException{
       
         //variable booleana que almacena el valor a devolver la inicializamos a true
         boolean b=true;
         
      	//Si el circuito pasado por parámetro apunta a null, se devuelve false
         if(circuito==null)
            b=false;
            
         //Si uno de los dos circuitos no tiene entradas devolvemos false
         else if(getEntradas()!=null&&circuito.getEntradas()==null)
            b= false;
         else if(getEntradas()==null&&circuito.getEntradas()!=null)
            b=false;
            
            //Si no tienen las mismas entradas devolvemos false
         else if(getNumeroEntradas()!=circuito.getNumeroEntradas())
            b= false;
            
         
         else{
         
            //declaramos dos variables locales para tener accesibles el numero de entradas de cada circuito
         	//sin tener que llamar al metodo que lo hace
            int numEntradas1=getNumeroEntradas();
            int numEntradas2=circuito.getNumeroEntradas();
            
            //declaramos dos arrays "entradas1" y "entradas2" que van a guardar los array de entradas de 
         	//los dos circuitos para despues de hacer la comparación volverlos a conectar
            Circuito[]entradas1=getEntradas();
            Circuito[]entradas2=circuito.getEntradas();
            
         	//variable que contiene la combinacion de entradas del circuito 
            int combinacion=0;
               
         		/*
         		*En este bucle while se va ha realizar una comparacion de la salida de ambos circuitos para cada
         		*posible combinacion de entradas(salida de los circuitos que se encuentren conectados a sus
         		*entradas, que puede ser true o false) que se simulan creando el array de fuentes correspondiente.
         		*Para cada valor de la variable "combinacion" se realiza este procedimiento, siempre y cuando 
         		*contador sea menor que 2^(numero de entradas del circuito - 1) porque en este caso ya se habran
         		*estudiado todas las posibles combinaciones de entradas y tambien mientras b sea true porque cuando
         		*sea false una vez ya no interesa seguir comparando (ya no pueden ser equivalentes)
         		*/
            while(combinacion<(Math.pow(2,numEntradas1))&&b){
            
              //cadena que guarda la representacion binaria del entero "i"
               String s=Integer.toBinaryString(combinacion);
                  
              //Si la longitud de esta cadena es menor que el numero de entradas del circuito se le concatenan ceros 
              //al principio hasta llegar a esta longitud, ya que cada caracter de esta cadena va a representar 
              //una entrada del circuito
               while(s.length()<numEntradas1)
                  s="0"+s;
                  
               //Se declara un array de circuitos, de prueba, del tamaño que tenian los array de entradas	
               Circuito[] prueba=new Circuito[numEntradas1];
                  
              //En este bucle for se recorre la cadena "s" y se van inicializando las posiciones del array de 
              //prueba segun corresponda
               for(int j=0;j<numEntradas1;j++){
               
                 //Si el caracter en la posicion "j" de la cadena s es un 0 se hace que la posicion "j" del 
                 //array de prueba apunte a una fuente que devuelve "false"
                  if(s.charAt(j)=='0')
                     prueba[j]=new Fuente(false);
                     
                  //Si el caracter en la posicion "j" de la cadena s es un 1 se hace que la posicion "j" del 
                  //array de prueba apunte a una fuente que devuelve "true"
                  else
                     prueba[j]=new Fuente(true);
               }
               
            	//una vez que se tiene el array de prueba completo se lo ponemos a los dos circuitos como 
            	//array de entradas utilizando el metodo setEntradas(entradas) de la clase Circuito
               setEntradas(prueba);
               circuito.setEntradas(prueba);
                  
            	//Si en alguna posible combinacion la salida del circuito1 (que se obtiene haciendo una llamada al
            	//su metodo evaluar) no es igual a la salida del circuito2 (obtenida de la misma forma) se hace que 
            	//b (variable a devolver) apunte a false, y esto a su vez hace terminar el bucle for
               if(evaluar()!=circuito.evaluar())
                  b=false;
                  
            //Se incrementa la variable combinacion para estudiar la siguiente combinacion de entradas 
            //si b no es false		
               combinacion++;
            }
            
         	//Una vez terminado el proceso de comparacion (bucle while) se reestablecen las entradas que
         	//tenia cada circuito antes de compararlos
            setEntradas(entradas1);
            circuito.setEntradas(entradas2);
            
            
         }
         
      	//Se devuelve la variable b
         return b;
         
      }
   			
   }//Fin de la clase

