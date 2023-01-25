package com.etiya.emojigame.entities;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String userName;


    @OneToOne(mappedBy = "user")
    private Score score;


    //  avatar
    /*@Column(name = "imageurl")
    private String imageUrl; */


}
