
//declaramos el package en el que se encontrara toda la practica.
   package circuitos;

/**
*Una Fuente de informaci�n es un Circuito que no tiene ninguna entrada y simplemente devuelve
*un valor como salida. El valor de salida se establece en tiempo de construcci�n, pero puede
*modificarse posteriormente. Cuando se ejecuta el proceso de evaluaci�n hacia atr�s, es el punto 
*que finaliza la cascada de evaluaci�n. 
*@author Fernando Diaz & Taoufik Aadia
*@version 2.1
*@see CircuitoFuncional
*@see CircuitoException
*/

    public class Fuente extends CircuitoFuncional{
   
   /**valor constante que se devuelve a la salida*/
      private  boolean valor;
          
   
   //CONSTRUCTORES
   
   /**
   *Crea un nuevo circuito Entrada cuya salida es un valor constante. 
   *@param valor  Valor l�gico que tiene a su salida el circuito. 
   *@throws CircuitoException  no deber�a saltar en condiciones normales.
   */
       public Fuente(boolean valor) throws CircuitoException { 
         super();
         this.valor=valor;
      }
   
   
   
   //METODOS
   
   /**
   *Funci�n de transferencia del circuito. Devuelve el valor l�gico a la salida del circuito (constante). 
   *@param valoresEntrada  valores l�gicos en las entradas del circuito 
   *@return valor l�gico a la salida del circuito 
   *@throws CircuitoException  si el tama�o del array de valores de entrada es mayor que cero y si el numero de entradas es mayor que 0
   */
       protected  boolean funcionTransferencia(boolean[] valoresEntrada) throws CircuitoException{
         if (getNumeroEntradas()>0)
            throw new CircuitoException("Una fuente no tiene entradas");
         //Lanzamos una excepci�n si valoresEntrada tiene un tama�o mayor que cero
         if (valoresEntrada.length>0)
            throw new CircuitoException("Una fuente no tiene entradas");
         
         //Variable local, de tipo boolean que almacena el valor a devolver
         boolean salida=valor;
      //El m�todo devuelve salida
         return salida;
      }
        
   
   /**
   *Devuelve la funci�n l�gica de la puerta, que ser� una constante.
   *@return String con la funci�n can�nica del circuito.
   *@throws CircuitoException si el numero de entradas es mayor que cero
   */
       public java.lang.String getFuncionCanonica()throws CircuitoException {
       //Comprueba que el numero de entradas sea 0
         if(getNumeroEntradas()>0)
            throw new CircuitoException("Una fuente no tiene entradas");
       
      	//cadena que devolvera el m�todo
         String fcan="";
      	
      	//pasamos el booleano de "valor" a cadena. Poniendo "1" si es true y "0" si es false
         if(valor==true)
            fcan="1";
         
         else
            fcan="0";
      
      //Devolvemos fcan
         return fcan;
      }
          
   
   /**
    *Devuelve el valor de salida de la Fuente
   *@return boolean valor de salida
   */
       public boolean getValor(){
         return valor;
      }
   
   
   /**
   *Establece el valor de salida de la Fuente 
   *@param valor El valor de salida que se pretende fijar
   */
       public void setValor(boolean valor) {
         this.valor=valor;
      }
         
   }//FIN DE LA CLASE
