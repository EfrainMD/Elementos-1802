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
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.Arrays;

/**
 *
 * @author campitos
 */
@SpringUI
@Theme("valo")
public class MiUI extends UI{

    @Override
    protected void init(VaadinRequest request) {
   VerticalLayout layout=new VerticalLayout();
   Label e1=new Label("Bienvenido");
   layout.addComponent(e1);
   Button b1=new Button("Guardar");
   layout.addComponent(b1);
   e1.addStyleName(ValoTheme.LABEL_H1);
   b1.addStyleName(ValoTheme.BUTTON_PRIMARY);
   b1.addClickListener(malo->{
   Notification.
           show("Error", "Este es un error que acabas de cometer", Notification.Type.ERROR_MESSAGE);
   }
   );

   
   
        setContent(layout);
    }
    
}
