package com.articmeat.apicompras.repositories;

import com.articmeat.apicompras.models.Compras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compras, Integer>{
	List<Compras> findByUsuarioID(Integer usuarioID);
}
