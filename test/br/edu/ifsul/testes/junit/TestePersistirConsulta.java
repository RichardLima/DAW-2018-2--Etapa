package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.Medico;
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
public class TestePersistirConsulta {
    
    EntityManager em;

    public TestePersistirConsulta() {
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
            Consulta obj = new Consulta();
            obj.setData(new GregorianCalendar(2018, Calendar.NOVEMBER,10));

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 22);
            calendar.set(Calendar.MINUTE, 29);
            calendar.set(Calendar.SECOND, 22);
            obj.setHora(calendar);
            obj.setPreconsulta("Preconsulta do paciente");
            obj.setPosconsulta("Posconsulta do paciente");
            obj.setMedico(em.find(Medico.class, 1));
            obj.setPaciente(em.find(Paciente.class, 2));
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