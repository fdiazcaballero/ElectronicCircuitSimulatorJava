//declaramos el package en el que se encontrara toda la practica.
   package circuitos;

/**
*Clase que representa una puerta lógica OR 
*@author Fernando Diaz & Taoufik Aadia
*@version 2.1
*@see CircuitoFuncional
*@see CircuitoException
*/
    public class PuertaOR extends CircuitoFuncional{
   
   
   //CONSTRUCTORES
   
   /**Crea una nueva puerta sin declarar entradas*/
       public PuertaOR(){
         super();
      }	
   
   
   /**
   *Crea una nueva puerta sin declarar entradas
   *@param numEntradas  numero de entradas de la puerta
   *@throws CircuitoException si numEntradas es un numero menor que dos
   */ 
       public PuertaOR(int numEntradas) throws CircuitoException{
      //Se comprueba que el numero de entradas es mayor o igual que dos 
         if(numEntradas<2)
            throw new CircuitoException("El numero de entradas de una puerta OR debe ser mayor o igual que 2");
         else
            setNumEntradas(numEntradas);
      }
   
   /**
   *Crea una puerta OR con un determinado número de entradas
   *@param entradas array de entradas del circuito
   *@throws CircuitoException si el array de entradas apunta a null y si el tamaño de entradas es menor que 2
   */ 
       public PuertaOR(Circuito[] entradas)throws CircuitoException{
         if(entradas==null)
            throw new CircuitoException("El numero de entradas de una puerta OR debe ser mayor o igual que 2");
         else if(entradas.length<2)
            throw new CircuitoException("El numero de entradas de una puerta OR debe ser mayor o igual que 2");
         else
            setEntradas(entradas);
      
      }
           
   
   //METODOS 
   
   /**
   *Función de trasferencia de una puerta OR.Calcula el valor de salida en función 
   *de los valores de entrada, según la función de transferencia de la puerta.
   *@param  valoresEntrada  Array de valores (boolean) a la entrada. 
   *@return  true si algún valor de entrada es true, false si todos los valores a la entrada son false 
   *@throws CircuitoException  si el tamaño del array de valores de entrada no coincide con el número de entradas del circuito, si el numero de entradas de la puerta es menor que dos, y si el tamño de valoresEntrada es menor que dos
   *@see #getNumeroEntradas()
   */
       protected  boolean funcionTransferencia(boolean[] valoresEntrada) throws CircuitoException{
        //Comprobamos que la puerta tiene mas de dos entradas
         if(getNumeroEntradas()<2)
            throw new CircuitoException("El numero de entradas de una puerta OR debe ser mayor o igual que 2");
      //Se lanza una excepcón si valoresEntrada es un array null
         if (valoresEntrada==null)
            throw new CircuitoException("Se ha pasado un array valoresEntrada que apunta a null");
      
      //Se lanza una exception Si el número de entradas es menor que dos
         if (valoresEntrada.length<2)
            throw new CircuitoException( "Se ha pasado un array valoresEntrada con menos de dos entradas");
      
      //Se lanza una excepción si el tamaño del array de valores de entrada no coincide con el número de entradas del circuito
         if (valoresEntrada.length!=getNumeroEntradas())
            throw new CircuitoException("Se ha pasado un array valoresEntrada con un tamaño distinto del numero de entradas de la puerta");
      	
      //variable local,de tipo boolean, que almacena el valor a devolver
         boolean fTransf=false;
      
      //variable local, que declaramos para recorrer el array valoresEntrada
         int cont=0;	
      	
      //Recorremos el array buscando alguna posicion con valor "true", pues es suficiente 
      //con que halla una, para que el valor de la funcionTransferencia, sea "true"
         while(cont<valoresEntrada.length && fTransf==false){
            if(valoresEntrada[cont]==true)
               fTransf=true;
         
            cont++;
         }
      	
      //solo si no hay ningun "true" devolverá "false"
         return fTransf;
      }
          
   	  
   
   /**
   *Devuelve la función canónica de la puerta, como suma de productos
   *@return String con la función de transferencia de la puerta cadena vacía si no existen entradas
   *@throws CircuitoException  lanza las excepciones q lanzan los metodos a los que llama(getFuncionCanonica()), y si el numero de entradas es menor que dos
   *@see #getNumeroEntradas()
   */
   
       public java.lang.String getFuncionCanonica() throws CircuitoException{
      //Se lanza una excepción si el Número de Entradas es menor a dos ya que esta puerta 
      //tienes al menos dos entradas
         if((getNumeroEntradas())<2)
            throw new CircuitoException("Una puerta OR tiene al menos dos entradas");
      
      //creamos un array de booleanos para almacenar la tabla de verdad de esta puerta
      //que tiene "true" en todas las posiciones menos en la última.
      //Le damos una longitud lógica a este array
         boolean [] salida=new boolean[(int)(Math.pow(2,getNumeroEntradas()))];
      
      //La primera posición de salida es siempre false	 
         salida[0]=false;
      
      //vamos rellenando las demas posiciones de salida con true
         for(int i=1;i<salida.length;i++)
            salida[i]=true;
      
      //creamos un objeto de la clase CircuitoTablaVerdad utilizando el constructor
      //que nos permite introcirle un array de booleanos, este es "salida"
         CircuitoTablaVerdad puertaOR=new CircuitoTablaVerdad(salida);
      
      //Declaramos la cadena a devolver por el método, esta es la que se obtiene
      //al llamar al método getFuncionCanonica(), de la clase CircuitoTablaVerdad,
      //sobre puertaOR, que nos acabamos de crear
         String fcan=puertaOR.getFuncionCanonica();
      
      //Devolvemos fcan
         return fcan;
      }
   
   }//Fin de la Clase
