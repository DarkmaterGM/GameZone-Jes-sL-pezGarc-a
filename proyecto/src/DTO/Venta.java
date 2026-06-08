package DTO;

import java.sql.Date;

public class Venta {

	private int id;
	private Date fecha_compra;
	private String id_cliente;
	private int id_empleado;
	private double importe;
	
	//Constructor
	public Venta(int id, Date fecha_compra, String id_cliente, int id_empleado, double importe) {
		super();
		this.id = id;
		this.fecha_compra = fecha_compra;
		this.id_cliente = id_cliente;
		this.id_empleado = id_empleado;
		this.importe = importe;
	}

	//Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha_compra() {
		return fecha_compra;
	}

	public void setFecha_compra(Date fecha_compra) {
		this.fecha_compra = fecha_compra;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public int getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(int id_empleado) {
		this.id_empleado = id_empleado;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	//toString
	@Override
	public String toString() {
		return "Venta [id=" + id + ", fecha_compra=" + fecha_compra + ", id_cliente=" + id_cliente + ", id_empleado="
				+ id_empleado + ", importe=" + importe + "]";
	}
	
	
	
	
}
