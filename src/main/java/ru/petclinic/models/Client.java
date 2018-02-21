package ru.petclinic.models;

public class Client extends Base {
    private String name;
    private String surname;
    private int phone;
    private String address;

    public Client() {
    }

    public Client(final int id, final String name/*, final String surname, final int phone, final String address*/) {
        this.id = id;
        this.name = name;
        /*this.surname = surname;
        this.phone = phone;
        this.address = address;*/
    }

    public String getName() {
        return name;
    }

    public void setName(String login) {
        this.name = login;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}