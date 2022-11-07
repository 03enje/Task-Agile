package com.taskagile;

import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user")
public class User extends AbstractBaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="username", nullable=false, length=50, unique=true)
  private String username;

  @Column(name="email_address", nullable=false, length=100, unique=true)
  private String emailAddress;

  @Column(name="password", nullable=false, length=30)
  private String password;

  @Column(name="first_name", nullable=false, length=45)
  private String firstName;

  @Column(name="last_name", nullable=false, length=45)
  private String lastName;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_date", nullable=false)
  private Date createdDate;

  public User() {}

  public static User create(String username, String emailAddress, String password) {
    User user = new User();
    user.username = username;
    user.emailAddress = emailAddress;
    user.password = password;
    user.firstName = "";
    user.lastName = "";
    user.createdDate = new User();
    return user;

  }
}
