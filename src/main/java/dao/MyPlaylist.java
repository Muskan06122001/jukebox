package dao;


import exception.InvalidChoiceException;
import org.apache.commons.lang3.StringUtils;
import util.Dbutil;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MyPlaylist {

    static Scanner sc = new Scanner(System.in);

    public static void createAndAddSongsToList() throws SQLException {
        Connection connection = Dbutil.getConnection();
        Statement st = connection.createStatement();
        int id1 = 0;
        boolean flag;
        List<String> songNameList = new ArrayList<>();
        List<String> userIdPlayList = new ArrayList<>();

        ResultSet songResultSet1 = st.executeQuery("select distinct NameOfPlayList from MyAllPlayList where UserId='" + Registration.UserId + "'");
        while (songResultSet1.next()) {
            userIdPlayList.add(songResultSet1.getString(1));
        }
        System.out.println(" *** Available PlayList are *** \n");
        System.out.println("------" + userIdPlayList + "------\n");

        System.out.println("Create PlayList , Enter Name Of Your PlayList");
        String playListName = sc.next();

        do {
            flag = true;
            System.out.println("Do you want to add songs ");
            String re2 = sc.next();


            if (re2.equalsIgnoreCase("Yes")) {
                System.out.println("Enter the name of Song");
                String name = sc.next();

                ResultSet r1 = st.executeQuery("select * from MySongs");
                while (r1.next()) {
                    songNameList.add(r1.getString(2));
                }
                ResultSet r2 = st.executeQuery("select SongId from MySongs where songName='" + name + "'");
                while (r2.next()) {
                    id1 = (r2.getInt(1));
                }

                if (songNameList.contains(name)) {
                    System.out.println(id1);
                    st.executeUpdate("Insert into MyAllPlayList  values('" + Registration.UserId + "', '" + playListName + "','" + id1 + "')");
                    System.out.println("------ Song Added ------ \n");
                } else {
                    System.out.println("This Song is not available ,Thanks for Your Response !!! \n");//song name done with songId

                }
            } else {
                System.out.println(" *** Thanks for Your Response !!! ***   \n");
                flag = false;

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
        List<Integer> songIdList = new ArrayList<>();
        List<String> songNameList = new ArrayList<>();
        List<String> userIdPlayList = new ArrayList<>();
        do {
            flag = true;
            System.out.println("\t *** Available PlayList are *** \t \n");
            ResultSet res = st.executeQuery("select distinct NameOfPlayList from MyAllPlayList where UserId='" + Registration.UserId + "' ");
            while (res.next()) {
                userIdPlayList.add(res.getString(1));
            }
            System.out.println(userIdPlayList + "\n");

            System.out.println("From Which playList Do You want to listen Song ? ");
            String playList = sc.next();
            if (userIdPlayList.contains(playList)) {
                ResultSet res2 = st.executeQuery("select  SongId from MyAllPlayList where NameOfPlayList='" + playList + "'");
                while (res2.next()) {
                    songIdList.add(res2.getInt(1));
                }
                System.out.println(songIdList + "\n");

                Iterator<Integer> l = songIdList.iterator();
                re2 = l.next();
                ResultSet r9 = st.executeQuery("select SongName from MySongs where songId=" + re2 + "");
                while (r9.next()) {
                    songNameList.add(r9.getString(1));                                                          // Print Song Of Particular PlayList

                }
                System.out.println("\t *** Available Song are *** \t \n");
                System.out.println(songNameList + "\n");
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
            if (songIdList.contains(re3)) {
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
            if (choice == 1 || choice == 2 || choice == 3 || choice == 4) {

                switch (choice) {

                    case 1:
                        System.out.println("1)-----Create Playlist & Add Songs");
                        availableSongs();
                        createAndAddSongsToList();
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
                        SongPlayer.yourChoice();
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
            System.out.println(e + "\n");
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

        ResultSet songResultSet = st.executeQuery("select * from MySongs");
        while (songResultSet.next()) {
            System.out.format("%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                    StringUtils.center(String.valueOf(songResultSet.getInt(1)), 20),
                    StringUtils.center(songResultSet.getString(2), 20),
                    StringUtils.center(songResultSet.getString(3), 25),
                    StringUtils.center(songResultSet.getString(4), 20),
                    StringUtils.center(songResultSet.getString(5), 20),
                    StringUtils.center(songResultSet.getString(6), 20));
            System.out.println();
        }
        System.out.println("+---------------------------------------------------------------------------------------------------------------------------------+\n");

    }


    public void viewPlayList() {
        try {
            Connection connection = Dbutil.getConnection();
            Statement st = connection.createStatement();
            System.out.println("Press Yes or no");
            String res1 = sc.next();
            if (res1.equalsIgnoreCase("Yes")) {

                List<String> userIdPlayList = new ArrayList<>();
                ResultSet res = st.executeQuery(" select distinct NameOfPlayList from MyAllPlayList where UserId='" + Registration.UserId + "'");

                while (res.next()) {
                    userIdPlayList.add(res.getString(1));

                }
                System.out.print("Your PlayList are :");
                System.out.println("-------------" + userIdPlayList + "------------");


            } else {
                System.out.println(" *** Thanks For Your Response *** ");
            }
        } catch (SQLException e) {
            System.out.println(e + "\n");
        }
    }

}


