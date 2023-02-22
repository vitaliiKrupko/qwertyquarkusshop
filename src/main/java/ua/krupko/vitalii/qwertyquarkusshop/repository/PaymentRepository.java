package ua.krupko.vitalii.qwertyquarkusshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.krupko.vitalii.qwertyquarkusshop.model.entity.Payment;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByAmountBetween(BigDecimal min, BigDecimal max);
}
