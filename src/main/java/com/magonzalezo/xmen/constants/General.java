package com.magonzalezo.xmen.constants;

public class General {
	
	private General() {}
	
	// Regular expresions
	public static final String PATTERN_ONLY_LETTERS = "^[a-zA-Z]+$";
	public static final String PATTERN_UPPERCASE = "^[A-Z]+$";
	public static final String PATTERN_VALID_CHARACTERS = "^[ATCG]+$";
	public static final String PATTERN_CHECK_SEQUENCE = "[A]{4}|[T]{4}|[C]{4}|[G]{4}";
	
	// Generic constants
	public static final int MIN_CHARACTERS_PER_RECORD = 4;

}
