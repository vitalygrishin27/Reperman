package reperman.controller;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reperman.entity.Song;
import reperman.service.SongSrv;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/reperman/songs")
public class MainController {
    @Autowired
    SongSrv songSrv;

    @GetMapping
    public ResponseEntity<List<Song>> getAll() {
        return new ResponseEntity<>(songSrv.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getAll(@PathVariable long id) {
        return new ResponseEntity<>(songSrv.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveSong(@NonNull @RequestBody Song song) {
        return ResponseEntity.status(songSrv.save(song)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity setActiveSong(@NotNull @PathVariable long id) {
        return ResponseEntity.status(songSrv.setActiveSong(id)).build();
    }

    @GetMapping("/activeSong")
    public ResponseEntity<Song> getActiveSong() {
        return new ResponseEntity<>(songSrv.getActiveSong(), HttpStatus.OK);
    }

    @GetMapping("/activeId")
    public ResponseEntity<Long> getActiveSongId() {
        return new ResponseEntity<>(songSrv.getActiveSongId(), HttpStatus.OK);
    }
}
