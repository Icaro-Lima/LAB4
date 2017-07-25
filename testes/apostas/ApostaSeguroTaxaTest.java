package apostas;

import org.junit.Test;

public class ApostaSeguroTaxaTest {

	/**
	 * Testa um construtor normal.
	 */
	@Test
	public void testApostaSeguro() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa("Ícaro", 1, "VAI ACONTECER", 1000);
	}
	
	/**
	 * Testa o caso do nomeAPostador nullo.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroNomeApostadorVazio0() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa(null, 1, "VAI ACONTECER", 1000);
	}
	
	/**
	 * Testa o caso do nomeAPostador simplesmente vazio.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroNomeApostadorVazio1() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa("", 1, "VAI ACONTECER", 1000);
	}
	
	/**
	 * Testa o caso do nomeAPostador vazio com espaços.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroNomeApostadorVazio2() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa("    ", 1, "VAI ACONTECER", 1000);
	}
	
	/**
	 * Testa o caso do valor == 0.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroTaxaMenorIgualZero0() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa("Ícaro", 0, "VAI ACONTECER", 1000);
	}
	
	/**
	 * Testa o caso do valor < 0.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroTaxaMenorIgualZero1() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa("Ícaro", -1, "VAI ACONTECER", 1000);
	}
	
	/**
	 * Testa o caso da previsão nula.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroTaxaDescricaoVazia0() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa("Ícaro", 1, null, 1000);
	}
	
	/**
	 * Testa o caso da previsão vazia.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroTaxaDescricaoVazia1() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa("Ícaro", 9, "", 1000);
	}
	
	/**
	 * Testa o caso da previsão vazia com espaços.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroTaxaDescricaoVazia2() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa("Ícaro", 11, "  ", 1000);
	}
	
	/**
	 * Testa o caso da previsão um pouco diferente.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroTaxaDescricaoErrada() {
		@SuppressWarnings("unused")
		ApostaSeguroTaxa apostaSeguroTaxa = new ApostaSeguroTaxa("Ícaro", 11, "N VAI ACONTECE", 1000);
	}

}
