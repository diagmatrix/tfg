package ann.dapso

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import ann.dapso.FitnessActor.ContinueReceivingMessage

import scala.collection.mutable.ListBuffer
import scala.concurrent.Channel
import scala.util.Random

object GlobalActor {
  // Messages
  sealed trait SystemMessage
  case object StartSendingMessages extends SystemMessage
  case object ContinueSendingMessages extends SystemMessage
  case object StopSendingMessages extends SystemMessage
  // Channels
  private var srch: Channel[Batch] = _
  private var fuch: Channel[ListBuffer[Array[Double]]] = _
  // DAPSO variables
  var bestFitness: Double = Double.MaxValue
  var bestPos: Array[Double] = Array.empty[Double]
  private var nWeights: Int = _
  private var batchSize: Int = _
  private var nIters: Int = _
  private var nParticles: Int = _
  private var rand: Random = _
  private var W: Double = _
  private var c1: Double = _
  private var c2: Double = _
  private var maxV: Double = _
  private var particlesPos: Array[Array[Double]] = _
  private var maxPos: Double = _

  /**
   * Initializes object
   */
  def initialize(srch: Channel[Batch], fuch: Channel[ListBuffer[Array[Double]]], N: Int, S: Int, I: Int, m: Int,
                 rand: Random, W: Double, c1: Double, c2: Double, maxPos: Double, particlesPos: Array[Array[Double]]): Unit = {
    GlobalActor.fuch = fuch
    GlobalActor.srch = srch
    GlobalActor.nWeights = N
    GlobalActor.batchSize = S
    GlobalActor.nIters = I
    GlobalActor.nParticles = m
    GlobalActor.rand = rand
    GlobalActor.W = W
    GlobalActor.c1 = c1
    GlobalActor.c2 = c2
    GlobalActor.maxPos = maxPos
    GlobalActor.maxV = 0.6 * maxPos
    GlobalActor.particlesPos = particlesPos
  }

  def apply(dapsoController: DAPSOController): Behavior[SystemMessage] = Behaviors.setup[SystemMessage] {
    actorContext =>
      val fitnessEvalActor = actorContext.spawn(FitnessActor(), "FitnessActor")
      val batch = new Batch(batchSize)

      // Add particles to the queue
      for (i <- 0 until nParticles) {
        if (batch.isFull) {
          srch.write(batch.copy())
          fitnessEvalActor ! ContinueReceivingMessage()
          batch.clean()
        }
        batch.add(particlesPos(i))
      }

      Behaviors.receiveMessage {
        case StartSendingMessages =>
          println("Start Global Actor task")

          val iters = nIters * nParticles / batchSize
          for (i <- 0 until iters) {
            // Read from the Fitness writing channel
            var sr = fuch.read

            var pos: Array[Double] = new Array[Double](0)
            var velocity: Array[Double] = new Array[Double](0)
            var bestGlobalPos: Array[Double] = new Array[Double](0)
            var fit: Double = 0

            for (par <- sr) {
              pos = par.slice(0, nWeights)
              velocity = par.slice(nWeights, 2 * nWeights)
              bestGlobalPos = par.slice(2 * nWeights, 3 * nWeights)
              fit = par(3 * nWeights)

              if (fit < bestFitness) {
                bestFitness = fit
                bestPos = bestGlobalPos
              }
              val newPar = FA.posEval(par, mejor_pos_global, N, rand, W, c_1, c_2, V_max, pos_max)
              if (batch.isFull) {
                srch.write(batch.copy())
                fitnessEvalActor ! ContinueReceivingMessage()
                batch.clean()
              }
              batch.add(newPar)
            }
            // Clean the vars
            sr = null
            pos = null
            velocity = null
            bestGlobalPos = null
          }



          actorContext.self ! StopSendingMessages

          Behaviors.same
        case StopSendingMessages =>
          dapsoController.recieveResult(bestPos, bestFitness)
          Behaviors.stopped
      }
  }
}
