package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Richard
 */
@Entity
@Table(name = "receituario")
public class Receituario implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_receituario", sequenceName = "seq_receituario_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_receituario", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "A posologia não pode ser nula")
    @NotBlank(message = "A posologia não pode ser em branco")
    @Column(name = "posologia", nullable = false)
    private String posologia;

    @NotNull(message = "A validade deve ser informada")
    @Temporal(TemporalType.DATE)
    @Column(name = "validade", nullable = false)
    private Calendar validade;
    
    @NotNull(message = "A consulta deve ser informada")
    @ManyToOne
    @JoinColumn(name = "consulta", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_receituario_consulta")     
    private Consulta consulta;

    @ManyToMany
    @JoinTable(name = "medicamento_receituario",
            joinColumns
            = @JoinColumn(name = "receituario_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "medicamento_id", referencedColumnName = "id", nullable = false))
    private List<Medicamento> medicamento = new ArrayList<>();
//    
//    @ManyToMany
//    @JoinTable(name = "medicamento_receituario",
//            joinColumns = 
//            @JoinColumn(name = "receituario", referencedColumnName = "id", nullable = false),
//            inverseJoinColumns = 
//            @JoinColumn(name = "medicamento", referencedColumnName = "id", nullable = false), 
//            uniqueConstraints = {@UniqueConstraint(columnNames = {"receituario", "medicamento"})})
//    private List<Medicamento> medicamento = new ArrayList<>();

    public Receituario() {

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
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Receituario other = (Receituario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the posologia
     */
    public String getPosologia() {
        return posologia;
    }

    /**
     * @param posologia the posologia to set
     */
    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }

    /**
     * @return the validade
     */
    public Calendar getValidade() {
        return validade;
    }

    /**
     * @param validade the validade to set
     */
    public void setValidade(Calendar validade) {
        this.validade = validade;
    }

    /**
     * @return the medicamento
     */
    public List<Medicamento> getMedicamento() {
        return medicamento;
    }

    /**
     * @param medicamento the medicamento to set
     */
    public void setMedicamento(List<Medicamento> medicamento) {
        this.medicamento = medicamento;
    }

    /**
     * @return the consulta
     */
    public Consulta getConsulta() {
        return consulta;
    }

    /**
     * @param consulta the consulta to set
     */
    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

}
