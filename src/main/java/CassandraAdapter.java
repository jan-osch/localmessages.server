import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CassandraAdapter {

    private String address;

    public CassandraAdapter(String address) {
        this.address = address;
    }

    public void connect() {
        Cluster cluster = null;
        try {
            cluster = Cluster.builder()
                    .addContactPoint(this.address)
                    .build();

            Session session = cluster.connect();

            ResultSet rs = session.execute("select release_version from system.local");
            Row row = rs.one();
            System.out.println(row.getString("release_version"));
        } finally {
            if (cluster != null) cluster.close();
        }
    }
}
