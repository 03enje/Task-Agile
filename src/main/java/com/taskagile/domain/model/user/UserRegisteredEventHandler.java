package com.taskagile.domain.model.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredEventHandler {

  private final static Logger log = LoggerFactory.getLogger(UserRegisteredEventHandler.class);

  @EventListener(UserRegisteredEvent.class)
  public void handlerEvent (UserRegisteredEvent event) {
    log.debug("Handling `{}` registration event", event.getUser().getEmailAddress());
  }
}
