package com.sg.dvdlibrary.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author darrylanthony
 */
public class DVD {
    private String title;
    private String releaseDate;
    private LocalDate releaseD;
    private String rating;
    private String directorName;
    private String studio;
    private String userNote;

    public DVD (String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }


    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(String date) {
        this.releaseDate = date;
        this.releaseD = LocalDate.parse(this.releaseDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating){
        this.rating = rating;
    }
    
    public String getDirectorName() {
        return this.directorName;
    }
    
    public void setDirectorName(String name) {
        this.directorName = name;
    }

    public String getStudio() {
        return this.studio;
    }
        
    public void setStudio(String studio) {
        this.studio = studio;
    }   
    
    public String getUserNote(){
        return this.userNote;
    }
    
    public void setUserNote(String note){
        this.userNote = note;
    }
}
