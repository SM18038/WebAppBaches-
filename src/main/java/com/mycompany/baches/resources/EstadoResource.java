/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baches.resources;

import com.mycompany.baches.control.EstadoBean;
import com.mycompany.baches.entity.Estado;
import java.io.Serializable;
import java.util.Date;
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
@Path("estado")
@RequestScoped

public class EstadoResource implements Serializable{
     @Inject
     EstadoBean toBean;
     
    @GET
    @Produces({"application/json; charset=UTF-8"})
    public Response findAll(){
        List<Estado> registros=toBean.findAll();
        Long total=toBean.contar();
        return Response.ok(registros)
                .header("Total-registros", total)
                .build();
    }
     
     @POST
    public Response crear(Estado nuevo) {
        nuevo.setNombre("EstadoResource");
        nuevo.setFechaCreacion(new Date());
        toBean.crear(nuevo);
        return Response.ok(nuevo)
                .header("Registro Creado", nuevo)
                .build();
    }

    @PUT
    public Response modificar(Estado actual) {
        actual.setNombre("EstadoResource modificado");
        actual.setFechaCreacion(new Date());
        toBean.modificar(actual);
        return Response.ok(actual)
                .header("Modificado", actual)
                .build();

    }

    @DELETE
    @Path("{idUserEstado}")
    public Response Eliminar(@PathParam("idUserEstado") int id){
        Estado registro = toBean.findById(id);
        registro.setIdEstado(id);
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
