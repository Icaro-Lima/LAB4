package lab4;

import java.util.Comparator;

import cenarios.Cenario;

public class ApostasComparator implements Comparator<Cenario> {

	@Override
	public int compare(Cenario o1, Cenario o2) {
		return o1.getTotalApostas() - o2.getTotalApostas();
	}

}
