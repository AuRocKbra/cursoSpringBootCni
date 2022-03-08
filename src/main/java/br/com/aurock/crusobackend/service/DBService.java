package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.*;
import br.com.aurock.crusobackend.domain.enuns.EstadoPagamento;
import br.com.aurock.crusobackend.domain.enuns.Perfil;
import br.com.aurock.crusobackend.domain.enuns.TipoCliente;
import br.com.aurock.crusobackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void instanciaDateBase() throws ParseException {
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
        Produto p4 = new Produto(null, "Mesa de escritório",300.00);
        Produto p5 = new Produto(null, "Toalha",50.00);
        Produto p6 = new Produto(null, "Colcha",200.00);
        Produto p7 = new Produto(null, "Tv true color",1200.00);
        Produto p8 = new Produto(null, "Roçadeira",800.00);
        Produto p9 = new Produto(null, "Abajur",100.00);
        Produto p10 = new Produto(null, "Pendente",180.00);
        Produto p11 = new Produto(null, "Shampoo",90.00);
        Produto p12 = new Produto(null, "Impressora",800.00);
        Produto p13 = new Produto(null, "Mouse",80.00);
        Produto p14 = new Produto(null, "Mesa de escritório",300.00);
        Produto p15 = new Produto(null, "Toalha",50.00);
        Produto p16 = new Produto(null, "Colcha",200.00);
        Produto p17 = new Produto(null, "Tv true color",1200.00);
        Produto p18 = new Produto(null, "Roçadeira",800.00);
        Produto p19 = new Produto(null, "Abajur",100.00);
        Produto p20 = new Produto(null, "Pendente",180.00);
        Produto p21 = new Produto(null, "Shampoo",90.00);
        Produto p22 = new Produto(null, "Impressora",800.00);
        Produto p23 = new Produto(null, "Mouse",80.00);
        Produto p24 = new Produto(null, "Mesa de escritório",300.00);
        Produto p25 = new Produto(null, "Toalha",50.00);
        Produto p26 = new Produto(null, "Colcha",200.00);
        Produto p27 = new Produto(null, "Tv true color",1200.00);
        Produto p28 = new Produto(null, "Roçadeira",800.00);
        Produto p29 = new Produto(null, "Abajur",100.00);
        Produto p30 = new Produto(null, "Pendente",180.00);
        Produto p31 = new Produto(null, "Shampoo",90.00);
        Produto p32 = new Produto(null, "Impressora",800.00);
        Produto p33 = new Produto(null, "Mouse",80.00);
        Produto p34 = new Produto(null, "Mesa de escritório",300.00);
        Produto p35 = new Produto(null, "Toalha",50.00);
        Produto p36 = new Produto(null, "Colcha",200.00);
        Produto p37 = new Produto(null, "Tv true color",1200.00);
        Produto p38 = new Produto(null, "Roçadeira",800.00);
        Produto p39 = new Produto(null, "Abajur",100.00);
        Produto p40 = new Produto(null, "Pendente",180.00);
        Produto p41 = new Produto(null, "Shampoo",90.00);
        Produto p42 = new Produto(null, "Impressora",800.00);
        Produto p43 = new Produto(null, "Mouse",80.00);
        Produto p44 = new Produto(null, "Mesa de escritório",300.00);
        Produto p45 = new Produto(null, "Toalha",50.00);
        Produto p46 = new Produto(null, "Colcha",200.00);
        Produto p47 = new Produto(null, "Tv true color",1200.00);
        Produto p48 = new Produto(null, "Roçadeira",800.00);
        Produto p49 = new Produto(null, "Abajur",100.00);
        Produto p50 = new Produto(null, "Pendente",180.00);




        cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3,p12,p13,p14,p15,p16,p17,p18,p19,p20,p21,p22,p23,p24,p25,p26,p27,p28,p29,p30,p31,p32,p33,p34,p35,p36
                ,p37,p38,p39,p40,p41,p42,p43,p44,p45,p46,p47,p48,p49,p50));
        cat2.getProdutos().addAll(Arrays.asList(p2,p4));
        cat3.getProdutos().addAll(Arrays.asList(p5,p6));
        cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
        cat5.getProdutos().add(p8);
        cat6.getProdutos().addAll(Arrays.asList(p9,p10));
        cat7.getProdutos().add(p11);

        p1.getCategorias().addAll(Arrays.asList(cat1,cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1,cat2,cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
        p4.getCategorias().add(cat2);
        p5.getCategorias().add(cat3);
        p6.getCategorias().add(cat3);
        p7.getCategorias().add(cat4);
        p8.getCategorias().add(cat5);
        p9.getCategorias().add(cat6);
        p10.getCategorias().add(cat6);
        p11.getCategorias().add(cat7);
        p12.getCategorias().add(cat1);
        p13.getCategorias().add(cat1);
        p14.getCategorias().add(cat1);
        p15.getCategorias().add(cat1);
        p16.getCategorias().add(cat1);
        p17.getCategorias().add(cat1);
        p18.getCategorias().add(cat1);
        p19.getCategorias().add(cat1);
        p20.getCategorias().add(cat1);
        p21.getCategorias().add(cat1);
        p22.getCategorias().add(cat1);
        p23.getCategorias().add(cat1);
        p24.getCategorias().add(cat1);
        p25.getCategorias().add(cat1);
        p26.getCategorias().add(cat1);
        p27.getCategorias().add(cat1);
        p28.getCategorias().add(cat1);
        p29.getCategorias().add(cat1);
        p30.getCategorias().add(cat1);
        p31.getCategorias().add(cat1);
        p32.getCategorias().add(cat1);
        p33.getCategorias().add(cat1);
        p34.getCategorias().add(cat1);
        p35.getCategorias().add(cat1);
        p36.getCategorias().add(cat1);
        p37.getCategorias().add(cat1);
        p38.getCategorias().add(cat1);
        p39.getCategorias().add(cat1);
        p40.getCategorias().add(cat1);
        p41.getCategorias().add(cat1);
        p42.getCategorias().add(cat1);
        p43.getCategorias().add(cat1);
        p44.getCategorias().add(cat1);
        p45.getCategorias().add(cat1);
        p46.getCategorias().add(cat1);
        p47.getCategorias().add(cat1);
        p48.getCategorias().add(cat1);
        p49.getCategorias().add(cat1);
        p50.getCategorias().add(cat1);

        Estado e1 = new Estado(null, "Minas Gerais");
        Estado e2 = new Estado(null,"São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia",e1);
        Cidade c2 = new Cidade(null,"São Paulo",e2);
        Cidade c3 = new Cidade(null,"Campinas",e2);

        Cliente cl1 = new Cliente(null,"Maria Silva", "maria@gmail.com","36378912377", TipoCliente.PESSOAFISICA, bCryptPasswordEncoder.encode("123456"));
        Cliente cl2 = new Cliente(null,"Mario Costa", "mario@gmail.com","63981896041", TipoCliente.PESSOAFISICA, bCryptPasswordEncoder.encode("123456"));
        cl2.addPerfis(Perfil.ADMIN);
        cl1.getTelefones().addAll(Arrays.asList("2736323","93838393"));
        cl2.getTelefones().addAll(Arrays.asList("12312321","523242323"));

        Endereco end1 = new Endereco(null,"Rua Flores","300","Apto 203","Jardim","38220834",cl1,c1);
        Endereco end2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","38777012",cl1,c2);
        Endereco end3 = new Endereco(null,"Avenida Matos","104",null,"Centro","38777012",cl2,c2);

        Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),cl1,end1);
        Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 19:35"),cl1,end2);
        Pagamento pag1 = new PagamentoCartao(null, EstadoPagamento.QUITADO,ped1,6);
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
        produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,p21,p22,p23,p24,p25,p26,p27,p28,p29,p30,p31,
                p32,p33,p34,p35,p36,p37,p38,p39,p40,p41,p42,p43,p44,p45,p46,p47,p48,p49,p50));
        estadoRepository.saveAll(Arrays.asList(e1,e2));
        cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
        clienteRepository.saveAll(Arrays.asList(cl1,cl2));
        enderecoRepository.saveAll(Arrays.asList(end1,end2,end3));
        pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
        pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));
        itemPedidoRepository.saveAll(Arrays.asList(item1,item2,item3));
    }
}
