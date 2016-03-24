//declaramos el package en el que se encontrara toda la practica
   package circuitos;
    
   import java.util.*;
   import java.util.Stack;
    
	/**
	*Circuito caracterizado por una composición interna de otros circuitos. 
	*
	*@author Fernando Diaz && Taoufik Aadia
	*@version 2.1
	*@see Circuito
	*/
    public class CircuitoEstructural extends Circuito{
   
     
      /** 
   	*Clase interna perteneciente la clase Circuito Estructural que permite calcular un circuito funcional 
      *a partir de una función canónica dada expresada como suma de productos
      */ 
       class MaquinaPostfija{
         
      /**Cadena que almacena la Función Canónica de los circuitos*/	
         private String funcionCanonica;
       
        /**Array de puertos de entrada */
         private PuertoEntrada[] puertosEntrada;
       
        /** Pila de circuitos para almacenar los resultados parciales de evaluar la función canónica */
         private Stack pilaCircuitos;
       
         /**Pila auxiliar de operadores para realizar la evaluación postfija de la función canónica.*/ 
         private Stack pilaOperadores;
       
        /**Objeto utilizado para recorrer la función canónica de entrada en notacion infija */ 
         private StringTokenizer st;
               	
        /**Constante para indicar el final de la pila de operadores*/ 
         private static final int TOK_EOL=-1; 
       
        /** Constante para representar el operador + de la función canónica e indicar su precedencia con respecto al resto de los operadores */
         private static final int TOK_MAS=2; 
       
        /** Constante para representar el operador * de la función canónica e indicar su precedencia con respecto al resto de los operadores */
         private static final int TOK_POR=3;
       
        /**Constante para representar el operador ! de la función canónica e indicar su precedencia con respecto al resto de los operadores */
         private static final int TOK_NEG=4;
       
        /**Constante para representar las variables xi de la función canónica e indicar su precedencia con respecto al resto de los factores */
         private static final int TOK_PIN=5;
         
      	/**Objeto de la Clase Integer que almacena la entrada de un circuito */
         private Integer pinEntrada;
      
        
      /**
        *Crea un objeto MaquinaPostfija a partir de una función canónica y un array de puertos de entrada.
        *@param funcionCanonica  función canónica en notación infija expresada como suma de productos.
        *@param puertosEntrada array de puertos de entrada
		*/
          public MaquinaPostfija(String funcionCanonica,PuertoEntrada[] puertosEntrada){
            
         	//asignamos al atributo funcionCanonica, el parámetro funcionCanonica
            this.funcionCanonica=funcionCanonica;
            
         	//asignamos al atributo puertosEntrada una referencia al parámetro puertosEntrada
            this.puertosEntrada=puertosEntrada;
          
            //inicializamos el atributo st, con el constructor de la clase StringTokenizer, 
            //pasando a éste por parámetros funcionCanonica, los tockens de parada, y un boolean true que 
         	//permita incluir a estos ultimos en otro token
            st=new StringTokenizer(funcionCanonica,"+*!",true);
            
         	//Creamos pilaCircuitos, con el constructor vacio de la clase Stack
            pilaCircuitos=new Stack();
            
         	//Creamos pilaOperadores, con el constructor vacio de la clase Stack
            pilaOperadores=new Stack();
            
         	//introducimos en  pilaOperadores, TOK_EOL despues de realizar el casting correspondiente
            pilaOperadores.push(new Integer(TOK_EOL));
         }
      
      
      //METODOS
      
       /** 
       *Método que calcula el circuito funcional a partir de la función canónica 
       *Para hacerlo recorre la función canónica obteniendo y procesando los diferentes elementos 
       *de la expresión: entradas xi y operadores negación, producto y suma 
       *@return Circuito funcional resultado de evaluar la función lógica 
       *@throws CircuitoException si queda mas de un circuito, no queda ninguno o el que queda es null, en la pilaCircuitos
       *@see #obtenerToken()
       *@see #procesarToken(int token)
       */
          public CircuitoFuncional calcularCircuito()throws CircuitoException {
           
            //variable local de tipo entero
            int token;
            
         	//Objeto de la clase CircuitoFuncional, inicializado a null,creado para almacenar el 
            //CircuitoFuncional que devuelve el método
            CircuitoFuncional salida=null;
         
            //Analizamos funcionCanonica procesando cada tocken, hacemos esto al menos una vez, ya que siempre tendremos como
            //primer tocken TOK_EOL, y hasta que encontramos el sigiente TOK_EOL (que indica el fin de la expresión)
            do {
               token= obtenerToken();
               procesarToken(token);
            } while (token != TOK_EOL); 
           
            //Después de todo el procesado de tockens, en pilaCircuitos solo debe quedar el Circuito Final, a devolver por el método
            if(!pilaCircuitos.isEmpty())
               salida= (CircuitoFuncional)pilaCircuitos.pop();
            
            //Si el último circuito de pilaCircuitos apunta  null, o si quedan mas circuitos, en la pila después de haber
            //sacado éste, la expresión que se pasa al constructor es errónea => lanzamos CircuitoExption
            if (salida==null ||!pilaCircuitos.isEmpty()) 
               throw new CircuitoException("Se intrudujo una expresión errónea");
               
            //devolvemos salida, después de comprobar que su valor es correcto
            else 
               return salida;
                  
         }
      
      
      /**
      *Método que encuentra el siguiente elemento de la función canónica (token) saltándose 
      *los espacios en blanco y devuelve el entero que lo representa cuando el resultado es una entrada 
      *también calcula el puerto al que debe conectarse.
      *Imprime un mensaje de error si no se reconoce la entrada 
      *
      *@return el entero que corresponde al token que extrae de la funcionCanonica
      *@see StringTokenizer
      */
          private int obtenerToken(){
            
         	//cadena que almacena un tocken de funcionCanonica
            String tockfCan;
            //entero que almacena el número de la entrada que se obtiene cuando el token no es un operador
            String nEnt;
         
            try {
               //alamcenamos en la variable local cada token de funcionCanonica
               tockfCan= st.nextToken();
            } 
            
                catch (NoSuchElementException e) {
               //si no quedan mas token's apilamos un TOK_EOL, q indica el fin de la pila
                  return TOK_EOL;
               } 
         
           //Analizamos los posibles token's a encontrar y devolvemos en cada caso la constante correspondiente
            if (tockfCan.equals("+")) 
               return TOK_MAS;
            else if (tockfCan.equals("*")) 
               return TOK_POR;
            else if(tockfCan.equals("!"))
               return TOK_NEG;
            //si el token no es un operador, almacenamos en PinEntrada el numero de entrada (realizando el casting correspondiente)
            else{
               nEnt=tockfCan.substring(1);
               pinEntrada=new Integer(nEnt);
               //devolvemos  la constante correspondiente
               return TOK_PIN;
            }
         
         }
      
      
      /**
      * Método que procesa los operadores negación, producto y suma de la función canónica.
      *Para hacerlo crea el circuito representado por dicho operador y extrae de la pila de circuitos tantos circuitos
      *como entradas necesite Una vez terminado el proceso saca el operador de la pila de operadores
      *@param token entero que representa al primer operador de la pila de operadores 
      *@throws CircuitoException si en la pilaCircuitos no quedan suficientes circuitos para desapilar el operador correspondiente o, si quedan, apuntan a null
      */
          private void procesarOperador(int token)throws CircuitoException {
            
         	//creamos dos Objetos Circuito, que inicializamos apuntando a null
            Circuito operador=null;
            Circuito circuito1=null;
            
            //si pilaCircuitos no esta vacia, extraemos un primer circuito que almacenamos en c1
            if(!pilaCircuitos.isEmpty())
               circuito1= (Circuito)pilaCircuitos.pop();
            
            //Analizamos los casos en los que el parámetro no es un TOK_NEG
            if(token!=TOK_NEG){
               
            	//Para estos casos necesitamos un segundo circuito, que creamos e inicializamos a null
               Circuito circuito2=null;
               
            	//Si pilaCircuitos no está vacia, desapilamos un circuito que almacenamos en circuito2;
               if(!pilaCircuitos.isEmpty())
                  circuito2= (Circuito)pilaCircuitos.pop();
                  
               //circuito1 y circuito2 , no pueden apuntar a null, en este caso lanzamos una exception
               if (circuito1==null || circuito2==null) 
                  throw new CircuitoException("Pila de circuitos incorrecta");
                  
               //si no salta la exception, es decir, la pila es correcta, analizamos los demás valores que puede tomar token
               else{
               
                  //si el parámetro del método es un TOK_MAS, asignamos a operador una PuertaOR con dos entradas
                  //y conectamos los dos pins de entrada con cada circuito, circuito1 y circuito2
                  if(token==TOK_MAS)
                     operador=new PuertaOR(2);
                  //si el parámetro del método es un TOK_POR, asignamos a operador una PuertaAND con dos entradas
                  //y conectamos los dos pins de entrada con cada circuito, circuito1 y circuito2
                  if(token==TOK_POR)
                     operador=new PuertaAND(2);
               	
                  operador.conectar(0,circuito1);
                  operador.conectar(1,circuito2);
                 
                  //apilamos el nuevo Circuito de operador, en la pila de operadores
                  pilaCircuitos.push(operador);
               }
            }
            
            //Si el parámetro es un TOK_NEG y la pila de valores es correcta, asignamos a operador una PuertaNOT de una entrada
            //y la conectamos a circuito1
            else if(token==TOK_NEG){
               
            	//Se lanza una exception si c1 apunta a null
               if (circuito1==null) 
                  throw new CircuitoException("Pila de valores incorrecta");
                  
               else{
                  operador=new PuertaNOT(1);
                  operador.conectar(0,circuito1);
                  //apilamos el nuevo Circuito de operador, en la pila de operadores
                  pilaCircuitos.push(operador);
               }
            }
            
            //Si el parámetro token no ha cumplido ninguno de los casos anteriores, este es incorrecto y se lanza un a exception
            else
               throw new CircuitoException("Token incorrecto(esto nunca deberia pasar)");
         }
      
                  
      /**
      *Método que analiza la función canónica. Si encuentra una entrada xi la introduce directamente en la pila
      *de Circuitos si encuentra un operador negación, producto o suma decide cual procesar primero 
      *teniendo en cuenta la precedencia de operadores. 
      *
      *throws CircuitoException
      *@see #procesarOperador(int token)
      */
          private void procesarToken(int token)throws CircuitoException {
            
         	//entero que almacena el ultimo operador a procesar
            int cima;
         
            //Analizamos los distintos casos, dependiendo del valor que tome el token pasado por parámetro
            switch (token) {
               case TOK_PIN:
                 //se apila un puertoEntrada en la pilaCircuitos
                  pilaCircuitos.push(puertosEntrada[pinEntrada.intValue()]);
                  break;
            
               case TOK_EOL:
                  // se desapila todo, almacenando en cima el ultimo operador sacado( despues de realizar elcasting debido) 
                  while ((cima=((Integer)pilaOperadores.pop()).intValue()) != TOK_EOL)
                  //procesamos todos los operadores q va tomando la variable cima   
                     procesarOperador(cima);	    
                  break;
            
               case TOK_MAS:
                  // precedencia menor: se desapila todo
                  while ((cima=((Integer)pilaOperadores.pop()).intValue()) != TOK_EOL)
                     procesarOperador(cima);
               
                  // volvemos a apilar el ultimo token, este es:TOK_EOL 
                  pilaOperadores.push(new Integer(cima));
                  // apila el operador, que se sacó( TOK_MAS)
                  pilaOperadores.push(new Integer(token));
                  break;
            
               case TOK_POR:
                  // precedencia mayor: se desapila todo hasta un +, procesando los distintos operadores q toma cima
                  //esto solo puede ser la negación
                  while ((cima=((Integer)pilaOperadores.pop()).intValue()) != TOK_EOL &&cima != TOK_MAS )
                     procesarOperador(cima);
               
                  // devuelve a PilaOperadores el último operador desapilado,
                  pilaOperadores.push(new Integer(cima));
                  // apila el operador que encontaramos en un principio, TOK_POR
                  pilaOperadores.push( new Integer(token));
                  break;
               
               case TOK_NEG:
                  
               	// es el de mayor precedencia
                  while((cima=((Integer)pilaOperadores.pop()).intValue())==TOK_NEG)
                     procesarOperador(cima);
                  
                  //devuelve el ultimo operador desapilado
                  pilaOperadores.push(new Integer(cima));
                  //Apila en PilaOperadores un TOK_NEG
                  pilaOperadores.push(new Integer(token));
                  break;
                     
            }
         }
      
      }
   
      /**Circuito interno del que se obtiene la salida de este circuito*/
      protected Circuito componenteSalida;
      
   	/** Circuitos que interconectan las entradas de este circuito con las entradas de los circuitos internos.*/
      protected PuertoEntrada [] puertosEntrada;
   
   
   
   //CONSTRUCTORES
       
      /**
      *Crea un nuevo circuito con un determinado array de entradas. Crea los puertos de entrada adecuados y los conecta. 
      *@param entradas array de entradas 
      *@throws CircuitoException si entradas es null
      */
       public CircuitoEstructural(Circuito[] entradas)throws CircuitoException{
         
      	//se lanza una CircuitoException si entradas apunta a null
         if(entradas==null)
            throw new CircuitoException("No se puede crear un circuito estructural con un array de entradas que apunta a null");
         
         else{
            //llamamos al metodo setNumEntradas de esta clase que es el unico que permite crear un array de puertosEntrada,
         	//y asigna el numero de entradas al circuito creado                                                     
            setNumEntradas(entradas.length);                   
            
            //llamamos al metodo setEntradas para asignar entradas al atributo del mismo nombre 
         	//y conectar los puertosEntrada
            setEntradas(entradas);	
         }
      		
      }
   
    
   
     /**
      *Crea un nuevo circuito con un determinado número de entradas. Crea los puertos de entrada adecuados. 
      *@param numEntradas número de entradas.
      *@throws CircuitoException si numEntradas es menor que cero.
      */
       public CircuitoEstructural(int numEntradas) throws CircuitoException{
         
      	//se recogen las excepciones de éste método y se vuelven a lanzar.
         //se llama al metodo setNumEntradas de esta clase para fijar el numero de entradas y crear los puertos de entrada
         setNumEntradas(numEntradas);
          
      }
   
   
    /**
    *Método que construye un nuevo circuito estructural que implementa la función canónica dada, expresada 
    *como suma de productos. Cuando la función canónica representa una fuente es decir es "0" o "1"
    *simplemente crea la fuente y la conecta a la salida. En el caso de una función canónica normal utiliza un objeto
    *de una clase auxiliar (MaquinaPostfija) que permite generar un CircuitoFuncional a partir de la función canónica. 
    *
    *@param funcionCanonica es la función canónica, apartir de la cual se constuye el circuito
    *@throws CircuitoException  si funcionCanonica es null o una cadena vacía
    *@see #getNumeroEntradas(String funcionCanonica)
    *@see #setNumEntradas(int numEntradas)
    *@see #conectarSalida(Circuito componenteSalida) 
    *@see MaquinaPostfija
    */
       public CircuitoEstructural(java.lang.String funcionCanonica) throws CircuitoException{
         
      	//Se lanza una excepción si funcionCanonica es null o una cadena vacía
         if(funcionCanonica==null || funcionCanonica.equals(""))
            throw new CircuitoException("No hay función Canónica");
         
         //tratamos por un lado el caso de que funcionCanonica sea una fuente, creamos asi el objeto de esta clase y lo 
         // asignamos a componenteSalida
         else if(funcionCanonica.length()==1){
            
            Circuito fuente=null;
            
            if(funcionCanonica.charAt(0)=='0')
               
            	//si la expresión pone un cero, creamos una fuente false.
               fuente=new Fuente(false);
            
            else if(funcionCanonica.charAt(0)=='1')
               
            	//si la funcionCanonica pone un uno, creamos una fuente true.
               fuente=new Fuente(true);
            
            //se asigna fuente al atributo componenteSalida
            componenteSalida=fuente;
         }
         
         //si la funcionCanonica, no es la de una fuente, creamos un objeto de MaquinaPostfija para obtener el componenteSalida
         else{
           //llamamos al metodo setNumEntradas utilizando el metodo getNumeroEntradas que proporciona el numero de entradas del circuito
           //a partir de la funcion canonica, para establecer el numero de entradas y los puertosEntrada del circuito 
            setNumEntradas(getNumeroEntradas(funcionCanonica));
         	
         	//creamos un objeto maquinapostfija con la funcioncanonica que se pasa y el atributo puertosEntrada que inicializo
         	//el metodo anterior(setNumEntradas()))
            MaquinaPostfija mp=new MaquinaPostfija(funcionCanonica,puertosEntrada);
            
         	//asignamos al atributo componenteSalida el circuito que se obtiene despues de llamar al metodo calcularCircuito()
         	//del objeto del mp de maquinaPostfija
            componenteSalida=mp.calcularCircuito();
         	
         }     
      }
      
       
     
   //METODOS 
                 
     /**
     *Establece una conexión entre un determinado pin de entrada de este circuito y la salida de otro circuito.
     *Para ello modifica la posición adecuada de entradas y conecta el puerto de entrada correspondiente. 
     *
     *@param pinEntrada número de pin de entrada de este circuito (entre 0 y entradas.length - 1).
     *@param circuito  circuito a cuya salida se realiza la conexión. 
     *@throws CircuitoException  si pinEntrada está fuera del rango permitido o si entradas o puertosEntrada son null.
     */
       public void conectar(int pinEntrada,Circuito circuito)throws CircuitoException{
         
      	//Si circuito, entradas o puertosEntrada son null,se lanza una CircuitoException
         if(circuito==null)
            throw new CircuitoException("El Circuito no puede apuntar a null");
         
         else if(puertosEntrada==null)
            throw new CircuitoException("El array de Puertos Entrada no puede ser null");
         
         
         /*En este metodo no es necesario comprobar que el atributo entrada apunte a null, que pinEntrada
         *sea menor que cero, o que sea mayor que el tamaño del atributo entradas menos uno ya que en estos 
         *casos el metodo conectar de la clase Circuito (al que llamamos aqui) lanza las CircuitoException necesarias*/
                  
         else{
         
         //Se hace que, si el pinEntrada es correcto, dicha posicion del array de entradas
         // apunte al circuito que se pasa
            super.conectar(pinEntrada,circuito);
            
         	//tambien se conecta el correspondiente puertoEntrada al circuito que se pasa por parametro
            puertosEntrada[pinEntrada].conectar(0,circuito);
         }
      }
      
   
     /**
     *Elimina una conexión asociada a un determinado pin de entrada. Desconecta también el puerto de entrada correspondiente. 
     *@param pinEntrada  número de pin 
     *@throws CircuitoException si pinEntrada está fuera del rango permitido ,el número de Entaradas o puertosEntarda son null.
     */
       public void desconectar(int pinEntrada) throws CircuitoException{
         if(getEntradas()==null)
            throw new CircuitoException("Entradas no puede ser null");
         
         else if(puertosEntrada==null)
            throw new CircuitoException("El array de Puertos Entrada no puede ser null");
         
         /*En este metodo no es necesario comprobar que pinEntrada sea menor que cero,
         * o que sea mayor que el tamaño del atributo entradas menos uno ya que en estos 
         *casos el metodo desconectar de la clase Circuito (al que llamamos aqui) lanza las CircuitoException necesarias*/  
         else{
          
            super.desconectar(pinEntrada);
            puertosEntrada[pinEntrada].desconectar(0);
         }
      }
   
      /**
     *método que devuelve el número de entradas, analizando la función canónica.
     *@param funcionCanonica Función Canónica del circuito
     *@return numero de Entradas 
     *@throws CircuitoException
     */
       private int getNumeroEntradas(java.lang.String funcionCanonica) throws CircuitoException{
       
       
      	//variable local de tipo entero, para almacenar el valor a devolver ( el número de entradas)
         int numEnt=0;
         
      	//variable local de tipo entero,que utilizamos de contador 
         int cont=0;
         
         //para que la Función tenga, al menos una entrada,la cadena tiene que tener mas de un carácter
         if(funcionCanonica.length()>1){
         
         //Analizamos el primer sumando, este debe contener las misma entradas que los demás
         //como este sumando es un producto de entradas (Suma de productos) incrementamos numEnt 
         //por cada simbolo de multiplicación: * .
            while(cont<funcionCanonica.length()&&funcionCanonica.charAt(cont)!='+'){
            
               if(funcionCanonica.charAt(cont)=='*')
                  numEnt++;
            
               cont++;
            }
         //sumamos una entrada mas ya que el número de Entradas es el número de productos que se realizan mas uno.
            numEnt++;
         }
        //devolvemos el Número de entradas
         return numEnt;
      }
   
   	   
    /**
     *Establece las entradas del circuito. Se sobreescribe el método de Circuito porque también se deben actualizar los puertos de entrada.
     *Si el circuito ya tiene un número de entradas asignado, este número no se puede cambiar. 
     *@param entradas Array de entradas.
     *@throws CircuitoException  si entradas es null o se intenta modificar el número de entradas de un circuito previamente construido.
     */
       public void setEntradas(Circuito[] entradas) throws CircuitoException{
       
        //Se lanzan las CircuitosException si es necesario.
         if(getEntradas()==null)
            throw new CircuitoException("el atributo entradas no puede ser null");
         
         else if(entradas==null)
            throw new CircuitoException("entradas en el parámetro, no puede ser null");
         
         else{
            if(puertosEntrada!=null){
            
            //Si puertosEntrada existe y su tamaño no coincide con el de el parametro entradas se lanza CircuitoException
               if(puertosEntrada.length!=entradas.length)
                  throw new CircuitoException("no se puede cambiar el número de entradas de un circuito previamente constuido");
            }
            
            
            else
            //se asigna al Circuito el número de entradas y se crea el atributo PuertosEntradas con el mismo tamaño
               setNumEntradas(entradas.length);
            
         	//llamamos al metodo setEntradas de la clase Circuito para asignar entradas al atributo del mismo nombre
            super.setEntradas(entradas);
            
         	//conectamos todos los puertos de entrada a las entradas correspondientes
            for(int i=0;i<entradas.length;i++)
               puertosEntrada[i].conectar(0,entradas[i]);
                  
         }
       
      }
   	
   
     /**
     *Crea un array de Circuito de tamaño numEntradas y se lo asigna al atributo entradas.
     *Crea un nuevo array de PuertoEntrada de tamaño numEntradas y se asigna al atributo de puertos de entrada.
     *Si el circuito ya tiene un número de entradas asignado, este número no se puede cambiar. 
     *@param numEntradas número de entradas
     *@throws CircuitoException  si  se intenta modificar el número de entradas de un circuito previamente construido, o el número de entradas es negativo.
     */
       public void setNumEntradas(int numEntradas) throws CircuitoException{
        
        //Creamos un Circuito y le asignamos el número de Entradas que se le pasa al método, como parámetro          
         super.setNumEntradas(numEntradas);       
            
         //Si el array de Puertos de Entrada ya existe, comprobamos que su tamaño es correcto
         //si no lo es , lanzamos una CircuitoException
         if(puertosEntrada!=null){
            if(puertosEntrada.length!=numEntradas)
               throw new CircuitoException("No se puede modificar el número de entradas del circuito");
         }
         
         //Si el array PuertosEntrada es null, lo creamos con tamaño el número de entradas que se pasa al método por parámetro
         else{
         
         //creamos el atributo PuertosEntrada con número de entradas el que se nos pasa por parámetro 
            puertosEntrada=new PuertoEntrada[numEntradas];
            //Creamos un objeto de la clase PuertoEntrada en cada posición del array PuertosEntrada
            for(int i=0;i<numEntradas;i++)
               puertosEntrada[i]=new PuertoEntrada();
         }
      }
      
       
      
    /**     
     *Devuelve el resultado de evaluar el valor lógico de la salida de este circuito (true equivale a un 1 lógico,false equivale a un 0 lógico). 
     *Para ello, devuelve el resultado de la incocación a evaluar() del componente de salida. 
     *@return valor lógico de la salida 
     *@throws CircuitoException  si el circuito no está adecuadamente conectado (si componenteSalida es null o se produce una excepción en algún punto de la cadena de invocación a evaluar())
     */
       public boolean evaluar() throws CircuitoException{
         //Se lanzan las excepciones necesarias      
         if(getEntradas()==null)
            throw new CircuitoException(" Entradas no puede ser null");
         
         else if(puertosEntrada==null)
            throw new CircuitoException(" PuertosEntrada no puede ser null");
         
         else if(getNumeroEntradas()!=puertosEntrada.length)
            throw new CircuitoException(" El número de entradas debe corresponder con el de puertos de Entrada");
         
         else if(componenteSalida==null)
            throw new CircuitoException(" La salida del circuito no puede ser null");
         
         else
         //Se devuelve el valor de lógico de evaluar componenteSalida
            return componenteSalida.evaluar();
       
      }
   
     
    /**
     *Conecta la salida del puerto de entrada indicado por pinEntrada con la entrada pinInterno del circuito interno. 
     *@param pinEntrada  pin de entrada de este circuito.
     *@param pinInterno  pin de entrada del circuito interno.
     *@param interno  circuito interno a conectar .
     *@throws CircuitoException  si algún pin está fuera de rango, interno es null o hay algún otro error.
     */
       public void conectarAPuerto(int pinEntrada, int pinInterno, Circuito interno) throws CircuitoException{
      
      //Se lanzan las excepciones necesarias
         if(interno==null)
            throw new CircuitoException("El circuito interno no puede ser null");
         
         else if(pinEntrada<0)
            throw new CircuitoException("El número de pin de entrada no puede ser negativo");
         
         else if(pinInterno<0)
            throw new CircuitoException("El número el pin de entrada del circuito interno no puede ser negativo");
         
         else if(getEntradas()==null)
            throw new CircuitoException("Las entradas no pueden ser null");
         
         else if(puertosEntrada==null)
            throw new CircuitoException("El array de Puertos de entrada no puede ser null");
         
         else if(pinEntrada>=puertosEntrada.length)
            throw new CircuitoException("El número del pin de entrada debe ser siempre menor al número total de puertos de entrada");
         
         else if(pinInterno>=interno.getNumeroEntradas())
            throw new CircuitoException("El número del pin de entrada del circuito interno, debe de ser siempre menor al número total de entradas");
         
         //Si no ha se ha lanzado, ninguna CircuitoException, se conecta la salida del puerto de entrada con la entrada pinInterno del circuito interno. 
         else
            interno.conectar(pinInterno,puertosEntrada[pinEntrada]);
      }
      
     
    /**
     *Desconecta la entrada pinInterno de un circuito interno del puerto de entrada al que previamente estuviese conectado. 
     *@param pinInterno   pin de entrada del circuito interno.
     *@param interno  circuito interno. 
     *@throws CircuitoException si interno es null o hay algún error en la desconexión
     */
       public void desconectarDePuerto(int pinInterno, Circuito interno) throws CircuitoException{
      
         //Se lanzan las excepciones necesarias
         if(interno==null)
            throw new CircuitoException("El circuito interno no puede ser null");
         
         else if(pinInterno<0)
            throw new CircuitoException("El número el pin de entrada del circuito interno no puede ser negativo");
         
         else if(pinInterno>=interno.getNumeroEntradas())
            throw new CircuitoException("El número del pin de entrada del circuito interno,debe de ser siempre menor al número total de entradas");
         
         else if(puertosEntrada==null)
            throw new CircuitoException("El array de Puertos de entrada no puede ser null");
         
         else if(getEntradas()==null)
            throw new CircuitoException("Las entradas no pueden ser null");
         
         else if(getNumeroEntradas()!=puertosEntrada.length)
            throw new CircuitoException("El número de entradas debe  coincidir con el número de puertos de entrada");
         
         //Si no se ha lanzado ninguna CircuitoException, se desconecta el la entrada del circuito interno del puerto de entrada 
         else
            interno.desconectar(pinInterno);
      }
   
   
     /**
     *Conecta la salida de un circuito interno a la salida de este circuito. 
     *@param componenteSalida  circuito interno.
     *@see #desconectarSalida()
     */
       public void conectarSalida(Circuito componenteSalida){
         this.componenteSalida=componenteSalida;
      }
   
      
    /**
     *Desconecta la salida de un circuito interno de la salida de este circuito. 
     */
       public void desconectarSalida(){
         componenteSalida=null;
      }
   
   }//FIN DE LA CLASE