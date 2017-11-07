package br.com.inatel.drury.dm107;

import java.io.IOException;
import java.util.Base64;

public class AuthenticationService {
	public boolean authenticate(String credentials) {
		
		System.out.println(credentials);

		if (null == credentials)
			return false;

		final String encodedUserPassword = credentials.replaceFirst("Basic"+ " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final String usernameAndPassSplit[] = usernameAndPassword.split(":");
		final String username = usernameAndPassSplit[0];
		final String password = usernameAndPassSplit[1];

		
		boolean authenticationStatus = "eu".equals(username) && "eu".equals(password);
		return authenticationStatus;
	}
}
