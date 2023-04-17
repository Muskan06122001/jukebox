package dao;


import exception.InvalidChoiceException;
import org.apache.commons.lang3.StringUtils;
import util.Dbutil;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MyPlaylist {

    static Scanner sc = new Scanner(System.in);

    public static void CreateAndAddSongsToList() throws SQLException {
        Connection connection = Dbutil.getConnection();
        Statement st = connection.createStatement();
        int id1 = 0;
        boolean flag;
        List<String> li = new ArrayList<>();
        List<String> li2 = new ArrayList<>();

        ResultSet re = st.executeQuery("select distinct NameOfPlayList from MyAllPlayList where UserId='" + Registration.UserId + "'");
        while (re.next()) {
            li2.add(re.getString(1));
        }
        System.out.println(" *** Available PlayList are *** \n");
        System.out.println("------" + li2 + "------\n");

        System.out.println("Create PlayList , Enter Name Of Your PlayList");
        String playListName=sc.next();

        do {
            flag = true;
            System.out.println("Do you want to add songs ");
            String re2 = sc.next();


            if (re2.equalsIgnoreCase("Yes")) {
                //System.out.println("In which PlayList do you want to add song ? ");
                //String c = sc.next();
                System.out.println("Enter the name of Song");
                String name = sc.next();

                //if (li2.contains(c)) {

                    //System.out.println("Enter the name of Song");
                    //String name = sc.next();

                    ResultSet r1 = st.executeQuery("select * from MySongs");
                    while (r1.next()) {
                        li.add(r1.getString(2));  // song name
                    }
                    ResultSet r2 = st.executeQuery("select SongId from MySongs where songName='" + name + "'");
                    while (r2.next()) {
                        id1 = (r2.getInt(1));
                    }

                    if (li.contains(name)) {
                        System.out.println(id1);
                        st.executeUpdate("Insert into MyAllPlayList  values('" + Registration.UserId + "', '" + playListName + "','" + id1 + "')");                        System.out.println("------ Song Added ------ \n");
             //st.executeUpdate("Insert into MyAllPlayList  values('" + Registration.UserId + "', '" + c + "','" + id1 + "')");
                    } else {
                        System.out.println("This Song is not available ,Thanks for Your Response !!! \n");//song name done with songId

                    }
                } else {
                    System.out.println(" *** Thanks for Your Response !!! ***   \n");
                    flag=false;

                }

            } while (flag);
    }



    public static void playSongs() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException {
        Scanner sc = new Scanner(System.in);
        Connection connection = Dbutil.getConnection();
        Statement st = connection.createStatement();
        int re3 = 0;
        int re2;
        boolean flag;
        List<Integer> li4 = new ArrayList<>();
        List<String> li6 = new ArrayList<>();
        List<String> li7 = new ArrayList<>();
        do {
            flag = true;
            System.out.println("\t *** Available PlayList are *** \t \n");
            ResultSet res = st.executeQuery("select distinct NameOfPlayList from MyAllPlayList where UserId='" + Registration.UserId + "' ");
            while (res.next()) {
                li7.add(res.getString(1));  // Available PlayList are
            }
            System.out.println(li7 + "\n");

            System.out.println("From Which playList Do You want to listen Song ? ");
            String playList = sc.next();
            if (li7.contains(playList)) {
                ResultSet res2 = st.executeQuery("select  SongId from MyAllPlayList where NameOfPlayList='" + playList + "'");
                while (res2.next()) {
                    li4.add(res2.getInt(1));  //SongId
                }
                System.out.println(li4+"\n");     //  printing SongId

                Iterator<Integer> l = li4.iterator();
                re2 = l.next();
                ResultSet r9 = st.executeQuery("select SongName from MySongs where songId=" + re2 + "");
                while (r9.next()) {
                    li6.add(r9.getString(1));                                                          // Print Song Of Particular PlayList
                    //System.out.println(li6);
                }
                System.out.println("\t *** Available Song are *** \t \n");
                System.out.println(li6 + "\n");
                flag = false;
            } else {
                System.out.println("  \t  *** Sorry , This PlayList Doesn't exist *** \t \n");
            }
        } while (flag);


        do {
            flag = true;
            System.out.println("Which Song Do you want to Listen ? ");
            String song = sc.next();
            ResultSet r8 = st.executeQuery("select * from MySongs where SongName='" + song + "'");
            while (r8.next()) {
                re3 = r8.getInt(1);
            }
            if (li4.contains(re3)) {
                SongPlayer.playing(re3);
                flag = false;
            } else {
                System.out.println("  \t  *** This Song is Not Available ***  \t \n ");
            }
        } while (flag);

    }

    public static void myAllPlaylist() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException {

        try {
            MyPlaylist mp = new MyPlaylist();
            System.out.println("1)-----Create Playlist And Add Songs");
            System.out.println("2)-----View Playlist");
            System.out.println("3)-----SongPlayer Song From Playlist");
            System.out.println("4)-----Go Back to Menu");


            System.out.println("Enter Your Choice");
            int choice = sc.nextInt();
            if (choice == 1 || choice == 2 || choice == 3 || choice == 4 ) {

                switch (choice) {

                    case 1:
                        System.out.println("1)-----Create Playlist & Add Songs");
                        //String r=mp.createdPlaylist();
                        //System.out.println(r);
                        availableSongs();
                        CreateAndAddSongsToList();
                        myAllPlaylist();
                        break;

                    case 2:
                        System.out.println("2)-----View Playlist");
                        mp.viewPlayList();
                        myAllPlaylist();
                        break;

                    case 3:
                        System.out.println("3)-----SongPlayer Song From Playlist \n");
                        playSongs();
                        myAllPlaylist();
                        break;
                    case 4:
                        System.out.println("4)-----Go Back \n");
                        SongPlayer.YourChoice();
                        myAllPlaylist();
                        break;

                    default:
                        System.out.println("Invalid choice");
                        myAllPlaylist();
                        break;

                }
            } else {
                throw new InvalidChoiceException("Invalid Choice");
            }
        } catch (InvalidChoiceException e) {
            System.out.println(e+"\n");
            myAllPlaylist();
        }
    }

    public static void availableSongs() throws SQLException {

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

        ResultSet res = st.executeQuery("select * from MySongs");
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



    /*public String createdPlaylist(){
        try {
            Connection connection = Dbutil.getConnection();
            Statement st = connection.createStatement();

            System.out.println("Press Yes or no");
            String res1 = sc.next();
            if (res1.equalsIgnoreCase("yes")) {
                System.out.println("Enter name of your PlayList");
                String PlayListName = sc.next();
                st.executeUpdate("insert into MyAllPlayList (UserId,NameOfPlayList) values ('" + Registration.UserId + "','" + PlayListName + "')");

            }
        } catch (SQLException e) {
            System.out.println(e+"\n");
        }
        return " *** PlayList created *** \n";
    }*/

    public void viewPlayList()  {  //used
        try {
            Connection connection = Dbutil.getConnection();
            Statement st = connection.createStatement();
            System.out.println("Press Yes or no");
            String res1 = sc.next();
            if (res1.equalsIgnoreCase("Yes")) {

                List<String> li1 = new ArrayList<>();
                ResultSet res = st.executeQuery(" select distinct NameOfPlayList from MyAllPlayList where UserId='" + Registration.UserId + "'");

                while (res.next()) {
                    li1.add(res.getString(1));

                }
                System.out.print("Your PlayList are :");
                System.out.println("-------------" + li1 + "------------");


            } else {
                System.out.println(" *** Thanks For Your Response *** ");
            }
        } catch (SQLException e) {
            System.out.println(e+"\n");
        }
    }

}


