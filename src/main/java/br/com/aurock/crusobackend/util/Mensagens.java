package br.com.aurock.crusobackend.util;

public class Mensagens {

    private Mensagens(){}

    public static final String MSG_SERVICE_BUSCA = "Serviço de busca de {}: Buscando por registro de {} = {}";
    public static final String MSG_RESULTADO_BUSCA = "Resultado da busca pelo {} = {} -> Valor encontrado ? {}";
    public static final String MSG_OBJECTO_NAO_ENCONTRADO = "Nenhum registro foi encontrado!";
    public static final String MSG_REQUISICAO_BUSCA = "Requisição de busca por {} recebida pelo resource {}";
    public static final String MSG_REQUISICAO_CRIACAO_OBJETO = "Requisição de criação recebida pelo resource {}";
    public static final String MSG_SERVICE_CRIA_OBJETO = "Serviço de criação de {}: Criando novo registro";
    public static final String MSG_SERVICE_ATUALIZA_OBJETO = "Serviço de atualização de {}: Atualizando objeto de ID = {}";
    public static final String MSG_SERVICE_CRIA_OBJETO_RESULTADO = "Resultado de criação do objeto {} -> Objeto criado ? {}";
    public static final String MSG_SERVICE_ATUALIZA_OBJETO_RESULTADO = "Resultado de atualização do objeto {} -> Objeto atualizado ? {}";
    public static final String MSG_OPERACAO_NAO_REALIZADA = "Um erro interno não permitiu que a operação fosse finalizada!";
    public static final String MSG_REQUISICAO_ATUALIZACAO_OBJETO = "Requisição de atualização recebida pelo resource {}";
    public static final String MSG_REQUISICAO_DELETE_OBJETO = "Requisição de deleção recebida pelo resource {}";
    public static final String MSG_SERVICE_DELETA_OBJETO = "Serviço de deleção de {}: Deletando objeto de ID = {}";
    public static final String MSG_OPERACAO_NAO_PERMITIDA = "Não é permitido deletar o objeto selecionado!";
    public static final String MSG_REQUISICAO_LISTAGEM = "Requisição de listagem recebida pelo resource {}";
    public static final String MSG_SERVICE_LISTAR_OBJETO = "Serviço de listagem de {}: Carregando objetos";
    public static final String MSG_RESULTADO_LISTAGEM = "Resultado da listagem -> Valor encontrado ? {}";
    public static final String MSG_SERVICE_LISTAR_PAGINADA_OBJETO = "Serviço de listagem de {} paginada: Carregando objetos";
    public static final String MSG_REQUISICAO_LISTAGEM_PAGINADA = "Requisição de listagem paginada recebida pelo resource {}";
    public static final String MSG_VALIDACAO_CAMPO = "Erro de validação de campo";
    public static final String MSG_CAMPO_VAZIO = "O campo não pode estar vazio";
    public static final String MSG_TAMANHO_STRING_PART1 = "O tamanho deve ser entre ";
    public static final String MSG_LIGACAO_FRASE = " e ";
    public static final String MSG_TAMANHO_STRING_PART2 = " caracteres!";
    public static final String MSG_VALIDACAO_EMAIL = "Email inválido";
    public static final String MSG_RESULTADO_DELETA = "Resultado da deleção de geristro -> Registro de ID = {} foi deletado ? {}";
    public static final String MSG_VALIDACAO_DADOS = "Processo de validação iniciado em {} -> Valor analisado {}";
    public static final String MSG_VALIDACAO_RESULTADO = "Resultado do processo de validação em {} -> Valor {} é válido ? {}";
    public static final String MSG_ERRO_ENVIO_EMAIL = "Falha ao enviar email de confirmação de pedido!";
    public static final String MSG_TITULO_EMAIL_CONFIRMACAO_PEDIDO = "Confirmação do pedido de número ";
    public static final String MSG_ARGUMENTO_INVALIDO = "Argumento inválido em {}";
    public static final String MSG_OBJETO_NAO_ENCONTRADO = "{} não foi encontrado!";
    public static final String MSG_ERRO_AUTENTICACAO = "Houve um erro no processo de autenticação";
    public static final String MSG_VALIDACAO_USUARIO = "Verificando se o usuário está logado!";
    public static final String MSG_USUARIO_NAO_LOGADO = "A requisição foi feita por um usuário não logado!";
    public static final String MSG_SERVICO_ESQUECI_SENHA = "Iniciando serviço de esqueci senha!";
    public static final String MSG_GERANDO_SENHA = "{} senha aleatória";
    public static final String MSG_ENVIANDO_EMAIL_NOVA_SENHA = "Enviando nova senha por email para {}";
    public static final String MSG_EMAIL_NOVA_SENHA = "Ola {0}, sua nova senha foi gerada, ela é {1}";
    public static final String MSG_TITULO_EMAIL_NOVA_SENHA = "Nova senha de acesso";
    public static final String MSG_CAMPO_OBRIGATORIO = "Campo obrigatório para preenchimento";
    public static final String MSG_UPLOAD_ARQUIVO = "{} upload do arquivo {}";
    public static final String MSG_ERRO_UPLOAD = "Erro ao processar arquivo {} - Erro : ";
    public static final String MSG_ACESSO_NEGADO = "Acesso negado para login {}";
    public static final String MSG_COVERTENDO_VALOR = "Iniciando processo de conversão de objetos de {} para {}";
}
