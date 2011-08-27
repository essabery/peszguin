package pes.service.comparator;

import java.util.Comparator;

import pes.domaine.ENiveauMatch;

public class NiveauMatchComparator implements Comparator<ENiveauMatch>{

	@Override
	public int compare(ENiveauMatch arg0, ENiveauMatch arg1) {
		return arg0.getCode().compareTo(arg1.getCode());
	}

}
