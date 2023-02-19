package com.example.application.views.adjacentSystems;

import com.example.application.views.MainLayout;
import com.example.application.views.Readiness;
import com.example.application.views.about.AboutView;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.style.Color;
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



        H4 missile_1 = new H4("Ракета №1");
        missile_1.addClassName("missile_h4_1");


        TextField missile_1StatusTf = new TextField();
        missile_1StatusTf.addClassName("missile-state-1");
        missile_1StatusTf.setReadOnly(true);
        missile_1StatusTf.setValue("3124DF");   // TODO need to add ID from system for this field
        missile_1StatusTf.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);


        FormLayout missile_form_1 = new FormLayout();
        missile_form_1.addClassName("missile_1");
        missile_form_1.addFormItem(missile_1StatusTf, "ID");

        H4 missile_2 = new H4("Ракета №2");
        missile_2.addClassName("missile_h4_2");


        TextField missile_2StatusTf = new TextField();
        missile_2StatusTf.addClassName("missile-state-2");
        missile_2StatusTf.setReadOnly(true);
        missile_2StatusTf.setValue("123DS");   // TODO need to add ID from system for this field
        missile_2StatusTf.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        FormLayout missile_form_2 = new FormLayout();
        missile_form_2.addClassName("missile_2");
        missile_form_2.addFormItem(missile_2StatusTf, "ID");

        TextField missile_info_1 = new TextField();
        missile_info_1.addClassName("missile-info-1");
        missile_info_1.setReadOnly(true);
        missile_info_1.setValue("Інформація");
        missile_info_1.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        TextField missile_info_2 = new TextField();
        missile_info_2.addClassName("missile-info-2");
        missile_info_2.setReadOnly(true);
        missile_info_2.setValue("Інформація");
        missile_info_2.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        Image img_r = new Image("images/rocket.png", "placeholder rocket");
        img_r.addClassNames("rocket_image");

        Image img_rg = new Image("images/rocket_grey.png", "placeholder rocketg");
        img_rg.addClassNames("rocket_grey_image");


        TextField missile_present_1 = new TextField();
        missile_present_1.addClassName("missile-present-1");
        missile_present_1.setReadOnly(true);
        missile_present_1.setValue("Заряджена");
        missile_present_1.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        TextField missile_present_2 = new TextField();
        missile_present_2.addClassName("missile-present-2");
        missile_present_2.setReadOnly(true);
        missile_present_2.setValue("Відсутня");
        missile_present_2.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        statusPanel.add(idTF,readinessTF, startDateTf, startTimeTf, plcStatusTf, ppoStatusTf, saeStatusTf,binsStatusTf,
        sutoStatusTf, meteoStatusTf, nppaStatusTf,missile_1, missile_2, missile_form_1, missile_1StatusTf,
        missile_form_2, missile_2StatusTf, missile_info_1, missile_info_2, img_r, img_rg, missile_present_1,
        missile_present_2);



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
        HorizontalLayout hz = new HorizontalLayout();
        hz.add(nav);
        nav.add(list);

        for (MainLayout.MenuItemInfo menuItem : createMenuItems()) {
            list.add(menuItem);
        }


        VerticalLayout tabPanel = new VerticalLayout();
        addClassName("systems-tab-panel");
        tabPanel.setMaxWidth("880px");
        tabPanel.add(nav);

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
