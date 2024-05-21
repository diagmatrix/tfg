package ann

import scala.collection.mutable.ListBuffer
import scala.math.{signum, tanh}
import scala.util.Random

package object utils {
  /**
   * Reads a CSV file with data
   *
   * @param fileName File path
   * @param nRows    Number of rows to keep from the file
   * @return A list with the rows of data
   */
  def readCSV(fileName: String, nRows: Int): List[List[Double]] = {
    import scala.io.Source

    // Get data from file
    val data = Source.fromFile(fileName)

    // Transform the data into a list of rows
    val rows = data.getLines().drop(1).filter(line => {
      val cols = line.split(",").map(_.trim)
      cols.forall(_.nonEmpty)
    }).take(nRows).map(line => {
      val cols = line.split(",").map(_.trim).toList.map(_.toDouble)
      cols
    }).toList

    data.close()
    rows
  }

  /**
   * Separates a list of data into the characteristics and the outcome
   *
   * @param data      List of data
   * @param setPosNeg Whether to interpolate {0, 1} to {-1, 1} for the outcome values. (For classification)
   * @return The characteristics data and its outcome
   */
  def separateXY(data: List[List[Double]], setPosNeg: Boolean = false): (List[List[Double]], List[Double]) = {
    val rowMax = data.head.size - 1
    val xData = data.map(_.take(rowMax))
    var yData = data.map(_(rowMax))

    // Interpolate outcome values
    if (setPosNeg) {
      yData = yData.map {
        case 0.0 => -1.0
        case 1.0 => 1.0
        case _ => throw new IllegalArgumentException("Invalid outcome value")
      }
    }

    (xData, yData)
  }

  /**
   * Generates a number in the interval (-x,x)
   *
   * @param x    The frontier value of the interval
   * @param rand Random object
   * @return A number between -x and x
   */
  def uniform(x: Double, rand: Random): Double = (rand.nextDouble() * 2 * x) - x

  /**
   * Calculates the mean square error of an ANN
   *
   * @param x          Characteristics data
   * @param y          Output data
   * @param weights    Net weights
   * @param nInput     Number of input neurons
   * @param nHidden    Number of hidden neurons
   * @param clas       Whether the net is a classifier
   * @return The MSE of the net
   */
  private def MSE(x: Array[Array[Double]], y: Array[Double], weights: Array[Double], nInput: Int, nHidden: Int, clas: Boolean): Double = {
    val yPredictor = fowardProp(_, _, _, _, clas)
    val predictions = x.map(xi => yPredictor(xi, weights, nInput, nHidden))
    val errors = (predictions zip y).map(v => math.pow(v._2 - v._1, 2))

    errors.sum / x.length
  }

  /**
   * Foward propagation prediction of output
   * @param xi Input data for prediction
   * @param weights Net weights
   * @param nInput Number of input neurons
   * @param nHidden Number of hidden neurons
   * @param clas Whether the net is for prediction or classification
   * @return The predicted output
   */
  private def fowardProp(xi: Array[Double], weights: Array[Double], nInput: Int, nHidden: Int, clas: Boolean): Double = {
    // Input to hidden
    val z2 = Array.fill(nHidden)(0.0)
    for (j <- 0 until nHidden) {
      val weightsSlice = weights.slice(nInput * j, nInput * (j + 1))
      val result = (xi zip weightsSlice).map(v => v._1 * v._2).sum
      z2(j) = tanh(result)
    }

    // Hidden to output
    val weightsSlice = weights.slice(nInput * nHidden, weights.length)
    val z3 = (z2 zip weightsSlice).map(v => v._1 * v._2).sum

    if (clas) {
      tanh(z3)
    } else {
      z3
    }
  }

  /**
   * MSE functions for classification and regression
   */
  val MSEClass: (Array[Array[Double]], Array[Double], Array[Double], Int, Int) => Double = MSE(_, _, _, _, _, clas = true)
  val MSEReg: (Array[Array[Double]], Array[Double], Array[Double], Int, Int) => Double = MSE(_, _, _, _, _, clas = false)

  /**
   * Calculates the fitness of the Net
   */
  def calculateFitness(x: Array[Array[Double]], y: Array[Double], weights: Array[Double], nInput: Int, nHidden: Int): Array[Double] = {
    val nWeights: Int = nHidden * (nInput + 1)

    // Return if there are no weights
    if (weights == null) {
      return Array.empty[Double]
    }

    // Calculate fitness
    val bestLocalFit = weights(3 * nWeights)
    val weightSlice = weights.slice(0, nWeights)
    val fit = calculateAccuracy(x, y, weightSlice, nInput, nHidden)  // TODO: Maybe change for not classification

    if (fit < bestLocalFit) {
      weights(3 * nWeights) = fit
      for (k <- 0 until nWeights) {
        weights(2 * nWeights + k) = weightSlice(k)
      }
    }
    weights
  }

  private def accuracy(yTrue: Array[Double], yPred: Array[Double]): Double = {
    require(yTrue.length == yPred.length, "Both lists must have the same length")

    val totalSamples = yTrue.length
    val correctPredictions = yTrue.zip(yPred).count { case (trueLabel, predictedLabel) => trueLabel == predictedLabel }

    correctPredictions.toDouble / totalSamples
  }

  /**
   * Calculates the accuracy of the net
   */
  def calculateAccuracy(x: Array[Array[Double]], y: Array[Double], weights: Array[Double], nInput: Int, nHidden: Int): Double = {
    var yPred: Array[Double] = Array.emptyDoubleArray
    for (i <- x.indices) {
      val pr = signum(fowardProp(x(i), weights, nInput, nHidden, clas = true))
      yPred = yPred :+ pr
    }
    1.0 - accuracy(y, yPred)
  }

  /**
   * Transforms a 2D array into a ListBuffer of an array
   */
  def toListBuffer(arrayArray: Array[Array[Double]]): ListBuffer[Array[Double]] = {
    val listBuffer: ListBuffer[Array[Double]] = ListBuffer.empty[Array[Double]]

    for (subArray <- arrayArray) {
      val list: List[Double] = subArray.toList
      listBuffer += list.toArray
    }
    listBuffer
  }
}
