package DTO;

public class EmpleadoNoEncontradoException extends Exception{
	public EmpleadoNoEncontradoException(int id) {
		super("Empleado con id "+ id +" no encontrado");
	}
}
