
package com.mycompany.baches.control;

import com.mycompany.baches.entity.Objeto;
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

   public class ObjetoBean extends AbstractDataAccess<Objeto> implements Serializable {

    @PersistenceContext(unitName = "Baches_PU")
    EntityManager em;
    
    @Override
    EntityManager getEntityManager() {
        return em;
    }
    public ObjetoBean() {
        super(Objeto.class);
    }
    
    
    public List<Objeto> findByIdTipoObjeto(final Integer idTipoObjeto, int first, int pageSize){
        if (this.em !=null && idTipoObjeto != null) {
            Query q = em.createNamedQuery("Objeto.findByTipoObeto");
            q.setParameter("idTipoObjeto", idTipoObjeto);
            q.setFirstResult(first);
            q.setMaxResults(pageSize);
            return q.getResultList();
        }
        
        return Collections.EMPTY_LIST;
    }
    
    public int countByIdTipoObjeto(final Integer idTipoObjeto){
        if (this.em !=null && idTipoObjeto != null) {
            Query q = em.createNamedQuery("Objeto.countByTipoObeto");
            q.setParameter("idTipoObjeto", idTipoObjeto);

            return ((Long)q.getSingleResult()).intValue();
        }
        
        return 0;
    }
    
}
