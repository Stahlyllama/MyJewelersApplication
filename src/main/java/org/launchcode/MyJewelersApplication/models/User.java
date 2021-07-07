package org.launchcode.MyJewelersApplication.models;

import com.sun.istack.NotNull;
import org.dom4j.tree.AbstractEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="Users")
public class User extends AbstractEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    @NotNull
    @Column(name="username")
    private String username;

    @NotNull
    @Column(name="password")
    private String pwHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User(String username, String password) {
        this.username=username;
        this.pwHash=encoder.encode(password);
    }

    public User(){}

    public String getUsername() {
        return username;
    }
    public boolean isMatchingPassword(String password){
        return encoder.matches(password, pwHash);
    }
}


