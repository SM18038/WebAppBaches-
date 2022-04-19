/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baches.resources;

import com.mycompany.baches.control.TipoObjetoBean;
import com.mycompany.baches.entity.TipoObjeto;
import java.io.Serializable;
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

@Path("tipoobjeto")
@RequestScoped

public class TipoObjetoResource implements Serializable{
    @Inject
    TipoObjetoBean toBean;

    @GET
    @Produces({"application/json; charset=UTF-8"})
    public Response findAll() {
        List<TipoObjeto> registros = toBean.findAll();
        Long total = toBean.contar();

        return Response.ok(registros)
                .header("Total-Registros", total)
                .build();

    }
    
    @POST
    public Response crear(TipoObjeto nuevo) {
        toBean.crear(nuevo);
        return Response.ok(nuevo)
                .header("Registro-Creado", nuevo)
                .build();
    }

    @PUT
    public Response modificar(TipoObjeto act) {
        toBean.modificar(act);
        return Response.ok(act)
                .header("Modificado", act)
                .build();

    }

    @DELETE
    @Path("{userId}")
    public Response eliminar(@PathParam("userId") int id) {
        TipoObjeto eliminar = new TipoObjeto();
        eliminar.setIdTipoObjeto(id);
        toBean.eliminar(eliminar);
        return Response.ok(eliminar)
                .header("ID eliminado", id)
                .build();
    }
    
    @GET
    @Path("contar")
    public CompletableFuture<Long> contar(){
        return CompletableFuture.supplyAsync(toBean::contar);
    }
}
