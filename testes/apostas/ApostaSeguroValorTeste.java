package apostas;

import org.junit.Test;

public class ApostaSeguroValorTeste {

	@Test
	public void testApostaSeguro() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor("Ícaro", 1, "VAI ACONTECER", 1000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroNomeApostadorVazio0() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor(null, 1, "VAI ACONTECER", 1000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroNomeApostadorVazio1() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor("", 1, "VAI ACONTECER", 1000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroNomeApostadorVazio2() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor("    ", 1, "VAI ACONTECER", 1000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroValorMenorIgualZero0() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor("Ícaro", 0, "VAI ACONTECER", 1000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroValorMenorIgualZero1() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor("Ícaro", -1, "VAI ACONTECER", 1000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroValorDescricaoVazia0() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor("Ícaro", -1, null, 1000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroValorDescricaoVazia1() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor("Ícaro", -1, "", 1000);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testApostaSeguroValorDescricaoVazia2() {
		@SuppressWarnings("unused")
		ApostaSeguroValor apostaSeguroValor = new ApostaSeguroValor("Ícaro", -1, "  ", 1000);
	}

}
