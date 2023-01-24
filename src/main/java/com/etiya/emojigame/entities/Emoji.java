package com.etiya.emojigame.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "emojis")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Emoji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;


}
