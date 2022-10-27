package com.taskagile.web.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.taskagile.domain.application.commands.RegistrationCommand;

public class RegistrationPayload {
  @Size(min = 2, max = 50, message = "이름을 2 ~ 50자 사이로 입력해주세요.")
  @NotNull
  private String username;

  @Email(message = "이메일 주소가 유효합니다.")
  @Size(max = 100, message = "이메일 주소를 100자 이상으로 입력해주세요.")
  @NotNull
  private String emailAddress;

  @Size(min = 6, max = 30, message = "비밀번호를 6 ~ 30자 사이로 입력해주세요.")
  @NotNull
  private String password;

  public RegistrationCommand toCommand() {
    return new RegistrationCommand(this.username, this.emailAddress, this.password);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


}
