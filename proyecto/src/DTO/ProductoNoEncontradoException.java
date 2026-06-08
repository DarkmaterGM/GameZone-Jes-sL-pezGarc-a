package DTO;

public class ProductoNoEncontradoException extends Exception{
	public ProductoNoEncontradoException(String id) {
		super("Producto con id "+ id +" no encontrado");
	}
}
