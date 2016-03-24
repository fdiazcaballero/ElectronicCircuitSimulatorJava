   package circuitos;

   import java.awt.*;
   import java.awt.event.*;
   import java.io.*;
	
	/**
	*
	*Clase MaquinaCafeGUI.
	*
	*Simula la máquina de café a través de una sencilla interfaz gráfica de usuario (GUI), codificada con java.awt.
	*Cuando se pulse cada botón, se debería colorear la etiqueta correspondiente a las salidas de la máquina de café.
	*Los surtidores no activado aparecerán en el estado por defecto, es decir, fondo blanco y letras negras.
	*
	*El funcionamiento es el siguiente:
	*Primero se debe introducir las monedas suficientes(pinchando en los checkbox) hasta igualar o sobrepasar el 
	*el importe que aparece en el botón que se quiere pulsar. Si se pulsa este botón antes de tener suficiente dinero
	*la maquina de café no hará nada y pedirá más monedas. Entonces se debe seguir pulsando los checkbox y la maquina
	*irá sumando a lo que ya tenía introducido.
	*Una vez que ya se tenga suficiente dinero se pincha en el botón de la opción elegida y la maquina de café
	*realizará la preparación. Debe esperar un tiempo inversamente proporcional al número de surtidores abiertos 
	*para poder recoger su café. Mientras la máquina este preparando el cafe no se puede introducir dinero porque 
	*se perderá ya que la máquina no lo va a procesar. 
	*Finalmente recoja su cafe
	*
	*@author Fernando Diaz && Taoufik Aadia
    *@version 2.1
	*@see MaquinaCafe
    *@see Circuito
    *@see CircuitoException
	*/
    public class MaquinaCafeGUI extends java.awt.Frame{
   
    /**
    *Identificador del pulsador de café solo
    */
      public static final int CAFE_SOLO=0;
   
   /**
   *Identificador del pulsador de café con leche
   */
      public static final int CAFE_LECHE=1;
   
   /**
   *Identificador del pulsador de café con azúcar
   */
      public static final int CAFE_AZUCAR=2;
   
   /**
   *Identificador del pulsador de café con leche y con azúcar
   */
      public static final int CAFE_LECHE_AZUCAR=3;
   
   /**
   *Identificador del pulsador de leche sola
   */
      public static final int LECHE_SOLA=4;
   
   /**
   *Identificador del pulsador de leche con azúcar
   */
      public static final int LECHE_AZUCAR=5;
   
   /**
   *almacena el importe en pesetas que se ha introducido en la maquina de cafe
   */
      private int importe;
   
   /**
   *almacena el cambio a recibir despues de realizar la eleccion
   */
      private int vueltas;
   
   /**
   *indica si el importe introducido es suficiente para la opcion deseada
   */
      private boolean pagado;
   
   /**
   *etiqueta que muestra el estado del surtidor de cafe (encendido => surtidor abierto, en blanco => surtidor cerrado)
   */
      private Label	surtidorCafe;
   
   /**
   *etiqueta que muestra el estado del surtidor de leche (encendido => surtidor abierto, en blanco => surtidor cerrado)
   */
      private Label	surtidorLeche;
   
   /**
   *etiqueta que muestra el estado del surtidor de azucar (encendido => surtidor abierto, en blanco => surtidor cerrado)
   */
      private Label	surtidorAzucar;
   
   /**
   *etiqueta que muestra el importe introducido en la maquina de cafe
   */
      private Label importeIntroducido;
   
   /**
   *etiqueta que muestra el importe a devolver despues de escoger una opcion
   */
      private Label	suCambio;
   
   /**
   *etiqueta que muestra los pasos a seguir para utilizar la maquina
   */
      private Label pantalla;
   
    /**
    *Objeto de la clase Checkbox que sirve para simular la introduccion de una moneda de 25 ptas. en la maquina de cafe
    *(importante: se recomienda no echar monedas mientras la maquina esta preparando el cafe, porque la maquina
    *no responderá de este dinero)
    */
      private Checkbox checkBox1;
      
    /**
    *Objeto de la clase Checkbox que sirve para simular la introduccion de una moneda de 50 ptas. en la maquina de cafe
    *(importante: se recomienda no echar monedas mientras la maquina esta preparando el cafe, porque la maquina
    *no responderá de este dinero)
    */
      private Checkbox checkBox2;
		
	/**
    *Objeto de la clase Checkbox que sirve para simular la introduccion de una moneda de 100 ptas. en la maquina de cafe
    *(importante: se recomienda no echar monedas mientras la maquina esta preparando el cafe, porque la maquina
    *no responderá de este dinero)
    */
      private Checkbox checkBox3;
   	//Estos Checkbox hemos tenido que ponerlos como atributo porque es necesario utilizarlos en un metodo que
   	//los bloquea mientras se está simulando la preparacion del café.
   
    /**
    *objeto de la clase MaquinaCafe que se utiliza en esta aplicacion
    */
      private MaquinaCafe maquina;
   
   
    /**
    *Se inicializan todos los atributos 
    */
       public MaquinaCafeGUI(){
         try{
         	//valor por defecto
            importe=0;
            vueltas=0;
            pagado=false;
         
         	//Creamos las etiquetas con su correspondiente nombre y situacion
            importeIntroducido = new Label("Introducido:"+importe+" ptas.");
            suCambio = new Label("Su cambio:"+vueltas+" ptas.");
            pantalla=  new Label("Introduzca el importe",Label.CENTER);
         
            surtidorCafe=new Label("salida de CAFÉ",Label.CENTER);
            surtidorLeche=new Label("salida de LECHE",Label.CENTER);
            surtidorAzucar=new Label("salida de AZÚCAR",Label.CENTER);
         
         	//creamos un objeto de la clase MaquinaCafe que nos va a servir de base para el funcionamiento
         	//de la aplicación
            maquina=new MaquinaCafe();
         }
         
         	//Se recoge la posibles CircuitoException que se hayan lanzado, se imprime el mensaje adecuado
         	//y se finaliza la aplicación
             catch(CircuitoException e){
               System.out.println(e.getMessage());
               System.exit(0);
            }
      }
   
   
      
   
   /**
   *
   *Clase VentanaListener
   *
   * Permite responder a los eventos lanzados sobre una ventana
   * Concretamente responde a el evento de cierre
   */
       private class VentanaListener extends WindowAdapter {
      
       /**
       *metodo que controla el funcionamiento del boton de cierre de la aplicacion
       *@param e de la clase WindowEvent
       */
          public void windowClosing (WindowEvent e) {
            System.exit (0);
         }
      }
      
   
   /**
   *Clase CheckBoxListener
   *
   *Clase para manejar el evento en cualquiera de los tres checkbox's. Cuando se
   *produce el evento aumenta en la cantidad correspondiente el atributo importe
   *y lo muestra en la etiqueta correspondiente
   */
       private class CheckBoxListener implements ItemListener {
      
       /**
       *almacena la cantidad que hay que incrementar el atributo importe al pulsar un checkbox,
       *que es distinta para cada Checkbox
       */
         private int incremento;
      
       /**
       *método que crea un objeto de la clase inicializando el atributo al valor que se pasa por parámetro
       *@param incremento
       */
          public CheckBoxListener(int incremento){
            this.incremento=incremento;
         }
      
      	 /**
      	 *metodo que controla la funcionalidad de los checkbox incrementando el atributo importe con el valor
      	 *del atributo de esta clase
      	 *@param e de la clase ItemEvent
      	 */
          public void itemStateChanged (ItemEvent e) {
         
          	//Se incremente el atributo importe
            importe=importe+incremento;
         
         	//Se muestra en una etiqueta el importe que llevamos introducido
            importeIntroducido.setText ("Introducidos:"+importe+" ptas.");
         
         }
      }

   
   /**
   *
   * Clase Option
   *
   *Permite responder al evento lanzado sobre cualquier boton de opcion.
   *Hace que se enciendan las etiquetas de los surtidores correspondientes durante un tiempo inversamente
   *proporcional al numero de surtidores abiertos y despues vuelvan a su estado por defecto
   *
   *
   *@see MaquinaCafe#setOpcion(int opcion)
   *@see #monedas()
   *@see #bloquear(boolean bloqueo)
   *@see #colores()
   *@see #apagar()
   *
   */
       private class Option  extends MouseAdapter {
      
       /**
       *atributo que indica la opcion correspondiente de MaquinaCafe que hay que utilizar, cada boton tiene un
       *numero de opcion por lo que ésta dependera del boton que se pulse
       */
         private int option;
      
       /*
       *Con un unica clase nos basta para gestionar los eventos producidos en todos los botones de opciones
       *ya que lo que hay que hacer (y por lo tanto el codigo) es exactamente igual, lo unico que varia es
       *el entero "opcion" que hay que pasarle al metodo setOpcion(int opcion) de la clase MaquinaCafe que
       *es el que genera la combinacion de surtidores abiertos. Este problema se soluciona con un atributo
       *asi al añadirle a cada boton el "mouseAdapter" cada uno creara un objeto de esta clase pasandole
       *el entero conveniente
       */
      
       /**
       *constructor que asigna el valor al atributo de esta clase
       *@param option de tipo entero
       */
          public Option(int option){
            this.option=option;
         }
      
      	/**
      	*metodo que controla los eventos producidos por el click del raton
      	*@param e de la clase MouseEvent
      	*/
          public void mouseClicked(MouseEvent e){
         
            try{
            
            	//primero establece la opcion en el objeto de la clase MaquinaCafe a traves de su metodo setOpcion(int opcion)
               maquina.setOpcion(option);
               //bloqueamos los CheckBox, para no permititr así al usuario introducir mas monedas mientras se ejecutan los demás métodos
               bloquear(false);
            	//con este metodo se decide si poner a true el atributo pagado y establecer el cambio a devolver
            	//en caso de que se halla introducido suficiente dinero o pedir mas dinero en caso contrario
               monedas();
               //si el atributo pagado apunta a true cambia de color los elementos necesarios
               colores();
               //se encarga de volver a poner los surtidores en blanco y los atributos como vueltas e importe al estado inicial
               apagar();
               //desbloqueamos los checkBox, permitiendo la introduccion de monedas
               bloquear(true);
            }
            
            	//se recoge las CircuitoException que puedan lanzar los metodos utilizados, en condiciones normales no seran lanzadas
                catch(CircuitoException e1){
               
                //Se imprime el mensage y se cierra la aplicacion
                  System.out.println(e1.getMessage());
                  System.exit(0);
               
               }
         }
      }
   
   	/**
   	*
   	*este metodo es el que permite que los surtidores correspondientes a cada eleccion se abran,
   	*lo hace poniendo el atributo pagado a true si el importe introducido es mayor o igual que
   	*el coste de la opcion deseada.
   	*
   	*Los precios establecidos son: 0.50 Euros si solo se va abrir un surtidor, 0.70 Euros para 2
   	*surtidores, y 1.20 para los tres surtidores.
   	*
   	*Tambien calcula el valor del cambio que usted recibira, si es que ha introducido mas dinero
   	*del necesario.
   	*
   	*En el caso de que no se halla introducido el dinero suficiente, hace que aparezca un mensaje
   	*pidiendo mas dinero
   	*
   	*@throws CircuitoException
   	*
   	*@see MaquinaCafe#getSurtidores()
   	*
   	*/
       public void monedas()throws CircuitoException{
      
       	//este contador llevara la cuenta del numero de surtidores que hay encendidos
         int cont=0;
      
         //variable local que apunta al array que contiene el estado de los surtidores
         boolean [] salidas=maquina.getSurtidores();
      
      	//bucle que recorre el array de surtidores
         for(int i=0; i<salidas.length;i++)
         
         	//Si la salida de un surtidor (cada surtidor es la salida de un circuito)
         	//es true  incrementamos el contador
            if(salidas[i])
               cont++;
      
      	/*Se analizan las distintas posibilidades de numero de surtidores abiertos
      	y para cada una se comprueba si el importe introducido es suficiente de acuerdo
      	con los precios establecidos, en este caso ponemos el atributo pagado a true y se
      	calculan las vueltas, en caso contrario se pide mas dinero*/
         switch(cont){
         
            case (1):
            
               if(importe>=125){
                  pagado=true;
                  vueltas=importe-125;
               }
               
               else
                  pantalla.setText("introduzca más monedas");
            
               break;
         
            case (2):
            
               if(importe>=150){
                  pagado=true;
                  vueltas=importe-150;
               }
               
               else
                  pantalla.setText("introduzca más monedas");
            
               break;
         
            case (3):
            
               if(importe>=200){
                  pagado=true;
                  vueltas=importe-200;
               }
               
               else
                  pantalla.setText("introduzca más monedas");
            
               break;
         
            default:
            //no ocurrira nunca, a no ser que se añadieran mas surtidores
               throw new CircuitoException("Funcionamiento inadecuado de la maquina de cafe");
         }
         suCambio.setText("Devolución:"+vueltas+" ptas.");
      
      }
   
   
   
   
   /**
   *
   *Este metodo es el encargado de colorear las etiquetas de los surtidores con el color
   *correspondiente si hay suficiente dinero dentro de la maquina.
   *
   *Si un surtidor no esta abierto la etiqueta aparecera en el color por defecto (fondo
   *blanco y letras negras), cada surtidor tendra un segundo color para en el caso de que
   *se encuentre abierto poner el fondo de su etiqueta a ese color y las letras en blanco
   *Estos segundos colores son: para CAFE==>rojo, LECHE==>verde, AZUCAR==>azul
   *
   *@throws CircuitoException
   *@see #pagado
   */
       public void colores()throws CircuitoException{
      
      	//Se controla que se halla introducido el importe necesario
         if(pagado){
         
         	//array que almacena los segundos colores de cada etiqueta
            Color[] fondos=new Color[]{Color.red,Color.yellow,Color.blue};
            Color[] letras=new Color[]{Color.white,Color.blue,Color.white};
         
         	//guardamos en un array las tres etiquetas para trabajar mas comodo
            Label[] label= new Label[]{surtidorCafe,surtidorLeche,surtidorAzucar};
         
         	//guardamos en otro array el que contiene el estado de los surtidores
            boolean [] salidas=maquina.getSurtidores();
         
         	//Se recorre el array de las etiquetas para poner el color adecuado
            for(int i=0; i<salidas.length;i++){
            
            	//Si un surtidor esta abierto se le pone de fondo el color de la misma posicion del array "fondos"
            	//y de color de letras el blanco
               if(salidas[i]){
                  label[i].setBackground(fondos[i]);
                  label[i].setForeground(letras[i]);
               }
               
               //Si un surtidor esta cerrado se le pone de fondo el blanco y de letras el negro
               else {
                  label[i].setBackground(Color.white);
                  label[i].setForeground(Color.black);
               }
            }
         
         }
      
      }
   
   
   
   /**
   *
   *Este metodo es el encargado de (si se ha introducido suficiente dinero, como siempre)
   *de varias cosas:
   *Primero provoca una parada en la ejecucion para que las surtidores permanezcan encendidos
   *mientras se prepara el producto. Esta parada es inversamente proporcional al numero de surtidores
   *que se abran.
   *Segundo pone la maquina de cafe en el estado inicial y vuelve a utilizar el metodo colores() para
   *poner en blanco todas las etiquetas
   *Tercero vuelve a poner a cero el atributo importe y a false el atributo pagado
   *
   *@throws CircuitoException
   *
   *@see #pagado
   *@see #colores()
   *@see MaquinaCafe#reset()
   *
   */
       public void apagar()throws CircuitoException{
      
      
         try{
         
         	//Si se ha introducido suficiente dinero para opcion elegida
            if(pagado){
               int cont=0;
            
            	//guardamos en otro array el que contiene el estado de los surtidores
               boolean [] salidas=maquina.getSurtidores();
            
            	//bucle que recorre el array de surtidores
               for(int i=0; i<salidas.length;i++)
               
               	//Si la salida de un surtidor (cada surtidor es la salida de un circuito)
               	//es true  incrementamos el contador
                  if(salidas[i])
                     cont++;
            
               pantalla.setText("Preparando");
            
            	//Se produce la parada en el programa
               switch (cont){
               
                  case 1:
                  
                     Thread.sleep(4000);
                  
                     break;
               
                  case 2:
                  
                     Thread.sleep(2000);
                  
                     break;
               
                  case 3:
                  
                     Thread.sleep(1000);
                  
                     break;
               }
            
            	//Se pone la maquina en el estado inicial (en reposo)
               maquina.reset();
            
            	//Se vuelve a utilizar este metodo para poner las etiquetas al color por defecto
               colores();
            
            	//puesto que ya se a elegido se pone a cero el atributo "importe" y a false "pagado"
               importe=0;
               importeIntroducido.setText("Introducido:"+importe+"ptas.");
               pagado=false;
            
              //Se muestra un mensaje de "Listo"
               pantalla.setText("Listo");
            
               Thread.sleep(1500);
            
            
              //Se vuelve al estado inicial
               pantalla.setText("Introduzca el importe");
            
            }
         }
         
         //Solo recogemos la InterruptedException y la CircuitoException se lanza
             catch(InterruptedException e){
            
             //imprimimos un mensaje de error y salimos de la aplicacion
               System.out.println("Error");
               System.exit(0);
            }
      
      }
   
   
   	/**
   	*Método que bloquea los componentes de la clase cuando se está simulando el funcionamiento de la máquina.
   	*Si se pasa un boolean false, se bloquean los componentes, y si el parámetro es true,se desbloquean.
   	*
   	*@param bloqueo parámetro de tipo boolean.
   	*/
       public void bloquear(boolean bloqueo){
      
         //bloqueamos o desbloqueamos los checkBox
         checkBox1.setEnabled(bloqueo);
         checkBox2.setEnabled(bloqueo);
         checkBox3.setEnabled(bloqueo);
      
      }
   
   	/**
   	*Clase MiImagen.
   	*
   	*Introduce una imagen en la maquina de cafe
   	*
   	*/
       private class MiImagen extends Canvas {
      	
			/**
      	*atributo de la clase interna, objeto de la clase Image.
      	*/
         private Image img;
      
      	/**
      	*Método que crea un objeto de esta clase.
      	*@param fichero nombre del fichero que contiene la imagen
      	*/
          public MiImagen(String fichero) {
         	//se asigna al atributo de la clase la imagen del fichero y se determina su tamaño
            img = Toolkit.getDefaultToolkit().getImage(fichero);
            setSize(100,100);
         }
      
      	/**
      	*Método que se encarga de hacer que la imagen aperezca en nuestra aplicación
      	*@param graphics Objeto de la clase Graphics
      	*/
          public void paint(Graphics graphics) {
            graphics.drawImage(img, 0, 0, this);
         }
      
      	/**
      	*Método que se encarga de hacer que la imagen aparezca en nuestra aplicación
      	*
      	*@param img objeto de la clase Image
      	*@param infoflangs De tipo entero
      	*@param x De tipo entero
      	*@param y De tipo entero
      	*@param width De tipo entero
      	*@param height De tipo entero
      	*
      	*@return siempre es un boolean true
      	*/
          public boolean imageUpdate(Image img,int infoflags,int x,int y, int width,int height) {
            repaint();
            return true;
         }
      }
   
   
   	/**
   	*
   	*Este metodo sirve para añadir a cada panel principal todos los subpaneles y a su vez
   	*añadir a estos todos los componentes(botones, etiquetas...) o los fondos.
   	*Tambien se añaden a los botones y checkbox los "listener" correspondientes para
   	*controlar los eventos y a las etiquetas el color de las letras y del fondo.
   	*
   	*@throws CircuitoException Si las imagenes que se van a añadir no se encuentran en el directorio src/imagenes
   	*/
       public void pintarVentana()throws CircuitoException{
      
      	//se pone el titulo
         setTitle ("Maquina De Café --- HOMENAJE A LA PESETA (...asi era el mundo antes del Euro)");
      
      	//añadimos "windowListener" para que se pueda cerrar
         addWindowListener(new VentanaListener());
      
      	//creamos los botones de cada opcion con su correspondiente titulo
         Button cafeSolo = new Button("Café solo                                         125 ptas.");
         Button cafeConLeche= new Button ("Café con Leche                                150 ptas.");
         Button cafeConAzucar=new Button("Café con Azucar                                150 ptas.");
         Button cafeConLecheConAzucar=new Button("Café con Leche con Azucar             200 ptas.");
         Button leche=new Button("Leche Sola                                       125 ptas.");
         Button lecheConAzucar= new Button("Leche con Azucar                            150 ptas.");
         
      	//le ponemos a cada boton un todo de color distinto de fondo
         cafeSolo.setBackground(new Color(100,100,100));
         cafeConLeche.setBackground(new Color(120,120,120));
         cafeConAzucar.setBackground(new Color(140,140,140));
         cafeConLecheConAzucar.setBackground(new Color(160,160,160));
         leche.setBackground(new Color(180,180,180));
         lecheConAzucar.setBackground(new Color(200,200,200));
      
      
      	//añadimos a cada boton su "escuchador" con un objeto de la clase Option con el parametro adecuado
         cafeSolo.addMouseListener(new Option(CAFE_SOLO));
         cafeConLeche.addMouseListener(new Option(CAFE_LECHE));
         cafeConAzucar.addMouseListener(new Option(CAFE_AZUCAR));
         cafeConLecheConAzucar.addMouseListener(new Option(CAFE_LECHE_AZUCAR));
         leche.addMouseListener(new Option(LECHE_SOLA));
         lecheConAzucar.addMouseListener(new Option(LECHE_AZUCAR));
      
       	//Contiene los Checkbox's que sirven para introducir el dinero
         CheckboxGroup grupo = new CheckboxGroup ();
      
      	//creamos los Checkbox
         checkBox1 = new Checkbox("25 ptas.",grupo,false);
         checkBox2 = new Checkbox("50 ptas.",grupo,false);
         checkBox3 = new Checkbox("100 ptas.",grupo,false);
      
         //añadimos los ItemListener con un objeto de la clase CheckBoxListener pasandole el parametro adecuado
         checkBox1.addItemListener(new CheckBoxListener(25));
         checkBox2.addItemListener(new CheckBoxListener(50));
         checkBox3.addItemListener(new CheckBoxListener(100));
      
         //Creamos los paneles necesarios
         Panel panelDibujo=new Panel();
         Panel izquierdo=new Panel();
         Panel derecho=new Panel();
      
      	//Establecemos los fondos
         panelDibujo.setBackground(Color.gray);
         izquierdo.setBackground(Color.black);
         derecho.setBackground(Color.black);
      
         //ponemos el fondo, el color de letra, y el tipo
         pantalla.setBackground(Color.black);
         pantalla.setForeground(Color.green);
         pantalla.setFont(new Font("SansSerif", Font.BOLD, 20));
      
         surtidorCafe.setBackground(Color.white);
         surtidorCafe.setFont(new Font("Monospaced", Font.BOLD, 20));
      
         surtidorLeche.setBackground(Color.white);
         surtidorLeche.setFont(new Font("Serif",Font.ITALIC,22));
      
         surtidorAzucar.setBackground(Color.white);
         surtidorAzucar.setFont(new Font("Serif",Font.ITALIC,20));
      
         importeIntroducido.setBackground(Color.black);
         importeIntroducido.setForeground(Color.green);
         importeIntroducido.setFont(new Font("SansSerif", Font.BOLD, 15));
      
         suCambio.setBackground(Color.black);
         suCambio.setForeground(Color.green);
         suCambio.setFont(new Font("SansSerif", Font.BOLD, 15));
      
         //añadimos los Layout necesarios
         setLayout(new GridLayout(0,2));
      
      	//añadimos los paneles al panel principal
         add(izquierdo);
         add(derecho);
      
         izquierdo.setLayout(new GridLayout(6,0));
         derecho.setLayout(new GridLayout(3,0));
         panelDibujo.setLayout(new GridLayout(1,2));
      
         //Se añaden los botones al panel izquierdo
         izquierdo.add(cafeSolo);
         izquierdo.add(cafeConLeche);
         izquierdo.add(cafeConAzucar);
         izquierdo.add(cafeConLecheConAzucar);
         izquierdo.add(leche);
         izquierdo.add(lecheConAzucar);
      
      	//Se añaden las etiquetas
         derecho.add(surtidorCafe);
         derecho.add(surtidorLeche);
         derecho.add(surtidorAzucar);
         
      
         Panel importes=new Panel();
         importes.setLayout(new GridLayout(5,1));
      
      
         importes.add(checkBox1);
         importes.add(checkBox2);
         importes.add(checkBox3);
         importes.add(importeIntroducido);
         importes.add(suCambio);
      
      
         Panel foto=new Panel();
         foto.setLayout(new GridLayout(0,1));
      
        //comprobamos que las dos fotos que se quieren introducir existen en el directorio imagenes de src
         File file1=new File("imagenes/imagen.jpg");
         File file2=new File("imagenes/cafetera.jpg");
        
        //si no existen en el directorio src se lanza una CircuitoException
         if(!file1.exists()){
            throw new CircuitoException("la foto imagen.jpg no existe en src/imagenes, debe ponerla en src para que funcione el programa");
         }
			
			if(!file2.exists()){
            throw new CircuitoException("la foto cafetera.jpg no existe en src/imagenes, debe ponerla en src para que funcione el programa");
         }

         
         //imagen que se visualiza en la maquina de cafe
         MiImagen image=new MiImagen("imagenes/imagen.jpg");
         MiImagen image1=new MiImagen("imagenes/cafetera.jpg");
         
         image.setVisible(true);
         image1.setVisible(true);
      	
         foto.add(image);
         foto.add(image1);
      
         panelDibujo.add(importes);
         panelDibujo.add(foto);
      
      
      
         add(panelDibujo);
      
         add(pantalla);
      
      }
   
   
   	/**
   	*
   	*Método que hace ejecutable toda la aplicación, crea un objeto de la clase en la que está definido y
   	*llama a los métodos que permiten crear todos los componentes y lanzar la interfaz gráfica.
   	*
   	*@param args  argumentos de entrada. En este caso no es necesario definirlos.
   	*
   	*@see #pintarVentana()
   	*/
       public static void main (String []args) {
      
         try{
         	
				//se crea un objeto de la clase MaquinaCafeGUI
            MaquinaCafeGUI miVentana = new MaquinaCafeGUI();
         
         	//se incluyen y muestran todos los componentes del objeto creado
            miVentana.pintarVentana();
         
            //se determina el tamaño inicial de la aplicación
            miVentana.setSize(650,650);
         
          	//se hace visible
            miVentana.setVisible(true);
         
         	//finalmente se muestra todo el conjunto de la interfaz y aplicación correspondiente
            miVentana.show();
         }
         
         //Se coge la posible CircuitoException que pueda lanzar el metodo pintarVentana() si la fotos
         //que se quieren introducir no existen (es la unica CircuitoException que puede llegar aqui)
             catch(CircuitoException e1){
               System.out.println("Error: "+e1.getMessage());
               System.exit(0);
            }
         
         	//si se ha detectado algun tipo de error se imprime el mensaje adecuado y se finaliza la aplicación
             catch(Exception e1){
               System.out.println("Error:"+e1.getMessage());
               System.exit(0);
            }
      
      }
   }



