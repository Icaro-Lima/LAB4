package cenarios;

/**
 * Representa um cenário onde é somado um bonus aos apostadores vencedores.
 * 
 * @author Ícaro Dantas
 *
 */
public class CenarioBonus extends Cenario {

	private int bonus;

	/**
	 * Instancia um novo cenario bonus.
	 * @param descricao A descrição do cenário.
	 * @param bonus O bônus a ser somado ao rateamento dos jogadores.
	 */
	public CenarioBonus(String descricao, int bonus) {
		super(descricao);

		this.bonus = bonus;
	}

	public String toString() {
		return super.toString() + " - R$ "
				+ String.format("%.2f", this.getBonus() / 100.0);
	}

	public int getBonus() {
		return this.bonus;
	}

	public int getTotalRateioCenario() {
		return super.getTotalRateioCenario() + this.getBonus();
	}
}
