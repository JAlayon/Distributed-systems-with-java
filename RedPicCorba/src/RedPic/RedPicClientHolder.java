package RedPic;

/**
* RedPic/RedPicClientHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from redpic.idl
* Tuesday, June 4, 2019 7:03:03 PM CDT
*/

public final class RedPicClientHolder implements org.omg.CORBA.portable.Streamable
{
  public RedPic.RedPicClient value = null;

  public RedPicClientHolder ()
  {
  }

  public RedPicClientHolder (RedPic.RedPicClient initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RedPic.RedPicClientHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RedPic.RedPicClientHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RedPic.RedPicClientHelper.type ();
  }

}
