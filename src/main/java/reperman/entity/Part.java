package reperman.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reperman.utils.Instrument;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_song_id")
    private Song song;
    private Instrument instrument;
    @Lob
    private String picture;

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", songId=" + (song == null ? null : song.getId()) +
                ", instrument=" + instrument +
                ", picture='" + picture + '\'' +
                '}';
    }
}
