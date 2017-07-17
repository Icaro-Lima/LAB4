package cenarios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CenarioBonusTeste {

	CenarioBonus cenarioBonus;

	@Before
	public void Inicializa() {
		this.cenarioBonus = new CenarioBonus("Ícaro vai tirar 10,0 nesse lab.", 1000);
	}

	@Test
	public void testToString() {
		assertEquals("Ícaro vai tirar 10,0 nesse lab. - Nao finalizado - R$ 10,00", this.cenarioBonus.toString());
	}

	@Test
	public void testGetTotalRateioCenario() {
		this.cenarioBonus.cadastraAposta("Ícaro Dantas", 10000, "VAI ACONTECER");
		this.cenarioBonus.cadastraAposta("Monitor Mafra", 99999999, "N VAI ACONTECER");

		this.cenarioBonus.fecharAposta(true, 0.01);

		assertEquals((int) Math.ceil(99999999 * 0.99) + this.cenarioBonus.getBonus(),
				this.cenarioBonus.getTotalRateioCenario());
	}

}
