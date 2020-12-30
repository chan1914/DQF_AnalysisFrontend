package com.example.application.views.Home;

import com.example.application.Model.Group;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@PageTitle("Data Quality - Homepage")
@Route(value = "home", layout = MainView.class)
public class HomeView extends VerticalLayout {

    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    Grid<Group> grid = new Grid<>(Group.class);

    Group[] responseBody = restTemplate.getForObject(
            "http://server.mikk149c.com:89/GetGoups", Group[].class);


    public HomeView() {

        H1 logo = new H1("Data quality framework");
        logo.addClassName("logo");

        grid.setItems(responseBody);
        grid.setWidth("50%");

        ListBox listbox = new ListBox();
        grid.addItemClickListener(
                event -> {
                    listbox.setItems(event.getItem().getGroup());
                });

        add(logo);
        add(grid);
        add(listbox);


    }
}
