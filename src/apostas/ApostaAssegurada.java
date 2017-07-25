package apostas;

public class ApostaAssegurada extends Aposta {

	private Seguro seguro;
	
	public ApostaAssegurada(String nomeApostador, int valorAposta, String previsao, int valor) {
		super(nomeApostador, valorAposta, previsao);

		validar("valor");
		
		this.seguro = new SeguroValor(valor);
	}
	
	public ApostaAssegurada(String nomeApostador, int valorAposta, String previsao, double taxa) {
		super(nomeApostador, valorAposta, previsao);

		validar("taxa");
		
		this.seguro = new SeguroTaxa(taxa);
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
	
	
	public int getValorSeguro() {
		if (this.seguro instanceof SeguroValor) {
			return ((SeguroValor) this.seguro).getValor();
		} else {
			return (int)(((SeguroTaxa) this.seguro).getTaxa() * this.valorAposta);
		}
	}

	public void alterarSeguroValor(int valorSeguro) {
		this.seguro = new SeguroValor(valorSeguro);
	}
	
	public void alterarSeguroTaxa(double taxaSeguro) {
		this.seguro = new SeguroTaxa(taxaSeguro);
	}

}
