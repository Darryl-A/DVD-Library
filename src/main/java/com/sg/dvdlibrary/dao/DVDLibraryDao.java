package com.sg.dvdlibrary.dao;
import com.sg.dvdlibrary.dto.DVD;
import java.util.*;

/**
 *
 * @author darrylanthony
 */
public interface DVDLibraryDao {
    
    DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException;
    
    
    DVD removeDVD(String title) throws DVDLibraryDaoException;
    
    
    DVD editDVD(String title, DVD dvd) throws DVDLibraryDaoException;
    
    
    List<DVD> getAllDVDs() throws DVDLibraryDaoException;

    
    DVD getDVD(String title) throws DVDLibraryDaoException;

    
    DVD searchDVD(String title) throws DVDLibraryDaoException;
}
