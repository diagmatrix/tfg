package ann.trainer

/**
 * Training algorithms trait
 */
trait Trainer {
  // Net data
  var nInputs: Int = _
  var nHidden: Int = _
  var MSEFunction: MSE = _
  var forwardPropFunction: ForwardProp = _
  var nWeights: Int = _

  /**
   * Sets the data derived from the neural network
   * @param nInputs Number of input neurons
   * @param nHidden Number of hidden neurons
   * @param mse MSE function
   * @param forwardProp Forward Propagation function
   */
  def setStructure(nInputs: Int, nHidden: Int, mse: MSE, forwardProp: ForwardProp): Unit = {
    // Net data
    this.nInputs = nInputs
    this.nHidden = nHidden
    this.MSEFunction = mse
    this.forwardPropFunction = forwardProp

    // Weight array size
    this.nWeights = (nInputs + 1) * nHidden
  }

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
