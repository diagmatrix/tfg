package ann

import ann.dapso.DAPSO

/**
 * Represents a basic Artificial Neural Network for classification
 *
 * @param nInputs Dimension of the input values
 * @param nHidden Number of hidden neurons
 */
class Classifier(val nInputs: Int, val nHidden: Int) {
 // Training data
 private var xTrain: List[List[Double]] = _
 private var yTrain: List[Double] = _
 // Test data
 private var xTest: List[List[Double]] = _
 private var yTest: List[Double] = _
 // Fit algorithm
 private var trainer: DAPSO = _

 /**
  * Sets the training and test data for the classifier
  * @param xTrain Training dataset's characteristics
  * @param yTrain Training dataset's outcomes
  * @param xTest Test dataset's characteristics
  * @param yTest Test dataset's outcomes
  */
 def setData(xTrain: List[List[Double]], yTrain: List[Double], xTest: List[List[Double]], yTest: List[Double]): Unit = {
  this.xTrain = xTrain
  this.xTest = xTest
  this.yTrain = yTrain
  this.yTest = yTest
 }
}
