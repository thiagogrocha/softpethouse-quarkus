package br.com.softpethouse.servico;

import br.com.softpethouse.api.dto.PetDto;
import br.com.softpethouse.api.resource.Paginacao;
import br.com.softpethouse.dominio.Pet;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Transactional
public class PetServico {

    public String novoPet(PetDto dto) {
        Pet entidade = new Pet(dto);
        entidade.persist();
        return entidade.getId().toString();
    }

    public PetDto atualizaPet(int id, PetDto dto) throws Exception {
        if (Pet.findByIdOptional(id).isPresent()) {
            Pet.update("idusuario = '" + dto.getIdUsuario() + "', nome = '" + dto.getNome() + "', raca = '" + dto.getRaca() + "', especie = '" + dto.getEspecie() + "' where id = ?1", id);
            return dto;
        } else
            throw new Exception("Usuário ( " + id + " ) não encontrado!");
    }

    public PetDto pet(int id) {
        Pet pet = Pet.findById(id);
        return new PetDto(pet.getId(), pet.getIdUsuario(), pet.getNome(), pet.getEspecie(), pet.getRaca());
    }

    public List<PetDto> pets(Paginacao paginacao) {
        PanacheQuery<Pet> Pets = Pet.findAll();

        return Pets.page(paginacao.getIndex(), paginacao.getLimit()).stream().
                map(pet -> new PetDto(pet.getId(), pet.getIdUsuario(), pet.getNome(), pet.getEspecie(), pet.getRaca())).
                collect(Collectors.toList());
    }

    public boolean deletaPet(int id) throws Exception {
        if (Pet.findByIdOptional(id).isPresent())
            return Pet.deleteById(id);
        else
            throw new Exception("Usuário ( " + id + " ) não encontrado!");
    }
}
