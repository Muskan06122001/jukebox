package Main;

import DAO.*;
import Util.Dbutil;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import Exception.NotANumberException;

public class JukeBox {

        public static void main(String[]args) throws SQLException, LineUnavailableException, UnsupportedAudioFileException, IOException, NotANumberException {
            Dbutil.getConnection();

            Registration.Choose();
            MyPlaylist mp=new MyPlaylist();
           // mp.createdTableAllPlaylist();
            //mp.createdPlaylist();

           // MyPlaylist.availableSongs();
            //Play.playsongs();
            //PlayAllSongs.playSongs();
            //MyPlaylist.name();
              //Search.searchByArtist();
             //PlayAllSongs.checkException();
           //Search.searchBySongName();
           // MyPlaylist.createPlayList();
            //MyPlaylist.createdTableAllPlaylist();
           // MyPlaylist.CreateAllPlayList();
            //PlayAllSongs.playSongs();
            //MyPlaylist.addSongs();
            //Play.playsongs();
        }
    }












































