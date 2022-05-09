/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baches.resources;

import com.mycompany.baches.control.ObjetoEstadoBean;
import com.mycompany.baches.entity.ObjetoEstado;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author adrian
 */
@Path("objetoestado")
@RequestScoped

public class ObjetoEstadoResource {
    
    @Inject
    ObjetoEstadoBean toBean;
    
    @GET
    @Produces({"application/json; charset=UTF-8"})
    public Response findAll() {
        List<ObjetoEstado> registros = toBean.findAll();
        Long total = toBean.contar();
        return Response.ok(registros)
                .header("Total-Registros", total)
                .build();
    }

    public Response findRange(
            @QueryParam(value = "first")
            @DefaultValue(value = "0") int first,
            @QueryParam(value = "pagesize")
            @DefaultValue(value = "50") int pageSize) {
        List<ObjetoEstado> registros = toBean.findRange(first, pageSize);
        Long total = toBean.contar();
        return Response.ok(registros)
                .header("Total-Registros", total)
                .build();
    }

    @GET
    @Path("contar")
    public CompletableFuture<Long> contar() {
        return CompletableFuture.supplyAsync(toBean::contar);
    }

    @POST
    public Response crear(ObjetoEstado nuevo) {
        toBean.crear(nuevo);
        return Response.ok(nuevo)
                .header("Registro-Creado", nuevo)
                .build();
    }

    @PUT
    public Response modificar(ObjetoEstado modificar) {
        toBean.modificar(modificar);
        return Response.ok(modificar)
                .header("Modificado", modificar)
                .build();
    }
    
    @DELETE
    @Path("{idObjetoEstado}")
    public Response Eliminar(@PathParam("idObjetoEstado") Long id){
        ObjetoEstado registro = toBean.findById(id);
        registro.setIdObjetoEstado(id);
        toBean.eliminar(registro);
        return Response.ok(registro)
            .header("ID-eliminado",id)
                    .build();
    }
    
    
}
