  //declaramos el package en el que se encontrara toda la practica. 
   package circuitos;

    /**
	*Clase que representa una puerta l�gica NOT 
	*@author Fernando Diaz & Taoufik Aadia
	*@version 2.1
	*@see CircuitoFuncional
	*@see CircuitoException
	*/
    public class PuertaNOT extends CircuitoFuncional{
   
   //CONSTRUCTORES
   
   /**Crea una nueva puerta sin declarar entradas*/ 
       public PuertaNOT() {
         super();
      }
   
   /**
   *Crea una nueva puerta con un determinado array de entradas
   *@param entradas array de Entradas
   *@throws CircuitoException si entradas apunta a null y si tiene un tama�o distinto de 1
   */
       public PuertaNOT(Circuito[] entradas)throws CircuitoException{
       //Se comprueba que entradas no apunte a null
         if(entradas==null)
            throw new CircuitoException("El array de entradas apunta a null");
         else{
         //Se comprueba que el tama�o de entradas sea 1
            if(entradas.length!=1)
               throw new CircuitoException("El numero de entradas de una puerta NOT solo puede ser uno");
            else
               setEntradas(entradas);
         }
      }
   
   /**
   *Crea una puerta NOT con un determinado n�mero de entradas. 
   *@param numEntradas Numero de entradas de la puerta(mayor o igual a 0).
   *@throws CircuitoException si numEntradas es un numero distinto de 1 
   */ 
       public PuertaNOT(int numEntradas)throws CircuitoException{
       //Comprobamos que el numero de entradas es uno
         if (numEntradas!=1)
            throw new CircuitoException("El numero de entradas de una puerta NOT solo puede ser uno");
         else
            setNumEntradas(numEntradas);
      }
   
   //METODOS
   
   /**
   *Funci�n de trasferencia de una puerta NOT,Calcula el valor de salida, 
   *que es el inverso del valor recibido como entrada
   *@param  valoresEntrada - Array de un �nico elemento (boolean), con el valor recibido como entrada 
   *@return true si el valor de entrada es false, false si el valor de entrada es true 
   *@throws CircuitoException - si el tama�o del array de valores de entrada no coincide con el n�mero de entradas del circuito, si el numero de entradas es distino de uno y si el par�metro es null
   *@see #getNumeroEntradas()
   */
       protected final boolean funcionTransferencia(boolean[] valoresEntrada)throws CircuitoException{
         
       //Se lanza una excepc�n si valoresEntrada es un array null
         if (valoresEntrada==null)
            throw new CircuitoException("Error: se ha pasado un array que apunta a null");
         //Se lanza una excepci�n si el n�mero de entradas es distinto de uno
         else if (valoresEntrada.length!=1)
            throw new CircuitoException( "Error:En una puerta NOT solo se permite una entrada");
         //Se lanza una excepci�n si el tama�o del array de valores de entrada no coincide con el n�mero de entradas del circuito
         else if(valoresEntrada.length!=getNumeroEntradas())
            throw new CircuitoException( "Error: se ha pasado un array valoresEntrada de tama�o distinto al numero de entradas de la puerta");
         else{
         //variable local,de tipo boolean, que almacena el valor a devolver
            boolean fTransferencia=true;
         
         //Si la entrada es "true", devolvemos "false"
            if(valoresEntrada[0]==true)
               fTransferencia=false;
         
         //Si la entrada es "false" devolvemos "true"
            return fTransferencia;
         }
      }
   
   /**
   *Devuelve la funci�n can�nica de la puertaNOT
   *@return String con la funci�n de transferencia de la puerta cadena vac�a si no existen entradas
   *@throws CircuitoException Si el n�mero de entradas de la puerta es distinto de uno
   *@see #getNumeroEntradas()
   */
       public final java.lang.String getFuncionCanonica()throws CircuitoException{
       
       //Lanzamos una excepci�n si el numero de entradas es distinto de uno
         if((getNumeroEntradas())!=1)
            throw new CircuitoException("Error: Esta puerta solo puede tener una entrada");
            
       
       //Cadena que devolver� el m�todo 
         String fcan="!x0";
         
       //El m�todo devuelve fcan
         return fcan;
      }
   
   }//FIN DE LA CLASE

