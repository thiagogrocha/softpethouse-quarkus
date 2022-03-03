package br.com.softpethouse.servico;

import br.com.softpethouse.api.dto.UsuarioDto;
import br.com.softpethouse.api.resource.Paginacao;
import br.com.softpethouse.dominio.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Transactional
public class UsuarioServico {

    public String novoUsuario(UsuarioDto dto) throws Exception {
        if (!Usuario.find("email", dto.getEmail()).firstResultOptional().isPresent()) {
            Usuario entidade = new Usuario(dto);
            entidade.persist();
            return entidade.getId().toString();
        } else throw new Exception("O e-mail informado ( " + dto.getEmail() + " ) já existe!");
    }

    public UsuarioDto atualizaUsuario(int id, UsuarioDto dto) throws Exception {
        if (Usuario.findByIdOptional(id).isPresent()) {
            Usuario.update("email = '" + dto.getEmail() + "', senha = '" + dto.getSenha() + "', nome = '" + dto.getNome() + "' where id = ?1", id);
            return dto;
        } else
            throw new Exception("Usuário ( " + id + " ) não encontrado!");
    }

    public UsuarioDto usuario(int id) {
        Usuario usuario = Usuario.findById(id);
        return new UsuarioDto(usuario.getEmail(), usuario.getSenha(), usuario.getNome());
    }

    public List<UsuarioDto> usuarios(Paginacao paginacao) {
        PanacheQuery<Usuario> usuarios = Usuario.findAll();

        return usuarios.page(paginacao.getIndex(), paginacao.getLimit()).stream().
                map(usuario -> new UsuarioDto(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getNome())).
                collect(Collectors.toList());
    }

    public boolean deletaUsuario(int id) throws Exception {
        if (Usuario.findByIdOptional(id).isPresent())
            return Usuario.deleteById(id);
        else
            throw new Exception("Usuário ( " + id + " ) não encontrado!");
    }
}
