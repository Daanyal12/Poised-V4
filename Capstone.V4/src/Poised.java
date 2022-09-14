import java.sql.*;
import java.time.LocalDate;
import java.util.*;

/**
 * POISED MAIN
 * Main class
 *
 * @author Daanyal Kamish
 * @version Poised Project tracker 1.4
 */

public class Poised {

    /**
     * <p>This is the MAIN class Poised which does all the functionality such as:</p>
     * <br>
     * <p>Displaying Menus</p>
     *<br>
     * Writing to database
     *<br>
     * Taking User input
     *
     * @param args handles all the arguments in this main method
     */

    //  set main to run all users inputs
    public static void main(String[] args) {

        //welcome user to program and take their input
        System.out.println(" Welcome to Poised project Tracker ");
        System.out.println("< - - - - - - - - - - - - - - - - >");

        //Declare scanner
        Scanner sc = new Scanner(System.in);

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
                    "root",
                    "Kamish02"
            );

            Statement statement = connection.createStatement();

            //while userinput is not quit run the loop
            label:
            while (true) {

                //prompt user to type new to create new project by typing new
                System.out.println("""
                        enter what youd like to do?:
                        ____________________________\s
                        enter 1,2 or 3:\s
                        1 - project\s
                        2 - update\s
                        3 - Finalize\s
                        4 - view Overdue tasks
                        5 - View All
                        6 - quit\s
                        7 - Search for projects""");

                String userInput = sc.next();

                try {

                    //The reason this is an if statement is so that later on when we add more functionality we can just
                    //add more if statements
                    switch (userInput) {

                        case "1":
                            //calls the make project function
                            makeProject();
                            break;

                        case "6":
                            System.out.println("Good Bye");
                            break label;

                        case "2":
                            Update();

                            break;
                        case "3":
                            Finalize();

                            break;
                        case "4":
                            System.out.println("view over due tasks");
                            overDue();

                            break;
                        case "5":
                            viewAll(statement);

                            break;

                        case "7":
                            SearchForProject(statement);

                            break;

                        default:
                            System.out.println("Input invalid. Try again.\n");
                            break;
                    }

                    //catch any errors from the input
                } catch (RuntimeException e) {
                    System.out.println("something went wrong while selecting an operation");
                }
            }

            //Catch any errors over all
        } catch (SQLException e) {
            System.out.println("SQL Error has occoured");
        }

    }

    /**
     * MAKE PROJECT
     * This is the Make Project method that creates the project object when called in the main
     */
    public static void makeProject() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
                    "root",
                    "Kamish02"
            );
            Statement statement = connection.createStatement();

            //declare Scanner
            Scanner sc = new Scanner(System.in);

            System.out.println("making project");

            //runs the inputs for the purpose of creating a new Project object

            System.out.println("enter the Project number : ");
            String pjtNum = sc.nextLine();

            System.out.println("enter the type of building eg. house,apartment,mansion : ");
            String buildType = sc.nextLine();

            System.out.println("enter the Address of the building eg. no.8 Block rd  : ");
            String address = sc.nextLine();

            System.out.println("enter the deadline eg.2022-07-05 : ");
            String deadline = sc.nextLine();

            System.out.println("enter customers name :");
            String custName = sc.nextLine();

            System.out.println("enter customers number :");
            String custNum = sc.nextLine();

            System.out.println("enter the customers email :");
            String custEmail = sc.nextLine();

            System.out.println("enter the customers Address :");
            String custAddy = sc.nextLine();

            //if the pjtNAme is left empty then do this
            System.out.println("enter the name of the project: eg. Mike Tysons house");
            String pjtName = sc.nextLine();
            if (pjtName.isEmpty()){
                pjtName = custName + " " + buildType;
            }

            System.out.println("enter the contractors name : ");
            String contractorsName = sc.nextLine();

            System.out.println("enter the contractors number : ");
            String contractorsNum = sc.nextLine();

            System.out.println("enter the contractors email : ");
            String contractorsEmail = sc.nextLine();

            System.out.println("enter the contractors address : ");
            String contractorsAddy = sc.nextLine();

            System.out.println("enter the architects name : ");
            String architectsName = sc.nextLine();

            System.out.println("enter the architects number : ");
            String architectsNum = sc.nextLine();

            System.out.println("enter the architects email : ");
            String architectsEmail = sc.nextLine();

            System.out.println("enter the architects address : ");
            String architectsAddress = sc.nextLine();

            System.out.println("enter the ERF no. : ");
            int erf1 = sc.nextInt();

            System.out.println("enter the total amount : ");
            int amount = sc.nextInt();

            System.out.println("enter the total paid : ");
            int paid = sc.nextInt();

            //prints the toString with all the users inputed results
            System.out.println("Project name : " + pjtName + "\n"
                    + "Project num : " + pjtNum + "\n"
                    + "Building type : " + buildType + "\n"
                    + "Address : " + address + "\n"
                    + "Deadline : " + deadline + "\n"
                    + "ERF : " + erf1 + "\n"
                    + "Total amount Due :" + amount + "\n"
                    + "Total amount paid :" + paid +"\n"
                    +"Complete :"+ "False "+"\n"
                    + "Customer Name : "+custName+"\n"
                    +"Customer number : "+custNum  +"\n"
                    +"Customer Email : "+custEmail + "\n"
                    +"Customer Address : "+custAddy);


            //once thats done the user gets propted with the option to edit the deadline or the total paid
            System.out.println("would you like to edit anything? yes or no: ");
            String edit = sc.next();

            //if the user selects yes the option of dead line or total paid gets offered
            if (edit.equals("yes")) {
                System.out.println("what would you like to edit?\ndeadline - d:\ntotal paid - p");
                String edit2 = sc.next();

                //this is in place so that the above next doesnt skip over the input below
                String help = sc.nextLine();

                //if the user selects d the set deadline method gets called using the users new input being the new deadline
                if (edit2.equals("d")) {

                    System.out.println("enter new deadline dd mm yyyy");
                    String newDeadline = sc.nextLine();
                    System.out.println("goodbye");
                    statement.executeUpdate("INSERT INTO Project VALUES('"+pjtNum+"','"+pjtName+"','"+buildType+"','"+address+"','"+erf1+"','"+amount+"','"+paid+"','"+newDeadline+"','false')");
                    statement.executeUpdate("INSERT INTO Customer VALUES('"+pjtNum+"','"+custName+"','"+custEmail+"','"+custNum+"','"+custAddy+"')");
                    statement.executeUpdate("INSERT INTO Contractor VALUES('"+pjtNum+"','"+contractorsName+"','"+contractorsEmail+"','"+contractorsNum+"','"+contractorsAddy+"')");
                    statement.executeUpdate("INSERT INTO Architect VALUES('"+pjtNum+"','"+architectsName+"','"+architectsEmail+"','"+architectsNum+"','"+architectsAddress+"')");
                    System.out.println("Query complete, Added to database ");


                    //if the user selects p the set total method gets called with the users input of the new total
                } else if (edit2.equals("p")) {

                    System.out.println("enter new total paid");
                    int newTot = sc.nextInt();

                    System.out.println("goodbye");
                    statement.executeUpdate("INSERT INTO Project VALUES('"+pjtNum+"','"+pjtName+"','"+buildType+
                                    "','"+address+"','"+erf1+"','"+amount+"','"+newTot+"','"+deadline+"','false')");

                    statement.executeUpdate("INSERT INTO Customer VALUES('"+pjtNum+"','"+custName+"','"+custEmail+
                                                                            "','"+custNum+"','"+custAddy+"')");

                    statement.executeUpdate("INSERT INTO Contractor VALUES('"+pjtNum+"','"+contractorsName+"','"
                                                +contractorsEmail+"','"+contractorsNum+"','"+contractorsAddy+"')");

                    statement.executeUpdate("INSERT INTO Architect VALUES('"+pjtNum+"','"+architectsName+"','"
                                                +architectsEmail+"','"+architectsNum+"','"+architectsAddress+"')");

                    System.out.println("Query complete, Added to database ");

                }

            //elif the user enters no Write to the file without edits
            } else if (edit.equals("no")) {

                System.out.println("goodbye");

                statement.executeUpdate("INSERT INTO Project VALUES('"+pjtNum+"','"+pjtName+"','"+buildType+"','"
                                            +address+"','"+erf1+"','"+amount+"','"+paid+"','"+deadline+"','false')");

                statement.executeUpdate("INSERT INTO Customer VALUES('"+pjtNum+"','"+custName+"','"+custEmail
                                                                                +"','"+custNum+"','"+custAddy+"')");

                statement.executeUpdate("INSERT INTO Contractor VALUES('"+pjtNum+"','"+contractorsName+"','"
                                            +contractorsEmail+"','"+contractorsNum+"','"+contractorsAddy+"')");

                statement.executeUpdate("INSERT INTO Architect VALUES('"+pjtNum+"','"+architectsName+"','"
                                            +architectsEmail+"','"+architectsNum+"','"+architectsAddress+"')");

                System.out.println("Query complete, Added to database ");
                //printAllFromTable(statement);


                //Error handling
            } else {
                System.out.println("Invalid input");
            }

        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    /**
     * UPDATE
     * This method updates the Desired project that the user enters
     *
     * @throws SQLException checks for any errors when writing to database or reading from it
     */
    public static void Update() throws SQLException {

        //set connection
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
                "root",
                "Kamish02"
        );

        //create statement
        Statement statement = connection.createStatement();

        //Declare file inputs and scanner inputs
        Scanner ip = new Scanner(System.in);

        //prompt user to enter a task number and then search for it with a while loop
        System.out.println("Enter the task number you would like to update ");
        ResultSet Results = statement.executeQuery("SELECT ProjectNum, ProjectName, BuildType, Address, ERF, " +
                                                        "totalAmount, totalPaid, Deadline, Finalized FROM Project");

        while (Results.next()) {
            System.out.println("\nProject number: "
                    + Results.getInt("ProjectNum") + "\n"
                    +"Project name : " + Results.getString("ProjectName") + "\n"
                    +"Build type : " + Results.getString("BuildType") + "\n"
                    +"Address : " + Results.getString("Address")+"\n"
                    +"Erf : "+ Results.getInt("ERF")+"\n"
                    +"Total amount : " + Results.getInt("totalAmount")+"\n"
                    +"Total paid : " + Results.getInt("Totalpaid")+"\n"
                    +"Deadline : "+ Results.getDate("Deadline")+"\n"
                    +"Finalized : "+Results.getString("Finalized")+"\n"
            );
        }

        //take user input after the tasks have been displayed
        String userInput = ip.next();

        //Ask them what they would like to do
        System.out.println("""
                What would you like to edit?\s
                1 - Total due\s
                2 - Total Paid\s
                3 - Deadline""");

        String userChoice = ip.next();

        //whatever their desired input perform the following
        switch (userChoice) {

            case "1" -> {

                System.out.println("Enter new Total amount");
                int newTotalAmount = ip.nextInt();

                statement.executeUpdate("UPDATE Project SET totalAmount='"+newTotalAmount
                                                        +"' WHERE ProjectNum='"+userInput+"'");

                System.out.println("Total Amount has been updated successfully to "+ newTotalAmount);
            }
            case "2" -> {

                System.out.println("Enter new total paid ");
                int newTotal = ip.nextInt();

                statement.executeUpdate("UPDATE Project SET totalPaid='"+newTotal
                                                +"' WHERE ProjectNum='"+userInput+"'");

                System.out.println("Total Paid has been updated successfully to "+ newTotal);
            }

            case "3" -> {

                System.out.println("Enter new Deadline");
                //dummy input prevents skipping over input
                String dummy = ip.nextLine();
                String newDeadline = ip.nextLine();

                statement.executeUpdate("UPDATE Project SET Dealine='"+newDeadline+"' WHERE ProjectNum='"+userInput+"'");
                System.out.println("Deadline has been updated successfully to "+ newDeadline);

            }
            default -> System.out.println("Invalid input");
        }

    }

    /**
     * FINALIZE
     * this method Finalizes the desired project of the user by asking the user to enter the projects number
     * finalized projects mean the project is complete
     *
     * @throws SQLException checks for any errors when reading or writing from the database
     */
    public static void Finalize() throws SQLException {
        //Declare input
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
                "root",
                "Kamish02"
        );

        //Create connection
        Statement statement = connection.createStatement();

        //declare scanner
        Scanner ip = new Scanner(System.in);

        //Does the same as update method asks the user which task they would like to finalize

        System.out.println("Enter the task number you are looking for ");

        //take user input
        String userInput = ip.next();
        statement.executeUpdate("UPDATE Project SET Finalized='True' WHERE ProjectNum='"+userInput+"'");
        System.out.println("Updated Successfully");
        ResultSet results = statement.executeQuery("SELECT ProjectNum, totalAmount, totalPaid FROM Project WHERE " +
                "ProjectNum='"+userInput+"'");

        //Collects all the integer data from the while loop
        int ProjectNum = 0;
        int totalAmount =0;
        int totalPaid =0;

        //fetches the Project number, totalpaid and totalamount and adds it to the variables above
        while (results.next()) {

            //adds all the data to the variables
            ProjectNum += results.getInt("ProjectNum");
            totalAmount += results.getInt("totalAmount");
            totalPaid += results.getInt("totalPaid");

        }

        //gets the customers details related to the project number (Name email phone number) for the invoice
        ResultSet results2 = statement.executeQuery("SELECT name, Email, Pnumber FROM Customer WHERE ProjectNum='"+userInput+"'");

        //Array list to store the data from the while loop
        ArrayList<String> CustName = new ArrayList<>();
        ArrayList<Integer> CustNum = new ArrayList<>();
        ArrayList<String> CustEmail = new ArrayList<>();

        //Adds all the string data to each individual list so that they can be accessed once extracted from the database
        while (results2.next()) {

            //uses .add to add results to the database
            CustName.add(results2.getString("name"));
            CustNum.add(results2.getInt("Pnumber"));
            CustEmail.add(results2.getString("Email"));
        }

        //prints space
        System.out.println();

        //calculates the difference if there is one. to be displayed on the invoice
        int sum = totalAmount-totalPaid;

        //if the amount is paid up write straight to the file without generating an invoice
        if (totalAmount>totalPaid) {

            //prints out the invoice
            System.out.println(
                    "INVOICE OF PROJECT no. " + ProjectNum + "\n" +
                            "Customer name : " +CustName.get(0)  + "\n" +
                            "Customer number : " +CustNum.get(0)  + "\n" +
                            "Customer Email : " +CustEmail.get(0)  + "\n" +
                            "Total paid R" + totalPaid + "\n" +
                            "Total amount R" + totalAmount + "\n" +
                            "Amount Still Due R" + sum
            );

        } else {

            //if the total paid equals zero no invoice is needed as the amounts are fully paid up
            System.out.println("Task finalized successfully no INVOICE");

        }

        System.out.println("\n");

    }

    /**
     * <p>OVER DUE</p>
     * This method displays all the tasks that are over due to the user
     *
     * @throws SQLException Checks for reading or writing errors to and from the database
     */


    public static void overDue() throws SQLException {

        //declare the connection
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
                "root",
                "Kamish02"
        );

        //Create statement
        Statement statement = connection.createStatement();

        //get the current date using a variable
        LocalDate currentDate = LocalDate.now();

        //prints the current date for the user to see
        System.out.println("Todays date is :"+currentDate);

        System.out.println();

        //Selects all the tasks where the Data is less than todays date
        ResultSet results = statement.executeQuery("SELECT * FROM Project WHERE Deadline < CURRENT_DATE()");

        //prints out all that data
        while (results.next()){

            //print out all the data
            System.out.println(
                    results.getInt("ProjectNum") + ", "
                            + results.getString("ProjectName") + ", "
                            + results.getString("BuildType") + ", "
                            + results.getString("Address") +", "
                            + results.getInt("ERF")+ ", "
                            + results.getInt("totalAmount")+", "
                            + results.getInt("totalPaid")+", "
                            + results.getDate("deadline")+", "
                            + results.getString("Finalized")
            );
        }
    }

    /**
     * View All
     *
     * views all the data in the database
     * @param statement takes in the Statement parameter which allows the function to connect to the database
     * @throws SQLException if there are any errors to and from the database
     */

    public static void viewAll(Statement statement) throws SQLException{

        //Selects all the data from the database using there names
        ResultSet results = statement.executeQuery("SELECT ProjectNum, ProjectName, BuildType, Address, ERF," +
                " totalAmount, totalPaid, deadline, Finalized FROM Project");

        //whiles data in the database print it
        while (results.next()) {
            System.out.println(
                              results.getInt("ProjectNum") + ", "
                            + results.getString("ProjectName") + ", "
                            + results.getString("BuildType") + ", "
                            + results.getString("Address") +", "
                            + results.getInt("ERF")+ ", "
                            + results.getInt("totalAmount")+", "
                            + results.getInt("totalPaid")+", "
                            + results.getDate("deadline")+", "
                            + results.getString("Finalized")
            );
        }
    }

    /**
     * Search for projects
     * searchs for a project in the database and displays it
     *
     * @param statement pulls from the database connection in the main
     * @throws SQLException checks for any errors related to SQL
     */
    public static void SearchForProject(Statement statement)throws SQLException{
        Scanner sc = new Scanner(System.in);

        //Enter the number youd like to search for
        System.out.println("enter the project number you are looking for");
        String userInput = sc.nextLine();

        //Selects all from the database and print it in the while loop
        ResultSet results = statement.executeQuery("SELECT * FROM Project WHERE ProjectNum='"+userInput+"'");

        //whiles data in the database print it
        while (results.next()) {
            System.out.println("Project number : "+
                    results.getInt("ProjectNum") + "\n"+
                    "Project Name : "
                            + results.getString("ProjectName") + "\n"+
                    "Building type : "
                            + results.getString("BuildType") + "\n"+
                    "Address : "
                            + results.getString("Address") +"\n"+
                    "ERF : "
                            + results.getInt("ERF")+ "\n"+
                    "Total Amount : "
                            + results.getInt("totalAmount")+"\n"+
                    "Total Paid : "
                            + results.getInt("totalPaid")+"\n"+
                    "Deadline : "
                            + results.getDate("deadline")+"\n"+
                    "Finalized : "
                            + results.getString("Finalized")
            );
        }
    }

}

