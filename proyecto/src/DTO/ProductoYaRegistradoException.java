package DTO;

public class ProductoYaRegistradoException extends Exception{
	public ProductoYaRegistradoException(String id) {
		super("Producto con id "+ id +" ya registrado");
	}
}
