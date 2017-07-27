package lab4;

import java.util.Comparator;

import cenarios.Cenario;;

public class NomeComparator implements Comparator<Cenario> {

	@Override
	public int compare(Cenario o1, Cenario o2) {
		int compare = o1.getDescricao().compareTo(o2.getDescricao());
		
		return compare != 0 ? compare : o1.getCadastroID() - o2.getCadastroID();
	}
	
}
