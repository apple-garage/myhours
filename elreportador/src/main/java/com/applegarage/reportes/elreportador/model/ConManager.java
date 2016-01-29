package com.applegarage.reportes.elreportador.model;

import java.util.List;

import org.json.simple.JSONObject;

public class ConManager {
	
	private static final int max = 20;
	private static int current = 0;
	private List<JSONObject> conexiones;
	
	
	// usa una conexion o establece una nueva de no ser especificado el connection id
	public void request(JSONObject obj){
	}


	public void close(Object object) {
		// TODO Auto-generated method stub
		
	}
	
}
