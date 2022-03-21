package com.sg.dvdlibrary.ui;

import java.util.Scanner;
/**
 *
 * @author darrylanthony
 */
public class UserIOConsoleImpl implements UserIO{
    final private Scanner console = new Scanner(System.in);

    /**
     * Prints a simple give message
     * @param msg 
     */
    @Override
    public void print(String msg) {
        System.out.println(msg);

    }

    /**
     * Takes in user input message to display it.
     * @param prompt
     * @return 
     */
    @Override
    public double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(this.readString(prompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    /**
     * A more complex version of the same method, takes in user input to display.
     * @param prompt
     * @param min
     * @param max
     * @return 
     */
    @Override
    public double readDouble(String prompt, double min, double max) {
        double result;
        do {
            result = readDouble(prompt);
        } while (result < min || result > max);
        return result;
    }

    
    /**
     * Reads a prompt from the user to display.
     * @param prompt
     * @return 
     */
    @Override
    public float readFloat(String prompt) {
        while (true) {
            try {
                return Float.parseFloat(this.readString(prompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    /**
     * A more complex version of the same method, takes in user input to display.
     * @param prompt
     * @param min
     * @param max
     * @return 
     */
    @Override
    public float readFloat(String prompt, float min, float max) {
        float result;
        do {
            result = readFloat(prompt);
        } while (result < min || result > max);

        return result;
    }
    
    /**
     * Takes in a message from the user to display.
     * @param prompt
     * @return 
     */
    @Override
    public int readInt(String prompt) {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
                String stringValue = this.readString(prompt);
                num = Integer.parseInt(stringValue); 
                invalidInput = false;
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
        return num;
    }

    /**
     * More complex version - takes in user prompt to display
     * @param prompt
     * @param min
     * @param max
     * @return 
     */
    @Override
    public int readInt(String prompt, int min, int max) {
        int result;
        do {
            result = readInt(prompt);
        } while (result < min || result > max);

        return result;
    }
    
    /**
     * Takes in a user input to display.
     * @param prompt
     * @return 
     */
    @Override
    public long readLong(String prompt) {
        while (true) {
            try {
                return Long.parseLong(this.readString(prompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }
    
    /**
     * More complex version - takes in user prompt to display
     * @param prompt
     * @param min
     * @param max
     * @return 
     */
    @Override
    public long readLong(String prompt, long min, long max) {
        long result;
        do {
            result = readLong(prompt);
        } while (result < min || result > max);

        return result;
    }

    /**
     * Reads in a prompt to display.
     * @param prompt
     * @return 
     */
    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return console.nextLine();
    }
}