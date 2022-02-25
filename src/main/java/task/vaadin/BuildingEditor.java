package task.vaadin;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import task.entity.Building;
import task.repository.BuildingRepository;

import javax.persistence.Column;
import java.util.Optional;
import java.util.UUID;

@SpringComponent
@UIScope
public class BuildingEditor extends VerticalLayout implements KeyNotifier {

    private final BuildingRepository buildingRepository;
    private Building building;

    private TextField address = new TextField("", "Адрес");
    private TextField people = new TextField("", "Кількість мешканців (пд'їзд/будинок)");
    private TextField gaz = new TextField("", "Місце знаходження засувок мереж газопостачання під'їзду/будівлі");
    private TextField electric = new TextField("", "Місце знеструмлення під'їзу/будівлі");
    private TextField peopleWithDisabilities = new TextField("", "Кількість та місця перебування осіб віднесених до маломобільної групи населення");
    private TextField floors = new TextField("", "Поверховість будувлі");
    private TextField primaryMeans = new TextField("", "Місце розташування первинних засобів пожежогасіння");;
    private TextField externalWaterSupply = new TextField("", "Місце розташування джерел зовнішнього протипожежного водопостачання");;
    private TextField internalWaterSupply = new TextField("", "Наявність мереж внутрішнього протипожежного водопостачання під'їзду/будівлі");;
    private TextField fireProtectionSystems = new TextField("", "Наявність та вид систем протипожежного захисту (сигналізація, оповіщення, управління евакуюванням, димовидення)");;
    private TextField staircase = new TextField("", "Кількість та тип сходових клітин");;
    private TextField phoneNumber = new TextField("", "Номер телефону відповідальної особи під'їзду/будівлі");;


    private Button save = new Button("Зберегти");
    private Button cancel = new Button("Відмінити");
    private Button delete = new Button("Видалити");
    private HorizontalLayout buttons = new HorizontalLayout(save, cancel, delete);
    private Binder<Building> binder = new Binder<>(Building.class);
    private Dialog dialog = new Dialog();

    @Setter
    private ChangeHandler changeHandler;


    public interface ChangeHandler {
        void onChange();
    }

    @Autowired
    public BuildingEditor(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
        onCreate();
    }

    private void onCreate() {
        add(address, people, gaz, electric, peopleWithDisabilities, floors, primaryMeans, externalWaterSupply, internalWaterSupply,
                fireProtectionSystems, staircase, phoneNumber, buttons);

        binder.bindInstanceFields(this);

        address.setWidth("900px");
        people.setWidth("900px");
        gaz.setWidth("900px");
        electric.setWidth("900px");
        peopleWithDisabilities.setWidth("900px");
        floors.setWidth("900px");
        primaryMeans.setWidth("900px");
        externalWaterSupply.setWidth("900px");
        internalWaterSupply.setWidth("900px");
        fireProtectionSystems.setWidth("900px");
        staircase.setWidth("900px");
        phoneNumber.setWidth("900px");

        setSpacing(true);
        initializeDialog();

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editEmployee(building));
        setVisible(false);
    }

    private void initializeDialog() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        HorizontalLayout horizontalLayout2 = new HorizontalLayout();

        Label label = new Label("Ви повинні заповнити поле про адрес");
        label.setTitle("Error massage");
        label.getStyle()
                .set("fontWeight", "bold")
                .set("font-size", "20px");

        horizontalLayout.add(label);
        horizontalLayout2.setVisible(true);

        dialog.add(horizontalLayout);
        dialog.setWidth("280px");
        dialog.setHeight("90px");

        Button button = new Button("OK");
//        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickListener(e -> dialog.close());
        button.getStyle()
                .set("margin-right", "auto");

        horizontalLayout2.add(button);
        horizontalLayout2.setPadding(true);

        dialog.add(horizontalLayout2);
    }

    private boolean isFieldsEmpty() {
        return people.isEmpty() && address.isEmpty();
    }

    private void save() {
        if (isFieldsEmpty()) {
            dialog.open();
        } else {
            if(building.getGuid()==null) {
                building.setGuid(UUID.randomUUID().toString());
            }
            buildingRepository.save(building);
            changeHandler.onChange();
        }
    }

    private void delete() {
        buildingRepository.delete(building);
        changeHandler.onChange();
    }





    public void editEmployee(Building emp) {
        if (emp == null) {
            setVisible(false);
            return;
        }

        if (emp.getGuid() != null) {
            this.building = Optional.of(buildingRepository.findByGuid(emp.getGuid())).orElse(emp);
        } else {
            this.building = emp;
        }

        binder.setBean(this.building);
        setVisible(true);
    }
}