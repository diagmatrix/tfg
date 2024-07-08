import ann.trainer.dapso.DAPSO
import ann.{Classifier, Regressor, utils}
import ann.utils.{ForwardPropClass, ForwardPropReg, MSEClass, MSEReg}

import java.io.{FileWriter, PrintWriter}

object main {
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

//    val net1 = new Classifier(cI, cH)
//    net1.setTrainingData("cancer_train.csv", nRows)
//    net1.setTestData("cancer_test.csv", nRows)
//    val dapso1 = new DAPSO(cI,cH,nParts,MSEClass,ForwardPropClass,maxPos,c1 = c1, c2=c2, w=1, batchSize = 5, rddNum = 4)
//    net1.setTrainer(dapso1)

    val net2 = new Regressor(rI, rH)
    net2.setTrainingData("abalone_train.csv", nRows)
    net2.setTestData("abalone_test.csv", nRows)
    val dapso2 = new DAPSO(nParts,maxPos,c1 = c1, c2=c2, batchSize = 5, rddNum = 8)
    net2.setTrainer(dapso2)

    val validationFile = new FileWriter(s"resultados.txt", true)
    val hiperparametersFile = new PrintWriter(validationFile)

//    hiperparametersFile.println(s"CLASIFICADOR (c1=$c1, c2=$c2, maxPos=$maxPos)")
//    val cTime = net1.fit(nIters)
//    hiperparametersFile.println(s"Tiempo de entrenamiento: $cTime")
//    val cMSE = net1.MSEFit()
//    hiperparametersFile.println(s"MSE final: $cMSE")
//    val acc = net1.accuracy()
//    hiperparametersFile.println(s"Accuracy: $acc")

    hiperparametersFile.println(s"REGRESOR (c1=$c1, c2=$c2, maxPos=$maxPos)")
    val rTime = net2.fit(nIters)
    hiperparametersFile.println(s"Tiempo de entrenamiento: $rTime")
    val rMSE = net2.MSEFit()
    hiperparametersFile.println(s"MSE final: $rMSE")
    net2.writeWeights()

    hiperparametersFile.close()
  }
}
