package ANN

package object utils {
  /**
   * Reads a CSV file with data
   * @param fileName File path
   * @param nRows Number of rows to keep from the file
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
   * @param data List of data
   * @param setPosNeg Whether to interpolate {0, 1} to {-1, 1} for the outcome values. (For classification)
   * @return The characteristics data and its outcome
   */
  def separateXY(data: List[List[Double]], setPosNeg: Boolean = false): (List[List[Double]], List[Double]) = {
    val rowMax = data.head.size - 1
    val xData = data.map(_.take(rowMax))
    var yData = data.map(_(rowMax))

    if (setPosNeg) {
      yData = yData.map {
        case 0.0 => -1.0
        case 1.0 => 1.0
        case _ => throw new IllegalArgumentException("Invalid outcome value")
      }
    }

    (xData, yData)
  }
}
