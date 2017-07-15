package cenarios;

import java.util.ArrayList;

import apostas.Aposta;

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

	    if (aposta.getPrevisao().equals("VAI ACONTECER")) {
		if (!ocorreu) {
		    sum += aposta.getValorAposta();
		} else {
		    alguemVenceu = true;
		}
	    } else {
		if (ocorreu) {
		    sum += aposta.getValorAposta();
		} else {
		    alguemVenceu = true;
		}
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

}
