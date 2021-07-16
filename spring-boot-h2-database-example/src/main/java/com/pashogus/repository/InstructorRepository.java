/**
 * 
 */
package com.pashogus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pashogus.model.Instructor;

/**
 * @author 540091
 *
 */
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

}
