package task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import task.entity.Building;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

    Building findByGuid(String guid);

    @Transactional
    void deleteByGuid(String guid);

    @Query("from Building a where a.address like concat('%', :address, '%')")
    List<Building> findAllByName(@Param("address") String address);

}
