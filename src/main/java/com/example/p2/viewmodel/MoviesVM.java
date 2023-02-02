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

import static com.ns.util.En.A1;

@Slf4j
@VariableResolver(DelegatingVariableResolver.class)
public class MoviesVM {
    @WireVariable
    private MovieRepository movieRepository;

    private Movies movies;
    public Movies getMovies(){return movies;}

    public void setMovies(Movies movies) {this.movies = movies; }

    public @Init void doInit(@ContextParam(ContextType.SESSION) Session session) {
        log.debug("movies doInit() called");
        setMovies(new Movies());
        // Initialize data
    }

    //Wywoływanie metod
    public static final String CMDX = "cmd1";

    @Command(CMDX)
    public void cmd1()
    {
        log.debug("movies test");

    }
}
