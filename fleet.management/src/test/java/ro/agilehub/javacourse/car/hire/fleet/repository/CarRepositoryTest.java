package ro.agilehub.javacourse.car.hire.fleet.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
@ActiveProfiles("test")
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void findByRegistrationNumber_whenInputOk_thenResult() {
        Car car = Car.builder().registrationNumber("222TTT").fuel("Diesel").mileage(222333).carClass(CarDTO.CarClassEnum.SUPERCAR.getValue()).build();
        carRepository.save(car);

        Car foundCar = carRepository.findByRegistrationNumber("222TTT");

        assertEquals("Diesel", foundCar.getFuel());
        assertEquals(Integer.valueOf(222333), foundCar.getMileage());
        assertEquals(CarDTO.CarClassEnum.SUPERCAR.getValue(), foundCar.getCarClass());
    }
}
