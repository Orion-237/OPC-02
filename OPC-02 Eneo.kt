
fun main() {
  val hello = "Bienvenue sur l'application de facturation d'électricité"
  println(hello)

  println("Veuillez saisir vos informatons")
  print("Entrez votre nom :")
  val nom = readLine()
  print("Entrez votre numéro de compteur :")
  val NumCompteur = readLine()
  print("Entrez votre consommation en Kwh :")
  val ConsoKwh = readLine()
  println("Nom: $nom")
  println("Numéro de compteur: $NumCompteur")
  println("Consommation: $ConsoKwh")

  val Tarif: Double=50.0
  var Facture : Double = ConsoKwh*Tarif
  var Reduction : Double = 0.0
  println("$Facture FCFA")

  if (ConsoKwh>1000) { Reduction=($Facture*10)/100 };
  println("votre facture est de $Reduction FCFA") }
}
fun calculerFacture() {}

