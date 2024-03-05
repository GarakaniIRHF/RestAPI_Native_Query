package com.garakaniirhf.spring.datajpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.garakaniirhf.spring.datajpa.model.Book;


public interface BookRepository extends JpaRepository<Book, Long> {

	@Query(value = "SELECT * FROM books", nativeQuery = true)
	List<Book> findAll();

	@Query(value = "SELECT * FROM books t WHERE t.published=?1", nativeQuery = true)
	List<Book> findByPublished(boolean isPublished);

	@Query(value = "SELECT * FROM books t WHERE t.heading LIKE %?1%", nativeQuery = true)
	List<Book> findByHeadingLike(String heading);

	@Query(value = "SELECT * FROM books t WHERE LOWER(t.heading) LIKE LOWER(CONCAT('%', ?1,'%'))", nativeQuery = true)
	List<Book> findByHeadingLikeCaseInsensitive(String heading);

	@Query(value = "SELECT * FROM books t WHERE t.grade >= ?1", nativeQuery = true)
	List<Book> findByGradeGreaterThanEqual(int grade);

	@Query(value = "SELECT * FROM books t WHERE t.created_at >= ?1", nativeQuery = true)
	List<Book> findByDateGreaterThanEqual(Date date);

	@Query(value = "SELECT * FROM books t WHERE t.grade BETWEEN ?1 AND ?2", nativeQuery = true)
	List<Book> findByGradeBetween(int start, int end);

	@Query(value = "SELECT * FROM books t WHERE t.created_at BETWEEN ?1 AND ?2", nativeQuery = true)
	List<Book> findByDateBetween(Date start, Date end);

	@Query(value = "SELECT * FROM books t WHERE t.published=:isPublished AND t.grade BETWEEN :start AND :end", nativeQuery = true)
	List<Book> findByGradeBetween(@Param("start") int start, @Param("end") int end, @Param("isPublished") boolean isPublished);

	@Query(value = "SELECT * FROM books t WHERE LOWER(t.heading) LIKE LOWER(CONCAT('%', :keyword,'%')) OR LOWER(t.description) LIKE LOWER(CONCAT('%', :keyword,'%'))", nativeQuery = true)
	List<Book> findByHeadingContainingOrDescriptionContainingCaseInsensitive(String keyword);

	@Query(value = "SELECT * FROM books t WHERE LOWER(t.heading) LIKE LOWER(CONCAT('%', :heading,'%')) AND t.published=:isPublished", nativeQuery = true)
	List<Book> findByHeadingContainingCaseInsensitiveAndPublished(String heading, boolean isPublished);

	@Transactional
	@Modifying
	@Query(value = "UPDATE books SET published=true WHERE id=?1", nativeQuery = true)
	int publishBook(Long id);

	@Query(value = "SELECT * FROM books t ORDER BY t.grade DESC", nativeQuery = true)
	List<Book> findAllOrderByGradeDesc();

	@Query(value = "SELECT * FROM books t WHERE LOWER(t.heading) LIKE LOWER(CONCAT('%', ?1,'%')) ORDER BY t.grade ASC", nativeQuery = true)
	List<Book> findByHeadingOrderByGradeAsc(String heading);

	@Query(value = "SELECT * FROM books t WHERE t.published=true ORDER BY t.created_at DESC", nativeQuery = true)
	List<Book> findAllPublishedOrderByCreatedDesc();



	@Query(value = "SELECT * FROM books t WHERE LOWER(t.heading) LIKE LOWER(CONCAT('%', ?1,'%'))", nativeQuery = true)
	Page<Book> findByHeadingLike(String heading, Pageable pageable);

	@Query(value = "SELECT * FROM books t WHERE t.published=?1", nativeQuery = true)
	Page<Book> findByPublished(boolean isPublished, Pageable pageable);

	@Query(value = "SELECT * FROM books", nativeQuery = true)
	Page<Book> findAllWithPagination(Pageable pageable);
}
