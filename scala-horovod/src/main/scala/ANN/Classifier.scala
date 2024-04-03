package ANN

/**
 * Represents a basic Artificial Neural Network for classification
 * @param nInputs - Size of the input values
 * @param layers - List containing the amount of neurons per layer. For example:
 *               [100, 50] represents 2 hidden layers, the first with 100 neurons
 *               and the second with 50.
 */
class Classifier(val nInputs: Int, val layers: List[Int]) {
 // Training data
 private var xTrain: List[Double] = _
 private var yTrain: List[Double] = _
 // Test data
 private var xTest: List[Double] = _
 private var yTest: List[Double] = _
 // Fit algorithm

 def setData(xTrain: List[Double], yTrain: List[Double], xTest: List[Double], yTest: List[Double]): Unit = {
  this.xTrain = xTrain
  this.xTest = xTest
  this.yTrain = yTrain
  this.yTest = yTest
 }
}
