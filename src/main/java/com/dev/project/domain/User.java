package com.dev.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Data
@NoArgsConstructor

@SQLDelete(sql = "UPDATE users SET status = false WHERE id = ?")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "users")
public class User {

    private static final long serialVersionUID = -8620743269951146000L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String token;
    private String name;
    private String email;
    private String password;
    private Boolean status;
    private Boolean googleAuth;

    @PrePersist
    public void prePersist() {
        status = true;
        googleAuth = false;
    }


}
