//declaramos el package en el que se encontrara toda la practica
   package circuitos;
//importamos java.io.* para poder utilizar BufferedReader y FileReader
   import java.io.*;

/**
*La Clase PruebaEntradaSalida
*
*contiene un método main que abre el fichero "entrada.in", que es un fichero descriptor
*que contiene la descripción de tres circuitos de la clase CircuitoTablaVerdad, con etiquetas 
*"TV0","TV1" y "TV2",cada uno de ellos con dos entradas. Los valores de la tabla de verdad 
*pueden ser cualquiera
*@author Fernando Diaz && Taoufik Aadia
*@version 2.1
*@see Circuito
*@see CircuitoTablaVerdad
*@see CircuitoException
*/
    public class PruebaEntradaSalida{
   
   /** 
   *método main
   *@see #toString()
   */
       public static void main(String args[]){
      //declaramos una cadena
         String linea;
      //la cadena primlinea nos va a servir para comparar con la que va a leer y saber si hay que llamar al constructor
         String primlinea="CircuitoTablaVerdad";
      //declaramos un array de circuitos que va a contener los tres circuitos que se pasan en el fichero
        //en el mismo orden en que se leen
         Circuito [] circuit=new Circuito[3];
      
        //bloque try contiene las sentencias susceptibles de lanzar una excepcion
         try{
         
           //comprobamos que existe el fichero entrada.in, si no existe se lanza CircuitoException
            File file=new File("entrada.in");
            if(!file.exists())
               throw new CircuitoException("El fichero entrada.in no existe");
         
         //se crea un BufferedReader a partir de un FileReader que a su vez se crea a partir del fichero "entrada.in"
            BufferedReader in=new BufferedReader(new FileReader("entrada.in"));
            
         //este bucle for se va a encargar de asignar cada circuito que se lee a una posicion del array circuit  
            for(int p=0;p<circuit.length;p++){
            //se lee la primera linea del fichero
               linea=in.readLine();
            //se repite este bucle while mientras que la longitud de la cadena linea sin espacios en blanco 
            //al principio ni al final sea menor que 1 por si hay lineas en blanco
               while(linea.trim().length()<1)
               //Lee la siguiente linea
                  linea=in.readLine();
            
            	//Si la cadena linea sin espacios en blanco al principio ni al final es igual a la cadena primLinea
            	//le asigna a la posicion p de circuit un objeto de la clase CircuitoTablaVerdad llamando a un 
            	//constructor de esta clase
               if((linea.trim().compareTo(primlinea))==0)
                  circuit[p]=new CircuitoTablaVerdad(in);
            }
         
         //se cierra el flujo
            in.close();
         //se crea un array de objetos de la clase Circuito de longitud 2 que va a ser el atributo entradas
            //para el circuito con etiqueta "TV0"
            Circuito[] circuit2=new Circuito[2];
         
         //en este bucle for se recorre el array circuito y si la etiqueta de una posicion de circuit coincide con
         //"TV1" ó "TV2" le asigna a la posicion 0 o 1 de circuit2 dicho objeto
            for(int m=0;m<circuit.length;m++){
               if((circuit[m].getEtiqueta().compareTo("TV1"))==0)
                  circuit2[0]=circuit[m];
               if((circuit[m].getEtiqueta().compareTo("TV2"))==0)
                  circuit2[1]=circuit[m];
            }
            
         //este bucle recorre el array circuit y cuando encuentra un circuito con etiqueta "TV0" se le asigna a su
         //atributo entradas el array circuit2 a traves del metodo setEntradas de la clase Circuito
            for(int cont=0;cont<circuit.length;cont++){
            // si la etiqueta del circuito de la posicion cont coincide con "TV0" h sera igual a 0
               int h=(circuit[cont].getEtiqueta().compareTo("TV0"));
               if(h==0)
                  circuit[cont].setEntradas(circuit2);
            }
         
            /*
         *Este bucle for recorre el array circuit para mostrar por pantalla las cadenas que describen sus circuitos
         *usando el metodo toString() de la clase circuito, en el mismo orden en el que se leyeron
            */
            for(int u=0;u<circuit.length;u++){
            //cadena s => descripcion del circuito de la posicion u de circuit
               String s=circuit[u].toString();
            //Se muestra por pantalla la cadena s
               System.out.println(s);
            //Se deja una linea en blanco
               System.out.println();
            }
            
         
         }
         
         /*
         *atrapa una posible excepcion causada si no se encuentra el fichero con el que se pretende abrir el
         *FileReader
         */
             catch(IOException e){
               System.out.println("Error: Imposible abrir el archivo para leer, puede que no exista");
            }
         
         /*
         *atrapa las posibles CircuitoException que pueden haber lanzado hacia arriba los metodos de la clase
         *Circuito o CircuitoTablaVerdad que se utilizan en el main o que son llamados por los metodos que se 
         *utilizan
         */
             catch(CircuitoException e2){
               System.out.println("Error: "+e2.getMessage());
            }
         
         /*
         *atrapa cualquier excepcion distinta de las dos anteriores que se pueda producir en el desarrollo del
         *programa para que al ejecutarlo no lance las excepciones por pantalla. Se pone la ultima porque es 
         *la mas general
         */
             catch(java.lang.Exception e3){
               System.out.println("Error: Se ha producido un fallo en el programa");
            }
      }
   
   }//Fin de la clase


