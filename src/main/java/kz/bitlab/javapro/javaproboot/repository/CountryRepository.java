package kz.bitlab.javapro.javaproboot.repository;

import kz.bitlab.javapro.javaproboot.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CountryRepository extends JpaRepository<Country, Long> {
}
