package com.wildessilva.cursomc.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wildessilva.cursomc.domain.Cliente;
import com.wildessilva.cursomc.domain.ItemPedido;
import com.wildessilva.cursomc.domain.PagamentoComBoleto;
import com.wildessilva.cursomc.domain.Pedido;
import com.wildessilva.cursomc.domain.enums.EstadoPagamento;
import com.wildessilva.cursomc.repositories.ClienteRepository;
import com.wildessilva.cursomc.repositories.ItemPedidoRepository;
import com.wildessilva.cursomc.repositories.PagamentoRepository;
import com.wildessilva.cursomc.repositories.PedidoRepository;
import com.wildessilva.cursomc.security.UserSS;
import com.wildessilva.cursomc.services.exceptions.AuthorizationException;
import com.wildessilva.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;	
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo" + Pedido.class.getName()));	
	
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());			
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
	
	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
	    UserSS user = UserService.authenticated();
	    if(user == null) {
	        throw new AuthorizationException("Acesso negado");
	    }	    
	    
	    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
	    Cliente cliente = clienteService.find(user.getId());
	    return repo.findByCliente(cliente, pageRequest);
	}
	
	
	
}
