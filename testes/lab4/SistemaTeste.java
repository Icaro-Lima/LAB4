package lab4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SistemaTeste {

    Sistema sistema;

    /**
     * Inicializa as variáveis.
     */
    @Before
    public void inicializar() {
	this.sistema = new Sistema(0, 0.01);
    }

    /**
     * Testa os métodos: cadastrarCenario, exibirCenario e fecharAposta.
     */
    @Test
    public void testCadastrarCenarioEExibirCenarioEFecharAposta() {
	int cenarioID = this.sistema.cadastrarCenario("A Red Cannids vai pro mundial.");
	assertEquals("O id do cenário não corresponde.", 1, cenarioID);
	assertEquals("1 - A Red Cannids vai pro mundial. - Não finalizado", this.sistema.exibirCenario(1));

	cenarioID = this.sistema.cadastrarCenario("Meu roteador não vai pifar hoje.");
	assertEquals("O id do cenário não corresponde.", 2, cenarioID);
	this.sistema.fecharAposta(2, false);
	assertEquals("2 - Meu roteador não vai pifar hoje. - Finalizado (n ocorreu)", this.sistema.exibirCenario(2));

	cenarioID = this.sistema.cadastrarCenario("Temer cairá.");
	assertEquals("O id do cenário não corresponde.", 3, cenarioID);
	this.sistema.fecharAposta(3, true);
	assertEquals("3 - Temer cairá. - Finalizado (ocorreu)", this.sistema.exibirCenario(3));
    }

    /**
     * Testa o método exibirCenarios (que deve retornar a representação textual
     * de todos os cenários no sistema).
     */
    @Test
    public void testExibirCenarios() {
	this.sistema.cadastrarCenario("A Red Cannids vai pro mundial.");

	this.sistema.cadastrarCenario("Meu roteador não vai pifar hoje.");
	this.sistema.fecharAposta(2, true);

	this.sistema.cadastrarCenario("Temer cairá.");
	this.sistema.fecharAposta(3, false);

	assertEquals(
		"1 - A Red Cannids vai pro mundial. - Nao finalizado" + System.lineSeparator()
			+ "2 - Meu roteador não vai pifar hoje. - Finalizado (ocorreu)" + System.lineSeparator()
			+ "3 - Temer cairá. - Finalizado (n ocorreu)" + System.lineSeparator(),
		this.sistema.exibirCenarios());
    }

    /**
     * Testa o método valorTotalDeApostas.
     */
    @Test
    public void testValorTotalDeApostas() {
	this.sistema.cadastrarCenario("Algo.");

	assertEquals(0, this.sistema.valorTotalDeApostas(1));

	this.sistema.cadastrarAposta(1, "Ícaro Dantas", 10000, "VAI ACONTECER");
	this.sistema.cadastrarAposta(1, "Monitor Mafra", 99999999, "N VAI ACONTECER");

	assertEquals(10000 + 99999999, this.sistema.valorTotalDeApostas(1));
    }

    /**
     * Testa o método totalDeApostas.
     */
    @Test
    public void testTotalDeApostas() {
	this.sistema.cadastrarCenario("Algo.");

	assertEquals(0, this.sistema.totalDeApostas(1));

	this.sistema.cadastrarAposta(1, "Ícaro Dantas", 10000, "VAI ACONTECER");
	this.sistema.cadastrarAposta(1, "Monitor Mafra", 99999999, "N VAI ACONTECER");

	assertEquals(2, this.sistema.totalDeApostas(1));
    }

    /**
     * Testa o método cadastraAposta e o método exibeApostas.
     */
    @Test
    public void testCadastraApostaEExibeApostas() {
	this.sistema.cadastrarCenario("Isso não vai importar exatamente agora.");

	this.sistema.cadastrarAposta(1, "Ícaro Dantas", 10000, "VAI ACONTECER");
	this.sistema.cadastrarAposta(1, "Monitor Mafra", 99999999, "N VAI ACONTECER");

	assertEquals("",
		"Ícaro Dantas - R$100,00 - VAI ACONTECER" + System.lineSeparator()
			+ "Monitor Mafra - R$999999,99 - N VAI ACONTECER" + System.lineSeparator(),
		this.sistema.exibeApostas(1));
    }

    /**
     * Testa os métodos getCaixaCenario e getTotalRateioCenario.
     */
    @Test
    public void testGetCaixaCenarioEGetTotalRateioCenario() {
	this.sistema.cadastrarCenario("A Red Cannids vai pro mundial.");
	this.sistema.cadastrarAposta(1, "Ícaro Dantas", 10000, "VAI ACONTECER");
	this.sistema.cadastrarAposta(1, "Monitor Mafra", 99999999, "N VAI ACONTECER");
	this.sistema.fecharAposta(1, true);
	assertEquals(99999999, this.sistema.getCaixaCenario(1));
	assertEquals((int) Math.ceil(99999999 * (1 - 0.01)), this.sistema.getTotalRateioCenario(1));

	this.sistema.cadastrarCenario("Leruado.");
	this.sistema.cadastrarAposta(2, "Ícaro Dantas", 10000, "VAI ACONTECER");
	this.sistema.cadastrarAposta(2, "Monitor Mafra", 999999, "VAI ACONTECER");
	this.sistema.fecharAposta(2, true);
	assertEquals(0, this.sistema.getCaixaCenario(2));
	assertEquals(0, this.sistema.getTotalRateioCenario(2));

	this.sistema.cadastrarCenario("Ardrak.");
	this.sistema.cadastrarAposta(3, "Ícaro Dantas", 10000, "VAI ACONTECER");
	this.sistema.cadastrarAposta(3, "Monitor Mafra", 999999, "VAI ACONTECER");
	this.sistema.fecharAposta(3, false);
	assertEquals(10000 + 999999, this.sistema.getCaixaCenario(3));
	assertEquals(0, this.sistema.getTotalRateioCenario(3));

	assertEquals((int) (99999999 * 0.01) + 10000 + 999999, this.sistema.getCaixa());
    }

    /**
     * Testa o método getCaixa.
     */
    @Test
    public void testGetCaixa() {
	assertEquals(0, this.sistema.getCaixa());

	Sistema sistemaNovo = new Sistema(10, 0.3);
	assertEquals(10, sistemaNovo.getCaixa());
    }

}
