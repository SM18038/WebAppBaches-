
package com.mycompany.baches.control;


import com.mycompany.baches.entity.TipoObjeto;
import java.io.Serializable;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean

public class TipoObjetoBean extends AbstractDataAccess<TipoObjeto> implements Serializable{
    
    @PersistenceContext(unitName = "Baches_PU")
    EntityManager em;
    
    @Override
    EntityManager getEntityManager() {
        return em;
    }
    public TipoObjetoBean() {
        super(TipoObjeto.class);
    }
    
}
