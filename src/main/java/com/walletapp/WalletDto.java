package com.walletapp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;
import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
import java.util.Objects;
@Entity
public class WalletDto {
    public WalletDto(Integer id, String name, Double balance, LocalDate dateOfJoiningBalance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        dateOfJoiningBalance= dateOfJoiningBalance;
    }


    @NotNull(message = "Id cant be null")
    @Id
    private Integer id;

    @NotBlank(message = "Name cant be blank")
    @Pattern(regexp = "[a-zA-Z]{3,20}", message = "Name should contain 3 - 20 character and no special characters allowed")
    private String name;
    private Double balance;
    private LocalDate dateOfJoiningBalance;

    public LocalDate getdateOfJoiningBalance() {
        return dateOfJoiningBalance;
    }

    public void setdateOfJoiningBalance(LocalDate dateOfJoiningBalance) {
        dateOfJoiningBalance = dateOfJoiningBalance;
    }

    public WalletDto(Integer id, String name, Double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;

    }

    public WalletDto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "WalletDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", dateOfJoiningBalance=" + dateOfJoiningBalance +
                '}';
    }


//    public class WalletDto { // POJO
//
//   // @NotNull(message = "Id can be null")
//    @Id
//    @GeneratedValue
//    private Integer id;
//
//    @NotBlank(message = "Name cant be null, it should contain chars")
//    @Pattern(regexp = "[a-zA-Z ]{3,16}", message = "Name should contain min 3 & max 16 chars , no digits and special chars allowed.")
//    private String name;
//    @Email(message = "Please provide valid email. e.g name@ford.com")
//    private String email;
//    @Pattern(regexp = "[0-9]{3}",message = "Pin code should contain 3 digits")
//    private String pin;
//    @Pattern(regexp = "[0-9]{10}",message = "Tel no should contain only 10 digits")
//    private String phoneNumber;
//
//
//    @FutureOrPresent(message = "Join data cant be in pas")
//    private LocalDate dateOfJoiningBalance;
//    @Value("${application.balance}")
//
//
//    private Double balance;
//    // email, pWD, date of wallet creation
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    public String getPin() {
//        return pin;
//    }
//
//    public void setPin(String pin) {
//        this.pin = pin;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//    public LocalDate getDateOfJoiningBalance() {
//        return dateOfJoiningBalance;
//    }
//
//    public void setDateOfJoiningBalance(LocalDate dateOfJoiningBalance) {
//        this.dateOfJoiningBalance= dateOfJoiningBalance;
//    }
//
//
//
//    public WalletDto(Integer id, String name,String email, String pin, String phoneNumber,LocalDate dateOfJoiningBalance,Double balance) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.pin = pin;
//        this.phoneNumber = phoneNumber;
//        this.dateOfJoiningBalance = dateOfJoiningBalance;
//        this.balance = balance;
//
//
//    }
//    public WalletDto() {
//    }
//    public WalletDto(Integer id, String name, Double balance) {
//        this.id = id;
//        this.name = name;
//        this.balance = balance;
//
//
//    }
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Double getBalance() {
//        return balance;
//    }
//
//    public void setBalance(Double balance) {
//        this.balance = balance;
//    }
//
//    @Override
//    public String toString() {
//        return "Wallets{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", balance=" + balance +
//                '}';
//    }


}
