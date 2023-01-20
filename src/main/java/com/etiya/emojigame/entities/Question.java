package com.etiya.emojigame.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "questions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "emojiimage1")
    private String emojiImage1;

    @Column(name = "emojiimage2")
    private String emojiImage2;

    @Column(name = "emojiimage3")
    private String emojiImage3;

    @Column(name = "emojiimage4")
    private String emojiImage4;

    @Column(name = "emojiimage5")
    private String emojiImage5;


}
