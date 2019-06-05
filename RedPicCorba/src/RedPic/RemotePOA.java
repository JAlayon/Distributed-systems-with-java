package RedPic;


/**
* RedPic/RemotePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from redpic.idl
* Tuesday, June 4, 2019 7:03:04 PM CDT
*/

public abstract class RemotePOA extends org.omg.PortableServer.Servant
 implements RedPic.RemoteOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("uploadImage", new java.lang.Integer (0));
    _methods.put ("addClient", new java.lang.Integer (1));
    _methods.put ("removeClient", new java.lang.Integer (2));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // RedPic/Remote/uploadImage
       {
         String img = in.read_string ();
         String username = in.read_string ();
         this.uploadImage (img, username);
         out = $rh.createReply();
         break;
       }

       case 1:  // RedPic/Remote/addClient
       {
         RedPic.RedPicClient client = RedPic.RedPicClientHelper.read (in);
         this.addClient (client);
         out = $rh.createReply();
         break;
       }

       case 2:  // RedPic/Remote/removeClient
       {
         RedPic.RedPicClient client = RedPic.RedPicClientHelper.read (in);
         this.removeClient (client);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:RedPic/Remote:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Remote _this() 
  {
    return RemoteHelper.narrow(
    super._this_object());
  }

  public Remote _this(org.omg.CORBA.ORB orb) 
  {
    return RemoteHelper.narrow(
    super._this_object(orb));
  }


} // class RemotePOA
