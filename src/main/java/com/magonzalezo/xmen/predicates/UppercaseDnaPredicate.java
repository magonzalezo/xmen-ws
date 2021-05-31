package com.magonzalezo.xmen.predicates;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import com.magonzalezo.xmen.constants.General;

public class UppercaseDnaPredicate implements Predicate<String> {

	@Override
	public boolean test(String data) {		
		return Pattern.compile(General.PATTERN_UPPERCASE).matcher(data).find();
	}	

}
