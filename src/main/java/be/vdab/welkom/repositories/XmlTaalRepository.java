package be.vdab.welkom.repositories;

import be.vdab.welkom.domain.Taal;
import be.vdab.welkom.exeptions.RepositoryException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("XML")
public class XmlTaalRepository implements TaalRepository{
    private final String pad;
    public XmlTaalRepository(@Value("${talenXmlPad}") String pad) {
        this.pad = pad;
    }

    private final XMLInputFactory factory = XMLInputFactory.newInstance();
    @Override
    public List<Taal> findAll() {
        var talen = new ArrayList<Taal>();
        try(var bufferedReader = Files.newBufferedReader(Path.of(pad))){
            var reader = factory.createXMLStreamReader(bufferedReader);
            while (reader.hasNext()){
                reader.next();
                if(reader.isStartElement() && "taal".equals(reader.getLocalName())){
                    var taalCode = reader.getAttributeValue(0);
                    var naam = reader.getAttributeValue(1);
                    talen.add(new Taal(taalCode,naam));
                }
            }
            return talen;
        }catch (IOException | XMLStreamException e){
            throw new RepositoryException(e);
        }

    }
}
