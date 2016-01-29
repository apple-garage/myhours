package com.applegarage.reportes.elreportador;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.applegarage.reportes.elreportador.database.SuperConexionClass;
import com.applegarage.reportes.elreportador.model.ConManager;

/**
 * Root resource
 */
@Path("conexion")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     * @throws SQLException 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String nuevaConexion( JSONObject jobj ) throws SQLException {
    	SuperConexionClass spclass = new SuperConexionClass();
    	Connection connection = spclass.getConnection();
    	
        connection.close();
        return "Got it!";
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void cerrarConexion ( JSONObject jobj){
    	ConManager conManager = new ConManager();
    	conManager.close(jobj.get("id"));
    }
    
}
