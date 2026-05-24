package DTO;

import java.sql.Date;

public class Producto {

	private String id;
	private String nombre;
	private String plataforma;
	private int id_categoria;
	private double precio;
	private int stock;
	private Date fecha_lanzamiento;
	//Constructor
	public Producto(String id, String nombre, String plataforma, int id_categoria, double precio, int stock,
			Date fecha_lanzamiento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.plataforma = plataforma;
		this.id_categoria = id_categoria;
		this.precio = precio;
		this.stock = stock;
		this.fecha_lanzamiento = fecha_lanzamiento;
	}
	
	//Getters y Setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Date getFecha_lanzamiento() {
		return fecha_lanzamiento;
	}
	public void setFecha_lanzamiento(Date fecha_lanzamiento) {
		this.fecha_lanzamiento = fecha_lanzamiento;
	}

	//toString
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", plataforma=" + plataforma + ", id_categoria="
				+ id_categoria + ", precio=" + precio + ", stock=" + stock + ", fecha_lanzamiento=" + fecha_lanzamiento
				+ "]";
	}
	
	
}
