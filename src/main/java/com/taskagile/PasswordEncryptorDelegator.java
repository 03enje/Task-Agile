package com.taskagile;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptorDelegator implements PasswordEncryptor {

  @Override
  public String encrypt(String rawPassword) {
    // TODO 이 부분 구현하기
    return rawPassword;
  }

}
