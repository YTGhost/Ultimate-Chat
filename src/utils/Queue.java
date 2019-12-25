package utils;

/**
 * This interface defines a standard sequential FIFO queue.
 */
public interface Queue {

  /**
   * This procedure adds an object to the end of the queue.
   *
   * @param object  the Object to be added
   *
   */
  public void addBack(Object object);

  /**
   * This function retrieves (but not removes) the object on the front
   * of the queue.
   *
   * @return Object  the item at the front of the queue
   *
   * @throws EmptyQueueException  when the queue is empty
   */
  public Object getFront() throws EmptyQueueException;

  /**
   * This function retrieves (but not removes) the object on the front
   * of the queue.
   *
   * @return Object  the item at the front of the queue
   *
   * @throws EmptyQueueException  when the queue is empty
   */
  public Object removeFront() throws EmptyQueueException;

  /**
   * This function determines whether or not the queue is empty.
   *
   * @return boolean  <TT>true</TT> if the queue is empty
   */
  public boolean isEmpty();

  /**
   * This function returns the current length of the queue.
   *
   * @return int  the length of the queue
   */
  public int getSize();

  /**
   * This function returns the maximum size of the queue.
   * It may return <TT>-1</TT>, if the capacity of the queue is not fixed.
   *
   * @return int  the capacity of the queue
   */
  public int getCapacity();
}
