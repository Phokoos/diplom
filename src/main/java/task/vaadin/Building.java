package task.vaadin;

import javax.persistence.Column;
import javax.persistence.Id;

public class Building {

    @Id
    private String guid;


    private String address;
    private String people;
    private String gaz;
    private String electric;
    private String peopleWithDisabilities;
    private String floors;
    private String primaryMeans;
    private String externalWaterSupply;
    private String internalWaterSupply;
    private String fireProtectionSystems;
    private String staircase;
    private String phoneNumber;


}
