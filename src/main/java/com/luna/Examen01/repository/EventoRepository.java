package com.luna.Examen01.repository;

import com.luna.Examen01.entity.Evento;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findByNombreContaining(String nombre, Pageable page);

}
