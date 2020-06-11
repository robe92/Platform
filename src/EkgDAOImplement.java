import java.sql.*;
import java.util.List;

public class EkgDAOImplement implements EkgDAO {

    Connection conn = Connector.getConn();

    public String save(EkgDTO ekgDTO) {
        try {
            int[] id = new int[20];
            id = ekgDTO.getId();
            int[] ekg = new int[20];
            ekg =ekgDTO.getEkgList();
            Timestamp[] tid = new Timestamp[20];
            tid =ekgDTO.getTid();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO Målinger (ID,Ekg,Tid) values (?,?,?)");
            for (int i = 0; i < ekg.length; i++) {
                statement.setInt(1, id[i]);
                statement.setInt(2, ekg[i]);
                statement.setTimestamp(3, tid[i]);
                statement.addBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void savebatch(List<EkgDTO> batch) {
    }

    public EkgDTO load(EkgDTO EkgDTO) {
        try {
            Statement statement = conn.createStatement();
            ResultSet show_tables = statement.executeQuery("SELECT Temp FROM Målinger");
            while (show_tables.next()) {
                EkgDTO.setEkg(show_tables.getInt("Ekg"));
            }
            return EkgDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


