/**
 * 
 */
package com.udiansoft.util;

import java.util.Iterator;

/**
 * An iterator for ForumThreads that allows backwards and forwards iteration,
 * as well as setting an index into the Iteration.
 * 
 * @author Steven Li
 * Create Data: Oct 13, 2006
 */
public abstract class ElementIterator implements Iterator {

	 /**
     * Returns <tt>true</tt> if this iterator has more threads when
     * traversing the list in the forward direction. (In other words, returns
     * <tt>true</tt> if <tt>next</tt> would return a thread rather than
     * throwing an exception.)
     *
     * @return <tt>true</tt> if the iterator has more threads when
     *		traversing the list in the forward direction.
     */
    public abstract boolean hasNext();

    /**
     * Returns the next thread in the list. This method may be called
     * repeatedly to iterate through the list, or intermixed with calls to
     * <tt>previous</tt> to go back and forth.  (Note that alternating calls
     * to <tt>next</tt> and <tt>previous</tt> will return the same element
     * repeatedly.)
     *
     * @return the next thread in the list.
     * @exception NoSuchElementException if the iteration has no next element.
     */
    public abstract Object next();

    /**
     * Returns <tt>true</tt> if this list iterator has more threads when
     * traversing the list in the reverse direction.  (In other words, returns
     * <tt>true</tt> if <tt>previous</tt> would return a thread rather than
     * throwing an exception.)
     *
     * @return <tt>true</tt> if the list iterator has more elements when
     *	       traversing the list in the reverse direction.
     */
  //  public abstract boolean hasPrevious();

    /**
     * Returns the previous element in the list.  This method may be called
     * repeatedly to iterate through the list backwards, or intermixed with
     * calls to <tt>next</tt> to go back and forth.  (Note that alternating
     * calls to <tt>next</tt> and <tt>previous</tt> will return the same
     * element repeatedly.)
     *
     * @return the previous element in the list.
     *
     * @exception NoSuchElementException if the iteration has no previous
     *            element.
     */
   // public abstract Object previous();

    /**
     * Sets the index for the iteration to the specified ForumThread.
     *
     * @exception NoSuchElementException if <tt>thread</tt> is not a valid
     *      element of the iteration.
     */
    public abstract void setIndex(int index);

    /**
     * This method of the Iterator interface is not supported due to security
     * considerations. Calling it will always result in an an exception being
     * thrown.
     *
     * @throws UnsupportedOperationException if this method is called.
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    
    public abstract int getTotal();
    
}
