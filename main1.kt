import java.lang.Math.random

// Fonction principale
fun main() {
    // Liste pour stocker les clients
    val listeClients = mutableListOf<Client>()
    var choix: Int

    do {
        // Affichage du menu
        println("\n==============================")
        println("1. Ajouter un client")
        println("2. Afficher les factures")
        println("3. Calculer la consommation moyenne")
        println("4. Quitter")
        println("==============================")
        print("Entrez votre choix : ")
        choix = readln().toInt()

        // Traiter le choix de l'utilisateur
        when (choix) {
            1 -> {
                // Choisir le type de client
                println("Quel type de client ?")
                println("1. Particulier")
                println("2. Entreprise")
                print("Entrez le type de client (1 ou 2) : ")
                val typeClient = readln().toInt()

                // Créer un nouveau client
                val client = if (typeClient == 1) {
                    creerClientParticulier() // Créer un client particulier
                } else {
                    creerClientEntreprise() // Créer un client entreprise
                }

                // Ajouter le client à la liste
                listeClients.add(client)
            }
            2 -> {
                // Afficher les factures des clients
                if (listeClients.isEmpty()) {
                    println("Aucun client dans la liste.")
                } else {
                    println("\nFactures des clients :")
                    listeClients.forEach {
                        println(it.afficherFacture())
                    }
                }
            }
            3 -> {
                // Calculer la consommation moyenne
                if (listeClients.isEmpty()) {
                    println("Aucun client pour calculer la consommation.")
                } else {
                    val consommationTotale = listeClients.sumOf { it.consommation }
                    val consommationMoyenne = consommationTotale / listeClients.size
                    println("La consommation moyenne est : $consommationMoyenne kWh.")
                }
            }
            4 -> println("Au revoir!")
            else -> println("Choix invalide, essayez à nouveau.")
        }
    } while (choix != 4) // Répéter le menu jusqu'à ce que l'utilisateur choisisse de quitter
}

// Classe de base Client
abstract class Client(
    val nom: String,
    val numeroCompte: String,
    val consommation: Double
) {
    // Méthode abstraite pour calculer la facture
    abstract fun calculerFacture(): Double

    // Méthode pour afficher la facture du client
    fun afficherFacture(): String {
        val facture = calculerFacture()
        return "Client: $nom, Compte: $numeroCompte, Consommation: $consommation kWh, Facture: $facture FCFA"
    }
}

// Classe pour un client particulier
class ClientParticulier(nom: String, numeroCompte: String, consommation: Double) :
    Client(nom, numeroCompte, consommation) {

    // Calculer la facture avec une réduction si la consommation est élevée
    override fun calculerFacture(): Double {
        return if (consommation > 1000) {
            val reduction = (consommation * 10) / 100
            (consommation - reduction) * 50
        } else {
            consommation * 50
        }
    }
}

// Classe pour un client entreprise
class ClientEntreprise(nom: String, numeroCompte: String, consommation: Double) :
    Client(nom, numeroCompte, consommation) {

    // Calculer la facture avec un tarif spécial pour les entreprises
    override fun calculerFacture(): Double {
        return consommation * 45
    }
}

// Fonction pour créer un client particulier
fun creerClientParticulier(): ClientParticulier {
    println("Entrez le nom du client particulier :")
    val nom = readln()

    println("Entrez le numéro de compte :")
    val numeroCompte = readln()

    val consommation = random() * 2000
    println("Consommation générée : $consommation kWh")

    return ClientParticulier(nom, numeroCompte, consommation)
}

// Fonction pour créer un client entreprise
fun creerClientEntreprise(): ClientEntreprise {
    println("Entrez le nom de l'entreprise :")
    val nom = readln()

    println("Entrez le numéro de compte :")
    val numeroCompte = readln()

    val consommation = random() * 5000
    println("Consommation générée : $consommation kWh")

    return ClientEntreprise(nom, numeroCompte, consommation)
}
