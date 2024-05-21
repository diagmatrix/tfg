package ann.dapso

class DAPSOController(dapso: DAPSO) {
  def recieveResult(res: Array[Double], fitness: Double): Unit = dapso.updateResult(res, fitness)
}