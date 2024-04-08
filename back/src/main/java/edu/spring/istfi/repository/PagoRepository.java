package edu.spring.istfi.repository;

import edu.spring.istfi.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago,Integer> {
}
