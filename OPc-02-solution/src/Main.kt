

fun main() {
    println("\t \uD83D\uDC4B Bienvenue à l'application de gestion d'énergie !     ")
    println("\t=====================================\n")

    val listeClients: MutableList<Client> = mutableListOf()
    val listeConsommations = mutableListOf<Double?>()
    var choixSortie: Int? = 2

    while (choixSortie == 2) {
        println("1- Ajouter des clients\n" +
                "2- Afficher les factures des clients \n" +
                "3- Calcul de la consommation moyenne\n")
        println("Entrez votre choix :")
        val choixOption = readLine()?.toInt()

        when (choixOption) {
            1 -> {
                println("Entrez le nombre de clients :")
                val nombreClients = readLine()?.toInt() ?: 0
                println("Quel type d'enregistrement ? \n1. Client particulier 2. Client d'entreprise")
                val typeClient = readLine()?.toInt()
                repeat(nombreClients) {
                    listeClients.add(creerClient(typeClient))
                }
                listeClients.forEach { it.afficherClient() }
            }
            2 -> {
                listeClients.forEach { it.afficherFacture() }
            }
            3 -> {
                listeClients.forEach { listeConsommations.add(it.consommation) }
                val moyenneConsommation = calculerMoyenneEnergie(listeConsommations)
                println("\n=============================================================")
                println("La consommation moyenne des clients pour ce mois est : $moyenneConsommation kWh")
                println("=============================================================\n\n\n")
            }
            else -> println("Choix non disponible")
        }

        println("Voulez-vous quitter le programme : \n1. oui et 2. non")
        choixSortie = readLine()?.toInt() ?: 2
    }
}

fun calculerMoyenneEnergie(listeConsommations: List<Double?>): Double {
    val totalEnergie = listeConsommations.filterNotNull().sum()
    return totalEnergie / listeConsommations.size
}

