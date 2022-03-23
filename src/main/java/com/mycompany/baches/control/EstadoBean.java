package com.mycompany.baches.control;

import com.mycompany.baches.entity.Estado;
import java.io.Serializable;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
