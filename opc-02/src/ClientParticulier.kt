
class ClientParticulier(nomClient: String?, numeroCompteur: Int?, consommation : Double?): Client(nomClient,numeroCompteur,consommation) {

    //taux en fonction du type de client
    override var tauxParKwh = 50.0

    override fun AffichageClient(){
        super.AffichageClient()
    }

    override fun calculerFacture(): Double {
        return super.calculerFacture()

    }

    override fun AffichageFacturation(){
        super.AffichageFacturation()
    }






}