package com.sg.dvdlibrary.dao;
import com.sg.dvdlibrary.dto.DVD;
import java.util.*;

/**
 *
 * @author darrylanthony
 */
public interface DVDLibraryDao {
    
    /**
     * Adds a DVD into the library.
     * @param title
     * @param dvd
     * @return
     * @throws DVDLibraryDaoException 
     */
    DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException;
    
    /**
     * Removes a DVD from the library.
     * @param title
     * @return
     * @throws DVDLibraryDaoException 
     */
    DVD removeDVD(String title) throws DVDLibraryDaoException;
    
    /**
     * Allows the user to edit a library.
     * @param title
     * @param dvd
     * @return
     * @throws DVDLibraryDaoException 
     */
    DVD editDVD(String title, DVD dvd) throws DVDLibraryDaoException;
    
    
    /**
     * Lists all the DVDs in the library
     * @return all the DVDs
     * @throws DVDLibraryDaoException 
     */
    List<DVD> getAllDVDs() throws DVDLibraryDaoException;

    /**
     * Retrieves the DVD for the user
     * @param title
     * @return DVD
     * @throws DVDLibraryDaoException 
     */
    DVD getDVD(String title) throws DVDLibraryDaoException;

    /**
     * Allows the user to search for a DVD by title.
     * @param title
     * @return DVD
     * @throws DVDLibraryDaoException 
     */
    DVD searchDVD(String title) throws DVDLibraryDaoException;
}
