package DTO;

public class CategoriaNoEncontradoException extends Exception{
	public CategoriaNoEncontradoException(String id) {
		super("Categoría con id "+ id +" no encontrado");
	}
}
