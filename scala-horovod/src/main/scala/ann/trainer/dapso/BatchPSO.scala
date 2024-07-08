package ann.trainer.dapso

import scala.collection.mutable.ListBuffer

class BatchPSO(private val size: Int) {
  private val batches: ListBuffer[Array[Double]] = ListBuffer.empty[Array[Double]]
  private var index: Int = 0

  def add(elem: Array[Double]): Unit = {
    if (index < size) {
      batches += elem
      index += 1
    } else {
      throw new IllegalStateException("Batch full")
    }
  }

  def isFull: Boolean = index == size

  def getBatch: ListBuffer[Array[Double]] = batches

  def getIndex: Int = index

  def copy(): BatchPSO = {
    val copiedBatch = new BatchPSO(size)
    copiedBatch.index = index
    for (i <- 0 until index) {
      copiedBatch.batches += batches(i).clone()
    }
    copiedBatch
  }

  def clean(): Unit = {
    batches.clear()
    index = 0
  }
}
