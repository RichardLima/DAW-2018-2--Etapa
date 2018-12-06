package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Paciente;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Richard
 */
public class TestePersistirPaciente {
    
    EntityManager em;

    public TestePersistirPaciente() {
    }

    @Before
    public void setUp() {
        em = EntityManagerUtil.getEntityManager();
    }

    @After
    public void tearDown() {
        em.close();
    }
    
    @Test
    public void teste(){
        boolean erro = false;
        try {
            Paciente obj = new Paciente();
            obj.setNome("João");
            obj.setNascimento(new GregorianCalendar(1990, Calendar.NOVEMBER,20));
            obj.setTelefone("(54)8737-9847");
            obj.setSexo("Masculino");
            obj.setHistorico("Histórico do paciente João");
            obj.setPeso(90.20);  
            obj.setAltura(1.92);
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e){
            erro = true;
            e.printStackTrace();
        }
        // aqui verifico se o valor do erro continua falso oque indica que o teste passou
        Assert.assertEquals(false, erro);
    }

}