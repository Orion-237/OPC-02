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

    override fun toString(): String {
        return "\t$nom\t$compteur\t$consommation"
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
    var activite: String = ""
): Client(nom, compteur, consommation){
    override val seuilDeConsomationNormale: Double = 50000.0

    override fun toString(): String {
        return "\t$nom\t$compteur\t$consommation\t$activite"
    }

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
    items.forEachIndexed { index, s -> println("\t\t${index+1}) $s") }
}

// Fonction qui affiche la liste des clients
fun afficherListeClients(clients: MutableList<Client>){
    println("\tNom\tCompteur\tConsomation\tActivité")
    clients.forEachIndexed { index, client ->
        print("${index+1})")
        println(client.toString())
    }

    var total = 0.0
    for (client in clients){
        total += client.consommation
    }
    println("Moyenne des consomations: ${total/clients.size}")
    readln()
}

// Fonction pour ajouter un client
fun ajouterClient(clients: MutableList<Client>){
    println("Entrez le nom du client: ")
    var nom = readln()
    println("Entrez le numero du compteur: ")
    var compteur = readln().toInt()
    println("Entrez la consomation")
    var consomation = readln().toDouble()

    var newbie: Client

    println("Is the client an Enterprise?(y or n): ")
    if (readln() == "y"){
        print("Enterprise description: ")
        var activite = readln()
        newbie = ClientEntreprise(nom, compteur, consomation, activite)
        clients.add(newbie)
    }else{
        newbie = ClientParticulier(nom, compteur, consomation)
        clients.add(newbie)
    }
    println("Client ajouté: ${newbie.toString()}")
    readln()
}

// Fonction principale du programme
fun main() {
    var clients: MutableList<Client> = mutableListOf(
        ClientParticulier("Abena Alex", 111, 2322.9),
        ClientEntreprise("Numérique", 142, 12000.0, "Prestation de services informatiques"),
        ClientParticulier("Obono Marie", 123, 1034.4),
    )

    //Options du menu principale
    val optionsMenuPrincipal = listOf(
        "Voir la liste des clients",
        "Ajouter un client",
        "Quitter"
    )

    // Afficher le menu et demander le choix de l'utilisateur et executer la tache correspondente
    var choix = 0 // Choix de l'utilizateur dans le menu
    var inApp: Boolean = true
    while(inApp){
        try {
            // Affichage du message de bienvenue
            println("\n\n" +
                    "\n" +
                    "\n\t=====================================")
            println("   \t \uD83D\uDC4B Bienvenue à ENEO Cameroun   ")
            println("\t=====================================\n")

            afficherMenu(optionsMenuPrincipal)
            print("\tEntrez votre choix: ")
            choix = readln().toInt()
            if(choix !in 1..optionsMenuPrincipal.size) throw Exception()
        }catch (e: Exception){
            println("Entrez seulement des nombres entre 1 et ${optionsMenuPrincipal.size}")
            print("Appuyez sur entrer: ")
            readln()
            continue
        }

        when(choix){
            1 -> afficherListeClients(clients)
            2 -> ajouterClient(clients)
            3 -> inApp = false
        }
    }


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
