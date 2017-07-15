package lab4;

/**
 * 
 * 
 * @author Ícaro Dantas
 *
 */
public class CenarioBonus extends Cenario {

    private int bonus;

    public CenarioBonus(String descricao, int bonus) {
	super(descricao);

	this.bonus = bonus;
    }

    public String toString() {
	return super.toString() + " - R$ " + String.format("%.2f", this.getBonus() / 100.0);
    }

    public int getBonus() {
	return this.bonus;
    }

    public int getTotalRateioCenario() {
	return super.getTotalRateioCenario() + this.getBonus();
    }
}
