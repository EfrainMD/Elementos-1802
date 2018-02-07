/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.elementos;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.data.util.*;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author campitos
 */
@SpringUI
@Theme("valo")
public class MiUI extends UI {
public TextField t1;
public TextField t2;
public Integer miId;
    @Autowired
    RepositorioMensajitos repoMensa;

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        Label e1 = new Label("Bienvenido");
        layout.addComponent(e1);
        Button b1 = new Button("Guardar");
        layout.addComponent(b1);
        e1.addStyleName(ValoTheme.LABEL_H1);
        b1.addStyleName(ValoTheme.BUTTON_PRIMARY);
        b1.addClickListener(malo -> {
            Notification.
                    show("Error", "Este es un error que acabas de cometer", Notification.Type.ERROR_MESSAGE);
        }
        );

        List<Mensajitos> mensajitos = (List<Mensajitos>) repoMensa.findAll();

        Grid<Mensajitos> grid = new Grid<>();
        grid.setItems(mensajitos);
        grid.addColumn(Mensajitos::getId).setCaption("ID");
        grid.addColumn(Mensajitos::getTitulo).setCaption("Titulo del mensaje");
        grid.addColumn(Mensajitos::getCuerpo).setCaption("Cuerpo del mensaje");

        grid.setSelectionMode(SelectionMode.SINGLE);

        grid.addItemClickListener(evento -> {
            Notification.show("Valor: " + evento.getItem().getTitulo());
            MiVentana sub = new MiVentana();

            UI.getCurrent().addWindow(sub);
        });

        layout.addComponent(grid);

        setContent(layout);
    }

}

class MiVentana extends Window {

    public MiVentana() {
        super("Actualizar o Borrar");
        center();
        VerticalLayout vl2 = new VerticalLayout();

        TextField t1 = new TextField();
        TextField t2 = new TextField();

        Button boton = new Button("Actualizar");
        boton.addClickListener(evento -> {
            close();
        });
        vl2.addComponent(t1);
        vl2.addComponent(t2);
        vl2.addComponent(boton);

        setContent(vl2);
    }
}