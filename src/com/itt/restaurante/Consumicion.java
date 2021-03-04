package com.itt.restaurante;

import com.itt.excepciones.CantidadIncorrectaException;

public class Consumicion {
	//Atributos
	 private String producto;
     private int cantidad;
     private double precio;
     
     //Constructores
     public Consumicion(String producto, int cantidad, double precio) {
           this.producto = producto;
           if(cantidad<= 0)
        	   throw new CantidadIncorrectaException(cantidad);
           else
               this.cantidad = cantidad;
           
           this.precio = precio;

     }
     //Getters - Las propiedades de sólo lectura.
     public String getProducto() {
           return producto;
     }

     public int getCantidad() {
           return cantidad;
     }

     public double getPrecio() {
           return precio;
      }
     
     //Métodos
      @Override
      public String toString() {
    	  return "Consumición [producto=" + producto +
            ", cantidad=" + cantidad
            + ", precio=" + precio + "]";
      }
      
      
}
