import ann.dapso.DAPSO
import ann.{Classifier, Regressor, utils}
import ann.utils.{ForwardPropClass, MSEClass}

import java.io.{FileWriter, PrintWriter}

object main {
  /*
  def main(args: Array[String]): Unit = {
    // Constants
    val nRows = 2080

    // Get files
    val trainFile = utils.readCSV("covid_training.csv", nRows)
    val testFile = utils.readCSV("covid_test.csv", nRows)
    val (xTrain, yTrain) = utils.separateXY(trainFile, setPosNeg = true)
    val (xTest, yTest) = utils.separateXY(testFile, setPosNeg = true)

    // ANN & DAPSO constants
    val nInputs = xTrain.headOption.map(_.length).getOrElse(0)
    val nHidden = (1.9*nInputs).toInt
    val nIters = 10
    val nParts = 100
    val maxPos = 1.0
    val c1 = 0.4
    val c2 = 0.2

    // Output
    val validationFile = new FileWriter(s"resultados_validacion_3.txt", true)
    val hiperparametersFile = new PrintWriter(validationFile)

    // Execution
    hiperparametersFile.println(s"resultados con c1 $c1, c2 $c2, posicion maxima $maxPos")
    val trainer = new DAPSO(nInputs, nHidden, nParts, maxPos, MSEClass, ForwardPropClass, c1 = c1, c2 = c2)
    trainer.init_weights(xTrain, yTrain)

    val start = System.nanoTime()
    trainer.fit(nIters)
    val end = System.nanoTime()
    val weights = trainer.getWeights
    val totalTime = ((end - start).toDouble / 1000000000).round

    var predictions = List[Double]()
    for (i <- xTest.indices) {
      val xTestSer = xTest(i).toArray
      val prDouble = ForwardPropClass.compute(xTestSer, weights, nInputs, nHidden)
      val pr = math.signum(prDouble)
      predictions = predictions :+ pr
    }

    val successes = (predictions zip yTest).map(yPairs => if (yPairs._1 == yPairs._2) 1 else 0).sum.toDouble
    val acc = successes / yTest.length

    hiperparametersFile.println("Tiempo total: " + totalTime)
    hiperparametersFile.println("Eout accuracy: " + acc)

    hiperparametersFile.close()
  }
  */
  def main(args: Array[String]): Unit = {
    // Constants
    val nRows = 2080
    val cI = 30
    val cH = (1.9*cI).toInt
    val rI = 8
    val rH = (1.9*rI).toInt
    val nIters = 100
    val nParts = 100
    val maxPos = 1.0
    val c1 = 0.4
    val c2 = 0.2

    val net1 = new Classifier(cI, cH)
    net1.setTrainingData("cancer_train.csv", nRows)
    net1.setTestData("cancer_test.csv", nRows)
    net1.setParams(nParts, maxPos, 5, 1, c1, c2)

    val net2 = new Regressor(rI, rH)
    net2.setTrainingData("abalone_train.csv", nRows)
    net2.setTestData("abalone_test.csv", nRows)
    net2.setParams(nParts, maxPos, 5, 1, c1, c2)

    val validationFile = new FileWriter(s"resultados.txt", true)
    val hiperparametersFile = new PrintWriter(validationFile)

    hiperparametersFile.println(s"CLASIFICADOR (c1=$c1, c2=$c2, maxPos=$maxPos)")
    val cTime = net1.fit(nIters)
    hiperparametersFile.println(s"Tiempo de entrenamiento: $cTime")
    val cMSE = net1.MSEFit()
    hiperparametersFile.println(s"MSE final: $cMSE")
    val acc = net1.accuracy()
    hiperparametersFile.println(s"Accuracy: $acc")

    hiperparametersFile.println(s"REGRESOR (c1=$c1, c2=$c2, maxPos=$maxPos)")
    val rTime = net2.fit(nIters)
    hiperparametersFile.println(s"Tiempo de entrenamiento: $rTime")
    val rMSE = net2.MSEFit()
    hiperparametersFile.println(s"MSE final: $rMSE")

    hiperparametersFile.close()
  }
}
