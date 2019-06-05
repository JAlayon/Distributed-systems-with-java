
import RedPic.Remote;
import RedPic.RemoteHelper;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

public class Server {

    public static ORB orb;
    public static POA rootpoa;

    public static void main(String args[]) {
        try {
            String refFile = "remote.ref";
            Runtime.getRuntime().exec("orbd -ORBInitialPort 900");

            // create and initialize the ORB //// get reference to rootpoa &amp; activate the POAManager
            orb = ORB.init(new String[]{"-ORBInitialPort 900", "-ORBInitialHost localhost"}, null);
            rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

            // create servant and register it with the ORB
            RemoteImpl remote_impl = new RemoteImpl();
            Remote remote = remote_impl._this(orb);
            putRef(remote, refFile);
            rootpoa.the_POAManager().activate();

            
            System.out.println("RedPic Server ready and waiting ...");

            // wait for invocations from clients
            for(;;){
                orb.run();
            }
            
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }

        System.out.println("RedPic Server Exiting ...");

    }

    private static void putRef(org.omg.CORBA.Object obj, String refFile) throws FileNotFoundException, IOException {
        try {
            FileOutputStream file = new FileOutputStream(refFile);
            PrintWriter writer = new PrintWriter(file);
            String ref = orb.object_to_string(obj);
            writer.println(ref);
            writer.flush();
            file.close();
        } catch (IOException ex) {
            System.out.println("Error : " + ex.getMessage());
            System.exit(4);
        }
    }
}
