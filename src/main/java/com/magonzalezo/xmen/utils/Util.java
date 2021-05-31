package com.magonzalezo.xmen.utils;

import com.magonzalezo.xmen.constants.General;

public class Util {
	
	private Util() {}
	
	public static String[] getTransposedData(String[] dna) {
		
		String[] transposedDna = new String[dna.length];
		
		StringBuilder stb = new StringBuilder();
		for (int i = 0; i <= dna.length-1; i++) {
			stb.setLength(0);
			for (int j = 0; j <= dna[i].length()-1; j++) {
				stb.append(dna[j].charAt(i));
			}			
			transposedDna[i] = stb.toString();			
		}
		
		return transposedDna;
	}
	
	public static String[] getObliqueLeftData(String[] dna) {
		
		StringBuilder stb = new StringBuilder();
		StringBuilder stbReverse = new StringBuilder();
		
		String[] dnaOblique = new String[dna.length-1];
		
		int limit = dna.length - General.MIN_CHARACTERS_PER_RECORD;

		for (int i = 0; i <= limit; i++) {		
			
			stb.setLength(0);
			stbReverse.setLength(0);
			
			for (int j = 0; j <= (dna[i].length()-(i+1)); j++) {
				if (i==0) {
					stb.append(dna[j].charAt(j));
				} else {
					stb.append(dna[j].charAt(j+i));
					stbReverse.append(dna[j+i].charAt(j));
				}				
			}
			
			dnaOblique[i] = stb.toString();
			
			if (!stbReverse.toString().isEmpty()) {
				dnaOblique[i+2] = stbReverse.toString();
			}
			
		}		
		
		return dnaOblique;
	}
	
	public static String[] getObliqueRightData(String[] dna) {
		
		StringBuilder stb = new StringBuilder();
		StringBuilder stbReverse = new StringBuilder();
		
		String[] dnaOblique = new String[dna.length-1];
		
		int limit = dna.length - General.MIN_CHARACTERS_PER_RECORD;

		for (int i = 0; i <= limit; i++) {		
			
			stb.setLength(0);
			stbReverse.setLength(0);
			
			int iTemp = i;
			
			for (int j = dna[i].length()-1; j >= i; j--) {
				if (i==0) {
					stb.append(dna[iTemp].charAt(j));
				} else {
					stb.append(dna[iTemp-i].charAt(j-i));
					stbReverse.append(dna[iTemp].charAt(j));					
				}
				iTemp++;
			}
			
			dnaOblique[i] = stb.toString();
			
			if (!stbReverse.toString().isEmpty()) {
				dnaOblique[i+2] = stbReverse.toString();
			}
			
		}		
		
		return dnaOblique;
	}

}
