package apostas;

public class ApostaSeguroValor extends Aposta {
    
    private int valorSeguro;
    
    public ApostaSeguroValor(String apostador, int valor, String previsao, int valorSeguro) {
	super(apostador, valor, previsao);
	
	this.setValorSeguro(valorSeguro);
    }

    public int getValorSeguro() {
	return valorSeguro;
    }

    public void setValorSeguro(int valorSeguro) {
	this.valorSeguro = valorSeguro;
    }
    
}
