package br.com.aurock.crusobackend;

import br.com.aurock.crusobackend.domain.*;
import br.com.aurock.crusobackend.domain.enuns.TipoCliente;
import br.com.aurock.crusobackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

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

	public static void main(String[] args) {
		SpringApplication.run(CursoBackEndApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");

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

		e1.getCidades().add(c1);
		e2.getCidades().addAll(Arrays.asList(c2,c3));

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(e1,e2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		clienteRepository.save(cl1);
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
	}
}
