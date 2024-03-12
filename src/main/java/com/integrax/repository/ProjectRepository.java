package com.integrax.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrax.entity.Project;

import jakarta.validation.constraints.NotNull;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	List<Project> findByAccountId(@NotNull Long accountId);
}