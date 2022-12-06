package com.nonso.tennisplayerrest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Tennis Player REST Api";
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/players/{id}")
    public Player getPlayer(@PathVariable("id") int playerId) {
        return playerService.getPlayer(playerId);
    }

    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player) {
        player.setId(0);
        return playerService.addPlayer(player);
    }

    @PutMapping("/players/{id}")
    public Player updatePlayer(@PathVariable("id") int playerId, @RequestBody Player player) {
        return playerService.updatePlayer(playerId, player);
    }

    @PatchMapping("/players/{id}")
    public Player partialUpdate(@PathVariable("id") int id, @RequestBody HashMap<String, Object> patchPlayer) {
        return playerService.partialPatchUpdate(id, patchPlayer);
    }

    @PatchMapping("/players/{id}/titles")
    public void updateTitles(@PathVariable("id") int id, @RequestBody int titles) {
        playerService.updateTitles(id, titles);
    }

    @DeleteMapping("/players/{id}")
    public String deletePlayer(@PathVariable("id") int id) {
       return playerService.deletePlayer(id);
    }
}
