import java.sql.*;
import java.util.List;

public class PulsDAOImplement implements PulsDAO {

    Connection conn = Connector.getConn();

    public String save(PulsDTO pulsDTO) {
        try {
            int[] id = new int[20];
            id = pulsDTO.getId();
            int[] puls = new int[20];
            puls = pulsDTO.getPulsList();
            Timestamp[] tid = new Timestamp[20];
            tid = pulsDTO.getTid();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO Målinger (ID,Puls,Tid) values (?,?,?)");
            for (int i = 0; i < puls.length; i++) {
                statement.setInt(1, id[i]);
                statement.setInt(2, puls[i]);
                statement.setTimestamp(3, tid[i]);
                statement.addBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void savebatch(List<PulsDTO> batch) {

    }

    public PulsDTO load(PulsDTO pulsDTO) {

        try {
            Statement statement = conn.createStatement();
            ResultSet show_tables = statement.executeQuery("SELECT Temp FROM Målinger");
            while (show_tables.next()) {
                PulsDTO.setPuls(show_tables.getInt("Puls"));
            }
            return pulsDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}