/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baches.control;

import com.mycompany.baches.entity.TipoObjeto;
import com.sun.jdi.LongValue;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

/**
 *
 * @author adrian
 */
public class TipoObjetoBeanTest {

    public TipoObjetoBeanTest() {
    }

    /**
     * Test of crear method, of class TipoObjetoBean.
     */
    @Test
    public void testCrear() throws Exception {
        System.out.println("crear");
        TipoObjeto nuevo = new TipoObjeto();
        TipoObjetoBean cut = new TipoObjetoBean();

        assertThrows(IllegalArgumentException.class, () -> {
            cut.crear(null);
        });
        assertThrows(IllegalStateException.class, () ->{
            cut.crear(nuevo);
        });
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        cut.em =mockEM;
        cut.crear(nuevo);
    }

    /**
     * Test of findById method, of class TipoObjetoBean.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        Integer id = 1;
        EntityManager mockEM=Mockito.mock(EntityManager.class);
        
        TipoObjetoBean cut = new TipoObjetoBean();
        TipoObjeto esperado = new TipoObjeto();
        Mockito.when(mockEM.find(TipoObjeto.class,id)).thenReturn(esperado);
        assertThrows(IllegalArgumentException.class, () -> {
            cut.findById(null);
        });
        assertThrows(IllegalStateException.class, () ->{
            cut.findById(id);
        });
        
        cut.em=mockEM;
        TipoObjeto encontrado=cut.findById(id);
        assertNotNull(encontrado);
        assertEquals(esperado,encontrado);
        TipoObjetoBean espia = Mockito.spy(TipoObjetoBean.class);
        espia.em = mockEM;
        
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        try {
            espia.findById(id);
        } catch (Exception e) {
        }
        Mockito.verify(espia, Mockito.times(1)).getEntityManager();
        
    }   


    /**
     * Test of findAll method, of class TipoObjetoBean.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
    }

    /**
     * Test of findRange method, of class TipoObjetoBean.
     */
    @Test
    public void testFindRange() throws Exception {
        System.out.println("findRange");
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of contar method, of class TipoObjetoBean.
     */
    @Test
    public void testContar() throws Exception {
        System.out.println("contar");
        Long esperado=Long.valueOf(1);
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        CriteriaBuilder mockCB = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery mockCQ = Mockito.mock(CriteriaQuery.class);
        TypedQuery mockTQ = Mockito.mock(TypedQuery.class);
        
        Mockito.when(mockEM.getCriteriaBuilder()).thenReturn(mockCB);
        Mockito.when(mockCB.createQuery(Long.class)).thenReturn(mockCQ);
        Mockito.when(mockEM.createQuery(mockCQ)).thenReturn(mockTQ);
        Mockito.when(mockTQ.getSingleResult()).thenReturn(esperado);
        
        TipoObjetoBean cut = new TipoObjetoBean();
        assertThrows(IllegalStateException.class, ()-> {
            cut.contar();
        });
        
        cut.em=mockEM;
        Long resultado = cut.contar();
        assertNotNull(resultado);
        assertEquals(esperado, resultado);
        
        try {
            cut.em=null;
            cut.contar();
            //fail("El entity manager era nulo");
        } catch (Exception e) {
        }
        
        TipoObjetoBean espia = Mockito.spy(TipoObjetoBean.class);
        espia.em = mockEM;
        
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        try {
            espia.contar();
        } catch (Exception e) {
            
        }
        Mockito.verify(espia, Mockito.times(1)).getEntityManager();
 
    }   
    /**
     * Test of eliminar method, of class TipoObjetoBean.
     */
    @Test
    public void testEliminar() throws Exception {
        System.out.println("eliminar");
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        TipoObjetoBean cut = new TipoObjetoBean();
        cut.em=mockEM;
        TipoObjeto eliminado = new TipoObjeto(1);
        cut.eliminar(eliminado);
        Mockito.verify(mockEM,Mockito.times(1)).remove(ArgumentMatchers.any());
        try {
            cut.eliminar(null);
            fail("El parametro era nulo");
        } catch (Exception e) {
        }
        
        try {
            cut.em=null;
            cut.eliminar(eliminado);
            fail("El entity manager era nulo");
        } catch (Exception e) {
        }
        
        //fail("Esta prueba fallara");
    }

    /**
     * Test of modificar method, of class TipoObjetoBean.
     */
    @Test
    public void testModificar() throws Exception {
        System.out.println("modificar");

    }

}
