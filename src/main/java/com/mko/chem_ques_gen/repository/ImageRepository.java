package com.mko.chem_ques_gen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mko.chem_ques_gen.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

	@Query(nativeQuery = true,
			value = "SELECT * FROM images where name = :name ")
	public Image findImageByName(@Param("name") String name);
}
