package cenarios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CenarioBonusTeste {

	CenarioBonus cenarioBonus;

	/**
	 * Simplesmente inicializa um cenario bonus pra fazer pequenos testes.
	 */
	@Before
	public void Inicializa() {
		this.cenarioBonus = new CenarioBonus("Ícaro vai tirar 10,0 nesse lab.", 1, 1000);
	}

	/**
	 * Testa o toString do cenário bonus.
	 */
	@Test
	public void testToString() {
		assertEquals("Ícaro vai tirar 10,0 nesse lab. - Nao finalizado - R$ 10,00", this.cenarioBonus.toString());
	}

	/**
	 * Testa o total rateio desse cenário somado com o bônus dele.
	 */
	@Test
	public void testGetTotalRateioCenario() {
		this.cenarioBonus.cadastraAposta("Ícaro Dantas", 10000, "VAI ACONTECER");
		this.cenarioBonus.cadastraAposta("Monitor Mafra", 99999999, "N VAI ACONTECER");

		this.cenarioBonus.fecharAposta(true, 0.01);

		assertEquals((int) Math.ceil(99999999 * 0.99) + this.cenarioBonus.getBonus(),
				this.cenarioBonus.getTotalRateioCenario());
	}

}
