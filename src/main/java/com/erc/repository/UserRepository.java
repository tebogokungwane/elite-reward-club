package com.erc.repository;

import com.erc.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Page<User> findAll(Pageable pageable);
    Optional<User> findByEmailAddress(String email);


}
