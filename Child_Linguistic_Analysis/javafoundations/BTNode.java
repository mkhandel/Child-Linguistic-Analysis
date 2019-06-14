//*******************************************************************
//  BTNode.java       Java Foundations
//
//  Represents a node in a binary tree with a left and right child.
//  Therefore this class also represents the root of a subtree.
//  
//*******************************************************************
package javafoundations;

import java.util.*;

public class BTNode<T>
{
    protected T element;
    protected BTNode<T> left, right;

    //-----------------------------------------------------------------
    //  Creates a new tree node with the specified data.
    //-----------------------------------------------------------------
    public BTNode (T element)
    {
        this.element = element;
        left = right = null;
    }

    //-----------------------------------------------------------------
    //  Returns the element stored in this node.
    //-----------------------------------------------------------------
    public T getElement()
    {
        return element;
    }

    //-----------------------------------------------------------------
    //  Sets the element stored in this node.
    //-----------------------------------------------------------------
    public void setElement (T element)
    {
        this.element = element;
    }

    //-----------------------------------------------------------------
    //  Returns the left subtree of this node.
    //-----------------------------------------------------------------
    public BTNode<T> getLeft()
    {
        return left;
    }

    //-----------------------------------------------------------------
    //  Sets the left child of this node.
    //-----------------------------------------------------------------
    public void setLeft (BTNode<T> left)
    {
        this.left = left;
    }

    //-----------------------------------------------------------------
    //  Returns the right subtree of this node.
    //-----------------------------------------------------------------
    public BTNode<T> getRight()
    {
        return right;
    }

    //-----------------------------------------------------------------
    //  Sets the right child of this node.
    //-----------------------------------------------------------------
    public void setRight (BTNode<T> right)
    {
        this.right = right;
    }

    //-----------------------------------------------------------------
    //  Returns the element in this subtree that matches the
    //  specified target. Returns null if the target is not found.
    //-----------------------------------------------------------------
    public BTNode<T> find (T target)
    {
        BTNode<T> result = null;

        if (element.equals(target))
            result = this;
        else
        {
            if (left != null)
                result = left.find(target);
            if (result == null && right != null)
                result = right.find(target);
        }

        return result;
    }

    //-----------------------------------------------------------------
    //  Returns the number of nodes in this subtree.
    //-----------------------------------------------------------------
    public int count()
    {
        int result = 1;

        if (left != null)
            result += left.count();

        if (right != null)
            result += right.count();

        return result;
    }

    //-----------------------------------------------------------------
    //  Performs an inorder traversal on this subtree, updating the
    //  specified iterator.
    //-----------------------------------------------------------------
    public void inorder (ArrayIterator<T> iter)
    {
        if (left != null)
            left.inorder (iter);

        iter.add (element);

        if (right != null)
            right.inorder (iter);
    }

    //-----------------------------------------------------------------
    //  The following methods are left as programming projects.
    //-----------------------------------------------------------------
    public void preorder (ArrayIterator<T> iter) 
    {
        iter.add(element);

        if(left != null)
            left.preorder(iter);

        if(right != null)
            right.preorder(iter);
    }

    //-----------------------------------------------------------------
    public void postorder (ArrayIterator<T> iter) 
    {
        if(left != null)
            left.preorder(iter);

        if(right != null)
            right.preorder(iter);

        iter.add(element);
    }

    public BTNode<T> ancestors (T target){
        BTNode<T> result = null;

        // if (element.equals(target)){
            // System.out.println("This element: " + element + " equals target: "+ target);
            // result = this;
            // System.out.println ("1This is result for element: " + element);
        // }
        
        //if element of current node equals target return 
        if (element.equals(target)){  //this.left.equals(target) || this.right.equals(target)
            //System.out.println("This element: " + element + " equals target: "+ target);
            result = this;
            //System.out.println ("1This is result for element: " + element);
            return result;
        }
        
        else
        {
            //recursive bit, searches through left and performs ancestors on left side of node
            if (left != null){
                //System.out.println("left is not null for element " + element);
                result = left.ancestors(this.getElement());     //previously target
                //System.out.println ("2This is result for element: " + element);
            }   
            //recursive bit, searches through right and performs ancestors on right side of node
            if (right != null){
                //System.out.println("right is not null for element " + element);
                result = right.ancestors(this.getElement());    //previously target
                //System.out.println ("3This is result for element: " + element);
            }   
        }
        //System.out.println("this is restult" + this.getElement());
        System.out.println ("4This is result for element: " + element);
        return result;
    }

    
    //attempted another method that did not work
    // public BTNode<T> ancestors (T target){
    // BTNode<T> result = null;

    // if(left == target|| right == target){
    // System.out.println("this is an ancestor: " + this.getElement());
    // result = this;
    // return this;
    // }

    // if (element.equals(target)){
    // System.out.println("This element: " + element + " equals target: "+ target);
    // result = this;
    // }
    // else
    // {
    // if(left!=null){
    // System.out.println("this is left not null for: " + this.getElement());
    // result = this.left.ancestors(result.getElement());
    // }
    // if(result == null && right != null){
    // System.out.println("this is right not null for: " + this.getElement());
    // result = this.right.ancestors(result.getElement());
    // }
    // }
    // System.out.println("this is restult" + this.getElement());
    // return result;
    // }
}
 