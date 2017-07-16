package lab4;

import java.util.HashMap;

import cenarios.Cenario;
import cenarios.CenarioBonus;

/**
 * Representa a classe sistema, que controla praticamente a maior parte da
 * lógica das apostas. Um sistema tem um caixa, uma taxa e uma lista de
 * cenários. Cada cenário pode conter apostas e o sistema pode agir sobre eles.
 * 
 * @author Ícaro Dantas
 *
 */
public class Sistema {

    private final String LINE_SEPARATOR = System.lineSeparator();

    private int caixa;
    private double taxa;

    private HashMap<Integer, Cenario> cenarios;

    /**
     * Instancia um novo sistema.
     * 
     * @param caixa
     *            Uma quantidade inicial de caixa.
     * @param taxa
     *            Uma taxa para o caixa do sistema receber de cada aposta.
     */
    public Sistema(int caixa, double taxa) {
	if (caixa < 0) {
	    throw new IllegalArgumentException("Erro na inicializacao: Caixa nao pode ser inferior a 0");
	} else if (taxa < 0) {
	    throw new IllegalArgumentException("Erro na inicializacao: Taxa nao pode ser inferior a 0");
	}

	this.caixa = caixa;
	this.taxa = taxa;

	this.cenarios = new HashMap<Integer, Cenario>();
    }

    /**
     * Cadastra um cenário no sistema e retorna seu ID.
     * 
     * @param descricao
     *            A descrição do cenário.
     * @return Um inteiro representando o ID do cenário.
     */
    public int cadastrarCenario(String descricao) {
	if (descricao.trim().isEmpty()) {
	    throw new IllegalArgumentException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
	}

	Cenario cenario = new Cenario(descricao);

	this.cenarios.put(this.cenarios.size() + 1, cenario);

	return this.cenarios.size();
    }

    /**
     * Cadastra um cenário bônus no sistema e retorna seu ID.
     * 
     * @param descricao
     *            A descrição do cenário.
     * @param bonus
     *            O bonus a ser rateado pelos vencedores.
     * @return Um inteiro representando o ID do cenário.
     */
    public int cadastrarCenario(String descricao, int bonus) {
	if (descricao.trim().isEmpty()) {
	    throw new IllegalArgumentException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
	} else if (bonus <= 0) {
	    throw new IllegalArgumentException("Erro no cadastro de cenario: Bonus invalido");
	}

	CenarioBonus cenarioBonus = new CenarioBonus(descricao, bonus);

	this.cenarios.put(this.cenarios.size() + 1, cenarioBonus);

	this.caixa -= bonus;

	return this.cenarios.size();
    }

    /**
     * Retorna a representação textual do cenário dado.
     * 
     * @param cenarioID
     *            O ID do cenário.
     * @return Retorna uma String representando um cenário.
     */
    public String exibirCenario(int cenarioID) {
	if (cenarioID <= 0) {
	    throw new IllegalArgumentException("Erro na consulta de cenario: Cenario invalido");
	} else if (!this.contemCenario(cenarioID)) {
	    throw new NullPointerException("Erro na consulta de cenario: Cenario nao cadastrado");
	}

	return cenarioID + " - " + this.cenarios.get(cenarioID).toString();
    }

    /**
     * Retorna a representação em String de todos os cenários do sistema, um por
     * linha.
     * 
     * @return Uma String representando todos os cenários do sistema.
     */
    public String exibirCenarios() {
	String texto = "";
	for (int i = 1; i <= this.cenarios.size(); i++) {
	    texto += i + " - " + this.cenarios.get(i).toString() + LINE_SEPARATOR;
	}

	return texto;
    }

    /**
     * Verifica se existe um determinado cenário no sistema.
     * 
     * @param cenarioID
     *            O identificador do cenário.
     * @return Retorna true caso o cenário exista no sistema e false caso
     *         contrário.
     */
    private boolean contemCenario(int cenarioID) {
	return this.cenarios.keySet().contains(cenarioID);
    }

    /**
     * Cadastra uma nova aposta em algum determinado cenário, com uma
     * determinada previsão e um valor em centavos.
     * <ul>
     * Previsões possíveis:
     * <li>VAI ACONTECER</li>
     * <li>N VAI ACONTECER</li>
     * </ul>
     * 
     * @param cenarioID
     *            O identificador do cenário.
     * @param apostador
     *            O nome do apostador.
     * @param valor
     *            O valor em centavos a ser apostado.
     * @param previsao
     *            A previsão do apostador.
     */
    public void cadastrarAposta(int cenarioID, String apostador, int valor, String previsao) {
	if (cenarioID <= 0) {
	    throw new IllegalArgumentException("Erro no cadastro de aposta: Cenario invalido");
	} else if (!this.contemCenario(cenarioID)) {
	    throw new NullPointerException("Erro no cadastro de aposta: Cenario nao cadastrado");
	} else if (apostador == null || apostador.trim().isEmpty()) {
	    throw new IllegalArgumentException("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
	} else if (valor <= 0) {
	    throw new IllegalArgumentException("Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
	} else if (previsao == null || previsao.trim().isEmpty()) {
	    throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
	} else if (!(previsao.equals("VAI ACONTECER") || previsao.equals("N VAI ACONTECER"))) {
	    throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao invalida");
	}

	Cenario cenario = this.cenarios.get(cenarioID);
	cenario.cadastraAposta(apostador, valor, previsao);
    }

    public int cadastrarApostaSeguraValor(int cenarioID, String apostador, int valor, String previsao, int valorSeguro,
	    int custoSeguro) {
	this.caixa += custoSeguro;
	
	Cenario cenario = this.cenarios.get(cenarioID);
	return cenario.cadastrarApostaSeguraValor(apostador, valor, previsao, valorSeguro);
    }

    public int cadastrarApostaSeguraTaxa(int cenarioID, String apostador, int valor, String previsao, double taxaSeguro,
	    int custoSeguro) {
	this.caixa += custoSeguro;
	
	Cenario cenario = this.cenarios.get(cenarioID);
	return cenario.cadastrarApostaSeguraTaxa(apostador, valor, previsao, taxaSeguro);
    }

    public void alterarSeguroValor(int cenarioID, int apostaAssegurada, int seguroValor) {
	Cenario cenario = this.cenarios.get(cenarioID);
	cenario.alterarSeguroValor(apostaAssegurada, seguroValor);
    }
    
    public void alterarSeguroTaxa(int cenarioID, int apostaAssegurada, double seguroTaxa) {
	Cenario cenario = this.cenarios.get(cenarioID);
	cenario.alterarSeguroTaxa(apostaAssegurada, seguroTaxa);
    }

    /**
     * Retorna a soma de todas as apostas feitas em um determinado cenário.
     * 
     * @param cenarioID
     *            O identificador do cenário.
     * @return Retorna um inteiro representando a soma de todas as apostas
     *         feitas em um determinado cenário.
     */
    public int valorTotalDeApostas(int cenarioID) {
	if (cenarioID <= 0) {
	    throw new IllegalArgumentException("Erro na consulta do valor total de apostas: Cenario invalido");
	} else if (!this.contemCenario(cenarioID)) {
	    throw new NullPointerException("Erro na consulta do valor total de apostas: Cenario nao cadastrado");
	}

	return this.cenarios.get(cenarioID).valorTotalDeApostas();
    }

    /**
     * Retorna a quantidade de apostas feitas em um determinado cenário.
     * 
     * @param cenarioID
     *            O identificador do cenário.
     * @return Retorna um inteiro representando a quantidade de apostas em um
     *         determinado cenário.
     */
    public int totalDeApostas(int cenarioID) {
	if (cenarioID <= 0) {
	    throw new IllegalArgumentException("Erro na consulta do total de apostas: Cenario invalido");
	} else if (!this.contemCenario(cenarioID)) {
	    throw new NullPointerException("Erro na consulta do total de apostas: Cenario nao cadastrado");
	}

	return this.cenarios.get(cenarioID).totalDeApostas();
    }

    /**
     * Retorna uma representação textual de todas as apostas (uma por linha) em
     * um determinado cenário.
     * 
     * @param cenarioID
     *            O identificador do cenário.
     * @return Uma String representando todas as apostas, uma por linha.
     */
    public String exibeApostas(int cenarioID) {
	return this.cenarios.get(cenarioID).exibeApostas();
    }

    /**
     * Fecha as apostas de um determinado cenário e acrescenta o valor retornado
     * ao caixa do sistema.
     * 
     * @param cenarioID
     *            O identificador do cenário.
     * @param ocorreu
     *            Um booleano dizendo se o cenário ocorreu ou não.
     */
    public void fecharAposta(int cenarioID, boolean ocorreu) {
	if (cenarioID <= 0) {
	    throw new IllegalArgumentException("Erro ao fechar aposta: Cenario invalido");
	} else if (!this.contemCenario(cenarioID)) {
	    throw new NullPointerException("Erro ao fechar aposta: Cenario nao cadastrado");
	}

	Cenario cenario = this.cenarios.get(cenarioID);

	cenario.fecharAposta(ocorreu, taxa);

	this.caixa += cenario.getCaixaCenario();
	this.caixa -= cenario.getTotalValorAssegurado();
    }

    /**
     * Retorna o caixa de um determinado cenário.
     * 
     * @param cenarioID
     *            O identificador do cenário.
     * @return Retorna um inteiro representando o caixa de um determinado
     *         cenário.
     */
    public int getCaixaCenario(int cenarioID) {
	if (cenarioID <= 0) {
	    throw new IllegalArgumentException("Erro na consulta do caixa do cenario: Cenario invalido");
	} else if (!this.contemCenario(cenarioID)) {
	    throw new NullPointerException("Erro na consulta do caixa do cenario: Cenario nao cadastrado");
	}

	return this.cenarios.get(cenarioID).getCaixaCenario();
    }

    /**
     * Retorna o rateio total de um determinado cenário.
     * 
     * @param cenarioID
     *            O identificador do cenário.
     * @return Retorna um inteiro representando o rateio total de um determinado
     *         cenário.
     */
    public int getTotalRateioCenario(int cenarioID) {
	if (cenarioID <= 0) {
	    throw new IllegalArgumentException("Erro na consulta do total de rateio do cenario: Cenario invalido");
	} else if (!this.contemCenario(cenarioID)) {
	    throw new NullPointerException("Erro na consulta do total de rateio do cenario: Cenario nao cadastrado");
	}

	return this.cenarios.get(cenarioID).getTotalRateioCenario();
    }

    /**
     * Retorna o caixa atual do sistema.
     * 
     * @return Um inteiro representando o caixa atual do sistema.
     */
    public int getCaixa() {
	return this.caixa;
    }

    /**
     * Retorna o estado do cenário identificado.
     * 
     * @param cenarioID
     *            O identificador do cenário.
     * @return Uma String representando o estado do cenário.
     */
    public String getEstadoCenario(int cenarioID) {
	return this.cenarios.get(cenarioID).getEstado();
    }

}
