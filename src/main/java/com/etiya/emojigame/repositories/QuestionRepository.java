package com.etiya.emojigame.repositories;

import com.etiya.emojigame.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Integer> {


}
