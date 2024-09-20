import java.util.*

// Fonction pour calculer la moyenne des consommations
fun calculerMoyenneConsommation(consommations: List<Double?>): Double {
    val total = consommations.filterNotNull().sum()
    return total / consommations.size
}

// Fonction pour cree un client
fun creationClient(choix : Int) : Client{

    println("###########################################")
    println("Creation client !")
    println("###########################################")

    // Saisie des information
    println("Entrez le nom client :")
    var nomClient = readLine()
    println("Entrez le numéro de compteur :")
    var numCompteur = readLine()?.toInt()
    println("Entrez la consommation :")
    var consommation = readLine()?.toDouble()

    if(choix == 1 ){
        return ClientParticulier(nomClient, numCompteur,consommation )
    }

    return ClientEntreprise(nomClient, numCompteur,consommation )

}


// Fonction principale du programme
fun main() {
    // Affichage du message de bienvenue
    println("\n\n" +
            "\n" +
            "\n\t=====================================")
    println("   \t \uD83D\uDC4B Bienvenue à l'application de facturation d'électricité !     ")
    println("\t=====================================\n")


    //Creation de plusieurs clients
    val clients : MutableList<Client> = mutableListOf()

    // Liste pour stocker les consommations
    val consommations = mutableListOf<Double?>()

    var choixContinu :Int? = 2
    do{


        println("1- Ajouter des clients\n" +
                "2- Afficher les factures des clients \n" +
                "3- calcule de la consommation moyenne\n" )


        println("Entrez votre choix :")
        var choixMenu = readLine()?.toInt()
        when(choixMenu){
            1 -> {
                //ajouter les client
                println("Entrez le nombre de client a enregistrer :")
                var nbrClient  = readLine()?.toInt()

                println("Qu'elle type d'enregistre \n" +
                        "1. Client particulier 2. Client d'entreprise")
                var choixclient : Int?  = readLine()?.toInt()

                if(choixclient == 1){

                    for (i in 0..nbrClient!!-1){

                        clients.add(creationClient(choixclient))
                    }
                    // Affichage des informations saisie
                    for (client in clients){
                        client.AffichageClient()
                    }

                }else if(choixclient == 2){
                    for (i in 0..nbrClient!!-1){

                        clients.add(creationClient(choixclient))
                    }
                    // Affichage des informations saisie
                    for (client in clients){
                        client.AffichageClient()
                    }
                }else{
                    println("Choix non trouver")
                }


            }
            2 -> {
                // Affichage la facturation
                for (client in clients){
                    client.AffichageFacturation()
                }
            }
            3 -> {

                for (client in clients) {
                    consommations.add(client.consommation)
                }

                // Calcul et affichage de la consommation moyenne
                val consommationMoyenne = calculerMoyenneConsommation(consommations)
                println("\n=============================================================")
                println("La consommation moyenne des clients pour ce mois est : $consommationMoyenne kWh")
                println("=============================================================\n\n\n")

            }
            else -> println("Choix non disponible")
        }
        println("Voulez vous quitter le programme : \n" +
                "1. oui et 2. non")
        choixContinu = readLine()?.toInt()!!
    }while(choixContinu == 2)




}
