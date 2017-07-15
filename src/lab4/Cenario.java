package lab4;

import java.util.ArrayList;

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

	protected ArrayList<Aposta> apostas;

	protected int caixaCenario;
	protected int totalRateioCenario;

	/**
	 * Instancia um novo cenário, um cenário tem uma descrição, um estado e uma
	 * lista de apostas.
	 * 
	 * @param descricao
	 *            A descrição do cenário.
	 */
	public Cenario(String descricao) {
		this.descricao = descricao;
		this.estado = "Nao finalizado";

		this.apostas = new ArrayList<>();

		this.caixaCenario = 0;
		this.totalRateioCenario = 0;
	}

	/**
	 * Retorna a representação textual de um cenário. Modelo: "DESCRICAO -
	 * ESTADO"
	 */
	@Override
	public String toString() {
		return String.format("%s - %s", this.descricao, this.estado);
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
		Aposta aposta = new Aposta(apostador, valor, previsao);
		this.apostas.add(aposta);
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

		int sum = 0, vencedores = 0;
		for (int i = 0; i < this.apostas.size(); i++) {
			Aposta aposta = this.apostas.get(i);

			if (aposta.getPrevisao().equals("VAI ACONTECER")) {
				if (!ocorreu) {
					sum += aposta.getValorAposta();
				} else {
					vencedores++;
				}
			} else {
				if (ocorreu) {
					sum += aposta.getValorAposta();
				} else {
					vencedores++;
				}
			}
		}

		this.caixaCenario = sum;

		if (vencedores > 0) {
			this.totalRateioCenario = (int) Math.ceil(sum * (1 - taxa));

			this.caixaCenario -= this.totalRateioCenario;
		} else {
			this.totalRateioCenario = 0; 
		}

	}

	/**
	 * @return Retorna um inteiro representando o caixa total do cenário.
	 */
	public int getCaixaCenario() {
		if (!this.estado.startsWith("Finalizado")) {
			throw new IllegalAccessError("Erro na consulta do caixa do cenario: Cenario ainda esta aberto");
		}
		
		return this.caixaCenario;
	}

	/**
	 * @return Retorna um inteiro representando o rateio total do cenário.
	 */
	public int getTotalRateioCenario() {
		if (!this.estado.startsWith("Finalizado")) {
			throw new IllegalAccessError("Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");
		}
		
		return this.totalRateioCenario;
	}

	/**
	 * @return Retorna a descrição em String.
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * 
	 * @return Retorna uma String representando o estado do cenário.
	 */
	public String getEstado() {
		return this.estado;
	}

}
