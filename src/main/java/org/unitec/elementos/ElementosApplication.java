package org.unitec.elementos;

import com.vaadin.ui.Grid;
import java.util.ArrayList;
import java.util.List;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElementosApplication implements CommandLineRunner {
    @Autowired RepositorioMensajitos repoMensa;

	public static void main(String[] args) {
		SpringApplication.run(ElementosApplication.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {
     //   Mensajitos m=new Mensajitos("primero", "Mi primer mensajito con hibernate");
                //repoMensa.save(m);
              
    }
}
