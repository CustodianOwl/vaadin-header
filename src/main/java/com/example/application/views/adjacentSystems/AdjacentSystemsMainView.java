package com.example.application.views.adjacentSystems;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Смежные системы")
@Route(value = "adjacent-systems", layout = MainLayout.class)
public class AdjacentSystemsMainView extends HorizontalLayout {

    public AdjacentSystemsMainView() {
        addClassNames("span");
        Span span = new Span();
        Icon icon = new Icon(VaadinIcon.ROAD);
        span.add(icon);
        add(span);

        Icon icon2 = new Icon("rocket");
        add(icon2);
    }
}
