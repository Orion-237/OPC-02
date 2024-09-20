// Fonction pour vérifier si la consommation est anormale
fun verifierConsommationAnormale(consommation: Double) {
    if (consommation > 5000) {
        throw Exception("Vous avez atteint la limite maximal de consommation avec : $consommation kWh")
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
    return consommation * tauxParKwh
}

// Fonction pour calculer la moyenne des consommations
fun calculerMoyenneConsommation(consommations: List<Double?>): Double {
    val total = consommations.filterNotNull().sum()
    return total / consommations.size
}

// Fonction principale du programme
fun main() {
    // Affichage du message de bienvenue
    println("\n\n" +
            "\n" +
            "\n\t=====================================")
    println("   \t \uD83D\uDC4B Bienvenue à ENEO Cameroun     ")
    println("\t=====================================\n")

    // Liste des clients (nom, compteur, consommation)
    val clients = listOf(
        Triple("Mathieu Ntono", 12345, 200.0),
        Triple("Francis Tala", 67890, 300.0),
        Triple("Paul Biya", 11223, 1500.0),
        Triple("Luc Mbarga", 44556, 600.0),
        Triple("Anne Ndam", 78901, 5200.0) // Consommation anormale
    )

    // 

    // Liste pour stocker les consommations
    val consommations = mutableListOf<Double?>()

    // Itération sur chaque client pour afficher les factures ou erreurs
    for ((nom, compteur, consommation) in clients) {
        try {
            println("$nom (Compteur: $compteur) -> \uD83E\uDDEE Consommation: $consommation kWh | \uD83D\uDCB0 Facture: ${calculerFacture(consommation)} FCFA")
        } catch (e: Exception) {
            println("$nom (Compteur: $compteur) -> ⚠\uFE0F Alert: ${e.message}")
        }
        consommations.add(consommation)
    }

    // Calcul et affichage de la consommation moyenne
    val consommationMoyenne = calculerMoyenneConsommation(consommations)
    println("\n=============================================================")
    println("La consommation moyenne des clients pour ce mois est : $consommationMoyenne kWh")
    println("=============================================================\n\n\n")
}
