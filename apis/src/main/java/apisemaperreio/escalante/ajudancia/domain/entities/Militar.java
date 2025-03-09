package apisemaperreio.escalante.ajudancia.domain.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import apisemaperreio.escalante.ajudancia.domain.valueObjs.Ausencia;
import apisemaperreio.escalante.ajudancia.domain.valueObjs.DadoPatente;
import apisemaperreio.escalante.ajudancia.domain.valueObjs.Email;
import apisemaperreio.escalante.ajudancia.domain.valueObjs.Endereco;
import apisemaperreio.escalante.ajudancia.domain.valueObjs.Nome;
import apisemaperreio.escalante.ajudancia.domain.valueObjs.Patente;
import apisemaperreio.escalante.ajudancia.domain.valueObjs.Sexo;
import apisemaperreio.escalante.ajudancia.domain.valueObjs.Telefone;
import apisemaperreio.escalante.ajudancia.services.dtos.requestDtos.MilitarRequest;
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

    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_telefone")
    private Telefone telefone;

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

    private int folgaEspecial;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean escalavel;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean cov;

    @OneToMany(mappedBy = "militar", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ausencia> ausencias = new HashSet<Ausencia>();

    public Militar(String matricula, String cpf, Nome nome, LocalDate nascimento, Sexo sexo, Telefone telefone,
            Email email, Endereco endereco, Patente patente, Integer antiguidade, int folgaEspecial,
            Boolean escalavel, Boolean cov) {
        this.matricula = matricula;
        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.patente = patente;
        this.antiguidade = antiguidade;
        this.folgaEspecial = patente.getFolgaEspecial().compareTo(folgaEspecial) > 0 ? patente.getFolgaEspecial()
                : folgaEspecial;
        this.escalavel = escalavel;
        this.cov = cov;
    }

    public Militar(MilitarRequest militarRequest) {
        this.matricula = militarRequest.matricula();
        this.cpf = militarRequest.cpf();
        this.nome = new Nome(militarRequest.nome().nomeCompleto(), militarRequest.nome().nomePaz());
        this.nascimento = militarRequest.nascimento();
        this.sexo = Sexo.valueOf(militarRequest.sexo());
        this.telefone = new Telefone(militarRequest.telefone().ddd1(), militarRequest.telefone().numero1(),
                militarRequest.telefone().ddd2(), militarRequest.telefone().numero2());
        this.email = new Email(militarRequest.email().profissional(), militarRequest.email().pessoal());
        this.endereco = new Endereco(militarRequest.endereco().logradouro(), militarRequest.endereco().numero(),
                militarRequest.endereco().complemento(), militarRequest.endereco().bairro(),
                militarRequest.endereco().cep(), militarRequest.endereco().municipio(),
                militarRequest.endereco().siglaUf());
        this.patente = new Patente(DadoPatente.valueOf(militarRequest.patente().nomePatente()),
                militarRequest.patente().folgaEspecial());
        this.antiguidade = militarRequest.antiguidade();
        this.folgaEspecial = militarRequest.patente().folgaEspecial().compareTo(militarRequest.folgaEspecial()) > 0
                ? militarRequest.patente().folgaEspecial()
                : militarRequest.folgaEspecial();
        this.escalavel = militarRequest.escalavel();
        this.cov = militarRequest.cov();
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

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
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

    public int getFolgaEspecial() {
        return folgaEspecial;
    }

    public void setFolgaEspecial(int folgaEspecial) {
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
        return "Militar [matricula=" + matricula + ", nome=" + nome.getNomePaz() + ", sexo=" + sexo + ", patente=" + patente.getDadoPatente().getNome() + "]";
    }

}
