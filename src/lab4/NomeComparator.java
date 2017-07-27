package lab4;

import java.util.Comparator;

import cenarios.Cenario;;

public class NomeComparator implements Comparator<Cenario> {

	@Override
	public int compare(Cenario o1, Cenario o2) {
		return o1.getDescricao().compareTo(o2.getDescricao());
	}
	
	
}
