class ClientParticulier(nomDuClient: String?, numeroDuCompteur: Int?, consommationElectrique: Double?) : Client(nomDuClient, numeroDuCompteur, consommationElectrique) {

    override var tarifParKwh = 50.0

    override fun afficherClient() {
        super.afficherClient()
    }

    override fun calculerMontantFacture(): Double {
        return super.calculerMontantFacture()
    }

    override fun afficherFacture() {
        super.afficherFacture()
    }
}
