package model;

import java.io.Serializable;
import java.util.Date;

public class Movie implements Serializable {
    private Integer movieId;
    private String title;
    private String posterPath;
    private Date  timeline;
    private String description;
    private boolean isCurrentlyShowing;
    private String duration;
    private Integer  Base_Price;

    public Movie() {}
    public Movie(Integer movieId,String title, String posterPath, Date timeline , boolean isCurrentlyShowing,String description,String duration) {
        this.title = title;
        this.posterPath = posterPath;
        this.timeline = timeline;
        this.isCurrentlyShowing = isCurrentlyShowing;
        this.movieId = movieId;
        this.description = description;
        this.duration = duration;
        this.Base_Price = 50;
    }

    public String getDescription() {
            return description;
    }

    public Integer getMovieId() {
        return movieId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
    public Date getTimeline() {
        return timeline;
    }
    public void setTimeline(Date timeline) {
        this.timeline = timeline;
    }

    public boolean isCurrentlyShowing() {
        return isCurrentlyShowing;
    }
    public void setCurrentlyShowing(boolean isCurrentlyShowing) {
        this.isCurrentlyShowing = isCurrentlyShowing;
    }

    public String getDuration() {
        return duration;
    }
    public Integer getBase_Price() {
        return Base_Price;
    }
}
