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
import com.vaadin.ui.HorizontalLayout;
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
        t1=new TextField();
        t2=new TextField();
        t2.setWidth("340px");
        VerticalLayout layout = new VerticalLayout();
        Label e1 = new Label("Bienvenido");
        layout.addComponent(e1);
        Button b1 = new Button("Guardar");
        layout.addComponent(b1);
        e1.addStyleName(ValoTheme.LABEL_H1);
        b1.addStyleName(ValoTheme.BUTTON_PRIMARY);
        b1.addClickListener(malo -> {
            
            t1.setValue("");
            t2.setValue("");
            MiVentanaGuardar sub = new MiVentanaGuardar();

            UI.getCurrent().addWindow(sub);
            
           }
        );

        List<Mensajitos> mensajitos = (List<Mensajitos>) repoMensa.findAll();

        Grid<Mensajitos> grid = new Grid<>();
        grid.setItems(mensajitos);
        grid.addColumn(Mensajitos::getId).setCaption("ID");
        grid.addColumn(Mensajitos::getTitulo).setCaption("Titulo del mensaje");
        grid.addColumn(Mensajitos::getCuerpo).setCaption("Puerco del mensaje");

        //2. agregar al grid el modo de seleccion unico
        grid.setSelectionMode(SelectionMode.SINGLE);

        grid.addItemClickListener(evento -> {
          //  Notification.show("Valor: " + evento.getItem().getTitulo());
            miId=evento.getItem().getId();
            t1.setValue(evento.getItem().getTitulo());
            t2.setValue(evento.getItem().getCuerpo());
            MiVentana sub = new MiVentana();

            UI.getCurrent().addWindow(sub);
        });

        layout.addComponent(grid);

        setContent(layout);
    }
   public  class MiVentana extends Window {

    public MiVentana() {
        super("Actualizar o borrar");
        setWidth("400px");
        center();
        VerticalLayout vl2 = new VerticalLayout();


        Button boton = new Button("Actualizar");
        boton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        Button botonBorrar=new Button("Borrar");
        botonBorrar.addStyleName(ValoTheme.BUTTON_DANGER);
        botonBorrar.addClickListener(evento->{
            repoMensa.delete(miId);
        });
        
        boton.addClickListener(evento -> {
        
            repoMensa.save(new Mensajitos(miId,t1.getValue(),t2.getValue()));
            close();
        });
        vl2.addComponent(t1);
        vl2.addComponent(t2);
        HorizontalLayout hl=new HorizontalLayout();
        
        hl.addComponent(boton);
        hl.addComponent(botonBorrar);
        vl2.addComponent(hl);

        setContent(vl2);
    }
}
   
   
    public  class MiVentanaGuardar extends Window {

    public MiVentanaGuardar() {
        super("Guardar");
        setWidth("400px");
        center();
        VerticalLayout vl2 = new VerticalLayout();


        Button boton = new Button("Guardar");
        boton.addClickListener(evento -> {
            
            
            repoMensa.save(new Mensajitos(t1.getValue(),t2.getValue()));
            close();
        });
        vl2.addComponent(t1);
        vl2.addComponent(t2);
        vl2.addComponent(boton);

        setContent(vl2);
    }
}

}