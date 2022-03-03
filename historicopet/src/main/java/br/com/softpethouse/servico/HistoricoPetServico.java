package br.com.softpethouse.servico;

import br.com.softpethouse.api.dto.HistoricoPetDto;
import br.com.softpethouse.api.resource.Paginacao;
import br.com.softpethouse.dominio.HistoricoPet;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Transactional
public class HistoricoPetServico {

    public String novoHistoricoPet(HistoricoPetDto dto) {
        HistoricoPet entidade = new HistoricoPet(dto);
        entidade.persist();
        return entidade.getId().toString();
    }

    public HistoricoPetDto atualizaHistoricoPet(int id, HistoricoPetDto dto) throws Exception {
        if (HistoricoPet.findByIdOptional(id).isPresent()) {
            HistoricoPet.update("idpet = '" + dto.getIdPet() + "', descricao = '" + dto.getDescricao() + "' where id = ?1", id);
            return dto;
        } else
            throw new Exception("Histórico pet ( " + id + " ) não encontrado!");
    }

    public HistoricoPetDto historicoPet(int id) {
        HistoricoPet historicoPet = HistoricoPet.findById(id);
        return new HistoricoPetDto(historicoPet.getId(), historicoPet.getIdPet(), historicoPet.getDescricao());
    }

    public List<HistoricoPetDto> historicosPet(Paginacao paginacao) {
        PanacheQuery<HistoricoPet> historicosPet = HistoricoPet.findAll();

        return historicosPet.page(paginacao.getIndex(), paginacao.getLimit()).stream().
                map(historicoPet -> new HistoricoPetDto(historicoPet.getId(), historicoPet.getIdPet(), historicoPet.getDescricao())).
                collect(Collectors.toList());
    }

    public boolean deletaHistoricoPet(int id) throws Exception {
        if (HistoricoPet.findByIdOptional(id).isPresent())
            return HistoricoPet.deleteById(id);
        else
            throw new Exception("Histórico pet ( " + id + " ) não encontrado!");
    }
}
