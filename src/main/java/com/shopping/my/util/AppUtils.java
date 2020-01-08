/**
 * 
 */
package com.shopping.my.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shopping.my.entity.UserEntity;
import com.shopping.my.model.common.UserContext;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Syed Irfan
 *
 */
@Slf4j
public class AppUtils {


	//Private constructor, as it's an util class
	private AppUtils() {
		
	}
	
	public static UserContext getUserContextFromEntity(UserEntity entity) {
		return new UserContext(entity.getId(),entity.getName(),entity.getUsername(),entity.getEmail(),"",entity.getStatus(), null);
		
	}
	
	public static String readFile(String fileName) {
		InputStream stream = null;
		StringBuilder builder = new StringBuilder();

		try {
			
			
			Resource resource = new ClassPathResource(fileName);

		    stream = resource.getInputStream();
			Reader reader = new BufferedReader(new InputStreamReader(stream));
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			reader.close();
		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			try {
				if (stream != null)
					stream.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		return builder.toString();

	}
	
	public static void main(String[] args) {
		String password = "admin@123";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println("Encoded password is " + passwordEncoder.encode(password));
	}
}
