package com.etiya.emojigame.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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


    @OneToMany(mappedBy = "question")
    private List<Emoji> emojis;


    @OneToOne(mappedBy = "question", cascade = CascadeType.ALL)
    private Answer answer;


}
