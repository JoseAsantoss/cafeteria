package com.itt.excepciones;

public class CantidadIncorrectaException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private int cantidad;
	
	public CantidadIncorrectaException(int cantidad) {
		super("La cantidad indicada no es correcta");
		this.cantidad = cantidad;
	}
	
	@Override
	public String toString() {
		return "La cantidad " + this.cantidad + " introducida no es correcta";
	}
}
