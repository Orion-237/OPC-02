import java.util.InputMismatchException
import java.util.Scanner


class Client(val name: String,val meterNumber: String, val consumption: Double){
    fun calculatebill() = if (consumption>1000) 0.9*consumption*50 else consumption*50

    val bill = calculatebill()

}

fun main(){
    println("Bienvenue a l'application de facturation d'electricite\n")

    val reader = Scanner(System.`in`)
    var nextClient = true
    var name: String
    var meterNumber: String
    var consumption = 0.0
    var currentClient: Client
    var clients: MutableList<Client> = mutableListOf()
    var answer = ""
    var sum=0.0
    var average: Double

    while (nextClient){
        print("What's your name?\t")
        name = reader.nextLine()
        println()

        print("What's your meter number?\t")
        meterNumber = reader.nextLine()
        println()

        try {
            print("What's your consumption? (in kWh)\t")
            consumption = reader.nextDouble()
            println()
            if (consumption<0) throw ArithmeticException("Consumption can't be negative")
        }
        catch (e: ArithmeticException){
            while (consumption<0){
                println("Consumption can't be negative")
                print("What's your consumption? (in kWh)\t")
                consumption = reader.nextDouble()
                println()
            }
        }
        currentClient = Client(name,meterNumber,consumption)
        clients.add(currentClient)

        println("Hello, $name")
        println("Your meter number is $meterNumber and your consumption is $consumption kWh")

        println("Hence, your bill is ${currentClient.bill}\n")
//        bills.add(bill)

        print("Is there another Client? (Y/N)\t")
        answer = reader.nextLine()

        while (answer!= "Y" && answer!="y" && answer!="n" && answer!="N" ){
            print("Is there another Client? (Y/N)\t")
            answer = reader.nextLine()
        }

        if (answer=="n" || answer=="N") nextClient=false
    }

    for (client in clients){
        sum+=client.bill
    }
    average = sum/clients.size
    println("\nYour average consumption is $average")







}
