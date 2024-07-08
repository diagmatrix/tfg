package ann

import ann.trainer.Trainer
import ann.utils.{ForwardProp, ForwardPropClass, ForwardPropReg, MSE, MSEClass, MSEReg, writeCSV}

import java.time.LocalDateTime

/**
 * Artificial neural network trait
 */
trait ANN {
  // Net data
  var nInputNeurons: Int = _
  var nHiddenNeurons: Int = _
  // Training data
  var xTrain: List[List[Double]] = _
  var yTrain: List[Double] = _
  // Test data
  var xTest: List[List[Double]] = _
  var yTest: List[Double] = _
  // Weights
  var weights: Array[Double] = _
  // Mean square error method
  var netMSE: MSE = _
  // Forward propagation method
  var forwardProp: ForwardProp = _
  // Training mechanism
  var trainer: Trainer = _

  /**
   * Sets the training data for the net
   * @param filename Name of the file containing the data (csv file)
   * @param maxRows Maximum number of rows to keep
   */
  def setTrainingData(filename: String, maxRows: Int): Unit

  /**
   * Sets the test data for the net
   * @param filename Name of the file containing the data (csv file)
   * @param maxRows Maximum number of rows to keep
   */
  def setTestData(filename: String, maxRows: Int): Unit

  /**
   * Sets the training algorithm for the neural net
   * @param alg Training algorithm
   */
  def setTrainer(alg: Trainer): Unit = {
    trainer = alg
  }

  /**
   * Fits the weights to the data
   * @param nIters Number of iterations
   * @return Total number of seconds needed during training
   */
  def fit(nIters: Int): Double = {
    trainer.initWeights(xTrain, yTrain)

    val start = System.nanoTime()
    trainer.fit(nIters)
    val end = System.nanoTime()

    weights = trainer.getWeights
    ((end - start).toDouble / 1000000000).round
  }

  /**
   * Predicts an output given a value
   * @param xi Value to predict output
   * @return Predicted output
   */
  def predict(xi: List[Double]): Double = forwardProp.compute(xi.toArray, weights, nInputNeurons, nHiddenNeurons)

  /**
   * Predicts the full test dataset
   * @return The list of predicted outcomes
   */
  def predictTest(): List[Double] = xTest.map(xi => forwardProp.compute(xi.toArray, weights, nInputNeurons, nHiddenNeurons))

  /**
   * @return The MSE of the fitted net
   */
  def MSEFit(): Double = {
    val xTestArr = xTest.map(xi => xi.toArray).toArray
    val yTestArr = yTest.toArray
    netMSE.compute(xTestArr, yTestArr, weights, nInputNeurons, nHiddenNeurons)
  }

  /**
   * Writes the weights into a file
   * @param filename Name of the file
   */
  def writeWeights(filename: String = "no_file"): Unit = {
    val file= if (filename=="no_file") "net_"+LocalDateTime.now().toString else filename
    writeCSV(file, weights)
  }
}

/**
 * Classifier neural network
 * @param nInputs Number of input neurons
 * @param nHidden Number of hidden neurons
 */
class Classifier(val nInputs: Int, val nHidden: Int) extends ANN {
  // Set the net parameters
  nInputNeurons = nInputs
  nHiddenNeurons = nHidden
  // Set MSE and forwardProp
  netMSE = MSEClass
  forwardProp = ForwardPropClass

  override def setTrainingData(filename: String, maxRows: Int): Unit = {
    val trainFile = utils.readCSV(filename, maxRows)
    val (xData, yData) = utils.separateXY(trainFile, setPosNeg = true)
    xTrain = xData
    yTrain = yData
  }

  override def setTestData(filename: String, maxRows: Int): Unit = {
    val testFile = utils.readCSV(filename, maxRows)
    val (xData, yData) = utils.separateXY(testFile, setPosNeg = true)
    xTest = xData
    yTest = yData
  }

  /**
   * @return The accuracy of the net
   */
  def accuracy(): Double = {
    if (xTest.isEmpty || yTest.isEmpty) {
      throw new IllegalStateException("You need to set the data first")
    } else if (weights.isEmpty) {
      throw new IllegalStateException("Fit the data first")
    }

    val predictions = this.predictTest()
    val successes = (predictions zip yTest).map(yPairs => if (yPairs._1 == yPairs._2) 1 else 0).sum.toDouble

    successes / yTest.length
  }
}

/**
 * Regressor neural network
 * @param nInputs Number of input neurons
 * @param nHidden Number of hidden neurons
 */
class Regressor(val nInputs: Int, val nHidden: Int) extends ANN {
  // Set the net parameters
  nInputNeurons = nInputs
  nHiddenNeurons = nHidden
  // Set MSE and forwardProp
  netMSE = MSEReg
  forwardProp = ForwardPropReg

  override def setTrainingData(filename: String, maxRows: Int): Unit = {
    val trainFile = utils.readCSV(filename, maxRows)
    val (xData, yData) = utils.separateXY(trainFile)
    xTrain = xData
    yTrain = yData
  }

  override def setTestData(filename: String, maxRows: Int): Unit = {
    val testFile = utils.readCSV(filename, maxRows)
    val (xData, yData) = utils.separateXY(testFile)
    xTest = xData
    yTest = yData
  }
}