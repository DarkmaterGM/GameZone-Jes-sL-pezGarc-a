package DTO;

public class VentaYaRegistradaException extends Exception{
	public VentaYaRegistradaException(int id) {
		super("Venta con id "+ id +" ya registrada");
	}

}
