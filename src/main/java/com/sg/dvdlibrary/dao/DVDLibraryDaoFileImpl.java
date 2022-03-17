package com.sg.dvdlibrary.dao;


import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.*;
/**
 * 
 * @author darrylanthony
 */

public class DVDLibraryDaoFileImpl implements DVDLibraryDao{

    public static final String DVDLIBRARY_FILE = "DVDLibrary.txt";
    public static final String DELIMITER = "::";

    private Map<String, DVD> DVDLibrary = new HashMap<>();

    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException{
        loadLibrary();
        DVD newDVD = DVDLibrary.put(title, dvd);
        writeLibrary();
        return newDVD;
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryDaoException{
        loadLibrary();
        DVD removedDVD = DVDLibrary.remove(title);
        writeLibrary();
        return removedDVD;
    }

    @Override
    public DVD editDVD(String title, DVD dvd) throws DVDLibraryDaoException{
        loadLibrary();
        DVDLibrary.replace(title, dvd);
        writeLibrary();
        return DVDLibrary.get(title);
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException{
        loadLibrary();
        return new ArrayList(DVDLibrary.values());
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryDaoException{
        loadLibrary();
        return DVDLibrary.get(title);
    }

    @Override
    public DVD searchDVD(String title) throws DVDLibraryDaoException{
        loadLibrary();
        return DVDLibrary.get(title);
    }
    
    private DVD unmarshallDVD(String dvdAsText){
    // title::release date::mpaa rating::directorName::studio::userNote
    //  [0]     [1]            [2]     [3]     [4]     [5]
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        String dvdTitle = dvdTokens[0];

        DVD dvdFromFile = new DVD(dvdTitle);


        dvdFromFile.setReleaseDate(dvdTokens[1]);

        dvdFromFile.setRating(dvdTokens[2]);

        dvdFromFile.setDirectorName(dvdTokens[3]);
        
        dvdFromFile.setStudio(dvdTokens[4]);

        dvdFromFile.setUserNote(dvdTokens[5]);
        
        return dvdFromFile;
    }
    
    
    private void loadLibrary() throws DVDLibraryDaoException{
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(DVDLIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            //catches the FileNotFoundException and translates it to the DVDLibraryDaoException created
            throw new DVDLibraryDaoException("Error DVD library data could not be loaded into memory.", e);
        }
        String currentLine;
        DVD currentDVD;
        while (scanner.hasNextLine()) {
            
            // get next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into DVD
            currentDVD = unmarshallDVD(currentLine);

            DVDLibrary.put(currentDVD.getTitle(), currentDVD);
        }
        // close scanner
        scanner.close();
    }
    
    private String marshallDVD(DVD aDVD){

        //title::release date::mpaa rating::director::studio::user note
        String dvdAsText = aDVD.getTitle() + DELIMITER;
        
        // add the rest of the properties in the correct order:
        // release date
        dvdAsText += aDVD.getReleaseDate() + DELIMITER;
        
        // MPAA rating
        dvdAsText += aDVD.getRating() + DELIMITER;
        
        // Director Name
        dvdAsText += aDVD.getDirectorName() + DELIMITER;
        
        // Studio
        dvdAsText += aDVD.getStudio() + DELIMITER;
        
        //User Note
        dvdAsText += aDVD.getUserNote();
        
        return dvdAsText;
    }
    
    private void writeLibrary() throws DVDLibraryDaoException{
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(DVDLIBRARY_FILE));
        } catch(IOException e) {
            throw new DVDLibraryDaoException( "Process failed. Dvd data not written.", e);
        }

        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();
        for (DVD currentDVD : dvdList) {
            // Turn DVD into String
            dvdAsText = marshallDVD(currentDVD);
            
            out.println(dvdAsText);
            
            //Flush Out
            out.flush();
        }
        // Clean up and close printwriter
        out.close();
    }
    
}