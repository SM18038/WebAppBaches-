/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baches.control;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author adrian
 */
public class BeanDePruebaTest {
    
    public BeanDePruebaTest() {
    }

    /**
     * Test of getNombreUsuario method, of class BeanDePrueba.
     */
    @Test
    public void testGetNombreUsuario() throws Exception {
        System.out.println("getNombreUsuario");
        Integer idUsuario=1;
        BeanDePrueba cut = new BeanDePrueba();
        String expResult = BeanDePrueba.NOMBRE_USUARIO;
        String result = cut.getNombreUsuario(idUsuario);
        assertEquals(expResult, result);

    }
    
}
