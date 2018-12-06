package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Richard
 */
@Entity
@Table(name = "consulta")
public class Consulta implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_consulta", sequenceName = "seq_consulta_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_consulta", strategy = GenerationType.SEQUENCE)
    private Integer id;

//    @NotNull(message = "A data deve ser informada")
    @Temporal(TemporalType.DATE)
    @Column(name = "data", nullable = true)
    private Calendar data;

//    @NotNull(message = "A hora deve ser informada")
    @Column(name = "hora", columnDefinition = "time" , nullable = true)
    private Calendar hora;

    @NotNull(message = "A preconsulta não pode ser nulo")
    @NotBlank(message = "A preconsulta não pode ser em branco")
    @Column(name = "preconsulta", nullable = false)
    private String preconsulta;

    @NotNull(message = "A posconsulta não pode ser nulo")
    @NotBlank(message = "A posconsulta não pode ser em branco")
    @Column(name = "posconsulta", nullable = false)
    private String posconsulta;
    
    @ManyToOne
    @JoinColumn(name = "paciente", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_consulta_paciente")     
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "medico", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_consulta_medico")     
    private Medico medico;
    
    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Receituario> receituarios = new ArrayList<>();
    
    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Exame> exames = new ArrayList<>();
    

    public List<Receituario> getReceituarios() {
        return receituarios;
    }

    public void setReceituarios(List<Receituario> receituarios) {
        this.receituarios = receituarios;
    }
    
      public void adicionarReceituario(Receituario obj){
        obj.setConsulta(this);        
        this.receituarios.add(obj);
    }
    
    public void removerReceituario(int index){
        this.receituarios.remove(index);
    }    
    
    public List<Exame> getExames() {
        return exames;
    }

    public void setExames(List<Exame> exames) {
        this.exames = exames;
    }
    
    public void adicionarExame(Exame obj){
        obj.setConsulta(this);        
        this.exames.add(obj);
    }
    
    public void removerExame(int index){
        this.exames.remove(index);
    } 
   
    public Consulta() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Consulta other = (Consulta) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the data
     */
    public Calendar getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Calendar data) {
        this.data = data;
    }

    /**
     * @return the hora
     */
    public Calendar getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(Calendar hora) {
        this.hora = hora;
    }

    /**
     * @return the preconsulta
     */
    public String getPreconsulta() {
        return preconsulta;
    }

    /**
     * @param preconsulta the preconsulta to set
     */
    public void setPreconsulta(String preconsulta) {
        this.preconsulta = preconsulta;
    }

    /**
     * @return the posconsulta
     */
    public String getPosconsulta() {
        return posconsulta;
    }

    /**
     * @param posconsulta the posconsulta to set
     */
    public void setPosconsulta(String posconsulta) {
        this.posconsulta = posconsulta;
    }

    /**
     * @return the medico
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     * @param medico the medico to set
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    /**
     * @return the paciente
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * @param paciente the paciente to set
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

}
