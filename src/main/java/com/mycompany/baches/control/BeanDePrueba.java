
package com.mycompany.baches.control;

import java.io.Serializable;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author adrian
 */
@Stateless
@LocalBean
public class BeanDePrueba implements Serializable{
    
    public final static String NOMBRE_USUARIO = "NO DISPONIBLE";
    
    public String getNombreUsuario(final Integer idUsuario){
        return NOMBRE_USUARIO;
    }
    
}
