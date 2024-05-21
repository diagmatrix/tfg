import ann.utils

object main {
  def main(args: Array[String]): Unit = {
    // Constants
    val nRows = 2080

    // Get file
    val file = utils.readCSV("covid_test.csv", nRows)
    val (xTrain, yTrain) = utils.separateXY(file)

    print(yTrain)

  }
}
