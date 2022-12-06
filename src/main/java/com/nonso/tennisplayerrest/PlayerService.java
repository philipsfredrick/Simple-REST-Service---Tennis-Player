package com.nonso.tennisplayerrest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PlayerService {

//Get all players
    List<Player> getAllPlayers();

    //Get player by ID
    Player getPlayer(int id);

    //Add a player
    Player addPlayer(Player newPlayer);

    //Update a player
    Player updatePlayer(int id, Player player);

    //Partial update
    Player partialPatchUpdate(int id, HashMap<String, Object> playerPatch);
    // update single field without using save for whole table just colum modification.
    void updateTitles(int id, int titles);

    //delete a player
    String deletePlayer(int id);
}
