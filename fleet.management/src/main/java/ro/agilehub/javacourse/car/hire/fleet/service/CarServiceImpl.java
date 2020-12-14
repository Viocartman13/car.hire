package ro.agilehub.javacourse.car.hire.fleet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocumentDTO;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;
import ro.agilehub.javacourse.car.hire.fleet.repository.CarRepository;
import ro.agilehub.javacourse.car.hire.fleet.service.mapper.CarMapperService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapperService carMapperService;
    private final ObjectMapper objectMapper;

    public CarServiceImpl(CarRepository carRepository, CarMapperService carMapperService, ObjectMapper objectMapper) {
        this.carRepository = carRepository;
        this.carMapperService = carMapperService;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<CarDO> findAll() {
        return carRepository.findAll()
                .stream()
                .map(this::mapToDO)
                .collect(Collectors.toList());
    }

    @Override
    public CarDO findById(String id) {
        return carRepository.findById(new ObjectId(id))
                .map(this::mapToDO)
                .orElseThrow();
    }

    @Override
    public void deleteById(String id) {
        carRepository.deleteById(new ObjectId(id));
    }

    @Override
    public CarDO addCar(CarDO carDO) {
        checkRegistrationNumber(carDO.getRegistrationNumber());
        Car savedCar = carRepository.save(mapToEntity(carDO));
        return mapToDO(savedCar);
    }

    @Override
    public CarDO updateCar(String carId, List<PatchDocumentDTO> patchDocumentDTO) throws JsonPatchException, JsonProcessingException {
        var patch = objectMapper.convertValue(patchDocumentDTO, JsonPatch.class);
        var car = carRepository.findById(new ObjectId(carId)).orElseThrow();
        checkRegistrationNumber(car.getRegistrationNumber());

        var patchedCar = applyPatchToCar(patch, car);
        patchedCar.setId(car.getId());

        return mapToDO(carRepository.save(patchedCar));
    }

    private Car applyPatchToCar(JsonPatch patch, Car car) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(car, JsonNode.class));

        return objectMapper.treeToValue(patched, Car.class);
    }

    private CarDO mapToDO(Car car) {
        return carMapperService.toDomainObject(car);
    }

    private Car mapToEntity(CarDO carDO) {
        return carMapperService.toEntity(carDO);
    }

    private void checkRegistrationNumber(String registrationNumber) {
        Car foundCar = carRepository.findByRegistrationNumber(registrationNumber);

        if (foundCar != null) {
            throw new IllegalArgumentException("Car with this registration number already exists");
        }
    }
}
