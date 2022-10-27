package com.wildessilva.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wildessilva.cursomc.domain.Categoria;
import com.wildessilva.cursomc.domain.Cidade;
import com.wildessilva.cursomc.domain.Cliente;
import com.wildessilva.cursomc.domain.Endereco;
import com.wildessilva.cursomc.domain.Estado;
import com.wildessilva.cursomc.domain.ItemPedido;
import com.wildessilva.cursomc.domain.Pagamento;
import com.wildessilva.cursomc.domain.PagamentoComBoleto;
import com.wildessilva.cursomc.domain.PagamentoComCartao;
import com.wildessilva.cursomc.domain.Pedido;
import com.wildessilva.cursomc.domain.Produto;
import com.wildessilva.cursomc.domain.enums.EstadoPagamento;
import com.wildessilva.cursomc.domain.enums.TipoCliente;
import com.wildessilva.cursomc.repositories.CategoriaRepository;
import com.wildessilva.cursomc.repositories.CidadeRepository;
import com.wildessilva.cursomc.repositories.ClienteRepository;
import com.wildessilva.cursomc.repositories.EnderecoRepository;
import com.wildessilva.cursomc.repositories.EstadoRepository;
import com.wildessilva.cursomc.repositories.ItemPedidoRepository;
import com.wildessilva.cursomc.repositories.PagamentoRepository;
import com.wildessilva.cursomc.repositories.PedidoRepository;
import com.wildessilva.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		
	}
	
}
