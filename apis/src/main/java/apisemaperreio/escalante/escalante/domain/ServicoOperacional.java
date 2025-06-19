package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public abstract class ServicoOperacional {

    private Long id;
    private LocalDate dataServico;
    private Funcao funcao;
    private int folga;
    private Militar militar;

    public ServicoOperacional(LocalDate dataServico, Funcao funcao) {
        this.dataServico = dataServico;
        this.funcao = funcao;
    }

    public abstract void escalarMilitar(Optional<Militar> militar);

    public abstract Optional<Militar> buscarMilitar(List<Militar> militares);

    public abstract ServicoOperacional cloneDataSeguinte(ServicoOperacional servicoOperacional, Optional<Militar> militar);

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
        result = prime * result + ((dataServico == null) ? 0 : dataServico.hashCode());
        result = prime * result + ((funcao == null) ? 0 : funcao.hashCode());
        result = prime * result + folga;
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
        if (folga != other.folga)
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
        return "ServicoOperacional [id=" + id + ", dataServico=" + dataServico + ", funcao=" + funcao + ", folga="
                + folga + ", patenteEscalado=" + militar.getPatente()+ "]";
    }

}
