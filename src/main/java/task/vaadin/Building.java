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


}
