/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.util;


import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.Exame;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.modelo.Paciente;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author richa
 */
public class FabricaObjetosConsultas {

    public static List<Consulta> carregaConsultas() {
        List<Consulta> lista = new ArrayList<>();
        Paciente p = new Paciente();
        p.setNome("Paciente 01");
        
        Medico m = new Medico();
        m.setNome("Medico 01");
        
 
        Exame ex = new Exame();
        ex.setNome("Exame 01");
        ex.setDescricao("Descrição do exame 01");
        
        
         Consulta obj = new Consulta() {};
         obj.setData(new GregorianCalendar(2018, Calendar.NOVEMBER,10));
         obj.setHora(new GregorianCalendar(2018, Calendar.HOUR_OF_DAY, 20));
         obj.setPreconsulta("Preconsulta do paciente");
         obj.setPosconsulta("Posconsulta do paciente");
         obj.setMedico(m);
         obj.setPaciente(p);
         obj.getExames().add(ex);
         
       

        lista.add(obj);

        return lista;
    }
}
