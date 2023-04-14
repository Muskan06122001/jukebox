package DAO;

import Bean.Songs;

import Util.Dbutil;
import org.apache.commons.lang3.StringUtils;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.*;

import Exception.NotANumberException;
import Exception.GenreNotAvailableException;
import Exception.SongNotAvailableException;
import Exception.AlbumNotAvailableException;
import Exception.ArtistNotAvailableException;
import Exception.InvalidChoiceException;

public class Search {
    static Scanner sc = new Scanner(System.in);

    public static void SearchBySongId() throws LineUnavailableException, SQLException, UnsupportedAudioFileException, IOException, NotANumberException {
        Dbutil.getConnection();
        Connection con = Dbutil.getConnection();
        Statement st = con.createStatement();
        boolean flag;
        int id = 0;
        List<Integer> li = new ArrayList<>();
        List<String> li2 = new ArrayList<>();
        List<Integer> li1 = new ArrayList<>();
        flag = true;
        do {
            try {

                Play.playsongs();
                for (Songs s1 : Play.list) {
                    li.add(s1.getSongId());
                }


                ResultSet res1=st.executeQuery("select * from mysongs");
                while(res1.next()){
                    li1.add(res1.getInt(1));                                          // for printing  songsId
                }

                ResultSet res2=st.executeQuery("select * from mysongs");
                while(res2.next()){
                    li2.add(res2.getString(2));
                }


                System.out.println("\t\t***-----Available SongId-----***\t\t\n");
                System.out.println(li1+"\n");
                System.out.println("\t\t***-----Available SongName-----***\t\t\n");
                System.out.println(li2+"\n");
                System.out.println("Enter the Id");
                id = sc.nextInt();
                ResultSet res = st.executeQuery("select * from mysongs where SongId='" + id + "'");


                if (li.contains(id)) {
                    while (res.next()) {
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                                StringUtils.center("SongId", 20),
                                StringUtils.center("SongName", 20),
                                StringUtils.center("Album", 25),
                                StringUtils.center("Genre", 20),
                                StringUtils.center("Artist", 20),
                                StringUtils.center("Duration", 20));

                        System.out.println();

                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                                StringUtils.center(String.valueOf(res.getInt(1)), 20),
                                StringUtils.center(res.getString(2), 20),
                                StringUtils.center(res.getString(3), 25),
                                StringUtils.center(res.getString(4), 20),
                                StringUtils.center(res.getString(5), 20),
                                StringUtils.center(res.getString(6), 20));

                        System.out.println();
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        Play.playing(res.getInt(1));
                        flag = false;
                    }

                } else {
                    throw new InvalidChoiceException("Invalid Choice");
                }

            } catch (InvalidChoiceException | FileNotFoundException e) {
                System.out.println(e);
            }
        } while (flag);
    }

    public static void searchBySongName() throws LineUnavailableException, UnsupportedAudioFileException, SQLException, IOException, NotANumberException {
        Dbutil.getConnection();
        Connection con = Dbutil.getConnection();
        Statement st = con.createStatement();
        boolean flag;
        String name = "";
        List<String> li = new ArrayList<>();
        flag = true;
        do {
            try {

                Play.playsongs();
                for (Songs s1 : Play.list) {
                    li.add(s1.getSong_name());
                }
                System.out.printf("%s\n",
                StringUtils.center("***-----Available Songs-----***\n", 100));
                ResultSet res1=st.executeQuery("select distinct SongName from mysongs");
                while(res1.next()){
                    System.out.println(res1.getString(1));
                }
                System.out.println("Enter the name of the song :");
                name = sc.next();
                ResultSet res = st.executeQuery("select * from mysongs where SongName='" + name + "'");

                if (li.contains(name)) {
                    while (res.next()) {
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                                StringUtils.center("SongId", 20),
                                StringUtils.center("SongName", 20),
                                StringUtils.center("Album", 25),
                                StringUtils.center("Genre", 20),
                                StringUtils.center("Artist", 20),
                                StringUtils.center("Duration", 20));

                        System.out.println();

                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                                StringUtils.center(String.valueOf(res.getInt(1)), 20),
                                StringUtils.center(res.getString(2), 20),
                                StringUtils.center(res.getString(3), 25),
                                StringUtils.center(res.getString(4), 20),
                                StringUtils.center(res.getString(5), 20),
                                StringUtils.center(res.getString(6), 20)); //print table

                        System.out.println();
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        Play.playing(res.getInt(1)); //taking Id
                        flag = false;
                    }


                } else {
                    throw new SongNotAvailableException("Invalid Song Name");
                }


            } catch (SongNotAvailableException | FileNotFoundException e) {
                System.out.println(e);
            }
        } while (flag);

    }


    public static void SearchByAlbum() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException, NotANumberException {
        Dbutil.getConnection();
        Connection con = Dbutil.getConnection();
        Statement st = con.createStatement();
        boolean flag;
        List<String> li = new ArrayList<>();
        flag = true;
        do {
            try {

                Play.playsongs();
                for (Songs s1 : Play.list) {
                    li.add(s1.getAlbum());
                }
                System.out.printf("%s\n",
                StringUtils.center("***-----Available Album-----***\n", 100));
                ResultSet res1=st.executeQuery("select distinct Album from mysongs");
                while(res1.next()){
                    System.out.println(res1.getString(1));
                }

                System.out.println("Enter the Name Of the Album");
                String Album = sc.next();
                ResultSet res = st.executeQuery("select * from mysongs where Album='" + Album + "'");


                if (li.contains(Album)) {
                    while (res.next()) {
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                                StringUtils.center("SongId", 20),
                                StringUtils.center("SongName", 20),
                                StringUtils.center("Album", 25),
                                StringUtils.center("Genre", 20),
                                StringUtils.center("Artist", 20),
                                StringUtils.center("Duration", 20));

                        System.out.println();

                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                                StringUtils.center(String.valueOf(res.getInt(1)), 20),
                                StringUtils.center(res.getString(2), 20),
                                StringUtils.center(res.getString(3), 25),
                                StringUtils.center(res.getString(4), 20),
                                StringUtils.center(res.getString(5), 20),
                                StringUtils.center(res.getString(6), 20));

                        System.out.println();
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        Play.playing(res.getInt(1));
                        flag = false;
                    }
                } else {
                    throw new AlbumNotAvailableException("This Album is not Available");
                }

            } catch (AlbumNotAvailableException | FileNotFoundException e) {
                System.out.println(e);
            }
        } while (flag);
    }

    public static void searchingByGenre() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        Dbutil.getConnection();
        Connection con = Dbutil.getConnection();
        Statement st = con.createStatement();
        boolean flag;
        flag = true;
        List<String> li = new ArrayList<>();
        List<Integer> li1 = new ArrayList<>();
        do {
            try {
                Play.playsongs();
                for (Songs s : Play.list) {
                    li.add(s.getGenre());
                }
                System.out.printf("%s\n",
                StringUtils.center("***-----Available Genre-----***\n", 100));
                ResultSet res1=st.executeQuery("select distinct Genre from mysongs");
                while(res1.next()){
                    System.out.println(res1.getString(1));
                }
                System.out.println("Enter Genre");
                String genre = sc.next();

                ResultSet res = st.executeQuery("select * from mysongs where Genre='" + genre + "'");
                if (li.contains(genre)) {

                    while (res.next()) {
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                                StringUtils.center("SongId", 20),
                                StringUtils.center("SongName", 20),
                                StringUtils.center("Album", 25),
                                StringUtils.center("Genre", 20),
                                StringUtils.center("Artist", 20),
                                StringUtils.center("Duration", 20));

                        System.out.println();

                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                                StringUtils.center(String.valueOf(res.getInt(1)), 20),
                                StringUtils.center(res.getString(2), 20),
                                StringUtils.center(res.getString(3), 25),
                                StringUtils.center(res.getString(4), 20),
                                StringUtils.center(res.getString(5), 20),
                                StringUtils.center(res.getString(6), 20));

                        System.out.println();
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        li1.add(res.getInt(1));
                        //System.out.println(li1);
                    }

                    do {
                        flag = true;
                        try {
                            System.out.println("Enter SongId");
                            int id = sc.nextInt();

                            if (li1.contains(id)) {
                                Play.playing(id);
                                flag = false;
                            } else {
                                throw new InvalidChoiceException("Invalid Choice");
                            }
                        } catch (InvalidChoiceException e) {
                            System.out.println(e);

                        }
                    } while (flag);


                } else {
                    throw new GenreNotAvailableException("This Genre is not Available");
                }
            } catch (GenreNotAvailableException e) {
                System.out.println(e);
            }

        } while (flag);

    }

    public static void searchingByArtist() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        Dbutil.getConnection();
        Connection con = Dbutil.getConnection();
        Statement st = con.createStatement();
        boolean flag;
        List<String> li = new ArrayList<>();
        List<Integer> li1 = new ArrayList<>();
        flag = true;
        do {
            try {
                Play.playsongs();
                for (Songs s : Play.list) {
                    li.add(s.getArtist());
                }
                System.out.printf("%s\n",
                StringUtils.center("***-----Available Artist-----***\n", 100));

                ResultSet res1=st.executeQuery("select distinct Artist from mysongs");
                while(res1.next()){
                    System.out.println(res1.getString(1));
                }
                System.out.println("Enter the Name Of Artist");
                String artist = sc.next();


                ResultSet res = st.executeQuery("select * from mysongs where Artist='" + artist + "'");

                if (li.contains(artist)) {
                    while (res.next()) {
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                                StringUtils.center("SongId", 20),
                                StringUtils.center("SongName", 20),
                                StringUtils.center("Album", 25),
                                StringUtils.center("Genre", 20),
                                StringUtils.center("Artist", 20),
                                StringUtils.center("Duration", 20));

                        System.out.println();

                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                                StringUtils.center(String.valueOf(res.getInt(1)), 20),
                                StringUtils.center(res.getString(2), 20),
                                StringUtils.center(res.getString(3), 25),
                                StringUtils.center(res.getString(4), 20),
                                StringUtils.center(res.getString(5), 20),
                                StringUtils.center(res.getString(6), 20));

                        System.out.println();
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        li1.add(res.getInt(1));
                    }
                } else {
                    throw new ArtistNotAvailableException("This Artist is Not Available");
                }

                do {
                    try {
                        flag = true;
                        System.out.println("Enter SongId");
                        int id = sc.nextInt();
                        if (li1.contains(id)) {
                            Play.playing(id);
                            flag = false;
                        } else {
                            throw new InvalidChoiceException("Invalid Choice");
                        }
                    } catch (InvalidChoiceException e) {
                        System.out.println(e);
                    }
                } while (flag);


            } catch (ArtistNotAvailableException e) {
                System.out.println(e);
            }
        } while (flag);
    }

    public static void searchingByDuration() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        Dbutil.getConnection();
        Connection con = Dbutil.getConnection();
        Statement st = con.createStatement();
        List<String> li = new ArrayList<>();
        boolean flag = true;

        do {

            try {
                for (Songs s : Play.list) {
                    li.add(s.getDuration());
                }
                System.out.printf("%s\n",
                StringUtils.center("***-----Available Duration-----***\n", 100));
                ResultSet res1=st.executeQuery("select distinct Duration from mysongs");
                while(res1.next()){
                    System.out.println(res1.getString(1));
                }
                System.out.println("Enter Duration ");
                String duration = sc.next();

                ResultSet res = st.executeQuery("select * from mysongs where Duration='" + duration + "'");

                if (li.contains(duration)) {
                    while (res.next()) {
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                                StringUtils.center("SongId", 20),
                                StringUtils.center("SongName", 20),
                                StringUtils.center("Album", 25),
                                StringUtils.center("Genre", 20),
                                StringUtils.center("Artist", 20),
                                StringUtils.center("Duration", 20));

                        System.out.println();

                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                                StringUtils.center(String.valueOf(res.getInt(1)), 20),
                                StringUtils.center(res.getString(2), 20),
                                StringUtils.center(res.getString(3), 25),
                                StringUtils.center(res.getString(4), 20),
                                StringUtils.center(res.getString(5), 20),
                                StringUtils.center(res.getString(6), 20));

                        System.out.println();
                        System.out.print("+----------------------------------------------------------------------------------------------------------------------------------+\n");
                        Play.playing(res.getInt(1));
                    }
                } else {
                    throw new InvalidChoiceException("Invalid Choice");
                }

            } catch (InvalidChoiceException e) {
                System.out.println(e);
            }

        } while (flag);
    }
}










