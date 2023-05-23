package be.vdab.welkom.repository;

import be.vdab.welkom.domain.Land;
import be.vdab.welkom.exeptions.RepositoryException;
import org.springframework.stereotype.Component;

import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class LandRepository {
    public List<Land> findAll(){
        try (var stream = Files.lines(Path.of("E:/VDAB/Lessen PDF/Java/5 - Spring Core/SpringCoreMateriaal/landen.csv"))){
            return stream
                    .map(regel -> regel.split(","))
                    .map(regelOnderdelen -> new Land(regelOnderdelen[0], regelOnderdelen[1], Integer.parseInt(regelOnderdelen[2])))
                    .toList();
        }catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e){
            throw new RepositoryException(e);
        }
    }
}
