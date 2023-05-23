package be.vdab.welkom.console;

import be.vdab.welkom.exeptions.RepositoryException;
import be.vdab.welkom.repository.LandRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {
    private final LandRepository landRepo;
    public MyRunner(LandRepository landRepo){
        this.landRepo = landRepo;
    }

    @Override
    public void run(String... args) {
        try {
            landRepo.findAll()
                    .forEach(land -> System.out.println(land.getNaam()));
        }catch (RepositoryException e){
            e.printStackTrace(System.err);
        }
    }
}
