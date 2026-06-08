package DTO;

public class VentaNoEncontradoException extends Exception{
	public VentaNoEncontradoException(int id) {
		super("Venta con id "+ id +" no encontrado");
	}
}
