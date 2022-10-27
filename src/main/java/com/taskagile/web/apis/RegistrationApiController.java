package com.taskagile.web.apis;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.taskagile.domain.application.UserService;
import com.taskagile.domain.model.user.EmailAddressExistsException;
import com.taskagile.domain.model.user.RegistrationException;
import com.taskagile.domain.model.user.UsernameExistsException;
import com.taskagile.web.payload.RegistrationPayload;
import com.taskagile.web.results.ApiResult;
import com.taskagile.web.results.Result;

@Controller
public class RegistrationApiController {
  private UserService service;

  public RegistrationApiController(UserService service) {
    this.service = service;
  }

  @PostMapping("/api/registrations")
  public ResponseEntity<ApiResult> register(
      @Valid @RequestBody RegistrationPayload payload) {
    try {
      service.register(payload.toCommand());
      return Result.created();
    } catch (RegistrationException e) {
        String errorMessage = "회원가입 실패";
        if ( e instanceof UsernameExistsException) {
          errorMessage = "닉네임이 이미 존재합니다.";
        } else if (e instanceof EmailAddressExistsException) {
          errorMessage = "이메일 주소가 이미 존재합니다.";
        }
      return Result.failure(errorMessage);
    }
  }
}
