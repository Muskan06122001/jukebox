package dao;


import org.apache.commons.lang3.StringUtils;
import util.Dbutil;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Registration {


    static int UserId;
    static Scanner sc = new Scanner(System.in);

    public static void Choose() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        Registration r = new Registration();
        System.out.println("\n");
        System.out.printf("%s\n",
                StringUtils.center("***-----Welcome To My JukeBox-----***\n", 100));
        System.out.println("1)----->LogIn\n");
        System.out.println("2)----->Create Account\n");
        System.out.println("3)----->Exit\n");

        System.out.println("Enter Your Choice");
        int choose = sc.nextInt();
        //int choose=0;

        switch (choose) {
            case 1:
                System.out.println("1)----->LogIn Account\n");
                register();
                //Play.YourChoice();
                Play.SongsTable();
                break;
            case 2:
                System.out.println("2)----->Create Account\n");
                createAccount();
                Play.SongsTable();
                break;
            case 3:
                System.out.println("3)----->Exit\n");
                r.Logout();
                break;
            default:
                System.out.println("Invalid choice");
                Choose();
                break;
        }

    }


    public static void register() throws SQLException {
        Registration r = new Registration();
         Dbutil.getConnection();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Your Name");
        String name = sc.next();
        System.out.println("Enter Your Password");
        sc.nextLine();
        String password = sc.nextLine();
        check(name, password);
        r.UserIdCheck(name, password);
    }

    public static void check(String name, String password) throws SQLException {
        Connection connection = Dbutil.getConnection();
        Statement st = connection.createStatement();
        ResultSet res = st.executeQuery("select count(userName) from Registration where username='" + name + "' AND password='" + password + "'");
        res.next();
        int value = res.getInt(1);
        if (value == 0) {
            System.out.println("There is No Account With this UserName,Please Register");
            createAccount();
        } else {
            System.out.println("SuccessFully LoggedIn !!! ");
        }
    }

    public static void createAccount() throws SQLException {
        Registration r = new Registration();
        Connection connection = Dbutil.getConnection();
        Statement st = connection.createStatement();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Name");
        String name = sc.nextLine();
        System.out.print("Enter Password");
        sc.nextLine();
        String password = sc.nextLine();
        st.executeUpdate("insert into Registration(username,password) values('" + name + "','" + password + "')");
        System.out.println("Thank you For Registration");
        r.UserIdCheck(name, password);
    }

    public String Logout() {
        System.out.printf("%s\n",
                StringUtils.center("***-----You Are LoggedOut-----***\n", 100));
        return "**** You Are LoggedOut ****";
    }

    public int UserIdCheck(String name, String password) throws SQLException {
        Connection connection = Dbutil.getConnection();
        Statement st = connection.createStatement();
        ResultSet res = st.executeQuery("select * from Registration  where username='" + name + "' AND password='" + password + "'");
        System.out.println("--------------------");
        res.next();
        UserId = res.getInt(3);
        System.out.println(UserId);
        return UserId;
    }
}




