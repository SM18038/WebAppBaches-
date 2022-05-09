/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baches.resources;

import com.mycompany.baches.control.AbstractDataAccess;
import com.mycompany.baches.control.EstadoBean;
import com.mycompany.baches.entity.Estado;
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
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 *
 * @author adrian
 */
@ExtendWith(ArquillianExtension.class)
public class EstadoResourceIT {
    
    @Deployment
    public static WebArchive crearDespliegue() {
        WebArchive salida = ShrinkWrap.create(WebArchive.class)
                .addPackage("com.mycompany.baches.entity")
                .addAsResource("persistence-arquillian.xml")
                .addClass(AbstractDataAccess.class)
                .addClass(EstadoBean.class)
                .addClass(JakartaRestConfiguration.class)
                .addClass(EstadoResource.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsResource("META-INF/sql/datos.sql", "META-INF/sql/datos.sql")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(salida.toString(true));
        return salida;
    }
    @Inject
    EstadoBean cut;
    
    @ArquillianResource
    URL url;
    
    
    @Test
    @RunAsClient
    @Order(1)
    public void testCrear() {
        System.out.println("\n\n*************************************************************");
        System.out.println("Crear Estado");
        Estado nuevo = new Estado();
        nuevo.setNombre("testPrueba");
        nuevo.setFechaCreacion(new Date());
        nuevo.setObservaciones("pruebaDeIT");

        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        Response respuesta = target.path("estado").request("application/json").post(Entity.entity(nuevo, MediaType.APPLICATION_JSON));
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
        System.out.println("Modificar Estado");
        Estado edit = new Estado();
        edit.setNombre("EstadoResourceIT Modificado");
        edit.setFechaCreacion(new Date());
        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        Response respuesta = target.path("estado").request("application/json").put(Entity.entity(edit, MediaType.APPLICATION_JSON));
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
        System.out.println("Eliminar Estado");
        Estado eliminar = new Estado();

        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        Response respuesta = target.path("estado/3").request("accept","application/json").delete();
        assertEquals(resultadoEsperado, respuesta.getStatus());
        String registro = respuesta.getHeaderString("ID-eliminado");
        assertNotEquals(null, registro);
        String cuerpoString = respuesta.readEntity(String.class);
        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
        JsonObject objeto = lector.readObject();
        System.out.println("\n");
        System.out.println("ID:" + objeto.getInt("idEstado") + " eliminado correctamente");
        System.out.println("\n");
    }
    
    @Test
    @RunAsClient
    @Order(4)
    public void testFindAll(){
        System.out.println("\n\n*************************************************************");
        System.out.println("findAll Estado");
        int resultadoEsperado=200;
        Client cliente=ClientBuilder.newClient();
        WebTarget target= cliente.target(url.toString()+"resources/");
        Response respuesta = target.path("estado").request("accept","application/json").get(); 
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
            System.out.println("ID: " + objeto.getInt("idEstado"));
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
