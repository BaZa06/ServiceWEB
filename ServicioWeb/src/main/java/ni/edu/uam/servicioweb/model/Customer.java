package ni.edu.uam.servicioweb.model;

import java.time.LocalDate;
import java.util.Date;

public class Customer {

    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private Date date_of_birth;
    private String country;
    private String city;
    private String address;
    private Double annual_income;
    private Integer credit_score;
    private Boolean has_unpaid_debt;
    private Double unpaid_debt_amount;
    private String status;
    private LocalDate created_at;
    private LocalDate updated_at;


    public Customer() {

    }

    public Customer(Integer id, String first_name, String last_name, String email, String phone, Date date_of_birth, String country, String city, String address, Double annual_income, Integer credit_score, Boolean has_unpaid_debt, Double unpaid_debt_amount, String status, LocalDate created_at, LocalDate updated_at) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.date_of_birth = date_of_birth;
        this.country = country;
        this.city = city;
        this.address = address;
        this.annual_income = annual_income;
        this.credit_score = credit_score;
        this.has_unpaid_debt = has_unpaid_debt;
        this.unpaid_debt_amount = unpaid_debt_amount;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }


    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getUnpaid_debt_amount() {
        return unpaid_debt_amount;
    }

    public void setUnpaid_debt_amount(Double unpaid_debt_amount) {
        this.unpaid_debt_amount = unpaid_debt_amount;
    }

    public Boolean getHas_unpaid_debt() {
        return has_unpaid_debt;
    }

    public void setHas_unpaid_debt(Boolean has_unpaid_debt) {
        this.has_unpaid_debt = has_unpaid_debt;
    }

    public Integer getCredit_score() {
        return credit_score;
    }

    public void setCredit_score(Integer credit_score) {
        this.credit_score = credit_score;
    }

    public Double getAnnual_income() {
        return annual_income;
    }

    public void setAnnual_income(Double annual_income) {
        this.annual_income = annual_income;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
