package apostas;

public class ApostaAssegurada extends Aposta {

	private Seguro seguro;
	
	public ApostaAssegurada(String nomeApostador, int valorAposta, String previsao, int valor) {
		super(nomeApostador, valorAposta, previsao);

		this.seguro = new SeguroValor(valor);
	}
	
	public ApostaAssegurada(String nomeApostador, int valorAposta, String previsao, double taxa) {
		super(nomeApostador, valorAposta, previsao);
		
		this.seguro = new SeguroTaxa(taxa);
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
