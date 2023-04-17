package dao;

import bean.Song;
import exception.*;
import org.apache.commons.lang3.StringUtils;
import util.Dbutil;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Search {
    static Scanner sc = new Scanner(System.in);

    public static void SearchBySongId() throws LineUnavailableException, SQLException, UnsupportedAudioFileException, IOException {
        Dbutil.getConnection();
        Connection con = Dbutil.getConnection();
        Statement st = con.createStatement();
        boolean flag;
        int id;
        List<Integer> getAllSongIdList = new ArrayList<>();
        List<String> songNameList = new ArrayList<>();
        List<Integer> songIdList = new ArrayList<>();
        flag = true;
        do {
            try {

                SongPlayer.playSong();
                for (Song s1 : SongPlayer.list) {
                    getAllSongIdList.add(s1.getSongId());
                }


                ResultSet songResultSet1 = st.executeQuery("select * from MySongs");
                while (songResultSet1.next()) {
                    songIdList.add(songResultSet1.getInt(1));
                    songNameList.add(songResultSet1.getString(2));
                }


                System.out.println("\t\t***-----Available SongId-----***\t\t\n");
                System.out.println(songIdList + "\n");
                System.out.println("\t\t***-----Available SongName-----***\t\t\n");
                System.out.println(songNameList + "\n");
                System.out.println("Enter the Id");
                id = sc.nextInt();
                ResultSet songResultSet2 = st.executeQuery("select * from MySongs where SongId='" + id + "'");


                if (getAllSongIdList.contains(id)) {
                    while (songResultSet2.next()) {
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|", StringUtils.center("SongId", 20), StringUtils.center("SongName", 20), StringUtils.center("Album", 25), StringUtils.center("Genre", 20), StringUtils.center("Artist", 20), StringUtils.center("Duration", 20));

                        System.out.println();

                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|", StringUtils.center(String.valueOf(songResultSet2.getInt(1)), 20), StringUtils.center(songResultSet2.getString(2), 20), StringUtils.center(songResultSet2.getString(3), 25), StringUtils.center(songResultSet2.getString(4), 20), StringUtils.center(songResultSet2.getString(5), 20), StringUtils.center(songResultSet2.getString(6), 20));

                        System.out.println();
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        SongPlayer.playing(songResultSet2.getInt(1));
                        flag = false;
                    }

                } else {
                    throw new InvalidChoiceException("Invalid Choice");
                }

            } catch (InvalidChoiceException | FileNotFoundException e) {
                System.out.println(e + "\n");
            }
        } while (flag);
    }

    public static void searchBySongName() throws LineUnavailableException, UnsupportedAudioFileException, SQLException, IOException {
        Dbutil.getConnection();
        Connection con = Dbutil.getConnection();
        Statement st = con.createStatement();
        boolean flag;
        String name;
        List<String> songNameList = new ArrayList<>();
        flag = true;
        do {
            try {

                SongPlayer.playSong();
                for (Song s1 : SongPlayer.list) {
                    songNameList.add(s1.getName());
                }
                System.out.printf("%s\n", StringUtils.center("***-----Available Song-----***\n", 100));

                ResultSet songResultSet1 = st.executeQuery("select distinct SongName from MySongs");
                while (songResultSet1.next()) {
                    System.out.println(songResultSet1.getString(1));
                }

                System.out.println("Enter the name of the song :");
                name = sc.next();
                ResultSet songResultSet2 = st.executeQuery("select * from MySongs where SongName='" + name + "'");

                if (songNameList.contains(name)) {
                    while (songResultSet2.next()) {
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|", StringUtils.center("SongId", 20), StringUtils.center("SongName", 20), StringUtils.center("Album", 25), StringUtils.center("Genre", 20), StringUtils.center("Artist", 20), StringUtils.center("Duration", 20));

                        System.out.println();

                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|", StringUtils.center(String.valueOf(songResultSet2.getInt(1)), 20), StringUtils.center(songResultSet2.getString(2), 20), StringUtils.center(songResultSet2.getString(3), 25), StringUtils.center(songResultSet2.getString(4), 20), StringUtils.center(songResultSet2.getString(5), 20), StringUtils.center(songResultSet2.getString(6), 20)); //print table

                        System.out.println();
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        SongPlayer.playing(songResultSet2.getInt(1)); //taking Id
                        flag = false;
                    }


                } else {
                    throw new SongNotAvailableException("Invalid Song Name");
                }


            } catch (SongNotAvailableException | FileNotFoundException e) {
                System.out.println(e + "\n");
            }
        } while (flag);

    }


    public static void SearchByAlbum() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException {
        Dbutil.getConnection();
        Connection con = Dbutil.getConnection();
        Statement st = con.createStatement();
        boolean flag;
        List<String> songAlbumList = new ArrayList<>();
        flag = true;
        do {
            try {

                SongPlayer.playSong();
                for (Song s1 : SongPlayer.list) {
                    songAlbumList.add(s1.getAlbum());
                }
                System.out.printf("%s\n", StringUtils.center("***-----Available Album-----***\n", 100));

                ResultSet songResultSet1 = st.executeQuery("select distinct Album from MySongs");
                while (songResultSet1.next()) {
                    System.out.println(songResultSet1.getString(1));
                }

                System.out.println("Enter the Name Of the Album");
                String Album = sc.next();
                ResultSet songResultSet2 = st.executeQuery("select * from MySongs where Album='" + Album + "'");


                if (songAlbumList.contains(Album)) {
                    while (songResultSet2.next()) {
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|", StringUtils.center("SongId", 20), StringUtils.center("SongName", 20), StringUtils.center("Album", 25), StringUtils.center("Genre", 20), StringUtils.center("Artist", 20), StringUtils.center("Duration", 20));

                        System.out.println();

                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|", StringUtils.center(String.valueOf(songResultSet2.getInt(1)), 20), StringUtils.center(songResultSet2.getString(2), 20), StringUtils.center(songResultSet2.getString(3), 25), StringUtils.center(songResultSet2.getString(4), 20), StringUtils.center(songResultSet2.getString(5), 20), StringUtils.center(songResultSet2.getString(6), 20));

                        System.out.println();
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        SongPlayer.playing(songResultSet2.getInt(1));
                        flag = false;
                    }
                } else {
                    throw new AlbumNotAvailableException("This Album is not Available");
                }

            } catch (AlbumNotAvailableException | FileNotFoundException e) {
                System.out.println(e + "\n");
            }
        } while (flag);
    }

    public static void searchingByGenre() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        Dbutil.getConnection();
        Connection con = Dbutil.getConnection();
        Statement st = con.createStatement();
        boolean flag;
        flag = true;
        List<String> songAlbumList = new ArrayList<>();
        List<Integer> songIdList = new ArrayList<>();
        do {
            try {
                SongPlayer.playSong();
                for (Song s : SongPlayer.list) {
                    songAlbumList.add(s.getGenre());
                }
                System.out.printf("%s\n", StringUtils.center("***-----Available Genre-----***\n", 100));

                ResultSet songResultSet1 = st.executeQuery("select distinct Genre from MySongs");
                while (songResultSet1.next()) {
                    System.out.println(songResultSet1.getString(1));
                }
                System.out.println("Enter Genre");
                String genre = sc.next();

                ResultSet songResultSet2 = st.executeQuery("select * from MySongs where Genre='" + genre + "'");
                if (songAlbumList.contains(genre)) {

                    while (songResultSet2.next()) {
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|", StringUtils.center("SongId", 20), StringUtils.center("SongName", 20), StringUtils.center("Album", 25), StringUtils.center("Genre", 20), StringUtils.center("Artist", 20), StringUtils.center("Duration", 20));

                        System.out.println();

                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|", StringUtils.center(String.valueOf(songResultSet2.getInt(1)), 20), StringUtils.center(songResultSet2.getString(2), 20), StringUtils.center(songResultSet2.getString(3), 25), StringUtils.center(songResultSet2.getString(4), 20), StringUtils.center(songResultSet2.getString(5), 20), StringUtils.center(songResultSet2.getString(6), 20));

                        System.out.println();
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        songIdList.add(songResultSet2.getInt(1));
                        //System.out.println(li1);
                    }

                    do {
                        flag = true;
                        try {
                            System.out.println("Enter SongId");
                            int id = sc.nextInt();

                            if (songIdList.contains(id)) {
                                SongPlayer.playing(id);
                                flag = false;
                            } else {
                                throw new InvalidChoiceException("Invalid Choice");
                            }
                        } catch (InvalidChoiceException e) {
                            System.out.println(e + "\n");

                        }
                    } while (flag);


                } else {
                    throw new GenreNotAvailableException("This Genre is not Available");
                }
            } catch (GenreNotAvailableException e) {
                System.out.println(e + "\n");
            }

        } while (flag);

    }

    public static void searchingByArtist() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        Dbutil.getConnection();
        Connection con = Dbutil.getConnection();
        Statement st = con.createStatement();
        boolean flag;
        List<String> songArtistList = new ArrayList<>();
        List<Integer> songIdList = new ArrayList<>();
        flag = true;
        do {
            try {
                SongPlayer.playSong();
                for (Song s : SongPlayer.list) {
                    songArtistList.add(s.getArtist());
                }
                System.out.printf("%s\n", StringUtils.center("***-----Available Artist-----***\n", 100));

                ResultSet songResultSet1 = st.executeQuery("select distinct Artist from MySongs");
                while (songResultSet1.next()) {
                    System.out.println(songResultSet1.getString(1));
                }
                System.out.println("Enter the Name Of Artist");
                String artist = sc.next();


                ResultSet songResultSet2 = st.executeQuery("select * from MySongs where Artist='" + artist + "'");

                if (songArtistList.contains(artist)) {
                    while (songResultSet2.next()) {
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|", StringUtils.center("SongId", 20), StringUtils.center("SongName", 20), StringUtils.center("Album", 25), StringUtils.center("Genre", 20), StringUtils.center("Artist", 20), StringUtils.center("Duration", 20));

                        System.out.println();

                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|", StringUtils.center(String.valueOf(songResultSet2.getInt(1)), 20), StringUtils.center(songResultSet2.getString(2), 20), StringUtils.center(songResultSet2.getString(3), 25), StringUtils.center(songResultSet2.getString(4), 20), StringUtils.center(songResultSet2.getString(5), 20), StringUtils.center(songResultSet2.getString(6), 20));

                        System.out.println();
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        songIdList.add(songResultSet2.getInt(1));
                    }
                } else {
                    throw new ArtistNotAvailableException("This Artist is Not Available");
                }

                do {
                    try {
                        // flag = true;
                        System.out.println("Enter SongId");
                        int id = sc.nextInt();
                        if (songIdList.contains(id)) {
                            SongPlayer.playing(id);
                            flag = false;
                        } else {
                            throw new InvalidChoiceException("Invalid Choice");
                        }
                    } catch (InvalidChoiceException e) {
                        System.out.println(e + "\n");
                    }
                } while (flag);


            } catch (ArtistNotAvailableException e) {
                System.out.println(e + "\n");
            }
        } while (flag);
    }

    public static void searchingByDuration() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        Dbutil.getConnection();
        Connection con = Dbutil.getConnection();
        Statement st = con.createStatement();
        List<String> songDurationList = new ArrayList<>();
        boolean flag = true;

        do {

            try {
                for (Song s : SongPlayer.list) {
                    songDurationList.add(s.getDuration());
                }
                System.out.printf("%s\n", StringUtils.center("***-----Available Duration-----***\n", 100));

                ResultSet songResultSet1 = st.executeQuery("select distinct Duration from MySongs");
                while (songResultSet1.next()) {
                    System.out.println(songResultSet1.getString(1));
                }
                System.out.println("Enter Duration ");
                String duration = sc.next();

                ResultSet songResultSet2 = st.executeQuery("select * from MySongs where Duration='" + duration + "'");

                if (songDurationList.contains(duration)) {
                    while (songResultSet2.next()) {
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|", StringUtils.center("SongId", 20), StringUtils.center("SongName", 20), StringUtils.center("Album", 25), StringUtils.center("Genre", 20), StringUtils.center("Artist", 20), StringUtils.center("Duration", 20));

                        System.out.println();

                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|", StringUtils.center(String.valueOf(songResultSet2.getInt(1)), 20), StringUtils.center(songResultSet2.getString(2), 20), StringUtils.center(songResultSet2.getString(3), 25), StringUtils.center(songResultSet2.getString(4), 20), StringUtils.center(songResultSet2.getString(5), 20), StringUtils.center(songResultSet2.getString(6), 20));

                        System.out.println();
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        SongPlayer.playing(songResultSet2.getInt(1));
                        flag = false;
                    }
                } else {
                    throw new InvalidChoiceException("Invalid Choice");
                }

            } catch (InvalidChoiceException e) {
                System.out.println(e + "\n");
            }

        } while (flag);
    }
}










