
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

    @Override
    public Long contar() throws IllegalStateException {
        try {
            Thread.sleep(2000);
        } catch (Exception ex) {
        }
        return super.contar(); //To change body of generated methods, choose Tools | Templates.
    }
}
