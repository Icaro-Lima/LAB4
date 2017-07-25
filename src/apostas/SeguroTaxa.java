package apostas;

public class SeguroTaxa extends Seguro {
	
	private double taxa;
	
	public SeguroTaxa(double taxa) {
		this.taxa = taxa;
	}
	
	public double getTaxa() {
		return this.taxa;
	}
	
}
