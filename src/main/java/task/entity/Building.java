package task.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Builder
@Table(name = "building")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Building {

    @Id
    private String guid;

    @Column(nullable = false)
    private String address;

    @Column
    private String people;

    @Column
    private String gaz;

    @Column
    private String electric;

    @Column
    private String peopleWithDisabilities;

    @Column
    private String floors;

    @Column
    private String primaryMeans;

    @Column
    private String externalWaterSupply;

    @Column
    private String internalWaterSupply;

    @Column
    private String fireProtectionSystems;

    @Column
    private String staircase;

    @Column
    private String phoneNumber;


}
