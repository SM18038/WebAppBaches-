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
@Path("tipoobjeto")
@RequestScoped

public class TipoObjetoResource implements Serializable {

    @Inject
    TipoObjetoBean toBean;
    
    @GET
    @Path("all")
    public Response findAll() {
        List<TipoObjeto> registros = toBean.findAll();
        Long total = toBean.contar();

        return Response.ok(registros)
                .header("Total-Registros", total)
                .build();

    }

    @GET
    @Produces({"application/json; charset=UTF-8"})
    @Path("findRange/{first}/{pagesize}")
    public Response findRange(
            @QueryParam(value = "first")
            @DefaultValue(value = "0") int first,
            @QueryParam(value = "pageSize")
            @DefaultValue(value = "50") int pageSize) {
        List<TipoObjeto> registros = toBean.findRange(first, pageSize);
        Long total = toBean.contar();
        return Response.ok(registros)
                .header("Total-Registros", total)
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Credentials", "true")
//                .header("Access-Control-Allow-Headers", "origin,content-type,accept,authorization")
//                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                
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
    @Path("eliminar/{idTipoObjeto}")
    public Response Eliminar(@PathParam("idTipoObjeto") int id){
        TipoObjeto registro = toBean.findById(id);
        toBean.eliminar(registro);
        return Response.ok(registro)
            .header("ID-eliminado",id)
                    .build();
    }

    @GET
    @Path("contar")
    public CompletableFuture<Long> contar() {
        return CompletableFuture.supplyAsync(toBean::contar);
    }
}
