package com.example.demo.entities;

import com.example.demo.entities.pk.ScorePK;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Table(name = "tb_score")
public class Score
{

    @EmbeddedId
    private ScorePK id = new ScorePK();
    @Getter
    @Setter
    private Integer entry;

    public Score(User user, Anime anime, Integer entry)
    {
        id.setUser(user);
        id.setAnime(anime);
        this.entry = entry;
    }

    public User getUser()
    {
        return id.getUser();
    }

    public void setUser(User user)
    {
        id.setUser(user);
    }

    public Anime getAnime()
    {
        return id.getAnime();
    }

    public void setUser(Anime anime)
    {
        id.setAnime(anime);
    }

}
