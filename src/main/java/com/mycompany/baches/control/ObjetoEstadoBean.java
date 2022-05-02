/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baches.control;

import com.mycompany.baches.entity.ObjetoEstado;
import java.io.Serializable;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author adrian
 */
@Stateless
@LocalBean

public class ObjetoEstadoBean extends AbstractDataAccess<ObjetoEstado>implements Serializable{
    
    @PersistenceContext(unitName = "Baches_PU")
    EntityManager em;
    
    @Override
    EntityManager getEntityManager() {
        return em;
    }
    public ObjetoEstadoBean() {
        super(ObjetoEstado.class);
    }
    
}
