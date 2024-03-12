package com.integrax.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrax.dto.UserDTO;
import com.integrax.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByEmail(String email);

	Optional<User> findByEmail(String username);
	
	List<User> findById(Long id);

	void save(UserDTO user);
}