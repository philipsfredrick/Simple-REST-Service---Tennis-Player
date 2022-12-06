package com.nonso.tennisplayerrest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;

    @Override
    public List<Player> getAllPlayers() {
      return playerRepository.findAll();
    }

    @Override
    public Player getPlayer(int id) {
        Optional<Player> tempPlayer = playerRepository.findById(id);
        Player p;

        // if Optional has a value, assign to p
        if (tempPlayer.isPresent()) {
            p = tempPlayer.get();
        } else {
            throw new PlayerNotFoundException("Player with id " + id + " not found");
        }
        return p;

        /** Alternatively do this and find way to handle the Optional exception. Experiment*/
//        Player player = null;
//        player = playerRepository.findById(id).orElseThrow();
//        return player;
        /**OR*/
        /**
         * Optional<Player> player = playerRepository.findById(id);
         * if (player.isEmpty()) {
         * throw new RuntimeException("message");
         * }
         * return player.get();
         */

    }

    @Override
    public Player addPlayer(Player newPlayer) {
        return playerRepository.save(newPlayer);
    }

    @Override
    public Player updatePlayer(int id, Player pl) {
        // get player object by Id
        Optional<Player> player = playerRepository.findById(id);
        System.out.println(player);
        Player play;

        if (player.isPresent()) {
            play = player.get();
        } else {
            throw new PlayerNotFoundException("Player with id " + id + " not found");
        }
        // update player details.
        play.setName(pl.getName());
        play.setNationality(pl.getNationality());
        play.setBirthDate(pl.getBirthDate());
        play.setTitles(pl.getTitles());

        // call repository method to update the player
        // save in repo
        return playerRepository.save(play);

        /**Alternatively this below */
//        // get player object by Id
//        Player ply = playerRepository.getOne(id);
        /**NB: getOne() method returns only a proxy object and does not really hit the database.
         * Hence, why it's deprecated and findById used.*/
//        System.out.println(player);

//        // update player details.
//        ply.setName(pl.getName());
//        ply.setNationality(pl.getNationality());
//        ply.setBirthDate(pl.getBirthDate());
//        ply.setTitles(pl.getTitles());
//
//        // call repository method to update the player
//        // save in repo
//        return playerRepository.save(ply);

        /**OR*/
        /**
         * Optional<Player> tempPlayer = repo.findById(id);
         *
         * 		if(tempPlayer.isEmpty())
         * 			throw new RuntimeException("Player with id {"+ id +"} not found");
         *
         * 		p.setId(id);
         * 		return repo.save(p);
         */
    }

    @Override
    public Player partialPatchUpdate(int id, HashMap<String, Object> playerPatch) {
        Optional<Player> player = playerRepository.findById(id);
        if (player.isEmpty()) {
            throw new PlayerNotFoundException("Player with id " + id + " not found");
        }
        playerPatch.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Player.class, key);
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, player.get(), value);
        });
        return playerRepository.save(player.get());
    }

//    @Override
    @Transactional
    public void updateTitles(int id, int titles) {
        playerRepository.updateTitles(id, titles);
    }

    @Override
    public String deletePlayer(int id) {
        try {
            playerRepository.deleteById(id);
        } catch (Exception e) {
            throw new PlayerNotFoundException("Player with id " + id + " not found");
        }
        return "Deleted player with id " + id;
    }

}
