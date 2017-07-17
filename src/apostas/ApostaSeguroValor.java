package apostas;

public class ApostaSeguroValor extends Aposta {

	private int valorSeguro;

	public ApostaSeguroValor(String apostador, int valor, String previsao, int valorSeguro) {
		super(apostador, valor, previsao);

		this.setValorSeguro(valorSeguro);
	}

	@Override
	public void validar() {
		if (this.nomeApostador == null || this.nomeApostador.trim().isEmpty()) {
			throw new IllegalAccessError(
					"Erro no cadastro de aposta assegurada por valor: Apostador nao pode ser vazio ou nulo");
		} else if (this.valorAposta <= 0) {
			throw new IllegalAccessError(
					"Erro no cadastro de aposta assegurada por valor: Valor nao pode ser menor ou igual a zero");
		} else if (this.previsao == null || this.previsao.trim().isEmpty()) {
			throw new IllegalAccessError("Erro no cadastro de aposta assegurada por valor: Previsao nao pode ser vazia ou nula");
		} else if (!(this.previsao.equals("VAI ACONTECER") || this.previsao.equals("N VAI ACONTECER"))) {
			throw new IllegalAccessError("Erro no cadastro de aposta assegurada por valor: Previsao invalida");
		}
	}

	public int getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(int valorSeguro) {
		this.valorSeguro = valorSeguro;
	}

}
