/**
 * 
 */
package com.pashogus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pashogus.model.User;

/**
 * @author 540091
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
