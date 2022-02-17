package task.service;

import task.dto.BuildingDto;
import task.entity.Building;

import java.util.List;

public interface BuildingService {

    List<Building> findAll();

    Building findOneByGuid(String guid);

    String create(BuildingDto buildingDto);

    String update(String guid, BuildingDto buildingDto);

    void delete(String guid);

}
