package DTO;

public class ClienteNoEncontradoException extends Exception{
	public ClienteNoEncontradoException(String dni) {
		super("Cliente con DNI "+ dni +" no encontrado");
	}

}
