package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public abstract class ServicoOperacional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataServico;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Funcao funcao;

    @Column(nullable = false)
    private int folga;

    @Column(nullable = false)
    private String matriculaMilitar;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_escala")
    private Escala escala;

    public ServicoOperacional(LocalDate dataServico, Funcao funcao) {
        this.dataServico = dataServico;
        this.funcao = funcao;
    }

    public abstract void escalarMilitar(Militar militar);

    public abstract Optional<Militar> buscarMilitar(List<Militar> militares);

    public abstract ServicoOperacional cloneDataSeguinte(ServicoOperacional servicoOperacional, Militar militar);

    protected int definirFolga(int folgaMilitar, int folgaServico) {
        return folgaMilitar > folgaServico ? folgaMilitar : folgaServico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataServico() {
        return dataServico;
    }

    public void setDataServico(LocalDate dataServico) {
        this.dataServico = dataServico;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public int getFolga() {
        return folga;
    }

    public void setFolga(int folga) {
        this.folga = folga;
    }

    public String getMatriculaMilitar() {
        return matriculaMilitar;
    }

    public void setMatriculaMilitar(String matriculaMilitar) {
        this.matriculaMilitar = matriculaMilitar;
    }

    public Escala getEscala() {
        return escala;
    }

    public void setEscala(Escala escala) {
        this.escala = escala;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((dataServico == null) ? 0 : dataServico.hashCode());
        result = prime * result + ((funcao == null) ? 0 : funcao.hashCode());
        result = prime * result + ((matriculaMilitar == null) ? 0 : matriculaMilitar.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ServicoOperacional other = (ServicoOperacional) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (dataServico == null) {
            if (other.dataServico != null)
                return false;
        } else if (!dataServico.equals(other.dataServico))
            return false;
        if (funcao != other.funcao)
            return false;
        if (matriculaMilitar == null) {
            if (other.matriculaMilitar != null)
                return false;
        } else if (!matriculaMilitar.equals(other.matriculaMilitar))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ServicoOperacional [id=" + id + ", dataServico=" + dataServico + ", funcao=" + funcao + ", folga="
                + folga + ", matriculaMilitar=" + matriculaMilitar + "]";
    }

}
