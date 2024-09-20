

open class Client  {

    var nomClient : String? = null
    var numeroCompteur :Int? = null
    var consommation : Double? = null
    var intence = 0
    open var tauxParKwh = 50.0

    constructor(nomClient: String?, numeroCompteur: Int?, consommation : Double?, intence : Int = 0){

        this.nomClient = nomClient
        this.numeroCompteur = numeroCompteur
        this.consommation = consommation
        this.intence = intence+1
    }

    // Fonction pour vérifier si la consommation est anormale
    fun verifierConsommationAnormale() {
        if (this.consommation!! > 5000) {
            throw Exception("Vous avez atteint la limite maximal de consommation avec : ${this.consommation!!} kWh")
        }
    }

    // Fonction pour vérifier si il merite reduction
    fun verifierReduction() : Boolean {
        if (this.consommation!! > 1000) {
            return true
        }
        return false
    }

    // Fonction pour calculer la facture avec gestion des exceptions
    open fun calculerFacture(): Double {
        if (this.consommation!! == null || this.consommation!! <= 0) {
            throw IllegalArgumentException("Consommation invalide.")
        }

        // Vérifier si la consommation est anormale
        verifierConsommationAnormale()


        if(verifierReduction()){
            return ((this.consommation!! * this.tauxParKwh ) * 10) / 100
        }

        return this.consommation!! * this.tauxParKwh
    }

    //Affichage des client
    open fun AffichageFacturation(){
        try {
            println("${this.nomClient} (Compteur: ${this.numeroCompteur}) -> \uD83E\uDDEE Consommation: ${this.consommation} kWh | \uD83D\uDCB0 Facture: ${calculerFacture()} FCFA")
        } catch (e: Exception) {
            println("${this.nomClient} (Compteur: ${this.numeroCompteur}) -> ⚠\uFE0F Alert: ${e.message}")
        }
    }

    // Fonction pour afficher un client
    open fun AffichageClient(){

            println("client ${this.intence} ")
            println("\nNom : ${this.nomClient} \n" +
                    "Numero compteur : ${this.numeroCompteur} \n" +
                    "Consommation : ${this.consommation}")
    }



}