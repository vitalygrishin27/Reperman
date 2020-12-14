package reperman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reperman.context.Context;
import reperman.entity.Song;
import reperman.repository.SongRepo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongSrv {
    @Autowired
    private SongRepo songRepo;

    public List<Song> findAll() {
        List<Song> result = songRepo.findAll().stream().sorted(Comparator.comparing(Song::getName))
                .collect(Collectors.toList());
        return result;
    }

    public Song findById(Long id) {
        return songRepo.findById(id).orElse(null);
    }

    public HttpStatus save(Song song) {
        song.getParts().forEach(song::addPart);
        songRepo.saveAndFlush(song);
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
