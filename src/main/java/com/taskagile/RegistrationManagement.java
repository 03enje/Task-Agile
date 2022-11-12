package com.taskagile;

import org.springframework.stereotype.Component;

import com.taskagile.domain.model.user.EmailAddressExistsException;
import com.taskagile.domain.model.user.RegistrationException;
import com.taskagile.domain.model.user.UsernameExistsException;

@Component
public class RegistrationManagement {
  private UserRepository repository;
  private PasswordEncryptor passwordEncryptor;

  public RegistrationManagement(UserRepository repository, PasswordEncryptor passwordEncryptor) {
    this.repository = repository;
    this.passwordEncryptor = passwordEncryptor;
  }

  public User register(String username, String emailAddress, String password) throws RegistrationException {
    User existingUser = repository.findByUsername(username);
    if (existingUser != null) {
      throw new UsernameExistsException();
    }

    existingUser = repository.findByEmailAddress(emailAddress.toLowerCase());
    if (existingUser != null) {
      throw new EmailAddressExistsException();
    }

    String encryptedPassword = passwordEncryptor.encrypt(password);
    User newUser = User.create(username, emailAddress.toLowerCase(), encryptedPassword);
    repository.save(newUser);
    return newUser;
  }
}
