package com.example.p2.viewmodel;

import com.example.p2.model.Author;
import com.example.p2.model.Movies;
import com.example.p2.repository.MovieRepository;
import com.ns.util.En;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.*;
import org.zkoss.zhtml.Button;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModelList;

import static com.ns.util.En.A1;

@Slf4j
@VariableResolver(DelegatingVariableResolver.class)
public class MoviesVM {
    @WireVariable
    private MovieRepository movieRepository;

    private ListModelList<Movies> movies;
    private Movies newMovie;
    public ListModelList<Movies> getMovies() { return movies; }

    public void setNewMovie(Movies newMovie) { this.newMovie = newMovie; }
    public void setMovies(ListModelList<Movies> movies) { this.movies = movies; }

    public @Init void doInit(@ContextParam(ContextType.SESSION) Session session) {
        log.debug("movies doInit() called");
        setNewMovie(new Movies());
        setMovies(new ListModelList<>(movieRepository.findAll()));

        // Initialize data
    }

    //Wywoływanie metod
    public static final String CMDX = "cmd1", CMD_SHOW_MOVIES = "cmd2";

    @Command(CMDX)
    public void cmd1()
    {
        log.debug("movies test");
    }

    @Command(CMD_SHOW_MOVIES)
    public void cmd2(){

    }
}
