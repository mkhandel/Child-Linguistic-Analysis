//*******************************************************************
//  LinkedBinaryTree.java       Java Foundations
//
//  Implements a binary tree using a linked representation.
//*******************************************************************

package javafoundations;

import java.util.*;
import javafoundations.*;
import javafoundations.exceptions.*;

public class LinkedBinaryTree<T> implements BinaryTree<T>
{
    protected BTNode<T> root;

    //-----------------------------------------------------------------
    //  Creates an empty binary tree.
    //-----------------------------------------------------------------
    public LinkedBinaryTree()
    {
        root = null;
    }

    //-----------------------------------------------------------------
    //  Creates a binary tree with the specified element as its root.
    //-----------------------------------------------------------------
    public LinkedBinaryTree (T element)
    {
        root = new BTNode<T>(element);
    }

    //-----------------------------------------------------------------
    //  Creates a binary tree with the two specified subtrees.
    //-----------------------------------------------------------------
    public LinkedBinaryTree (T element, LinkedBinaryTree<T> left,
    LinkedBinaryTree<T> right)
    {
        root = new BTNode<T>(element);
        root.setLeft(left.root);
        root.setRight(right.root);
    }

    //-----------------------------------------------------------------
    //  Returns the element stored in the root of the tree. Throws an
    //  EmptyCollectionException if the tree is empty.
    //-----------------------------------------------------------------
    public T getRootElement()
    {
        if (root == null)
            throw new EmptyCollectionException ("Get root operation "
                + "failed. The tree is empty.");

        return root.getElement();
    }

    //-----------------------------------------------------------------
    //  Returns the left subtree of the root of this tree.
    //-----------------------------------------------------------------
    public LinkedBinaryTree<T> getLeft()
    {
        if (root == null)
            throw new EmptyCollectionException ("Get left operation "
                + "failed. The tree is empty.");

        LinkedBinaryTree<T> result = new LinkedBinaryTree<T>();
        result.root = root.getLeft();

        return result;
    }

    //-----------------------------------------------------------------
    //  Returns the element in this binary tree that matches the
    //  specified target. Throws a ElementNotFoundException if the
    //  target is not found.
    //-----------------------------------------------------------------
    public T find (T target)
    {
        BTNode<T> node = null;

        if (root != null)
            node = root.find(target);

        if (node == null)
            throw new ElementNotFoundException("Find operation failed. "
                + "No such element in tree.");

        return node.getElement();
    }

    //-----------------------------------------------------------------
    //  Returns the number of elements in this binary tree.
    //-----------------------------------------------------------------
    public int size()
    {
        int result = 0;

        if (root != null)
            result = root.count();

        return result;
    }

    //-----------------------------------------------------------------
    //  Populates and returns an iterator containing the elements in
    //  this binary tree using an inorder traversal.
    //-----------------------------------------------------------------
    public Iterator<T> inorder()
    {
        ArrayIterator<T> iter = new ArrayIterator<T>();

        if (root != null)
            root.inorder (iter);

        return iter;
    }

    //-----------------------------------------------------------------
    //  Populates and returns an iterator containing the elements in
    //  this binary tree using a levelorder traversal.
    //-----------------------------------------------------------------
    public Iterator<T> levelorder()
    {
        LinkedQueue<BTNode<T>> queue = new LinkedQueue<BTNode<T>>();
        ArrayIterator<T> iter = new ArrayIterator<T>();

        if (root != null)
        {
            queue.enqueue(root);
            while (!queue.isEmpty())
            {
                BTNode<T> current = queue.dequeue();

                iter.add (current.getElement());

                if (current.getLeft() != null)
                    queue.enqueue(current.getLeft());
                if (current.getRight() != null)
                    queue.enqueue(current.getRight());
            }
        }

        return iter;
    }

    //-----------------------------------------------------------------
    //  Satisfies the Iterable interface using an inorder traversal.
    //-----------------------------------------------------------------
    public Iterator<T> iterator()
    {
        return inorder();
    }

    //-----------------------------------------------------------------
    //  The following methods are left as programming projects.
    //-----------------------------------------------------------------
    public LinkedBinaryTree<T> getRight() 
    {
        if (root == null)
            throw new EmptyCollectionException ("Get left operation "
                + "failed. The tree is empty.");

        LinkedBinaryTree<T> result = new LinkedBinaryTree<T>();
        result.root = root.getRight();

        return result;
    }

    //-----------------------------------------------------------------
    public boolean contains (T target) 
    {
        try
        {
            find(target);
        }
        catch(ElementNotFoundException enfe)
        {
            return false;
        }

        return true;
    }

    //-----------------------------------------------------------------
    public boolean isEmpty() 
    {
        return size() == 0;
    }

    //-----------------------------------------------------------------
    public String toString() 
    {
        String result = "";
        Iterator<T> iter = iterator();
        while(iter.hasNext())
            result += iter.next() + "\n";
        return result;
    }

    //-----------------------------------------------------------------
    public Iterator<T> preorder() 
    {
        ArrayIterator<T> iter = new ArrayIterator<T>();

        if (root != null)
            root.preorder (iter);

        return iter;
    }

    //-----------------------------------------------------------------
    public Iterator<T> postorder() 
    {
        ArrayIterator<T> iter = new ArrayIterator<T>();

        if (root != null)
            root.postorder (iter);

        return iter;
    }

    /**
     * WRITE YOUR CODE FOR EXAM 3 BELOW
     * @avery kim
     * 
     * isComplete() works, and successfully tests if a graph is complete. It is based off of the levelorder traversal method. 
     * I took the code from that and added a boolean marker set to false. Then I added conditionals that fit with the definition of a
     * complete tree.
     * 
     * getAncestors() does not work as intended. I based it off of find() because that method has a BTNode recursive element to it 
     * and also traverses the tree. Most of my code is in BTNode. I set a base case of it element of the node equals the target return
     * the node. Then there is a recursive bit that looks through each branch of the tree. I think the issue with this code is the base
     * case conditional or the parameter being plugged into the recursive function because it traverses the tree backwards which is what
     * I wanted but picks up every node rather than just the ancestors. I also did not get the LinkedBinaryTree getAncestor(target) part
     * to work because it does not add them all to the linked list. 
     */
    
    /**
     * added by me
     * isCompleted tests if the tree is a completed tree. If it is full or full to the next 
     * to last level with all the leaves on the bottom level and on the left side of the tree
     * 
     * @return boolean - true if tree is complete
     */
    public boolean isComplete() throws EmptyCollectionException{

        LinkedQueue<BTNode<T>> queue = new LinkedQueue<BTNode<T>>();
        ArrayIterator<T> iter = new ArrayIterator<T>();
        boolean marker = false;

        if(root == null){
            throw new EmptyCollectionException("EmptyCollectionExcpetion");
        }
        if (root != null) {
            queue.enqueue(root);

            while (!queue.isEmpty()) {
                BTNode<T> current = queue.dequeue();

                iter.add (current.getElement());
                if (current.getLeft() == null && current.getRight() != null){
                    //System.out.println("This is left null but right not null");
                    return false;
                }
                if (current.getLeft() != null ){
                    //System.out.println("This is left not null");
                    queue.enqueue(current.getLeft());
                    if(current.getRight() == null){
                        marker = true;
                    }

                }
                if (current.getRight() != null){
                    //System.out.println("This is 3rd if statment");

                    queue.enqueue(current.getRight());
                    marker = true;
                }
            }
        }
        return marker;
    }

    // public boolean isComplete() throws EmptyCollectionException{
    // LinkedQueue<BTNode<T>> queue = new LinkedQueue<BTNode<T>>();
    // ArrayIterator<T> iter = new ArrayIterator<T>();

    // marker = 
    // if (root != null)
    // {
    // queue.enqueue(root);

    // while (!queue.isEmpty())
    // {
    // BTNode<T> current = queue.dequeue();

    // iter.add (current.getElement());

    // if (current.count() >= this.size())
    // return false;
    // return
    // }
    // }
    // else if (root == null){
    // return true;
    // }

    // return iter;
    // }

    /**
     * getAncestors returns a LinkedList of ancestors of target node. Traverses tree in a 
     * recursive fashion.
     * 
     * @param T target - target element referenced by node in graph
     * @return LinkedList<T> - Linked List of nodes leading to node that holds the target element.
     */
    public LinkedList<T> getAncestors(T target) throws EmptyCollectionException{
        LinkedList<T> ancestorsList = new LinkedList<T>();
 
        
        //add each each ancestor node to linked list
       
            ancestorsList.add((root.ancestors(target)).getElement());
        

        


        //if (root != null)
        //    node = root.find(target);
        
        
        
        
        //ancestorsList.add(root.ancestors(target).getElement()); 
        
        
        //return ancestorsList.add(root.find(target));
        return ancestorsList;

    }
}

