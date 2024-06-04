package ann.dapso

import ann.utils.{ForwardProp, MSE, MSEClass, uniform}
import akka.actor.typed.ActorSystem
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Channel}
import scala.util.Random

/**
 * Asynchronous distributed particle swarm optimization
 *
 * @param nInputs Number of input values
 * @param nHidden Number of hidden neurons
 * @param nParticles Number of particles for the swarm optimization
 * @param maxPos Maximum value for the position of a particle when initialized
 * @param MSEFunction Function for MSE calculation
 * @param forwardPropFunction Function for forward propagation
 * @param batchSize Size for the batches (5 default)
 * @param w Parameter for velocity calculation (1 default)
 * @param c1 Parameter for velocity calculation (0.8 default)
 * @param c2 Parameter for velocity calculation (0.2 default)
 */
class DAPSO(
  val nInputs: Int,
  val nHidden: Int,
  val nParticles: Int,
  val maxPos: Double,
  val MSEFunction: MSE,
  val forwardPropFunction: ForwardProp,
  val batchSize: Int = 5,
  val w: Double = 1,
  val c1: Double = 0.8,
  val c2: Double = 0.2
 ) {
  // Spark configuration
  val sConf: SparkConf = new SparkConf()
    .setAppName("DAPSO")
    .setMaster("local[*]")
  val sContext: SparkContext = SparkContext.getOrCreate(sConf)

  // Channel definitions
  val srch = new Channel[BatchPSO]()
  val fuch = new Channel[ListBuffer[Array[Double]]]()

  // Weight array size
  val nWeights: Int = (nInputs + 1) * nHidden
  //Random object
  val rand = new Random
  // Maximum velocity
  val vMax: Double = 0.6 * maxPos
  // Particles' positions
  var particlesPos = Array.empty[Array[Double]]
  // Swarm's best position
  var bestPos = Array.empty[Double]
  // Swarm's best fitness
  var bestFitness: Double = Double.MaxValue

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
    // Initialize weights
    for(i<-0 until nParticles){
      val position = Array.fill(nWeights)(uniform(-maxPos, maxPos, rand))
      val velocity = Array.fill(nWeights)(uniform(-maxPos, maxPos, rand))
      val fit = MSEFunction.compute(this.x, this.y, position, nInputs, nHidden)
      val part_ = position ++ velocity ++ position ++ Array(fit)
      if (fit < bestFitness) {
        bestFitness = fit
        bestPos = position
      }
      particlesPos = particlesPos :+ part_
    }
  }

  /**
   * Fit the data
   */
  def fit(nIters: Int):Unit ={
    import ann.dapso.GlobalActor.{StartSendingMessages, StopSendingMessages}

    // Check if the net is a classifier
    val isClas = if (MSEFunction == MSEClass) true else false

    FitnessActor.initialize(srch,fuch,x,y,nInputs,nHidden,sContext, isClas)
    GlobalActor.initialize(srch,fuch,nWeights,batchSize,nIters,nParticles,rand,w,c1,c2,vMax, particlesPos)
    val dapsoController = new DAPSOController(this)
    val globalEvalActor = ActorSystem(GlobalActor(dapsoController),"GlobalEvalActor")

    globalEvalActor ! StartSendingMessages
    globalEvalActor ! StopSendingMessages
    Await.result(globalEvalActor.whenTerminated, Duration.Inf )

  }

  /**
   * @return The weights (swarm's best position)
   */
  def getWeights: Array[Double] = {
    bestPos
  }

  /**
   * Updates the swarm's best position and fitness
   * @param pos Swarm position
   * @param fitness Swarm fitness
   */
  def updateResult(pos: Array[Double], fitness: Double): Unit = {
    bestPos = pos
    bestFitness = fitness
  }
}
