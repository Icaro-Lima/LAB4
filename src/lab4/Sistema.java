package lab4;

import java.util.ArrayList;
import java.util.Collections;

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

	private String ordem;

	private ArrayList<Cenario> cenariosOrdenadosPeloCadastro;

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

		this.ordem = "Cadastro";

		this.cenariosOrdenadosPeloCadastro = new ArrayList<>();
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

		Cenario cenario = new Cenario(descricao, this.cenariosOrdenadosPeloCadastro.size() + 1);

		this.cenariosOrdenadosPeloCadastro.add(cenario);

		return this.cenariosOrdenadosPeloCadastro.size();
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

		CenarioBonus cenarioBonus = new CenarioBonus(descricao, this.cenariosOrdenadosPeloCadastro.size() + 1, bonus);

		this.cenariosOrdenadosPeloCadastro.add(cenarioBonus);

		this.caixa -= bonus;

		return this.cenariosOrdenadosPeloCadastro.size();
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
		return cenarioID >= 1 && this.cenariosOrdenadosPeloCadastro.size() >= cenarioID;
	}
	
	/**
	 * Retorna um objeto Cenario a partir do seu ID.
	 * 
	 * @param cenarioID
	 *            O identificador do cenário.
	 * @return Um objeto Cenario.
	 */
	private Cenario pegaCenario(int cenarioID) {
		return this.cenariosOrdenadosPeloCadastro.get(cenarioID - 1);
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

		return cenarioID + " - " + this.pegaCenario(cenarioID).toString();
	}

	/**
	 * Retorna a representação em String de todos os cenários do sistema, um por
	 * linha.
	 * 
	 * @return Uma String representando todos os cenários do sistema.
	 */
	public String exibirCenarios() {
		String texto = "";
		for (int i = 0; i < this.cenariosOrdenadosPeloCadastro.size(); i++) {
			texto += i + " - " + this.cenariosOrdenadosPeloCadastro.get(i).toString() + LINE_SEPARATOR;
		}

		return texto;
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
		}

		Cenario cenario = this.pegaCenario(cenarioID);
		cenario.cadastraAposta(apostador, valor, previsao);
	}

	/**
	 * Cadastra uma nova aposta com seguro valor.
	 * 
	 * @param cenarioID
	 *            O identificador do cenário.
	 * @param apostador
	 *            O nome do apostador.
	 * @param valor
	 *            O valor da aposta.
	 * @param previsao
	 *            A previsão do apostador, podendo ser: "VAI ACONTECER" e "N VAI
	 *            ACONTECER".
	 * @param valorSeguro
	 *            O valor do seguro.
	 * @param custoSeguro
	 *            O custo do seguro.
	 * @return Retorna o identificador da aposta que foi cadastrada.
	 */
	public int cadastrarApostaSeguraValor(int cenarioID, String apostador, int valor, String previsao, int valorSeguro,
			int custoSeguro) {
		if (cenarioID <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por valor: Cenario invalido");
		}

		this.caixa += custoSeguro;

		Cenario cenario = this.pegaCenario(cenarioID);
		return cenario.cadastrarApostaSeguraValor(apostador, valor, previsao, valorSeguro);
	}

	/**
	 * Cadastra uma nova aposta com seguro taxa. O valor que vai ser dado ao
	 * apostador caso ele perca é a taxa * valorAposta.
	 * 
	 * @param cenarioID
	 *            O identificador do cenário.
	 * @param apostador
	 *            O nome do apostador.
	 * @param valor
	 *            O valor da aposta.
	 * @param previsao
	 *            A previsão do apostador, podendo ser: "VAI ACONTECER" e "N VAI
	 *            ACONTECER".
	 * @param taxaSeguro
	 *            A taxa do seguro.
	 * @param custoSeguro
	 *            O custo do seguro.
	 * @return Retorna o identificador da aposta que foi cadastrada.
	 */
	public int cadastrarApostaSeguraTaxa(int cenarioID, String apostador, int valor, String previsao, double taxaSeguro,
			int custoSeguro) {
		if (cenarioID <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por taxa: Cenario invalido");
		}
		this.caixa += custoSeguro;

		Cenario cenario = this.pegaCenario(cenarioID);
		return cenario.cadastrarApostaSeguraTaxa(apostador, valor, previsao, taxaSeguro);
	}

	/**
	 * Altera uma apostaSeguroTaxa para uma apostaSeguroValor.
	 * 
	 * @param cenarioID
	 *            O identificador do cenário.
	 * @param apostaAssegurada
	 *            O identificador da aposta.
	 * @param seguroValor
	 *            O valor do seguro.
	 */
	public void alterarSeguroValor(int cenarioID, int apostaAssegurada, int seguroValor) {
		Cenario cenario = this.pegaCenario(cenarioID);
		cenario.alterarSeguroValor(apostaAssegurada, seguroValor);
	}

	/**
	 * Altera uma apostaSeguroValor para uma apostaSeguroTaxa.
	 * 
	 * @param cenarioID
	 *            O identificador do cenário.
	 * @param apostaAssegurada
	 *            O identificador da aposta.
	 * @param seguroTaxa
	 *            A taxa do seguro.
	 */
	public void alterarSeguroTaxa(int cenarioID, int apostaAssegurada, double seguroTaxa) {
		Cenario cenario = this.pegaCenario(cenarioID);
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

		return this.pegaCenario(cenarioID).valorTotalDeApostas();
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

		return this.pegaCenario(cenarioID).totalDeApostas();
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
		return this.pegaCenario(cenarioID).exibeApostas();
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

		Cenario cenario = this.pegaCenario(cenarioID);

		cenario.fecharAposta(ocorreu, taxa);

		this.caixa += cenario.getCaixaCenario();
		this.caixa -= cenario.getTotalValorAssegurado();
	}

	/**
	 * Determina uma ordem para o método exibirCenarioOrdenado.
	 * 
	 * @param ordem
	 *            A ordem na qual os cenários devem ser ordenados (Cadastro,
	 *            Nome, Apostas).
	 */
	public void alterarOrdem(String ordem) {
		this.ordem = ordem;
	}

	/**
	 * Exibe um cenário em uma ordem pré-definida pelo método alterarOrdem.
	 * 
	 * @param cenario
	 *            O identificador do cenário na nova ordem.
	 * @return Retorna uma String representando a exibição do cenário.
	 */
	String exibirCenarioOrdenado(int cenarioID) {
		if (this.ordem.equals("Cadastro")) {
			return cenarioID + " - " + this.pegaCenario(cenarioID).toString();
		} else {
			ArrayList<Cenario> listaOrdenada = new ArrayList<Cenario>(this.cenariosOrdenadosPeloCadastro);
			
			if (this.ordem.equals("Nome")) {
				Collections.sort(listaOrdenada, new NomeComparator());
			} else {
				Collections.sort(listaOrdenada, new ApostasComparator());
			}
			
			return cenarioID + " - " + listaOrdenada.get(cenarioID - 1);
		}
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

		return this.pegaCenario(cenarioID).getCaixaCenario();
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

		return this.pegaCenario(cenarioID).getTotalRateioCenario();
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
		return this.pegaCenario(cenarioID).getEstado();
	}

}
