package com.taskagile.domain.application.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.taskagile.MailManager;
import com.taskagile.MessageVariable;
import com.taskagile.RegistrationManagement;
import com.taskagile.User;
import com.taskagile.UserRegisteredEvent;
import com.taskagile.domain.application.UserService;
import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.common.event.DomainEventPublisher;
import com.taskagile.domain.model.user.RegistrationException;
import com.taskagile.domain.model.user.UserRegisteredEventHandler;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
  private RegistrationManagement registrationManagement;
  private DomainEventPublisher domainEventPublisher;
  private MailManager mailManager;

  public UserServiceImpl(RegistrationManagement registrationManagement,
                          DomainEventPublisher domainEventPublisher,
                          MailManager mailManager) {
    this.registrationManagement = registrationManagement;
    this.domainEventPublisher = domainEventPublisher;
    this.mailManager = mailManager;
  }

  @Override
  public void register(RegistrationCommand command) throws RegistrationException {
    Assert.notNull(command, "Parameter `command` must not be null");
    User newUser = registrationManagement.register(
      command.getUsername(),
      command.getEmailAddress(),
      command.getPassword());

    sendWelcomeMessage(newUser);
    domainEventPublisher.publish(new UserRegisteredEvent(newUser));
  }

  private void sendWelcomeMessage(User user) {
    mailManager.send(
      user.getEmailAddress(),
      "Welcome to TaskAgile",
      "welcome.ftl",
      MessageVariable.from("user", user)
    );
  }
}
