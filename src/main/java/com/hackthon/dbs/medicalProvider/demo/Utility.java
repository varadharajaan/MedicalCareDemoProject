package com.hackthon.dbs.medicalProvider.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public class Utility {
	
	public static void displayText(InputStream input) throws IOException{
		// Read one text line at a time and display.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            System.out.println("    " + line);
        }
	}
	
	public String getLoggedInUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String jwt = extractJwt(request);
        DecodedJWT decodedJWT = JWT.decode(jwt);
        return decodedJWT.getClaim("sub").asString();
    }
	
	private String extractJwt(HttpServletRequest request) {
        String authHeaderValue = request.getHeader(HttpHeaders.AUTHORIZATION);
        return authHeaderValue.replace("Bearer", "").trim();
    }
}
