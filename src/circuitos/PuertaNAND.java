   //declaramos el package en el que se encontrara toda la practica.
   package circuitos;
   
/**
*Clase que representa una puerta l�gica NAND 
*@author Fernando Diaz & Taoufik Aadia
*@version 2.1
*@see CircuitoFuncional
*@see CircuitoException
*/
public class PuertaNAND extends CircuitoFuncional{
   
   
//CONSTRUCTORES
   
   /**Crea una nueva puerta sin declarar entradas*/
	public PuertaNAND(){
		  super();
	 }
   
   
   /**
   *Crea una nueva puerta con un determinado array de entradas
   *@param entradas array de Entradas
	*@throws CircuitoException si el array de entradas apunta a null y si el tama�o de entradas es menor que 2
   */
      public PuertaNAND(Circuito[] entradas)throws CircuitoException{
		  if(entradas==null)
            throw new CircuitoException("El numero de entradas de una puerta NAND debe ser mayor o igual que 2");
         else if(entradas.length<2)
            throw new CircuitoException("El numero de entradas de una puerta NAND debe ser mayor o igual que 2");
         else
            setEntradas(entradas);
      }
   
     
	  
   /**
   *Crea una puerta NAND con un determinado n�mero de entradas. 
   *@param numEntradas Numero de entradas de la puerta(mayor o igual a 2).
   *@throws CircuitoException si numEntradas es un numero menor que 2
   */
	  public PuertaNAND(int numEntradas)throws CircuitoException{
		  //Se comprueba que el numero de entradas es mayor o igual que dos 
         if(numEntradas<2)
            throw new CircuitoException("El numero de entradas de una puerta NAND debe ser mayor o igual que 2");
         else
            setNumEntradas(numEntradas);
      }
   
   
   
   
		//METODOS
		
		/**
		*Funci�n de transferencia de una puerta NAND. Calcula el valor de salida en funci�n 
		*de los valores de entrada, seg�n la funci�n de transferencia de la puerta.
		*@param  valoresEntrada  Array de valores (boolean) a la entrada. 
		*@return  true si alg�n valor de entrada es false, false si todos los valores a la entrada son true 
		*@throws CircuitoException si el tama�o del array de valores de entrada no coincide con el n�mero de entradas del circuito, si el numero de entradas de la puerta es menor que dos, y si el tam�o de valoresEntrada es menor que dos
		*@see #getNumeroEntradas()
		*/
		protected final boolean funcionTransferencia(boolean[] valoresEntrada)throws CircuitoException{
		//Comprobamos que la puerta tiene mas de dos entradas
         if(getNumeroEntradas()<2)
            throw new CircuitoException("El numero de entradas de una puerta AND debe ser mayor o igual que 2");
			//comprobamos que la longitud del array valoresEntrada es valida
			//y lanzamos una excepciones en caso de que no lo fuese.
			 if (valoresEntrada==null)
		 		throw new CircuitoException("Error: Se ha pasado un array valoresEntrada que apunta a null");
			 if (valoresEntrada.length<2)
				throw new CircuitoException("Error: El numero de entradas (tama�o de valoresEntrada) tiene que ser mayor o igual que dos");
			if(valoresEntrada.length!=getNumeroEntradas())
				throw new CircuitoException("Error: El tama�o de valoresEntrada no coincide con el n�mero de entradas del circuito");
			
			//variable local,de tipo boolean, que almacena el valor a devolver
			 boolean ftransf= false;
			//variable local, que nos declaramos para recorrer el array valoresEntrada
			int cont=0;
			
			//Recorremos el array buscando alguna posicion con valor "false", pues es suficiente 
			//con que halla una, para que el valor de la funcionTransferencia, sea "True"
			while(cont<valoresEntrada.length&&ftransf==false){
				if(valoresEntrada[cont]==false)
					ftransf=true;
			 
			 cont++;
		    }
		
		 //solo si no hay ningun "false" devolver� "false"
         return ftransf;
      }
      
   
  
	/**
	*Devuelve la funci�n can�nica de la puerta, como suma de productos.
	*@return String con la funci�n de transferencia de la puerta cadena vac�a si no existen entradas.
	*@throws CircuitoException Si el n�mero de entradas de la puerta es menor que dos
	*@see #getNumeroEntradas()
	*@see #getFuncionCanonica()
	*/
   public java.lang.String getFuncionCanonica()throws CircuitoException{
	     //Se lanza una excepci�n si el N�mero de Entradas es menor a dos ya que esta puerta 
	     //tienes al menos dos entradas
		 if((getNumeroEntradas())<2)
            throw new CircuitoException("Una puerta NAND necesita al menos dos entradas");
			
		//creamos un array de booleanos para almacenar la tabla de verdad de esta puerta
		//que tiene "true" en todas las posiciones menos en la �ltima.
		//Le damos el tama�o correcto
        boolean [] salida=new boolean[(int)(Math.pow(2,getNumeroEntradas()))];
			
		//recorremos salida asignando "true" a todas sus posiciones menos a la ultima
		for(int i=0;i<salida.length-1;i++)
			salida[i]=true;
		 
		//asignamos "false" a la �ltima posici�n del array salida
		salida[salida.length-1]=false;
			
		//creamos un objeto de la clase CircuitoTablaVerdad utilizando el constructor
		//que nos permite introducirle un array de booleanos, este es "salida", que va a ser su tabla de la Verdad
	     CircuitoTablaVerdad puertaNAND=new CircuitoTablaVerdad(salida);
			
		//Declaramos la cadena a devolver por el m�todo, esta es la que se obtiene
		//al llamar al m�todo getFuncionCanonica(), de la clase CircuitoTablaVerdad,
		//sobre puertaNAND,que nos acabamos de crear
		String fcan=puertaNAND.getFuncionCanonica();
		
		//El m�todo devuelve fcan
		return fcan;
      }
   
   }//Fin de la Clase