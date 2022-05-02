/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baches.resources;

import com.mycompany.baches.control.ObjetoBean;
import com.mycompany.baches.entity.Objeto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author adrian
 */
@Path("objeto")
@RequestScoped

public class ObjetoResource implements Serializable{
    @Inject
    ObjetoBean toBean;
    
    @GET
    @Produces({"application/json; charset=UTF-8"})
    public Response findAll(){
        List<Objeto> registros=toBean.findAll();
        Long total=toBean.contar();
        return Response.ok(registros)
                .header("Total-registros", total)
                .build();
    }
    
    @POST
    public Response crear(Objeto nuevo) {
        nuevo.setNombre("ObjetoResource");
        nuevo.setLatitud(BigDecimal.TEN);
        nuevo.setLongitud(BigDecimal.ONE);
        toBean.crear(nuevo);
        return Response.ok(nuevo)
                .header("Registro Creado", nuevo)
                .build();
    }

    @PUT
    public Response modificar(Objeto actual) {
        actual.setNombre("ObjetoResource modificado");
        actual.setLatitud(BigDecimal.TEN);
        actual.setLongitud(BigDecimal.ONE);
        toBean.modificar(actual);
        return Response.ok(actual)
                .header("Modificado", actual)
                .build();

    }
    
    @DELETE
    @Path("{idObjeto}")
    public Response Eliminar(@PathParam("idObjeto") int id){
        Objeto registro = toBean.findById(id);
        toBean.eliminar(registro);
        return Response.ok(registro)
            .header("ID-eliminado",id)
                    .build();
    }
    
    @GET
    @Path("contar")
    public CompletableFuture<Long> contar(){
        return CompletableFuture.supplyAsync(toBean::contar);
    }
}