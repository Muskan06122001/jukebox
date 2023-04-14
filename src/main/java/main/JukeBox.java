package main;

import dao.*;
import util.Dbutil;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import exception.NotANumberException;

public class JukeBox {

        public static void main(String[]args) throws SQLException, LineUnavailableException, UnsupportedAudioFileException, IOException, NotANumberException {
            Dbutil.getConnection();

            Registration.Choose();

        }
    }












































