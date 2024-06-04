package ann.utils

import scala.math.{tan, tanh}

/**
 * Foward propagation trait
 */
trait ForwardProp {
  /**
   * Foward propagation prediction of output
   * @param xi Input data for prediction
   * @param weights Net weights
   * @param nInput Number of input neurons
   * @param nHidden Number of hidden neurons
   * @return The predicted output
   */
  def compute(xi: Array[Double], weights: Array[Double], nInput: Int, nHidden: Int): Double = {
    // Input to hidden
    val z2 = Array.fill(nHidden)(0.0)
    for (j <- 0 until nHidden) {
      val weightsSlice = weights.slice(nInput * j, nInput * (j + 1))
      val result = (xi zip weightsSlice).map(v => v._1 * v._2).sum
      z2(j) = tanh(result)
    }

    // Hidden to output
    val weightsSlice = weights.slice(nInput * nHidden, weights.length)
    (z2 zip weightsSlice).map(v => v._1 * v._2).sum
  }
}

/**
 * Foward propagation for Classifiers
 */
object ForwardPropClass extends ForwardProp {
  override def compute(xi: Array[Double], weights: Array[Double], nInput: Int, nHidden: Int): Double = {
    val z3 = super.compute(xi, weights, nInput, nHidden)
    tanh(z3)
  }
}

/**
 * Foward propagation for Regressors
 */
object ForwardPropReg extends ForwardProp {
  override def compute(xi: Array[Double], weights: Array[Double], nInput: Int, nHidden: Int): Double = super.compute(xi, weights, nInput, nHidden)
}