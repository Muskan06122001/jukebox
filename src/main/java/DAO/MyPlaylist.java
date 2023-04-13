package DAO;


import Bean.Songs;
import Util.Dbutil;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import Exception.NotANumberException;
import Exception.TableAlreadyCreatedException;
import Exception.InvalidChoiceException;
import org.apache.commons.lang3.StringUtils;

public class MyPlaylist {

    static Scanner sc = new Scanner(System.in);
    static String result;



    public  String createdTableAllPlaylist() throws SQLException {
        Connection connection = Dbutil.getConnection();
        Statement st = connection.createStatement();
        try {
            boolean res = st.execute("create table MyAllPlayList(UserId int ,NameOfPlayList varchar(100),SongId int ,foreign key(SongId) references mysongs(SongId))");
            if (!res) {
                System.out.println("Successful");
            }else {
                throw new TableAlreadyCreatedException("Table Already created");
            }

        }catch(TableAlreadyCreatedException | SQLSyntaxErrorException e){
            System.out.println("Table already Exist"+e);
        }
        return "Table creates";
    }

    public String createdPlaylist() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        try {
            Connection connection = Dbutil.getConnection();
            Statement st = connection.createStatement();
            int id1 = 0;

            List<String> li = new ArrayList<>();

            List<Integer> li2 = new ArrayList<>();
            System.out.println("Press Yes or no");
            String res1 = sc.next();
            if (res1.equalsIgnoreCase("yes")) {
                System.out.println("Enter name of your PlayList");
                String PlayListName = sc.next();
                int re=st.executeUpdate("insert into MyAllPlayList (UserId,NameOfPlayList) values ('"+Registration.UserId+"','"+PlayListName+"')");
                //("Insert into fruitgroup (gropId,GroupName) values ('" + id + "','" + name + "')");
                System.out.println(" *** PlayList created *** ");
            }
        }catch (SQLException e){
            System.out.println(e);
        }
            return "PlayList Created \n";
    }

    public static void addSongsToList() throws SQLException {
        Connection connection = Dbutil.getConnection();
        Statement st = connection.createStatement();
        int id1 = 0;
        boolean flag;
        List<String> li = new ArrayList<>();
        List<String> li2 = new ArrayList<>();

        ResultSet re=st.executeQuery("select * from MyAllPlayList where UserId='"+Registration.UserId+"'");
        while(re.next()){
            li2.add(re.getString(2));
        }
        System.out.println(" *** Available PlayList are *** \n");
        System.out.println("------" + li2 + "------\n");

        do {
            flag=true;
            System.out.println("Do you want to add songs ");
            String re2 = sc.next();

            if (re2.equalsIgnoreCase("Yes")) {
                System.out.println("In which PlayList do you want to add song ? ");
                String c = sc.next();

                    if (li2.contains(c)) {

                        System.out.println("Enter the name of Song");
                        String name = sc.next();

                        ResultSet r1 = st.executeQuery("select * from mysongs");
                        while (r1.next()) {
                            li.add(r1.getString(2));  // song name
                        }
                        ResultSet r2 = st.executeQuery("select SongId from mysongs where songName='" + name + "'");
                        while (r2.next()) {
                            id1 = (r2.getInt(1));
                        }

                        if (li.contains(name)) {
                            System.out.println(id1);
                            st.executeUpdate("Insert into MyAllPlayList  values('" + Registration.UserId + "', '" + c + "','" + id1 + "')");
                            System.out.println("------ Song Added ------ \n");

                        } else {
                            System.out.println("Thanks for Your Response !!! \n");     //song name done with songId
                        }
                    } else {
                        System.out.println(" ***  sorry , This Play List doesn't exist *** \n");

                    }

            } else {
                System.out.println("Thanks for Your Response !!! \n");
                flag=false;
            }
        }while (flag);
    }


    public static void playSongs() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException {
        Scanner sc = new Scanner(System.in);
        Connection connection = Dbutil.getConnection();
        Statement st = connection.createStatement();
        int re2 = 0;
        int re3 = 0;
        ResultSet r1;
        boolean flag;
        List<Integer> li4 = new ArrayList<>();
        List<String> li6 = new ArrayList<>();
        List<String> li7 = new ArrayList<>();
        do {
            flag = true;
            System.out.println("\t *** Available PlayList are *** \t \n");
            ResultSet res = st.executeQuery("select * from MyAllPlayList where UserId='" + Registration.UserId + "' ");
            while (res.next()) {
                li7.add(res.getString(2));  // Available PlayList are
            }
                System.out.println(li7 + "\n");
                System.out.println("From Which playList Do You want to listen Songs ? ");
                String playList = sc.next();
                if (li7.contains(playList)) {
                    System.out.println("  \t  *** Available Songs are *** \t \n");
                    ResultSet res2 = st.executeQuery("select * from MyAllPlayList where NameOfPlayList='" + playList + "'");
                    while (res2.next()) {
                        li4.add(res2.getInt(3));  //SongId
                    }

                    Iterator<Integer> li5 = li4.iterator();
                    re2 = li5.next();
                    ResultSet r9 = st.executeQuery("select * from mysongs where songId=" + re2 + "");
                    while (r9.next()) {
                        li6.add(r9.getString(2));  // Print Song Of Particular PlayList
                    }
                    System.out.println(li6 + "\n");
                    flag = false;
                } else {
                    System.out.println("  \t  *** Sorry , This PlayList Doesn't exist *** \t \n");
                }
            }while (flag) ;


            do {
                flag = true;
                System.out.println("Which Song Do you want to Listen ? ");
                String song = sc.next();
                ResultSet r8 = st.executeQuery("select * from mysongs where SongName='" + song + "'");
                while (r8.next()) {
                    re3 = r8.getInt(1);
                }
                if (li4.contains(re3)) {
                    Play.playing(re3);
                    flag = false;
                } else {
                    System.out.println("  \t  *** This Song is Not Available ***  \t \n ");
                }
            } while (flag);

        }


    public  void viewPlayList() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException {  //used
        try {
            Connection connection = Dbutil.getConnection();
            Statement st = connection.createStatement();
            System.out.println("Press Yes or no");
            String res1 = sc.next();
            if (res1.equalsIgnoreCase("Yes")) {

                List<String> li1 = new ArrayList<>();
                ResultSet res = st.executeQuery(" select * from MyAllPlayList where UserId='" + Registration.UserId + "'");

                while (res.next()) {
                    li1.add(res.getString(2));

                }
                System.out.print("Your PlayList are :");
                System.out.println("-------------"+li1+"------------");


            } else {
                System.out.println(" *** Thanks For Your Response *** ");
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }




    public static void  MyAllPlaylist() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException {

        try {
            MyPlaylist mp=new MyPlaylist();
            System.out.println("1)-----Create Playlist");
            System.out.println("2)-----View Playlist");
            System.out.println("3)-----Add Songs");
            System.out.println("4)-----Play Songs From Playlist");
            System.out.println("5)-----Go Back to Menu");


            System.out.println("Enter Your Choice");
            int choice = sc.nextInt();
            if(choice==1 || choice==2 || choice==3 || choice==4 || choice==5) {

                switch (choice) {

                    case 1:
                        System.out.println("1)-----Create Playlist");
                        mp.createdPlaylist();
                        MyAllPlaylist();
                        break;

                    case 2:
                        System.out.println("2)-----View Playlist");
                        mp.viewPlayList();
                        MyAllPlaylist();
                        break;

                    case 3:
                        System.out.println("3)-----Add songs");
                        availableSongs();
                        addSongsToList();
                        MyAllPlaylist();

                        break;

                    case 4:
                        System.out.println("4)-----Play Songs From Playlist \n");
                        playSongs();
                        MyAllPlaylist();

                        Play.YourChoice();
                        break;
                    case 5:
                        System.out.println("5)-----Go Back \n");

                        Play.YourChoice();
                        MyAllPlaylist();
                        break;

                    default:
                        System.out.println("Invalid choice");
                        MyAllPlaylist();
                        break;

                }
            }else {
                throw new InvalidChoiceException("Invalid Choice");
            }
        }catch (InvalidChoiceException  e){
            System.out.println(e);
            MyAllPlaylist();
        }
    }


    public static void availableSongs() throws SQLException {

        //Play.playsongs();
        Connection con = Dbutil.getConnection();
        Statement st = con.createStatement();
        System.out.printf("%s\n",
                StringUtils.center(">>> Relax Your Mind with Music <<<", 150));
        System.out.println("+---------------------------------------------------------------------------------------------------------------------------------+\n");
        System.out.format("%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                StringUtils.center("SongId", 20),
                StringUtils.center("SongName", 20),
                StringUtils.center("Album", 25),
                StringUtils.center("Genre", 20),
                StringUtils.center("Artist", 20),
                StringUtils.center("Duration", 20));
        System.out.println();
        System.out.println("+---------------------------------------------------------------------------------------------------------------------------------+\n");

        ResultSet res = st.executeQuery("select * from mysongs");
        while (res.next()) {
            System.out.format("%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                    StringUtils.center(String.valueOf(res.getInt(1)), 20),
                    StringUtils.center(res.getString(2), 20),
                    StringUtils.center(res.getString(3), 25),
                    StringUtils.center(res.getString(4), 20),
                    StringUtils.center(res.getString(5), 20),
                    StringUtils.center(res.getString(6), 20));
            System.out.println();
        }
        System.out.println("+---------------------------------------------------------------------------------------------------------------------------------+\n");

    }

}


