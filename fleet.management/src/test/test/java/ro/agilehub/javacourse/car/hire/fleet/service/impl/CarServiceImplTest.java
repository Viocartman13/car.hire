package test.java.ro.agilehub.javacourse.car.hire.fleet.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ro.agilehub.javacourse.car.hire.api.model.CarClass;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;
import ro.agilehub.javacourse.car.hire.fleet.repository.CarRepository;
import ro.agilehub.javacourse.car.hire.fleet.service.CarServiceImpl;
import ro.agilehub.javacourse.car.hire.fleet.service.mapper.CarMapperService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;
    @Mock
    private CarMapperService carMapperService;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    public void findAll_whenCars_thenResult() {
        CarDO car1 = mock(CarDO.class);
        Car car = mock(Car.class);
        when(carRepository.findAll()).thenReturn(List.of(car));
        when(carMapperService.toDomainObject(any())).thenReturn(car1);

        List<CarDO> cars = carService.findAll();

        assertEquals(1, cars.size());
    }

    @Test
    public void findById_whenId_thenResult() {
        final Integer carId = 1;
        final Integer mileage = 55555;
        final String carModel = "Zonda";
        CarDO carDO = CarDO.builder().id(carId).mileage(mileage).model(carModel).build();
        Car car = Car.builder().id(carId).mileage(mileage).model(carModel).build();
        when(carRepository.findById(any())).thenReturn(Optional.of(car));
        when(carMapperService.toDomainObject(any())).thenReturn(carDO);

        CarDO retrievedCar = carService.findById(carId);

        assertEquals(carId, retrievedCar.getId());
        assertEquals(mileage, retrievedCar.getMileage());
        assertEquals(carModel, retrievedCar.getModel());
    }

    @Test
    public void findById_whenIdNotFound_thenException() {
        final Integer carId = 2;

        when(carRepository.findById(carId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> carService.findById(carId));

    }

    @Test
    public void deleteById_whenId_thenResult() {
        final Integer carId = 3;

        doAnswer(invocationOnMock -> {
            Integer carIdToDelete = invocationOnMock.getArgument(0);
            assertEquals(carId, carIdToDelete);
            return null;
        }).when(carRepository).deleteById(any());

        carService.deleteById(carId);
    }

    @Test
    public void addCar_whenInput_thenResult() {
        CarDO carDO = CarDO.builder().carClass(CarClass.A_SEGMENT.getValue()).mileage(1111).fuel("Diesel")
                .registrationNumber("testRegistration").build();
        Car car = Car.builder().carClass(CarClass.A_SEGMENT.getValue()).mileage(1111).fuel("Diesel")
                .registrationNumber("testRegistration").build();
        when(carRepository.save(any())).thenReturn(car);
        when(carMapperService.toDomainObject(any())).thenReturn(carDO);

        CarDO savedCar = carService.addCar(carDO);

        assertEquals(carDO.getCarClass(), savedCar.getCarClass());
        assertEquals(carDO.getMileage(), savedCar.getMileage());
        assertEquals(carDO.getFuel(), savedCar.getFuel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addCar_whenRegistrationNotOk_thenException() {
        CarDO carDO = CarDO.builder().carClass(CarClass.C_SEGMENT.getValue()).mileage(1111).fuel("Diesel")
                .registrationNumber("testRegistration").build();
        Car car = Car.builder().carClass(CarClass.D_SEGMENT.getValue()).mileage(1111).fuel("Diesel")
                .registrationNumber("testRegistration").build();

        when(carRepository.findByRegistrationNumber(any())).thenReturn(car);

        carService.addCar(carDO);
    }

    //@Test
    //TODO: complete test
//    public void updateCar_whenInputOk_thenResult() throws JsonPatchException, JsonProcessingException {
//        PatchDocumentDTO patchDocumentDTO = new PatchDocumentDTO();
//        patchDocumentDTO.setOperation(PatchDocumentDTO.OperationEnum.ADD);
//        patchDocumentDTO.setPath("/mileage");
//        patchDocumentDTO.setValue("33333");
//
//        when(objectMapper.convertValue(any(), any(Class.class))).thenCallRealMethod();
//
//        carService.updateCar("666f6f2d6261722d71757578", List.of(patchDocumentDTO));
//
//    }
}
