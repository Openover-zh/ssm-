package com.itheim.ssm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <h3>heima_ssm</h3>
 * <p></p>
 *
 * @author : Dell
 * @date : 2020-08-25 20:47
 **/
public class PasswordEncoderUtils {
    @Autowired
    private PasswordEncoder passwordEncoder;
    public static void main(String[] args) {
        String i="123";
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(i);
        System.out.println(encode+"  "+encode.length());

    }
}
