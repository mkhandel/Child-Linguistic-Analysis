//********************************************************************
//  ElementNotFoundException.java     Java Foundations
//
//  Represents the situation in which a target element is not
//  present in a collection
//********************************************************************

package javafoundations.exceptions;

public class GraphCycleException extends RuntimeException
{
  //------------------------------------------------------------------
  //  Sets up this exception with an appropriate message.
  //------------------------------------------------------------------
  public GraphCycleException (String message)
  {
    super (message);
  }
}
