package br.com.padrao.criptografia;

import java.security.NoSuchAlgorithmException;

public class CriptografiaMD5 extends CriptografiaImpl implements Criptografia {
	public String encrypt(String value) throws NoSuchAlgorithmException {
		return encryptByAlgorithm("MD5", value);
	}
}
