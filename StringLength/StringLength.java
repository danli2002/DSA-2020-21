
/**
 * Write a program, StringLength.java to sort an array list of strings by increasing length. Supply a Comparator.
 * 
 * @author Daniel Li
 * 
 * @version Java 1.8.0
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class StringLength {
    String name;

    public StringLength(String name) {
        this.name = name;
    }

    // print out name
    public String toString() {
        return name;
    }

}

// reads names from file and adds them to array list
class ScannerReadFile {
    public ScannerReadFile() {
    }

    public ArrayList returnData() {
        // Location of file to read
        File file = new File("samplenames.txt");
        ArrayList<String> data = new ArrayList<String>();
        try {
            // add elements to arraylist, making new entry per line
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                data.add(line);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}

// comparator that sorts by length of a string, useful in this case.
class sortByLength implements Comparator<StringLength> {
    public int compare(StringLength string1, StringLength string2) {
        return string1.name.length() - string2.name.length();
    }
}

class Main {
    public static void main(String[] args) {
        ScannerReadFile readfile = new ScannerReadFile();
        ArrayList<String> data = readfile.returnData();
        ArrayList<StringLength> names = new ArrayList<StringLength>();
        // create array to be sorted
        for (int i = 0; i < data.size(); i++) {
            names.add(new StringLength(data.get(i)));
        }
        // sort via comparator we just made
        Collections.sort(names, new sortByLength());
        for (int j = 0; j < names.size(); j++) {
            System.out.println(names.get(j));
        }
    }
}

/** Output
 * 
 * ** BEFORE **
    Michael
    Christopher
    Jessica
    Matthew
    Ashley
    Jennifer
    Joshua
    Amanda
    Daniel
    David
    James
    Robert
    John
    Joseph
    Andrew
    Ryan
    Brandon
    Jason
    Justin
    Sarah
    William
    Jonathan
    Stephanie
    Brian
    Nicole
    Nicholas
    Anthony
    Heather
    Eric
    Elizabeth
    Adam
    Megan
    Melissa
    Kevin
    Steven
    Thomas
    Timothy
    Christina
    Kyle
    Rachel
    Laura
    Lauren
    Amber
    Brittany
    Danielle
    Richard
    Kimberly
    Jeffrey
    Amy
    Crystal
    Michelle
    Tiffany
    Jeremy
    Benjamin
    Mark
    Emily
    Aaron
    Charles
    Rebecca
    Jacob
    Stephen
    Patrick
    Sean
    Erin
    Zachary
    Jamie
    Kelly
    Samantha
    Nathan
    Sara
    Dustin
    Paul
    Angela
    Tyler
    Scott
    Katherine
    Andrea
    Gregory
    Erica
    Mary
    Travis
    Lisa
    Kenneth
    Bryan
    Lindsey
    Kristen
    Jose
    Alexander
    Jesse
    Katie
    Lindsay
    Shannon
    Vanessa
    Courtney
    Christine
    Alicia
    Cody
    Allison
    Bradley
    Samuel
    Shawn
    April
    Derek
    Kathryn
    Kristin
    Chad
    Jenna
    Tara
    Maria
    Krystal
    Jared
    Anna
    Edward
    Julie
    Peter
    Holly
    Marcus
    Kristina
    Natalie
    Jordan
    Victoria
    Jacqueline
    Corey
    Keith
    Monica
    Juan
    Donald
    Cassandra
    Meghan
    Joel
    Shane
    Phillip
    Patricia

 * ** AFTER **
    Amy
    John
    Ryan
    Eric
    Adam
    Kyle
    Mark
    Sean
    Erin
    Sara
    Paul
    Mary
    Lisa
    Jose
    Cody
    Chad
    Tara
    Anna
    Juan
    Joel
    David
    James
    Jason
    Sarah
    Brian
    Megan
    Kevin
    Laura
    Amber
    Emily
    Aaron
    Jacob
    Jamie
    Kelly
    Tyler
    Scott
    Erica
    Bryan
    Jesse
    Katie
    Shawn
    April
    Derek
    Jenna
    Maria
    Jared
    Julie
    Peter
    Holly
    Corey
    Keith
    Shane
    Ashley
    Joshua
    Amanda
    Daniel
    Robert
    Joseph
    Andrew
    Justin
    Nicole
    Steven
    Thomas
    Rachel
    Lauren
    Jeremy
    Nathan
    Dustin
    Angela
    Andrea
    Travis
    Alicia
    Samuel
    Edward
    Marcus
    Jordan
    Monica
    Donald
    Meghan
    Michael
    Jessica
    Matthew
    Brandon
    William
    Anthony
    Heather
    Melissa
    Timothy
    Richard
    Jeffrey
    Crystal
    Tiffany
    Charles
    Rebecca
    Stephen
    Patrick
    Zachary
    Gregory
    Kenneth
    Lindsey
    Kristen
    Lindsay
    Shannon
    Vanessa
    Allison
    Bradley
    Kathryn
    Kristin
    Krystal
    Natalie
    Phillip
    Jennifer
    Jonathan
    Nicholas
    Brittany
    Danielle
    Kimberly
    Michelle
    Benjamin
    Samantha
    Courtney
    Kristina
    Victoria
    Patricia
    Stephanie
    Elizabeth
    Christina
    Katherine
    Alexander
    Christine
    Cassandra
    Jacqueline
    Christopher
 */
