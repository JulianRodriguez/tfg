package com.proyecto.tfg.dao;

import com.proyecto.tfg.model.Restaurant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantDAO extends GenericDAO<Restaurant> {

//    @Modifying
//    @Query(value = "INSERT INTO USER_RESTAURANT (question_id_question, answer_id_answer) VALUES (?1,?2)", nativeQuery = true)
//    @Transactional
//    public void addanswerquestion(@Param("idQuestion") Integer idCourse,@Param("idAnswer") Integer idUser);

//   @Query(value = "SELECT q FROM Restaurant AS r join r.")
//    List<Restaurant> findRestaurantbyiduser(@Param("idUser") Long id, Pageable p);
}

