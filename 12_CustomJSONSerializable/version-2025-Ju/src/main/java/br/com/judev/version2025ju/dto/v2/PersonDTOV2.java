package br.com.judev.version2025ju.dto.v2;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PersonDTOV2 implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private Date birthday;
    private String gender;

    public PersonDTOV2() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTOV2 that = (PersonDTOV2) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(birthday, that.birthday) && Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getBirthday(), getGender(), getAddress());
    }

}
