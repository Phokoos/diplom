package task.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.constants.HttpStatuses;
import task.dto.BuildingDto;
import task.entity.Building;
import task.service.BuildingService;

import javax.validation.Valid;
import java.util.List;

import static task.constants.HttpStatuses.*;

@ApiResponses(value = {
    @ApiResponse(code = 200, message = OK),
    @ApiResponse(code = 400, message = BAD_REQUEST),
    @ApiResponse(code = 401, message = UNAUTHORIZED),
    @ApiResponse(code = 403, message = FORBIDDEN)
})
@RestController
public class BuildingController {



    private final BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @ApiOperation(value = "Get all building")
    @GetMapping("/building")
    public List<Building> getAllBuilding() {
        return buildingService.findAll();
    }


    @ApiOperation(value = "Get building by guid")
    @GetMapping("/building"+"/{id}")
    public Building getBuildingByGuid(@PathVariable String id) {return buildingService.findOneByGuid(id);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = HttpStatuses.CREATED),
            @ApiResponse(code = 400, message = BAD_REQUEST),
            @ApiResponse(code = 401, message = UNAUTHORIZED),
            @ApiResponse(code = 403, message = FORBIDDEN)
    })
    @ApiOperation(value = "Save new article")
    @PostMapping("/building")
    public String save(@Valid @RequestBody BuildingDto buildingDto) {
        return buildingService.create(buildingDto);
    }

    @PutMapping("/building"+"/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody BuildingDto buildingDto, @PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(buildingService.update(id, buildingDto));
    }

    @DeleteMapping("/building"+"/{id}")
    public void delete(@PathVariable String id) {
        buildingService.delete(id);
    }




}
