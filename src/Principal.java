import java.util.ArrayList;
import java.util.Scanner;

import com.itt.excepciones.CantidadIncorrectaException;
import com.itt.restaurante.Consumicion;

public class Principal {
	
	private static Scanner sc;
	public static ArrayList<Consumicion> consu = new ArrayList<Consumicion>();
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		String con;
		int cant = 0;
		double pvp = 0.0;
		boolean salir = false;
		String op = "";
		int control = 0;
		boolean oc = false;
		
		do {
			//Le pedido que consumición quiere
			System.out.println("¿Qué desas tomar?");
			con = pedirConsumicion();
			if(!con.equals("")) {
				//Pedimos la cantidad
				System.out.println("¿Qué cantidad?");
				cant = pedirCantidad();
				if (cant>0) {
					//Buscamos el precio de ese producto
					pvp = precio(con);
					//Si el precio es menor o igual a 0, el producto está agotado
					// y se le pide un nuevo producto.
					do {
						if(pvp<=0) {
							System.out.println("El producto actualmente está agotado");
							if(control == 2) {
								oc = true;
							}else {
								System.out.println("Dispones de " + (3-(control+1)) + " intentos");
							}
							System.out.println("Elija otro producto");
							con = pedirConsumicion();
							pvp = precio(con);
							if(!con.equals("") && pvp>0) {
								oc = true;
							}
							System.out.println("Indica de nuevo la cantidad");
							cant = pedirCantidad();
							if(cant<=0) {
								oc = true;
							}
						}else {
							oc = true;
						}
						control++;
					}while(!oc);
					System.out.println("Precio: " + pvp);
					if(pvp>0) {
						consu.add(new Consumicion(con, cant, pvp));
						
						System.out.println("Para seguir pidiendo teclee SI: ");
						op = sc.nextLine();
						
						if(!op.equalsIgnoreCase("si")) {
							System.out.println();
							double total = 0.0;
							for(Consumicion consumo: consu) {
								System.out.println(consumo.toString());
								total = total + (consumo.getPrecio() * consumo.getCantidad());
							}
							
							System.out.println("Aquí tiene su comida, que aproveche");
							System.out.printf("Son %.2f Euros", total);
							salir = true;
						}else {
							System.out.println();
						}
					}else {
						salir = true;
					}	
				}else {
					salir = true;
				}
			}else {
				salir = true;
			}
		
		}while(!salir);

	}
	
	/**
	 * Método que solicita la consumición al cliente
	 * @return un String con la consumicion que el cliente ha solicitado
	 */
	public static String pedirConsumicion() {
 		String consumicion = "";
 		int cont = 0;
		do {
			consumicion = sc.nextLine();
			if(cont == 2) {
				System.out.println("Se ha terminado la sesión");
			}else {
				if(esNumero(consumicion)) {
					System.out.println("Indica el nombre de la consumición, gracias");
					System.out.println("Te quedan " + (3-(cont + 1) + " intentos"));
				}
			}
			cont++;
		}while(esNumero(consumicion) && cont <3);
		
		if(cont>2)
			return (consumicion = "");
		else
			return consumicion;
	}
	
	/**
	 * Método que solicita la cantidad a consumir.
	 * @return Un entero con la cantidad a consumir
	 */
	public static int pedirCantidad() {
		int cantidad = 0;
		String cant = "";
		int cont = 0;
		do {
			try {
				cant = sc.nextLine();
				cantidad = Integer.parseInt(cant);
				if(cantidad<=0) {
					throw new CantidadIncorrectaException(cantidad);
				}
			}catch (NumberFormatException | CantidadIncorrectaException e) {
				System.out.println(e.toString());
				//e.printStackTrace();
				if(cont==2) {
					System.out.println("Se ha terminado su sesión");
				}else {
					System.out.println("Vuelva a indicar la cantidad de forma correcta");
					System.out.println("Sólo números enteros");
					System.out.println("Tiene " + (3- (cont+1) + " intentos"));
				}
			}finally {
				cont ++;
			}
		}while(cantidad<=0 && cont<3);
		
		return cantidad;
	}
	
	public static boolean seguirPidiendo(String op){
		boolean seguir;
		
		if(op == "si") {
			seguir = true;
		}else {
			seguir  = false;
		}
		
		return seguir;
	}
	
	public static double precio (String consumicion) {
		double precio;
		switch (consumicion.toUpperCase()) {
			
			case "COCA COLA", "FANTA NARANJA", "FANTA LIMON", "BITTER",
			"BITTER KAS", "TUBO","COCACOLA", "NARANJADA", "LIMONADA":
				precio = 1.50;
				break;
			case "AGUA", "SOLO":
				precio = 1.00;
				break;
			case "DESCAFEINADO MAQUINA", "DESCAFEINADO SOBRE", "CAFE CON LECHE", "CORTADO", "CAÑA", "QUINTO":
				precio = 1.20;
				break;
			case "TERCIO", "JARRA":
				precio = 3.00;
				break;
			case "BOCADILLO DE CALAMARES", "BACON QUESO", "BOCADILLO DE TORTILLA", "HAMBURGUESA":
				precio = 4.50;
				break;
			case "BRAVAS", "RACIÓN DE CALAMARES", "RACION DE CALAMARES", "PULPITOS":
				precio = 3.50;
				break;
			default:
				precio = 0.0;
				break;
		}
		
		return precio;
		
	}
	
	private static boolean esNumero(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}
	}

}
