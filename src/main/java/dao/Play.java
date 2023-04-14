package dao;

import bean.Songs;
import util.Dbutil;
import org.apache.commons.lang3.StringUtils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import exception.NotANumberException;

public  class Play {
     static Scanner sc=new Scanner(System.in);

     public static final ArrayList<Songs> list = new ArrayList<>();

     public static ArrayList<Songs> playsongs() throws SQLException {
         Connection connection = Dbutil.getConnection();

         Statement st = connection.createStatement();
         ResultSet res = st.executeQuery("select * from MySongs");
         while (res.next()) {
             list.add(new Songs(res.getInt(1),
                     res.getString(2),
                     res.getString(3),
                     res.getString(4),
                     res.getString(5),
                     res.getString(6),
                     res.getString(7)));

         }

        Collections.sort(list);
         return list;
     }


     public static void YourChoice() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException, NotANumberException {
          Registration r=new Registration();
             Scanner sc = new Scanner(System.in);
             System.out.println("1)------>Play");
             System.out.println("2)----->Search By");
             System.out.println("3)------>My Playlist");
             System.out.println("4)----->LogOut");
             System.out.println("Enter Your Choice");
             int choice = sc.nextInt();
             switch (choice) {
                 case 1:
                     PlayAllSongs.playSongs();
                     break;
                 case 2:
                     SearchBy();
                     break;
                 case 3:
                     MyPlaylist.MyAllPlaylist();
                     break;
                 case 4:
                    r.Logout();
                     break;
                 default:
                     System.out.println("Invalid choice , Please Enter valid choice");
                     YourChoice();
                     break;
             }

         }

     public static void SearchBy() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException, NotANumberException {
         System.out.println("1)------>SearchBySongId");
         System.out.println("2)------>SearchBySongName");
         System.out.println("3)------>SearchBySongAlbum");
         System.out.println("4)------>SearchByGenre");
         System.out.println("5)------>SearchByArtist");
         System.out.println("6)------>SearchBySongDuration\n");

         System.out.println("Enter Your Choice");
         int choice=sc.nextInt();
         switch (choice){
             case 1:
                 Search.SearchBySongId();
                 break;
             case 2:
                 Search.searchBySongName();
                 break;
             case 3:
                 Search.SearchByAlbum();
                 break;
             case 4:
                 Search.searchingByGenre();
                 break;
             case 5:
                 Search.searchingByArtist();
                 break;
             case 6:
                 Search.searchingByDuration();
                 break;
             default:
                 System.out.println("Invalid Choice");
                 SearchBy();
                 break;
         }
     }

     public static void playing(int choice) throws LineUnavailableException, SQLException, UnsupportedAudioFileException, IOException, NotANumberException {
         try {
             Scanner sc = new Scanner(System.in);

             String path = "";

             for (Songs s : list) {
                 if (s.getSongId() == choice) {
                     path = s.getPath();
                 }
             }
             Clip clip = AudioSystem.getClip();
             File file = new File(String.valueOf(path));
             AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
             clip.open(audioInputStream);
             String demand = "";
             while (!demand.equalsIgnoreCase("e")) {
                 System.out.println("P=Play , S=Stop , R=Reset , N=Next ,PRE=Pre ,G=Go to Menu ");
                 System.out.println("Enter your choice");
                 demand = sc.nextLine().toUpperCase();

                 switch (demand) {
                     case "P":
                         System.out.println("Play");
                         clip.start();
                         break;

                     case "S":
                         clip.stop();
                         break;
                     case "R":
                         clip.setMicrosecondPosition(0);
                         break;
                     case "N":
                         //clip.stop();
                         System.out.println("play Next Song");
                         playing(choice + 1);
                         clip.start();
                         break;
                     case "PRE":
                         //clip.stop();
                         System.out.println("Play Previous Song");
                         playing(choice - 1);
                         clip.start();
                         break;
                     case "G":
                         YourChoice();
                         break;
                     default:
                         System.out.println("Invalid Choice");
                         playing(choice);
                         break;
                 }
             }
         }catch (FileNotFoundException e){
             System.out.println(e);

         }
     }


     public static void SongsTable() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException, NotANumberException {
         Play.playsongs();
         System.out.printf("%s\n",
                 StringUtils.center(">>> Relax Your Mind with Music <<<", 150));
         System.out.print("+---------------------------------------------------------------------------------------------------------------------------------+\n");
         System.out.format("%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                 StringUtils.center("SongId", 20),
                 StringUtils.center("SongName", 20),
                 StringUtils.center("Album", 25),
                 StringUtils.center("Genre", 20),
                 StringUtils.center("Artist", 20),
                 StringUtils.center("Duration", 20));
         System.out.println();
         System.out.printf("+---------------------------------------------------------------------------------------------------------------------------------+\n");

         for(Songs s:Play.list) {
             System.out.format("%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                     StringUtils.center(String.valueOf(s.getSongId()), 20),
                     StringUtils.center(s.getSong_name(), 20),
                     StringUtils.center(s.getAlbum(), 25),
                     StringUtils.center(s.getGenre(), 20),
                     StringUtils.center(s.getArtist(), 20),
                     StringUtils.center(s.getDuration(), 20));
                    System.out.println();
         }
         System.out.println("+---------------------------------------------------------------------------------------------------------------------------------+\n");
         YourChoice();
         }
     }




