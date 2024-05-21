package ann.dapso

import akka.actor.typed.Behavior
import akka.actor.typed.javadsl.{ActorContext, Behaviors}
import ann.utils.{calculateFitness, toListBuffer}
import org.apache.spark.SparkContext

import scala.collection.mutable.ListBuffer
import scala.concurrent.Channel

object FitnessActor {
  // Messages
  sealed trait MessageToFitnessActor
  final case class ContinueReceivingMessage() extends MessageToFitnessActor
  // Reading channel
  private var srch: Channel[Batch] = _
  // Writing channel
  private var fuch: Channel[ListBuffer[Array[Double]]] = _
  // Net data
  private var x: Array[Array[Double]] = _
  private var y: Array[Double] = _
  private var nInput: Int = _
  private var nHidden: Int = _
  // Spark context
  private var spContext: SparkContext = _

  /**
   * Initializes object variables
   */
  def init(
    srch: Channel[Batch], fuch: Channel[ListBuffer[Array[Double]]], data: Array[Array[Double]],
    y: Array[Double], nInputs: Int, nHidden: Int, sc: SparkContext
  ): Unit = {
    FitnessActor.srch = srch
    FitnessActor.fuch = fuch
    FitnessActor.x = data
    FitnessActor.y = y
    FitnessActor.nInput = nInputs
    FitnessActor.nHidden = nHidden
    FitnessActor.spContext = sc
  }

  /**
   * Processes data to get the fitness
   */
  private def processFitness(): Unit = {
    // Get batch
    val batch = srch.read
    val batchData = batch.getBatch.toArray
    // Set parallelization
    val RDD = spContext.parallelize(batchData, 4)
    val psfu_array = RDD.map(part => calculateFitness(x, y, part, nInput, nHidden)).collect()
    // Write result
    val psfu = toListBuffer(psfu_array)
    fuch.write(psfu)
  }

  def apply(): Behavior[MessageToFitnessActor] = Behaviors.setup {
    context: ActorContext[MessageToFitnessActor] =>
      Behaviors.receiveMessage {
        case ContinueReceivingMessage() =>
          processFitness()
          Behaviors.same
      }
  }
}
