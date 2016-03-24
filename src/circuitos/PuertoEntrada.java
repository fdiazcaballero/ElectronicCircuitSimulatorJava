   package circuitos;

/** 
*
*Circuito diseñado para interconectar una entrada de un CircuitoEstructural con una o más entradas 
*de circuitos de su estructura interna. Tiene siempre una única entrada
*
*@author Fernando Diaz & Taoufik Aadia
*@version 2.1
*
*@see CircuitoFuncional
*@see CircuitoEstructural
*@see CircuitoException
*
*/
    public class PuertoEntrada extends CircuitoFuncional{
   
   /**
   *Crea un nuevo PuertoEntrada con una entrada (inicialmente desconectada) 
   */
       public PuertoEntrada(){
       //crea un circuito sin entradas
         super();
         
      	//el metodo setNumEntradas(int numEntradas) puede lanzar CircuitoException
      	//como el constructor no la puede lanzar la capturamos aqui, si embargo se sabe que nunca va a ocurrir
         try { 
         
            setNumEntradas(1); 
         } 
             catch (CircuitoException e) { 
             //nunca ocurrira
            } 
      
      }
   
   /**
   *Función identidad. Devuelve el mismo valor que tiene a su entrada. Utilizada en CircuitoEstructural
   *@param valoresEntrada  array de valores booleanos a la entrada 
   *@return el valor de la entrada
   *@throws CircuitoException  si entradas o valoresEntrada son null o tienen un número de elementos distinto de 1
   *@see #evaluar()
   */
       protected final boolean funcionTransferencia(boolean[] valoresEntrada) throws CircuitoException{
        
        //si el atributo entradas apunta a null se lanza CircuitoException
         if(this.getEntradas()==null)
            throw new CircuitoException("El atributo entradas de este PuertoEntrada apunta a null");
         
         //si valoresEntrada apunta a null se lanza CircuitoException
         else if(valoresEntrada==null)
            throw new CircuitoException("El parametro valoresEntrada apunta a null");
         
         //Si el tamaño del atributo entradas es distinto de 1 se lanza CircuitoException
         else if(getNumeroEntradas()!=1)
            throw new CircuitoException("Un puertoEntrada no puede tener un numero de entradas distinto de 1");
         
         //Si la longitud de valoresEntrada es distita de 1 se lanza Circuito Exception
         else if (valoresEntrada.length!=1)
            throw new CircuitoException("Un puertoEntrada no puede tener un numero de entradas distinto de 1");
         
         else
            return valoresEntrada[0];
      }
   
   
   /* 
   *Devuelve la función canónica del circuito (siempre es "x0") 
   *@return La función canónica (siempre es "x0"). 
   *@throws CircuitoException  si entradas es null o tiene un número de elementos distinto de 1
   */
       public java.lang.String getFuncionCanonica() throws CircuitoException{
        
        //Si el atributo entradas apunta a null se lanza CircuitoException
         if(this.getEntradas()==null)
            throw new CircuitoException("El atributo entradas apunta a null");
         
         //si el atributo entradas tiene un numero de elementos distinto de 1 se lanza CircuitoException
         else if(this.getNumeroEntradas()!=1)
            throw new CircuitoException("El atributo entradas de un puertoEntrada no puede tener mas de un elemento");
         
         else
            return "x0";
      }
   
   }
