package utils;

/**
 * This class defines the "empty queue" exception.
 * It is thrown when the client attempts to retrieve
 * or remove an element on a queue that is empty.
 */
public class EmptyQueueException extends RuntimeException {

  private Queue queue;

  public EmptyQueueException(Queue queue) {
    super("The queue is empty.");
    this.queue = queue;
  }

  public Queue getQueue() {
    return queue;
  }
}
