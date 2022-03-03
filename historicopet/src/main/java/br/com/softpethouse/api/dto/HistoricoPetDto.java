package br.com.softpethouse.api.dto;

public class HistoricoPetDto {

    private int id;

    private int idPet;

    private String descricao;

    public HistoricoPetDto() {
    }

    public HistoricoPetDto(int id, int idPet, String descricao) {
        this.id = id;
        this.idPet = idPet;
        this.descricao = descricao;
    }

    public HistoricoPetDto(int idPet, String descricao) {
        this.idPet = idPet;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPet() {
        return idPet;
    }

    public void setIdPet(int idPet) {
        this.idPet = idPet;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
