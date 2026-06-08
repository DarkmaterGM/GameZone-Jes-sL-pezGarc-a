package DTO;

public class CategoriaYaRegistradoException extends Exception{
	public CategoriaYaRegistradoException(String id) {
		super("Categoría con id "+ id +" ya registrada");
	}

}
