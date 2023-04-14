package dao;

import util.Dbutil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import exception.TableAlreadyCreatedException;
import exception.DuplicateValueException;

public class SongDAO {
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
            System.out.println("Table Already Exist" + e);
        }
        return "Table created";
    }

    public String insertValues() throws SQLException {
        Connection connection = Dbutil.getConnection();
        Statement st = connection.createStatement();
        try {
            st.executeUpdate("insert into MySongs values(1,'Ishq Bulaava','HTP','Love','Sanam puri','2:2','src/main/resources/Ishq-Bulaava_3.wav')");
            st.executeUpdate("insert into MySongs values(2,'Jaane Kyu','Dostana','Happy','Vishal Dadlani','3:01','src/main/resources/Jaane-Kyun-_320-kbps_.wav')");
            st.executeUpdate("insert into MySongs values(3,'Calm Down','My Strength','Happy','Selena Gomez','4:01','src/main/resources/Calm-Down-Calm-Down_PaglaSongs_.wav')");
            st.executeUpdate("insert into MySongs values(4,'Jame Raho','TZP','Happy','Vishal Dadlani','3:01','src/main/resources/Jame Raho - Taare Zameen Par 128 Kbps.wav')");
            st.executeUpdate("insert into MySongs values(5,'Khamoshiya','Silence Have Secrets','Love','Ayushmann Khurana','5:00','src/main/resources/Khamoshiyan - Khamoshiyan 128 Kbps.wav')");
            st.executeUpdate("insert into MySongs values(6,'Maiyya Mainu','Jersey','Happy','Sanam puri','3:01','src/main/resources/Maiyya-Mainu-(Lofi)(PagalWorldl) (1).wav')");
            st.executeUpdate("insert into MySongs values(7,'Ik Onkar','Rang De Basanti','Devotional','Harshdeep Kaur','2:2','src/main/resources/14 Ek Onkar - Zubaan - 190Kbps.wav')");
            st.executeUpdate("insert into MySongs values(8,'Namo Namo','Kedarnath','Devotional','Manish Tiwari','5:00','src/main/resources/Namo-Namo-Ji-Shankara(PagalWorld).wav')");
            st.executeUpdate("insert into MySongs values(9,'Woh Kishna Hai','Kishna','Devotional','Harshdeep Kaur','6:00','src/main/resources/old_Kisna-Woh Kisna Hai.wav')");
            st.executeUpdate("insert into MySongs values(10,'Om Sundaram','shiv','Devotional','Manish Tiwari','4:1','src/main/resources/om sundaram omkar sundaram.wav')");
            st.executeUpdate("insert into MySongs values(11,'Yahin Hoon Main','YHM','Happy','Ayushmann Khurana','2:2','src/main/resources/Yahin Hoon Main - Ayushmann Khurrana 190Kbps.wav')");
            st.executeUpdate("insert into MySongs values(12,'Saadi Galli Aaja','Nautanki','Love','Ayushmann Khurana','6:00','src/main/resources/02-Saadi-Galli-Aaja-_Nautanki-Saala_ (1).wav')");

            throw new DuplicateValueException("Duplicate value");
        } catch (DuplicateValueException e){
            System.out.println(e);
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