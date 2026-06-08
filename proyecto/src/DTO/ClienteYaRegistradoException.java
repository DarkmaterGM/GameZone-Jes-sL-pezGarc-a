package DTO;

public class ClienteYaRegistradoException extends Exception{
	public ClienteYaRegistradoException(String dni) {
		super("Cliente con dni "+ dni +" ya registrado");
	}
}
