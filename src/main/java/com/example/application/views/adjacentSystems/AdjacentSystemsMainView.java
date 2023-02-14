package com.example.application.views.adjacentSystems;

import com.example.application.views.MainLayout;
import com.example.application.views.Readiness;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@PageTitle("Смежные системы")
@Route(value = "adjacent-systems", layout = MainLayout.class)
public class AdjacentSystemsMainView extends HorizontalLayout {

    public AdjacentSystemsMainView() {

        VerticalLayout vt1 = setUpSystemsInfo();
//        vt1.addClassName("vt1");
//        vt1.add(setUpSystemsInfo());
        add(vt1, setUpMsuTab());


//        addClassNames("span");
//        Span span = new Span();
//        Icon icon = new Icon(VaadinIcon.ROAD);
//        span.add(icon);
//        add(span);
//
//        Icon icon2 = new Icon("rocket");
//        add(icon2);
    }


    private VerticalLayout setUpSystemsInfo() {

        VerticalLayout statusPanel = new VerticalLayout();

        statusPanel.addClassName("adjacent-systems-status-panel");
        TextField idTF = new TextField();
        idTF.addClassName("vehicle-id");
        idTF.setValue("101");

        TextField readinessTF = new TextField();
        readinessTF.addClassName("current-readiness");
        readinessTF.setValue(Readiness.BG_1.value);
        readinessTF.setReadOnly(true);

        // TODO: 14.02.2023 add registration for data provider
        TextField startDateTf = new TextField();
        startDateTf.addClassName("start-date");
        startDateTf.setValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy\tE")));
        startDateTf.setReadOnly(true);

        // TODO: 14.02.2023 add registration for start time provider
        TextField startTimeTf = new TextField();
        startTimeTf.addClassName("start-time");
        startTimeTf.setValue(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        startDateTf.setReadOnly(true);

        TextField plcStatusTf = new TextField();
        plcStatusTf.addClassName("plc-state");
        plcStatusTf.setReadOnly(true);
        plcStatusTf.setValue(SystemStatus.OK.value);

        TextField ppoStatusTf = new TextField();
        ppoStatusTf.addClassName("ppo-state");
        ppoStatusTf.setReadOnly(true);
        ppoStatusTf.setValue(SystemStatus.WARNING.value);

        TextField saeStatusTf = new TextField();
        saeStatusTf.addClassName("sae-state");
        saeStatusTf.setReadOnly(true);
        saeStatusTf.setValue(SystemStatus.ERROR.value);

        TextField binsStatusTf = new TextField();
        binsStatusTf.addClassName("bins-state");
        binsStatusTf.setReadOnly(true);
        binsStatusTf.setValue(SystemStatus.NOT_CONNECTED.value);

        TextField sutoStatusTf = new TextField();
        sutoStatusTf.addClassName("suto-state");
        sutoStatusTf.setReadOnly(true);
        sutoStatusTf.setValue(SystemStatus.UNDEFINED.value);

        TextField meteoStatusTf = new TextField();
        meteoStatusTf.addClassName("meteo-state");
        meteoStatusTf.setReadOnly(true);
        meteoStatusTf.setValue(SystemStatus.OK.value);

        TextField nppaStatusTf = new TextField();
        nppaStatusTf.addClassName("nppa-state");
        nppaStatusTf.setReadOnly(true);
        nppaStatusTf.setValue(SystemStatus.OK.value);


        statusPanel.add(
                addLabel("ID", idTF, "vehicle-id-span"),
                addLabel("Статус", readinessTF, "current-readiness-span"),
                addLabel("Дата початку", startDateTf, "start-date-span"),
                addLabel("Час початку", startTimeTf, "start-time-span"),
                addLabel("Контроллер",plcStatusTf,"plc-state-span"),
                addLabel("ППО",ppoStatusTf,"ppo-state-span"),
                addLabel("САЕ", saeStatusTf, "sae-state-span"),
                addLabel("БІНС",binsStatusTf,"bins-status-span"),
                addLabel("СУТО",sutoStatusTf,"suto-status-span"),
                addLabel("Метеостанція",meteoStatusTf,"-status-span"),
                addLabel("НППА",nppaStatusTf,"-status-span"));

        statusPanel.setSizeFull();
        statusPanel.setJustifyContentMode(JustifyContentMode.START);
        statusPanel.setDefaultHorizontalComponentAlignment(Alignment.END);
        getStyle().set("text-align", "right");

        return statusPanel;
    }


    private VerticalLayout setUpMsuTab() {
        VerticalLayout tabPanel = new VerticalLayout();
        addClassName("systems-tab-panel");


        return tabPanel;
    }

    private Span addLabel(String text, Object o, String className) {
        Span span = new Span(text);

        span.addClassName(className);
        if (o instanceof TextField) {
            span.add((TextField) o);
        }

        return span;
    }

    enum SystemStatus {
        OK("Норма"),
        WARNING("Увага"),
        ERROR("Помилка"),
        NOT_CONNECTED("Нема зв'язку"),
        UNDEFINED("Невідомо");
        public String value;

        SystemStatus(String value) {
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
