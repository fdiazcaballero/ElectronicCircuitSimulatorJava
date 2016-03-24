//declaramos el package en el que se encontrara toda la practica
   package circuitos;
//se importa java.util.*; necesario para utilizar StringTokenizer
   import java.util.*;

/**
*La Clase CircuitoTablaVerdad
*
*representa la funcionalidad de un circuito mediante su tabla de verdad. 
*Dado un circuito de N elementos, 
*una tabla de verdad se representa como un array de 2^N elementos boolean, donde 
*cada uno de ellos representa la salida para una combinación de entradas dada.
*
*Esta clase hereda de la superclase Circuito
*@author Fernando Diaz && Taoufik Aadia
*@version 2.1
*@see Circuito
*@see CircuitoException
*
*/
    public class CircuitoTablaVerdad extends Circuito{
    
   //ATRIBUTO
   
   /**
   *Tabla de verdad del circuito
   *Cada combinacion de entradas del circuito representa un indice del array.
   *El elemento del array tabla de verdad correspondiente a ese indice es la 
   *salida de la tabla de verdad para esas entradas
   *
   */
      private boolean [] tablaVerdad;
   
   //CONSTRUCTORES
   
   /** 
   *Crea un nuevo circuito con una tabla de verdad y un número de entradas dado.
   *@param  tablaVerdad  tabla de verdad del circuito 
   *@throws CircuitoException  si el array que se pasa por parametros apunta a null y si la longitud del array tablaVerdad no se puede expresar de la forma 2^N (donde N es el numero de entradas del circuito)
   *@see #setNumEntradas(int numEntradas)
   */
       public CircuitoTablaVerdad(boolean[] tablaVerdad)throws CircuitoException{
      
      // llamamos al constructor de la superclase para inicializar los atributos etiqueta y entradas
         super();
      //inicializamos el atributo tablaVerdad
         this.tablaVerdad=new boolean[0];
      
      //Se comprueba que el array que se pasa no apunte a null, en caso afirmativo lanza CircuitoException
         if (tablaVerdad==null)
            throw new CircuitoException("Se ha pasado una tablaVerdad que apunta a null");
      /*Este primer if es para particularizar los tres casos en los que el siguiente if falla, debido al casting
      Si la longitud de la tabla de la verdad es 0 y 1 lanza una CircuitoException*/
         if(tablaVerdad.length<2)
            throw new CircuitoException("La longitud de una tablaVerdad no puede ser menor que dos");
      
      /*si la longitud del array tablaVerdad no se puede expresar de la
       *forma 2^N (donde N es el numero de entradas del circuito), es decir
       *Si la longitud del array no es una potencia de dos, el logaritmo en base dos de dicha longitud no sera un
       *numero natural por tanto al hacer el casting se redondeara al entero mas proximo, si elevamos dos a dicho 
       *numero entero ya no dara la longitud del array y entonces lanzara una CircuitoException*/
         if(Math.pow(2,(int)(Math.log(tablaVerdad.length)/Math.log(2)))!=tablaVerdad.length)
            throw new CircuitoException("La longitud de tablaVerdad debe ser una potencia de dos");
      
      /*Si el array tablaVerdad es valido se hace el log en base dos de su longitud y se utiliza el metodo setNumEntradas
      *para crearse un array de entradas con dicha longitud. No se puede llamar al constructor super porque no es la
      *primera sentencia del constructor, por eso se utiliza este metodo que tiene la misma funcionalidad*/
         setNumEntradas((int)(Math.log(tablaVerdad.length)/Math.log(2)));
      
      //Se incicializa el atributo tablaVerdad
         this.tablaVerdad=tablaVerdad;
      
      }
   
   /**
   *Crea un nuevo circuito a partir de un BufferedReader Este BufferedReader es lo que queda del fichero
   *de descripción del circuito tras eliminar la primera línea El fichero de descripción tiene el 
   *siguiente formato: NombreClase\n nombreCircuito 10101010\n donde "10101010" es un ejemplo de tabla
   *de verdad, que debe interpretarse como {true,false,true,false,...}
   *@param in  BufferedReader que guarda el contenido del fichero de descripción a partir de la segunda línea.
   *@throws CircuitoException  si se le pasa una tablaVerdad null y si la longitud del array tablaVerdad no se puede expresar de la forma 2^N (donde N es el numero de entradas del circuito)
   *@throws java.io.IOException
   *@see #setNumEntradas(int numEntradas)
   */
       public CircuitoTablaVerdad(java.io.BufferedReader in)throws CircuitoException,java.io.IOException {
        // llamamos al constructor de la superclase para inicializar los atributos etiqueta y entradas
         super();
       //inicializamos el atributo tablaVerdad
         this.tablaVerdad=new boolean[0];
      //linea - variable local que contiene la linea del fichero en la que se pasa la etiqueta y 
      //los unos y ceros que representan la tabla de la verdad
         String linea=in.readLine();
      //se repite este bucle while mientras que la longitud de la cadena linea sin espacios en blanco 
      //al principio ni al final sea menor que 1 por si hay lineas en blanco
         while(linea.trim().length()<1)
         //Lee la siguiente linea
            linea=in.readLine();
      		
      /*Se crea un objeto de la clase StringTokenizer que "parte" el String linea cuando 
      *encuentra un espacio que es la separacion entre la etiqueta y los numeros de la tablaVerdad */
         StringTokenizer st =new StringTokenizer(linea," ");
      		
      /*Se llama al metodo setEtiqueta que da el valor al atributo etiqueta
      *para pasarle el parametro se utiliza el metodo nextToken de la clase StringTokenizer que
      *devuelbe el siguiente token que sera la etiqueta*/
         setEtiqueta(st.nextToken());
      	
      //Comprobamos que se ha pasado una tabla de la verdad
         if(!st.hasMoreTokens())
            throw new CircuitoException("Es necesario que despues de la etiqueta vaya una tabla de la verdad");
      		
      //Se llama al siguiente token y se le asigna a la variable local s que contiene los 0s y 1s*/
         String s=st.nextToken();
      
      //Se comprueba que s no apunta a null
         if(s==null)
            throw new CircuitoException("No se ha pasado tablaVerdad");
      		
      /*Se le pone la longitud de la cadena s a la variable local tVerdad, ya que cada 1 ó 0 va representar una posicion 
      *de dicho array false para el 0 true para el 1*/
         boolean []tVerdad=new boolean[s.length()];	
      
      /*Este primer if es para particularizar los tres casos en los que el siguiente if falla, debido al casting
      *Si la longitud de la tabla de la verdad es 0 y 1 lanza una CircuitoException*/
         if(tVerdad.length<2)
            throw new CircuitoException("el tamaño de tablaVerdad no puede ser menor que dos");
      	 
      /*si la longitud del array tablaVerdad no se puede expresar de la
       *forma 2^N (donde N es el numero de entradas del circuito), es decir
       *Si la longitud del array no es una potencia de dos, el logaritmo en base dos de dicha longitud no sera un
       *numero natural por tanto al hacer el casting se redondeara al entero mas proximo, si elevamos dos a dicho 
       *numero entero ya no dara la longitud del array y entonces lanzara una CircuitoException*/
         if(Math.pow(2,(int)(Math.log(tVerdad.length)/Math.log(2)))!=tVerdad.length)
            throw new CircuitoException("el tamaño del array tVerdad debe ser una potencia de dos");
      	
      	
      /*
      *en este bucle se recorre la cadena s. Si en una determinada posicion hay un cero se pone false
      *en la misma posicion del array tablaVerdad y si hay un 1 pone true
      */
         for(int i=0; i< s.length(); i++){
            switch(s.charAt(i)){
               case '0':
                  tVerdad[i]=false;
                  break;
            
               case '1':
                  tVerdad[i]=true;
                  break;
            	
               default:
                  throw new CircuitoException("solo se pueden pasar 0 y 1");
            }
         }
      
      //se asigna la variable local tablaVerdad al atributo del mismo nombre
         this.tablaVerdad=tVerdad;
      
      //se llama al metodo setNumEntradas(int numEntradas) para crear un array de entradas de la longitud 
      //determinada por el atributo tablaVerdad
         setNumEntradas((int)(Math.log(tablaVerdad.length)/Math.log(2)));
      }
   	 
   
   /**
   *Crea un nuevo circuito con una tabla de verdad y un número de entradas dado. El número de 
   *elementos de la tabla de verdad debe ser 2^N, con N el número de entradas
   *@param entradas  array de entradas
   *@param tablaVerdad  tabla de verdad del circuito 
   *@throws CircuitoException  si se le pasa un array entradas a null, si se le pasa un array tablaVerdad a null, si la longitud del array tablaVerdad no se puede expresar de la forma 2^N (donde N es el numero de entradas del circuito) y si la longitud que determina tablaVerdad no coincide con la longitud del array entradas  
   *@see #setEntradas(Circuito[] entradas)
   *
   */ 
       public CircuitoTablaVerdad(Circuito[] entradas, boolean[] tablaVerdad)throws CircuitoException{
      // llamamos al constructor de la superclase para inicializar los atributos etiqueta y entradas
         super();
      //inicializamos el atributo tablaVerdad
         this.tablaVerdad=new boolean[0];
      
      //Se comprueba que entradas no sea null
         if (entradas==null)
            throw new CircuitoException("Se ha pasado un array de entradas que apunta a null");
         
         //Se comprueba que tablaVerdad no sea null
         else if (tablaVerdad==null)
            throw new CircuitoException("Se ha pasado una tablaVerdad que apunta a null");
         
         //Se comprueba que la longitud de tablaVerdad es correcta
         else if(tablaVerdad.length<2)
            throw new CircuitoException("el tamaño de tablaVerdad no puede ser menor que dos");
         else if(Math.pow(2,(int)(Math.log(tablaVerdad.length)/Math.log(2)))!=tablaVerdad.length)
            throw new CircuitoException("el tamaño de tablaVerdad tiene que ser una potencia de dos");
         
         //Se comprueba que el numero de entradas que determina tablaVerdad coincida con el tamaño de entradas
         else if((int)(Math.log(tablaVerdad.length)/Math.log(2))!=entradas.length)
            throw new CircuitoException("no coincide el numero de entradas determinadas por la tablaVerdad con el tamaño del array entradas");
         
         else{
         //Se llama al metodo setEntradas para inicializar el atributo entradas ya que es privado y solo se puede modificar con este metodo
            setEntradas(entradas);
         
         //Se inicializa el atributo tablaVerdad
            this.tablaVerdad=tablaVerdad;
         }
      }
            
   
   
   /**
   *Compara este circuito con el que se le pasa por parámetro, para comprobar si son equivalentes. 
   *Se considera que dos circuitos son equivalentes si, para todas las posibles combinaciones de 
   *valores lógicos a la entrada, ambos devuelven el mismo valor lógico de salida. Esto es equivalente 
   *a comprobar que sus funciones lógicas coinciden.
   *@param circuito  circuito a comparar con el circuito que llama al metodo
   *@return true si son equivalentes false si no son equivalentes
   *@throws CircuitoException  si la lanza el metodo getFuncionCanonica() que es llamado en este metodo
   *@see #getFuncionCanonica()
   */
       public boolean equals(CircuitoFuncional circuito)throws CircuitoException{
      
      //variable b por defecto verdadera para almacenar el valor a devolver por el metodo
         boolean b=true;
      
         int contador=0;
      //cadena fcan1 => funcionCanonica del Circuito con el que llamamos al metodo
         String fcan1=getFuncionCanonica();
      //cadena fcan2 => funcionCanonica del CircuitoTablaVerdad que pasamos por parametro 
         String fcan2=circuito.getFuncionCanonica();
      
      //Si tienen la misma longitud entra en el bucle
         if(fcan1.length()==fcan2.length()){
         /*Se incrementa el contador mientras este sea menor que la longitud de ambas cadenas (que es la misma) 
          *y mientras encuentre caracteres en las mismas posiciones que sean iguales.
          *Es un y logico de modo que si la primera condicion es falsa ya no evalua la segunda porque las dos tienen
          *que ser verdaderas para pasar al while. De este modo se evita que se produzca una excepcion al intentar 
           *acceder a una posicion erronea de las cadenas
          */
            while(contador<fcan1.length()&&fcan1.charAt(contador)==fcan2.charAt(contador))
               contador++;
         
         //si el while no ha recorrido las dos cadenas enteras es porque no son iguales =>b es false
            if(contador!=fcan1.length())
               b=false;
         }
         
         //Si fcan1 y fcan2 no tienen la misma longitud => b es false (no son equivalentes)
         else 
            b=false;
      
         return b;
      
      }
   
   /**
   *Compara este circuito con el que se le pasa por parámetro, para comprobar si son equivalentes. 
   *Se considera que dos circuitos son equivalentes si, para todas las posibles combinaciones de 
   *valores lógicos a la entrada, ambos devuelven el mismo valor lógico de salida. Esto es equivalente 
   *a comprobar que sus funciones lógicas coinciden.
   *@param circuito  circuito a comparar con el circuito que llama al metodo
   *@return true si son equivalentes false si no son equivalentes
   *@throws CircuitoException  si la lanza el metodo getFuncionCanonica() que es llamado en este metodo
   *@see #getFuncionCanonica()
   */
       public boolean equals(CircuitoTablaVerdad circuito)throws CircuitoException{
      //variable b por defecto verdadera
         boolean b=true;
         int contador=0;
      //cadena fcan1 => funcionCanonica del Circuito con el que llamamos al metodo
         String fcan1=getFuncionCanonica();
      //cadena fcan2 => funcionCanonica del CircuitoTablaVerdad que pasamos por parametro 
         String fcan2=circuito.getFuncionCanonica();
      
      //Si tienen la misma longitud entra en el bucle
         if(fcan1.length()==fcan2.length()){
         /*Se incrementa el contador mientras este sea menor que la longitud de ambas cadenas (que es la misma) 
         *y mientras encuentre caracteres en las mismas posiciones que sean iguales.
         *Es un y logico de modo que si la primera condicion es falsa ya no evalua la segunda porque las dos tienen
         *que ser verdaderas para pasar al while. De este modo se evita que se produzca una excepcion al intentar 
         *acceder a una posicion erronea de las cadenas
         */
            while(contador<fcan1.length()&&fcan1.charAt(contador)==fcan2.charAt(contador))
               contador++;
         
         //si el while no ha recorrido las dos cadenas enteras es porque no son iguales =>b es false
            if(contador!=fcan1.length())
               b=false;
         }
         
         //Si fcan1 y fcan2 no tienen la misma longitud => b es false (no son equivalentes)
         else 
            b=false;
      
         return b;
      
      }
   
   /**
   *Devuelve la función canónica del circuito expresada como suma de productos. Ejemplo: 
   *Tabla: [1, 0, 1, 1, 0, 1, 0, 0] 
   *Función: "!x2*!x1*!x0 + !x2*x1*!x0 + !x2*x1*x0 + x2*!x1*x0"
   *@return funcion canónica del circuito
   *@throws CircuitoException si tablaVerdad es null y si la longitud de tablaVerdad es 0
   *
   */
       public java.lang.String getFuncionCanonica()throws CircuitoException{
         if (tablaVerdad==null)
            throw new CircuitoException("el array tablaVerdad apunta a null");
         if (tablaVerdad.length==0)
            throw new CircuitoException("el tamaño de tablaVerdad el 0");
      
      //variable numEntradas : numero de entradas del circuito que llama al metodo
         int numEntradas=getNumeroEntradas();
      //Se inicializan tres cadenas
         String fcan="";
         String fcan2="";
         String fcan3="";
      
      /*Se recorre su atributo tablaVerdad
      En el interior de este bucle se consigue una cadena que contiene la funcion canonica mas un signo "+" al final
      de esta que sera eliminado al salir del bucle*/
         for(int i=0;i<tablaVerdad.length;i++){
         
         //Se va a seguir un metodo en el que solo hay que fijarse en las posiciones de tablaVerdad que apunten a true
            if(tablaVerdad[i]==true){
             //Creamos una cadena s que contiene la representacion en codigo binario del entero que define la posicion del
            //array que apunta a true
               String s=Integer.toBinaryString(i);
            //se le concatenan tantos ceros al principio de esta cadena como diferencia haya entre su longitud y el 
            //numEntradas
               for(int j=numEntradas-s.length();j>0;j--)
                  s="0"+s;
            
            
            /*este bucle for concatena a fcan un grupo de determinadas combinaciones de entrada(negadas o afirmadas segun 
            corresponda). Es decir añade un sumando formado por tantos productos como entradas haya*/
               for(int h=0;h<s.length();h++){
               
               //si en la posicion h de array hay un uno concatena a fcan la entrada h-1 afirmada y un signo "*"
                  if (s.charAt(h)==('1')) 
                     fcan+="x"+(numEntradas-h-1)+"*";
                  
                  //si en la posicion h de array hay un cero concatena a fcan la entrada h-1 negada y un signo "*"
                  else 
                     fcan+="!x"+(numEntradas-h-1)+"*";
               
               }
            
            /*al finalizar un sumando de productos, gracias al método substring de la clase String se asigna a la cadena
            fcan2 la cadena fcan sin su ultima posicion, es decir se le quita el ultimo "*" y a cambio se le concatena
            un signo "+" */
               fcan2=fcan.substring(0,fcan.length()-1)+"+";
            //hacemos que fcan apunte a fcan2 para que vuelva a entrar en el bucle y se le siga concatenando
               fcan=fcan2;
            }
         }
      
      /*Si la longitud de la cadena fcan es mayor o igual que 2 asignamos a la cadena fcan3 la cadena fcan1 sin su ultima
      *posicion que contiene un signo "+" que ya sobra porque ya se ha acabado y no hay que seguir concatenando*/
         if(fcan.length()>=2)
            fcan3=fcan.substring(0,fcan2.length()-1);
         
         /*en caso contrario la longitud de fcan sera menor que 2 y eso quiere decir que ninguna posicion de su tablaVerdad
         *apunta a true por eso no ha entrado nunca en el if que evalua eso y no se ha concatenado nada a fcan
         *en este caso la funcion canonica es "0" */
         else 
            fcan3="0";
      
         return fcan3;
      }
   
   
   /**
   *Devuelve el resultado de evaluar el valor lógico de la salida de este circuito 
   *(true equivale a un 1 lógico, false equivale a un 0 lógico). Se invoca al método evaluar de todas 
   *sus entradas para poder calcular la salida. La salida se evalúa mediante una lectura de la posición 
   *adecuada de la tabla de verdad
   *@return valor logico de la salida del circuito
   *@throws CircuitoException - si el circuito no está adecuadamente conectado ('entradas' o alguno de sus elementos es null, la longitud de 'entradas' no es correcta, la tabala de verdad es null, o las invocaciones a evaluar() lanzan excepción)
   *@see #getEntradas()
   *@see #getNumeroEntradas()
   */
       public boolean evaluar() throws CircuitoException{
       
         //Se comprueba que el atributo entradas no apunte a null
         if (getEntradas()==null)
            throw new CircuitoException("El atributo entradas de este circuito apunta a null, no se puede evaluar la salida");
            
         //Se comprueba que el atributo tablaVerdad no apunte a null
         else if(tablaVerdad==null)
            throw new CircuitoException("El atributo tablaVerdad de este circuito apunta a null, no se puede evaluar la salida");
            
         //Se comprueba que la longitud de la tabla de verdad sea la correcta
         else if(Math.pow(2,getNumeroEntradas())!=tablaVerdad.length)
            throw new CircuitoException("La longitud de tablaVerdad de este circuito no es correcta, no se puede evaluar la salida");
         
         else{
         
           //variable que va a guardar el valor logico de las entradas en forma de 0 y 1
            String s="";
         
           //variable que se incrementa en el bucle while, y que contiene una determinada posicion de tablaVerdad
            int contador=0;
         
           //mientras sea true se ejecuta el bucle while
            boolean aux=true;
         
            //variable que guarda el atributo entradas del circuito
            Circuito [] imputs=getEntradas();
            
         	//variable que guarda el numero de entradas del circuito
            int numEntradas=getNumeroEntradas();
            
         	//Se recorre el array "imputs"            
            for(int i=0;i<numEntradas;i++){
            
            //Si alguna entrada del circuito esta desconectada (una posicion del array entradas apunta a null) se lanza
            //CircuitoException
               if(imputs[i]==null)
                  throw new CircuitoException("El pinEntrada "+i+" esta desconectado, no se puede evaluar la salida del circuito");
            }
            
            //Se recorre el array "imputs" desde el final al principio
            for(int j=numEntradas-1;j>=0;j--){
            
            //Se evalua la salida del circuito al que esta conectada la entrada "j"
            
               //Si el resultado es true, a la cadena "s" se le concatena un 1
               if(imputs[j].evaluar()==true)
                  s+=1;
                  
               //Si el resultado es false, a la cadena "s" se le concatena un 0
               else
                  s+=0;
            }
            
         	
         	//La mision de este bucle while es encontrar la posicion de la tablaVerdad que se corresponde con los 
         	//valores booleanos que recibe el circuito en sus entradas
            while(aux){
            
            //Cadena que contiene el valor del entero "contador" en binario
               String pos=Integer.toBinaryString(contador);
            
              //En este bucle while se le concatenan 0 al principio de la cadena "pos" mientras su longitud sea 
              //menor que el numero de entradas
               while(pos.length()<numEntradas)
                  pos=0+pos;
            
               //Si la cadena "pos" es igual a la cadena "s" => el contador es el que se busca, por lo que se
            	//hace apuntar aux a false para que el bucle no se vuelva a repetir
               if(pos.equals(s))
                  aux=false;
               
               //Si la cadena "pos"  NO es igual a la cadena "s" => el contador NO es el que se busca, por lo que 
               //se incrementa y se deja aux a true para que se vuelva a repetir el bucle con otro contador
               else contador++;
               
               
            }
         
           //Una vez que se ha salido de este bucle ya se tiene la posicion de la tablaVerdad que se busca
         
           //se devuelve dicha posicion
            return tablaVerdad[contador];
         }
            
      }
   }//Fin de la clase
