package util;

import exception.DuplicateValueException;
import exception.TableAlreadyCreatedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

public class SongDao {
    public String createTable() throws SQLException {

        Connection connection = Dbutil.getConnection();
        Statement st = connection.createStatement();
        try {
            boolean res = st.execute("create table MySongs(SongId int primary key,SongName varchar(100),Album varchar(100),Genre varchar(100),Artist Varchar(100),Duration varchar(30),path varchar(80))");
            if (!res) {
                System.out.println("Table created");
            } else {
                throw new TableAlreadyCreatedException("Table Already created");
            }
        } catch (TableAlreadyCreatedException | SQLSyntaxErrorException e) {
            System.out.println("Table Already Exist" + e.getMessage());
        }
        return "Table created";
    }

    public String insertValues() throws SQLException {
        Connection connection = Dbutil.getConnection();
        Statement st = connection.createStatement();
        try {
            st.executeUpdate("insert into MySongs values(1,'IshqBulaava','HTP','Love','Sanampuri','2:2','src/main/resources/Ishq-Bulaava_3.wav')");
            st.executeUpdate("insert into MySongs values(2,'JaaneKyu','Dostana','Happy','VishalDadlani','3:01','src/main/resources/Jaane-Kyun-_320-kbps_.wav')");
            st.executeUpdate("insert into MySongs values(3,'CalmDown','MyStrength','Happy','SelenaGomez','4:01','src/main/resources/Calm-Down-Calm-Down_PaglaSongs_.wav')");
            st.executeUpdate("insert into MySongs values(4,'JameRaho','TZP','Happy','VishalDadlani','3:01','src/main/resources/Jame Raho - Taare Zameen Par 128 Kbps.wav')");
            st.executeUpdate("insert into MySongs values(5,'Khamoshiya','SilenceHaveSecrets','Love','AyushmannKhurana','5:00','src/main/resources/Khamoshiyan - Khamoshiyan 128 Kbps.wav')");
            st.executeUpdate("insert into MySongs values(6,'MaiyyaMainu','Jersey','Happy','Sanampuri','3:01','src/main/resources/Maiyya-Mainu-(Lofi)(PagalWorldl) (1).wav')");
            st.executeUpdate("insert into MySongs values(7,'IkOnkar','RangDeBasanti','Devotional','HarshdeepKaur','2:2','src/main/resources/14 Ek Onkar - Zubaan - 190Kbps.wav')");
            st.executeUpdate("insert into MySongs values(8,'NamoNamo','Kedarnath','Devotional','ManishTiwari','5:00','src/main/resources/Namo-Namo-Ji-Shankara(PagalWorld).wav')");
            st.executeUpdate("insert into MySongs values(9,'WohKishnaHai','Kishna','Devotional','HarshdeepKaur','6:00','src/main/resources/old_Kisna-Woh Kisna Hai.wav')");
            st.executeUpdate("insert into MySongs values(10,'Om Sundaram','shiv','Devotional','ManishTiwari','4:1','src/main/resources/om sundaram omkar sundaram.wav')");
            st.executeUpdate("insert into MySongs values(11,'Yahin Hoon Main','YHM','Happy','AyushmannKhurana','2:2','src/main/resources/Yahin Hoon Main - Ayushmann Khurrana 190Kbps.wav')");
            st.executeUpdate("insert into MySongs values(12,'SaadiGalliAaja','Nautanki','Love','AyushmannKhurana','6:00','src/main/resources/02-Saadi-Galli-Aaja-_Nautanki-Saala_ (1).wav')");

            throw new DuplicateValueException("Duplicate value");
        } catch (DuplicateValueException e) {
            System.out.println(e + "\n");
        }
        return "Values Inserted";
    }
}



 /*Connection con=Util.Dbutil.getConnection();
        Statement st=con.createStatement();
        String song="";
        ResultSet res=st.executeQuery("select path from mysongs where songId='"+choice+"'");*/
          /*  while(res.next()) {
                song = res.getString(1);
            }*/