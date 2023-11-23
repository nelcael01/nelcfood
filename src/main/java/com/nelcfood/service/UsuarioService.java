package com.nelcfood.service;

import com.nelcfood.api.dto.request.UsuarioAtualizarSenhaDTORequest;
import com.nelcfood.model.entities.Usuario;
import com.nelcfood.model.exception.NegocioException;
import com.nelcfood.model.exception.naoEncontrada.UsuarioNaoEncontradoException;
import com.nelcfood.model.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

  UsuarioRepository usuarioRepository;

  public List<Usuario> listar() {
    return usuarioRepository.findAll();
  }

  public Usuario buscar(Long id) {
    return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException());
  }

  @Transactional
  public Usuario salvar(Usuario usuario) {
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

}
