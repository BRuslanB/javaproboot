package kz.bitlab.javapro.javaproboot.repository;

import kz.bitlab.javapro.javaproboot.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByEngineVolumeGreaterThanOrderByPriceDesc(double engineVolume);
    Car findCarByIdAndEngineVolumeGreaterThan(Long id, double engineVolume);
}