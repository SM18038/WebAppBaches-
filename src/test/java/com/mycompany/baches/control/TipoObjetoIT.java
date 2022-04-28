package com.mycompany.baches.control;

import com.mycompany.baches.entity.TipoObjeto;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

/**
 *
 * @author adrian
 */
@ExtendWith(ArquillianExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class TipoObjetoIT {

    @Deployment
    public static WebArchive crearDespliegue() {
        WebArchive salida = ShrinkWrap.create(WebArchive.class)
                .addPackage("com.mycompany.baches.entity")
                .addAsResource("persistence-arquillian.xml")
                .addClass(AbstractDataAccess.class)
                .addClass(TipoObjetoBean.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsResource("META-INF/sql/datos.sql", "META-INF/sql/datos.sql")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(salida.toString(true));
        return salida;
    }

    @Inject
    TipoObjetoBean cut;

    @Test
    @Order(6)
    public void testContar() throws Exception {
        System.out.println("Contar");
        assertNotNull(cut);
        Long resultado = cut.contar();
        assertNotNull(resultado);
        System.out.println("Se encontraron " + resultado + " registros");
    }

    @Test
    @Order(1)
    public void testCrear() throws Exception {
        System.out.println("Crear");
        assertNotNull(cut);
        TipoObjeto nuevo = new TipoObjeto();
        cut.crear(nuevo);
        TipoObjeto esperado = cut.findById(5);
        Long contar = cut.contar();
        System.out.println("Total: " + contar);

    }

    @Test
    @Order(7)
    public void testfindById() {
        System.out.println("findById");
        assertNotNull(cut);
        Integer id = 1;
        TipoObjeto resultado = cut.findById(id);
        System.out.println("Se encontro el resultado " + resultado.getActivo());

    }

    @Test
    @Order(2)
    public void testModificar() throws Exception {
        System.out.println("modificar");
        assertNotNull(cut);
        int id = 2;
        TipoObjeto nuevo = new TipoObjeto();
        nuevo.setIdTipoObjeto(id);
        TipoObjeto sinModificar = cut.findById(id);
        cut.modificar(nuevo);
        TipoObjeto modificado = cut.findById(id);
    }
    
    @Test
    @Order(3)
    public void testFindAll() {
        System.out.println("FindAll");
        Assertions.assertNotNull(cut);
        List<TipoObjeto> resultado = cut.findAll();
        Assertions.assertNotNull(resultado);
        Assertions.assertTrue(!resultado.isEmpty());
        System.out.println("La lista posee "+ resultado.size());

    }

    @Test
    @Order(4)
    public void testfindRange() {
        System.out.println("findRange");
        assertNotNull(cut);
        int first = 1;
        int pageSize = 3;
        List<TipoObjeto> resultado = cut.findRange(first, pageSize);
        assertNotNull(resultado);
        assertTrue(!resultado.isEmpty());
        System.out.println("Se encontraron " + resultado.size());
    }

    @Test
    @Order(5)
    public void testEliminar() throws Exception {
         System.out.println("Eliminar");
        assertNotNull(cut);
        TipoObjeto eliminado = new TipoObjeto(2);
        cut.eliminar(eliminado);
        System.out.println("Eliminado el Registro "+ eliminado);
    }
    
}
