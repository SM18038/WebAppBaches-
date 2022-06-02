package com.mycompany.baches.control;

import com.mycompany.baches.entity.Estado;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean

public class EstadoBean extends AbstractDataAccess<Estado> implements Serializable {

    @PersistenceContext(unitName = "Baches_PU")
    EntityManager em;
    
    @Override
    EntityManager getEntityManager() {
        return em;
    }
    public EstadoBean() {
        super(Estado.class);
    }
    
    public List<Estado> findByIdEstado(final Integer idEstado) {
        if (this.em != null && idEstado != null) {
            Query q = em.createNamedQuery("Estado.findByIdEstado");
            q.setParameter("idEstado", idEstado);
            return q.getResultList();

        }
        return Collections.EMPTY_LIST;
    }

    public List<Estado> findNombre(String nombre) {

        Query q = em.createNamedQuery("Estado.findByNombre");
        q.setParameter("nombre", nombre);

        return q.getResultList();
    }
}
