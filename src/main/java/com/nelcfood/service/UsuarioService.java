package com.nelcfood.service;

import com.nelcfood.api.dto.request.UsuarioAtualizarSenhaDTORequest;
import com.nelcfood.model.entities.Grupo;
import com.nelcfood.model.entities.Usuario;
import com.nelcfood.model.exception.NegocioException;
import com.nelcfood.model.exception.naoEncontrada.UsuarioNaoEncontradoException;
import com.nelcfood.model.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UsuarioService {

  UsuarioRepository usuarioRepository;
  GrupoService grupoService;
  //Deve estar no repository. Mas por algum bug, não está funcionando
  EntityManager manager;

  public List<Usuario> listar() {
    return usuarioRepository.findAll();
  }

  public Usuario buscar(Long id) {
    return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException());
  }

  @Transactional
  public Usuario salvar(Usuario usuario) {
    manager.detach(usuario);
    Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
    if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
      throw new NegocioException(String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
    }
    return usuarioRepository.save(usuario);
  }

  @Transactional
  public void atualizarSenha(Long id, UsuarioAtualizarSenhaDTORequest usuarioAtualizarSenhaDTORequest) {
    Usuario usuarioAtual = buscar(id);
    if (usuarioAtual.getSenha().equals(usuarioAtualizarSenhaDTORequest.getSenhaAtual())) {
      usuarioAtual.setSenha(usuarioAtualizarSenhaDTORequest.getNovaSenha());
      salvar(usuarioAtual);
    } else {
      throw new NegocioException("Senha atual não coincide com a senha do usuário.");
    }
  }

  @Transactional
  public void excluir(Long id) {
    usuarioRepository.deleteById(id);
  }

  public Set<Grupo> listarGrupos(Long usuarioId) {
    Usuario usuarioBuscado = buscar(usuarioId);
    return usuarioBuscado.getGrupos();
  }

  @Transactional
  public void associar(Long usuarioId, Long grupoId) {
    Usuario usuarioBuscado = buscar(usuarioId);
    Grupo grupoBuscado = grupoService.buscar(grupoId);
    usuarioBuscado.associar(grupoBuscado);
    salvar(usuarioBuscado);
  }

  @Transactional
  public void desasociar(Long usuarioId, Long grupoId) {
    Usuario usuarioBuscado = buscar(usuarioId);
    Grupo grupoBuscado = grupoService.buscar(grupoId);
    usuarioBuscado.desasociar(grupoBuscado);
    salvar(usuarioBuscado);
  }


}
