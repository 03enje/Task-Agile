package com.taskagile;

public interface MailManager {
  void send(String emailAddress, String subject, String template, MessageVariable... variables);
}
