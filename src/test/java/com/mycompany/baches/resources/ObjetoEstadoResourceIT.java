/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baches.resources;

import com.mycompany.baches.control.AbstractDataAccess;
import com.mycompany.baches.control.ObjetoEstadoBean;
import com.mycompany.baches.entity.ObjetoEstado;
import java.io.StringReader;
import java.net.URL;
import java.util.Date;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 *
 * @author adrian
 */
@ExtendWith(ArquillianExtension.class)
public class ObjetoEstadoResourceIT {
    
    @Deployment
    public static WebArchive crearDespliegue() {
        WebArchive salida = ShrinkWrap.create(WebArchive.class)
                .addPackage("com.mycompany.baches.entity")
                .addAsResource("persistence-arquillian.xml")
                .addClass(AbstractDataAccess.class)
                .addClass(ObjetoEstadoBean.class)
                .addClass(JakartaRestConfiguration.class)
                .addClass(ObjetoEstadoResource.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsResource("META-INF/sql/datos.sql", "META-INF/sql/datos.sql")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(salida.toString(true));
        return salida;
    }
    
    @Inject
    ObjetoEstadoBean cut;
    
    @ArquillianResource
    URL url;
    
    
    @Test
    @RunAsClient
    @Order(1)
    public void testCrear() {
        System.out.println("\n\n*************************************************************");
        System.out.println("Crear ObjetoEstado");
        ObjetoEstado nuevo = new ObjetoEstado();
        nuevo.setActual(Boolean.TRUE);
        nuevo.setFechaAlcanzado(new Date());
        nuevo.setObservaciones("nuevoRegistro");

        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        Response respuesta = target.path("objetoestado").request("application/json").post(Entity.entity(nuevo, MediaType.APPLICATION_JSON));
        assertEquals(resultadoEsperado, respuesta.getStatus());
        String registro = respuesta.getHeaderString("Registro-Creado");
        assertNotEquals(null, registro);
        String cuerpoString = respuesta.readEntity(String.class);
        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
        JsonObject objeto = lector.readObject();
        System.out.println("\n\n");
        System.out.println("Creado " + objeto);
        System.out.println("\n\n");
    }


    @Test
    @RunAsClient
    @Order(2)
    public void testModificar() {
        System.out.println("\n\n*************************************************************");
        System.out.println("Modificar ObjetoEstado");
        ObjetoEstado edit = new ObjetoEstado();
        edit.setActual(Boolean.FALSE);
        edit.setFechaAlcanzado(new Date());
        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        Response respuesta = target.path("objetoestado").request("accept","application/json").put(Entity.entity(edit, MediaType.APPLICATION_JSON));
        assertEquals(resultadoEsperado, respuesta.getStatus());
        String registro = respuesta.getHeaderString("Modificado");
        assertNotEquals(null, registro);
        String cuerpoString = respuesta.readEntity(String.class);
        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
        JsonObject objeto = lector.readObject();
        System.out.println("\n\n");
        System.out.println("Modificado " + objeto);
        System.out.println("\n\n");

    }
     @Test
    @RunAsClient
    @Order(3)
    public void testEliminar() {
        System.out.println("\n\n*************************************************************");
        System.out.println("Eliminar ObjetoEstado");
        ObjetoEstado delete = new ObjetoEstado();        
        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        Response respuesta = target.path("objetoestado/3").request("accept","application/json").delete();
        assertEquals(resultadoEsperado, respuesta.getStatus());
        String registro = respuesta.getHeaderString("ID-eliminado");
        assertNotEquals(null, registro);
        String cuerpoString = respuesta.readEntity(String.class);
        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
        JsonObject objeto = lector.readObject();

        System.out.println("\n\n");
        System.out.println("ID:" + objeto.getInt("idObjetoEstado") + " eliminado con exito");
        System.out.println("\n\n");
    }
    
    @Test
    @RunAsClient
    @Order(4)
    public void testFindAll(){
        System.out.println("\n\n*************************************************************");
        System.out.println("findAllObjetoEstado");
        int resultadoEsperado=200;
        Client cliente=ClientBuilder.newClient();
        WebTarget target= cliente.target(url.toString()+"resources/");
        Response respuesta = target.path("objetoestado").request("accept","application/json").get(); 
        assertEquals(resultadoEsperado, respuesta.getStatus());
        String totalTexto = respuesta.getHeaderString("Total-Registros");
        assertNotEquals(Integer.valueOf(0), Integer.valueOf(totalTexto));
        System.out.println("Total: "+totalTexto);
        String cuerpoString = respuesta.readEntity(String.class);
        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
        JsonArray listaJson = lector.readArray();
        int totalRegistros = listaJson.size();
        assertTrue(totalRegistros>0);
        System.out.println("\n");
        for(int i=0; i< listaJson.size(); i++){
            JsonObject objeto = listaJson.getJsonObject(i);
            System.out.println("ID: " + objeto.getInt("idObjetoEstado"));
        }
        System.out.println("\n");
    }
    
   @Test
    @Order(5)
    public void testContar() {
        System.out.println("\n\n*************************************************************");
        System.out.println("Contar");
        assertNotNull(cut);
        Long resultado = cut.contar();
        assertNotNull(resultado);
        System.out.println("Se encontraron " + resultado);

    }
    
}
