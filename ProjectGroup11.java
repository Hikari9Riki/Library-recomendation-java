/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project.group11;

import java.util.Scanner;

/**
 *
 * @author kanek
 */
public class ProjectGroup11 {
    
    public static void clearScreen(){
        for (int i = 0; i < 50 ; i++){
            System.out.println("");
        }
    }

    public static void errorPage(){
        clearScreen();
        System.out.println("Invalid input\n");
    }
    
    public static int errorIntCheck(Scanner scanner){
        int input;
        try{
            input = scanner.nextInt();
            return input;
        } catch (Exception e){
            clearScreen();
            return input= 10000;
        
        }
        
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
 
        while (!exit) {
          
            System.out.println("""
                                                           +--------------------------------+
                                                           |    Library Recommendation      |
                                                           |           System               |
                                                           +--------------------------------+""");
            System.out.println("1. Search by Genre");
            System.out.println("2. Search by Title/Author");
            System.out.println("3. View Saved Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
 
            int choice = errorIntCheck(scanner);
            scanner.nextLine(); // Consume the newline
 
            switch (choice) {
                case 1:
                    genrePage(scanner);
                    break;
                case 2:
                    titleOrAuthorPage(scanner);
                    break;
                case 3:
                    clearScreen();
                    userBookListPage(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the program...");
                    exit = true;
                    break;
                default:
                    clearScreen();
                    System.out.println("Invalid choice. Please try again.");
            }
            
        }
        scanner.close();
    }
 
    public static void showBookDetail (String[][] search, int choice, Scanner scanner){
        for (int i = 1; i < 7; i++){
            System.out.println("* " + search[choice][i] );
            }
        System.out.print("\n\n");
        System.out.println("""
                           1. Add to Book List
                           2. Main Menu""");
        System.out.print("Your choice: ");
        int next = errorIntCheck(scanner);
        if (next == 1){
            addBookListGenre(search[choice][0]);
        } else if( next == 2){
            clearScreen();
        } else{
            errorPage();
        }
        
    }
    
    public static void genrePage(Scanner scanner){
        clearScreen();
        int index;
        System.out.println("""
                                       +--------------------------+
                                       |          Genre           |
                                       +--------------------------+
                           
                           """);
        for (int i = 0; i < genre().length; i++) {
                System.out.printf("%-4d%-15s", (i + 1), genre(i)); // Format: Number with fixed spacing
                if ((i + 1) % 3 == 0) { // New line after every 4 genres
                    System.out.println();
                }
            }
        System.out.println("");
        System.out.print("Your choice: ");
        index = errorIntCheck(scanner);
        if (index > genre().length || index < 0){
            
            errorPage();
            
        } else {
            
            if(index <= genre().length || index > 0){
                clearScreen();
                String genre = genre(index - 1);
                System.out.println(genre);
                String[][] books = books();
                String[][] search = new String[50][7];

                for (int j = 0, a = 0; j < books.length; j++ ){
                    if (genre.equals(books[j][3])){
                        System.arraycopy(books[j], 0, search[a], 0, 7);
                        a++;
                    }
                }


                for (int i = 0; i < search.length; i++) {
                    if (search[i][0] != null){
                        System.out.printf("%-4d%-15s", (i + 1), search[i][1]); // Format: Number with fixed spacing
                        System.out.println();
                    }

                }

                System.out.print("Your choice: ");
                int choice = errorIntCheck(scanner) -1 ;
                clearScreen();
                if (choice != 9999){
                    if (search[choice][0] != null){
                        if (choice < search.length && choice >= 0){
                            showBookDetail(search,choice,scanner);
                        } else {
                            errorPage();
                        }
                    } else {
                        errorPage();
                    }
                }


            } else {
                clearScreen();
                System.out.println("Invalid input");

            }
        }
        
    }
//part2
    
    public static void titleOrAuthorSearch(Scanner scanner, int category){
        
        String[][] books = books();
        String[][] search = new String[150][7];
        int a = 0;
        String Choice = scanner.nextLine();
        clearScreen();
        System.out.println("\"" + Choice + ": \"");
        for (int j = 0; j < books.length; j++ ){
            
                if ((books[j][category].toLowerCase().contains(Choice.toLowerCase()))){
                    System.arraycopy(books[j], 0, search[a], 0, 7);
                    a++;
                }
            }
        if (a > 0){
            for (int i = 0; i < search.length; i++) {
                if (search[i][0] != null){
                    System.out.printf("%-4d%-15s", (i + 1), search[i][1]); // Format: Number with fixed spacing
                    System.out.println();
                }  
            }
            
            System.out.print("Your choice: ");
            int choice = errorIntCheck(scanner) -1 ;
            clearScreen();
            if (choice != 9999){
                if (search[choice][0] != null){
                    if (choice < search.length && choice >= 0){
                        showBookDetail(search,choice,scanner);
                    } else {
                        errorPage();
                    } 
                }else {
                    errorPage();
                }
            } else {
                errorPage();
            }
        } else {
            System.out.println("None\n\n");
        }
        
    }
    public static void titleOrAuthorPage(Scanner scanner){
        clearScreen();
        
        System.out.println("""
                                       +--------------------------+
                                       |    Title  Or  Author     |
                                       +--------------------------+
                           
                           """);
        System.out.println("""
                           1. Book
                           2. Author
                           """);
        System.out.print("Your choice: ");
        int choice = errorIntCheck(scanner);
        scanner.nextLine();
        
        
        switch (choice) {
            case 1:
                choice = 1;
                System.out.println("""
                                                               +--------------------------+
                                                               |           Title          |
                                                               +--------------------------+
                                                                            """);
                System.out.print("Write the title name: ");
                titleOrAuthorSearch(scanner, choice);
                break;
            case 2:
                choice = 2;
                System.out.println("""
                                                               +--------------------------+
                                                               |          Author          |
                                                               +--------------------------+
                                                                            """);
                System.out.print("Write the author name: ");
                titleOrAuthorSearch(scanner, choice);
                break;
            default:
                errorPage();
                break;
        }
        
        
       
    }
    
//part3
    
    
    public static void addBookListGenre(String index){
        String[][] books = books();
        int id = Integer.parseInt(index);
        
        for (int i = 0; i < books.length; i++) {
            if (userBookList[i][0] == index){
                clearScreen();
                System.out.println("Already in the list\n");
                break;
            }
            if (userBookList[i][0] == null){
                for (int j = 0; j < 7; j++) {

                    userBookList[listBook][j] = books[id][j];

                    }
                    listBook ++; 
                    clearScreen();
                    System.out.println("Succesfully add to list");
                    break;
            }
        }
        
        
    }
    
    public static void removeBookList (int index, Scanner scanner){
        for (int i = index; i < (userBookList.length-1); i++) {
                        for (int j = 0; j < 7; j++) {
                            userBookList[i][j] = userBookList[i+1][j];
                        }
        }
        listBook --;
        clearScreen();
        System.out.println("Remove Succesful");
        userBookListPage(scanner);
    }
    
 
    public static String[][] userBookList = new String[50][7];
    public static int listBook = 0;
    
    public static void userBookListPage(Scanner scanner){
        
        System.out.println("""
                                       +--------------------------+
                                       |     User's Book List     |
                                       +--------------------------+
                           
                           """);
        
        if (userBookList[0][0] != null){
            for (int i = 0; i < userBookList.length; i++) {
                if (userBookList[i][0] != null){
                    System.out.printf("%-4d%-15s", (i+1), userBookList[i][1]); // Format: Number with fixed spacing
                    System.out.println();
                }
            }
            
            System.out.print("\n\nYour choice: ");
            int choice = errorIntCheck(scanner) -1 ;
            clearScreen();
            if (userBookList[choice][0] != null){
                if (choice < userBookList.length && choice >= 0){
                    for (int i = 1; i < 7; i++){
                        System.out.println("* " + userBookList[choice][i] );
                    }

                    System.out.print("\n\n");
                    System.out.println("""
                                       1. Back
                                       2. Remove
                                       3. Exit""");
                    System.out.print("Your choice: ");
                    int next = errorIntCheck(scanner);

                switch (next) {
                    case 1:
                        clearScreen();
                        userBookListPage(scanner);
                        break;
                    case 2:
                        removeBookList(choice,scanner);
                        break;
                    case 3:
                        clearScreen();
                        break;
                    default:
                        errorPage();
                        break;
                }

                } else {
                    errorPage();
                }
            } else {
                errorPage();
            }
            
        } else{
            clearScreen();
            System.out.println("User Book List is empty\n");
        }
    }
    
    
    
        
    
    
    public static String[] genre(){
        
        String genre[] = {
                "Technology", 
                "Philosophy", 
                "History", 
                "Revealation", 
                "Medical",
                "Economy", 
                "Bussiness", 
                "Engineering", 
                "Science", 
                "Short Story", 
                "Literature",
                "Review", 
                "Motivation"
                };
        
        return genre;
    }
    
    public static String genre(int index){
        String genre[] = genre();
        return genre[index];
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
    public static String[][] books (){        
        String[][] books = {
                {"0", "The Code Breaker", "Walter Isaacson", "Medical", "The story of CRISPR and the pioneers of genetic editing.", "2021", "L2/R3/Col1/Row3"},
                {"1", "The Innovators", "Walter Isaacson", "Technology", "A history of the people who created the computer and the internet.", "2014", "L1/R1/Col1/Row1"},
                {"2", "Guns, Germs, and Steel", "Jared Diamond", "History", "An exploration of the factors that influenced the development of civilizations.", "1997", "L1/R2/Col1/Row2"},
                {"3", "Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "History", "A narrative of humanity's evolution from hunter-gatherers to a dominant species.", "2011", "L1/R3/Col1/Row3"},
                {"4", "Meditations", "Marcus Aurelius", "Philosophy", "Personal writings by a Roman emperor on Stoic philosophy.", "180", "L1/R4/Col1/Row4"},
                {"5", "The Lean Startup", "Eric Ries", "Business", "A guide to building businesses efficiently through innovation.", "2011", "L1/R5/Col1/Row5"},
                {"6", "Thinking, Fast and Slow", "Daniel Kahneman", "Economy", "A look into the two systems of thought influencing decision-making.", "2011", "L1/R6/Col1/Row6"},
                {"7", "The Art of War", "Sun Tzu", "Philosophy", "Ancient strategies for success in war and life.", "5th Century BC", "L1/R7/Col1/Row7"},
                {"8", "The Structure of Scientific Revolutions", "Thomas S. Kuhn", "Science", "A theory on how scientific paradigms shift.", "1962", "L1/R8/Col1/Row8"},
                {"9", "The Selfish Gene", "Richard Dawkins", "Science", "A revolutionary perspective on evolution and natural selection.", "1976", "L1/R9/Col1/Row9"},
                {"10", "Good to Great", "Jim Collins", "Business", "An analysis of companies that made the leap from good to great.", "2001", "L1/R10/Col1/Row10"},
                {"11", "The 7 Habits of Highly Effective People", "Stephen R. Covey", "Motivation", "A framework for personal and professional effectiveness.", "1989", "L2/R1/Col1/Row1"},
                {"12", "Man's Search for Meaning", "Viktor E. Frankl", "Motivation", "A Holocaust survivor's lessons on finding purpose.", "1946", "L2/R2/Col1/Row2"},
                {"13", "The Wealth of Nations", "Adam Smith", "Economy", "A foundational text in classical economics.", "1776", "L2/R4/Col1/Row4"},
                {"14", "Principles: Life and Work", "Ray Dalio", "Business", "A billionaire investor's guide to decision-making and success.", "2017", "L2/R5/Col1/Row5"},
                {"15", "The Goal", "Eliyahu M. Goldratt", "Engineering", "A novel about process improvement in manufacturing.", "1984", "L2/R6/Col1/Row6"},
                {"16", "The Feynman Lectures on Physics", "Richard P. Feynman", "Science", "A series of physics lectures by a Nobel laureate.", "1964", "L2/R7/Col1/Row7"},
                {"17", "A Brief History of Time", "Stephen Hawking", "Science", "An overview of cosmology and the universe.", "1988", "L2/R8/Col1/Row8"},
                {"18", "The Alchemist", "Paulo Coelho", "Literature", "A mystical story about following one's dreams.", "1988", "L2/R9/Col1/Row9"},
                {"19", "The Prophet", "Kahlil Gibran", "Literature", "A poetic collection of philosophical essays.", "1923", "L2/R10/Col1/Row10"},
                {"20", "Outliers", "Malcolm Gladwell", "Review", "An exploration of the factors behind extraordinary success.", "2008", "L3/R1/Col1/Row1"},
                {"21", "Atomic Habits", "James Clear", "Motivation", "A guide to building good habits and breaking bad ones.", "2018", "L3/R2/Col1/Row2"},
                {"22", "The Innovator's Dilemma", "Clayton M. Christensen", "Business", "A theory of disruptive innovation in business.", "1997", "L3/R3/Col1/Row3"},
                {"23", "The Black Swan", "Nassim Nicholas Taleb", "Economy", "How improbable events shape history and markets.", "2007", "L3/R4/Col1/Row4"},
                {"24", "The Art of Thinking Clearly", "Rolf Dobelli", "Philosophy", "Common cognitive biases and how to avoid them.", "2011", "L3/R5/Col1/Row5"},
                {"25", "Silent Spring", "Rachel Carson", "Science", "An exposé of the environmental impact of pesticides.", "1962", "L3/R6/Col1/Row6"},
                {"26", "The Checklist Manifesto", "Atul Gawande", "Medical", "The power of checklists in complex fields like surgery.", "2009", "L3/R7/Col1/Row7"},
                {"27", "How to Win Friends and Influence People", "Dale Carnegie", "Motivation", "A guide to interpersonal skills and success.", "1936", "L3/R8/Col1/Row8"},
                {"28", "Meditations on First Philosophy", "René Descartes", "Philosophy", "Foundational ideas in Western philosophy.", "1641", "L3/R9/Col1/Row9"},
                {"29", "The History of the Peloponnesian War", "Thucydides", "History", "A detailed account of the Peloponnesian War.", "431 BC", "L3/R10/Col1/Row10"},
                {"30", "The Innovators", "Walter Isaacson", "Technology", "A history of the people who created the computer and the internet.", "2014", "L1/R1/Col1/Row1"},
                {"31", "Guns, Germs, and Steel", "Jared Diamond", "History", "An exploration of the factors that influenced the development of civilizations.", "1997", "L1/R2/Col1/Row2"},
                {"32", "Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "History", "A narrative of humanity's evolution from hunter-gatherers to a dominant species.", "2011", "L1/R3/Col1/Row3"},
                {"33", "Meditations", "Marcus Aurelius", "Philosophy", "Personal writings by a Roman emperor on Stoic philosophy.", "180", "L1/R4/Col1/Row4"},
                {"34", "The Lean Startup", "Eric Ries", "Business", "A guide to building businesses efficiently through innovation.", "2011", "L1/R5/Col1/Row5"},
                {"35", "Thinking, Fast and Slow", "Daniel Kahneman", "Economy", "A look into the two systems of thought influencing decision-making.", "2011", "L1/R6/Col1/Row6"},
                {"36", "The Art of War", "Sun Tzu", "Philosophy", "Ancient strategies for success in war and life.", "5th Century BC", "L1/R7/Col1/Row7"},
                {"37", "The Structure of Scientific Revolutions", "Thomas S. Kuhn", "Science", "A theory on how scientific paradigms shift.", "1962", "L1/R8/Col1/Row8"},
                {"38", "The Selfish Gene", "Richard Dawkins", "Science", "A revolutionary perspective on evolution and natural selection.", "1976", "L1/R9/Col1/Row9"},
                {"39", "Good to Great", "Jim Collins", "Business", "An analysis of companies that made the leap from good to great.", "2001", "L1/R10/Col1/Row10"},
                {"40", "The 7 Habits of Highly Effective People", "Stephen R. Covey", "Motivation", "A framework for personal and professional effectiveness.", "1989", "L2/R1/Col1/Row1"},
                {"41", "Man's Search for Meaning", "Viktor E. Frankl", "Motivation", "A Holocaust survivor's lessons on finding purpose.", "1946", "L2/R2/Col1/Row2"},
                {"42", "The Code Breaker", "Walter Isaacson", "Medical", "The story of CRISPR and the pioneers of genetic editing.", "2021", "L2/R3/Col1/Row3"},
                {"43", "The Wealth of Nations", "Adam Smith", "Economy", "A foundational text in classical economics.", "1776", "L2/R4/Col1/Row4"},
                {"44", "Principles: Life and Work", "Ray Dalio", "Business", "A billionaire investor's guide to decision-making and success.", "2017", "L2/R5/Col1/Row5"},
                {"45", "The Goal", "Eliyahu M. Goldratt", "Engineering", "A novel about process improvement in manufacturing.", "1984", "L2/R6/Col1/Row6"},
                {"46", "The Feynman Lectures on Physics", "Richard P. Feynman", "Science", "A series of physics lectures by a Nobel laureate.", "1964", "L2/R7/Col1/Row7"},
                {"47", "A Brief History of Time", "Stephen Hawking", "Science", "An overview of cosmology and the universe.", "1988", "L2/R8/Col1/Row8"},
                {"48", "The Alchemist", "Paulo Coelho", "Literature", "A mystical story about following one's dreams.", "1988", "L2/R9/Col1/Row9"},
                {"49", "The Prophet", "Kahlil Gibran", "Literature", "A poetic collection of philosophical essays.", "1923", "L2/R10/Col1/Row10"},
                {"50", "Outliers", "Malcolm Gladwell", "Review", "An exploration of the factors behind extraordinary success.", "2008", "L3/R1/Col1/Row1"},
                {"51", "Atomic Habits", "James Clear", "Motivation", "A guide to building good habits and breaking bad ones.", "2018", "L3/R2/Col1/Row2"},
                {"52", "The Innovator's Dilemma", "Clayton M. Christensen", "Business", "A theory of disruptive innovation in business.", "1997", "L3/R3/Col1/Row3"},
                {"53", "The Black Swan", "Nassim Nicholas Taleb", "Economy", "How improbable events shape history and markets.", "2007", "L3/R4/Col1/Row4"},
                {"54", "The Art of Thinking Clearly", "Rolf Dobelli", "Philosophy", "Common cognitive biases and how to avoid them.", "2011", "L3/R5/Col1/Row5"},
                {"55", "Silent Spring", "Rachel Carson", "Science", "An exposé of the environmental impact of pesticides.", "1962", "L3/R6/Col1/Row6"},
                {"56", "The Checklist Manifesto", "Atul Gawande", "Medical", "The power of checklists in complex fields like surgery.", "2009", "L3/R7/Col1/Row7"},
                {"57", "How to Win Friends and Influence People", "Dale Carnegie", "Motivation", "A guide to interpersonal skills and success.", "1936", "L3/R8/Col1/Row8"},
                {"58", "Meditations on First Philosophy", "René Descartes", "Philosophy", "Foundational ideas in Western philosophy.", "1641", "L3/R9/Col1/Row9"},
                {"59", "The History of the Peloponnesian War", "Thucydides", "History", "A detailed account of the Peloponnesian War.", "431 BC", "L3/R10/Col1/Row10"},
                {"60", "The Mythical Man-Month", "Frederick P. Brooks Jr.", "Technology", "Essays on software engineering and project management.", "1975", "L4/R1/Col1/Row1"},
                {"61", "Zero to One", "Peter Thiel", "Business", "Insights on creating innovative startups.", "2014", "L4/R2/Col1/Row2"},
                {"62", "Thinking in Systems", "Donella Meadows", "Science", "An accessible introduction to systems thinking.", "2008", "L4/R3/Col1/Row3"},
                {"63", "Deep Work", "Cal Newport", "Motivation", "A guide to achieving focus and productivity in a distracted world.", "2016", "L4/R4/Col1/Row4"},
                {"64", "Start with Why", "Simon Sinek", "Motivation", "Explores how leaders inspire action by communicating their 'why.'", "2009", "L4/R5/Col1/Row5"},
                {"65", "The Machine That Changed the World", "James P. Womack", "Engineering", "The story of lean production and Toyota's success.", "1990", "L4/R6/Col1/Row6"},
                {"66", "The Innovator's Solution", "Clayton M. Christensen", "Business", "A follow-up to the Innovator's Dilemma, with strategies for innovation.", "2003", "L4/R7/Col1/Row7"},
                {"67", "The Fabric of Reality", "David Deutsch", "Science", "A unified view of physics, computation, and knowledge.", "1997", "L4/R8/Col1/Row8"},
                {"68", "The Wright Brothers", "David McCullough", "History", "The story of the inventors of modern aviation.", "2015", "L4/R9/Col1/Row9"},
                {"69", "Flow", "Mihaly Csikszentmihalyi", "Motivation", "Explores the state of optimal experience and its benefits.", "1990", "L4/R10/Col1/Row10"},
                {"70", "The Wisdom of Crowds", "James Surowiecki", "Economy", "How collective intelligence can solve problems and predict outcomes.", "2004", "L5/R1/Col1/Row1"},
                {"71", "Superforecasting", "Philip E. Tetlock", "Review", "Techniques for making better predictions about the future.", "2015", "L5/R2/Col1/Row2"},
                {"72", "The Fourth Industrial Revolution", "Klaus Schwab", "Technology", "An overview of the technological changes shaping our world.", "2016", "L5/R3/Col1/Row3"},
                {"73", "Being Mortal", "Atul Gawande", "Medical", "A surgeon's reflections on end-of-life care.", "2014", "L5/R4/Col1/Row4"},
                {"74", "Homo Deus: A Brief History of Tomorrow", "Yuval Noah Harari", "History", "Exploring the future of humanity and the implications of technology.", "2015", "L5/R5/Col1/Row5"},
                {"75", "Educated", "Tara Westover", "Literature", "A memoir of growing up in a strict and abusive household and seeking education.", "2018", "L5/R6/Col1/Row6"},
                {"76", "Loonshots", "Safi Bahcall", "Business", "How nurturing crazy ideas leads to breakthroughs.", "2019", "L5/R7/Col1/Row7"},
                {"77", "The Big Short", "Michael Lewis", "Economy", "The story behind the 2008 financial crisis.", "2010", "L5/R8/Col1/Row8"},
                {"78", "The Design of Everyday Things", "Don Norman", "Engineering", "A classic book on user-centered design.", "1988", "L5/R9/Col1/Row9"},
                {"79", "Algorithms to Live By", "Brian Christian and Tom Griffiths", "Science", "How computer algorithms can optimize decision-making.", "2016", "L5/R10/Col1/Row10"},
                {"80", "The Tipping Point", "Malcolm Gladwell", "Review", "How small changes can create a tipping point for widespread impact.", "2000", "L6/R1/Col1/Row1"},
                {"81", "The Road to Character", "David Brooks", "Philosophy", "A look at building a life of meaning and inner virtue.", "2015", "L6/R2/Col1/Row2"},
                {"82", "Dare to Lead", "Brené Brown", "Motivation", "A guide to courageous leadership and embracing vulnerability.", "2018", "L6/R3/Col1/Row3"},
                {"83", "Team of Rivals", "Doris Kearns Goodwin", "History", "The political genius of Abraham Lincoln.", "2005", "L6/R4/Col1/Row4"},
                {"84", "Range", "David Epstein", "Review", "How generalists thrive in a specialized world.", "2019", "L6/R5/Col1/Row5"},
                {"85", "The Culture Code", "Daniel Coyle", "Business", "Unlocking the secrets of highly successful groups.", "2018", "L6/R6/Col1/Row6"},
                {"86", "Factfulness", "Hans Rosling", "Science", "A data-driven look at why the world is better than we think.", "2018", "L6/R7/Col1/Row7"},
                {"87", "Why Nations Fail", "Daron Acemoglu and James A. Robinson", "Economy", "The origins of power, prosperity, and poverty.", "2012", "L6/R8/Col1/Row8"},
                {"88", "Drive", "Daniel H. Pink", "Motivation", "The surprising truth about what motivates us.", "2009", "L6/R9/Col1/Row9"},
                {"89", "The Power of Habit", "Charles Duhigg", "Motivation", "How habits work and how to change them.", "2012", "L6/R10/Col1/Row10"},
                {"90", "Lean In", "Sheryl Sandberg", "Motivation", "A call to action for women to achieve their ambitions.", "2013", "L7/R1/Col1/Row1"},
                {"91", "Bad Blood", "John Carreyrou", "Medical", "The scandal behind the rise and fall of Theranos.", "2018", "L7/R2/Col1/Row2"},
                {"92", "The Hard Thing About Hard Things", "Ben Horowitz", "Business", "Insights on building and running a startup.", "2014", "L7/R3/Col1/Row3"},
                {"93", "The Everything Store", "Brad Stone", "Business", "A biography of Jeff Bezos and the rise of Amazon.", "2013", "L7/R4/Col1/Row4"},
                {"94", "What Got You Here Won't Get You There", "Marshall Goldsmith", "Motivation", "How to overcome personal and professional roadblocks.", "2007", "L7/R5/Col1/Row5"},
                {"95", "Switch", "Chip Heath and Dan Heath", "Motivation", "How to make change happen when it's hard.", "2010", "L7/R6/Col1/Row6"},
                {"96", "The Omnivore's Dilemma", "Michael Pollan", "Science", "An exploration of the food choices we make.", "2006", "L7/R7/Col1/Row7"},
                {"97", "Lifespan", "David A. Sinclair", "Medical", "Why we age and how we can stop it.", "2019", "L7/R8/Col1/Row8"},
                {"98", "Thinking About Crime", "James Q. Wilson", "Philosophy", "An analysis of crime and its prevention.", "1975", "L7/R9/Col1/Row9"},
                {"99", "The Better Angels of Our Nature", "Steven Pinker", "History", "Why violence has declined over time.", "2011", "L7/R10/Col1/Row10"},
                {"100", "An Astronaut's Guide to Life on Earth", "Chris Hadfield", "Motivation", "Life lessons from space exploration.", "2013", "L8/R1/Col1/Row1"},
                {"101", "How to Avoid a Climate Disaster", "Bill Gates", "Science", "Solutions for the climate crisis.", "2021", "L8/R2/Col1/Row2"},
                {"102", "The Upside of Stress", "Kelly McGonigal", "Motivation", "Why stress can be good for you and how to embrace it.", "2015", "L8/R3/Col1/Row3"},
                {"103", "Extreme Ownership", "Jocko Willink and Leif Babin", "Business", "Leadership principles learned from Navy SEALs.", "2015", "L8/R4/Col1/Row4"},
                {"104", "Measure What Matters", "John Doerr", "Business", "How objectives and key results drive success.", "2018", "L8/R5/Col1/Row5"},
                {"105", "The Art of Learning", "Josh Waitzkin", "Motivation", "A journey into mastery from a chess prodigy.", "2007", "L8/R6/Col1/Row6"},
                {"106", "Mastery", "Robert Greene", "Motivation", "How to unlock your potential for greatness.", "2012", "L8/R7/Col1/Row7"},
                {"107", "The Power Broker", "Robert A. Caro", "History", "The story of Robert Moses and the building of New York City.", "1974", "L8/R8/Col1/Row8"},
                {"108", "Collapse", "Jared Diamond", "History", "How societies choose to fail or succeed.", "2005", "L8/R9/Col1/Row9"},
                {"109", "Crucial Conversations", "Kerry Patterson et al.", "Motivation", "Tools for having difficult conversations effectively.", "2002", "L8/R10/Col1/Row10"}
            };
        
        return books;
    }
    
    
}
