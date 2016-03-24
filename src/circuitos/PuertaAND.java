 //declaramos el package en el que se encontrara toda la practica. 
   package circuitos;

/**
*Clase que representa una puerta l�gica AND
*@author Fernando Diaz & Taoufik Aadia
*@version 2.1
*@see CircuitoFuncional
*@see CircuitoException
*/
    public class  PuertaAND extends CircuitoFuncional{
   
   
   //CONSTRUCTORES
   
   /**Crea una nueva puerta sin declarar entradas*/ 
       public PuertaAND(){
         super();
      }
   
   
   /**
   *Crea una nueva puerta con un determinado array de entradas
   *@param entradas  array de Entradas
   *@throws CircuitoException si el array de entradas apunta a null y si el tama�o de entradas es menor que 2
   */
       public PuertaAND(Circuito[] entradas)throws CircuitoException{
         if(entradas==null)
            throw new CircuitoException("El numero de entradas de una puerta AND debe ser mayor o igual que 2");
         else if(entradas.length<2)
            throw new CircuitoException("El numero de entradas de una puerta AND debe ser mayor o igual que 2");
         else
            setEntradas(entradas);
      }
   
   /**
   *Crea una puerta AND con un determinado n�mero de entradas. 
   *@param numEntradas Numero de entradas de la puerta(mayor o igual a 2).
   *@throws CircuitoException si numEntradas es un numero menor 2
   */
       public PuertaAND(int numEntradas)throws CircuitoException{
       //Se comprueba que el numero de entradas es mayor o igual que dos 
         if(numEntradas<2)
            throw new CircuitoException("El numero de entradas de una puerta AND debe ser mayor o igual que 2");
         else
            setNumEntradas(numEntradas);
      }
   
   //METODOS
   
   
   /**
   *Funci�n de trasferencia de una puerta AND,Calcula el valor de salida 
   *en funci�n de los valores de entrada, seg�n la funci�n de transferencia de la puerta. 
   *@param  valoresEntrada  Array de valores (boolean) a la entrada. 
   *@return true si todos los valores a la entrada son true, false si alg�n valor de entrada es false 
   *@throws CircuitoException  si el tama�o del array de valores de entrada no coincide con el n�mero de entradas del circuito, si el numero de entradas de la puerta es menor que dos, y si el tam�o de valoresEntrada es menor que dos
   *@see #getNumeroEntradas()
   */
       protected final boolean funcionTransferencia(boolean[] valoresEntrada)throws CircuitoException{
       //Comprobamos que la puerta tiene mas de dos entradas
         if(getNumeroEntradas()<2)
            throw new CircuitoException("El numero de entradas de una puerta AND debe ser mayor o igual que 2");
       //Comprobamos que valoresEntrada no apunte a null
         if(valoresEntrada==null)
            throw new CircuitoException("Una puerta AND solo funciona con 2 o mas entradas");
       //comprobamos que la longitud del array valoresEntrada es valida
      //y lanzamos una excepciones en caso de que no lo fuese.
         if ((valoresEntrada.length<2)&&(valoresEntrada.length!=getNumeroEntradas()))
            throw new CircuitoException( "El numero de entradas en una PuertaAND debe de ser al menos 2 o el tama�o del parametro valoresEntrada no coincide con el numero de entradas del circuito");
         
      	
      //variable local,de tipo boolean, que almacena el valor a devolver
         boolean fTransf=true;
      
      //variable local, que declaramos para recorrer el array valoresEntrada
         int cont=0;
      	
      //Recorremos el array buscando alguna posicion con valor "false", pues es suficiente 
      //con que halla una, para que el valor de la funcionTransferencia, lo sea tambi�n
         while(cont<valoresEntrada.length && fTransf==true){
            if(valoresEntrada[cont]==false)
               fTransf=false;
         
            cont++;
         }
       
         return fTransf;
      }
   
   
   
   /**
   *Devuelve la funci�n can�nica de la puerta, como suma de productos.
   *@return String con la funci�n de transferencia de la puerta cadena vac�a si no existen entradas.
   *@throws CircuitoException Si el n�mero de entradas de la puerta es menor que dos
   *@see #getNumeroEntradas()
   */
       public java.lang.String getFuncionCanonica()throws CircuitoException{
         
       //Se lanza una excepci�n si el N�mero de Entradas es menor a dos ya que esta puerta 
       //tienes al menos dos entradas
         if((getNumeroEntradas())<2){
            throw new CircuitoException("Esta puerta no puede tener menos de dos entradas");
         }
      	
      //en principio es la cadena a devolver
         String fcan="";
      //almcena la cadena a devolver
         String fcan2="";
      
      //asignamos a fcan la funci�n can�nica de esta puerta         
         for(int i=(getNumeroEntradas()-1); i>=0;i--)
            fcan+="x"+i+"*";
         
       //eleminamos a "fcan" el �ltimo car�cter
         fcan2=fcan.substring(0,fcan.length()-1);
      
         return fcan2;
      }
   
   }//Fin de la Clase





