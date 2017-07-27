package cenarios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CenarioTeste {

	Cenario cenario;

	/**
	 * Inicializa as variáveis que vão ser utilizadas nos testes.
	 */
	@Before
	public void initializar() {
		this.cenario = new Cenario("Ícaro vai tirar 10,0 nesse lab.", 1);
	}

	/**
	 * Testa o construtor do cenário.
	 */
	@Test
	public void testCenario() {
		assertEquals("Problema na inicialização ou no toString", "Ícaro vai tirar 10,0 nesse lab. - Nao finalizado",
				cenario.toString());
	}

	/**
	 * Testa o método toString e o método cadastraAposta.
	 */
	@Test
	public void testToStringECadastraAposta() {
		assertEquals("Problema no toString", "Ícaro vai tirar 10,0 nesse lab. - Nao finalizado",
				this.cenario.toString());
	}

	/**
	 * Testa a soma de todas as apostas cadastradas em um cenário.
	 */
	@Test
	public void testValorTotalDeApostas() {
		this.cenario.cadastraAposta("Ícaro Dantas", 10000, "VAI ACONTECER");
		this.cenario.cadastraAposta("Monitor Mafra", 99999999, "N VAI ACONTECER");

		assertEquals(10000 + 99999999, this.cenario.valorTotalDeApostas());
	}

	/**
	 * Testa o contador de apostas de um cenário.
	 */
	@Test
	public void testTotalDeApostas() {
		assertEquals(0, this.cenario.totalDeApostas());

		this.cenario.cadastraAposta("Ícaro Dantas", 10000, "VAI ACONTECER");
		this.cenario.cadastraAposta("Monitor Mafra", 99999999, "N VAI ACONTECER");

		assertEquals(2, this.cenario.totalDeApostas());
	}

	/**
	 * Testa a exibição de apostas.
	 */
	@Test
	public void testExibeApostas() {
		this.cenario.cadastraAposta("Ícaro Dantas", 10000, "VAI ACONTECER");
		this.cenario.cadastraAposta("Monitor Mafra", 99999999, "N VAI ACONTECER");

		assertEquals("",
				"Ícaro Dantas - R$100,00 - VAI ACONTECER" + System.lineSeparator()
						+ "Monitor Mafra - R$999999,99 - N VAI ACONTECER" + System.lineSeparator(),
				this.cenario.exibeApostas());
	}

	/**
	 * Testa os casos limites de fechar aposta.
	 */
	@Test
	public void testFecharAposta() {
		this.cenario.fecharAposta(true, 0.01);

		assertEquals(0, this.cenario.getCaixaCenario());
		assertEquals(0, this.cenario.getTotalRateioCenario());

		this.cenario = new Cenario("Ícaro vai tirar 10,0 nesse lab.", 2);
		this.cenario.cadastraAposta("Ícaro Dantas", 10000, "VAI ACONTECER");
		this.cenario.cadastraAposta("Monitor Mafra", 99999999, "N VAI ACONTECER");

		this.cenario.fecharAposta(true, 0.01);

		assertEquals((int) (99999999 * 0.01), this.cenario.getCaixaCenario());
		assertEquals((int) Math.ceil(99999999 * (1 - 0.01)), this.cenario.getTotalRateioCenario());
	}

}
