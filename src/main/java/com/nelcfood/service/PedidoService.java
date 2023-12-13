package com.nelcfood.service;

import com.nelcfood.model.entities.*;
import com.nelcfood.model.exception.NegocioException;
import com.nelcfood.model.exception.naoEncontrada.PedidoNaoEncontradoException;
import com.nelcfood.model.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PedidoService {
  private PedidoRepository pedidoRepository;
  private RestauranteService restauranteService;
  private FormaPagamentoService formaPagamentoService;
  private CidadeService cidadeService;
  private UsuarioService usuarioService;
  private ProdutoService produtoService;

  public List<Pedido> listar() {
    return pedidoRepository.findAll();
  }

  public Pedido buscar(Long id) {
    return pedidoRepository.findById(id).orElseThrow(() -> new PedidoNaoEncontradoException());
  }

  public Pedido salvar(Pedido pedido) {
    validarPedido(pedido);
    validarItens(pedido);

    pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
    pedido.calcularValorTotal();

    return pedidoRepository.save(pedido);
  }

  private void validarPedido(Pedido pedido) {
    Restaurante restaurante = restauranteService.buscar(pedido.getRestaurante().getId());
    FormaPagamento formaPagamento = formaPagamentoService.buscar(pedido.getFormaPagamento().getId());
    Cidade cidade = cidadeService.buscar(pedido.getEnderecoEntrega().getCidade().getId());
    Usuario cliente = usuarioService.buscar(pedido.getCliente().getId());

    pedido.getEnderecoEntrega().setCidade(cidade);
    pedido.setCliente(cliente);
    pedido.setRestaurante(restaurante);
    pedido.setFormaPagamento(formaPagamento);

    if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
      throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
              formaPagamento.getDescricao()));
    }
  }

  private void validarItens(Pedido pedido) {
    pedido.getItens().forEach(item -> {
      Produto produto = produtoService.buscar(
              pedido.getRestaurante().getId(), item.getProduto().getId());

      item.setPedido(pedido);
      item.setProduto(produto);
      item.setPrecoUnitario(produto.getPreco());
    });
  }

}
