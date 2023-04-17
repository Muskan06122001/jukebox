package dao;

import bean.Song;
import org.apache.commons.lang3.StringUtils;
import util.Dbutil;

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


public class SongPlayer {
    public static final ArrayList<Song> list = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void playSong() throws SQLException {
        Connection connection = Dbutil.getConnection();

        Statement st = connection.createStatement();
        ResultSet res = st.executeQuery("select * from MySongs");
        while (res.next()) {
            list.add(new Song(res.getInt(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6),
                    res.getString(7)));

        }

        Collections.sort(list);
    }


    public static void YourChoice() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException {
        Registration r = new Registration();
        Scanner sc = new Scanner(System.in);
        System.out.println("1)------>SongPlayer");
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
                MyPlaylist.myAllPlaylist();
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

    public static void SearchBy() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException {
        System.out.println("1)------>SearchBySongId");
        System.out.println("2)------>SearchBySongName");
        System.out.println("3)------>SearchBySongAlbum");
        System.out.println("4)------>SearchByGenre");
        System.out.println("5)------>SearchByArtist");
        System.out.println("6)------>SearchBySongDuration\n");

        System.out.println("Enter Your Choice");
        int choice = sc.nextInt();
        switch (choice) {
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

    public static void playing(int choice) throws LineUnavailableException, SQLException, UnsupportedAudioFileException, IOException {
        try {
            Scanner sc = new Scanner(System.in);

            String path = "";

            for (Song s : list) {
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
                System.out.println("P=SongPlayer , S=Stop , R=Reset , N=Next ,PRE=Pre ,G=Go to Menu ");
                System.out.println("Enter your choice");
                demand = sc.nextLine().toUpperCase();

                switch (demand) {
                    case "P":
                        System.out.println("SongPlayer");
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
                        System.out.println("SongPlayer Previous Song");
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
        } catch (FileNotFoundException e) {
            System.out.println(e + "\n");
        }
    }


    public static void songsTable() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException {
        SongPlayer.playSong();
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
        System.out.print("+---------------------------------------------------------------------------------------------------------------------------------+\n");

        for (Song s : SongPlayer.list) {
            System.out.format("%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                    StringUtils.center(String.valueOf(s.getSongId()), 20),
                    StringUtils.center(s.getName(), 20),
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




