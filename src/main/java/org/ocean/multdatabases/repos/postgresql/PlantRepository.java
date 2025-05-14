package org.ocean.multdatabases.repos.postgresql;

import org.ocean.multdatabases.models.postgres.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {
}
