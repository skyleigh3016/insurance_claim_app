package com.OJTProject.InsuranceAndClaimApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    @ManyToMany(mappedBy="roles")
    private List<User> users;

//    @Id @GeneratedValue(strategy = GenerationType.AUTO)
//  private int id;
//    @Column(nullable = false, unique = true)
//    @NotEmpty
//    private String name;
//
//    @ManyToMany(mappedBy = "roles")
//    private List< User > users;
//
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
//    public List < User > getUsers() {
//        return users;
//    }
//
//    public void setUsers(List < User > users) {
//        this.users = users;
//    }

}
