package apisemaperreio.escalante.ajudancia.domain.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import apisemaperreio.escalante.ajudancia.domain.valueObjs.Ausencia;
import apisemaperreio.escalante.ajudancia.domain.valueObjs.Email;
import apisemaperreio.escalante.ajudancia.domain.valueObjs.Endereco;
import apisemaperreio.escalante.ajudancia.domain.valueObjs.Nome;
import apisemaperreio.escalante.ajudancia.domain.valueObjs.Patente;
import apisemaperreio.escalante.ajudancia.domain.valueObjs.Sexo;
import apisemaperreio.escalante.ajudancia.domain.valueObjs.Telefone;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Militar {

    @Id
    @Column(length = 8, unique = true, nullable = false)
    private String matricula;

    @Column(length = 11, unique = true, nullable = false)
    private String cpf;

    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_nome")
    private Nome nome;

    @Column(nullable = false)
    private LocalDate nascimento;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Sexo sexo;

    @OneToMany(mappedBy = "militar", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Telefone> telefones = new HashSet<Telefone>();

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_email")
    private Email email;

    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_patente")
    private Patente patente;

    @Column(unique = true, nullable = false)
    private Integer antiguidade;

    private Integer folgaEspecial;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean escalavel;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean cov;

    @OneToMany(mappedBy = "militar", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ausencia> ausencias = new HashSet<Ausencia>();

    public Militar(String matricula, String cpf, Nome nome, LocalDate nascimento, Sexo sexo, Telefone telefone,
            Email email, Endereco endereco, Patente patente, Integer antiguidade, Integer folgaEspecial,
            Boolean escalavel, Boolean cov) {
        this.matricula = matricula;
        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.telefones.add(telefone);
        this.email = email;
        this.endereco = endereco;
        this.patente = patente;
        this.antiguidade = antiguidade;
        this.folgaEspecial = folgaEspecial;
        this.escalavel = escalavel;
        this.cov = cov;
    }

    public Militar() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Nome getNome() {
        return nome;
    }

    public void setNome(Nome nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Patente getPatente() {
        return patente;
    }

    public void setPatente(Patente patente) {
        this.patente = patente;
    }

    public Integer getAntiguidade() {
        return antiguidade;
    }

    public void setAntiguidade(Integer antiguidade) {
        this.antiguidade = antiguidade;
    }

    public Integer getFolgaEspecial() {
        return folgaEspecial;
    }

    public void setFolgaEspecial(Integer folgaEspecial) {
        this.folgaEspecial = folgaEspecial;
    }

    public Boolean getEscalavel() {
        return escalavel;
    }

    public void setEscalavel(Boolean escalavel) {
        this.escalavel = escalavel;
    }

    public Boolean getCov() {
        return cov;
    }

    public void setCov(Boolean cov) {
        this.cov = cov;
    }

    public Set<Ausencia> getAusencias() {
        return ausencias;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
        Militar other = (Militar) obj;
        if (matricula == null) {
            if (other.matricula != null)
                return false;
        } else if (!matricula.equals(other.matricula))
            return false;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Militar [matricula=" + matricula + ", nome=" + nome + ", sexo=" + sexo + ", patente=" + patente + "]";
    }

}
