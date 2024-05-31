package com.tkosmulski.amore.dto;

public class SafePerson {
    private String email;
    private String name;
    private String surname;
    private String gender;
    private String phone;
    private String address;
    public SafePerson(Person person) {
        email = person.getEmail();
        name = person.getName();
        surname = person.getSurname();
        gender = person.getGender();
        phone = (person.getPhone() == null) ? "" : person.getPhone();
        address = (person.getAddress() == null) ? "" : person.getAddress();
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
