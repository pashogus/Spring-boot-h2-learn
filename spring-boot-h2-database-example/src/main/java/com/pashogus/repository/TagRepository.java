/**
 * 
 */
package com.pashogus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pashogus.model.Tag;

/**
 * @author 540091
 *
 */
public interface TagRepository extends JpaRepository<Tag, Long> {

}
