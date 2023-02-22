package ua.krupko.vitalii.qwertyquarkusshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.krupko.vitalii.qwertyquarkusshop.model.entity.Cart;
import ua.krupko.vitalii.qwertyquarkusshop.model.enums.CartStatus;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByStatus(CartStatus status);
    List<Cart> findByStatusAndCustomerId(CartStatus status, Long customerId);
}
