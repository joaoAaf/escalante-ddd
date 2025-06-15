package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import apisemaperreio.escalante.escalante.utils.factories.ServicoOperacionalFactory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Escala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataFim;

    @Column(nullable = false)
    private int diasServico;

    @OneToMany(mappedBy = "escala", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServicoOperacional> militaresEscalados = new ArrayList<ServicoOperacional>();

    public Escala(LocalDate dataInicio, LocalDate dataFim, int diasServico) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.diasServico = diasServico;
    }

    public void preencherEscala(List<Militar> militares) {
        var dataAtual = this.dataInicio;
        while (dataAtual.compareTo(this.dataFim) <= 0) {
            this.preencherDiasServico(militares,
                    ServicoOperacionalFactory.criarServicoOperacional(Funcao.COV, dataAtual));
            this.preencherDiasServico(militares,
                    ServicoOperacionalFactory.criarServicoOperacional(Funcao.PERMANENTE, dataAtual));
            this.preencherDiasServico(militares,
                    ServicoOperacionalFactory.criarServicoOperacional(Funcao.AJUDANTE_DE_LINHA, dataAtual));
            dataAtual = dataAtual.plusDays(this.diasServico);
        }
    }

    private void preencherDiasServico(List<Militar> militares, ServicoOperacional servicoOperacional) {
        var militarEscalado = servicoOperacional.buscarMilitar(militares);
        servicoOperacional.escalarMilitar(militarEscalado);
        this.militaresEscalados.add(servicoOperacional);
        if (this.diasServico > 1) {
            for (int diaServico = 2; diaServico <= this.diasServico; diaServico++) {
                this.militaresEscalados.add(servicoOperacional.cloneDataSeguinte(servicoOperacional, militarEscalado));
            }
        }
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public int getDiasServico() {
        return diasServico;
    }

    public void setDiasServico(int diasServico) {
        this.diasServico = diasServico;
    }

    public List<ServicoOperacional> getMilitaresEscalados() {
        return militaresEscalados;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
        result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
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
        Escala other = (Escala) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (dataInicio == null) {
            if (other.dataInicio != null)
                return false;
        } else if (!dataInicio.equals(other.dataInicio))
            return false;
        if (dataFim == null) {
            if (other.dataFim != null)
                return false;
        } else if (!dataFim.equals(other.dataFim))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Escala [id=" + id + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", diasServico="
                + diasServico + "]";
    }

}
