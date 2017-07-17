package apostas;

public class ApostaSeguroTaxa extends Aposta {

	private double taxaSeguro;

	public ApostaSeguroTaxa(String apostador, int valor, String previsao, double taxaSeguro) {		
		super(apostador, valor, previsao);
		
		this.setTaxaSeguro(taxaSeguro);
	}
	
	@Override
	public void validar() {
		if (this.nomeApostador == null || this.nomeApostador.trim().isEmpty()) {
			throw new IllegalAccessError(
					"Erro no cadastro de aposta assegurada por taxa: Apostador nao pode ser vazio ou nulo");
		} else if (this.valorAposta <= 0) {
			throw new IllegalAccessError(
					"Erro no cadastro de aposta assegurada por taxa: Valor nao pode ser menor ou igual a zero");
		} else if (this.previsao == null || this.previsao.trim().isEmpty()) {
			throw new IllegalAccessError("Erro no cadastro de aposta assegurada por taxa: Previsao nao pode ser vazia ou nula");
		} else if (!(this.previsao.equals("VAI ACONTECER") || this.previsao.equals("N VAI ACONTECER"))) {
			throw new IllegalAccessError("Erro no cadastro de aposta assegurada por taxa: Previsao invalida");
		}
	}

	public double getTaxaSeguro() {
		return taxaSeguro;
	}

	public void setTaxaSeguro(double taxaSeguro) {
		this.taxaSeguro = taxaSeguro;
	}
}
