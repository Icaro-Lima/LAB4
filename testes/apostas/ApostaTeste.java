package apostas;

import static org.junit.Assert.*;

import org.junit.Test;

public class ApostaTeste {

	/**
	 * Testando o construtor, que querendo ou não, testa também os getters.
	 */
	@Test
	public void testAposta() {
		Aposta aposta = new Aposta("Ícaro Lima", 10, "N VAI ACONTECER");
		assertEquals("Nome difere.", "Ícaro Lima", aposta.getNomeApostador());
		assertEquals("Valor da aposta difere.", 10, aposta.getValorAposta());
		assertEquals("Previsão difere.", "N VAI ACONTECER",
				aposta.getPrevisao());
	}

	/**
	 * Testa o toString de Aposta.
	 */
	@Test
	public void testToString() {
		Aposta aposta0 = new Aposta("Ícaro Lima", 10, "N VAI ACONTECER");
		assertEquals("toString com problemas.",
				"Ícaro Lima - R$0,10 - N VAI ACONTECER", aposta0.toString());

		Aposta aposta1 = new Aposta("Ícaro Lima", 1, "VAI ACONTECER");
		assertEquals("toStrng com problemas.",
				"Ícaro Lima - R$0,01 - VAI ACONTECER", aposta1.toString());
	}

	/**
	 * Testa o caso do nomeApostador null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testApostaSeguroNomeApostadorVazio0() {
		@SuppressWarnings("unused")
		Aposta aposta = new Aposta(null, 1, "VAI ACONTECER");
	}

	/**
	 * Testa o caso do nomeApostador vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testApostaSeguroNomeApostadorVazio1() {
		@SuppressWarnings("unused")
		Aposta aposta = new Aposta("", 1, "VAI ACONTECER");
	}

	/**
	 * Testa o caso do nomeApostador vazio com espaços.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testApostaSeguroNomeApostadorVazio2() {
		@SuppressWarnings("unused")
		Aposta aposta = new Aposta("    ", 1, "VAI ACONTECER");
	}

	/**
	 * Testa o caso do valor == 0.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testApostaMenorIgualZero0() {
		@SuppressWarnings("unused")
		Aposta aposta = new Aposta("Ícaro", 0, "VAI ACONTECER");
	}

	/**
	 * Testa o caso do valor < 0.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testApostaMenorIgualZero1() {
		@SuppressWarnings("unused")
		Aposta aposta = new Aposta("Ícaro", -1, "VAI ACONTECER");
	}

	/**
	 * Testa o caso da previsão nula.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testApostaDescricaoVazia0() {
		@SuppressWarnings("unused")
		Aposta aposta = new Aposta("Ícaro", 10, null);
	}

	/**
	 * Testa o caso da previsão vazia.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testApostaDescricaoVazia1() {
		@SuppressWarnings("unused")
		Aposta aposta = new Aposta("Ícaro", 33, "");
	}

	/**
	 * Testa o caso da preisão vazia com espaços.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testApostaDescricaoVazia2() {
		@SuppressWarnings("unused")
		Aposta aposta = new Aposta("Ícaro", 50, "  ");
	}

	/**
	 * Testa o caso da previsão um pouco diferente.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testApostaDescricaoErrada() {
		@SuppressWarnings("unused")
		Aposta aposta = new Aposta("Ícaro", 50, "VAI ACONTECERR");
	}

}
