package com.sample.app.entity;

public class Player {
  private long id;

  private String name;

  private String firstName;

  private String lastName;

  private String dateOfBirth;

  private String countryOfBirth;

  private String nationality;

  private String position;

  private String lastUpdated;

  public void setId(long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setCountryOfBirth(String countryOfBirth) {
    this.countryOfBirth = countryOfBirth;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public void setLastUpdated(String lastUpdated) {
    this.lastUpdated = lastUpdated;
  }
}
