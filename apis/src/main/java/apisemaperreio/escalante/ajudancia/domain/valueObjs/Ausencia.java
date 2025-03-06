package apisemaperreio.escalante.ajudancia.domain.valueObjs;

import java.time.LocalDate;

import apisemaperreio.escalante.ajudancia.domain.entities.Militar;

public class Ausencia {

    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String motivo;
    private String descricao;
    private Militar militar;

    public Ausencia(LocalDate dataInicio, LocalDate dataFim, String motivo, String descricao, Militar militar) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.motivo = motivo;
        this.descricao = descricao;
        this.militar = militar;
    }

    public Ausencia(LocalDate dataInicio, LocalDate dataFim, String motivo, Militar militar) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.motivo = motivo;
    }

    public Ausencia() {
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Militar getMilitar() {
        return militar;
    }

    public void setMilitar(Militar militar) {
        this.militar = militar;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
        result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
        result = prime * result + ((motivo == null) ? 0 : motivo.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((militar == null) ? 0 : militar.hashCode());
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
        Ausencia other = (Ausencia) obj;
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
        if (motivo == null) {
            if (other.motivo != null)
                return false;
        } else if (!motivo.equals(other.motivo))
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (militar == null) {
            if (other.militar != null)
                return false;
        } else if (!militar.equals(other.militar))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Ausencia [dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", motivo=" + motivo + "]";
    }

}
