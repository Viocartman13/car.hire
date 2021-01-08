package ro.agilehub.javacourse.car.hire.fleet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    Car findByRegistrationNumber(String registrationNumber);
}
