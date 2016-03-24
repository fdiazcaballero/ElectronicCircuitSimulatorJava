//declaramos el package en el que se encontrara toda la practica.
   package circuitos;

   import java.io.*;

/**
*Circuito cuya salida est� caracterizada por c�digo Java.
*Esta clase hereda de Circuito y modela su funcionamiento. Es una clase abstracta ya que contiene 
*m�todos que lo son. 
*@author Fernando Diaz & Taoufik Aadia
*@version 2.1
*@see Circuito
*@see CircuitoException
*@see PuertaAND
*@see PuertaOR
*@see PuertaNAND
*@see PuertaNOT
*@see Fuente
*@see PuertoEntrada
*/
    public abstract class CircuitoFuncional extends Circuito{
   
   //CONSTRUCTORES
   
   /**Crea un nuevo circuito sin declarar entradas*/
       public CircuitoFuncional(){
         super();
      }
   
   /**
   *Crea un circuito con un array de entradas
   *@param entradas  array de entradas
   */
       public CircuitoFuncional(Circuito [] entradas)throws CircuitoException{
         if(entradas==null)
            throw new CircuitoException();
         else
            this.setEntradas(entradas);
      }
   
   /**
   *Crea un circuito con un determinado numero de entradas
   *@param numEntradas  n�mero de entradas (mayor o igual a 0).
   *@throws CircuitoException  si numEntradas es un numero negativo
   */
       public CircuitoFuncional(int numEntradas)throws CircuitoException{
         if(numEntradas<0)
            throw new CircuitoException();
         else
            this.setNumEntradas(numEntradas);
      }	
   
   
   
   //METODOS
   
   
   
   /**
   *Devuelve el valor l�gico a la salida del circuito cuando las entradas tienen los valores l�gicos especificados. 
   *@param valoresEntrada  valores l�gicos en las entradas del circuito. 
   *@return  valor l�gico a la salida del circuito .
   *@throws  CircuitoException  si el tama�o del array de valores de entrada no coincide con el n�mero de entradas.
   */
       protected abstract boolean funcionTransferencia(boolean[] valoresEntrada) throws CircuitoException;
   
   
   /**
   *Devuelve una cadena de texto representando la funci�n l�gica del circuito. 
   *@return funci�n l�gica del circuito.
   *@throws CircuitoException
   */
       public abstract java.lang.String getFuncionCanonica()throws CircuitoException;
   
   
   
   /**
   *Compara este circuito con el que se le pasa por par�metro, para comprobar si son equivalentes. 
   *Se considera que dos circuitos son equivalentes si, para todas las posibles combinaciones 
   *de valores l�gicos a la entrada, ambos devuelven el mismo valor l�gico de salida.
   *Esto es equivalente a comprobar que sus funciones l�gicas coinciden. 
   *@param circuito.
   *@return true si ambos circuitos son equivalentes, false si no lo son
   *@throws CircuitoException  lanza las excepciones q lanzan los metodos a los q llama.
   *@see #getFuncionCanonica()
   */
       public boolean equals(CircuitoFuncional circuito)throws CircuitoException{
         
      	//Si el circuito pasado por par�metro apunta a null,se devuelve false
         if(circuito==null)
            return false;
            
         else{
            //variable local,de tipo boolean, que almacena el valor a devolver
            boolean b=true;
           
           //cadena que almacena la funci�n can�nica del circuito a comparar. 
            String fcan1=getFuncionCanonica();
            
         	//cadena que almacena la funci�n can�nica del circuito que se le pasa como par�metro
            String fcan2=circuito.getFuncionCanonica();
            
         	// variable local que nos ayuda a recorrer fcan1 y fcan2
            int contador=0;
         
         //Si la longitud de las dos cadenas es igual
            if(fcan1.length()==fcan2.length()){
            
            //recorremos las dos cadenas mientras el contador sea menor que su longitud y los caracteres correspondientes
            //sean iguales, y se incrementa el contador
               while(contador<fcan1.length()&&fcan1.charAt(contador)==fcan2.charAt(contador)){
                  contador++;
               }
               
            	//Si no se ha recorrido las dos cadenas enteras es porque no son iguales => se devuelve false
               if(contador!=fcan1.length())
                  b=false;
            }
            
            //Si la longitud de las funciones can�nicas son diferentes, no hace falta compararlos 
            //devolvemos "false" directamente.
            else 
               b=false;
            return b;
         }
      
      }
   
   
   /**	
   *Compara este circuito con el que se le pasa por par�metro, para comprobar si son equivalentes. 
   *Se considera que dos circuitos son equivalentes si, para todas las posibles combinaciones 
   *de valores l�gicos a la entrada, ambos devuelven el mismo valor l�gico de salida.
   *Esto es equivalente a comprobar que sus funciones l�gicas coinciden. 
   *@param circuito
   *@return true si ambos circuitos son equivalentes.
   *@throws CircuitoException lanza las excepciones q lanzan los metodos a los q llama.
   *@see #getFuncionCanonica()
   */
       public boolean equals(CircuitoTablaVerdad circuito)throws CircuitoException{
      
       //Si el circuito que se pasa por par�metro apunta a null, se devuelve false
         if(circuito==null)
            return false;
         
                     
         else{
         
           //variable local,de tipo boolean, que almacena el valor a devolver
            boolean b=true;   
           
           //cadena que almacena la funci�n can�nica del circuito a comparar. 
            String fcan1=getFuncionCanonica();
           
            //cadena que almacena la funci�n can�nica del circuito que se le pasa como par�metro
            String fcan2=circuito.getFuncionCanonica();
            
         	// variable local que nos ayuda a recorrer fcan1 y fcan2
            int contador=0;
         
         //Si la longitud de las dos cadenas es igual
            if(fcan1.length()==fcan2.length()){
            
            //recorremos las dos cadenas mientras el contador sea menor que su longitud y los caracteres correspondientes
            //sean iguales, y se incrementa el contador
               while(contador<fcan1.length()&&fcan1.charAt(contador)==fcan2.charAt(contador)){
                  contador++;
               }
               
            	//Si no se ha recorrido las dos cadenas enteras es porque no son iguales => se devuelve false
               if(contador!=fcan1.length())
                  b=false;
            }
            
            //Si la longitud de las funciones can�nicas son diferentes, no hace falta compararlos 
            //devolvemos "false" directamente.
            else 
               b=false;
            
            return b;
         }
      }
      
   
   /**
   *Devuelve el resultado de evaluar el valor l�gico de la salida de este circuito (true equivale a 
   *un 1 l�gico, false equivale a un 0 l�gico). Se invoca al m�todo evaluar de todas sus entradas
   *para poder calcular la salida. La salida se eval�a mediante una invocaci�n a funcionTransferencia(), 
   *pas�ndole en un array el resultado de las invocaciones anteriores. 
   *
   *@return valor logico de la salida
   *@throws CircuitoException  si el circuito no est� adecuadamente conectado ('entradas' o alguno de sus elementos es null, funcionTransferencia() lanza excepci�n, o las invocaciones a evaluar() lanzan excepci�n)
   *@see #funcionTransferencia(boolean[])
   *@see #getEntradas()
   *@see #getNumeroEntradas()
   */
       public boolean evaluar() throws CircuitoException{
      
         //Array que va a contener el valor logico de las salidas de los circuitos conectados a las entradas del circuito
         //que llama al metodo
         boolean []valoresEntrada;
      
         //Si el atributo entradas del circuito apunta a null se lanza una CircuitoException
         if (getEntradas()==null)
            throw new CircuitoException("El atributo entradas apunta a null (no se puede evaluar este circuito)");
         
         else{
         
            //se guarda en una variable local el atributo entradas
            Circuito [] ent=getEntradas();
            int n=getNumeroEntradas();
            
            //Se recorre el array que apunta al atributo entradas para comprobar que todas las entradas estan conectadas
            for(int i=0;i<n;i++){
               
               //Si una entrada no esta conectada (apunta a null) se lanza CircuitoException
               if(ent[i]==null)
                  throw new CircuitoException("El pinEntrada "+i+" esta desconectado (apunta a null) => no se puede "+
                        "evaluar la salida del circuito");
            }
            
            //Se le asigna un tama�o igual al numero de entradas
            valoresEntrada=new boolean[n];
            
            //Se inicializan todas las posiciones del array de booleanos "valoresEntrada" con el resultado
            //de evaluar la salida del circuito al que apunte la correspondiente posicion del array de entradas
            for(int j=0;j<n;j++)
               valoresEntrada[j]=ent[j].evaluar();
         }
         
         //se devuelve el resultado de llamar al metodo funcionTransferencia de este circuito con el array "valoresEntrada"
         //(que son los valores reales que recibe por sus entradas)
         return funcionTransferencia(valoresEntrada);
      }
      
   
   }//Fin de la Clase

		
	
	
 

	

													  



