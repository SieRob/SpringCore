package be.vdab.welkom.console;

import be.vdab.welkom.exeptions.RepositoryException;
import be.vdab.welkom.repositories.LandRepository;
import be.vdab.welkom.repositories.TaalRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {
    private final LandRepository landRepo;
    private final TaalRepository taalRepo;
    public MyRunner(LandRepository landRepo, @Qualifier("XML") TaalRepository taalRepo){
        this.landRepo = landRepo;
        this.taalRepo = taalRepo;
    }

    @Override
    public void run(String... args) {
        try {
            landRepo.findAll()
                    .forEach(land -> System.out.println(land.getNaam()));
            System.out.println();
            taalRepo.findAll().
                    forEach(taal -> System.out.println(taal.getNaam()));
        }catch (RepositoryException e){
            e.printStackTrace(System.err);
        }
    }
}
