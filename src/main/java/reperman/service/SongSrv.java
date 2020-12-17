package reperman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reperman.context.Context;
import reperman.entity.Part;
import reperman.entity.Song;
import reperman.exception.PartNotFoundException;
import reperman.exception.SongNotFoundException;
import reperman.repository.PartRepo;
import reperman.repository.SongRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongSrv {
    @Autowired
    private SongRepo songRepo;

    @Autowired
    private PartRepo partRepo;

    public List<Song> findAll() {
        List<Song> result = songRepo.findAll().stream().sorted(Comparator.comparing(Song::getName))
                .collect(Collectors.toList());
        return result;
    }

    public Song findById(Long id) {
        return songRepo.findById(id).orElse(null);
    }

    public HttpStatus save(Song song) {
        if (song.getId() != null) {
            Optional<Song> optionalSongFromDB = songRepo.findById(song.getId());
            if (optionalSongFromDB.isEmpty()) {
                throw new SongNotFoundException(song.getId());
            }
            Song songFromDB = optionalSongFromDB.get();
            songFromDB.setName(song.getName());
            songFromDB.setLyrics(song.getLyrics());
            songFromDB.setMainPicture(song.getMainPicture());
            songFromDB.setMeasure(song.getMeasure());
            songFromDB.setTemp(song.getTemp());
            songFromDB.setTonality(song.getTonality());
            song.getParts().forEach(part -> {
                Optional<Part> optPartFromDB = songFromDB.getParts().stream().filter(part1 -> part.getInstrument().equals(part1.getInstrument())).findFirst();
                if (optPartFromDB.isPresent()) {
                    Part partFromDB = optPartFromDB.get();
                    partFromDB.setPicture(part.getPicture());
                    partRepo.saveAndFlush(partFromDB);
                } else {
                    Part newPart = new Part(null, songFromDB, part.getInstrument(), part.getPicture());
                    songFromDB.addPart(newPart);
                  //  partRepo.saveAndFlush(newPart);
                }
            });
            songFromDB.getParts().forEach(part -> {
                Optional<Part> optPart = song.getParts().stream().filter(part1 -> part.getInstrument().equals(part1.getInstrument())).findFirst();
                if (optPart.isEmpty()) {
                    songFromDB.removePart(part);
                    partRepo.delete(part);
                }
            });
          //  songFromDB.setParts(null);
            songRepo.saveAndFlush(songFromDB);
        } else {
            song.getParts().forEach(song::addPart);
            songRepo.saveAndFlush(song);
        }
        return HttpStatus.CREATED;
    }

    public HttpStatus setActiveSong(Long id) {
        Context.setActiveSongId(id);
        return HttpStatus.OK;
    }

    public Song getActiveSong() {
        return songRepo.findById(Context.getActiveSongId()).orElse(null);
    }

    public Long getActiveSongId() {
        return Context.getActiveSongId();
    }
}
