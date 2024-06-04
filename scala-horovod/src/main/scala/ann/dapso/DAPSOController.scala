package ann.dapso

class DAPSOController(dapso: DAPSO) {
  def receiveResult(res: Array[Double], fitness: Double): Unit = dapso.updateResult(res, fitness)
}