/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baches.control;

import com.mycompany.baches.entity.Ruta;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author adrian
 */

@Stateless
@LocalBean
public class RutaBean extends AbstractDataAccess<Ruta> implements Serializable{
    
    @PersistenceContext(unitName = "Baches_PU")
    EntityManager em;
    
    @Override
    EntityManager getEntityManager() {
        return em;
    }
    public RutaBean() {
        super(Ruta.class);
    }
    
    public List<Ruta> findByIdRuta(final Long idRuta) {
        if (this.em != null && idRuta != null) {
            Query q = em.createNamedQuery("Ruta.findByIdRuta");
            q.setParameter("idRuta", idRuta);
            return q.getResultList();

        }
        return Collections.EMPTY_LIST;
    }

    public List<Ruta> findNombre(String nombre) {

        Query q = em.createNamedQuery("Ruta.findByNombre");
        q.setParameter("nombre", nombre);

        return q.getResultList();
}
    
}
