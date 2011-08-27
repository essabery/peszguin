package pes.ihm.service;

import java.util.Comparator;

public class SelectOrdreNaturel implements Comparator<String> {

	public int compare(String s1, String s2) {
		int resultCompare = 0;
		if (s1.equals("default") || s2.equals("default")) {
			if (s1.equals("default")) resultCompare = -1;
			if (s2.equals("default")) resultCompare = 1;
			if (s1.equals(s2)) resultCompare = 0;
		}
		else {
			resultCompare = s1.compareTo(s2);
		}
		return resultCompare;
	}
}
