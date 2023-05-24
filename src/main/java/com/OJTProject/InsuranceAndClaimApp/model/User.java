package com.OJTProject.InsuranceAndClaimApp.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;
//    @Column(nullable=false)
    private String gender;
    @Column(nullable = true, length = 64)
    private String photos;
    private String avatar;
//    @Column(nullable=false)
    private LocalDate birthday;
//    @Column(nullable=false)
    private Integer age;
    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password;
    private String street;
    private String barangay;
    private String municipality;
    private String state;
    private String country;
    private Integer house_no;
    private Integer postal_code;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Client> clients = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Vehicle> vehicles = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Beneficiary> beneficiary = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Dependent> dependent = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Claim> claims = new HashSet<>();
    @Transient
    public String getPhotosImagePath() {
        if (avatar == null || id == null) return null;

        return "/user-photos/" + avatar;
    }
    @Transient
    public String getAvatar() {
        return "/user-photos/" + avatar;
    }
    //    @Id @GeneratedValue(strategy=GenerationType.AUTO)
//    private Integer id;
//    @Column(nullable=false)
//    @NotEmpty()
//    private String name;
//    @Column(nullable=false, unique=true)
//    @NotEmpty
//    @Email(message="{errors.invalid_email}")
//    private String email;
//    @Column(nullable=false)
//    @NotEmpty
//    @Size(min=4)
//    private String password;
//
//    @ManyToMany(cascade=CascadeType.MERGE)
//    @JoinTable(
//            name="user_role",
//            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
//            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
//    private List<Role> roles;
//
//    public Integer getId()
//    {
//        return id;
//    }
//    public void setId(Integer id)
//    {
//        this.id = id;
//    }
//    public String getName()
//    {
//        return name;
//    }
//    public void setName(String name)
//    {
//        this.name = name;
//    }
//    public String getEmail()
//    {
//        return email;
//    }
//    public void setEmail(String email)
//    {
//        this.email = email;
//    }
//    public String getPassword()
//    {
//        return password;
//    }
//    public void setPassword(String password)
//    {
//        this.password = password;
//    }
//    public List<Role> getRoles()
//    {
//        return roles;
//    }
//    public void setRoles(List<Role> roles)
//    {
//        this.roles = roles;
//    }
}
