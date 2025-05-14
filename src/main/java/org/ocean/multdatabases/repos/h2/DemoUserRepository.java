package org.ocean.multdatabases.repos.h2;

import org.ocean.multdatabases.models.h2.DemoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoUserRepository extends JpaRepository<DemoUser, Integer> {
}
