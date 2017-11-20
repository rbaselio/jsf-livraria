package br.com.rbaselio.livraria.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encriptador {
	private static MessageDigest md;
	private static byte[] digest;

	public static String toSHA(String convertme, String tipo) {

		try {
			md = MessageDigest.getInstance(tipo);
			md.reset();
			byte[] buffer = convertme.getBytes("UTF-8");
			md.update(buffer);
			digest = md.digest();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			return "";
		}
		String hexStr = "";
		for (int i = 0; i < digest.length; i++) {
			hexStr += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
		}
		return hexStr;

	}

}
