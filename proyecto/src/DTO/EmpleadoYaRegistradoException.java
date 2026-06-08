package DTO;

public class EmpleadoYaRegistradoException extends Exception{
	public EmpleadoYaRegistradoException(int id) {
		super("Empleado con id "+ id +" ya registrado");
	}

}
