package main;

import dao.*;
import util.Dbutil;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;


public class JukeBox {

        public static void main(String[]args) throws SQLException, LineUnavailableException, UnsupportedAudioFileException, IOException {
            Dbutil.getConnection();
            Registration.Choose();

        }
    }












































