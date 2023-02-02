package com.example.p2.viewmodel;

import com.example.p2.model.Author;
import com.example.p2.model.Movies;
import com.example.p2.repository.MovieRepository;
import com.ns.util.En;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;

import static com.ns.util.En.A1;
import static org.zkoss.zk.ui.util.Notification.*;

@Slf4j
@VariableResolver(DelegatingVariableResolver.class)
public class MoviesVM {
    @WireVariable
    private MovieRepository movieRepository;

    private ListModelList<Movies> movies;

    private static final String NEW_MOVIE_PROPERTY = "newMovie";
    private Movies newMovie;
    public ListModelList<Movies> getMovies() { return movies; }

    public void setNewMovie(Movies newMovie) { this.newMovie = newMovie; }
    public void setMovies(ListModelList<Movies> movies) { this.movies = movies; }
    public Movies getNewMovie(){return newMovie;}

    public @Init void doInit(@ContextParam(ContextType.SESSION) Session session) {
        log.debug("movies doInit() called");
        setNewMovie(new Movies());
        setMovies(new ListModelList<>(movieRepository.findAll()));

        // Initialize data
    }

    //Wywoływanie metod
    public static final String CMDX = "cmd1", CMD_SHOW_MOVIES = "cmd2", CMD_SUBMIT = "cmd3";

    @Command(CMDX)
    public void cmd1()
    {
        log.debug("movies test");
    }

    @Command(CMD_SHOW_MOVIES)
    public void cmd2(){

    }
    @Command(CMD_SUBMIT) // --------------------------------------------------------------------------------------------
    public void addNewMovie(@BindingParam(A1) Button a1) {

        if (En.isAnyNull(newMovie, newMovie.getName(), newMovie.getDirector(), newMovie.getProduction_date())) {
            log.error("First name, last name, and email cannot be empty!");
            a1.setDisabled(false); // re-enable the button
            return;
        }
        newMovie = movieRepository.saveAndFlush(newMovie); // Persist entity in the DB
        showInfo("ID nowego filmu " + newMovie.getId());
        System.out.println(newMovie);
        newMovie = new Movies(); // Create a new object for another one
        BindUtils.postNotifyChange(this, NEW_MOVIE_PROPERTY); // Notify of change

        Executions.getCurrent().sendRedirect("/z/movies");
    }

    private void showInfo(String msg) {
        showNotification(msg, TYPE_INFO,2000,false);
    }

    private void showWarning(String msg) {
        showNotification(msg, TYPE_WARNING,4000,false);
    }

    private void showError(String msg) {
        showNotification(msg, TYPE_ERROR,0,true);
    }

    private void showNotification(String msg, String type, int duration, boolean closable) {
        Clients.showNotification(msg, type,null,"middle_center", duration, closable);
    }
}
