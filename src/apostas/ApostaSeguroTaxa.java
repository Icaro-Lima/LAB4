package apostas;

public class ApostaSeguroTaxa extends Aposta {

    private double taxaSeguro;
    
    public ApostaSeguroTaxa(String apostador, int valor, String previsao, double taxaSeguro) {
	super(apostador, valor, previsao);
	
	this.setTaxaSeguro(taxaSeguro);
    }

    public double getTaxaSeguro() {
	return taxaSeguro;
    }

    public void setTaxaSeguro(double taxaSeguro) {
	this.taxaSeguro = taxaSeguro;
    }
}
