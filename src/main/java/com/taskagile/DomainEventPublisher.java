package com.taskagile;

import com.taskagile.domain.common.event.DomainEvent;

public interface DomainEventPublisher {

  /**
   * Publish a domain event
   */
  void publish(DomainEvent event);
}
