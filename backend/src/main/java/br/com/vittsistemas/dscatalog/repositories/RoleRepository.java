package br.com.vittsistemas.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vittsistemas.dscatalog.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
