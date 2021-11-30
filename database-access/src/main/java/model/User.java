package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String fullName;

    public User(){}
    public User(String fullName ){
        this.fullName =fullName;
    }

    @Override
    public String toString() {
        return "*||" + userId + ". " + "Username: " + fullName;
    }

}