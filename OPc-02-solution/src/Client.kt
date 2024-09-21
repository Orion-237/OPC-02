
fun creerClient(type: Int): Client {

    println("------------------------------")
    println("Création d'un nouveau client !")


    println("Entrez le nom du client :")
    val nomDuClient = readLine()
    println("Entrez le numéro de compteur :")
    val numeroCompteur = readLine()?.toInt()
    println("Entrez la consommation électrique :")
    val consommationElectrique = readLine()?.toDouble()

    return if (type == 1) {
        ClientParticulier(nomDuClient, numeroCompteur, consommationElectrique)
    } else {
        ClientEntreprise(nomDuClient, numeroCompteur, consommationElectrique)
    }
}
