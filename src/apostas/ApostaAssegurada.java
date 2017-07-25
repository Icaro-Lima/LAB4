package apostas;

public class ApostaAssegurada extends Aposta {

	
	
	public ApostaAssegurada(String nomeApostador, int valorAposta, String previsao, String tipo) {
		super(nomeApostador, valorAposta, previsao);

		validar(tipo);
	}

	public void validar(String tipo) {
		if (this.nomeApostador == null || this.nomeApostador.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Erro no cadastro de aposta assegurada por " + tipo + ": Apostador nao pode ser vazio ou nulo");
		} else if (this.valorAposta <= 0) {
			throw new IllegalArgumentException(
					"Erro no cadastro de aposta assegurada por " + tipo + ": Valor nao pode ser menor ou igual a zero");
		} else if (this.previsao == null || this.previsao.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Erro no cadastro de aposta assegurada por " + tipo + ": Previsao nao pode ser vazia ou nula");
		} else if (!(this.previsao.equals("VAI ACONTECER") || this.previsao.equals("N VAI ACONTECER"))) {
			throw new IllegalArgumentException(
					"Erro no cadastro de aposta assegurada por " + tipo + ": Previsao invalida");
		}
	}

}
