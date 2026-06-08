package DTO;

import java.sql.Date;

public class Empleado {

	private int id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private Date fecha_contratacion;
	private String cargo;
	private int anios_experiencia;
	//Constructor
	public Empleado(int id, String nombre, String apellido1, String apellido2, Date fecha_contratacion, String cargo,
			int anios_experiencia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fecha_contratacion = fecha_contratacion;
		this.cargo = cargo;
		this.anios_experiencia = anios_experiencia;
	}
	//Getters y Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public Date getFecha_contratacion() {
		return fecha_contratacion;
	}
	public void setFecha_contratacion(Date fecha_contratacion) {
		this.fecha_contratacion = fecha_contratacion;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public int getAnios_experiencia() {
		return anios_experiencia;
	}
	public void setAnios_experiencia(int anios_experiencia) {
		this.anios_experiencia = anios_experiencia;
	}
	
	//toString
	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", fecha_contratacion=" + fecha_contratacion + ", cargo=" + cargo + ", anios_experiencia="
				+ anios_experiencia + "]";
	}
	
	
}
