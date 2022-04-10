package com.example.prepitbackend.domain;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class VerificationToken {
    
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Date expireDate;

    private String token;

    
    public VerificationToken() {
        super();
    }

    public VerificationToken(final String token) {
        super();

        this.token = token;
        this.expireDate = calculateExpiryDate(EXPIRATION);
    }

    public VerificationToken(final String token, final User user) {
        super();

        this.token = token;
        this.user = user;
        this.expireDate = calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public Boolean verify(Calendar calendar){
        return (this.expireDate.getTime() - calendar.getTime().getTime()) <= 0;
    }



}
