package task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingDto {


    private String address;

    private String people;

    private String gaz;

    private String electric;

    private String peopleWithDisabilities;

    private String floors;

}
