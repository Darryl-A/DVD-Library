package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author darrylanthony
 */
public class DVDLibraryController {
    private UserIO io = new UserIOConsoleImpl();

    private DVDLibraryView view;
    private DVDLibraryDao dao;
    
    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
    
    public void run(){
        Scanner sc = new Scanner(System.in);
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try{
            while (keepGoing) {
                menuSelection = getMenuSelection(); //Get choice from user

                switch (menuSelection) {
                    case 1:
                        boolean addAgain = false;
                        do{
                            addDVD();
                            System.out.println("Do you want to add again?");
                            String addChoice = sc.nextLine();
                            if (addChoice.equals("No")){
                                break;
                            }
                            else if(addChoice.equals("Yes")){
                                addAgain = true;
                            }
                        } while(addAgain == true);
                        break;
                    case 2:
                        boolean removeAgain = false;
                        do{
                            removeDVD();
                            System.out.println("Do you want to remove again?");
                            String removeChoice = sc.nextLine();
                            if (removeChoice.equals("No")){
                                break;
                            }
                            else if(removeChoice.equals("Yes")){
                                removeAgain = true;
                            }
                        } while(removeAgain == true);
                        break;
                    case 3:
                        boolean editAgain = false;
                        do{
                            editDVD();
                            System.out.println("Do you want to edit again?");
                            String addChoice = sc.nextLine();
                            if (addChoice.equals("No")){
                                break;
                            }
                            else if(addChoice.equals("Yes")){
                                editAgain = true;
                            }
                        } while(editAgain == true);
                        break;
                    case 4:
                        listDVDs();
                        break;
                    case 5:
                        viewDVD();
                        break;
                    case 6:
                        searchDVD();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch(DVDLibraryDaoException e){
           view.displayErrorMessage(e.getMessage()); 
        }
    }

    //Get's the method of the desired user choice
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    //Adds a DVD to the  library
    private void addDVD() throws DVDLibraryDaoException{
        view.displayAddDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayAddSuccessBanner();
    }
    
    //Removes a DVD from the library
    private void removeDVD() throws DVDLibraryDaoException{
        view.displayRemoveDVDBanner();
        String dvdTitle = view.getDVDChoice();
        DVD removedDVD = dao.removeDVD(dvdTitle);
        view.displayRemoveResult(removedDVD);
    }
    
    //Allows the user to a  DVD in the library
    private void editDVD() throws DVDLibraryDaoException{
        view.displayEditDVDBanner();
        String dvdTitle = view.getDVDChoice();
        DVD dvdView = dao.getDVD(dvdTitle);
        view.editDVD(dvdView);
        dao.editDVD(dvdTitle, dvdView);
    }
    
    //Lists all the DVDs in the library
    private void listDVDs() throws DVDLibraryDaoException{
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }
    
    //Allows the user to view a DVD
    private void viewDVD() throws DVDLibraryDaoException{
        view.displayDisplayDVDBanner();
        String dvdTitle = view.getDVDChoice();
        DVD dvdView = dao.getDVD(dvdTitle);
        view.displayDVD(dvdView);
    }
    
    
    //Search for a particular DVD
    private void searchDVD() throws DVDLibraryDaoException{
        view.displaySearchDVDBanner();
        String dvdTitle = view.getDVDChoice();
        DVD dvdView = dao.getDVD(dvdTitle);
        view.searchDVD(dvdView);
    }
    
    //Default message
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    //Exit message
    private void exitMessage() {
        view.displayExitBanner();
    }
}