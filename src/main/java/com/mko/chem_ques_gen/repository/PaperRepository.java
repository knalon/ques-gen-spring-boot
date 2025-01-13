package com.mko.chem_ques_gen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.mko.chem_ques_gen.entities.Paper;

//@Repository
public interface PaperRepository extends JpaRepository<Paper, Integer>{

}
