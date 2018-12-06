package br.edu.ifsul.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


/**
 *
 * @author Richard
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "medico")
public class Medico extends Paciente implements Serializable {

    @NotNull(message = "O CRM não pode ser nulo")
    @Length(max = 10, message = "O CRM não pode ter mais que {max} caracteres")
    @NotBlank(message = "O CRM não pode ser em branco")
    @Column(name = "CRM", nullable = false, length = 10)
    private String crm;
    
    @NotNull(message = "A especialidade deve ser informada")
    @ManyToOne
    @JoinColumn(name = "especialidade", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_medico_especialidade")       
    private Especialidade especialidade;
  

    public Medico() {
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    /**
     * @return the especialidade
     */
    public Especialidade getEspecialidade() {
        return especialidade;
    }

    /**
     * @param especialidade the especialidade to set
     */
    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
    
}
