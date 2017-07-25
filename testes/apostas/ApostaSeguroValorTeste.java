package apostas;

import org.junit.Test;

public class ApostaSeguroValorTeste {

	/**
	 * Testa o funcionamento normal do construtor.
	 */
	@Test
	public void testApostaSeguro() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor("Ícaro", 1, "VAI ACONTECER", 1000);
	}
	
	/**
	 * Testa o caso do nomeAPostador nulo.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroNomeApostadorVazio0() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor(null, 1, "VAI ACONTECER", 1000);
	}
	
	/**
	 * Testa o caso do nomeApostador vazio.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroNomeApostadorVazio1() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor("", 1, "VAI ACONTECER", 1000);
	}
	
	/**
	 * Testa o caso do nomeApostador vazio com espaços.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroNomeApostadorVazio2() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor("    ", 1, "VAI ACONTECER", 1000);
	}
	
	/**
	 * Testa o caso do valor == 0.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroValorMenorIgualZero0() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor("Ícaro", 0, "VAI ACONTECER", 1000);
	}
	
	/**
	 * Testa o caso do valor < 0.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroValorMenorIgualZero1() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor("Ícaro", -1, "VAI ACONTECER", 1000);
	}
	
	/**
	 * Testa o caso da previsão nula.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroValorDescricaoVazia0() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor("Ícaro", 22, null, 1000);
	}
	
	/**
	 * Testa o caso da previsão vazia.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroValorDescricaoVazia1() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor("Ícaro", 33, "", 1000);
	}
	
	/**
	 * Testa o caso da previsão vazia com espaços.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroValorDescricaoVazia2() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor("Ícaro", 9, "  ", 1000);
	}
	
	/**
	 * Testa o caso da previsão um pouco diferente.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroValorDescricaoErrada() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor("Ícaro", 11, "N VAI ACONTECE", 1000);
	}

}
