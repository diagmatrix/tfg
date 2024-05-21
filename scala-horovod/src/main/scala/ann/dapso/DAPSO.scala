package ann.dapso

import ann.utils.{MSEClass, uniform}

import scala.util.Random
import org.apache.spark.{SparkConf, SparkContext}

val W = 0.721  // 1 / (2 * ln(2))
val C = 1.193  //  1/2 + ln(2)

/**
 * Asynchronous distributed particle swarm optimization
 *
 * @param nInputs Dimension of the input values
 * @param nHidden Number of hidden neurons
 * @param nParticles Number of particles for the swarm optimization
 * @param maxPos Maximum value for the position of a particle when initialized
 * @param batchSize Size for the batches
 * @param w Parameter for velocity calculation
 * @param c1 Parameter for velocity calculation
 * @param c2 Parameter for velocity calculation
 */
class DAPSO(
 val nInputs: Int,
 val nHidden: Int,
 val nParticles: Int,
 val maxPos: Double,
 val batchSize: Int,
 val w: Double = W,
 val c1: Double = C,
 val c2: Double = C,
) {
  // Spark config
  val conf: SparkConf = new SparkConf()
    .setAppName("DAPSO")
    .setMaster("local[*]")
  val spContext: SparkContext = SparkContext.getOrCreate(conf)
  // Weight array size
  val nWeights: Int = (nInputs + 1) * nHidden
  // Particles' positions
  var particlesPos: Array[Array[Double]] = Array.ofDim[Array[Double]](nParticles)
  // Swarm's best position
  var bestPos = Array.empty[Double]
  // Swarm's best fitness
  var bestFitness: Double = Double.MaxValue
  // Random object
  val rand = new Random
  // Maximum velocity
  val maxV: Double = 0.6 * maxPos
  // Data to use for the algorithm
  var x: Array[Array[Double]] = _
  var y: Array[Double] = _

  /**
   * Sets the data for the particle swarm optimization and initializes weights
   * @param x Characteristics data
   * @param y Output data
   */
  def init_weights(x: List[List[Double]], y: List[Double]): Unit = {
    // Set data
    this.x = x.map(v => v.toArray).toArray
    this.y = y.toArray

    // Init weights
    for(i<-0 until nParticles){
      val position = Array.fill(nWeights)(uniform(maxPos, rand))
      val velocity = Array.fill(nWeights)(uniform(maxPos, rand))
      val fit = MSEClass(this.x, this.y, position, nInputs, nHidden)  // TODO: Change depending on net type
      val particle = position ++ velocity ++ position ++ Array(fit)
      if (fit < bestFitness) {
        bestFitness = fit
        bestPos = position
      }
      particlesPos = particlesPos :+ particle
    }
  }

  def fit(): Unit = {

  }

  def get_weights(): List[Double] = {
   return List(1.1)
  }

  def updateResult(res: Array[Double], fitness: Double): Unit = ???
}
