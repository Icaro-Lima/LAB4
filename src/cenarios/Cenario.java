package cenarios;

import java.util.ArrayList;

import apostas.Aposta;
import apostas.ApostaAssegurada;

/**
 * Essa classe representa um cenário. Um cenário pode ou não acontecer, ele tem
 * uma descrição e um estado que varia entre finalizado (ocorreu), finalizado
 * (não ocorreu) e não finalizado.
 * 
 * @author Ícaro Dantas
 *
 */
public class Cenario {

	private final String LINE_SEPARATOR = System.lineSeparator();

	private String descricao;
	private String estado;

	private ArrayList<Aposta> apostas;

	private int caixaCenario;
	private int totalRateioCenario;
	private int totalValorAssegurado;
	
	private int cadastroID;

	/**
	 * Instancia um novo cenário, um cenário tem uma descrição, um estado e uma
	 * lista de apostas.
	 * 
	 * @param descricao
	 *            A descrição do cenário.
	 */
	public Cenario(String descricao, int cadastroID) {
		this.descricao = descricao;
		this.estado = "Nao finalizado";

		this.apostas = new ArrayList<>();

		this.caixaCenario = 0;
		this.totalRateioCenario = 0;
		
		this.cadastroID = cadastroID;
	}

	/**
	 * Retorna a representação textual de um cenário. Modelo: "DESCRICAO -
	 * ESTADO"
	 */
	@Override
	public String toString() {
		return String.format("%s - %s", this.getDescricao(), this.getEstado());
	}

	/**
	 * Cadastra uma nova aposta.
	 * 
	 * @param apostador
	 *            O nome do apostador.
	 * @param valor
	 *            O valor da aposta em centavos.
	 * @param previsao
	 *            A previsão da aposta, que pode ser: "VAI ACONTECER" e "N VAI
	 *            ACONTECER".
	 */
	public void cadastraAposta(String apostador, int valor, String previsao) {
		if (apostador == null || apostador.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		} else if (valor <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
		} else if (previsao == null || previsao.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		} else if (!(previsao.equals("VAI ACONTECER") || previsao.equals("N VAI ACONTECER"))) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao invalida");
		}
		
		Aposta aposta = new Aposta(apostador, valor, previsao);
		this.apostas.add(aposta);
	}

	/**
	 * Cadastra uma nova aposta assegurada por valor, caso o apostador perca a
	 * aposta, o mesmo receberá um seguro de acordo com o valor definido.
	 * 
	 * @param apostador
	 *            O nome do apostador.
	 * @param valor
	 *            O valor da aposta em centavos.
	 * @param previsao
	 *            A previsão da aposta, que pode ser: "VAI ACONTECER" e "N VAI
	 *            ACONTECER".
	 * @param valorSeguro
	 *            O valor que será tirado do caixa do sistema e será dado ao
	 *            apostador caso ele perca.
	 */
	public int cadastrarApostaSeguraValor(String apostador, int valor, String previsao, int valorSeguro) {
		if (apostador == null || apostador.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Erro no cadastro de aposta assegurada por valor: Apostador nao pode ser vazio ou nulo");
		} else if (valor <= 0) {
			throw new IllegalArgumentException(
					"Erro no cadastro de aposta assegurada por valor: Valor nao pode ser menor ou igual a zero");
		} else if (previsao == null || previsao.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Erro no cadastro de aposta assegurada por valor: Previsao nao pode ser vazia ou nula");
		} else if (!(previsao.equals("VAI ACONTECER") || previsao.equals("N VAI ACONTECER"))) {
			throw new IllegalArgumentException(
					"Erro no cadastro de aposta assegurada por valor: Previsao invalida");
		}
		
		ApostaAssegurada apostaAssegurada = new ApostaAssegurada(apostador, valor, previsao, valorSeguro);
		this.apostas.add(apostaAssegurada);

		return this.apostas.size() - 1;
	}

	/**
	 * Cadastra uma nova aposta assegurada por taxa, caso o apostador perca a
	 * aposta, o mesmo receberá um seguro de acordo com a taxa * valor da
	 * aposta.
	 * 
	 * @param apostador
	 *            O nome do apostador.
	 * @param valor
	 *            O valor da aposta em centavos.
	 * @param previsao
	 *            A previsão da aposta, que pode ser: "VAI ACONTECER" e "N VAI
	 *            ACONTECER".
	 * @param taxaSeguro
	 *            A taxa que será multiplicada pelo valor da aposta, tirada do
	 *            caixa do sistema e será dado ao apostador caso ele perca.
	 */
	public int cadastrarApostaSeguraTaxa(String apostador, int valor, String previsao, double taxaSeguro) {
		if (apostador == null || apostador.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Erro no cadastro de aposta assegurada por taxa: Apostador nao pode ser vazio ou nulo");
		} else if (valor <= 0) {
			throw new IllegalArgumentException(
					"Erro no cadastro de aposta assegurada por taxa: Valor nao pode ser menor ou igual a zero");
		} else if (previsao == null || previsao.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Erro no cadastro de aposta assegurada por taxa: Previsao nao pode ser vazia ou nula");
		} else if (!(previsao.equals("VAI ACONTECER") || previsao.equals("N VAI ACONTECER"))) {
			throw new IllegalArgumentException(
					"Erro no cadastro de aposta assegurada por taxa: Previsao invalida");
		}
		
		ApostaAssegurada apostAssegurada = new ApostaAssegurada(apostador, valor, previsao, taxaSeguro);
		this.apostas.add(apostAssegurada);

		return this.apostas.size() - 1;
	}

	/**
	 * Converte uma aposta assegurada por taxa em aposta assegurada por valor.
	 * 
	 * @param apostaAsseguradaID
	 *            O identificador da aposta.
	 * @param taxaSeguro
	 *            O valor de seguro da aposta.
	 */
	public void alterarSeguroValor(int apostaAsseguradaID, int valorSeguro) {
		ApostaAssegurada apostaAssegurada = (ApostaAssegurada) this.apostas.get(apostaAsseguradaID);
		apostaAssegurada.alterarSeguroValor(valorSeguro);
	}

	/**
	 * Converte uma aposta assegurada por valor em aposta assegurada por taxa.
	 * 
	 * @param apostaAsseguradaID
	 *            O identificador da aposta.
	 * @param taxaSeguro
	 *            A taxa de seguro da aposta.
	 */
	public void alterarSeguroTaxa(int apostaAsseguradaID, double taxaSeguro) {
		ApostaAssegurada apostaAssegurada = (ApostaAssegurada) this.apostas.get(apostaAsseguradaID);
		apostaAssegurada.alterarSeguroTaxa(taxaSeguro);
	}

	/**
	 * Calcula e retorna a soma de todas as apostas feitas nesse cenário.
	 * 
	 * @return Um inteiro representando a soma de todas as apostas feitas nesse
	 *         cenário.
	 */
	public int valorTotalDeApostas() {
		int soma = 0;

		for (int i = 0; i < this.apostas.size(); i++) {
			soma += this.apostas.get(i).getValorAposta();
		}

		return soma;
	}

	/**
	 * Retorna a quantidade de apostas feitas no cenário.
	 * 
	 * @return Um inteiro representando a quantidade de apostas feitas no
	 *         cenário.
	 */
	public int totalDeApostas() {
		return this.apostas.size();
	}

	/**
	 * Retorna a representação textual de todas as apostas. Modelo:
	 * <ul>
	 * <li>NOME APOSTADOR 1 - R$VALOR APOSTA 1 - PREVISAO 1</li>
	 * </ul>
	 * 
	 * @return
	 */
	public String exibeApostas() {
		String texto = "";

		for (Aposta aposta : this.apostas) {
			texto += aposta.toString() + LINE_SEPARATOR;
		}

		return texto;
	}

	/**
	 * Apenas verifica se um dado apostador foi perdedor ou não de acordo com a
	 * ocorrência do cenário.
	 * 
	 * @param aposta
	 *            A Aposta em questão.
	 * @param ocorreu
	 *            Se o cenário ocorreu ou não.
	 * @return Retorna true caso o Apostador seja perdedor e false caso
	 *         contrário.
	 */
	private boolean isPerdedor(Aposta aposta, boolean ocorreu) {
		return (aposta.getPrevisao().equals("VAI ACONTECER") && !ocorreu)
				|| (aposta.getPrevisao().equals("N VAI ACONTECER") && ocorreu);
	}

	/**
	 * Fecha a possibilidade de novas apostas e já calcula o caixa do cenário e
	 * o total de rateio do cenário.
	 * 
	 * @param ocorreu
	 *            Se o cenário ocorreu ou não.
	 * @param taxa
	 *            A taxa para o caixa do sistema.
	 * 
	 * @return Retorna um inteiro representando quanto deve ser adicionado ao
	 *         caixa do sistema.
	 */
	public void fecharAposta(boolean ocorreu, double taxa) {
		if (this.estado.startsWith("Finalizado")) {
			throw new IllegalAccessError("Erro ao fechar aposta: Cenario ja esta fechado");
		}

		this.estado = ocorreu ? "Finalizado (ocorreu)" : "Finalizado (n ocorreu)";

		int sum = 0;
		boolean alguemVenceu = false;
		for (int i = 0; i < this.apostas.size(); i++) {
			Aposta aposta = this.apostas.get(i);

			if (this.isPerdedor(aposta, ocorreu)) {
				if (aposta instanceof ApostaAssegurada) {
					ApostaAssegurada apostaAssegurada = (ApostaAssegurada) aposta;
					setTotalValorAssegurado(getTotalValorAssegurado() + apostaAssegurada.getValorSeguro());
				}

				sum += aposta.getValorAposta();
			} else {
				alguemVenceu = true;
			}
		}

		int rateioBase = (int) Math.ceil(sum * (1 - taxa));

		if (alguemVenceu) {
			this.setCaixaCenario(sum - rateioBase);

			this.setTotalRateioCenario(rateioBase);
		} else {
			this.setTotalRateioCenario(0);
		}
	}

	private void setCaixaCenario(int novoValor) {
		this.caixaCenario = novoValor;
	}

	public int getCaixaCenario() {
		if (!this.getEstado().startsWith("Finalizado")) {
			throw new IllegalAccessError("Erro na consulta do caixa do cenario: Cenario ainda esta aberto");
		}

		return this.caixaCenario;
	}

	private void setTotalRateioCenario(int novoValor) {
		this.totalRateioCenario = novoValor;
	}

	public int getTotalRateioCenario() {
		if (!this.getEstado().startsWith("Finalizado")) {
			throw new IllegalAccessError("Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");
		}

		return this.totalRateioCenario;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public String getEstado() {
		return this.estado;
	}

	public int getTotalValorAssegurado() {
		return totalValorAssegurado;
	}

	private void setTotalValorAssegurado(int totalValorAssegurado) {
		this.totalValorAssegurado = totalValorAssegurado;
	}
	
	public int getTotalApostas() {
		return this.apostas.size();
	}
	
	public int getCadastroID() {
		return this.cadastroID;
	}

}
