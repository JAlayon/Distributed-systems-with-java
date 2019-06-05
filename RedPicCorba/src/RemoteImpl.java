
import RedPic.RedPicClient;
import RedPic.RemotePOA;
import java.util.ArrayList;
import java.util.List;
import org.omg.CORBA.ORB;

/**
 *
 * @author alayon
 */
public class RemoteImpl extends RemotePOA {

    private List<RedPicClient> clients;
    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;

    }

    public RemoteImpl() {
        clients = new ArrayList<>();
    }

    @Override
    public void uploadImage(String img, String username) {
        _notify(img, username);
    }

    @Override
    public synchronized void addClient(RedPicClient client) {
        clients.add(client);
    }

    @Override
    public synchronized void removeClient(RedPicClient client) {
        clients.remove(client);
    }

    private synchronized void _notify(String img, String email) {
        for (RedPicClient c : clients) {
            if (c != null && !c.getEmail().equals(email)) {
                try {
                    c.update(img, email);
                } catch (Exception ign) {
                    System.out.println("Error en el recorrimiento de la lista...");
                }
            }
        }
    }

}
