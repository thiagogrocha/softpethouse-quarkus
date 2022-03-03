package br.com.softpethouse.dominio;

import br.com.softpethouse.api.dto.UsuarioDto;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@SequenceGenerator(name = "usuarioSeq", sequenceName = "usuario_id_seq", allocationSize = 1)
public class Usuario extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioSeq")
    private Integer id;

    @NotEmpty
    @Email(message = "E-mail inválido!")
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String senha;

    @NotEmpty
    private String nome;

    public Usuario() {
    }

    public Usuario(UsuarioDto dto) {
        this.email = dto.getEmail();
        this.senha = dto.getSenha();
        this.nome = dto.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
