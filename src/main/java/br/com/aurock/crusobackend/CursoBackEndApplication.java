package br.com.aurock.crusobackend;

import br.com.aurock.crusobackend.domain.*;
import br.com.aurock.crusobackend.domain.enuns.EstadoPagamento;
import br.com.aurock.crusobackend.domain.enuns.TipoCliente;
import br.com.aurock.crusobackend.repository.*;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.CancellationException;

@SpringBootApplication
public class CursoBackEndApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoBackEndApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		Categoria cat3 = new Categoria(null,"Limpeza");
		Categoria cat4 = new Categoria(null, "Verduras");
		Categoria cat5 = new Categoria(null, "Cozinha");
		Categoria cat6 = new Categoria(null,"Açougue");
		Categoria cat7 = new Categoria(null,"Adega");
		Categoria cat8 = new Categoria(null, "Cereais");
		Categoria cat9 = new Categoria(null,"Bomboniere");

		Produto p1 = new Produto(null, "Computador",2000.00);
		Produto p2 = new Produto(null, "Impressora",800.00);
		Produto p3 = new Produto(null, "Mouse",80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().add(p2);

		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().add(cat1);

		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null,"São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia",e1);
		Cidade c2 = new Cidade(null,"São Paulo",e2);
		Cidade c3 = new Cidade(null,"Campinas",e2);

		Cliente cl1 = new Cliente(null,"Maria Silva", "maria@gmail.com","36378912377", TipoCliente.PESSOAFISICA);
		cl1.getTelefones().addAll(Arrays.asList("2736323","93838393"));

		Endereco end1 = new Endereco(null,"Rua Flores","300","Apto 203","Jardim","38220834",cl1,c1);
		Endereco end2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","38777012",cl1,c2);

		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),cl1,end1);
		Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 19:35"),cl1,end2);
		Pagamento pag1 = new PagamentoCartao(null,EstadoPagamento.QUITADO,ped1,6);
		Pagamento pag2 = new PagamentoBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("20/10/2017 00:00"),null);
		ped1.setPagamento(pag1);
		ped2.setPagamento(pag2);
		cl1.getPedidos().addAll(Arrays.asList(ped1,ped2));

		ItemPedido item1 = new ItemPedido(ped1,p1,0.00,1,2000.00);
		ItemPedido item2 = new ItemPedido(ped1,p3,0.00,2,80.00);
		ItemPedido item3 = new ItemPedido(ped2,p2,100.00,1,800.00);

		ped1.getItens().addAll(Arrays.asList(item1,item2));
		ped2.getItens().add(item3);
		p1.getItens().add(item1);
		p2.getItens().add(item3);
		p3.getItens().add(item2);

		e1.getCidades().add(c1);
		e2.getCidades().addAll(Arrays.asList(c2,c3));

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(e1,e2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		clienteRepository.save(cl1);
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));
		itemPedidoRepository.saveAll(Arrays.asList(item1,item2,item3));
	}
}
