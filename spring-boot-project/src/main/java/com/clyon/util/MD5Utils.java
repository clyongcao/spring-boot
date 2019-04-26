package com.clyon.util;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class MD5Utils {
	
	public String newMd5Password(String newPassword) {
	
	String newMd5Password = DigestUtils.md5DigestAsHex((newPassword + "Oy57UP").getBytes());
	
	return newMd5Password;
	
	}

}
