package com.example.application.views.adjacentSystems;

import com.example.application.views.MainLayout;
import com.example.application.views.Readiness;
import com.example.application.views.about.AboutView;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@PageTitle("Сатус систем | МСЮ")
@Route(value = "adjacent-systems/msu", layout = MainLayout.class)
public class MSUMainView extends HorizontalLayout {

    public MSUMainView() {
        VerticalLayout vt1 = setUpSystemsInfo();



        add(vt1, setUpMsuTab());


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
                addLabel("Контроллер", plcStatusTf, "plc-state-span"),
                addLabel("ППО", ppoStatusTf, "ppo-state-span"),
                addLabel("САЕ", saeStatusTf, "sae-state-span"),
                addLabel("БІНС", binsStatusTf, "bins-status-span"),
                addLabel("СУТО", sutoStatusTf, "suto-status-span"),
                addLabel("Метеостанція", meteoStatusTf, "-status-span"),
                addLabel("НППА", nppaStatusTf, "-status-span"));

        statusPanel.setSizeFull();
        statusPanel.setJustifyContentMode(JustifyContentMode.START);
        statusPanel.setDefaultHorizontalComponentAlignment(Alignment.END);
        getStyle().set("text-align", "right");

        return statusPanel;
    }


    private VerticalLayout setUpMsuTab() {
        VerticalLayout tabPanel = new VerticalLayout();
        addClassName("systems-tab-panel");

        Span splNameSpan = new Span("Назва СПУ");
        splNameSpan.addClassName("spl-name-out");
        tabPanel.add(splNameSpan);
        splNameSpan.setText("СПУ №1 Стартова Батарея №1");

        Span commandsCaption = new Span("Команди");
        commandsCaption.addClassName("commands-caption");
        tabPanel.add(commandsCaption);

        setupMsuCommandsGrid(tabPanel);

        Span logCaption = new Span("Журнал");
        logCaption.addClassName("log-caption");
        tabPanel.add(logCaption);

        setupMsuLogGrid(tabPanel);



        Button navBt = new Button("navigate test");

        navBt.addClickListener(e -> {
            navBt.getUI().ifPresent(ui -> {
                ui.navigate(AboutView.class);
            });
        });

        tabPanel.add(navBt);

        return tabPanel;
    }

    private void setupMsuLogGrid(VerticalLayout tabPanel) {
        List<CommandRecord> commandsList = Arrays.asList(
                new CommandRecord("Перевести в рабочее положение", "$PMSDS,1,0,0,0"),
                new CommandRecord("Перевести в транспортное оложение", "$PMSDS,0,1,0,0"),
                new CommandRecord("Включить обогрев", "$PMSDS,0,0,1,0"),
                new CommandRecord("Выключить обогрев", "$PMSDS,0,0,0,1")
        );
        Grid<CommandRecord> logGrid = new Grid<>();
        logGrid.addClassName("msu-log-grid");
        logGrid.setMaxHeight("200px");



        tabPanel.add(logGrid);
    }

    private void setupMsuCommandsGrid(VerticalLayout tabPanel){
        List<CommandRecord> commandsList = Arrays.asList(
                new CommandRecord("Перевести в рабочее положение", "$PMSDS,1,0,0,0"),
                new CommandRecord("Перевести в транспортное оложение", "$PMSDS,0,1,0,0"),
                new CommandRecord("Включить обогрев", "$PMSDS,0,0,1,0"),
                new CommandRecord("Выключить обогрев", "$PMSDS,0,0,0,1")
        );

        Grid<CommandRecord> commandsGrid = new Grid<>(CommandRecord.class, false);

        Grid.Column<CommandRecord> caption = commandsGrid
                .addColumn(CommandRecord::getCaption).setHeader("Команда")
                /*.setTextAlign(ColumnTextAlign.START)*/;

        Grid.Column<CommandRecord> sentence = commandsGrid
                .addColumn(CommandRecord::getSentence).setHeader("Відправити")
                /*.setTextAlign(ColumnTextAlign.END)*/;
        sentence.setVisible(false);

        Grid.Column<CommandRecord> sendColumn = commandsGrid.addComponentColumn(commandRecord -> {
            String commandSentenceToSend = commandRecord.getSentence(); // TODO: 15.02.2023 add command builder

            Button sendBt = new Button("Відправити");
            sendBt.addClassName("send-sentence-button");
            sendBt.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_SMALL);
            sendBt.getStyle().set("background-color","#4F4F4F");
            sendBt.addClickListener(event -> {
                Dialog dialog = new Dialog();

                dialog.setHeaderTitle(commandRecord.caption + "?");
                dialog.add("Ви впевнені, що бажаєете " + commandRecord.caption + "?");

                Button confirmBt = new Button(sentence.getHeaderText(), (e) -> {
                    // TODO: 15.02.2023 send sentence to MSU
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + commandSentenceToSend + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");


                    dialog.close();
                });

                confirmBt.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
                        ButtonVariant.LUMO_ERROR);
                confirmBt.getStyle().set("margin-right", "auto");
                dialog.getFooter().add(confirmBt);

                Button cancelButton = new Button("Відміна", (e) -> dialog.close());
                cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
                dialog.getFooter().add(cancelButton);

                dialog.open();
            });

            return sendBt;
        }).setHeader("Відправити");

        commandsGrid.setMaxHeight("250px");
        commandsGrid.setItems(commandsList);
        tabPanel.add(commandsGrid);
    }


    private MainLayout.MenuItemInfo[] createMenuItems() {
        return new MainLayout.MenuItemInfo[]{ //

                new MainLayout.MenuItemInfo("Сатус систем", new Icon(VaadinIcon.CARET_RIGHT), MSUMainView.class,"adjacent-systems-main-view"),
                new MainLayout.MenuItemInfo("ПДП", new Icon(VaadinIcon.ROCKET), AboutView.class),
                new MainLayout.MenuItemInfo("Особовий склад", new Icon(VaadinIcon.USER_CARD), AboutView.class),
                new MainLayout.MenuItemInfo("Зміна готовності", new Icon(VaadinIcon.SPLIT), AboutView.class),
                new MainLayout.MenuItemInfo("Повідомлення", new Icon(VaadinIcon.BELL), AboutView.class),
                new MainLayout.MenuItemInfo("Довідники", new Icon(VaadinIcon.TWIN_COL_SELECT), AboutView.class),

                new MainLayout.MenuItemInfo("About", "la la-file", AboutView.class),

        };
    }

    private Span addLabel(String text, Object o, String className) {
        Span span = new Span(text);

        span.addClassName(className);
        if (o instanceof TextField) {
            span.add((TextField) o);
        }

        return span;
    }


    public class CommandRecord {
        String caption;
        String sentence;

        public CommandRecord(String caption, String sentence) {
            this.caption = caption;
            this.sentence = sentence;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public String getSentence() {
            return sentence;
        }

        public void setSentence(String sentence) {
            this.sentence = sentence;
        }
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
