package task.vaadin;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Value;



@PageTitle("SafeBud crm system")
@Route(value = "")
public class MainView extends VerticalLayout {

    @Value("${server.host}")
    public static String HOST = "";

    public MainView() {
        createToolbar();
        createTimetableAnchor();
    }

    private void createToolbar() {
        Label label = new Label("CRM System");
        label.getStyle()
                .set("fontWeight", "bold")
                .set("font-size", "40px")
                .set("text-align", "center");
        label.setSizeFull();

        HorizontalLayout toolbar = new HorizontalLayout(label);
        toolbar.setSizeFull();

        Label labelEmpty = new Label();
        label.getStyle()
                .set("font-size", "45px");

        add(toolbar, labelEmpty);
    }

    private void createTimetableAnchor() {
        Anchor anchor = new Anchor(HOST + "manager", "Manager");
        anchor.setSizeFull();
        anchor.getStyle()
                .set("fontWeight", "bold")
                .set("font-size", "40px")
                .set("text-align", "center");
        add(anchor);
    }
}