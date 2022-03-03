package br.com.softpethouse.dominio;

import br.com.softpethouse.api.dto.PetDto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@SequenceGenerator(name = "petSeq", sequenceName = "pet_id_seq", allocationSize = 1)
public class Pet extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "petSeq")
    private Integer id;

    private Integer idUsuario;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String especie;

    @NotEmpty
    private String raca;

    public Pet() {
    }

    public Pet(PetDto dto) {
        this.idUsuario = dto.getIdUsuario();
        this.nome = dto.getNome();
        this.especie = dto.getEspecie();
        this.raca = dto.getRaca();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }
}
