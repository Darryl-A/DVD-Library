package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import java.util.List;

/**
 *
 * @author darrylanthony
 */
public class DVDLibraryController {
    
    private DVDLibraryView view;
    private DVDLibraryDao dao;
    
    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
    
    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try{
            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addDVD();
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        editDVD();
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

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void addDVD() throws DVDLibraryDaoException{
        view.displayAddDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayAddSuccessBanner();
    }
    
    private void removeDVD() throws DVDLibraryDaoException{
        view.displayRemoveDVDBanner();
        String dvdTitle = view.getDVDChoice();
        DVD removedDVD = dao.removeDVD(dvdTitle);
        view.displayRemoveResult(removedDVD);
    }
    
    private void editDVD() throws DVDLibraryDaoException{
        view.displayEditDVDBanner();
        String dvdTitle = view.getDVDChoice();
        DVD dvdView = dao.getDVD(dvdTitle);
        view.editDVD(dvdView);
        dao.editDVD(dvdTitle, dvdView);
    }
    
    private void listDVDs() throws DVDLibraryDaoException{
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }
    
    private void viewDVD() throws DVDLibraryDaoException{
        view.displayDisplayDVDBanner();
        String dvdTitle = view.getDVDChoice();
        DVD dvdView = dao.getDVD(dvdTitle);
        view.displayDVD(dvdView);
    }
    
    
    private void searchDVD() throws DVDLibraryDaoException{
        view.displaySearchDVDBanner();
        String dvdTitle = view.getDVDChoice();
        DVD dvdView = dao.getDVD(dvdTitle);
        view.searchDVD(dvdView);
    }
    
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
}