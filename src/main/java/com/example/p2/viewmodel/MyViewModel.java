package com.example.p2.viewmodel;

import com.example.p2.model.Author;
import com.example.p2.repository.AuthorRepository;
import com.example.p2.repository.MovieRepository;
import com.ns.util.En;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.*;

import static com.ns.util.En.*;
import static org.zkoss.zk.ui.util.Notification.*;

@Slf4j @VariableResolver(DelegatingVariableResolver.class)
public class MyViewModel {

    @WireVariable private AuthorRepository authorRepository;

    private static final String NEW_AUTHOR_PROPERTY = "newAuthor"; // property name for refresh notification
    private Author newAuthor;
    public Author getNewAuthor() { return newAuthor; }
    public void setNewAuthor(Author newAuthor) { this.newAuthor = newAuthor; }

    // Session variable for passing arguments between viewModel instances
    private static final String SELECTED_AUTHOR = "selected_author";

    private Author author;
    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }

    private ListModelList<Author> authors;
    public ListModelList<Author> getAuthors() { return authors; }
    public void setAuthors(ListModelList<Author> authors) { this.authors = authors; }

    // -----------------------------------------------------------------------------------------------------------------
    public static final String AGE_PROPERTY = "ageInYears";
    public Integer ageInYears(Date birthdate) {
        log.debug("ageInYears() Called. Birthdate: {}", birthdate != null ? birthdate.toString(): "null");
        if (birthdate == null)
            return null;
        return LocalDate.now().getYear() - birthdate.toLocalDate().getYear();
    }

    public String salutationLabel(Integer salutation) { return Author.getSalutationLabel(salutation); }

    public boolean isSalutationNone() {
        return author != null && Author.SALUTATION.none.ordinal() == author.getSalutation(); }

    public String formatDate(Date date) { return (new SimpleDateFormat("yyyy-MM-dd")).format(date); }

    // -----------------------------------------------------------------------------------------------------------------
    // ZUL init method -------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    public @Init void doInit(@ContextParam(ContextType.SESSION) Session session) {
        log.debug("doInit() called");
        // Initialize data
        setNewAuthor(new Author());
        setAuthors(new ListModelList<>(authorRepository.findAll()));
        log.debug("Found the following authors:");
        for(Author author : getAuthors().getInnerList())
            System.out.printf("%d %s %s <%s>%n",
                    author.getId(), author.getFirstName(), author.getLastName(), author.getEmail());

        if (session != null) { // Try to restore last selected author from session variable
            setAuthor((Author) session.getAttribute(SELECTED_AUTHOR));
            if (getAuthor() != null) {
                log.debug("Found SELECTED_AUTHOR var in Session: {} {} (id: {})",
                        getAuthor().getFirstName(), getAuthor().getLastName(), getAuthor().getId());
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // ZUL @Commands ---------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    public static final String CMD_SIMPLE1 = "cmd1", CMD_SIMPLE2 = "cmd2", CMD_SIMPLE3 = "cmd3"
            , CMD_DATE_CHANGE = "cmd4", CMD_SUBMIT = "cmd5", CMD_SHOW_AUTHOR = "cmd6";

    @Command(CMD_SIMPLE1) // -------------------------------------------------------------------------------------------
    public void cmd1() {
        log.info("Command {} has been successfully executed!", CMD_SIMPLE1);
        showInfo("OK!");
    }

    @Command(CMD_SIMPLE2) // -------------------------------------------------------------------------------------------
    public void defaultParams(@BindingParam(A1) @Default("Joe") String param1,
                              @BindingParam(A2) @Default("13") Integer param2,
                              @BindingParam(A3) @Default("Hello %s! Your number is: %d, right?") String pattern) {

        log.debug("Command {} has been executed.", CMD_SIMPLE2);
        String message = String.format(pattern, param1, param2);
        log.debug("Displaying message: '{}' on the client side.", message);
        showInfo(message);
    }

    @Command(CMD_SIMPLE3) // -------------------------------------------------------------------------------------------
    public void contextParams(@ContextParam(ContextType.COMMAND_NAME) String cmdName,
                              @ContextParam(ContextType.TRIGGER_EVENT) Event event,
                              @ContextParam(ContextType.SESSION) Session session) {

        if (event == null || session == null) {
            log.error("Session or event is null!");
            showError("Session or event is null!");
            return;
        }

        String cookie = (String) session.getAttribute("JSESSIONID");
        log.debug("\nExecuted command  : '{}'" +
                "\nTrigger event name: '{}'" +
                "\nHTTP JSESSIONID   : '{}'", cmdName, event.getName(), cookie);
        showInfo("JSESSIONID: " + cookie);
    }

    @Command(CMD_DATE_CHANGE) // ---------------------------------------------------------------------------------------
    public void dateChange(@BindingParam(A1) Datebox a1) {
        log.debug("Command {} has been called.", CMD_DATE_CHANGE);
        showInfo("Your age has been automatically computed.");
        BindUtils.postNotifyChange(this, AGE_PROPERTY); // Notify ZkBind to refresh the age field
    }

    @Command(CMD_SUBMIT) // --------------------------------------------------------------------------------------------
    public void addNewAuthor(@BindingParam(A1) Button a1) {
        log.debug("Command {} has been called.", CMD_DATE_CHANGE);

        if (En.isAnyNull(newAuthor, newAuthor.getFirstName(), newAuthor.getLastName(), newAuthor.getEmail())) {
            showWarning("First name, last name, and email cannot be empty!");
            a1.setDisabled(false); // re-enable the button
            return;
        }

        newAuthor = authorRepository.saveAndFlush(newAuthor); // Persist entity in the DB
        showInfo("New author's id: " + newAuthor.getId());
        System.out.println(newAuthor);
        newAuthor = new Author(); // Create a new object for another one
        BindUtils.postNotifyChange(this, NEW_AUTHOR_PROPERTY); // Notify of change
        //
        Executions.getCurrent().sendRedirect("/z/user/listAuthors");
    }

    @Command(CMD_SHOW_AUTHOR) // ---------------------------------------------------------------------------------------
    public void showAuthor(@BindingParam(A1) Author a1) {
        log.info("Command {} has been called.", CMD_SHOW_AUTHOR);
        Sessions.getCurrent().setAttribute(SELECTED_AUTHOR, a1); // Storing selected author in Session var
        ((Window) Executions.createComponents("~./user/window.zul",null,null)).doModal();
    }


    // -----------------------------------------------------------------------------------------------------------------
    // Auxiliary methods -----------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
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