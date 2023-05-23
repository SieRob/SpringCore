package be.vdab.welkom.repository;

import be.vdab.welkom.domain.Taal;

import java.util.List;

public interface TaalRepository {
    List<Taal> findAll();
}
