package utils;
/**
 * CircularQueue工具类（用于实现消息类）
 */

/**
 * This class defines the fixed-length, circular FIFO queue implementation.
 */
public class CircularQueue implements Queue {

  private Object[]	queue;
  private int		frontIndex;
  private int		backIndex;

  /**
   * This is the default constructor for an standard, empty, FIFO queue.
   */
  public CircularQueue(int capacity) {
    queue = new Object[capacity];
    frontIndex = 0;
    backIndex = 0;
  }

  /**
   * This procedure adds an object to the end of the queue.
   *
   * @param object  the Object to be added
   *
   */
  public void addBack(Object object) {
    queue[backIndex] = object;
    backIndex = (backIndex + 1) % queue.length;
  }

  /**
   * This function retrieves (but not removes) the object on the front
   * of the queue.
   *
   * @return Object  the item at the front of the queue
   *
   * @throws EmptyQueueException  when the queue is empty
   */
  public Object getFront() throws EmptyQueueException {
    if ( frontIndex == backIndex ) {
      throw new EmptyQueueException(this);
    }
    return queue[frontIndex];
  }

  /**
   * This function retrieves (but not removes) the object on the front
   * of the queue.
   *
   * @return Object  the item at the front of the queue
   *
   * @throws EmptyQueueException  when the queue is empty
   */
  public Object removeFront() throws EmptyQueueException {
    if ( frontIndex == backIndex ) {
      throw new EmptyQueueException(this);
    }
    Object object = queue[frontIndex];
    queue[frontIndex] = null;
    frontIndex = (frontIndex + 1) % queue.length;
    return object;
  }

  /**
   * This function determines whether or not the queue is empty.
   *
   * @return boolean  <TT>true</TT> if the queue is empty
   */
  public boolean isEmpty() {
    return (getSize() == 0);
  }

  /**
   * This function the current length of the queue.
   *
   * @return int  the length of the queue
   */
  public int getSize() {
    return backIndex - frontIndex;
  }

  /**
   * This function the maximum size of the queue.  It may return <TT>-1</TT>,
   * if the capacity of the queue is not fixed.
   *
   * @return int  the capacity of the queue
   */
  public int getCapacity() {
    return queue.length;
  }

  public Object getIndex(int index){
    return queue[index];
  }
}
