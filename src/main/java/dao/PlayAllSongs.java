package dao;

import bean.Song;
import exception.InvalidChoiceException;
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
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class PlayAllSongs {

    static Scanner sc = new Scanner(System.in);

    public static void playSongs() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException {
        Connection con = Dbutil.getConnection();
        Statement st = con.createStatement();
        Dbutil.getConnection();
        List<Integer> getAllSongIdList = new ArrayList<>();
        List<Integer> songIdList = new ArrayList<>();
        List<String> songNameList = new ArrayList<>();
        boolean flag = true;
        int Id;
        do {
            try {

                SongPlayer.playSong();
                for (Song s1 : SongPlayer.list) {
                    getAllSongIdList.add(s1.getSongId());
                }


                ResultSet songResultSet1 = st.executeQuery("select *  from MySongs");
                while (songResultSet1.next()) {
                    songNameList.add(songResultSet1.getString(2));
                    songIdList.add(songResultSet1.getInt(1));
                }


                System.out.println("\t\t***-----Available SongId-----***\t\t\n");
                System.out.println(songIdList + "\n");
                System.out.println("\t\t***-----Available SongName-----***\t\t\n");
                System.out.println(songNameList + "\n");


                System.out.println("Enter SongId");
                Id = sc.nextInt();
                ResultSet songResultSet2 = st.executeQuery("select * from MySongs where SongId='" + Id + "'");


                if (getAllSongIdList.contains(Id)) {
                    while (songResultSet2.next()) {
                        System.out.print("+---------------------------------------------------------------------------------------------------------------------------------+\n");
                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|", StringUtils.center("SongId", 20), StringUtils.center("SongName", 20), StringUtils.center("Album", 25), StringUtils.center("Genre", 20), StringUtils.center("Artist", 20), StringUtils.center("Duration", 20));

                        System.out.println();

                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|", StringUtils.center(String.valueOf(songResultSet2.getInt(1)), 20), StringUtils.center(songResultSet2.getString(2), 20), StringUtils.center(songResultSet2.getString(3), 25), StringUtils.center(songResultSet2.getString(4), 20), StringUtils.center(songResultSet2.getString(5), 20), StringUtils.center(songResultSet2.getString(6), 20));

                        System.out.println();
                        System.out.print("+---------------------------------------------------------------------------------------------------------------------------------+\n");
                        flag = false;
                        SongPlayer.playing(Id);
                    }
                    //System.out.println();
                } else {
                    throw new InvalidChoiceException("Invalid SongId");
                }

            } catch (FileNotFoundException | InvalidChoiceException | InputMismatchException e) {
                System.out.println(e.getMessage() + "\n");
            }

        } while (flag);

    }
}








