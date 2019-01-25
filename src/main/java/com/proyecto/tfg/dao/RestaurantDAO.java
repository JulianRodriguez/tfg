package com.proyecto.tfg.dao;

import com.proyecto.tfg.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantDAO extends GenericDAO<Restaurant> {

//    @Modifying
//    @Query(value = "INSERT INTO USER_RESTAURANT (question_id_question, answer_id_answer) VALUES (?1,?2)", nativeQuery = true)
//    @Transactional
//    public void addanswerquestion(@Param("idQuestion") Integer idCourse,@Param("idAnswer") Integer idUser);
}
