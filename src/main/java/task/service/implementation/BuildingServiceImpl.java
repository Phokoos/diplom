package task.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.dto.BuildingDto;
import task.entity.Building;
import task.repository.BuildingRepository;
import task.service.BuildingService;

import javax.persistence.Column;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@Transactional
@Service
//@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public BuildingServiceImpl(BuildingRepository buildingRepository, ModelMapper modelMapper) {
        this.buildingRepository = buildingRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<Building> findAll() {
        return buildingRepository.findAll();
    }

    @Override
    public Building findOneByGuid(String guid) {
        return buildingRepository.findByGuid(guid);
    }

    @Override
    public String create(BuildingDto buildingDto) {
        Building building = null;
        try{
            building = modelMapper.map(buildingDto, Building.class);
            building.setGuid(UUID.randomUUID().toString());
            buildingRepository.save(building);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return building.getGuid();
    }



    @Override
    public String update(String guid, BuildingDto buildingDto) {
        Optional<Building> building = Optional.of(buildingRepository.findByGuid(guid));
        try {
            building.ifPresent(building1 -> {


                building1.setAddress(buildingDto.getAddress());
                building1.setGaz(buildingDto.getGaz());
                building1.setElectric(buildingDto.getElectric());
                building1.setPeople(buildingDto.getPeople());
                building1.setPeopleWithDisabilities(buildingDto.getPeopleWithDisabilities());
                building1.setFloors(buildingDto.getFloors());
                building1.setPrimaryMeans(buildingDto.getPrimaryMeans());
                building1.setExternalWaterSupply(buildingDto.getExternalWaterSupply());
                building1.setInternalWaterSupply(buildingDto.getInternalWaterSupply());
                building1.setFireProtectionSystems(buildingDto.getFireProtectionSystems());
                building1.setStaircase(buildingDto.getStaircase());
                building1.setPhoneNumber(buildingDto.getPhoneNumber());

                buildingRepository.deleteByGuid(guid);
                buildingRepository.save(building1);
            });
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return building.get().getGuid();
    }

    @Override
    public void delete(String guid) {
        buildingRepository.deleteByGuid(guid);
    }


}
