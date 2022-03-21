package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author darrylanthony
 */
public class DVDLibraryView{
    private UserIO io;
    
    public DVDLibraryView(UserIO io) {
        this.io = io;
    }
    
    //Print menu and get user choice
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add a DVD");
        io.print("2. Remove a DVD");
        io.print("3. Edit a DVD");
        io.print("4. List all DVDs");
        io.print("5. View a DVD");
        io.print("6. Search for a DVD by title");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }
    
    //Gets the information about a particular DVD
    public DVD getNewDVDInfo() {
        String title = io.readString("Please enter DVD title");
        String releaseDate = io.readString("Please enter release date");
        String rating = io.readString("Please enter MPAA rating");
        String director = io.readString("Please enter director's name");
        String studio = io.readString("Please enter your studio");
        String userNote = io.readString("Please enter remarks on the DVD");
        DVD currentDVD = new DVD(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setRating(rating);
        currentDVD.setDirectorName(director);
        currentDVD.setStudio(studio);
        currentDVD.setUserNote(userNote);
        return currentDVD;
    }
 
    //Display banner for adding a DVD
    public void displayAddDVDBanner() {
        io.print("=== Add DVD ===");
    }
    
    //Display banner for succeessfully adding a DVD
    public void displayAddSuccessBanner() {
        io.readString("DVD successfully added.  Please hit enter to continue");
    }
    
    //Display method to display all the dvds.
    public void displayDVDList(List<DVD> dvdList) {
        for (DVD currentDVD : dvdList) {
            String dvdInfo = String.format("%s : %s : %s : %s : %s : %s",
                currentDVD.getTitle(),
                currentDVD.getReleaseDate(),
                currentDVD.getRating(),
                currentDVD.getDirectorName(),
                currentDVD.getStudio(),
                currentDVD.getUserNote());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    
    //Display banner for displaying all DVDs.
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
        io.print("=== Title : Date: Rating : Director Name: Studio : UserNote ===");
    }
    
    ////Display banner for displaying a DVD
    public void displayDisplayDVDBanner () {
        io.print("=== Display DVD ===");
    }
    
    public String getDVDChoice() {
        return io.readString("Please enter the title of the DVD.");
    }
    
    //Method to display the DVD info.
    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print("Release Date: " + dvd.getReleaseDate());
            io.print("Rating: " + dvd.getRating());
            io.print("Director: " + dvd.getDirectorName());
            io.print("Studio: " + dvd.getStudio());
            io.print("User Note: " + dvd.getUserNote());
            io.print("");
        } else {
            io.print("No such DVD");
        }
        io.readString("Please hit enter to continue.");
    }
    
    //Display banner for removing a DVD
    public void displayRemoveDVDBanner () {
        io.print("=== Remove DVD ===");
    }
    
    //Display banner for successfully removing a DVD
    public void displayRemoveResult(DVD storedDVD) {
        if(storedDVD != null){
            io.print("DVD successfully removed from library.");
        }else{
            io.print("No such DVD");
        }
        io.readString("Please hit enter to continue.");
    }
    
    //Display banner for adding a DVD
    public void displayEditDVDBanner () {
        io.print("=== Edit DVD ===");
    }
    
    //Allows the user to update a particular DVD
    public void editDVD(DVD dvd) {
        if (dvd != null) {
            io.print("DVD to be edited " + dvd.getTitle());
            String editOption = io.readString("What do you want to edit?");
            switch (editOption){
                case "Release Date":
                    String releaseDate = io.readString("Please enter release date");
                    dvd.setReleaseDate(releaseDate);
                    break;
                case "Rating":
                    String rating = io.readString("Please enter MPAA rating");
                    dvd.setRating(rating);
                    break;
                case "Director":
                    String directorName = io.readString("Please enter director name");
                    dvd.setDirectorName(directorName);
                    break;
                case "Studio":
                    String studio = io.readString("Please enter studio");
                    dvd.setDirectorName(studio);
                    break;
                case "UserNote":
                    String userNote = io.readString("Please enter notes on the DVD");
                    dvd.setDirectorName(userNote);
                    break;
                default:
                    System.out.println("Couldn't recognize command");
            }
        } else {
            io.print("No such DVD in library.");
        }
        io.readString("Please hit enter to continue.");
    }
    
     public void displaySearchDVDBanner () {
        io.print("=== Search DVD ===");
    }
     
    //Allows the user to search for a particular DVD
    public void searchDVD(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle() +" is in the library");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    //Displays an exit banner
    public void displayExitBanner() {
        io.print("Thank you. Good bye!");
    }
    
    //Displays the unknown command message to the user.
    public void displayUnknownCommandBanner() {
        io.print("Error Unknown Command!");
    }    
    
    //Displays the error message
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}