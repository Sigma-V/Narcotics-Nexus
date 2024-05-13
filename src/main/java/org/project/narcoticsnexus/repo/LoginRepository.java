package org.project.narcoticsnexus.repo;

import org.project.narcoticsnexus.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login,String> {
}
