package apostas;

import org.junit.Test;

public class ApostaSeguroTaxaTest {

	@Test
	public void testApostaSeguro() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa("Ícaro", 1, "VAI ACONTECER", 1000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroNomeApostadorVazio0() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa(null, 1, "VAI ACONTECER", 1000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroNomeApostadorVazio1() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa("", 1, "VAI ACONTECER", 1000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroNomeApostadorVazio2() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa("    ", 1, "VAI ACONTECER", 1000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroTaxaMenorIgualZero0() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa("Ícaro", 0, "VAI ACONTECER", 1000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroTaxaMenorIgualZero1() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa("Ícaro", -1, "VAI ACONTECER", 1000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroTaxaDescricaoVazia0() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa("Ícaro", -1, null, 1000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroTaxaDescricaoVazia1() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa("Ícaro", -1, "", 1000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroTaxaDescricaoVazia2() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa("Ícaro", -1, "  ", 1000);
	}

}
