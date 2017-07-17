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
		assertEquals("Previsão difere.", "N VAI ACONTECER", aposta.getPrevisao());
	}

	/**
	 * Testa o toString de Aposta.
	 */
	@Test
	public void testToString() {
		Aposta aposta0 = new Aposta("Ícaro Lima", 10, "N VAI ACONTECER");
		assertEquals("Ícaro Lima - R$0,10 - N VAI ACONTECER", aposta0.toString());

		Aposta aposta1 = new Aposta("Ícaro Lima", 0, "VAI ACONTECER");
		assertEquals("Ícaro Lima - R$0,00 - VAI ACONTECER", aposta1.toString());
	}

}
