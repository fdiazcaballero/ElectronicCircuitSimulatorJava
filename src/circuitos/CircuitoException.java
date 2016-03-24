//declaramos el package en el que se encontrara toda la practica
package circuitos;

/**
*Clase CircuitoException
*Excepción utilizada por todos los métodos de la aplicación para gestionar los casos de error.
*@author Fernando Diaz && Taoufik Aadia
*@version 2.1
*/
public class CircuitoException extends Exception{
	//CONSTRUCTORES

	/**
	*Constructor sin parametros.
	*/
	public CircuitoException(){
		//se llama al constructor de su superclase Exception
		super();
		}

		/**
		*Constructor con parametros
		*@param mensaje  mensaje que se muestra por pantalla en caso de error
		*/
    public CircuitoException(java.lang.String mensaje){
		//Se llama al constructor con parametros se la superclase Exception
		super(mensaje);		
		}
}//Fin de la clase

			
