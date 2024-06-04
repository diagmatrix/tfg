package ann

import ann.dapso.DAPSO
import ann.utils.{ForwardProp, ForwardPropClass, ForwardPropReg, MSE, MSEClass, MSEReg}

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
  var trainer: DAPSO = _

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
   * Sets the parameters for the DAPSO
   * @param nParticles Number of particles
   * @param maxPos Maximum position of a particle
   * @param batchSize Batch size for parallel tasks
   * @param w Parameter for velocity calculation
   * @param c1 Parameter for velocity calculation
   * @param c2 Parameter for velocity calculation
   */
  def setParams(nParticles: Int, maxPos: Double, batchSize: Int, w: Double, c1: Double, c2: Double): Unit = {
    trainer = new DAPSO(nInputNeurons, nHiddenNeurons, nParticles, maxPos, netMSE, forwardProp, batchSize, w, c1, c2)
  }

  /**
   * Fits the weights to the data
   * @param nIters Number of iterations
   * @return Total number of seconds needed during training
   */
  def fit(nIters: Int): Double = {
    trainer.init_weights(xTrain, yTrain)

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
  def predict(xi: List[Double]): Double = {
    val Xi = xi.toArray
    val pr = forwardProp.compute(Xi, weights, nInputNeurons, nHiddenNeurons)
    math.signum(pr)
  }

  /**
   * @return The MSE of the fitted net
   */
  def MSEFit(): Double = {
    val xTestArr = xTest.map(xi => xi.toArray).toArray
    val yTestArr = yTest.toArray
    netMSE.compute(xTestArr, yTestArr, weights, nInputNeurons, nHiddenNeurons)
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

    val predictions = xTest.map(xi => predict(xi))
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