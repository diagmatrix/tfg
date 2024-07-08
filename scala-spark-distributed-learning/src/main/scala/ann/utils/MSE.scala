package ann.utils

/**
 * Mean Square Error trait
 */
trait MSE {
  /**
   * Calculates the mean square error of an ANN
   *
   * @param x          Characteristics data
   * @param y          Output data
   * @param weights    Net weights
   * @param nInput     Number of input neurons
   * @param nHidden    Number of hidden neurons
   * @return The MSE of the net
   */
  def compute(x: Array[Array[Double]], y: Array[Double], weights: Array[Double], nInput: Int, nHidden: Int): Double
}

/**
 * MSE for classification
 */
object MSEClass extends MSE {
  override def compute(x: Array[Array[Double]], y: Array[Double], weights: Array[Double], nInput: Int, nHidden: Int): Double = {
    val predictions = x.map(xi => ForwardPropClass.compute(xi, weights, nInput, nHidden))
    val errors = (predictions zip y).map(v => math.pow(v._2 - v._1, 2))

    errors.sum / x.length
  }
}

/**
 * MSE for regression
 */
object MSEReg extends MSE {
  override def compute(x: Array[Array[Double]], y: Array[Double], weights: Array[Double], nInput: Int, nHidden: Int): Double = {
    val predictions = x.map(xi => ForwardPropReg.compute(xi, weights, nInput, nHidden))
    val errors = (predictions zip y).map(v => math.pow(v._2 - v._1, 2))

    errors.sum / x.length
  }
}