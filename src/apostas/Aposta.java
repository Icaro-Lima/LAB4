package apostas;

/**
 * Representa uma aposta, uma aposta tem um nome de um apostador, um valor e uma
 * previsão ("VAI ACONTECER", "N VAI ACONTECER").
 * 
 * @author Ícaro Dantas
 *
 */
public class Aposta {

    private String nomeApostador;
    private int valorAposta;
    private String previsao;

    /**
     * Instancia uma nova aposta.
     * 
     * @param nomeApostador
     *            Nome do apostador.
     * @param valorAposta
     *            Valor da aposta.
     * @param previsao
     *            Previsão, podendo ser: "VAI ACONTECER" e "N VAI ACONTECER".
     */
    public Aposta(String nomeApostador, int valorAposta, String previsao) {
	this.nomeApostador = nomeApostador;
	this.valorAposta = valorAposta;
	this.previsao = previsao;
    }

    /**
     * Retorna a representação textual de uma aposta. Modelo: <br>"NOME APOSTADOR -
     * R$VALOR APOSTA - PREVISAO".</br>
     */
    @Override
    public String toString() {
	return this.nomeApostador + " - R$" + String.format("%.2f", this.valorAposta / 100.0) + " - " + this.previsao;
    }

    /**
     * @return Retorna uma String representando o nome do apostador.
     */
    public String getNomeApostador() {
	return nomeApostador;
    }

    /**
     * @return Retorna um inteiro representando o valor da aposta.
     */
    public int getValorAposta() {
	return this.valorAposta;
    }

    /**
     * @return Retorna uma String representando a previsão da aposta.
     */
    public String getPrevisao() {
	return this.previsao;
    }

}
