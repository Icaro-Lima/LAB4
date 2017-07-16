package cenarios;

import java.util.ArrayList;

import apostas.Aposta;
import apostas.ApostaSeguroTaxa;
import apostas.ApostaSeguroValor;

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
	Aposta aposta = new Aposta(apostador, valor, previsao);
	this.apostas.add(aposta);
    }

    public int cadastrarApostaSeguraValor(String apostador, int valor, String previsao, int valorSeguro) {
	ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor(apostador, valor, previsao, valorSeguro);
	this.apostas.add(apostaSeguroValor);

	return this.apostas.size() - 1;
    }

    public int cadastrarApostaSeguraTaxa(String apostador, int valor, String previsao, double taxaSeguro) {
	ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa(apostador, valor, previsao, taxaSeguro);
	this.apostas.add(apostaSeguroTaxa);

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
	Aposta aposta = this.apostas.get(apostaAsseguradaID);
	ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor(aposta.getNomeApostador(), aposta.getValorAposta(),
		aposta.getPrevisao(), valorSeguro);

	this.apostas.add(apostaAsseguradaID, apostaSeguroValor);
	this.apostas.remove(apostaAsseguradaID + 1);
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
	Aposta aposta = this.apostas.get(apostaAsseguradaID);
	ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa(aposta.getNomeApostador(), aposta.getValorAposta(),
		aposta.getPrevisao(), taxaSeguro);

	this.apostas.add(apostaAsseguradaID, apostaSeguroTaxa);
	this.apostas.remove(apostaAsseguradaID + 1);
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

	int sum = 0;
	boolean alguemVenceu = false;
	for (int i = 0; i < this.apostas.size(); i++) {
	    Aposta aposta = this.apostas.get(i);

	    if ((aposta.getPrevisao().equals("VAI ACONTECER") && !ocorreu)
		    || (aposta.getPrevisao().equals("N VAI ACONTECER") && ocorreu)) {
		if (aposta instanceof ApostaSeguroValor) {
		    ApostaSeguroValor apostaSeguroValor = (ApostaSeguroValor) aposta;

		    setTotalValorAssegurado(getTotalValorAssegurado() + apostaSeguroValor.getValorSeguro());
		} else if (aposta instanceof ApostaSeguroTaxa) {
		    ApostaSeguroTaxa apostaSeguroTaxa = (ApostaSeguroTaxa) aposta;

		    setTotalValorAssegurado((int) (getTotalValorAssegurado()
			    + apostaSeguroTaxa.getTaxaSeguro() * apostaSeguroTaxa.getValorAposta()));
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

}
