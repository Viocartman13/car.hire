package ro.agilehub.javacourse.car.hire.fleet.service;

import org.bson.types.ObjectId;
import ro.agilehub.javacourse.car.hire.fleet.domain.MakeDO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Make;
import ro.agilehub.javacourse.car.hire.fleet.repository.MakeRepository;
import ro.agilehub.javacourse.car.hire.fleet.service.mapper.MakeMapperService;

import java.util.List;
import java.util.stream.Collectors;

public class MakeServiceImpl implements MakeService {

    private final MakeRepository makeRepository;
    private final MakeMapperService makeMapperService;

    public MakeServiceImpl(MakeRepository makeRepository, MakeMapperService makeMapperService) {
        this.makeRepository = makeRepository;
        this.makeMapperService = makeMapperService;
    }

    @Override
    public List<MakeDO> findAll() {
        return makeRepository.findAll()
                .stream()
                .map(this::mapToDO)
                .collect(Collectors.toList());
    }

    @Override
    public MakeDO findById(String id) {
        return makeRepository.findById(new ObjectId(id))
                .map(this::mapToDO)
                .orElseThrow();
    }

    @Override
    public void deleteById(String id) {
        makeRepository.deleteById(new ObjectId(id));

    }

    @Override
    public MakeDO addCar(MakeDO makeDO) {
        Make savedMake = makeRepository.save(mapToEntity(makeDO));
        return mapToDO(savedMake);
    }

    private MakeDO mapToDO(Make make) {
        return makeMapperService.toDomainObject(make);
    }

    private Make mapToEntity(MakeDO makeDO) {
        return makeMapperService.toEntity(makeDO);
    }
}
