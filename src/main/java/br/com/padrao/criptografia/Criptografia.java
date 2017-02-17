package br.com.padrao.criptografia;

import java.security.NoSuchAlgorithmException;

public interface Criptografia {
	String encrypt(String value) throws NoSuchAlgorithmException;
}
