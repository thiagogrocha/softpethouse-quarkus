package br.com.softpethouse.api.dto;

public class PetDto {

    private int id;

    private int idUsuario;

    private String nome;

    private String especie;

    private String raca;

    public PetDto() {
    }

    public PetDto(int idUsuario, String nome, String especie, String raca) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
    }

    public PetDto(int id, int idUsuario, String nome, String especie, String raca) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
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
