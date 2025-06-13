package com.ensayo.casapp.registro.infraestructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ensayo.casapp.registro.infraestructure.entities.UserEntity;

@Repository
public interface ORM extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u WHERE" +
           "(:name IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:lastName IS NULL OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND "+
           "(:rol IS NULL OR u.rol = :rol)")
    List<UserEntity> findByFilters(@Param("name") String name, @Param("lastName") String lastName, @Param("rol") String rol);
}
