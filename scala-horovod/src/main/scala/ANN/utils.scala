package ANN

package object utils {
  /**
   * Reads a CSV file with data
   * @param fileName File path
   * @param nRows Number of rows to keep from the file
   * @return A list with the rows of data
   */
  def readCSV(fileName: String, nRows: Int, removeEmpty: Boolean): List[List[Double]] = {
    import scala.io.Source

    // Get data from file
    val data = Source.fromFile(fileName)
    data.close()

    // Transform the data into a list of rows
    val rows = data.getLines().drop(1).filter(line => {
      val cols = line.split(",").map(_.trim)
      cols.forall(_.nonEmpty)
    }).take(nRows).map(line => {
      val cols = line.split(",").map(_.trim).toList.map(_.toDouble)
      cols
    }).toList

    rows
  }
}
