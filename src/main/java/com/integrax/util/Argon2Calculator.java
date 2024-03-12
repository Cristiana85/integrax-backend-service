package com.integrax.util;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Constants;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;

public class Argon2Calculator {

	// parameters
	private static final Integer ITERATIONS = 8;
	private static final Integer MEMORY = 262144;
	private static final Integer PARALLELISM = 8;
	
	private static final Argon2 ARGON2_INSTANCE = Argon2Factory.create(Argon2Types.ARGON2id);

	public static String hash(String input) {
		return ARGON2_INSTANCE.hash(ITERATIONS, MEMORY, PARALLELISM, input.toCharArray());
	}

	public static Boolean match(String plainText, String digest) {
		return ARGON2_INSTANCE.verify(digest, plainText.toCharArray());
	}
	
	public static String argonEncoder(String plainPassword) {
		Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder(Argon2Constants.DEFAULT_SALT_LENGTH, Argon2Constants.DEFAULT_HASH_LENGTH, PARALLELISM, MEMORY, ITERATIONS);
		return argon2PasswordEncoder.encode(plainPassword);
	}
}
