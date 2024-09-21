import kotlin.reflect.typeOf

// Fonction pour vérifier si la consommation est anormale
fun verifierConsommationAnormale(consommation: Double) {
    if (consommation > 5000) {
        throw Exception("Vous avez atteint la limite maximal de consommation avec : $consommation kWh")
    }
}

// Fontion pour verifier si un string est une consomation valide en Double
fun verifConsoValide(entre: String){
    val doubleForm: Double
    try{
        doubleForm = entre.toDouble()
    }catch(e: Exception){
        throw Exception("Entré non valide! Nombres uniquement svp")
    }
    if(doubleForm < 0){
        throw Exception("Erreur! Consomation négative!")
    }
}

// Fonction pour calculer la facture avec gestion des exceptions
fun calculerFacture(consommation: Double?): Double {
    if (consommation == null || consommation <= 0) {
        throw IllegalArgumentException("Consommation invalide.")
    }

    // Vérifier si la consommation est anormale
    verifierConsommationAnormale(consommation)

    val tauxParKwh = 50.0 // Tarif par kWh en FCFA

    return if (consommation <= 1000) consommation*tauxParKwh else 0.9 * consommation*tauxParKwh
}

// Fonction pour calculer la moyenne des consommations
fun calculerMoyenneConsommation(consommations: List<Double?>): Double {
    val total = consommations.filterNotNull().sum()
    return total / consommations.size
}

abstract class Client(
    val nom: String,
    val compteur: Int,
    var consommation: Double
){
    open val seuilDeConsomationNormale: Double = 5000.0

    open fun verifierConsommationAnormale(){
        if (consommation > seuilDeConsomationNormale) {
            throw Exception("Vous avez atteint la limite maximal de consommation avec : $consommation kWh")
        }
    }

    open fun calculerFacture():Double{
        if (consommation == null || consommation <= 0) {
            throw IllegalArgumentException("Consommation invalide.")
        }
        this.verifierConsommationAnormale()
        val tauxParKwh = 50.0 // Tarif par kWh en FCFA
        return if (consommation <= 1000) consommation*tauxParKwh else 0.9 * consommation*tauxParKwh
    }
}

class ClientParticulier(
    nom: String,
    compteur: Int,
    consommation: Double
): Client(nom, compteur, consommation)

class ClientEntreprise(
    nom: String,
    compteur: Int,
    consommation: Double,
): Client(nom, compteur, consommation){
    var activite: String = ""

    override val seuilDeConsomationNormale: Double = 50000.0

    override fun calculerFacture(): Double {
        if (consommation == null || consommation <= 0) {
            throw IllegalArgumentException("Consommation invalide.")
        }
        this.verifierConsommationAnormale()
        val tauxParKwh = 30.0 // Tarif par kWh en FCFA
        return if (consommation <= 1000) consommation*tauxParKwh else 0.9 * consommation*tauxParKwh
    }
}


// Fonction pour afficher un menu
fun afficherMenu(items: List<String>){
    items.forEachIndexed { index, s -> println("\t\t$index) $s") }
}

// Fonction principale du programme
fun main() {
    //Options du menu principale
    var optionsMenuPrincipal = listOf(
        "Voir la liste des clients",
        "Ajouter un client",
        "Calculer la consomation moyenne"
    )
    var choix = 0
    // Affichage du message de bienvenue
    println("\n\n" +
            "\n" +
            "\n\t=====================================")
    println("   \t \uD83D\uDC4B Bienvenue à ENEO Cameroun     ")
    println("\t=====================================\n")

    afficherMenu(optionsMenuPrincipal)


    /*println("Veuillez entrer votre nom: ")
    var name =  readln()
    println("Entrez plusieurs consommations (n pour arreter):")

    // Liste pour stocker les consommations
    val consommations = mutableListOf<Double?>()
    while(true){
        println("Entrez une consomation: ")
        var ans = readln()
        if(ans == "n") break
        else {
            try {
                verifConsoValide(ans)
                consommations.add(ans.toDouble())
            }catch(e: Exception){
                println(e.message)
                continue
            }
        }
        println("Vous avez entré: $ans")
    }

    println("La moyenne de consomation est: ${calculerMoyenneConsommation(consommations)}")*/
}
