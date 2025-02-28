import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

   Connect connect =new Connect();

   while (true) {
       System.out.println("do u have account");
       String account = scanner.nextLine();
       if (account.equalsIgnoreCase("yes")) {
           System.out.println("enter user name and password");
           String username = scanner.nextLine();
           String pass = scanner.nextLine();

           if (connect.getItems(username, pass)) {
               System.out.println("successfully loged");
               System.out.println("if u want enter new contact typr yes,");
               String login = scanner.nextLine();

               if (login.equalsIgnoreCase("yes")) {
                   System.out.println("enter contact name");
                   String name = scanner.nextLine();
                   System.out.println("enter contact no");
                   String contact = scanner.nextLine();
                   connect.setcontact(name, contact);


               } else if (login.equalsIgnoreCase("no")) {
                   connect.getcontacts();


               } else {
                   System.out.println("please enter yes or no");
               }


           }

       } else if (account.equalsIgnoreCase("no")) {
           System.out.println("please register heare");
           System.out.println("username");
           String user_name = scanner.nextLine();
           System.out.println("password");
           String password = scanner.nextLine();

           connect.signup( user_name, password);


       }else if ((account.equalsIgnoreCase("exit"))){
           break;
       }

   }







    }
}