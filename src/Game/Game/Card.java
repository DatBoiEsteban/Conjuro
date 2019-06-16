package Game;

import java.awt.image.BufferedImage;

public class Card implements java.io.Serializable{

	private String Nombre;
	private String  Imagen;
	private String Tipo;
	private byte[] DescripcionCifrada;
	private String Descripcion;
	private  Object Llave1;
	private  Object Llave2;

	public Card(String pNombre,String pDescripcion, String pImagen ){
		Nombre = pNombre;
		 Imagen= pImagen;
		 Descripcion= pDescripcion;

	}
	public void encryptData(byte[] pDescripcionCifrada,Object pLlave1, Object pLlave2,String pTipo){
		DescripcionCifrada=pDescripcionCifrada;
		Llave1 = pLlave1;
		Llave2 = pLlave2;
		Tipo= pTipo;
		
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public byte[] getDescripcionCifrada() {
		return DescripcionCifrada;
	}
	public void setDescripcionCifrada(byte[] descripcionCifrada) {
		DescripcionCifrada = descripcionCifrada;
	}
	public Object getLlave1() {
		return Llave1;
	}
	public void setLlave1(Object llave1) {
		Llave1 = llave1;
	}
	public Object getLlave2() {
		return Llave2;
	}
	public void setLlave2(Object llave2) {
		Llave2 = llave2;
	}
	public String getImagen() {
		return this.Imagen;
	}
}
