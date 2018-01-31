package org.unitec.elementos;

import java.util.ArrayList;
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
        Mensajitos m=new Mensajitos("primero", "Mi primer mensajito con hibernate");
                //repoMensa.save(m);
                
                //Probamos el de buscar Todos
                ArrayList<Mensajitos>mensajitos=
                        (ArrayList<Mensajitos>)repoMensa.findAll();
                for(Mensajitos mensa:mensajitos){
                    System.out.print(mensa.getTitulo());
                }
                
                //Ahora probaremos el de buscar por Id
                Mensajitos m1=repoMensa.findOne( 1);
                System.out.println(m1.getTitulo());
                
                //Actualizacion
                repoMensa.save(new Mensajitos(1, "otro" ,"malo"));
                
                //Borrar
                repoMensa.delete(1);
    }
}
