package task.vaadin;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
//import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import task.entity.Building;
import task.repository.BuildingRepository;

@PageTitle("manager")
@Route("manager")
public class BuildingView extends VerticalLayout {

    @Value("${server.host}")
    public static String HOST = "";

    private final BuildingRepository buildingRepository;

    private final BuildingEditor buildingEditor;

    private Grid<Building> buildingGrid = new Grid<>(Building.class);
    private final TextField filter = new TextField();
    private final Button addNewButton = new Button("New building", VaadinIcon.PLUS.create());
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewButton);

    @Autowired
    public BuildingView(BuildingRepository buildingRepository, BuildingEditor buildingEditor) {
        this.buildingRepository = buildingRepository;
        this.buildingEditor = buildingEditor;
        onCreate();
    }

    private void onCreate() {
        createToolbar();
        createFilter();
        createGrid();

        add(toolbar, buildingGrid, buildingEditor);

        addNewButton.addClickListener(e -> buildingEditor.editEmployee(new Building()));

        buildingEditor.setChangeHandler(() -> {
            buildingEditor.setVisible(false);
            fillList(filter.getValue());
        });

        fillList("");
    }

    private void createToolbar() {
        Anchor anchor = new Anchor(HOST, "Home");
        anchor.getStyle()
                .set("fontWeight", "bold")
                .set("font-size", "30px")
                .set("text-align", "left");

        Label label = new Label("manager");
        label.getStyle()
                .set("fontWeight", "bold")
                .set("font-size", "40px")
                .set("text-align", "center");
        label.setSizeFull();

        HorizontalLayout toolbar = new HorizontalLayout(anchor, label);
        toolbar.setSizeFull();

        add(toolbar);
    }

    private void createFilter() {
        filter.setPlaceholder("Type to filter buildings");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(field -> fillList(field.getValue()));
    }

    private void createGrid() {
        buildingGrid
                .asSingleSelect()
                .addValueChangeListener(e -> buildingEditor.editEmployee(e.getValue()));

//        buildingGrid.removeColumnByKey("guid");
        buildingGrid.getColumnByKey("address").setHeader("Адреса");
//
//        buildingGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
//                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
    }

    private void fillList(String name) {
        if (StringUtils.isEmpty(name)) {
            buildingGrid.setItems(this.buildingRepository.findAll());
        } else {
//            buildingGrid.setItems(this.buildingRepository.findAll());
            buildingGrid.setItems(this.buildingRepository.findAllByName(name));
        }
    }
}