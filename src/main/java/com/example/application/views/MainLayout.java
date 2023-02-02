package com.example.application.views;


import com.example.application.views.about.AboutView;
import com.example.application.views.adjacentSystems.AdjacentSystemsMainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    /**
     * A simple navigation item component, based on ListItem element.
     */
    public static class MenuItemInfo extends ListItem {

        private final Class<? extends Component> view;

        public MenuItemInfo(String menuTitle, String iconClass, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            // Use Lumo classnames for various styling
            link.addClassNames(Display.FLEX, Gap.XSMALL, Height.MEDIUM, AlignItems.CENTER, Padding.Horizontal.SMALL,
                    TextColor.BODY);
            link.setRoute(view);

            Span text = new Span(menuTitle);
            // Use Lumo classnames for various styling
            text.addClassNames(FontWeight.MEDIUM, FontSize.MEDIUM, Whitespace.NOWRAP);

            link.add(new LineAwesomeIcon(iconClass), text);
            add(link);
        }

        public MenuItemInfo(String menuTitle, Icon vaadinIcon, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            // Use Lumo classnames for various styling
            link.addClassNames(Display.FLEX, Gap.XSMALL, Height.MEDIUM, AlignItems.CENTER, Padding.Horizontal.SMALL,
                    TextColor.BODY);
            link.setRoute(view);
            link.getElement().getChildCount();

            Span text = new Span(menuTitle);
            // Use Lumo classnames for various styling
            text.addClassNames(FontWeight.MEDIUM, FontSize.MEDIUM, Whitespace.NOWRAP);


            link.add(vaadinIcon, text);
            add(link);
        }

        public Class<?> getView() {
            return view;
        }

        /**
         * Simple wrapper to create icons using LineAwesome iconset. See
         * https://icons8.com/line-awesome
         */
        @NpmPackage(value = "line-awesome", version = "1.3.0")
        public static class LineAwesomeIcon extends Span {
            public LineAwesomeIcon(String lineawesomeClassnames) {
                // Use Lumo classnames for suitable font styling
                addClassNames(FontSize.LARGE, TextColor.SECONDARY);
                if (!lineawesomeClassnames.isEmpty()) {
                    addClassNames(lineawesomeClassnames);
                }
            }
        }

    }

    public MainLayout() {
        addToNavbar(createHeaderContent());
    }

    private Component createHeaderContent() {
        addClassName("main-header");
        Header header = new Header();
        header.addClassNames(BoxSizing.BORDER, Display.FLEX, FlexDirection.COLUMN, Width.FULL/*, Background.CONTRAST_80*/);

        Div layout = new Div();
        layout.addClassNames(Display.FLEX, AlignItems.CENTER, Padding.Horizontal.LARGE);
        layout.getStyle().set("padding-left", "0px");

        //sdo image
        Image sdoLogo = new Image("images/sdo-logo-white.png", "SDO Logo");
        sdoLogo.addClassNames("sdo-logo");

        layout.add(sdoLogo);

        TextField currentDateTf = new TextField();
        currentDateTf.addClassName("current-date");

        currentDateTf.setPrefixComponent(new Span("Дата: "));

        currentDateTf.setEnabled(false);


        TextField currentTime = new TextField();
        addClassName("date-time-field");
        currentTime.setValue(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        Span timePrefix= new Span("Час: ");
        timePrefix.getStyle().set("color","white");
        currentTime.setPrefixComponent(timePrefix);
        currentTime.getStyle().set("color", "white");

        Span splitter = new Span();
        splitter.setWidth("30px");

        layout.add(currentDateTf, splitter, currentTime);

        H1 splName = new H1("СПУ №1 Стартова Батарея №1");
        splName.addClassNames(Margin.Vertical.SMALL, Margin.End.AUTO, FontSize.MEDIUM);


        Nav nav = new Nav();
        nav.addClassNames(Display.FLEX, Overflow.AUTO, Padding.Horizontal.SMALL, Padding.Vertical.XSMALL);

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames(Display.FLEX, Gap.SMALL, ListStyleType.NONE, Margin.NONE, Padding.NONE);
        nav.add(list);

        for (MenuItemInfo menuItem : createMenuItems()) {
            list.add(menuItem);
        }

        header.add(layout, new Div(splName), nav);
        return header;
    }

    private MenuItemInfo[] createMenuItems() {
        return new MenuItemInfo[]{ //

                new MenuItemInfo("Сатус систем", new Icon(VaadinIcon.CARET_RIGHT), AdjacentSystemsMainView.class),
                new MenuItemInfo("ПДП", new Icon(VaadinIcon.ROCKET), AboutView.class),
                new MenuItemInfo("Особовий склад", new Icon(VaadinIcon.USER_CARD), AboutView.class),
                new MenuItemInfo("Зміна готовності", new Icon(VaadinIcon.SPLIT), AboutView.class),
                new MenuItemInfo("Повідомлення", new Icon(VaadinIcon.BELL), AboutView.class),
                new MenuItemInfo("Довідники", new Icon(VaadinIcon.TWIN_COL_SELECT), AboutView.class),

                new MenuItemInfo("About", "la la-file", AboutView.class),

        };
    }

}