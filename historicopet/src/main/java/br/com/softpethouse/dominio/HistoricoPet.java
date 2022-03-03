package br.com.softpethouse.dominio;

import br.com.softpethouse.api.dto.HistoricoPetDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@SequenceGenerator(name = "historicoPetSeq", sequenceName = "historicopet_id_seq", allocationSize = 1)
public class HistoricoPet extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historicoPetSeq")
    private Integer id;

    @NotNull
    private Integer idPet;

    @NotNull
    private String descricao;

    public HistoricoPet() {
    }

    public HistoricoPet(HistoricoPetDto dto) {
        this.idPet = dto.getIdPet();
        this.descricao = dto.getDescricao();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPet() {
        return idPet;
    }

    public void setIdPet(Integer idPet) {
        this.idPet = idPet;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}