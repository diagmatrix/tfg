package ann.trainer

/**
 * Training algorithms trait
 */
trait Trainer {
  /**
   * Initializes weights and sets the training data for the algorithm
   * @param x Features data
   * @param y Expected output
   */
  def initWeights(x: List[List[Double]], y: List[Double]): Unit

  /**
   * Fits the data
   * @param nIters Number of iterations
   */
  def fit(nIters: Int): Unit

  /**
   * @return The fitted weights
   */
  def getWeights: Array[Double]
}
