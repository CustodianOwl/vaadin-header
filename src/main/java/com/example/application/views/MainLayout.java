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

import java.time.LocalDateTime;
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

        public MenuItemInfo(String menuTitle, Icon vaadinIcon, Class<? extends Component> view, String cssClassName) {
            this.view = view;
            RouterLink link = new RouterLink();
            link.addClassName(cssClassName);

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
        public MenuItemInfo(String menuTitle, Icon vaadinIcon, Class<? extends Component> view) {
            this(menuTitle, vaadinIcon, view, view.getSimpleName());
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
        currentDateTf.setValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy\tE")));
        Span datePrefix = new Span("Дата: ");
        datePrefix.getStyle().set("color","white");
        datePrefix.getStyle().set("padding-right","10px");
        currentDateTf.setPrefixComponent(datePrefix);

        currentDateTf.setEnabled(false);


        TextField currentTime = new TextField();
        currentTime.addClassName("current-time");
        currentTime.setValue(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        Span timePrefix= new Span("Час:");
        timePrefix.getStyle().set("color","white");
        timePrefix.getStyle().set("padding-right","10px");
        currentTime.setPrefixComponent(timePrefix);
//        currentTime.setEnabled(false);

        TextField readinessTF = new TextField();
        readinessTF.addClassName("current-readiness");
        readinessTF.setValue(Readiness.BG_1.value);


        Image userIcon = new Image("images/user-icon.png","user-ico");
        userIcon.addClassName("user-icon");

        Label userFullName = new Label();
        userFullName.addClassName("user-full-name");
        userFullName.setText("Залужний Валерій Федорович");

//        Span userInfoField = new Span();
//        userInfoField.addClassName("user-info-field");
//        userInfoField.add(userIcon,userFullName);



        layout.add(currentDateTf, currentTime,readinessTF,userIcon,userFullName);

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

                new MenuItemInfo("Сатус систем", new Icon(VaadinIcon.CARET_RIGHT), AdjacentSystemsMainView.class,"adjacent-systems-main-view"),
                new MenuItemInfo("ПДП", new Icon(VaadinIcon.ROCKET), AboutView.class),
                new MenuItemInfo("Особовий склад", new Icon(VaadinIcon.USER_CARD), AboutView.class),
                new MenuItemInfo("Зміна готовності", new Icon(VaadinIcon.SPLIT), AboutView.class),
                new MenuItemInfo("Повідомлення", new Icon(VaadinIcon.BELL), AboutView.class),
                new MenuItemInfo("Довідники", new Icon(VaadinIcon.TWIN_COL_SELECT), AboutView.class),

                new MenuItemInfo("About", "la la-file", AboutView.class),

        };
    }

    enum Readiness{
        BG_1 ("Готовність № 1"),
        BG_2A("Готовність № 2а"),
        BG_2B("Готовність № 2б"),
        BG_3("Готовність № 3"),
        NONE("Дупа");

        String value;

        Readiness(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

}
