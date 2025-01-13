package com.mko.chem_ques_gen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;

import com.mko.chem_ques_gen.entities.ChemQuestion;


//@Repository
public interface ChemQuestionRepository extends JpaRepository<ChemQuestion, Integer>{
	
	//all by chapter
	public List<ChemQuestion> findByChemQuestionChapter(String chapter);

	//all by chapter and label is not utilized. But implemented with another approach in coding.
	
	//all by chapter and type
	@Query(nativeQuery = true,
			value = "SELECT * FROM chem_question "
					+ "Where chapter = :chapter and type = :type and grade = :grade ")
	public List<ChemQuestion> findQuestionByChapterAndtypeAndGrade
								(@Param("chapter") String chapter,
								 @Param("type") String type,
								 @Param("grade") String grade);


	
	//all by chapter and type and difficulty
	@Query(nativeQuery = true,
			value = "SELECT * FROM chem_question "
					+ "Where chapter = :chapter and type = :type and difficulty = :difficulty and grade = :grade ")
	public List<ChemQuestion> findQuestionByChapterAndtypeAndDifficultyAndGrade
								(@Param("chapter") String chapter,
								 @Param("type") String type, 
								 @Param("difficulty") String difficulty,
								 @Param("grade") String grade);
	

	
	
	
	//---------------------------
	//limited number of questions by chapter
	
	//limited num by chapter and type (UnUsed) implemented just for existence
	@Query(nativeQuery = true,
			value = "SELECT * FROM chem_question "
					+ "Where chapter = :chapter and type = :type and grade = :grade "
					+ "ORDER BY RAND() LIMIT :numQ ")
	public List<ChemQuestion> findANumberOfRandomQuesByChapterAndTypeAndGrade
								(@Param("chapter") String chapter, 
								 @Param("numQ") Integer numQ,
								 @Param("type") String type,
								 @Param("grade") String grade);
	
	
	//limited num by chapter and type <Unique Label>  ----------- (MOST USED)
	//https://www.geeksforgeeks.org/sql-server-row_number-function-with-partition-by/
	@Query(nativeQuery = true,
			value = "WITH uniqueLabel as "
					+ "(SELECT *, ROW_NUMBER() OVER ("
					+ "PARTITION BY label "
					+ "ORDER BY RAND()) "
					+ "AS rank_num FROM chem_question "
					+ "WHERE type= :type and chapter = :chapter and grade = :grade ) "
					+ "SELECT * FROM uniqueLabel "
					+ "WHERE rank_num = 1 "
					+ "ORDER BY RAND() limit :numQ ")
	public List<ChemQuestion> findANumberOfRandomQuesByChapterAndUniqueLabelAndTypeAndGrade
								(@Param("chapter") String chapter,
								 @Param("numQ") Integer numQ,
								 @Param("type") String type,
								 @Param("grade") String grade);
	
	
	//limited num by chapter and type and difficulty
	@Query(nativeQuery = true,
			value = "SELECT * FROM chem_question "
					+ "Where chapter = :chapter and type = :type and difficulty = :difficulty and grade = :grade "
					+ "ORDER BY RAND() LIMIT :numQ ")
	public List<ChemQuestion> findANumberOfRandomQuesByChapterAndTypeAndDifficultyAndGrade
								(@Param("chapter") String chapter, 
								 @Param("numQ") Integer numQ,
								 @Param("type") String type, 	
								 @Param("difficulty") String difficulty,
								 @Param("grade") String grade);
	
	
	//limited num by chapter and type and difficulty <Unique Label>  ----------- (MOST USED)
	//https://www.geeksforgeeks.org/sql-server-row_number-function-with-partition-by/
	@Query(nativeQuery = true,
			value = "WITH uniqueLabel as "
					+ "( SELECT *, ROW_NUMBER() OVER ("
					+ "PARTITION BY label "
					+ "ORDER BY RAND()) "
					+ "AS rank_num FROM chem_question "
					+ "WHERE type= :type and chapter = :chapter and difficulty = :difficulty and grade = :grade) "
					+ "SELECT * FROM uniqueLabel "
					+ "WHERE rank_num = 1 "
					+ "ORDER BY RAND() limit :numQ ")
	public List<ChemQuestion> findANumberOfRandomQuesByChapterAndUniqueLabelAndTypeAndDifficultyAndGrade
								(@Param("chapter") String chapter,
								 @Param("numQ") Integer numQ,
								 @Param("type") String type,
								 @Param("difficulty") String difficulty,
								 @Param("grade") String grade);
	
}
