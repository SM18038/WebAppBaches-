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

/**
 *
 * @author adrian
 */
@ExtendWith(ArquillianExtension.class)
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
    public void testCrear() throws Exception {
        System.out.println("Crear");
        assertNotNull(cut);
        TipoObjeto nuevo = new TipoObjeto();
        cut.crear(nuevo);
        TipoObjeto esperado = cut.findById(5);

    }

    @Test
    public void testfindById() {
        System.out.println("findById");

    }

    @Test
    public void testFindAll() {
        System.out.println("findAll");
        assertNotNull(cut);
        List<TipoObjeto> resultado = cut.findAll();
        assertNotNull(resultado);
        assertTrue(!resultado.isEmpty());
        System.out.println("La lista posee " + resultado.size());

    }

    @Test
    public void testfindRange() {
        System.out.println("findRange");

    }

    @Test
    public void testContar() throws Exception {
        System.out.println("contar");
    }

    @Test
    public void testEliminar() throws Exception {
        System.out.println("eliminar");
    }
    
    @Test
    public void testModificar() throws Exception {
        System.out.println("modificar");
    }

    }
