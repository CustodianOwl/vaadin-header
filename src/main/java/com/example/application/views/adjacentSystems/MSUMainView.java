package com.example.application.views.adjacentSystems;

import com.example.application.views.MainLayout;
import com.example.application.views.Readiness;
import com.example.application.views.about.AboutView;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.style.Color;
import com.vaadin.flow.component.charts.model.style.Theme;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep.LabelsPosition;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@PageTitle("Сатус систем | МСЮ")
@Route(value = "adjacent-systems/msu", layout = MainLayout.class)
public class MSUMainView extends HorizontalLayout {

    public MSUMainView() {

//        Nav nav = new Nav();
//        nav.addClassNames("adjacent-systems-nav");
//
//        UnorderedList list = new UnorderedList();
//        list.addClassNames("adjacent-systems-nav-list");
//        nav.add(list);
//        for (MainLayout.MenuItemInfo menuItem : createMenuItems()) {
//            list.add(menuItem);
//        }
//        add(nav);


        VerticalLayout vt1 = new VerticalLayout();
        Image img = new Image("images/image_6.png", "placeholder plant");
        img.addClassNames("spl_image");
        vt1.add(img);


        vt1.add(setUpSystemsInfo());
        vt1.setWidth("400x");

        add(vt1, setUpMsuTab());

   }



    private HorizontalLayout setUpSystemsInfo() {

        HorizontalLayout statusPanel = new HorizontalLayout();


        statusPanel.addClassName("adjacent-systems-status-panel");
        TextField idTF = new TextField();
        idTF.addClassName("vehicle-id");
        idTF.setValue("101");
        idTF.setLabel("ID");
        idTF.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);


        TextField readinessTF = new TextField();
        readinessTF.addClassName("Rcurrent-readiness");
        readinessTF.setLabel("Статус");
        readinessTF.setValue(Readiness.BG_1.value);
        readinessTF.setReadOnly(true);
        readinessTF.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        // TODO: 14.02.2023 add registration for data provider
        TextField startDateTf = new TextField();
        startDateTf.addClassName("start-date");
        startDateTf.setLabel("Дата початку");
        startDateTf.setValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy\tE")));
        startDateTf.setReadOnly(true);
        startDateTf.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        // TODO: 14.02.2023 add registration for start time provider
        TextField startTimeTf = new TextField();
        startTimeTf.addClassName("start-time");
        startTimeTf.setLabel("Час початку");
        startTimeTf.setValue(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        startTimeTf.setReadOnly(true);
        startTimeTf.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        TextField plcStatusTf = new TextField();
        plcStatusTf.addClassName("plc-state");
        plcStatusTf.setLabel("Контроллер");
        plcStatusTf.setReadOnly(true);
        plcStatusTf.setValue(SystemStatus.OK.value);
        plcStatusTf.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        TextField ppoStatusTf = new TextField();
        ppoStatusTf.addClassName("ppo-state");
        ppoStatusTf.setLabel("ППО");
        ppoStatusTf.setReadOnly(true);
        ppoStatusTf.setValue(SystemStatus.WARNING.value);
        ppoStatusTf.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        TextField saeStatusTf = new TextField();
        saeStatusTf.addClassName("sae-state");
        saeStatusTf.setLabel("САЕ");
        saeStatusTf.setReadOnly(true);
        saeStatusTf.setValue(SystemStatus.ERROR.value);
        saeStatusTf.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        TextField binsStatusTf = new TextField();
        binsStatusTf.addClassName("bins-state");
        binsStatusTf.setLabel("БІНС");
        binsStatusTf.setReadOnly(true);
        binsStatusTf.setValue(SystemStatus.NOT_CONNECTED.value);
        binsStatusTf.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        TextField sutoStatusTf = new TextField();
        sutoStatusTf.addClassName("suto-state");
        sutoStatusTf.setLabel("СУТО");
        sutoStatusTf.setReadOnly(true);
        sutoStatusTf.setValue(SystemStatus.UNDEFINED.value);
        sutoStatusTf.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        TextField meteoStatusTf = new TextField();
        meteoStatusTf.addClassName("meteo-state");
        meteoStatusTf.setLabel("Метеостанція");
        meteoStatusTf.setReadOnly(true);
        meteoStatusTf.setValue(SystemStatus.OK.value);
        meteoStatusTf.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        TextField nppaStatusTf = new TextField();
        nppaStatusTf.addClassName("nppa-state");
        nppaStatusTf.setLabel("НППА");
        nppaStatusTf.setReadOnly(true);
        nppaStatusTf.setValue(SystemStatus.OK.value);
        nppaStatusTf.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);



        H4 missileLeft = new H4("Ракета №1");
        missileLeft.addClassName("missile-h4-left");


        TextField missileLeftStatusTf = new TextField();
        missileLeftStatusTf.addClassName("missile-state-left");
        missileLeftStatusTf.setReadOnly(true);
        missileLeftStatusTf.setValue("3124DF");   // TODO need to add ID from system for this field
        missileLeftStatusTf.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);


        FormLayout missileFormLeft = new FormLayout();
        missileFormLeft.addClassName("missile-left");
        missileFormLeft.addFormItem(missileLeftStatusTf, "ID");

        H4 missileRight = new H4("Ракета №2");
        missileRight.addClassName("missile-h4-right");


        TextField missileRightStatusTf = new TextField();
        missileRightStatusTf.addClassName("missile-state-right");
        missileRightStatusTf.setReadOnly(true);
        missileRightStatusTf.setValue("123DS");   // TODO need to add ID from system for this field
        missileRightStatusTf.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);


        FormLayout missileFormRight = new FormLayout();
        missileFormRight.addClassName("missile-right");
        missileFormRight.addFormItem(missileRightStatusTf, "ID");

        TextField missileInfoLeft = new TextField();
        missileInfoLeft.addClassName("missile-info-left");
        missileInfoLeft.setReadOnly(true);
        missileInfoLeft.setValue("Інформація");
        missileInfoLeft.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        TextField missileInfoRight = new TextField();
        missileInfoRight.addClassName("missile-info-right");
        missileInfoRight.setReadOnly(true);
        missileInfoRight.setValue("Інформація");
        missileInfoRight.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        Image img_r = new Image("images/rocket.png", "placeholder rocket");
        img_r.addClassNames("rocket_image");

        Image img_rg = new Image("images/rocket_grey.png", "placeholder rocketg");
        img_rg.addClassNames("rocket_grey_image");


        TextField missilePresentLeft = new TextField();
        missilePresentLeft.addClassName("missile-present-left");
        missilePresentLeft.setReadOnly(true);
        missilePresentLeft.setValue("Заряджена");
        missilePresentLeft.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        TextField missilePresentRight = new TextField();
        missilePresentRight.addClassName("missile-present-right");
        missilePresentRight.setReadOnly(true);
        missilePresentRight.setValue("Відсутня");
        missilePresentRight.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        statusPanel.add(idTF,readinessTF, startDateTf, startTimeTf, plcStatusTf, ppoStatusTf, saeStatusTf,binsStatusTf,
        sutoStatusTf, meteoStatusTf, nppaStatusTf,missileLeft, missileLeft, missileRight, missileFormLeft, missileLeftStatusTf,
        missileFormRight, missileRightStatusTf, missileInfoLeft, missileInfoRight, img_r, img_rg, missilePresentLeft,
        missilePresentRight);



        statusPanel.setSizeFull();
//        statusPanel.setJustifyContentMode(JustifyContentMode.START);
//        statusPanel.setDefaultHorizontalComponentAlignment(Alignment.END);
//        getStyle().set("text-align", "right");

        return statusPanel;
    }




    private VerticalLayout setUpMsuTab() {

        Nav nav = new Nav();
        nav.addClassName("adjacent-systems-nav");

        UnorderedList list = new UnorderedList();// TODO: 15.02.2023 replace with HorizontalLayout to arrange as horizontal line
        list.addClassName("adjacent-systems-nav-list");
        list.addClassNames(LumoUtility.Display.BLOCK);
        HorizontalLayout hz = new HorizontalLayout();
        hz.add(nav);
        nav.add(list);

        for (MainLayout.MenuItemInfo menuItem : createMenuItems()) {
            list.add(menuItem);
        }


        VerticalLayout tabPanel = new VerticalLayout();
        addClassName("systems-tab-panel");
        tabPanel.setMaxWidth("845px");
        tabPanel.add(nav);

        Span splNameSpan = new Span("Назва СПУ");
        splNameSpan.addClassName("spl-name-out");
        tabPanel.add(splNameSpan);
        splNameSpan.setText("Система метеорологічного забезпечення");

        Span commandsCaption = new Span("Перелік команд");
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

    private void setupMsuCommandsGrid(VerticalLayout tabPanel) {

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
            sendBt.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SMALL);
            sendBt.getStyle().set("background-color", "#4F4F4F");
            sendBt.addClickListener(event -> {
                Dialog dialog = new Dialog();
                dialog.addClassName("dialog-msu");

                dialog.setHeaderTitle(commandRecord.caption + "?");
                dialog.add("Ви впевнені, що бажаєете " + commandRecord.caption + "?");

                Button confirmBt = new Button(sentence.getHeaderText(), (e) -> {
                    // TODO: 15.02.2023 send sentence to MSU
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + commandSentenceToSend + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");


                    dialog.close();
                });
                confirmBt.addClassName("confirm-button-msu-dialog");

                confirmBt.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
                        ButtonVariant.LUMO_ERROR);
                confirmBt.getStyle().set("margin-right", "auto");
                dialog.getFooter().add(confirmBt);

                Button cancelButton = new Button("Відміна", (e) -> dialog.close());
                cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
                cancelButton.addClassName("cancel-button-msu-dialog");

                dialog.getFooter().add(cancelButton);

                dialog.open();
            });

            return sendBt;
        }).setHeader("Відправити");

        commandsGrid.setMaxHeight("250px");
        commandsGrid.addClassName("commands-grid");
        commandsGrid.setItems(commandsList);
        tabPanel.add(commandsGrid);
    }


    private MainLayout.MenuItemInfo[] createMenuItems() {
        return new MainLayout.MenuItemInfo[]{
                new MainLayout.MenuItemInfo("БІНС", "", BinsView.class),
                new MainLayout.MenuItemInfo("САЕ", "", SaeView.class),
                new MainLayout.MenuItemInfo("СУТО", "", SutoView.class),
                new MainLayout.MenuItemInfo("НППА", "", NppaView.class),
                new MainLayout.MenuItemInfo("ППО", "", PpoView.class),
                new MainLayout.MenuItemInfo("МЕТЕО", "", MSUMainView.class)
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
