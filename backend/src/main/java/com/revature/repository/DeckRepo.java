package com.revature.repository;

import com.revature.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepo extends JpaRepository<Deck, Integer> {

    public List<Deck> findAll();
    public Deck findDeckByDeckId(int id);

}
