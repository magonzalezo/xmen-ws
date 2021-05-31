package com.magonzalezo.xmen.predicates;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import com.magonzalezo.xmen.constants.General;

public class MutantPredicate implements Predicate<String> {

	@Override
	public boolean test(String data) {		
		return  Pattern.compile(General.PATTERN_CHECK_SEQUENCE).matcher(data).find();
	}

}
