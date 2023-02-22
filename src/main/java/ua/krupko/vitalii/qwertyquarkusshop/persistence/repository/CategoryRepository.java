package ua.krupko.vitalii.qwertyquarkusshop.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.krupko.vitalii.qwertyquarkusshop.persistence.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
