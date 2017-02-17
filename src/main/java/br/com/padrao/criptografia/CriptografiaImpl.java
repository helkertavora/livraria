package br.com.padrao.criptografia;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.thoughtworks.xstream.core.util.Base64Encoder;

public class CriptografiaImpl {
	private MessageDigest messageDigest;
	private Base64Encoder encoder;

	protected void useAlgortithm(String algorithm)
			throws NoSuchAlgorithmException {
		if (messageDigest == null || messageDigest.getAlgorithm() != algorithm) {
			messageDigest = MessageDigest.getInstance(algorithm);
		}
		if (encoder == null) {
			encoder = new Base64Encoder();
		}

	}

	protected String encryptByAlgorithm(String algorithm, String value)
			throws NoSuchAlgorithmException {
		if (value == null) {
			throw new IllegalArgumentException("Valor null");
		}

		useAlgortithm(algorithm);
		byte[] hash = messageDigest.digest(value.getBytes());
		return encoder.encode(hash);
	}
}
